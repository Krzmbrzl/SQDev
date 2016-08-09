package raven.sqdev.exceptions;

import raven.sqdev.infoCollection.base.Keyword;
import raven.sqdev.infoCollection.base.KeywordList;

/**
 * An exception indicating that something went wrong during a collection process
 * 
 * @author Raven
 * 
 */
public class SQDevCollectionException extends SQDevException {
	
	private static final long serialVersionUID = 5076943149344521751L;
	
	private Keyword failedKeyword;
	
	private KeywordList prevKeywords;
	
	public SQDevCollectionException(String message, Keyword failedKeyword, KeywordList list) {
		super(message);
		this.failedKeyword = failedKeyword;
		this.prevKeywords = list;
	}
	
	public SQDevCollectionException(Throwable cause, Keyword failedKeyword, KeywordList list) {
		super(cause);
		this.failedKeyword = failedKeyword;
		this.prevKeywords = list;
	}
	
	public SQDevCollectionException(String message, Throwable cause, Keyword failedKeyword,
			KeywordList list) {
		super(message, cause);
		this.failedKeyword = failedKeyword;
		this.prevKeywords = list;
	}
	
	/**
	 * Gets the <code>Keword</code> that has been processed when this exception
	 * has been thrown. (May be <code>null</code>)
	 */
	public Keyword getFailedKeyword() {
		return failedKeyword;
	}
	
	/**
	 * Gets the list of keyowrds that were processed before this exception has
	 * occured
	 */
	public KeywordList getPreviouslyProcessedKeywords() {
		return (prevKeywords == null) ? new KeywordList() : prevKeywords;
	}
	
}
