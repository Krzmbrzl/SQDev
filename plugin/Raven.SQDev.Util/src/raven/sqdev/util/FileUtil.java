package raven.sqdev.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;

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
					"Trying to copy the file \"" + file.getAbsolutePath() + "\" to \""
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
					"Failed at copying file \"" + targetFile.getName() + "\"", e);
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
					"Trying tocopy the folder \"" + folder.getAbsolutePath() + "\" to \""
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
				copyFolder(currentFile, (Path) destination.append(folder.getName()));
			} else {
				copyFile(currentFile, (Path) destination.append(folder.getName()));
			}
		}
		
		return targetFolder;
	}
	
}
