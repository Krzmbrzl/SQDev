package raven.sqdev.fileSystemListener;

import java.io.File;
import java.nio.file.Path;

import org.eclipse.core.runtime.IPath;

/**
 * This adapter provides pre-defined methods for the different file change-types
 * in addition to the generic change-method of
 * {@linkplain AbstractFileSystemChangeListener}
 * 
 * @see #anyChange(FileSystemChangeEvent)
 * @see #fileCreated(FileSystemChangeEvent)
 * @see #fileDeleted(FileSystemChangeEvent)
 * @see #fileChanged(FileSystemChangeEvent)
 * 
 * @author Raven
 *
 */
public abstract class FileSystemChangeAdapter extends AbstractFileSystemChangeListener {
	
	/**
	 * Creates a new instance of this <code>FileSystemChangeAdapter</code>
	 * 
	 * @param path
	 *            The <code>Path</code> this listener should watch for changes
	 */
	public FileSystemChangeAdapter(Path path) {
		super(path);
	}
	
	/**
	 * Creates a new instance of this <code>FileSystemChangeAdapter</code>
	 * 
	 * @param path
	 *            The path this listener should watch for changes
	 */
	public FileSystemChangeAdapter(String path) {
		super(path);
	}
	
	/**
	 * Creates a new instance of this <code>FileSystemChangeAdapter</code>
	 * 
	 * @param path
	 *            The <code>IPath</code> this listener should watch for changes
	 */
	public FileSystemChangeAdapter(IPath path) {
		super(path);
	}
	
	/**
	 * Creates a new instance of this <code>FileSystemChangeAdapter</code>
	 * 
	 * @param path
	 *            The path this listener should watch for changes
	 */
	public FileSystemChangeAdapter(File file) {
		super(file);
	}
	
	@Override
	public final void changed(FileSystemChangeEvent event) {
		// Forward the event to the respective methods
		switch (event.getType()) {
			case CHANGED:
				fileChanged(event);
				break;
			case CREATED:
				fileCreated(event);
				break;
			case DELETED:
				fileDeleted(event);
				break;
		}
		
		anyChange(event);
	}
	
	/**
	 * Gets called on any change after the specific method for this change has
	 * been called
	 * 
	 * @param event
	 *            The <code>FileSystemChangeEvent</code> with context
	 *            information about this change
	 */
	public void anyChange(FileSystemChangeEvent event) {
		
	}
	
	/**
	 * Gets called when a file is created
	 * 
	 * @param event
	 *            The <code>FileSystemChangeEvent</code> with context
	 *            information about this change
	 */
	public void fileCreated(FileSystemChangeEvent event) {
		
	}
	
	/**
	 * Gets called when a file gets deleted
	 * 
	 * @param event
	 *            The <code>FileSystemChangeEvent</code> with context
	 *            information about this change
	 */
	public void fileDeleted(FileSystemChangeEvent event) {
		
	}
	
	/**
	 * Gets called when a file changes it's content
	 * 
	 * @param event
	 *            The <code>FileSystemChangeEvent</code> with context
	 *            information about this change
	 */
	public void fileChanged(FileSystemChangeEvent event) {
		
	}
	
}
