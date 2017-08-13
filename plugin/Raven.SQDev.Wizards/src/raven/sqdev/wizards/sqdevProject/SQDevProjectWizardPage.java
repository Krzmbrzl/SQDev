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
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import raven.sqdev.misc.TextUtils;
import raven.sqdev.util.EProjectType;
import raven.sqdev.util.SQDevInfobox;
import raven.sqdev.util.SQDevInformation;
import raven.sqdev.util.SQDevPreferenceUtil;
import raven.sqdev.util.Util;
import raven.sqdev.utilInterfaces.ISQDevInformationProvider;

/**
 * The witard page for creating a SQDev project
 * 
 * @author Raven
 * 
 */
public class SQDevProjectWizardPage extends WizardPage
		implements ISQDevInformationProvider {
	
	/**
	 * Field for the name of the project
	 */
	private Text nameText;
	
	/**
	 * Field for the project type
	 */
	private Combo typeCombo;
	
	/**
	 * Field for the profile selection
	 */
	private Combo profileCombo;
	
	/**
	 * Field for the selection of the terrain
	 */
	private Combo terrainCombo;
	/**
	 * The label corresponding to {@link #mpButton}
	 */
	private Label mpLabel;
	/**
	 * Checkbox to indicate that it is a MP mission
	 */
	private Button mpButton;
	/**
	 * The label for the terrain selection
	 */
	private Label terrainLabel;
	
	/**
	 * Field for enabling autoExport
	 */
	private Button autoExportButton;
	
	/**
	 * The project type
	 */
	private EProjectType projectType;
	
	public SQDevProjectWizardPage(String pageName) {
		super(pageName);
		setTitle("New SQDev project");
	}
	
	public SQDevProjectWizardPage(String pageName, String title,
			ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		layout.verticalSpacing = 9;
		
		// name selection
		String nameTooltip = "The project name (will be the mission name as well)";
		
		Label nameLabel = new Label(container, SWT.NULL);
		nameLabel.setText("&Project name:");
		nameLabel.setToolTipText(nameTooltip);
		
		setNameText(new Text(container, SWT.BORDER | SWT.SINGLE));
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		getNameText().setToolTipText(nameTooltip);
		getNameText().setLayoutData(gd);
		getNameText().addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		// type selection
		String typeTooltip = "Defines the project type";
		
		Label typeLabel = new Label(container, SWT.NULL);
		typeLabel.setText("&Project type:");
		typeLabel.setToolTipText(typeTooltip);
		
		typeCombo = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		typeCombo.setToolTipText(typeTooltip);
		typeCombo.setLayoutData(gd);
		
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
				EProjectType type = EProjectType
						.getIndex(typeCombo.getSelectionIndex());
				
				if (!type.equals(EProjectType.MISSION)) {
					terrainCombo.setVisible(false);
					terrainLabel.setVisible(false);
					mpLabel.setVisible(false);
					mpButton.setVisible(false);
				} else {
					terrainCombo.setVisible(true);
					terrainLabel.setVisible(true);
					mpLabel.setVisible(true);
					mpButton.setVisible(true);
				}
				
				setProjectType(type);
				dialogChanged();
			}
		});
		
		// map selection
		String terrainToolTip = "The map the mission should be played on";
		
		terrainLabel = new Label(container, SWT.NULL);
		terrainLabel.setText("&Terrain:");
		terrainLabel.setToolTipText(terrainToolTip);
		
		terrainCombo = new Combo(container, SWT.DROP_DOWN);
		
		for (String currentTerrain : Util.getTerrains()) {
			terrainCombo.add(currentTerrain);
		}
		
		terrainCombo.setText(SQDevPreferenceUtil.getDefaultTerrain());
		terrainCombo.setToolTipText(terrainToolTip);
		terrainCombo.setLayoutData(gd);
		
		terrainCombo.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		// profile selection
		String profileTooltip = "The ArmA profile this project should be associated with";
		
		Label profileLabel = new Label(container, SWT.NULL);
		profileLabel.setText("&Profile:");
		profileLabel.setToolTipText(profileTooltip);
		
		profileCombo = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		profileCombo.setToolTipText(profileTooltip);
		profileCombo.setLayoutData(gd);
		
		for (String currentProfile : Util.getProfiles()) {
			profileCombo.add(currentProfile);
		}
		
		int index = profileCombo
				.indexOf(SQDevPreferenceUtil.getDefaultProfile());
		if (index >= 0) {
			profileCombo.select(index);
		} else {
			profileCombo.select(0);
			
			SQDevInfobox info = new SQDevInfobox("The default profile \""
					+ SQDevPreferenceUtil.getDefaultProfile()
					+ "\" can no longer be found!", SWT.ICON_WARNING);
			
			info.open(false);
		}
		
		profileCombo.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		// autoExport selection
		String autoExportTooltip = "Defines whether the project should get exported automatically"
				+ " whenever a file in it changed";
		
		Label autoExportLabel = new Label(container, SWT.NULL);
		autoExportLabel.setText("Auto export:");
		autoExportLabel.setToolTipText(autoExportTooltip);
		
		autoExportButton = new Button(container, SWT.CHECK);
		autoExportButton.setToolTipText(autoExportTooltip);
		autoExportButton.setSelection(
				SQDevPreferenceUtil.getAutoExportDefaultEnabled());
		
		// MP selection
		String mpTooltip = "Indicates whether this mission should be a MP mission";
		
		mpLabel = new Label(container, SWT.NONE);
		mpLabel.setText("&Multiplayer:");
		mpLabel.setToolTipText(mpTooltip);
		
		mpButton = new Button(container, SWT.CHECK);
		mpButton.setToolTipText(mpTooltip);
		
		mpButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				dialogChanged();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
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
		// check if the entered project name is valid
		if (!TextUtils.isValidFileName(getProjectName())) {
			updateStatus(TextUtils.whyIsInvalidFileName(getProjectName()));
			return;
		}
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject project = root.getProject(getProjectName());
		
		if (project.exists()) {
			updateStatus("A project with the given name does already exist!");
			return;
		}
		
		if (terrainCombo.getText().isEmpty()) {
			updateStatus("No terrain selected!");
			return;
		}
		
		if (profileCombo.getText().isEmpty()) {
			updateStatus("No profile selected!");
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
	 * Gets the project type the user has chosen
	 */
	public EProjectType getProjectType() {
		return projectType;
	}
	
	/**
	 * Gets the name the user has chosen for the project to be created
	 * 
	 * @return
	 */
	public String getProjectName() {
		return getNameText().getText();
	}
	
	/**
	 * Gets the user chosen profile
	 */
	public String getProfile() {
		return profileCombo.getText();
	}
	
	/**
	 * Gets the user chosen terrain
	 */
	public String getTerrain() {
		return terrainCombo.getText();
	}
	
	/**
	 * Gets the user chosen value for autoExport
	 */
	public boolean getAutoExport() {
		return autoExportButton.getSelection();
	}
	
	/**
	 * Gets the user chosen value whether the mission is going to be MP
	 */
	public boolean getMP() {
		return mpButton.getSelection();
	}
	
	/**
	 * Gets the data of this page bundled into a SQDevInformation
	 */
	public SQDevInformation getInformation() {
		SQDevInformation info = new SQDevInformation();
		
		info.setAutoExport(getAutoExport());
		info.setName(getProjectName());
		info.setProfile(getProfile());
		info.setTerrain(getTerrain());
		info.setMp(getMP());
		
		return info;
	}
	
}
