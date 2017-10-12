package raven.sqdev.fileSystemListener;

/**
 * An enum containg the different possibilities for a file change
 * 
 * @author Raven
 *
 */
public enum EFileChangeType {
	/**
	 * The file was created
	 */
	CREATED,
	/**
	 * The file was deleted
	 */
	DELETED,
	/**
	 * The file has changed
	 */
	CHANGED;
}
