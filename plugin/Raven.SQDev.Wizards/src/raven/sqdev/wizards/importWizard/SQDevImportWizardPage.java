package raven.sqdev.wizards.importWizard;

import java.io.File;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

import raven.sqdev.util.ProjectUtil;
import raven.sqdev.util.Util;

public class SQDevImportWizardPage extends WizardPage {
	
	/**
	 * The directory label
	 */
	Label pathLabel;
	
	/**
	 * The combo for the chosen directory
	 */
	Combo directoryCombo;
	
	/**
	 * The browse button
	 */
	Button browseButton;
	
	public SQDevImportWizardPage(String pageName) {
		super(pageName);
		setTitle("Import");
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		container.setLayout(layout);
		
		// directory selection
		String pathTooltip = "The path to the folder that should be imported";
		
		pathLabel = new Label(container, SWT.NULL);
		pathLabel.setText("&Path:");
		pathLabel.setToolTipText(pathTooltip);
		
		
		directoryCombo = new Combo(container, SWT.DROP_DOWN);
		directoryCombo.setToolTipText(pathTooltip);
		
		for (String currentProfile : Util.getProfiles()) {
			// add the profiles directories
			for (File currentFile : new File(Util.getMissionsDirectory(currentProfile).toOSString())
					.listFiles()) {
					
				String fileName = currentFile.getName();
				fileName = (fileName.contains(".")) ? fileName.substring(0, fileName.indexOf("."))
						: fileName;
						
				if (!ProjectUtil.exists(fileName)) {
					// add all paths to the respective missions if their names
					// don't conflict with an existing project
					directoryCombo.add(currentFile.getAbsolutePath());
				}
			}
		}
		
		directoryCombo.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		
		browseButton = new Button(container, SWT.PUSH);
		browseButton.setToolTipText("Browse for the folder to import");
		browseButton.setText("Browse...");
		
		browseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent event) {
				DirectoryDialog dialog = new DirectoryDialog(
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
						
				String initialPath = directoryCombo.getText();
				
				if (initialPath != null && !initialPath.isEmpty()) {
					dialog.setFilterPath(initialPath);
				}
				
				String newPath = dialog.open();
				
				if (newPath != null && !newPath.isEmpty()) {
					directoryCombo.setText(newPath);
				}
			}
		});
		
		
		setControl(container);
		dialogChanged();
	}
	
	/**
	 * Gets called whenever the dialog changes
	 */
	protected void dialogChanged() {
		validate();
	}
	
	/**
	 * Validates the current input
	 */
	protected void validate() {
		Path filePath = new Path(directoryCombo.getText());
		
		filePath = (Path) filePath.makeAbsolute();
		
		File file = filePath.toFile();
		
		if (!file.exists()) {
			updateStatus("The file\"" + file.getAbsolutePath() + "\" does not exist!");
			return;
		}
		
		if (!file.isDirectory()) {
			updateStatus("The given file has to be a folder!");
			return;
		}
		
		if (!Util.isMissionFolder(file)) {
			updateStatus("The given folder is not a mission!");
			return;
		}
		
		if (ProjectUtil.exists(file.getName().substring(0, file.getName().indexOf(".")))) {
			updateStatus("A project with the given name does already exist!");
			return;
		}
		
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
	protected void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}
	
	/**
	 * Gets the selected flder that should be imported
	 */
	public Path getDirectory() {
		return (Path) new Path(directoryCombo.getText()).makeAbsolute();
	}
	
}