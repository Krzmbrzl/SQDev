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
		Assert.isTrue(leafElement != null && !leafElement.isEmpty());

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
		this.encapsulator = encapsulator;
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
			if (encapsulator != null) {
				return getEncapsulator().getOpener() + subSyntax.toString() + getEncapsulator().getCloser();
			} else {
				return subSyntax.toString();
			}
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

	/**
	 * Parses the given input in a <code>SyntaxElement</code>
	 * 
	 * @param input
	 *            The input to parse the element from
	 * @return The parsed <code>SyntaxElement</code>
	 * @throws BadSyntaxException
	 *             If the input is onvalid
	 */
	public static SyntaxElement parseSyntaxElement(String input) throws BadSyntaxException {
		if (input == null || (input = input.trim()).isEmpty()) {
			throw new BadSyntaxException(
					"The given input cannot be parsed into a syntaxElement (Empty or null)!");
		}

		char startingChar = input.charAt(0);
		CharacterPair encapsulator = null;

		if (!Character.isLetterOrDigit(startingChar)) {
			// find the respective encapsulator
			encapsulator = CharacterPair.getDefinedPairFor(startingChar);

			if (encapsulator != null && encapsulator.equals(CharacterPair.TAG)) {
				// In SQF there is no tag encapsulator
				encapsulator = null;
			}
		}

		String[] elements = TextUtils.getTextAreas(input);

		if (elements == null || elements.length == 0) {
			throw new BadSyntaxException("TextUtils.getAreas() was not able to find areas in input");
		}

		if (elements.length > 1) {
			// create a subSyntax with each of those elements as a
			// syntaxElements

			Syntax subSyntax = new Syntax();

			for (String currentArea : elements) {
				if (TextUtils.isSingleTextArea(currentArea)) {
					// add as leaf node
					subSyntax.addElement(new SyntaxElement(currentArea));
				} else {
					// add as sub node
					subSyntax.addElement(parseSyntaxElement(currentArea));
				}
			}

			return new SyntaxElement(subSyntax, encapsulator);
		} else {
			if (encapsulator == null) {
				// There is just one area -> leaf element
				return new SyntaxElement(elements[0]);
			} else {
				Syntax subSyntax = new Syntax();

				String value = elements[0];
				// remove encapsulating characters
				value = value.substring(value.indexOf(encapsulator.getOpener()) + 1,
						value.lastIndexOf(encapsulator.getCloser()));

				subSyntax.addElement(new SyntaxElement(value));

				return new SyntaxElement(subSyntax, encapsulator);
			}
		}
	}
}
