package raven.sqdev.wizards.export;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import raven.sqdev.exceptions.SQDevFileIsInvalidException;
import raven.sqdev.exceptions.SQDevFileNoSuchAttributeException;
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.sqdevFile.ESQDevFileAnnotation;
import raven.sqdev.sqdevFile.ESQDevFileAttribute;
import raven.sqdev.sqdevFile.SQDevFile;
import raven.sqdev.util.ProjectUtil;

public class SQDevExportWizard extends Wizard implements IExportWizard {

	/**
	 * The wizard's page
	 */
	protected SQDevExportWizardPage page;

	/**
	 * The project to export
	 */
	protected IProject project;

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		Object sel = selection.getFirstElement();

		if (sel instanceof IProject) {
			project = (IProject) sel;

			if (ProjectUtil.isSQDevProject(project)) {
				// create page and add to this wizard
				page = new SQDevExportWizardPage("SQDevExportWizardPage", project);
				addPage(page);
			} else {
				// only export SQDevProjects
				SQDevInfobox info = new SQDevInfobox(
						"This type of export wizard is designated for the export of SQDevProjects !", SWT.ICON_ERROR);
				info.open();
			}
		} else {
			// There should be a project selected
			SQDevInfobox info = new SQDevInfobox("Only projects can be exported!", SWT.ICON_ERROR);
			info.open();
		}
	}

	@Override
	public boolean performFinish() {
		SQDevFile linkFile = ProjectUtil.getLinkFile(project);

		try {
			linkFile.processAttribute(ESQDevFileAttribute.TERRAIN);
			Path exportPath = (Path) page.getDestination()
					.append(project.getName() + "." + ESQDevFileAttribute.TERRAIN.getValue());

			linkFile.processAnnotation(ESQDevFileAnnotation.IGNORE);
			ArrayList<String> ignore = ESQDevFileAnnotation.IGNORE.getValues();

			linkFile.processAnnotation(ESQDevFileAnnotation.PRESERVE);
			ArrayList<String> preserve = ESQDevFileAnnotation.PRESERVE.getValues();

			Job exportJob = new Job("Export Project") {

				@Override
				protected IStatus run(IProgressMonitor monitor) {
					monitor.beginTask("Exporting project " + project.getName() + "...", 1);

					// export the project
					ProjectUtil.export(project, exportPath, ignore, preserve);

					monitor.worked(1);
					monitor.done();

					return Status.OK_STATUS;
				}
			};

			exportJob.schedule();

			// accept finish
			return true;
		} catch (SQDevFileIsInvalidException | SQDevFileNoSuchAttributeException | IOException e) {
			// inform user
			String message = "Failed at exporting project \"" + project.getName() + "\"\n\nReason: " + e.getMessage();

			SQDevInfobox info = new SQDevInfobox(message, SWT.ICON_ERROR);
			info.open();

			e.printStackTrace();

			// accept finish
			return true;
		}
	}

}
