package raven.sqdev.editors.sqfeditor;

import java.util.ArrayList;
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

	public void setProject(IProject project) {
		KeywordList list = getKeywordList();

		// get mod-dependencies for the project
		List<String> configuredMods = ProjectUtil.getProjectModNames(project);

		Job getFunctionsJob = new Job("Retrieving SQF-functions") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				long start = System.currentTimeMillis();
				
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
				
				System.out.println("\n\n Function load time:" + (System.currentTimeMillis() - start + "\n"));

				if (configuredMods.size() > 0) {
					SQFFunctionProvider.this.notifyKeywordListChangeListener();
				}

				// update editor
				editor.update(true);
				// re-parse with loaded functions
				editor.parseInput();

				monitor.done();

				return Status.OK_STATUS;
			}
		};

		getFunctionsJob.schedule();
	}
}
