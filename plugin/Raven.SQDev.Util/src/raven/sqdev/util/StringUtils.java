package raven.sqdev.util;

/**
 * A class containing various static String functions
 * @author Raven
 *
 */
public class StringUtils {
	
	/**
	 * Counts the occurence of a String in another String
	 * @param str The String to be searched
	 * @param match The String to be searched for
	 * @return How often the searched string has been found
	 */
	public static int countMatches(String str, String match) {
		int counter = 0;
		
		while(str.contains(match)) {
			counter++;
			
			str = str.substring(str.indexOf(match) + match.length());
		}
		
		return counter;
	}
	
}
