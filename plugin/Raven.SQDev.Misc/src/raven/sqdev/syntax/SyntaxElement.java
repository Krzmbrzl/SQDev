package raven.sqdev.syntax;

import org.eclipse.core.runtime.Assert;

import raven.sqdev.exceptions.BadSyntaxException;
import raven.sqdev.misc.CharacterPair;
import raven.sqdev.misc.TextUtils;

/**
 * A class representing a syntax element.<br>
 * A syntax element can either be a leaf-element meaning that is simply
 * represented by a String or it can be a node-element meaning that it consists
 * of another sub-syntax and the respective encapsulating characters.
 * 
 * @author Raven
 * 
 */
public class SyntaxElement {
	
	/**
	 * The value of this <code>SyntaxElement</code> if it's a leaf-element
	 */
	private String leafElement;
	
	/**
	 * The value of this <code>SyntaxElement</code> if it's a node-element
	 */
	private Syntax subSyntax;
	
	/**
	 * The encapsulating characters if this is a node element
	 */
	private CharacterPair encapsulator;
	
	
	/**
	 * Creates a <code>SyntaxElement</code> as a leaf element representing the
	 * given String
	 * 
	 * @param leafElement
	 *            The String that is represented by this
	 *            <code>SyntaxElement</code>. It may only be one word
	 */
	public SyntaxElement(String leafElement) {
		Assert.isTrue(leafElement != null && !leafElement.isEmpty() && !leafElement.contains(" "));
		
		this.leafElement = leafElement;
	}
	
	/**
	 * Creates a <code>SyntaxElement</code> as a node element representing the
	 * given <code>Syntax</code> as a subSyntax
	 * 
	 * @param subSyntax
	 *            The <code>Syntax</code> represented by this
	 *            <code>SyntaxElement</code>.
	 * @param encapsulator
	 *            The <code>CharacterPair</code> encapsulating this node element
	 */
	public SyntaxElement(Syntax subSyntax, CharacterPair encapsulator) {
		Assert.isTrue(subSyntax != null && !subSyntax.isEmpty());
		
		this.subSyntax = subSyntax;
	}
	
	/**
	 * Checks whether this <code>SyntaxElement</code> is a leaf element
	 * 
	 * @return <code>True</code> if this <code>SyntaxElement</code> is a leaf
	 *         element. <code>False</code> if it's a node element
	 */
	public boolean isLeafElement() {
		return leafElement != null;
	}
	
	@Override
	public String toString() {
		if (isLeafElement()) {
			return leafElement;
		} else {
			return getEncapsulator().getOpener() + subSyntax.toString()
					+ getEncapsulator().getCloser();
		}
	}
	
	/**
	 * Gets the encapsulating <code>CharacterPair</code>
	 */
	public CharacterPair getEncapsulator() {
		return encapsulator;
	}
	
	/**
	 * Gets the amount of leafs represented by this <code>SyntaxElement</code>
	 */
	public int getLeafCount() {
		if (isLeafElement()) {
			return 1;
		} else {
			return subSyntax.getComponentCount();
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!this.getClass().equals(obj.getClass())) {
			return false;
		}
		
		SyntaxElement comp = (SyntaxElement) obj;
		
		if (this.isLeafElement() != comp.isLeafElement()) {
			return false;
		}
		
		if (this.getLeafCount() != comp.getLeafCount()) {
			return false;
		}
		
		return this.toString().equals(comp.toString());
	}
	
	public static SyntaxElement parseSyntaxElement(String input) throws BadSyntaxException {
		if (input == null || (input = input.trim()).isEmpty()) {
			throw new BadSyntaxException("The given input cannot be parsed into a syntaxElement!");
		}
		
		char startingChar = input.charAt(0);
		
		if (!Character.isLetterOrDigit(startingChar)) {
			CharacterPair encapsulator;
			
			switch (startingChar) {
				case '(':
					encapsulator = CharacterPair.ROUND_BRACKETS;
					break;
				
				case '[':
					encapsulator = CharacterPair.SQUARE_BRACKETS;
					break;
				
				case '{':
					encapsulator = CharacterPair.CURLY_BRACKETS;
					break;
				
				case '"':
					encapsulator = CharacterPair.DOUBLE_QUOTATION_MARKS;
					break;
				
				case '\'':
					encapsulator = CharacterPair.SINGLE_QUOTATION_MARKS;
					break;
				
				default:
					throw new BadSyntaxException(
							"Unknown starting character \"" + startingChar + "\"");
			}
			
			//TODO: figure out subSyntax
		} else {
			String[] elements = TextUtils.getAreas(input);
			
			if(elements.length > 1) {
				//create a subSyntax with each of those elements as a syntaxElements
			}else {
				
			}
		}
		
		return null;
	}
}
