package raven.sqdev.wizards.sqfNewFileWizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.*;

import raven.sqdev.util.EFileType;
import raven.sqdev.util.ProjectUtil;
import raven.sqdev.util.SQDevInformation;

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
	
	@Override
	public void addPages() {
		page = new SqfNewFileWizardPage(selection);
		addPage(page);
	}
	
	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We
	 * will create an operation and run it using wizard as execution context.
	 */
	@Override
	public boolean performFinish() {
		final String fileName = page.getFileName();
		
		EFileType file = EFileType.SQF;
		SQDevInformation info = null;
		
		if (selection instanceof IStructuredSelection) {
			Object obj = ((IStructuredSelection) selection).getFirstElement();
			
			if (obj instanceof IResource && obj != null) {
				IContainer container;
				
				if (obj instanceof IContainer) {
					container = (IContainer) obj;
				} else {
					container = ((IResource) obj).getParent();
				}
				
				IPath containerLocation = container.getRawLocation();
				
				if (containerLocation == null) {
					// in case rawLocation does not work
					containerLocation = container.getLocation();
				}
				
				// set path for the file to be created
				file.setPath(containerLocation.toOSString());
				
				while (!(container instanceof IProject) && container != null) {
					container = container.getParent();
				}
				
				if(container != null) {
					IProject project = (IProject) container;
					
					if (ProjectUtil.isSQDevProject(project)) {
						// retrieve additional information
						info = ProjectUtil.getInformation(project);
						
						info.setName(project.getName());
					}
				}
			}
		}
		
		if(info != null) {
			file.setInformation(info);
		}
		
		file.create(fileName);
		
		return true;
	}
	
	/**
	 * We will accept the selection in the workbench to see if we can initialize
	 * from it.
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}