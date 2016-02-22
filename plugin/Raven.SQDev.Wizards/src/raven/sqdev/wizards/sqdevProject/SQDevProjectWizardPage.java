package raven.sqdev.wizards.sqdevProject;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import raven.sqdev.util.EProjectType;
import raven.sqdev.util.StringUtils;

public class SQDevProjectWizardPage extends WizardPage {
	
	private Text nameText;
	private Combo typeCombo;
	private EProjectType projectType;
	
	public SQDevProjectWizardPage(String pageName) {
		super(pageName);
		setTitle("New SQDev project");
	}
	
	public SQDevProjectWizardPage(String pageName, String title, ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		layout.verticalSpacing = 9;
		
		Label nameLabel = new Label(container, SWT.NULL);
		nameLabel.setText("&Project name:");
		
		setNameText(new Text(container, SWT.BORDER | SWT.SINGLE));
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		getNameText().setLayoutData(gd);
		getNameText().addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		Label typeLabel = new Label(container, SWT.NULL);
		typeLabel.setText("&Project type:");
		
		typeCombo = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		
		for (EProjectType current : EProjectType.values()) {
			typeCombo.add(current.getDisplayName());
		}
		
		if (EProjectType.values().length > 0) {
			typeCombo.select(0);
			setProjectType(EProjectType.getIndex(0));
		}
		
		typeCombo.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				setProjectType(EProjectType.getIndex(typeCombo.getSelectionIndex()));
				dialogChanged();
			}
		});
		
		getNameText().setFocus();
		dialogChanged();
		setControl(container);
	}
	
	/**
	 * Gets notified whenever the dialog has changed
	 */
	private void dialogChanged() {
		validate();
		
		setDescription(getProjectType().getCreationDescription());
		
	}
	
	/**
	 * Validates the given input
	 */
	private void validate() {
		// check if the cosen project type is valid
		if (getProjectType() != EProjectType.SQF) {
			// any other prject type is not yet implemented
			updateStatus("The projectType \"" + getProjectType().getDisplayName()
					+ "\" is not yet available!");
			return;
		}
		
		// check if the entered project name is valid	
		if(!StringUtils.isValidProjectName(getProjectName())) {
			updateStatus(StringUtils.whyIsInvalidProjectName(getProjectName()));
			return;
		}
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject project = root.getProject(getProjectName());
		
		if(project.exists()) {
			updateStatus("A project with the given name does already exist!");
			return;
		}
		
		// remove any error message and make the page finishable
		updateStatus(null);
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
	public void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}
	
	public EProjectType getProjectType() {
		return projectType;
	}
	
	private void setProjectType(EProjectType projectType) {
		this.projectType = projectType;
	}

	private Text getNameText() {
		return nameText;
	}

	private void setNameText(Text nameText) {
		this.nameText = nameText;
	}
	
	/**
	 * Gets the name the user has chosen for the project to be created
	 * @return
	 */
	public String getProjectName() {
		return getNameText().getText();
	}
	
}
