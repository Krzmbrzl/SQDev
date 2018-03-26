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
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.misc.SQDevPreferenceUtil;
import raven.sqdev.pluginManagement.ResourceManager;
import raven.sqdev.pluginManagement.SQDevEclipseEventManager;
import raven.sqdev.preferences.preferenceEditors.BooleanSQDevPreferenceEditor;
import raven.sqdev.preferences.preferenceEditors.ValueSQDevPreferenceEditor;
import raven.sqdev.util.Util;

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
		updateButton.setToolTipText("Updates the SQF commands according to the BIKI. This may take a while");
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

		addPreferenceEditor(new ValueSQDevPreferenceEditor(SQDevPreferenceConstants.SQDEV_COLLECTION_API_ADRESS,
				"&BIKI API:", "The adress to the BIKI API. If there is no urgent need do not change this value!",
				keywordGroup));

		addPreferenceEditor(new ValueSQDevPreferenceEditor(SQDevPreferenceConstants.SQDEV_COLLECTION_API_MAINPAGE,
				"&Main Page Name:", "The name of the main page that lists the SQF commands."
						+ " If there is no urgent need do not change this value!",
				keywordGroup));


		// all preferences about user prompts
		Group promptGroup = createGroup("User prompt");

		createDescription(promptGroup, "Preferences about when to prompt the user to do something");

		addPreferenceEditor(new BooleanSQDevPreferenceEditor(SQDevPreferenceConstants.SQDEV_PROMPT_ALWAYS_SAVE_ON_EXIT,
				"&Always save on exit:",
				"Whether unsaved preferences should get saved automatically when clicking \"OK\" without a popup asking for it",
				promptGroup));

		addPreferenceEditor(new BooleanSQDevPreferenceEditor(SQDevPreferenceConstants.SQDEV_PROMPT_ASK_FOR_DELETION,
				"&Validate deletions:", "Whether the plugin should prompt the user to validate a deletion he caused",
				promptGroup));
	}

	/**
	 * Schedules the keyword update job
	 */
	private void updateKeywords() {
		commandList = new KeywordList();

		updateKeywords(null);
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
	 * @param repeat
	 *            The URL that should be repeated. <code>Null</code> if none
	 */
	private void updateKeywords(URL repeat) {
		collectionJob = new Job("Updating keywords") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Updating keywords", IProgressMonitor.UNKNOWN);

				try {
					// gather keywords from the wiki
					SQFCommandCollector collector = new SQFCommandCollector(SQDevPreferenceUtil.getAPIAdress(),
							SQDevPreferenceUtil.getAPIMainPage());

					KeywordList list = collector.collect(monitor, repeat);

					list.addKeywords(commandList.getKeywords().values()); // add previously gathered commands

					if (monitor.isCanceled()) {
						// ask whether to save the list
						SQDevInfobox info = new SQDevInfobox("The keyword update has been interrupted.\n"
								+ "Do you wish to store the current keywords?"
								+ " (This will override the current keword list and may"
								+ " leed to an incomplete list)", SWT.ICON_QUESTION | SWT.YES | SWT.NO);

						if (info.open() != SWT.YES) {
							// don't save
							return Status.OK_STATUS;
						}
					}

					// save the keywords
					monitor.done();
					monitor.beginTask("Storing keywords...", IProgressMonitor.UNKNOWN);

					ResourceManager manager = ResourceManager.getManager();
					manager.updateResource(ResourceManager.KEYWORDS_RESOURCE, list.getSaveableFormat());

					// tell the user about restart
					SQDevInfobox info = new SQDevInfobox("In order for the new keywords to take effect"
							+ " all respective editors will be restarted", SWT.ICON_INFORMATION);

					if (info.open() != SWT.OK) {
						return Status.OK_STATUS;
					}

					// restart all editors in order to overtake the changes
					Util.restartAllEditors();
				} catch (IOException | SQDevException e) {
					if (e instanceof SQDevCollectionException
							&& ((SQDevCollectionException) e).getFailedKeywordURL() != null) {
						SQDevCollectionException ex = (SQDevCollectionException) e;

						commandList.addKeywords(ex.getPreviouslyProcessedKeywords().getKeywords().values());

						SQDevInfobox info = new SQDevInfobox(
								"Failed at updating keywords at \"" + ((ex.getFailedKeywordURL() == null) ? "Internal"
										: ex.getFailedKeywordURL().toString()) + "\"",
								ex, "Do you want to retry this command?");
						info.addStyle(SWT.CANCEL);

						int result = info.open();

						switch (result) {
						case SWT.YES:
							updateKeywords(ex.getFailedKeywordURL());
							break;

						case SWT.NO:
							updateKeywords(null);
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
