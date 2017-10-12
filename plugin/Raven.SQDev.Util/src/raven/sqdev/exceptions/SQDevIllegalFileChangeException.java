package raven.sqdev.exceptions;

/**
 * An exception indicating that there was an illeagl attempt to change an
 * SQDevFile.
 * 
 * @author Raven
 *		
 */
public class SQDevIllegalFileChangeException extends SQDevException {
	
	private static final long serialVersionUID = 2430239064812933356L;
	
	public SQDevIllegalFileChangeException() {
	}
	
	public SQDevIllegalFileChangeException(String message) {
		super(message);
	}
	
	public SQDevIllegalFileChangeException(Throwable cause) {
		super(cause);
	}
	
	public SQDevIllegalFileChangeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SQDevIllegalFileChangeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
