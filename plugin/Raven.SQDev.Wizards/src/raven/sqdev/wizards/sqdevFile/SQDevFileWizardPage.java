package raven.sqdev.wizards.sqdevFile;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import raven.sqdev.sqdevFile.ESQDevFileType;
import raven.sqdev.util.EFileType;

/**
 * The wizard page for the {@linkplain SQDevFileWizard}
 * 
 * @author Raven
 *
 */
public class SQDevFileWizardPage extends WizardPage {

	/**
	 * The Combo-Box for selecting the SQDevFile-type
	 */
	private Combo combo;
	/**
	 * The project this wizard is being invoked on
	 */
	private IProject project;

	/**
	 * Creates a new instance of this wizard page
	 * 
	 * @param container
	 *            The {@linkplain IProject} this wizard is being invoked on
	 */
	protected SQDevFileWizardPage(IProject container) {
		super("SQDevFileWizardPage");

		setTitle("New SQDevFile");
		setDescription("Creates a new SQdevFile of the given type");

		this.project = container;
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		container.setLayout(new GridLayout(2, false));

		String tooltip = "Specifies the type of the SQDev-file to be created";

		Label label = new Label(container, SWT.NONE);
		label.setText("&Type:");
		label.setToolTipText(tooltip);

		combo = new Combo(container, SWT.READ_ONLY | SWT.DROP_DOWN);
		combo.setToolTipText(tooltip);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		for (ESQDevFileType currentType : ESQDevFileType.values()) {
			if (currentType == ESQDevFileType.NULLTYPE) {
				continue;
			}

			combo.add(currentType.toString());
		}

		combo.select(0);

		combo.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				dialogChanged();
			}
		});

		setControl(container);
		dialogChanged();
	}

	private void dialogChanged() {
		String selection = combo.getText();

		if (ESQDevFileType.resolve(selection) == null) {
			updateStatus("Unknown SQDevFile type!");
			return;
		}

		if (project == null) {
			updateStatus("Invalid container (internal error)");
			return;
		}

		IResource res = project.findMember(selection + EFileType.SQDEV.getExtension());

		if (res != null) {
			updateStatus("The selected SQDevFile type does already exist in the project " + project.getName());
			return;
		}

		updateStatus(null);
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	/**
	 * Gets the file type as set in this page
	 */
	public ESQDevFileType getType() {
		return ESQDevFileType.resolve(combo.getText());
	}

}
