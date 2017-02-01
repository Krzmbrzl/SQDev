package raven.sqdev.misc;

/**
 * A class containing util functions for dealing with arrays
 * 
 * @author Raven
 *
 */
public class ArrayUtils {
	
	/**
	 * Checks whether the given array contains the given element in a given
	 * distance from the given start position. In order to match the element it
	 * has to be equal with the one in the array
	 * 
	 * @param array
	 *            The array to check
	 * @param element
	 *            The element to search
	 * @param start
	 *            Where to start the search
	 * @param distance
	 *            In whcih distance the element may occure
	 * @return Whether or not the element occurs in the given distance from the
	 *         given start point
	 */
	public static final boolean containsElementInmaxDistance(Object[] array,
			Object element, int start, int distance) {
		
		if (array == null || start >= array.length || distance <= 0) {
			return false;
		}
		
		for (int i = start; i < array.length; i++) {
			if (array[i].equals(element)) {
				return true;
			}
			
			distance--;
			
			if (distance == 0) {
				break;
			}
		}
		
		return false;
	}
	
}
