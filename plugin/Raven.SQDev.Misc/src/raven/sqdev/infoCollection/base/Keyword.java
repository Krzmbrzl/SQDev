package raven.sqdev.infoCollection.base;

import java.io.Serializable;

import org.eclipse.core.runtime.Assert;

import raven.sqdev.exceptions.BadSyntaxException;
import raven.sqdev.interfaces.ISaveable;

/**
 * A class representing a keyword and providing additional information about the
 * represented keyword
 * 
 * @author Raven
 * 
 */
public class Keyword implements Comparable<Keyword>, ISaveable, Serializable {
	
	private static final long serialVersionUID = -2975038074702562868L;
	
	/**
	 * The sequence indicating the start of the keyword attribute in the
	 * saveable String format of this class
	 */
	public static final String KEYWORD_START_SAVESEQUENCE = "<Keyword>";
	/**
	 * The sequence indicating the end of the keyword attribute in the saveable
	 * String format of this class
	 */
	public static final String KEYWORD_END_SAVESEQUENCE = "</Keyword>";
	/**
	 * The sequence indicating the start of the description attribute in the
	 * saveable String format of this class
	 */
	public static final String DESCRIPTION_START_SAVESEQUENCE = "<Description>";
	/**
	 * The sequence indicating the end of the description attribute in the
	 * saveable String format of this class
	 */
	public static final String DESCRIPTION_END_SAVESEQUENCE = "</Description>";
	
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
	 */
	public Keyword() {
		this("", null);
	}
	
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
		Assert.isTrue(keyword != null);
		
		keyword = keyword.trim();
		
		this.keyword = keyword;
	}
	
	/**
	 * Gets the description for this keyword
	 * 
	 * @return The description or an empty String if no description is given
	 */
	public String getDescription() {
		return (description == null) ? "" : description;
	}
	
	/**
	 * Sets the description for this keyword
	 * 
	 * @param description
	 *            The new description for this keyword or <code>null</code> to
	 *            indicate that no description is provided
	 */
	public void setDescription(String description) {
		if (description != null) {
			description = description.trim();
			
			if (description.isEmpty()) {
				description = null;
			}
		}
		
		this.description = description;
	}
	
	/**
	 * Checks whether this keyword does provide a description
	 */
	public boolean hasDescription() {
		return !getDescription().isEmpty();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!this.getClass().equals(obj.getClass())) {
			return false;
		}
		
		Keyword compare = (Keyword) obj;
		
		if (!this.getKeyword().equals(compare.getKeyword())) {
			return false;
		}
		
		if (!this.getDescription().equals(compare.getDescription())) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public int compareTo(Keyword keyword) {
		// check if they are equal
		if (this.equals(keyword)) {
			return 0;
		}
		
		// declare Strings which will be compared
		String own;
		String compare;
		
		if (this.getKeyword().equals(keyword.getKeyword())) {
			own = getDescription();
			compare = keyword.getDescription();
		} else {
			own = getKeyword();
			compare = keyword.getKeyword();
		}
		
		int minLength = own.length();
		
		if (compare.length() < minLength) {
			minLength = compare.length();
		}
		
		// compare according to alphabetical order
		for (int i = 0; i < minLength; i++) {
			if (own.charAt(i) < compare.charAt(i)) {
				return -1;
			} else {
				if (own.charAt(i) > compare.charAt(i)) {
					return 1;
				}
			}
		}
		
		return 0;
	}
	
	@Override
	public String getSaveableFormat() {
		// store attributes
		String saveFormat = KEYWORD_START_SAVESEQUENCE + "\n\t" + getKeyword()
				+ "\n" + KEYWORD_END_SAVESEQUENCE + "\n";
		saveFormat += DESCRIPTION_START_SAVESEQUENCE + "\n\t" + getDescription()
				+ "\n" + DESCRIPTION_END_SAVESEQUENCE;
		
		return saveFormat;
	}
	
	@Override
	public boolean recreateFrom(String savedFormat) throws BadSyntaxException {
		if (!isSaveFormat(savedFormat)) {
			return false;
		}
		
		// get the keyword
		String keyword = savedFormat.substring(
				savedFormat.indexOf(KEYWORD_START_SAVESEQUENCE)
						+ KEYWORD_START_SAVESEQUENCE.length(),
				savedFormat.indexOf(KEYWORD_END_SAVESEQUENCE)).trim();
		// get the description
		String description = savedFormat
				.substring(
						savedFormat.indexOf(DESCRIPTION_START_SAVESEQUENCE)
								+ DESCRIPTION_START_SAVESEQUENCE.length(),
						savedFormat.indexOf(DESCRIPTION_END_SAVESEQUENCE))
				.trim();
		
		// apply the values
		setKeyword(keyword);
		setDescription(description);
		
		return true;
	}
	
	@Override
	public boolean isSaveFormat(String format) {
		if (!format.contains(KEYWORD_START_SAVESEQUENCE)
				|| !format.contains(KEYWORD_END_SAVESEQUENCE)
				|| !format.contains(DESCRIPTION_START_SAVESEQUENCE)
				|| !format.contains(DESCRIPTION_END_SAVESEQUENCE)) {
			// all these keywords have to be present
			return false;
		}
		
		// check for positions of these keywords
		int keywordStart = format.indexOf(KEYWORD_START_SAVESEQUENCE);
		int keywordEnd = format.indexOf(KEYWORD_END_SAVESEQUENCE);
		int descriptionStart = format.indexOf(DESCRIPTION_START_SAVESEQUENCE);
		int descriptionEnd = format.indexOf(DESCRIPTION_END_SAVESEQUENCE);
		
		if (keywordEnd < keywordStart || descriptionEnd < descriptionStart
				|| keywordStart > descriptionStart
				|| descriptionStart < keywordEnd) {
			// the order of the keywords is messed up
			return false;
		}
		
		// check that there is actually content in it
		String keyword = format.substring(
				format.indexOf(KEYWORD_START_SAVESEQUENCE)
						+ KEYWORD_START_SAVESEQUENCE.length(),
				format.indexOf(KEYWORD_END_SAVESEQUENCE)).trim();
		String description = format.substring(
				format.indexOf(DESCRIPTION_START_SAVESEQUENCE)
						+ DESCRIPTION_START_SAVESEQUENCE.length(),
				format.indexOf(DESCRIPTION_END_SAVESEQUENCE)).trim();
		
		if (keyword.isEmpty() || description.isEmpty()) {
			return false;
		}
		
		// everything is ok
		return true;
	}
	
	@Override
	public String toString() {
		return getKeyword();
	}
	
}
