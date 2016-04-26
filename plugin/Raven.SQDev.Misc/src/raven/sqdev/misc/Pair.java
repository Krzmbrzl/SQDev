package raven.sqdev.misc;

public class Pair<T, S> {
	
	private T object1;
	
	private S object2;
	
	/**
	 * Creates a new Pair
	 * 
	 * @param obj1
	 *            The first component of this Pair
	 * @param obj2
	 *            The second component ofthis Pair
	 */
	public Pair(T obj1, S obj2) {
		object1 = obj1;
		object2 = obj2;
	}
	
	/**
	 * Gets the first component of this Pair
	 */
	public T getFirst() {
		return object1;
	}
	
	/**
	 * Gets the second component of this Pair
	 */
	public S getSecond() {
		return object2;
	}
	
}
