package raven.sqdev.editors.sqfeditor.exceptions;

import raven.sqdev.editors.exceptions.SQDevEditorException;

public class IllegalBlankException extends SQDevEditorException {
	
	private static final long serialVersionUID = 6667057385104170750L;

	public IllegalBlankException() {}
	
	public IllegalBlankException(String message) {
		super(message);
	}
	
	public IllegalBlankException(Throwable cause) {
		super(cause);
	}
	
	public IllegalBlankException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IllegalBlankException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
