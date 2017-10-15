package raven.sqdev.exceptions;

/**
 * This exception is thrown whenever an object is instantiated through an
 * constructor (of a super class) that wasn't intended to be used with that
 * particular class
 * 
 * @author Raven
 * 		
 */
public class IllegalConstructorException extends SQDevEditorException {
	
	private static final long serialVersionUID = -5133365501589582258L;
	
	/**
	 * @param allowedConstructor
	 *            The name of the constructor that would be valid to use (with
	 *            arguments in brackets!) e.g.
	 *            <code>"MyConstructor(String arg0, int arg1)"</code>
	 * @see IllegalConstructorException
	 */
	public IllegalConstructorException(String allowedConstructor) {
		super(generateMessage(allowedConstructor));
	}
	
	/**
	 * Assembles the exception message<br>
	 * Returns an empty string if the given <code>allowedConstructor</code> is invalid
	 * @param allowedConstructor see {@linkplain #IllegalConstructorException(String)}
	 * @return
	 */
	private static String generateMessage(String allowedConstructor) {
		if(allowedConstructor.isEmpty() || !allowedConstructor.contains("(")) {
			// allowedConstructor is invalid -> empty message
			return "";
		}
		
		String className = allowedConstructor.substring(0, allowedConstructor.indexOf("("));
				
		String message = "Illegal constructor usage for instantiation of an object of class '"
				+ className + "'\nUse constructor '" + allowedConstructor + "' instead";
				
		return message;
	}
	
	public IllegalConstructorException() {
		super();
	}
	
	public IllegalConstructorException(Throwable cause) {
		super(cause);
	}
	
	public IllegalConstructorException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IllegalConstructorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
