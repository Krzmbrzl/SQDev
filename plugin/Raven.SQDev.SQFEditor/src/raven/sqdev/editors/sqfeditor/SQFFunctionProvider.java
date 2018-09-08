package raven.sqdev.editors.sqfeditor;

import raven.sqdev.editors.BasicKeywordProvider;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.misc.ModUtils;

public class SQFFunctionProvider extends BasicKeywordProvider {

	public SQFFunctionProvider() {
		KeywordList list = new KeywordList();
		// TODO:
		list.addKeywords(ModUtils.getFunctionsFor("cba"));
		setKeywordList(list);
	}

}
