package raven.sqdev.constants;

/**
 * A class holding constants for the usage with text
 * 
 * @author Raven
 * 		
 */
public class TextConstants {
	/**
	 * An array containing whitespace characters
	 */
	public static final char[] WHITESPACE = { ' ', '\n', '\r', '\t' };
	
	/**
	 * An array containg all bracket characters (uneven index = opening bracket;
	 * even index = closing bracket)
	 */
	public static final char[] BRACKETS = { '(', ')', '[', ']', '{', '}' };
	
	/**
	 * An array containing all special characters a valid word can constist of
	 * apart from letters and digits
	 */
	public static final char[] SPECIAL_WORD_CHARACTERS = { '_' };
}
