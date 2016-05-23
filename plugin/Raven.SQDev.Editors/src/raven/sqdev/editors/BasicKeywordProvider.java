package raven.sqdev.editors;

import java.util.ArrayList;
import java.util.List;

import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.interfaces.IKeywordListChangeListener;
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
	
	/**
	 * A list of <code>IKeywordListChangeListeners</code>
	 */
	protected List<IKeywordListChangeListener> keywordListListeners;
	
	public BasicKeywordProvider() {
		keywordsAreSorted = false;
		isSorting = false;
		keywordListListeners = new ArrayList<IKeywordListChangeListener>();
	}
	
	@Override
	public KeywordList getKeywordList() {
		return (keywords == null) ? new KeywordList() : keywords;
	}
	
	@Override
	public void setKeywordList(KeywordList keywords) {
		this.keywords = keywords;
		
		// notify listeners
		for (IKeywordListChangeListener listener : keywordListListeners) {
			listener.keywordListChanged(IKeywordListChangeListener.CTX_LIST_CHANGED);
		}
	}
	
	@Override
	public void addKeywordListChangeListener(IKeywordListChangeListener listener) {
		if (!keywordListListeners.contains(listener)) {
			keywordListListeners.add(listener);
		}
	}
	
	@Override
	public void removeKeywordListChangeListener(IKeywordListChangeListener listener) {
		keywordListListeners.remove(listener);
		
	}
	
}
