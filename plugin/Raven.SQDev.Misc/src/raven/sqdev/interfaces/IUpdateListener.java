package raven.sqdev.interfaces;

/**
 * An interface describing a listener that gets notified about updates
 * 
 * @author Raven
 *
 */
public interface IUpdateListener {
	
	/**
	 * Gets called whenever an update was made
	 */
	public void updated();
}
