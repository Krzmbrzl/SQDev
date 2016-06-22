package raven.sqdev.wizards.stringtable;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

public class StringTableWizardPage extends WizardPage {
	/**
	 * The selected project
	 */
	private IProject project;
	
	public StringTableWizardPage(String pageName) {
		super(pageName);
		
		setTitle("New StringTable");
	}
	
	public StringTableWizardPage(String pageName, String title, ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout());
		
		Label infoLabel = new Label(container, SWT.NONE);
		infoLabel.setText("This wizard will create a new StringTable...");
		
		setControl(container);
		check();
	}
	
	/**
	 * Checks if there is already an existing StringTable
	 */
	private void check() {
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getSelectionService().getSelection();
		
		if (selection == null) {
			updateStatus("No project selected!");
			return;
		}
		
		if (!(selection instanceof IStructuredSelection)) {
			updateStatus("The selection is not of type IStructuredSelection!");
			return;
		}
		
		IStructuredSelection structured = (IStructuredSelection) selection;
		Object firstElement = structured.getFirstElement();
		
		if (!(firstElement instanceof IResource)) {
			updateStatus("No resource selected!");
			return;
		}
		
		if (!(firstElement instanceof IProject || firstElement instanceof IFile)) {
			updateStatus("You have to select either a project or a file!");
			return;
		}
		
		project = ((IResource) firstElement).getProject();
		
		if (project == null) {
			updateStatus("Can't retrieve corresponding project for "
					+ ((IResource) firstElement).getName());
			return;
		}
		
		IPath path = project.getLocation();
		File file = path.toFile();
		File[] fileList = file.listFiles();
		
		if (fileList != null) {
			for (File currentFile : fileList) {
				if (currentFile.getName().toLowerCase().equals("stringtable.xml")) {
					updateStatus("The project " + project.getName()
							+ " does already contain a StringTable!");
					return;
				}
			}
		}
	}
	
	/**
	 * Sets the error message for this wizard page. It will automatically
	 * prevent the page from being finished as long as there is a error message.
	 * If you want to make remove the error message pass <code>null</code> as an
	 * argument which will make the page finishable again.
	 * 
	 * @param message
	 *            The message to be displayed
	 */
	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}
	
	/**
	 * Gets the project the stringTable should be created in
	 */
	public IProject getProject() {
		return project;
	}
	
}
