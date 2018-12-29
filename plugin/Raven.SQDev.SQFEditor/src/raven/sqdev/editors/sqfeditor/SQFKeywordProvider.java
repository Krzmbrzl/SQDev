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
	 * Creates an instance of this SQFKeywordProvider that will set it's keywords
	 * automatically
	 */
	public SQFKeywordProvider() {
		ResourceManager manager = ResourceManager.getManager();
		String savedKeywords = getKeywordResource(manager);

		if (savedKeywords == null) {
			setKeywordList(new KeywordList());

			SQDevInfobox info = new SQDevInfobox("Failed at instantiating editor properly!\n\nReason:"
					+ "\nProblems with reading respective resource");
			info.open();

			return;
		}

		KeywordList list = new KeywordList(savedKeywords);

		if (list.getFailures().size() > 0) {
			SQDevInfobox info = new SQDevInfobox("Failed to load " + list.getFailures().size() + " commands",
					list.getFailures());

			info.open(false);
		}

		setKeywordList(list);
	}

	/**
	 * Gets the content of the keyword resource for this provider
	 * 
	 * @param manager
	 *            The {@linkplain ResourceManager} to use in order to retrieve the
	 *            resource
	 */
	protected String getKeywordResource(ResourceManager manager) {
		return manager.getResourceContent(ResourceManager.SQF_KEYWORDS_RESOURCE);
	}
}
