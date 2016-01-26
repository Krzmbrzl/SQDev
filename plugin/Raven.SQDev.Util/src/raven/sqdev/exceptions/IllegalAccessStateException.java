package raven.sqdev.exceptions;

/**
 * An exception indicating that the accessability of a file is not as it was expected to be
 * @author Raven
 *
 */
public class IllegalAccessStateException extends SQDevException {
	
	private static final long serialVersionUID = -3677753360548335575L;

	public IllegalAccessStateException() {
	}
	
	public IllegalAccessStateException(String message) {
		super(message);
	}
	
	public IllegalAccessStateException(Throwable cause) {
		super(cause);
	}
	
	public IllegalAccessStateException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IllegalAccessStateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
