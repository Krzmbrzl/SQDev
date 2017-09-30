package raven.sqdev.editors.exceptions;

import raven.sqdev.exceptions.SQDevException;

/**
 * An exception thrown from an editor within the <code>SQDev</code>-plugin
 * @author Raven
 *
 */
public class SQDevEditorException extends SQDevException {
	
	private static final long serialVersionUID = -9019020497958203180L;

	public SQDevEditorException() {
		super();
	}
	
	public SQDevEditorException(String message) {
		super(message);
	}
	
	public SQDevEditorException(Throwable cause) {
		super(cause);
	}
	
	public SQDevEditorException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SQDevEditorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
