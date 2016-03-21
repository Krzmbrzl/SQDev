package raven.sqdev.syntax;

import java.util.ArrayList;

import org.eclipse.core.runtime.Assert;

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
	 * A list of all SyntaxElements this syntax conatains
	 */
	private ArrayList<SyntaxElement> elements;
	
	public Syntax(String commandName) {
		this.commandName = commandName;
		
		elements = new ArrayList<SyntaxElement>();
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
		if (!getElements().contains(element)) {
			getElements().add(element);
		}
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
}
