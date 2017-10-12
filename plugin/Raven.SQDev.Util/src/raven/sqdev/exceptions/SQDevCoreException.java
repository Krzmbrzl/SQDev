package raven.sqdev.exceptions;

/**
 * An exception indicating that something has gone so wrong that the program
 * can't continue
 * 
 * @author Raven
 *		
 */
public class SQDevCoreException extends RuntimeException {
	
	private static final long serialVersionUID = -4883855185998437822L;
	
	public SQDevCoreException() {
	}
	
	public SQDevCoreException(String arg0) {
		super(arg0);
	}
	
	public SQDevCoreException(Throwable arg0) {
		super(arg0);
	}
	
	public SQDevCoreException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
	public SQDevCoreException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
}
