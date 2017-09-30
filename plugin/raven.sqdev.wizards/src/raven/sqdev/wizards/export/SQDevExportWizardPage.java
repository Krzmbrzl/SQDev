package raven.sqdev.wizards.export;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

import raven.sqdev.util.ProjectUtil;
import raven.sqdev.util.Util;

public class SQDevExportWizardPage extends WizardPage {
	
	/**
	 * The label for the export destination
	 */
	protected Label exportLabel;
	
	/**
	 * The text field displaying the selected destination folder
	 */
	protected Combo destinationCombo;
	
	/**
	 * The button for browsing for the destination
	 */
	protected Button browseButton;
	
	/**
	 * The project that should be exported
	 */
	protected IProject project;
	
	public SQDevExportWizardPage(String pageName, IProject project) {
		super(pageName);
		
		setTitle("Export SQDev project");
		
		this.project = project;
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		
		container.setLayout(layout);
		
		// create controls for selecting the export destination
		String destinationTooltip = "The folder in which the project should be exported";
		
		exportLabel = new Label(container, SWT.NULL);
		exportLabel.setText("&Destination:");
		exportLabel.setToolTipText(destinationTooltip);
		
		
		destinationCombo = new Combo(container, SWT.DROP_DOWN);
		destinationCombo.setToolTipText(destinationTooltip);
		destinationCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		for (String currentProfile : Util.getProfiles()) {
			boolean select = false;
			
			if (currentProfile.equals(ProjectUtil.getMissionProfile(project))) {
				// make this entry the selected one
				select = true;
			}
			
			destinationCombo.add(Util.getMissionsDirectory(currentProfile).toOSString());
			
			if (select) {
				// select the entry that has just been added
				destinationCombo.select(destinationCombo.getItemCount() - 1);
			}
		}
		
		destinationCombo.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		
		browseButton = new Button(container, SWT.PUSH);
		browseButton.setText("Browse...");
		browseButton.setToolTipText("Browse for the destination");
		browseButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				DirectoryDialog chooser = new DirectoryDialog(
						PlatformUI.getWorkbench().getDisplay().getActiveShell());
						
				chooser.setFilterPath(destinationCombo.getText());
				
				String dir = chooser.open();
				
				if (dir != null && !dir.isEmpty()) {
					destinationCombo.setText(dir);
				}
			}
		});
		
		
		setControl(container);
		dialogChanged();
	}
	
	/**
	 * Gets executed every time a value of this page changes
	 */
	protected void dialogChanged() {
		evaluate();
	}
	
	/**
	 * evaluates the current input
	 */
	protected void evaluate() {
		File destination = new File(destinationCombo.getText());
		
		if (!destination.exists()) {
			updateStatus("The folder \"" + destinationCombo.getText() + "\" does not exist!");
			return;
		}
		
		if (!destination.isDirectory()) {
			updateStatus("The selected file is not a folder!");
			return;
		}
		
		// reset page's status
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
	 * Gets the selected export destination
	 */
	public Path getDestination() {
		return new Path(destinationCombo.getText());
	}
	
}
