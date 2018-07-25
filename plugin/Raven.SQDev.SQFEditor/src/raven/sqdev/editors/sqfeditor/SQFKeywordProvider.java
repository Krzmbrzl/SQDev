package raven.sqdev.editors.sqfeditor;

import raven.sqdev.editors.BasicKeywordProvider;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.pluginManagement.ResourceManager;

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
		ResourceManager manager = ResourceManager.getManager();
		String savedKeywords = manager.getResourceContent(ResourceManager.KEYWORDS_RESOURCE);
		
		if (savedKeywords == null) {
			setKeywordList(new KeywordList());
			
			SQDevInfobox info = new SQDevInfobox(
					"Failed at instantiating SQF editor properly!\n\nReason:"
							+ "\nProblems with reading respective resource");
			info.open();
			
			return;
		}
		
		KeywordList list = new KeywordList(savedKeywords);
		
		if (list.getFailures().size() > 0) {
			SQDevInfobox info = new SQDevInfobox(
					"Failed to load " + list.getFailures().size() + " commands",
					list.getFailures());
			
			info.open(false);
		}
		
		setKeywordList(list);
	}
}
