package Exceptions;

public class UnexpectedInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7168748384403911985L;
	
	public UnexpectedInputException() {
		super("An unexpected input has been detected");
	}
}
