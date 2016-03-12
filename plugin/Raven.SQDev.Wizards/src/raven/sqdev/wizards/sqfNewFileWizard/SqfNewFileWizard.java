package raven.sqdev.wizards.sqfNewFileWizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.*;

import raven.sqdev.util.EFileType;

/**
 * This is a little wizard for the creation of a new SQF-file that will have the
 * extension <code>"*.sqf"</code>
 */

public class SqfNewFileWizard extends Wizard implements INewWizard {
	private SqfNewFileWizardPage page;
	private ISelection selection;
	
	/**
	 * Constructor for SqfNewFileWizard.
	 */
	public SqfNewFileWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Adding the page to the wizard.
	 */
	
	public void addPages() {
		page = new SqfNewFileWizardPage(selection);
		addPage(page);
	}
	
	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We
	 * will create an operation and run it using wizard as execution context.
	 */
	public boolean performFinish() {
		final String fileName = page.getFileName();
		
		EFileType.SQF.create(fileName);
		
		return true;
	}
	
	/**
	 * We will accept the selection in the workbench to see if we can initialize
	 * from it.
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}