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
import raven.sqdev.exceptions.SQDevCollectionException;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.infoCollection.SQFCommandCollector;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.pluginManagement.ResourceManager;
import raven.sqdev.pluginManagement.SQDevEclipseEventManager;
import raven.sqdev.preferences.preferenceEditors.BooleanSQDevPreferenceEditor;
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
	/**
	 * The list storing previously processed commands
	 */
	private KeywordList commandList;
	
	public SQDevMiscPreferencePage() {
		super();
	}
	
	@Override
	public void init(IWorkbench workbench) {
		setDescription("Miscellaneous preferences about the plugin");
		
		// SQF keyword collection
		updateButton = new Button(createContainer(), SWT.PUSH);
		updateButton.setToolTipText(
				"Updates the SQF commands according to the BIKI. This may take a while");
		updateButton.setEnabled(collectionJob == null || collectionJob.getResult() != null);
		
		// set text according to status
		if (!updateButton.isEnabled()) {
			updateButton.setText("Updating commands...");
		} else {
			updateButton.setText("Update commands");
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
		
		// preferences about the command collection
		Group keywordGroup = createGroup("Command collection/updating");
		
		createDescription(keywordGroup, "Preferences about the command update process");
		
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
		
		
		// all preferences about user prompts
		Group promptGroup = createGroup("User prompt");
		
		createDescription(promptGroup, "Preferences about when to prompt the user to do something");
		
		addPreferenceEditor(new BooleanSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_PROMPT_ALWAYS_SAVE_ON_EXIT, "&Always save on exit:",
				"Whether unsaved preferences should get saved automatically when clicking \"OK\" without a popup asking for it",
				promptGroup));
		
		addPreferenceEditor(new BooleanSQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_PROMPT_ASK_FOR_DELETION, "&Validate deletions:",
				"Whether the plugin should prompt the user to validate a deletion he caused",
				promptGroup));
	}
	
	/**
	 * Schedules the keyword update job
	 */
	private void updateKeywords() {
		commandList = new KeywordList();
		
		updateKeywords(SQDevPreferenceUtil.getFirstCommand(), SQDevPreferenceUtil.getLastCommand(),
				false);
	}
	
	/**
	 * Schedules the keyword update Job
	 * 
	 * @param firstCommand
	 *            The command to start with
	 * @param lastCommand
	 *            The command to stop at
	 * @param previous
	 *            A list of already gathered commands
	 * @param skipFirst
	 *            Indicating whether the first command should be skipped
	 */
	private void updateKeywords(String firstCommand, String lastCommand, boolean skipFirst) {
		collectionJob = new Job("Updating keywords") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Updating keywords", IProgressMonitor.UNKNOWN);
				
				try {
					// gather keywords from the wiki
					SQFCommandCollector collector = new SQFCommandCollector(
							new URL("https://community.bistudio.com/wiki/Category:"
									+ "Scripting_Commands_Arma_3"),
							firstCommand, lastCommand);
					
					if (skipFirst) {
						collector.skipNext();
					}
					
					KeywordList list = collector.collect(monitor);
					
					list.addKeywords(commandList.getKeywords()); // add
																	// previously
																	// gathered
																	// commands
					
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
					if (e instanceof SQDevCollectionException
							&& ((SQDevCollectionException) e).getFailedKeyword() != null) {
						SQDevCollectionException ex = (SQDevCollectionException) e;
						
						commandList.addKeywords(ex.getPreviouslyProcessedKeywords().getKeywords());
						
						SQDevInfobox info = new SQDevInfobox(
								"Failed at updating keywords at \""
										+ ex.getFailedKeyword().getKeyword() + "\"",
								ex, "Do you want to retry this command?");
						info.addStyle(SWT.CANCEL);
						
						int result = info.open();
						// TODO: store prev-Keywords in case of multiple
						// interruptions
						switch (result) {
							case SWT.YES:
								updateKeywords(ex.getFailedKeyword().getKeyword(),
										SQDevPreferenceUtil.getLastCommand(), false);
								break;
							
							case SWT.NO:
								updateKeywords(ex.getFailedKeyword().getKeyword(),
										SQDevPreferenceUtil.getLastCommand(), true);
								break;
						}
					} else {
						SQDevInfobox info = new SQDevInfobox("Failed at updating keywords!", e);
						info.open();
					}
					
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
