package raven.sqdev.wizards.sqfNewFileWizard;

import java.io.File;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import raven.sqdev.misc.TextUtils;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (sqf).
 */

public class SqfNewFileWizardPage extends WizardPage {
	private String containerText;
	
	private Text fileText;
	
	private ISelection selection;
	
	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public SqfNewFileWizardPage(ISelection selection) {
		super("newSqfFileWizardPage");
		setTitle("New SQF file");
		setDescription("This wizard creates a new file with *.sqf extension");
		this.selection = selection;
	}
	
	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		layout.verticalSpacing = 9;
		
		Label label = new Label(container, SWT.NULL);
		label.setText("&File name:");
		
		fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		fileText.setLayoutData(gd);
		fileText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		initialize();
		dialogChanged();
		setControl(container);
	}
	
	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */
	
	private void initialize() {
		if (selection != null && selection.isEmpty() == false
				&& selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return;
			Object obj = ssel.getFirstElement();
			if (obj instanceof IResource) {
				IContainer container;
				if (obj instanceof IContainer)
					container = (IContainer) obj;
				else
					container = ((IResource) obj).getParent();
				containerText = container.getFullPath().toString();
			}
		}
		
		fileText.setText("");
		fileText.setFocus();
	}
	
	/**
	 * Ensures that both text fields are set.
	 */
	
	private void dialogChanged() {
		IResource container = ResourcesPlugin.getWorkspace().getRoot()
				.findMember(new Path(getContainerName()));
		String fileName = getFileName();
		
		if (getContainerName().length() == 0) {
			updateStatus("File container must be specified");
			return;
		}
		
		if (container == null
				|| (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
			updateStatus("File container must exist");
			return;
		}
		
		if (!container.isAccessible()) {
			updateStatus("Project must be writable");
			return;
		}
		
		if (fileName.length() == 0) {
			updateStatus("File name must be specified");
			return;
		}
		
		if (!TextUtils.isValidFileName(fileName)) {
			updateStatus(TextUtils.whyIsInvalidFileName(fileName));
			return;
		}
		
		if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
			updateStatus("File name must be valid");
			return;
		}
		
		int dotLoc = fileName.lastIndexOf('.');
		if (dotLoc != -1) {
			String ext = fileName.substring(dotLoc + 1);
			if (ext.equalsIgnoreCase("sqf") == false) {
				updateStatus("File extension must be \"sqf\"");
				return;
			}
		}
		
		
		String fileNameWithExt = (fileName.endsWith(".sqf")) ? fileName : fileName + ".sqf";
		File toTest = new File(container.getLocationURI().getPath() + "/" + fileNameWithExt);
		
		if (toTest.exists()) {
			updateStatus("A file with the given name does already exist!");
			return;
		}
		
		updateStatus(null);
	}
	
	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}
	
	public String getContainerName() {
		return containerText;
	}
	
	public String getFileName() {
		return fileText.getText();
	}
}