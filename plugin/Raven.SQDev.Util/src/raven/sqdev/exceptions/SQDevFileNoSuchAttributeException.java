package raven.sqdev.exceptions;

/**
 * An exception indicating that there is no such attribute specified in a
 * SQDevFile
 * 
 * @author Raven
 *		
 */
public class SQDevFileNoSuchAttributeException extends SQDevCoreException {
	
	private static final long serialVersionUID = -3784503138418825581L;
	
	public SQDevFileNoSuchAttributeException() {
	}
	
	public SQDevFileNoSuchAttributeException(String arg0) {
		super(arg0);
	}
	
	public SQDevFileNoSuchAttributeException(Throwable arg0) {
		super(arg0);
	}
	
	public SQDevFileNoSuchAttributeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
	public SQDevFileNoSuchAttributeException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
}
