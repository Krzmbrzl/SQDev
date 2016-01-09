package raven.sqdev.wizards.exceptions;

import raven.sqdev.exceptions.SQDevException;

public class FailedAtCreatingFileException extends SQDevException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3841747047339787544L;

	public FailedAtCreatingFileException() {}
	
	public FailedAtCreatingFileException(String message) {
		super(message);
	}
	
	public FailedAtCreatingFileException(Throwable cause) {
		super(cause);
	}
	
	public FailedAtCreatingFileException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FailedAtCreatingFileException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
