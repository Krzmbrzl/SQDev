package raven.sqdev.interfaces;

import raven.sqdev.infoCollection.base.KeywordList;

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
	public KeywordList getKeywordList();
	
	/**
	 * Sets the keywords for this provider
	 * 
	 * @param keywords
	 *            The array of keywords
	 */
	public void setKeywordList(KeywordList keywords);
	
	/**
	 * Adds the given <code>IKeywordListChangeListener</code> if it is not
	 * already registered
	 * 
	 * @param listener
	 *            The listener to add
	 */
	public void addKeywordListChangeListener(IKeywordListChangeListener listener);
	
	/**
	 * Removes the given <code>IKeywordListChangeListener</code>
	 * 
	 * @param listener
	 *            The listener to remove
	 */
	public void removeKeywordListChangeListener(IKeywordListChangeListener listener);
}
