package raven.sqdev.wizards.importWizard;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

import raven.sqdev.util.ProjectUtil;

public class SQDevImportWizard extends Wizard implements IImportWizard {
	
	private SQDevImportWizardPage page;
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		page = new SQDevImportWizardPage("importWiazrdPage");
		addPage(page);
	}
	
	@Override
	public boolean performFinish() {
		Path selectedPath = page.getDirectory();
		
		Job importJob = new Job("Importing folder...") {
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Importing folder...", 1);
				
				ProjectUtil.importAsProject(selectedPath);
				
				monitor.worked(1);
				monitor.done();
				
				return Status.OK_STATUS;
			}
		};
		
		importJob.schedule();
		
		return true;
	}
	
}
