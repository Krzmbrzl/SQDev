package raven.sqdev.editors;

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
	
	private char[] pair;
	
	/**
	 * Creates a new character pair
	 * @param a Opening character of the pair
	 * @param b Closing character of the pair
	 */
	public CharacterPair(char a, char b) {
		char[] pair = { a, b };
		
		this.setPair(pair);
	}
	
	public char[] getPair() {
		return this.pair;
	}
	
	public void setPair(char[] pair) {
		this.pair = pair;
	}
	
	/**
	 * Gets the opening character of this pair
	 * @return
	 */
	public char getOpener() {
		return this.getPair()[0];
	}
	
	/**
	 * Gets the closing character of this pair
	 * @return
	 */
	public char getCloser() {
		return this.getPair()[1];
	}
	
	/**
	 * Check if this pair includes the given character
	 * @param c The character to search for
	 * @return
	 */
	public boolean includes(char c) {
		return (this.getOpener() == c || this.getCloser() == c);
	}
	
}
