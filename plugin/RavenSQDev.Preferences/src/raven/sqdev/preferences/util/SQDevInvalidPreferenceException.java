package raven.sqdev.preferences.util;

import org.eclipse.swt.SWT;

import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.misc.SQDevInfobox;

public class SQDevInvalidPreferenceException extends SQDevCoreException {
	
	private static final long serialVersionUID = 5485599572842076878L;
	
	/**
	 * The prefix for every message
	 */
	protected String prefix = "The current preferences are not valid!\n\n";
	
	/**
	 * The default message to inform the user about the problem
	 */
	protected String defaultMessage = prefix + "Please consider changing them.";
	
	public SQDevInvalidPreferenceException() {
		inform(null);
	}
	
	/**
	 * 
	 * @param message The message stating the reason for the invalid preferences
	 */
	public SQDevInvalidPreferenceException(String message) {
		super(message);
		
		inform(message);
	}
	
	public SQDevInvalidPreferenceException(Throwable cause) {
		super(cause);
		
		String message = (cause.getMessage() == null || cause.getMessage().isEmpty()) ? "Unknown"
				: cause.getMessage();
				
		inform(message);
	}
	
	/**
	 * Informs the user about the invalid preferences
	 * 
	 * @param message
	 *            The message explaining the reason
	 */
	protected void inform(String message) {
		// open a infobox to inform the user
		if (message == null || message.isEmpty()) {
			// if no message is given use the default one
			message = defaultMessage;
		} else {
			message = prefix + "Reason: " + message;
		}
		
		SQDevInfobox info = new SQDevInfobox(message, SWT.ICON_ERROR | SWT.OK);
		
		info.open();
	}
}
