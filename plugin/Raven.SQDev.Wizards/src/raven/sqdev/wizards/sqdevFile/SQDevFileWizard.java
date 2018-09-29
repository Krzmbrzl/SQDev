package raven.sqdev.wizards.sqdevFile;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import raven.sqdev.sqdevFile.ESQDevFileType;
import raven.sqdev.util.EFileType;

public class SQDevFileWizard extends Wizard implements INewWizard {

	private SQDevFileWizardPage page;
	private IProject project;

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		Object o = selection.getFirstElement();

		if (o instanceof IResource) {
			project = ((IResource) o).getProject();
		}
	}

	@Override
	public boolean performFinish() {
		ESQDevFileType type = page.getType();

		if (type != null && project != null) {
			IResource file = project.findMember(type + EFileType.SQDEV.getExtension());

			if (file == null) {
				EFileType fileType = EFileType.SQDEV;

				IPath containerLocation = project.getRawLocation();

				if (containerLocation == null) {
					// in case rawLocation does not work
					containerLocation = project.getLocation();
				}

				fileType.setPath(containerLocation.toOSString());

				fileType.create(type.toString());

				return true;
			}
		}

		return false;
	}

	/**
	 * Adding the page to the wizard.
	 */
	@Override
	public void addPages() {
		page = new SQDevFileWizardPage(project);
		addPage(page);
	}

}
