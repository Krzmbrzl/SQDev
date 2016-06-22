package raven.sqdev.preferences.pages;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.infoCollection.SQFCommandCollector;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.pluginManagement.ResourceManager;
import raven.sqdev.pluginManagement.SQDevEclipseEventManager;
import raven.sqdev.preferences.preferenceEditors.ValueSQDevPreferenceEditor;
import raven.sqdev.util.SQDevInfobox;
import raven.sqdev.util.SQDevPreferenceUtil;

public class SQDevMiscPreferencePage extends SQDevPreferencePage {
	/**
	 * The job used for updating the keywords
	 */
	private static Job collectionJob;
	/**
	 * The button corresponding to the update function
	 */
	private Button updateButton;
	
	public SQDevMiscPreferencePage() {
		super();
	}
	
	@Override
	public void init(IWorkbench workbench) {
		setDescription("Miscellaneous preferences about the plugin");
		
		
		Group keywordGroup = createGroup("Keyword collection/updating");
		
		addPreferenceEditor(new ValueSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_COLLECTION_STARTCOMMAND, "&First command:",
				"The name of the first command in the list in the BIKI that should be"
						+ " processed. If there is no urgent need do not change this value!",
				keywordGroup));
		
		addPreferenceEditor(new ValueSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_COLLECTION_ENDCOMMAND, "&Last command:",
				"The name of the last command in the list in the BIKI that should be"
						+ " processed. If there is no urgent need do not change this value!",
				keywordGroup));
		
		// SQF keyword collection
		updateButton = new Button(createContainer(), SWT.PUSH);
		updateButton.setToolTipText(
				"Updates the SQF keywords according to the BIKI. This may take a while");
		updateButton.setEnabled(collectionJob == null || collectionJob.getResult() != null);
		
		// set text according to status
		if (!updateButton.isEnabled()) {
			updateButton.setText("Updating keywords...");
		} else {
			updateButton.setText("Update keywords");
		}
		
		updateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// change button status
				updateButton.setEnabled(false);
				updateButton.setText("Updating keywords...");
				updateButton.pack(true);
				
				updateKeywords();
			}
		});
		
		Button resetButton = new Button(createContainer(), SWT.PUSH);
		resetButton.setText("Reset Keywords");
		
		if (ResourceManager.getManager().isOnBackup("SQFKeywords.txt")) {
			resetButton.setEnabled(false);
		} else {
			resetButton.setToolTipText("Resets the keywords to the backup version");
			
			resetButton.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseUp(MouseEvent e) {
					// backup keywords
					ResourceManager.getManager().switchToBackup("SQFKeywords.txt");
					
					resetButton.setEnabled(false);
				}
			});
		}
	}
	
	/**
	 * Schedules the keyword update Job
	 */
	private void updateKeywords() {
		collectionJob = new Job("Updating keywords") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Updating keywords", IProgressMonitor.UNKNOWN);
				
				try {
					// gather keywords from the wiki
					KeywordList list = new SQFCommandCollector(
							new URL("https://community.bistudio.com/wiki/Category:"
									+ "Scripting_Commands_Arma_3"),
							SQDevPreferenceUtil.getFirstCommand(),
							SQDevPreferenceUtil.getLastCommand()).collect(monitor);
					
					if (monitor.isCanceled()) {
						// ask whether to save the list
						SQDevInfobox info = new SQDevInfobox(
								"The keyword update has been interrupted.\n"
										+ "Do you wish to store the current keywords?"
										+ " (This will override the current keword list and may"
										+ " leed to an incomplete list)",
								SWT.ICON_QUESTION | SWT.YES | SWT.NO);
						
						if (info.open() != SWT.YES) {
							// don't save
							return Status.OK_STATUS;
						}
					}
					
					// save the keywords
					monitor.done();
					monitor.beginTask("Storing keywords...", IProgressMonitor.UNKNOWN);
					
					ResourceManager manager = ResourceManager.getManager();
					manager.updateResource(ResourceManager.KEYWORDS_RESOURCE,
							list.getSaveableFormat());
					
					// tell the user to restart
					SQDevInfobox info = new SQDevInfobox(
							"In order for the new keywords to take effect"
									+ " you have to restart all respective editors",
							SWT.ICON_INFORMATION);
					
					info.open();
					
				} catch (IOException | SQDevException e) {
					SQDevInfobox info = new SQDevInfobox("Failed at updating keywords", e);
					info.open();
					
					e.printStackTrace();
					return Status.CANCEL_STATUS;
				} finally {
					if (!PlatformUI.getWorkbench().getDisplay().isDisposed()) {
						// reset buton status
						PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
							
							@Override
							public void run() {
								if (updateButton != null && !updateButton.isDisposed()) {
									updateButton.setText("Update keywords");
									updateButton.setEnabled(true);
									updateButton.pack(true);
								}
							}
						});
					}
					
					monitor.done();
				}
				
				return Status.OK_STATUS;
			}
		};
		
		// make sure eclipse is not closed with this job running
		SQDevEclipseEventManager.getManager().registerCloseSuspendingJob(collectionJob);
		
		collectionJob.schedule();
	}
	
}
