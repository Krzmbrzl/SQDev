package raven.sqdev.editors;

/**
 * An interface for an keyword provider that has to declare a method for
 * providing an array of Strings
 * 
 * @author Raven
 * 		
 */
public interface IKeywordProvider {
	/**
	 * Gets the keywords as an String array
	 * 
	 */
	public String[] getKeywords();
	
	/**
	 * Sets the keywords for this provider
	 * 
	 * @param keywords
	 *            The array of keywords
	 */
	public void setKeywords(String[] keywords);
	
	/**
	 * Gets the keywords sorted alphabetically. Normally the keywords starting
	 * with an A are listed at index 0
	 * 
	 * @return The twodimensional array of sorted keywords
	 */
	public String[][] getSortedKeywords();
}
