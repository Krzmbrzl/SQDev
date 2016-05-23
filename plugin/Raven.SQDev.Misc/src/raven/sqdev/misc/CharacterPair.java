package raven.sqdev.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is to associate a character with a respective partner element.<br>
 * A typical example of a character pair is <code>'('</code> and
 * <code>')'</code>
 * 
 * @author Raven
 * 
 */
public class CharacterPair {
	
	public static final CharacterPair SINGLE_QUOTATION_MARKS = new CharacterPair('\'', '\'');
	public static final CharacterPair DOUBLE_QUOTATION_MARKS = new CharacterPair('"', '"');
	public static final CharacterPair ROUND_BRACKETS = new CharacterPair('(', ')');
	public static final CharacterPair SQUARE_BRACKETS = new CharacterPair('[', ']');
	public static final CharacterPair CURLY_BRACKETS = new CharacterPair('{', '}');
	
	/**
	 * A list of all defined <code>CharacterPairs</code>
	 */
	private static List<CharacterPair> definedPairs;
	
	/**
	 * The actual pair
	 */
	private char[] pair;
	
	/**
	 * Creates a new character pair
	 * 
	 * @param a
	 *            Opening character of the pair
	 * @param b
	 *            Closing character of the pair
	 */
	public CharacterPair(char a, char b) {
		char[] pair = { a, b };
		
		this.setPair(pair);
		
		if (definedPairs == null) {
			definedPairs = new ArrayList<CharacterPair>();
		}
		
		// register pair
		definedPairs.add(this);
	}
	
	public char[] getPair() {
		return this.pair;
	}
	
	public void setPair(char[] pair) {
		this.pair = pair;
	}
	
	/**
	 * Gets the opening character of this pair
	 * 
	 * @return
	 */
	public char getOpener() {
		return this.getPair()[0];
	}
	
	/**
	 * Gets the closing character of this pair
	 * 
	 * @return
	 */
	public char getCloser() {
		return this.getPair()[1];
	}
	
	/**
	 * Check if this pair includes the given character
	 * 
	 * @param c
	 *            The character to search for
	 * @return
	 */
	public boolean includes(char c) {
		return (this.getOpener() == c || this.getCloser() == c);
	}
	
	/**
	 * Finds the <b>defined</b> <code>CharacterPair</code> that includes the
	 * given Character
	 * 
	 * @param c
	 *            The character to search for
	 * @return The respective <code>CharacterPair</code> or <code>null</code> if
	 *         none could be found
	 */
	public static CharacterPair getDefinedPairFor(char c) {
		for (CharacterPair current : definedPairs) {
			if (current.includes(c)) {
				return current;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return "'" + getOpener() + "' + '" + getCloser() + "'";
	}
}
