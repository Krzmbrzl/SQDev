package raven.sqdev.exceptions;

import java.net.URL;

import raven.sqdev.infoCollection.base.KeywordList;

/**
 * An exception indicating that something went wrong during a collection process
 * 
 * @author Raven
 * 
 */
public class SQDevCollectionException extends SQDevException {
	
	private static final long serialVersionUID = 5076943149344521751L;
	
	private URL failedKeywordURL;
	
	private KeywordList prevKeywords;
	
	public SQDevCollectionException(String message, URL failedKeywordURL,
			KeywordList list) {
		super(message);
		this.failedKeywordURL = failedKeywordURL;
		this.prevKeywords = list;
	}
	
	public SQDevCollectionException(Throwable cause, URL failedKeywordURL,
			KeywordList list) {
		super(cause);
		this.failedKeywordURL = failedKeywordURL;
		this.prevKeywords = list;
	}
	
	public SQDevCollectionException(String message, Throwable cause,
			URL failedKeywordURL, KeywordList list) {
		super(message, cause);
		this.failedKeywordURL = failedKeywordURL;
		this.prevKeywords = list;
	}
	
	/**
	 * Gets the <code>URL</code> of the Keyword that has been processed when
	 * this exception has been thrown. (May be <code>null</code>)
	 */
	public URL getFailedKeywordURL() {
		return failedKeywordURL;
	}
	
	/**
	 * Gets the list of keywords that were processed before this exception has
	 * occured
	 */
	public KeywordList getPreviouslyProcessedKeywords() {
		return (prevKeywords == null) ? new KeywordList() : prevKeywords;
	}
	
}
