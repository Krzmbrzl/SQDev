package raven.sqdev.misc;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;

import raven.sqdev.exceptions.SQDevException;

/**
 * A class containing static util methods for files
 * 
 * @author Raven
 * 
 */
public class FileUtil {
	
	/**
	 * Copies the given file into the given destination
	 * 
	 * @param file
	 *            The file that should be copied
	 * @param destination
	 *            The directory in which the file should be copied
	 * @return The copied file in the new directory
	 */
	public static File copyFile(File file, Path destination) {
		Assert.isNotNull(destination);
		Assert.isNotNull(file);
		Assert.isTrue(file.isFile());
		Assert.isTrue(destination.toFile().exists());
		Assert.isTrue(destination.toFile().isDirectory());
		
		File targetFile = destination.append(file.getName()).toFile();
		
		if (targetFile.exists()) {
			SQDevInfobox info = new SQDevInfobox(
					"Trying to copy the file \"" + file.getAbsolutePath()
							+ "\" to \""
							+ destination.append(file.getName()).toOSString()
							+ "\" but the file does already exist.\n\nDo you want to overwrite the file?",
					SWT.ICON_QUESTION | SWT.YES | SWT.NO);
			
			int result = info.open();
			
			if (result == SWT.NO) {
				// abort copy process
				return null;
			}
		}
		
		try {
			// create the file
			targetFile.createNewFile();
			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String content = "";
			String currentLine = "";
			
			// get content
			while ((currentLine = reader.readLine()) != null) {
				content += currentLine + "\n";
			}
			
			if (content.length() > 0) {
				// remove last newLine
				content = content.substring(0, content.length() - 1);
				
				reader.close();
				
				// transfer content
				FileWriter writer = new FileWriter(targetFile);
				
				writer.write(content);
				
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			
			SQDevInfobox info = new SQDevInfobox(
					"Failed at copying file \"" + targetFile.getName() + "\"",
					e);
			info.open();
		}
		
		return targetFile;
	}
	
	/**
	 * Copies the given folder and it's content into the given destination
	 * 
	 * @param folder
	 *            The folder that should be copied
	 * @param destination
	 *            The directory in which the folder should be copied
	 * @return The copied file in the new directory
	 */
	public static File copyFolder(File folder, Path destination) {
		Assert.isNotNull(destination);
		Assert.isNotNull(folder);
		Assert.isTrue(folder.isDirectory());
		Assert.isTrue(destination.toFile().exists());
		Assert.isTrue(destination.toFile().isDirectory());
		
		// create the folder
		File targetFolder = destination.append(folder.getName()).toFile();
		
		if (targetFolder.exists()) {
			SQDevInfobox info = new SQDevInfobox(
					"Trying tocopy the folder \"" + folder.getAbsolutePath()
							+ "\" to \""
							+ destination.append(folder.getName()).toOSString()
							+ "\" but there is already a folder with this name.\n\n"
							+ "Do you want to overwrite this folder?",
					SWT.ICON_QUESTION | SWT.YES | SWT.NO);
			
			int result = info.open();
			
			if (result == SWT.NO) {
				// abort copying process
				return null;
			}
		} else {
			targetFolder.mkdir();
		}
		
		for (File currentFile : folder.listFiles()) {
			if (currentFile.isDirectory()) {
				copyFolder(currentFile,
						(Path) destination.append(folder.getName()));
			} else {
				copyFile(currentFile,
						(Path) destination.append(folder.getName()));
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
			throw new SQDevException(
					"Failed at getting content of file \""
							+ file.getAbsolutePath() + "\"",
					new FileNotFoundException(
							"The requested file does not exist"));
		}
		
		try {
			// BufferedReader reader = new BufferedReader(new FileReader(file));
			//
			// String content = "";
			// String currentLine = "";
			//
			// while ((currentLine = reader.readLine()) != null) {
			// content += (content.isEmpty()) ? currentLine : "\n" +
			// currentLine;
			// }
			//
			// reader.close();
			//
			// return content;
			
			return readAll(new FileInputStream(file), (int) file.length());
			
		} catch (IOException e) {
			throw new SQDevException("Failed at getting content of file \""
					+ file.getAbsolutePath() + "\"", e);
		}
	}
	
	/**
	 * Reads the complete InputStream into a String.
	 * 
	 * @param in
	 *            The <code>InputStream</code> to read from
	 * @param size
	 *            The length of the <code>InputStream</code> or <code>-1</code>
	 *            if unknown.
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
