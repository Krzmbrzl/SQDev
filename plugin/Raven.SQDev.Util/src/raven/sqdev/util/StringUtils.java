package raven.sqdev.util;

import java.util.ArrayList;

/**
 * A class containing various static String functions
 * 
 * @author Raven
 * 		
 */
public class StringUtils {
	
	/**
	 * An array containing all sort of brackets
	 */
	public static final char[] BRACKETS = { '(', ')', '[', ']', '{', '}' };
	/**
	 * An array containing all special characters that are allowed in project names
	 */
	public static final char[] ALLOWED_SPECIAL_CHARACTER_PROJECTNAME = {'.', ' ', '_'};
	
	/**
	 * Counts the occurence of a String in another String
	 * 
	 * @param str
	 *            The String to be searched
	 * @param match
	 *            The String to be searched for
	 * @return How often the searched string has been found
	 */
	public static int countMatches(String str, String match) {
		int counter = 0;
		
		while (str.contains(match)) {
			counter++;
			
			str = str.substring(str.indexOf(match) + match.length());
		}
		
		return counter;
	}
	
	/**
	 * Checks if the given name is valid.<br>
	 * A name is considered valid if it starts with a letter and then continues
	 * with either letters or digits or any character specified in
	 * <code>allowedChars</code>.<br>
	 * If you don't want any additional characters to be allowed just pass
	 * <code>null</code>
	 * 
	 * @param name
	 *            The name to validate
	 * @param allowedChars
	 *            A list of additional characters that are allowed for this
	 *            name. May be <code>null</code>
	 */
	public static boolean isValidName(String name, ArrayList<Character> allowedChars) {
		if (name.isEmpty() || name == null) {
			// an empty name can't be valid
			return false;
		}
		
		if (allowedChars == null) {
			// initialize empty list
			allowedChars = new ArrayList<Character>();
		}
		
		char[] chars = name.toCharArray();
		
		if (!Character.isLetter(chars[0])) {
			// name has to start with a letter
			return false;
		}
		
		for (char currentChar : chars) {
			if (!Character.isLetterOrDigit(currentChar)) {
				// check if special character is allowed
				if (!allowedChars.contains((Character) currentChar)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Checks for the reason the given name is invalid.
	 * 
	 * @param name
	 *            The invalid name (mustn't be valid)
	 * @param allowedChars
	 *            A list of additional characters that are allowed for this
	 *            name. May be <code>null</code>
	 * @return The error message explaining why the given name isn't valid.
	 */
	public static String whyIsInvalidName(String name, ArrayList<Character> allowedChars) {
		if (isValidName(name, allowedChars) || name == null) {
			// if it is a valid name no error message can be found
			return null;
		}
		
		if(name.isEmpty()) {
			return "A name must not be empty!";
		}
		
		if (allowedChars == null) {
			// initialize empty list
			allowedChars = new ArrayList<Character>();
		}
		
		char[] chars = name.toCharArray();
		
		if (!Character.isLetter(chars[0])) {
			// name has to start with a letter
			return "A name has to start with a letter!";
		}
		
		for (char currentChar : chars) {
			if (!Character.isLetterOrDigit(currentChar)) {
				// check if special character is allowed
				if (!allowedChars.contains((Character) currentChar)) {
					return "Invalid character '" + currentChar + "' in \"" + name + "\"!";
				}
			}
		}
		
		// one of the above has to have matched
		return null;
	}
	
	/**
	 * Checks if the given name is a valid project name
	 * @param name The name to check
	 * @see #isValidName
	 */
	public static boolean isValidProjectName(String name) {
		ArrayList<Character> allowedChars = new ArrayList<Character>();
		
		for(char currentChar : ALLOWED_SPECIAL_CHARACTER_PROJECTNAME) {
			allowedChars.add((Character) currentChar);
		}
		
		return isValidName(name, allowedChars);
	}
	
	/**
	 * Get the error code for why the name isn't valid
	 * @param name The name to check (mustn't be valid)
	 * @see #whyIsInvalidName
	 */
	public static String whyIsInvalidProjectName(String name) {
		ArrayList<Character> allowedChars = new ArrayList<Character>();
		
		for(char currentChar : ALLOWED_SPECIAL_CHARACTER_PROJECTNAME) {
			allowedChars.add((Character) currentChar);
		}
		
		return whyIsInvalidName(name, allowedChars);
	}
	
	/**
	 * Checks if the given character is a bracket
	 * @param c
	 * @see #BRACKETS
	 */
	public static boolean isBracket(char c) {
		for(char currentChar : BRACKETS) {
			if(currentChar == c) {
				return true;
			}
		}
		
		return false;
	}
	
}
