package raven.sqdev.interfaces;

/**
 * A listener that gets notified whenever a change in the keyword list occurs
 * 
 * @author Raven
 *
 */
public interface IKeywordListChangeListener {
	/**
	 * The context of a list change
	 */
	public static final String CTX_LIST_CHANGED = "KeywordListChangeListener.changed";
	/**
	 * The context when the list gets removed
	 */
	public static final String CTX_LIST_REMOVED = "KeywordListChangeListener.removed";
	
	/**
	 * Gets called after the keyword list has changed
	 * 
	 * @param ctx
	 *            The context of the change
	 */
	public void keywordListChanged(String ctx);
}
