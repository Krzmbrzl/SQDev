package raven.sqdev.exceptions;

/**
 * An excpetion that is thrown whenever a bad syntax occurs
 * 
 * @author Raven
 *
 */
public class BadSyntaxException extends SQDevException {

	private static final long serialVersionUID = 2819258652076585225L;
	
	
	public BadSyntaxException() {
		super();
	}
	
	public BadSyntaxException(String message) {
		super(message);
	}
	
	public BadSyntaxException(Throwable cause) {
		super(cause);
	}
	
	public BadSyntaxException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BadSyntaxException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
