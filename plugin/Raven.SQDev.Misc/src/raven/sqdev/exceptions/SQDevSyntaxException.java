package raven.sqdev.exceptions;

/**
 * An exception thrown whenever a given format is not respected so that the
 * processing can't continue
 * 
 * @author Raven
 *		
 */
public class SQDevSyntaxException extends SQDevCoreException {
	
	private static final long serialVersionUID = -6664220779760880244L;
	
	public SQDevSyntaxException() {
	}
	
	public SQDevSyntaxException(String msg) {
		super(msg);
	}
	
	public SQDevSyntaxException(Throwable arg0) {
		super(arg0);
	}
	
	public SQDevSyntaxException(String msg, Throwable arg1) {
		super(msg, arg1);
	}
	
	public SQDevSyntaxException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
}
