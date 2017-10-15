package raven.sqdev.fileSystemListener;

import java.io.File;
import java.nio.file.Path;

/**
 * This object contains all necessary information about a change in the
 * filesystem
 * 
 * @author Raven
 *
 */
public class FileSystemChangeEvent {
	
	/**
	 * The file change type
	 */
	private EFileChangeType type;
	/**
	 * Path of the file the listener is registered to
	 */
	private Path rootPath;
	/**
	 * Path to the actual changed file (relative to {@link #rootPath})
	 */
	private Path filePath;
	
	
	/**
	 * Creates a new instance of this event
	 * 
	 * @param type
	 *            The <code>EFileChangeType</code> of the current change
	 * @param rootPath
	 *            The root path (The one the listener is registered to)
	 * @param filePath
	 *            The path to the changed file (relative to the rootPath)
	 * @param isSubDirectory
	 *            Whether or not the change occured in a subdirecpry of the
	 *            registered listener
	 */
	public FileSystemChangeEvent(EFileChangeType type, Path rootPath, Path filePath) {
		this.type = type;
		this.rootPath = rootPath;
		this.filePath = filePath;
	}
	
	/**
	 * Gets the root-path of this change (The one the listener is registered for
	 */
	public Path getRootPath() {
		return rootPath;
	}
	
	/**
	 * Gets the path of the actual changed file (relative to the root-path)
	 */
	public Path getFilePath() {
		return filePath;
	}
	
	/**
	 * Gets the fullpath to the changed file
	 */
	public Path getFullPath() {
		return rootPath.resolve(filePath);
	}
	
	/**
	 * Gets the type of this file change
	 */
	public EFileChangeType getType() {
		return type;
	}
	
	/**
	 * Gets the changed file
	 */
	public File getChangedFile() {
		return getFullPath().toFile();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("FileSystemChangeEvent\n");
		builder.append("\tChanged file: " + getFullPath().toString() + "\n");
		builder.append("\tChangeType: " + getType().toString() + "\n");
		
		return builder.toString();
	}
}
