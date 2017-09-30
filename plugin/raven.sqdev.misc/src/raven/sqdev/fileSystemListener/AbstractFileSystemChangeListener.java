package raven.sqdev.fileSystemListener;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;

import raven.sqdev.interfaces.IFileSystemChangeListener;

/**
 * An abstract implementation of an <code>IFileSystemChangeListener</code>
 * 
 * @author Raven
 *
 */
public abstract class AbstractFileSystemChangeListener implements IFileSystemChangeListener {
	
	/**
	 * The path this listener ois configured for
	 */
	private Path path;
	
	/**
	 * Creates a new instance of this
	 * <code>AbstractFileSystemChangeListener</code>
	 * 
	 * @param path
	 *            The <code>Path</code> this listener should watch for changes
	 */
	public AbstractFileSystemChangeListener(Path path) {
		Assert.isNotNull(path);
		
		this.path = path;
	}
	
	/**
	 * Creates a new instance of this
	 * <code>AbstractFileSystemChangeListener</code>
	 * 
	 * @param path
	 *            The path this listener should watch for changes
	 */
	public AbstractFileSystemChangeListener(String path) {
		Assert.isNotNull(path);
		
		this.path = Paths.get(path);
	}
	
	/**
	 * Creates a new instance of this
	 * <code>AbstractFileSystemChangeListener</code>
	 * 
	 * @param path
	 *            The <code>IPath</code> this listener should watch for changes
	 */
	public AbstractFileSystemChangeListener(IPath path) {
		Assert.isNotNull(path);
		
		this.path = Paths.get(path.toOSString());
	}
	
	/**
	 * Creates a new instance of this
	 * <code>AbstractFileSystemChangeListener</code>
	 * 
	 * @param path
	 *            The path this listener should watch for changes
	 */
	public AbstractFileSystemChangeListener(File file) {
		Assert.isNotNull(file);
		
		this.path = Paths.get(file.getAbsolutePath());
	}
	
	@Override
	public Path getConfiguredPath() {
		return path;
	}
	
}
