package raven.sqdev.editors.sqfeditor;

import raven.sqdev.editors.BasicKeywordProvider;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.util.ResourceManager;
import raven.sqdev.util.SQDevInfobox;

/**
 * The KeywordProvider for the SQF keywords
 * 
 * @author Raven
 * 		
 */
public class SQFKeywordProvider extends BasicKeywordProvider {
	
	/**
	 * Creates an instance of this SQFKeywordProvider that will set it's
	 * keywords automatically
	 */
	public SQFKeywordProvider() {
		ResourceManager manager = new ResourceManager();
		String savedKeywords = manager.getResourceContent("SQFKeywords.txt");
		
		if (savedKeywords == null) {
			setKeywordList(new KeywordList());
			
			SQDevInfobox info = new SQDevInfobox(
					"Failed at instantiating SQF editor properly!\n\nReason:"
							+ "\nProblems with reading respective resource");
			info.open();
			
			return;
		}
		
		KeywordList list = new KeywordList(savedKeywords);
		
		setKeywordList(list);
	}
}
