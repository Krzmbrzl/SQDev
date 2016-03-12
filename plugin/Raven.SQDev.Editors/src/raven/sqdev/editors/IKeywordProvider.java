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
}
