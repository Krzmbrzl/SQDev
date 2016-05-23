package raven.sqdev.misc;

import java.util.List;
import java.util.ListIterator;

/**
 * A class containing various util functions for lists
 * 
 * @author Raven
 *
 */
public class ListUtils {
	
	/**
	 * Turns every element in the given String list into lowercase
	 * 
	 * @param list
	 *            The String list to process
	 * @return The processed list
	 */
	public static List<String> toLowerCase(List<String> list) {
		ListIterator<String> iterator = list.listIterator();
		
		while (iterator.hasNext()) {
			iterator.set(iterator.next().toLowerCase());
		}
		
		return list;
	}
}
