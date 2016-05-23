package raven.sqdev.misc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;

import raven.sqdev.constants.TextConstants;
import raven.sqdev.exceptions.BadSyntaxException;
import raven.sqdev.exceptions.SQDevException;

/**
 * A class containing various static text functions
 * 
 * @author Raven
 * 
 */
public class TextUtils {
	/**
	 * An array containing all special characters that are allowed in file names
	 */
	public static final char[] ALLOWED_SPECIAL_CHARACTER_FILENAME = { '.', '_' };
	
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
		return whyIsInvalidName(name, allowedChars) == null;
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
		if (name.isEmpty()) {
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
					if (currentChar == ' ') {
						return "Blanks are not allowed in this name!";
					}
					
					return "Invalid character '" + currentChar + "' in \"" + name + "\"!";
				}
			}
		}
		
		// all good with the given name
		return null;
	}
	
	/**
	 * Checks if the given name is a valid project name
	 * 
	 * @param name
	 *            The name to check
	 * @see #isValidName
	 */
	public static boolean isValidFileName(String name) {
		ArrayList<Character> allowedChars = new ArrayList<Character>();
		
		for (char currentChar : ALLOWED_SPECIAL_CHARACTER_FILENAME) {
			allowedChars.add((Character) currentChar);
		}
		
		return isValidName(name, allowedChars);
	}
	
	/**
	 * Get the error code for why the name isn't valid
	 * 
	 * @param name
	 *            The name to check (mustn't be valid)
	 * @see #whyIsInvalidName
	 */
	public static String whyIsInvalidFileName(String name) {
		ArrayList<Character> allowedChars = new ArrayList<Character>();
		
		for (char currentChar : ALLOWED_SPECIAL_CHARACTER_FILENAME) {
			allowedChars.add((Character) currentChar);
		}
		
		return whyIsInvalidName(name, allowedChars);
	}
	
	/**
	 * Checks if the given character is a bracket
	 * 
	 * @param c
	 * @see #BRACKETS
	 */
	public static boolean isBracket(char c) {
		for (char currentChar : TextConstants.BRACKETS) {
			if (currentChar == c) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks whether the given character can be part of a word
	 * 
	 * @param c
	 *            The character to check
	 */
	public static boolean isWordPart(char c) {
		if (Character.isLetterOrDigit(c)) {
			return true;
		}
		
		// check for valid special characters
		for (char currentChar : TextConstants.SPECIAL_WORD_CHARACTERS) {
			if (c == currentChar) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Gets the different areas from an input such as words(seperated
	 * whitespace) or bracket areas (encapsulated in brackets).
	 * 
	 * @param input
	 *            The input whose areas should be obtained
	 * @param repair
	 *            Indicates whether this function should try to repair the input
	 *            in case the syntax is not correct (most commonly this applies
	 *            to inserting closing brackets at the end of the input)
	 * @return The found areas or <code>null</code> if an error occured
	 * @throws BadSyntaxException
	 *             If a bracket area is missing it's closing bracket
	 */
	public static String[] getTextAreas(String input, boolean repair) throws BadSyntaxException {
		Assert.isNotNull(input);
		
		if (input.isEmpty()) {
			// empty string results in no areas
			return new String[0];
		}
		
		StringReader reader = new StringReader(input);
		
		char currentChar;
		int currentCharInt;
		
		List<String> areaList = new ArrayList<String>();
		ByteArrayOutputStream characterStream = new ByteArrayOutputStream();
		
		/**
		 * A bracket pair that might be found in the input
		 */
		CharacterPair bracketPair = null;
		/**
		 * How often trailing opener of the same bracketPair occured before the
		 * last closer (wrapped brackets)
		 */
		int remainingOpenerToBeClosed = 0;
		
		try {
			while ((currentCharInt = reader.read()) > 0) {
				boolean flush = false;
				
				currentChar = (char) currentCharInt;
				
				if (isWordPart(currentChar)) {
					// simply append
					characterStream.write(currentChar);
				} else {
					if (Character.isWhitespace(currentChar)) {
						// check that there this is not in a bracket area
						if (bracketPair == null) {
							flush = true;
						} else {
							// write it to the stream as it belongs to the
							// bracket area
							characterStream.write(currentChar);
						}
					} else {
						if (isBracket(currentChar)) {
							if (bracketPair == null) {
								// get the bracket pair
								bracketPair = CharacterPair.getDefinedPairFor(currentChar);
								
								if (bracketPair.getOpener() == currentChar) {
									// manually flush
									if (characterStream.size() > 0) {
										areaList.add(characterStream.toString());
										characterStream.reset();
									}
								} else {
									// There is a closing bracket whose starting
									throw new BadSyntaxException(
											"Missing opening bracket " + bracketPair.getOpener());
								}
							} else {
								// check if the closer has been reached
								if (bracketPair.getCloser() == currentChar) {
									// check for wrapping
									if (remainingOpenerToBeClosed == 0) {
										flush = true;
										
										// reset bracket pair
										bracketPair = null;
									} else {
										remainingOpenerToBeClosed--;
									}
								} else {
									if (bracketPair.getOpener() == currentChar) {
										// beginning wrap
										remainingOpenerToBeClosed++;
									}
									// if it is another bracket don't care about
									// it -> no wrapped areas
								}
							}
							
							// add bracket to stream
							characterStream.write(currentChar);
						} else {
							// just put the character into the stream
							characterStream.write(currentChar);
						}
					}
				}
				
				if (flush && characterStream.size() > 0) {
					// add stream as an area
					areaList.add(characterStream.toString());
					
					// reset stream
					characterStream.reset();
				}
			}
			
			if (bracketPair != null) {
				// one more bracket has to be closed
				remainingOpenerToBeClosed++;
				
				// we are still in a warped area -> missing closing character
				if (remainingOpenerToBeClosed > 0 && repair && characterStream.size() > 0) {
					// "repair" the input by inserting respective amount of
					// closing brackets
					String closer = "";
					
					while (remainingOpenerToBeClosed > 0) {
						closer += bracketPair.getCloser();
						
						remainingOpenerToBeClosed--;
					}
					
					// append it to buffer
					characterStream.write(closer.getBytes());
				} else {
					throw new BadSyntaxException(
							"Incomplete CharacterPair " + bracketPair + " in input!");
				}
			}
			
			if (characterStream.size() > 0) {
				areaList.add(characterStream.toString());
			}
			
			return areaList.toArray(new String[areaList.size()]);
		} catch (IOException e) {
			try {
				// rethrow
				throw new SQDevException("Failed at retrieving areas", e);
			} catch (SQDevException e1) {
				e1.printStackTrace();
				
				return null;
			}
		}
	}
	
	/**
	 * Gets the different areas from an input such as words(seperated by blank
	 * or newLine) or bracket areas (encapsulated in brackets).
	 * 
	 * @param input
	 *            The input whose areas should be obtained
	 * @return The found areas or <code>null</code> if an error occured
	 * @throws BadSyntaxException
	 *             If a bracket area is missing it's closing bracket
	 */
	public static String[] getTextAreas(String input) throws BadSyntaxException {
		return getTextAreas(input, false);
	}
	
	/**
	 * Checks whether the given input conists of only one text area. That is
	 * when getTextAreas() returns an array of size 1
	 * 
	 * @param input
	 *            The input to check
	 * @throws BadSyntaxException
	 *             When getTextAreas() throws such an exception
	 * 
	 * @see #getTextAreas(String)
	 */
	public static boolean isSingleTextArea(String input) throws BadSyntaxException {
		input = input.trim();
		
		if (input.isEmpty()) {
			return true;
		}
		
		return getTextAreas(input).length == 1;
	}
}
