package raven.sqdev.wizards.sqdevProject;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;

import raven.sqdev.util.EProjectType;
import raven.sqdev.util.SQDevInformation;
import raven.sqdev.wizards.SQDevBaseNewProjectWizard;

/**
 * The wizard used to create a new SQDevProject
 * 
 * @author Raven
 *
 */
public class SQDevProjectWizard extends SQDevBaseNewProjectWizard {
	/**
	 * This wizard's ID
	 */
	public static String WIZARD_ID = "raven.sqdev.wizards.sqdevproject";
	
	private SQDevProjectWizardPage page;
	
	public SQDevProjectWizard() {
		super();
	}
	
	@Override
	public void addPages() {
		page = new SQDevProjectWizardPage("SQDevProjectWizardPage");
		addPage(page);
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		
	}
	
	@Override
	public EProjectType getProjectType() {
		EProjectType type = page.getProjectType();
		type.setInformation(page.getInformation());
		
		return type;
	}

	@Override
	public SQDevInformation getInformation() {
		return page.getInformation();
	}
	
}
