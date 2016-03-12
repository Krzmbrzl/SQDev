package raven.sqdev.editors;

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
	protected String[] keywords;
	
	@Override
	public String[] getKeywords() {
		return (keywords == null)? new String[0] : keywords;
	}
	
	@Override
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
	
}
