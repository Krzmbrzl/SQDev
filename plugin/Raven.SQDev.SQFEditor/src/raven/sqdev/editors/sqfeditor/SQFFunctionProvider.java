package raven.sqdev.editors.sqfeditor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
		this.editor = editor;
	}

	/**
	 * Initializes this provider on the given project. This will cause the
	 * vanilla functions plus the ones from the project-specific mods to be
	 * loaded.
	 * 
	 * @param project
	 *            The {@linkplain IProject} to initialize this provider on or
	 *            <code>null</code> if there is none
	 */
	public void init(IProject project) {
		KeywordList list = getKeywordList();

		// get mod-dependencies for the project
		List<String> configuredMods = project != null ? ProjectUtil.getProjectModNames(project)
				: Collections.emptyList();

		Job getFunctionsJob = new Job("Retrieving SQF-functions") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				List<Function> vanillaFunctions = new ArrayList<>();
				ModUtils.getVanillaFunctions(vanillaFunctions);
				list.addKeywords(vanillaFunctions);

				monitor.beginTask("Extracting functions from PBOs", configuredMods.size());
				for (String currentMod : configuredMods) {
					list.addKeywords(ModUtils.getFunctionsFor(currentMod));
					monitor.worked(1);

					if (monitor.isCanceled()) {
						return Status.CANCEL_STATUS;
					}
				}

				// notify about changed keywords
				SQFFunctionProvider.this.notifyKeywordListChangeListener();

				// re-parse with loaded functions
				editor.parseInput();

				monitor.done();

				return Status.OK_STATUS;
			}
		};

		getFunctionsJob.schedule();
	}
}
