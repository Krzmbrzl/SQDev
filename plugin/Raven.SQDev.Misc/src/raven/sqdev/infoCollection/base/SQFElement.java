package raven.sqdev.infoCollection.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.Assert;

import raven.sqdev.exceptions.BadSyntaxException;
import raven.sqdev.exceptions.SQDevException;

/**
 * A <code>Keyword</code> representing an SQF Element.
 * 
 * @author Raven
 * 
 */
public class SQFElement extends Keyword {
	
	/**
	 * The sequence indicating the start of the wikiPage attribute in the
	 * saveable String format of this class
	 */
	public static final String WIKI_START_SAVESEQUENCE = "<WikiPage>";
	/**
	 * The sequence indicating the end of the wikiPage attribute in the saveable
	 * String format of this class
	 */
	public static final String WIKI_END_SAVESEQUENCE = "</WikiPage>";
	
	/**
	 * The url to the wiki page of this command
	 */
	private URL wikiPage;
	
	/**
	 * Creates an instance of this <code>SQFElement</code>
	 */
	public SQFElement() {
		this("", null);
	}
	
	/**
	 * Creates an instance of this <code>SQFElement</code>
	 * 
	 * @param name
	 *            The name of this element
	 */
	public SQFElement(String name) {
		this(name, "");
	}
	
	/**
	 * Creates an instance of this <code>SQFElement</code>
	 * 
	 * @param name
	 *            The name of this element
	 * @param description
	 *            The description of this element
	 */
	public SQFElement(String name, String description) {
		super(name, description);
	}
	
	/**
	 * Gets the URL of this command's wiki page
	 */
	public URL getWikiPage() {
		return wikiPage;
	}
	
	/**
	 * Sets the URL to this command's wiki page
	 * 
	 * @param wikiPage
	 *            The wiki page URL
	 */
	public void setWikiPage(URL wikiPage) {
		Assert.isNotNull(wikiPage);
		
		this.wikiPage = wikiPage;
	}
	
	/**
	 * Checks whether the wiki page of this command has been set
	 */
	public boolean isWikiPageGiven() {
		return getWikiPage() != null;
	}
	
	@Override
	public String getSaveableFormat() {
		String format = super.getSaveableFormat();
		
		if (isWikiPageGiven()) {
			// append own attributes
			format += "\n" + WIKI_START_SAVESEQUENCE + "\n\t"
					+ getWikiPage().toString() + "\n" + WIKI_END_SAVESEQUENCE;
		}
		
		return format;
	}
	
	@Override
	public boolean recreateFrom(String savedFormat) throws BadSyntaxException {
		if (!super.recreateFrom(savedFormat) || !isSaveFormat(savedFormat)) {
			return false;
		}
		
		if (savedFormat.contains(WIKI_START_SAVESEQUENCE)
				&& savedFormat.contains(WIKI_END_SAVESEQUENCE)) {
			// get wikiPage
			String wiki = savedFormat.substring(
					savedFormat.indexOf(WIKI_START_SAVESEQUENCE)
							+ WIKI_START_SAVESEQUENCE.length(),
					savedFormat.indexOf(WIKI_END_SAVESEQUENCE)).trim();
			
			try {
				// store wikiPage
				setWikiPage(new URL(wiki));
			} catch (MalformedURLException e) {
				try {
					throw new SQDevException(
							"The URL for the wiki page for the command "
									+ getKeyword()
									+ " is not in the proper format!",
							e);
				} catch (SQDevException e1) {
					e1.printStackTrace();
					
					// state that something went wrong
					return false;
				}
			}
		}
		
		return true;
	}
	
	@Override
	public boolean isSaveFormat(String format) {
		if (!super.isSaveFormat(format)) {
			return false;
		}
		
		if (!format.contains(WIKI_START_SAVESEQUENCE)
				|| !format.contains(WIKI_END_SAVESEQUENCE)) {
			return false;
		}
		
		int wikiStart = format.indexOf(WIKI_START_SAVESEQUENCE);
		int wikiEnd = format.indexOf(WIKI_END_SAVESEQUENCE);
		
		if (wikiStart > wikiEnd) {
			return false;
		}
		
		return true;
	}
}
