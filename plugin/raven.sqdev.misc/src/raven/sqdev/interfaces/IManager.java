package raven.sqdev.interfaces;

/**
 * This interface describes a manager class
 * 
 * @author Raven
 *
 */
public interface IManager {
	/**
	 * Applies the changes of this manager
	 */
	public void apply();
	
	/**
	 * Gets the type of this manager.
	 */
	public String getType();
}
