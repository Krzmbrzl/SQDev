package raven.sqdev.exceptions;

/**
 * An exception indicating that something went wrong during a collection process
 * 
 * @author Raven
 * 		
 */
public class SQDevCollectionException extends SQDevException {
	
	private static final long serialVersionUID = 5076943149344521751L;
	
	public SQDevCollectionException(String message) {
		super(message);
	}
	
	public SQDevCollectionException(Throwable cause) {
		super(cause);
	}
	
	public SQDevCollectionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SQDevCollectionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
