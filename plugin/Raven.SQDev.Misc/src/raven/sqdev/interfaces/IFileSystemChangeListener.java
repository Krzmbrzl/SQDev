package raven.sqdev.interfaces;

import java.nio.file.Path;

import raven.sqdev.fileSystemListener.FileSystemChangeEvent;

/**
 * An interface describing a FileSystemListener
 * 
 * @author Raven
 *
 */
public interface IFileSystemChangeListener {
	
	/**
	 * Gets the path this listener should be notified about changes.
	 */
	public Path getConfiguredPath();
	
	/**
	 * Gets called whenever the file of this listener has changed
	 * 
	 * @param event
	 *            The <code>FileSystemChangeEvent</code> containing all
	 *            necessary information about the change
	 */
	public void changed(FileSystemChangeEvent event);
}
