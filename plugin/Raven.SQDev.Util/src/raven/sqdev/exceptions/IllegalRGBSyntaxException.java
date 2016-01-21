package raven.sqdev.exceptions;

/**
 * An exception that is thrown when the syntax of the string that should be
 * decoded as an RGB is not of the proper syntax.
 * 
 * @author Raven
 *		
 */
public class IllegalRGBSyntaxException extends SQDevException {
	
	private static final long serialVersionUID = 7629178324751952523L;
	
	public IllegalRGBSyntaxException() {
	}
	
	public IllegalRGBSyntaxException(String message) {
		super(message);
	}
	
	public IllegalRGBSyntaxException(Throwable cause) {
		super(cause);
	}
	
	public IllegalRGBSyntaxException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IllegalRGBSyntaxException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
