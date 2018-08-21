package raven.sqdev.misc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;

import raven.sqdev.exceptions.SQDevException;

/**
 * A class containing various util methods for dealing with files and/or
 * directories
 * 
 * @author Raven
 * 
 */
public class FileSystemUtil {

	/**
	 * Checks if the given folder exists in the given directory or one of it's
	 * sub-directories
	 * 
	 * @param directory
	 *            A file pointing to the main directory the search should start at
	 * @param folderName
	 *            The name of the folder to match
	 * @return
	 */
	public static boolean containsFolder(File directory, String folderName) {
		return getFolder(directory, folderName) != null;
	}

	/**
	 * Gets the folder with the given name contained in the given directory
	 * 
	 * @param directory
	 *            The main directory that contains the desired folder
	 * @param folderName
	 *            The name of the desired folder (case-sensitive!)
	 * @return A <code>File</code> that holds a reference to the desired folder or
	 *         <code>null</code> if the folder couldn't be found.
	 */
	public static File getFolder(File directory, String folderName) {
		if (directory.getName().equals(folderName) && directory.isDirectory()) {
			return directory;
		}

		for (File currentFile : directory.listFiles()) {
			if (currentFile.isDirectory()) {
				// check if that's the desired folder
				if (currentFile.getName().equals(folderName)) {
					return currentFile;
				} else {
					// check if the folder is contained in the sub-directory
					File result = getFolder(currentFile, folderName);

					if (result != null) {
						return result;
					}
				}
			}
		}

		return null;
	}

	/**
	 * Checks if the given directory or one of it's sub-directories contain a file
	 * with the specified name
	 * 
	 * @param directory
	 *            The directory that eventually contains the file
	 * @param fileName
	 *            The name of the file to search
	 * @return <code>True</code> if the directory contains a file with the given
	 *         name
	 */
	public static boolean containsFile(File directory, String fileName) {
		return (getFile(directory, fileName) == null) ? false : true;
	}

	/**
	 * Gets the file with the given name within the specified directory and it's
	 * sub-directories
	 * 
	 * @param directory
	 *            The directory containing the file
	 * @param fileName
	 *            The name of the desired file
	 * @return The desired file or <code>null</code> if the file couldn't be found
	 */
	public static File getFile(File directory, String fileName) {
		if (directory.isFile()) {
			return (directory.getName().equals(fileName)) ? directory : null;
		}

		for (File currentFile : directory.listFiles()) {
			if (currentFile.isDirectory()) {
				// try to find the file in the directory
				File result = getFile(currentFile, fileName);

				if (result != null) {
					return result;
				}
			} else {
				// check if it's the desired file
				if (currentFile.getName().equals(fileName)) {
					return currentFile;
				}
			}
		}

		// if the file couldn't be found return null
		return null;
	}

	/**
	 * Deletes the given file. If the given file is a directory it will delete the
	 * directories content first and afterwards the directory itself.<br>
	 * This method will not delete files with a name specified in
	 * <code>filesToPreserve</code>
	 * 
	 * @param file
	 *            The file/directroy to be deleted
	 * @param filesToPreserve
	 *            A list of fileNames/directoryNames that should not get deleted
	 * @return <code>True</code> if successfull
	 */
	public static boolean deleteFilesWithException(File file, ArrayList<String> filesToPreserve) {
		if (filesToPreserve.contains(file.getName())) {
			// don't delete this file but return true as this behavior is
			// wanted
			return true;
		}

		// check if it's a directory
		if (file.isDirectory()) {
			String[] children = file.list();
			for (int i = 0; i < children.length; i++) {
				// delete every single file/directory in here
				boolean success = deleteFilesWithException(new File(file, children[i]), filesToPreserve);
				if (!success) {
					return false;
				}
			}
		}

		if (file.isFile() || file.listFiles().length == 0) {
			return file.delete();
		} else {
			// directories that contains preserved files have to be preserved as
			// well
			for (File currentFile : file.listFiles()) {
				if (!filesToPreserve.contains(currentFile.getName())) {
					return false;
				}
			}

			return true;
		}
	}

	/**
	 * Deletes the given file. If the given file is a directory it will delete the
	 * directories content first and afterwards the directory itself
	 * 
	 * @param file
	 *            The file/directory to be deleted
	 * @return <code>True</code> if successful
	 */
	public static boolean deleteFiles(File file) {
		return deleteFilesWithException(file, new ArrayList<String>());
	}

	/**
	 * This method will copy the given file to the specified path. If the file is a
	 * directory it will recursively copy all sub-directories and files. <br>
	 * Files or directories specified in the ignore list won't get copied
	 * 
	 * @param directory
	 *            The file/directory that should get copied
	 * @param targetDir
	 *            The path to the directory the file(s) should get copied to
	 * @param filesToIgnore
	 *            A list containing all fileNames of files/directories that
	 *            shouldn't be copied at all
	 * @param respectHiddenFiles
	 *            Indicates whether the hidden nature of files should be respected.
	 *            If this is set to <code>true</code> hidden resources (prefixed by
	 *            a dot) will be ignored during copying
	 */
	public static void copyFilesWithExceptions(File directory, IPath targetDir, ArrayList<String> filesToIgnore,
			boolean respectHiddenFiles) {
		if (directory.isFile() && !filesToIgnore.contains(directory.getName())
				&& !(respectHiddenFiles && directory.getName().startsWith("."))) {
			// copy the file if it shouldn't be ignored
			copyFile(directory, targetDir);
		} else {
			if (directory.isDirectory() && !filesToIgnore.contains(directory.getName())) {
				// copy the directory itself
				copyFolder(directory, targetDir, false);

				// all contained files have to be contained in the copied
				// directory -> adjust destination
				targetDir = targetDir.append(directory.getName());

				for (File currentFile : directory.listFiles()) {
					copyFilesWithExceptions(currentFile, targetDir, filesToIgnore, respectHiddenFiles);
				}
			}
		}
	}

	/**
	 * Copies the given file into the given destination
	 * 
	 * @param file
	 *            The file that should be copied
	 * @param targetDir
	 *            The directory in which the file should be copied
	 * @return The copied file in the new directory
	 */
	public static File copyFile(File file, IPath targetDir) {
		Assert.isNotNull(targetDir, "Target directory must not be null");
		Assert.isNotNull(file, "File to copy must not be null");
		Assert.isTrue(file.isFile(), "The given file is not actually a file");
		Assert.isTrue(targetDir.toFile().exists(), "The target deirectory does not exist");
		Assert.isTrue(targetDir.toFile().isDirectory(), "The given target directory is not actually a directory");

		File targetFile = targetDir.append(file.getName()).toFile();

		if (targetFile.exists()) {
			SQDevInfobox info = new SQDevInfobox(
					"Trying to copy the file \"" + file.getAbsolutePath() + "\" to \""
							+ targetDir.append(file.getName()).toOSString()
							+ "\" but the file does already exist.\n\nDo you want to overwrite the file?",
					SWT.ICON_QUESTION | SWT.YES | SWT.NO);

			int result = info.open();

			if (result == SWT.NO) {
				// abort copy process
				return null;
			}
		}

		try {
			Files.copy(file.toPath(), targetFile.toPath(), new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
		} catch (IOException e) {
			e.printStackTrace();

			SQDevInfobox info = new SQDevInfobox("Failed at copying file \"" + targetFile.getName() + "\"", e);
			info.open();
		}

		return targetFile;
	}

	/**
	 * Copies the given folder and it's content into the given destination
	 * 
	 * @param folder
	 *            The folder that should be copied
	 * @param targetDir
	 *            The directory in which the folder should be copied
	 * @param recursive
	 *            Indicates whether recursive copying should be done. That means
	 *            that all of the folders content should get copied as well
	 * @return The copied file in the new directory
	 */
	public static File copyFolder(File folder, IPath targetDir, boolean recursive) {
		Assert.isNotNull(targetDir, "Target directory must not be null");
		Assert.isNotNull(folder, "Folder to copy must not be null");
		Assert.isTrue(folder.isDirectory(), "The folder to copy is not actually a folder");
		Assert.isTrue(targetDir.toFile().exists(), "The target directory does not exist");
		Assert.isTrue(targetDir.toFile().isDirectory(), "The target directory is not actually a directory");

		// create the folder
		File targetFolder = targetDir.append(folder.getName()).toFile();

		if (targetFolder.exists()) {
			SQDevInfobox info = new SQDevInfobox("Trying tocopy the folder \"" + folder.getAbsolutePath() + "\" to \""
					+ targetDir.append(folder.getName()).toOSString()
					+ "\" but there is already a folder with this name.\n\n" + "Do you want to overwrite this folder?",
					SWT.ICON_QUESTION | SWT.YES | SWT.NO);

			int result = info.open();

			if (result == SWT.NO) {
				// abort copying process
				return null;
			}
		} else {
			targetFolder.mkdir();
		}

		if (recursive) {
			for (File currentFile : folder.listFiles()) {
				if (currentFile.isDirectory()) {
					copyFolder(currentFile, (Path) targetDir.append(folder.getName()), recursive);
				} else {
					copyFile(currentFile, (Path) targetDir.append(folder.getName()));
				}
			}
		}

		return targetFolder;
	}

	/**
	 * Gets the content of the given file
	 * 
	 * @param file
	 *            The <code>File</code> whose content should be obtained. Has to
	 *            exist!
	 * @return The file's content
	 * @throws SQDevException
	 *             If anything goes wrong
	 */
	public static String getContent(File file) throws SQDevException {
		if (!file.exists()) {
			throw new SQDevException("Failed at getting content of file \"" + file.getAbsolutePath() + "\"",
					new FileNotFoundException("The requested file does not exist"));
		}

		try {
			return readAll(new FileInputStream(file), (int) file.length());

		} catch (IOException e) {
			throw new SQDevException("Failed at getting content of file \"" + file.getAbsolutePath() + "\"", e);
		}
	}

	/**
	 * Reads the complete InputStream into a String.
	 * 
	 * @param in
	 *            The <code>InputStream</code> to read from
	 * @param size
	 *            The length of the <code>InputStream</code> or <code>-1</code> if
	 *            unknown.
	 * @return The created String
	 * @throws IOException
	 */
	public static String readAll(InputStream in, int size) throws IOException {
		byte[] initialBytes;

		if (size < 0) {
			initialBytes = new byte[in.available()];
		} else {
			initialBytes = new byte[size];
		}

		in.read(initialBytes);

		byte[] furtherBytes = new byte[0];
		// check that all input has been read
		char currentChar = (char) -1;
		if ((currentChar = (char) in.read()) != (char) -1) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			out.write(currentChar);

			while ((currentChar = (char) in.read()) != (char) -1) {
				// read rest of the inputStream
				out.write(currentChar);
			}

			furtherBytes = out.toByteArray();
		}

		return new String(initialBytes) + new String(furtherBytes);
	}

	/**
	 * Reads the complete InputStream into a String. If the size of the
	 * <code>InputStream</code> can be obtained
	 * <code>readAll(InputStream in, int size)</code> should be used for safer
	 * results.
	 * 
	 * @param in
	 *            The <code>InputStream</code> to read from
	 * @return The created String
	 * @throws IOException
	 */
	public static String readAll(InputStream in) throws IOException {
		return readAll(in, -1);
	}

	/**
	 * Gets all files in a directory and it's subdirectories
	 * 
	 * @param parentDir
	 *            The parent directory whose files should be obtained
	 * @return The list of files found by this method
	 */
	public static List<File> getAllSubFiles(File parentDir) {
		List<File> files = new ArrayList<File>();

		if (parentDir.isFile()) {
			files.add(parentDir);

			return files;
		}

		if (parentDir.isDirectory()) {
			for (File subFile : parentDir.listFiles()) {
				if (subFile.isFile()) {
					files.add(subFile);
				} else {
					if (subFile.isDirectory()) {
						files.addAll(getAllSubFiles(subFile));
					}
				}
			}
		}

		return files;
	}
}
