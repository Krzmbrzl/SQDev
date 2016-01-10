package raven.sqdev.wizards.sqdevProject;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class SQDevProjectWizard extends Wizard implements INewWizard {
	private SQDevProjectWizardPage page;
	
	public SQDevProjectWizard() {
		super();
	}
	
	public void addPages() {
		page = new SQDevProjectWizardPage("SQDevProjectWizardPage");
		addPage(page);
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		
	}
	
	@Override
	public boolean performFinish() {
		String projectName = page.getProjectName();
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		
		page.getProjectType().create(projectName, root);
		
		return true;
		
	}
	
}
