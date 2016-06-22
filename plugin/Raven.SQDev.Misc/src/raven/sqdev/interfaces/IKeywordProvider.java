package raven.sqdev.interfaces;

import raven.sqdev.infoCollection.base.Keyword;
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
	 * Adds the given <code>Keyword</code> to the keyword list
	 * 
	 * @param keyword
	 *            The <code>Keyword</code> to add
	 */
	public void addKeyword(Keyword keyword);
	
	/**
	 * Removes the given <code>Keyword</code> from the keyword list
	 * 
	 * @param keyword
	 *            The <code>Keyword</code> to remove
	 */
	public void removeKeyword(Keyword keyword);
	
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
