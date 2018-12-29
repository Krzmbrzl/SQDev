package raven.sqdev.editors.o2scriptEditor;

import raven.sqdev.editors.sqfeditor.SQFKeywordProvider;
import raven.sqdev.pluginManagement.ResourceManager;

public class O2ScriptKeywordProvider extends SQFKeywordProvider {

	public O2ScriptKeywordProvider() {
		super();
	}

	@Override
	protected String getKeywordResource(ResourceManager manager) {
		return manager.getResourceContent(ResourceManager.O2SCRIPT_KEYWORDS_RESOURCE);
	}

}
