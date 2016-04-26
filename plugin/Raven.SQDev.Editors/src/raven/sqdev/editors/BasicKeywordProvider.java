package raven.sqdev.editors;

import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.interfaces.IKeywordProvider;

/**
 * A basic implementation of the keywordProvider providing the keywords for
 * syntax highlighting
 * 
 * @author Raven
 * 		
 */
public class BasicKeywordProvider implements IKeywordProvider {
	
	/**
	 * The keywords this provider will return
	 */
	protected KeywordList keywords;
	
	/**
	 * A flag indicating whether or not the keywords in keywordsSorted are
	 * currently sorted properly
	 */
	protected boolean keywordsAreSorted;
	
	/**
	 * A flag indicating whether the keywords are currently getting sorted
	 */
	protected boolean isSorting;
	
	public BasicKeywordProvider() {
		keywordsAreSorted = false;
		isSorting = false;
	}
	
	@Override
	public KeywordList getKeywordList() {
		return (keywords == null) ? new KeywordList() : keywords;
	}
	
	@Override
	public void setKeywordList(KeywordList keywords) {
		this.keywords = keywords;
	}
	
}
