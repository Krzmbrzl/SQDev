package raven.sqdev.syntax;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Assert;

import raven.sqdev.exceptions.BadSyntaxException;
import raven.sqdev.misc.TextUtils;

/**
 * A class representing a syntax that consist <code>SyntaxElements</code>
 * 
 * @see SyntaxElement
 * 
 * @author Raven
 * 
 */
public class Syntax {
	
	/**
	 * The name of the command this syntax is associated with
	 */
	private String commandName;
	
	/**
	 * A list of all SyntaxElements this syntax contains
	 */
	private ArrayList<SyntaxElement> elements;
	
	public Syntax(String commandName) {
		this.commandName = commandName;
		
		elements = new ArrayList<SyntaxElement>();
	}
	
	/**
	 * Creates a subSyntax that does not have a command name
	 */
	protected Syntax() {
		this("!SubSyntax!");
	}
	
	/**
	 * Gets the command name this syntax is associated with
	 */
	public String getCommandName() {
		return commandName;
	}
	
	/**
	 * Checks whether this syntax is empty
	 */
	public boolean isEmpty() {
		return getElements().size() == 0;
	}
	
	@Override
	public String toString() {
		String str = "";
		
		for (SyntaxElement currentElement : getElements()) {
			str += currentElement.toString() + " ";
		}
		
		return str.trim();
	}
	
	/**
	 * Gets the SyntaxElements this <code>Syntax</code> consists of
	 */
	public ArrayList<SyntaxElement> getElements() {
		return elements;
	}
	
	/**
	 * Sets the elements for this syntax
	 * 
	 * @param elements
	 *            The new elements (Must at least contain one element!)
	 */
	public void setElements(ArrayList<SyntaxElement> elements) {
		Assert.isTrue(elements != null && elements.size() >= 1);
		
		this.elements = elements;
	}
	
	/**
	 * Adds an <code>SyntaxElement</code> to this syntax
	 * 
	 * @param element
	 *            The element to add
	 */
	public void addElement(SyntaxElement element) {
		getElements().add(element);
	}
	
	/**
	 * Gets the <code>SyntaxElement</code> at the given index in this
	 * <code>Syntax</code>
	 * 
	 * @param index
	 *            The index of th desired <code>SyntaxElement</code>
	 */
	public SyntaxElement getElement(int index) {
		return getElements().get(index);
	}
	
	/**
	 * Gets the the amount of argument for this <code>Syntax</code>
	 */
	public int getArgumentCount() {
		return getElements().size() - 1;
	}
	
	/**
	 * Gets the amount of single component this syntax consists of (not counting
	 * arrays as an element -> only leafs are considered)
	 * 
	 * @return The component count
	 */
	public int getComponentCount() {
		int count = 0;
		
		for (SyntaxElement currentElement : getElements()) {
			count += currentElement.getLeafCount();
		}
		
		return count;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!this.getClass().equals(obj.getClass())) {
			return false;
		}
		
		Syntax comp = (Syntax) obj;
		
		if (this.getComponentCount() != comp.getComponentCount()) {
			return false;
		}
		
		for (int i = 0; i < this.getComponentCount(); i++) {
			if (!this.getElement(i).equals(comp.getElement(i))) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Check if this syntax is a nular syntax (= contains only the command with
	 * no arguments)
	 */
	public boolean isNular() {
		return getArgumentCount() == 0;
	}
	
	/**
	 * Check if this syntax is an unary syntax (= contains only the command with
	 * one argument)
	 */
	public boolean isUnary() {
		return getArgumentCount() == 1;
	}
	
	/**
	 * Check if this syntax is a binary syntax (= contains the command with an
	 * argument on both sides of it)
	 */
	public boolean isBinary() {
		return getArgumentCount() == 2;
	}
	
	/**
	 * Creates a syntax out of the given input
	 * 
	 * @param input
	 *            The input to derive the syntax from
	 * @param commandName
	 *            The name of the command this <code>Syntax</code> belongs to.
	 *            <b>Has to be contained in input</b>
	 * @return The parsed <code>Syntax</code>
	 * @throws BadSyntaxException
	 *             If the input is invalid
	 */
	public static Syntax parseSyntax(String input, String commandName)
			throws BadSyntaxException {
		if (input == null || input.trim().isEmpty() || commandName == null
				|| commandName.isEmpty()) {
			// can't process
			throw new IllegalArgumentException(
					"The given input or commandName is invalid!");
		}
		
		input = input.trim();
		
		// check that the commandName is properly contained in the input
		boolean isContained = false;
		
		String[] elements;
		String[] lowerElements;
		try {
			elements = TextUtils.getTextAreas(input);
			lowerElements = TextUtils.getTextAreas(input.toLowerCase());
		} catch (BadSyntaxException e) {
			throw new BadSyntaxException("The syntax input for the command \""
					+ commandName + "\" is invalid!", e);
		}
		
		String lowerCommandName = commandName.toLowerCase();
		
		for (int i = 0; i < lowerElements.length; i++) {
			String currentElement = lowerElements[i];
			
			if (currentElement.equals(lowerCommandName)) {
				// use the specified commandName
				elements[i] = commandName;
				isContained = true;
				break;
			}
		}
		
		if (!isContained) {
			throw new BadSyntaxException("The command \"" + commandName
					+ "\" is not contained in the given input!");
		}
		
		// recreate String out of the array
		StringBuilder builder = new StringBuilder();
		for (String currentElement : elements) {
			builder.append(" " + currentElement);
		}
		input = builder.toString().trim();
		
		Syntax syntax = new Syntax(commandName);
		
		// find the position of the command in the syntax
		Matcher commandMatcher;
		if (!Character.isLetter(commandName.charAt(0))) {
			// match without word boundaries
			commandMatcher = Pattern.compile(Pattern.quote(commandName))
					.matcher(input);
		} else {
			// match with word boundaries
			commandMatcher = Pattern
					.compile("\\b" + Pattern.quote(commandName) + "\\b")
					.matcher(input);
		}
		commandMatcher.find();
		
		String inputBeforeCommand = input.substring(0, commandMatcher.start())
				.trim();
		String inputAfterCommand = input.substring(commandMatcher.end()).trim();
		
		try {
			if (!inputBeforeCommand.isEmpty()) {
				// add leading syntaxElement
				syntax.addElement(
						SyntaxElement.parseSyntaxElement(inputBeforeCommand));
			}
			
			// add the command as a syntaxElement
			syntax.addElement(
					SyntaxElement.parseSyntaxElement(commandName, true));
			
			if (!inputAfterCommand.isEmpty()) {
				// add trailing syntaxElement
				syntax.addElement(
						SyntaxElement.parseSyntaxElement(inputAfterCommand));
			}
		} catch (BadSyntaxException e) {
			throw new BadSyntaxException(
					"Can't parse sub-elements fo command \"" + commandName
							+ "\" into SyntaxElements!",
					e);
		}
		
		return syntax;
	}
}
