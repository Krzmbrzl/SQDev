package raven.sqdev.interfaces;

/**
 * An interface for a class that can check whether a given instance can be
 * replaced by another one
 * 
 * @author Raven
 */
public interface IReplaceTester {

	/**
	 * Tests whether this object can be replaced by the given one
	 * 
	 * @param obj
	 *            The replacement object to check
	 */
	public boolean canBeReplacedBy(Object obj);
}
