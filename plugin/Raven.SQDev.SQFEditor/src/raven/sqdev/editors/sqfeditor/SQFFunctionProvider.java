package raven.sqdev.editors.sqfeditor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;

import raven.sqdev.editors.BasicKeywordProvider;
import raven.sqdev.infoCollection.base.Function;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.misc.ModUtils;
import raven.sqdev.util.ProjectUtil;

public class SQFFunctionProvider extends BasicKeywordProvider {

	public SQFFunctionProvider() {
		this(null);
	}

	public SQFFunctionProvider(IProject project) {
		KeywordList list = new KeywordList();

		// add vanilla functions
		Set<Function> vanillaFunctions = new HashSet<>();
		ModUtils.getVanillaFunctions(vanillaFunctions);

		list.addKeywords(vanillaFunctions);

		setKeywordList(list);

		if (project != null) {
			setProject(project);
		}
	}

	public void setProject(IProject project) {
		KeywordList list = getKeywordList();

		// get mod-dependencies for the project
		List<String> configuredMods = ProjectUtil.getProjectModNames(project);

		for (String currentMod : configuredMods) {
			list.addKeywords(ModUtils.getFunctionsFor(currentMod));
		}
		
		if(configuredMods.size() > 0) {
			this.notifyKeywordListChangeListener();
		}
	}
}
