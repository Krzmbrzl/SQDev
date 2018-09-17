package raven.sqdev.editors.sqfeditor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import raven.sqdev.editors.BasicKeywordProvider;
import raven.sqdev.infoCollection.base.Function;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.misc.ModUtils;
import raven.sqdev.util.ProjectUtil;

public class SQFFunctionProvider extends BasicKeywordProvider {

	SQF_Editor editor;

	public SQFFunctionProvider(SQF_Editor editor) {
		this(editor, null);
	}

	public SQFFunctionProvider(SQF_Editor editor, IProject project) {
		this.editor = editor;

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
		System.out.println("Project-Start: " + System.currentTimeMillis());

		KeywordList list = getKeywordList();

		// get mod-dependencies for the project
		List<String> configuredMods = ProjectUtil.getProjectModNames(project);

		Job getFunctionsJob = new Job("Retrieving SQF-functions") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Extracting functions from PBOs", configuredMods.size());
				for (String currentMod : configuredMods) {
					list.addKeywords(ModUtils.getFunctionsFor(currentMod));
					monitor.worked(1);

					if (monitor.isCanceled()) {
						return Status.CANCEL_STATUS;
					}
				}

				if (configuredMods.size() > 0) {
					SQFFunctionProvider.this.notifyKeywordListChangeListener();
				}

				// re-parse with loaded functions
				editor.parseInput();

				monitor.done();

				return Status.OK_STATUS;
			}
		};

		getFunctionsJob.schedule();

		System.out.println("Project-End: " + System.currentTimeMillis());
	}
}
