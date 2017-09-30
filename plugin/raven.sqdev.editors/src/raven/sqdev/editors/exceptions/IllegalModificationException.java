package raven.sqdev.editors.exceptions;

/**
 * This exception is thrown whenever an attempt is made to modify something that
 * isn't allowed to be modified at this point in time.
 * 
 * @author Raven
 *		
 */
public class IllegalModificationException extends SQDevEditorException {
	
	private static final long serialVersionUID = -827964495302148586L;
	
	public IllegalModificationException() {
		super();
	}
	
	public IllegalModificationException(String message) {
		super(message);
	}
	
	public IllegalModificationException(Throwable cause) {
		super(cause);
	}
	
	public IllegalModificationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IllegalModificationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
