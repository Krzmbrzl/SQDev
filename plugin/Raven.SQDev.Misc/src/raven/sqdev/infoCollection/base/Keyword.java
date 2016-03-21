package raven.sqdev.infoCollection.base;

import org.eclipse.core.runtime.Assert;

/**
 * A class representing a keyword and providing additional information about the
 * represented keyword
 * 
 * @author Raven
 *		
 */
public class Keyword {
	
	/**
	 * The keyword itself
	 */
	private String keyword;
	
	/**
	 * The description for this keyword
	 */
	private String description;
	
	/**
	 * Creates an instance of a Keyword
	 * 
	 * @param keyword
	 *            The keyword
	 */
	public Keyword(String keyword) {
		this(keyword, null);
	}
	
	/**
	 * Creates an instance of a Keyword
	 * 
	 * @param keyword
	 *            The keyword
	 * @param description
	 *            The description for this keyword
	 */
	public Keyword(String keyword, String description) {
		setKeyword(keyword);
		setDescription(description);
	}
	
	/**
	 * Gets the keyword
	 */
	public String getKeyword() {
		return keyword;
	}
	
	/**
	 * Sets the keyword
	 * 
	 * @param keyword
	 *            The new keyword (May not be null or empty)
	 */
	public void setKeyword(String keyword) {
		Assert.isTrue(keyword != null && !keyword.isEmpty());
		
		this.keyword = keyword;
	}
	
	/**
	 * Gets the description for this keyword
	 * 
	 * @return The description or <code>null</code> if no description is given
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description for this keyword
	 * 
	 * @param description
	 *            The new description for this keyword or <code>null</code> to
	 *            indicate that no description is provided
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Checks whether this keyword does provide a description
	 */
	public boolean hasDescription() {
		return (getDescription() != null && !getDescription().isEmpty());
	}
	
}
