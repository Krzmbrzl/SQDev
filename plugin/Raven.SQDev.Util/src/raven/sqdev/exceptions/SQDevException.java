package raven.sqdev.exceptions;

/**
 * An exception thrown from within the <code>SQDev</code>-plugin
 * @author Raven
 *
 */
public class SQDevException extends Exception {
	
	private static final long serialVersionUID = -8181652358185790669L;

	public SQDevException() {
		super();
	}
	
	public SQDevException(String message) {
		super(message);
	}
	
	public SQDevException(Throwable cause) {
		super(cause);
	}
	
	public SQDevException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SQDevException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
