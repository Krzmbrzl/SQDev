package raven.sqdev.preferences.util;

/**
 * An enum containing different status types
 * 
 * @author Raven
 * @see #OK
 * @see #ERROR
 * @see #WARNING
 *		
 */
public enum EStatus {
	/**
	 * A status indicating that everything is okay
	 */
	OK,
	
	/**
	 * A status indicating that there is something wrong.<br>
	 * May contain a hint message
	 */
	ERROR,
	
	/**
	 * A status indicating that it's okay but it can be improved<br>
	 * May contain a hint message
	 */
	WARNING;
	
	
	/**
	 * A String containg a hint message on how to fix the current error/warning
	 */
	private String hint;
	
	private EStatus() {
		// clear hint message on status change
		setHint("");
	}
	
	/**
	 * Gets the stored hint-message. Will return <code>null</code> when the
	 * status is not <code>EStatus.WARNING</code> or <code>EStatus.ERROR</code>
	 * 
	 * @return The hint describing how to fix that warning
	 */
	public String getHint() {
		if(!(this.equals(EStatus.WARNING) || this.equals(EStatus.ERROR))) {
			// when the state is not on warning there can't be 
			return null;
		}
		
		return hint;
	}
	
	/**
	 * Sets the hint message for describing how to fix the current warning
	 * @param hint The message
	 */
	public void setHint(String hint) {
		this.hint = hint;
	}
}
