package raven.sqdev.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.SWT;

import raven.sqdev.misc.SQDevInfobox;

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
	 *            A file pointing to the main directory the search should start
	 *            at
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
	 * @return A <code>File</code> that holds a reference to the desired folder
	 *         or <code>null</code> if the folder couldn't be found.
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
	 * Checks if the given directory or one of it's sub-directories contain a
	 * file with the specified name
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
	 * @return The desired file or <code>null</code> if the file couldn't be
	 *         found
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
	 * Deletes the given file. If the given file is a directory it will delete
	 * the directories content first and afterwards the directory itself.<br>
	 * This method will not delete files with a name specified in
	 * <code>filesToPreserve</code>
	 * 
	 * @param file
	 *            The file/directroy to be deleted
	 * @param filesToPreserve
	 *            A list of fileNames/directoryNames that should not get deleted
	 * @return <code>True</code> if successfull
	 */
	public static boolean deleteFilesWithException(File file,
			ArrayList<String> filesToPreserve) {
		if (filesToPreserve.contains(file.getName())) {
			// don't delete this file but return true as this behaviour is
			// wanted
			return true;
		}
		
		// check if it's a directory
		if (file.isDirectory()) {
			String[] children = file.list();
			for (int i = 0; i < children.length; i++) {
				// delete every single file/directory in here
				boolean success = deleteFilesWithException(
						new File(file, children[i]), filesToPreserve);
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
	 * Deletes the given file. If the given file is a directory it will delete
	 * the directories content first and afterwards the directory itself
	 * 
	 * @param file
	 *            The file/directory to be deleted
	 * @return <code>True</code> if successful
	 */
	public static boolean deleteFiles(File file) {
		return deleteFilesWithException(file, new ArrayList<String>());
	}
	
	/**
	 * This method will copy the given file to the specified path. If the file
	 * is a directory it will recursively copy all sub-directories and files.
	 * <br>
	 * Files or directories specified in the ignore list won't get copied
	 * 
	 * @param directory
	 *            The file/directory that should get copied
	 * @param destination
	 *            The path where the file(s) should get copied to
	 * @param filesToIgnore
	 *            A list containing all fileNames of files/directories that
	 *            shouldn't be copied at all
	 * @param respectHiddenFiles
	 *            Indicates whether the hidden nature of files should be
	 *            respected. If this is set to <code>true</code> hidden
	 *            resources (prefixed by a dot) will be ignored during copying
	 */
	public static void copyFilesWithExceptions(File directory,
			IPath destination, ArrayList<String> filesToIgnore,
			boolean respectHiddenFiles) {
		
		if (directory.isFile() && !filesToIgnore.contains(directory.getName())
				&& !(respectHiddenFiles
						&& directory.getName().startsWith("."))) {
			// copy the file if it shouldn't be ignored
			try {
				copyFile(directory, destination.append(directory.getName()));
			} catch (IOException e) {
				e.printStackTrace();
				
				// inform the user about the failure
				String message = "Failed at copying file \""
						+ directory.getName() + "\"!\nReason: "
						+ ((e.getMessage() == null || e.getMessage().isEmpty())
								? "Unknown" : e.getMessage());
				
				SQDevInfobox info = new SQDevInfobox(message, SWT.ERROR);
				
				info.open();
			}
		} else {
			if (directory.isDirectory()
					&& !filesToIgnore.contains(directory.getName())) {
				try {
					// copy the directory itself
					copyFile(directory,
							destination.append(directory.getName()));
					
					// all contained files have to be contained in the copied
					// directory -> adjust destination
					destination = destination.append(directory.getName());
					
					for (File currentFile : directory.listFiles()) {
						copyFilesWithExceptions(currentFile, destination,
								filesToIgnore, respectHiddenFiles);
					}
				} catch (IOException e) {
					e.printStackTrace();
					
					String fileType = (directory.isFile()) ? "file" : "folder";
					
					String message = "Failed at copying " + fileType + " \""
							+ directory.getName() + "\"!\nReason: "
							+ ((e.getMessage() == null
									|| e.getMessage().isEmpty()) ? "Unknown"
											: e.getMessage());
					
					SQDevInfobox info = new SQDevInfobox(message, SWT.ERROR);
					
					info.open();
				}
			}
		}
	}
	
	/**
	 * Copies the given file to the given location.
	 * 
	 * @param file
	 *            The original file
	 * @param destination
	 *            The path to where the file should get copied to
	 * @throws IOException
	 */
	public static void copyFile(File file, IPath destination)
			throws IOException {
		if (file.isFile()) {
			BufferedReader in = new BufferedReader(new FileReader(file));
			
			File outputFile = new File(destination.toOSString());
			
			if (!outputFile.exists()) {
				// create the output file
				outputFile.createNewFile();
			}
			
			FileWriter out = new FileWriter(outputFile);
			
			String content = "";
			
			// gather the file's content
			String currentLine;
			while ((currentLine = in.readLine()) != null) {
				content += currentLine + "\n";
			}
			
			if (!content.isEmpty()) {
				// remove the last newLine so that the copied file does not
				// differ
				// from the original
				content.substring(0, content.length() - 1);
			}
			
			// write the content into the new file
			out.write(content);
			
			in.close();
			out.close();
		} else {
			if (destination.toFile().exists()
					&& destination.lastSegment().equals(file.getName())) {
				// only create a new directory if the existing file is not a
				// directory
				if (!destination.toFile().isDirectory()) {
					destination.append(file.getName());
				} else {
					return;
				}
			}
			
			destination.toFile().mkdir();
		}
	}
}
