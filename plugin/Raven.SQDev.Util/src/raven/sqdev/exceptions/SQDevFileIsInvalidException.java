package raven.sqdev.exceptions;

/**
 * An exception indicating that a SQDevFile is invalid when trying to do
 * something on it
 * 
 * @author Raven
 *		
 */
public class SQDevFileIsInvalidException extends SQDevException {
	
	private static final long serialVersionUID = 8841899739011003043L;
	
	public SQDevFileIsInvalidException() {
		super("The SQDevFile is invalid!");
	}
	
	public SQDevFileIsInvalidException(String message) {
		super(message);
	}
	
	public SQDevFileIsInvalidException(Throwable cause) {
		super(cause);
	}
	
	public SQDevFileIsInvalidException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SQDevFileIsInvalidException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
