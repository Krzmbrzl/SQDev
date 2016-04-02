package raven.sqdev.preferences.pages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.MalformedURLException;
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
import raven.sqdev.pluginManagement.SQDevEclipseEventManager;
import raven.sqdev.preferences.preferenceEditors.ValueSQDevPreferenceEditor;
import raven.sqdev.util.ResourceManager;
import raven.sqdev.util.SQDevInfobox;
import raven.sqdev.util.SQDevPreferenceUtil;

public class SQDevMiscPreferencePage extends SQDevPreferencePage {
	/**
	 * The job used for updating the keywords
	 */
	private static Job collectionJob;
	
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
		Button btn = new Button(createContainer(), SWT.PUSH);
		btn.setText("Update keywords");
		btn.setToolTipText("Updates the SQF keywords according to the BIKI. This may take a while");
		btn.setEnabled(collectionJob == null || collectionJob.getResult() != null);
		
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				btn.setEnabled(false);
				
				ResourceManager manager = new ResourceManager();
				try {
					manager.updateResource("test.txt", "test here!");
					//TODO test
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				collectionJob = new Job("Updating keywords") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						monitor.beginTask("Updating keywords", IProgressMonitor.UNKNOWN);
						
						try {
							KeywordList list = new SQFCommandCollector(
									new URL("https://community.bistudio.com/wiki/Category:Scripting_Commands_Arma_3"),
									SQDevPreferenceUtil.getFirstCommand(),
									SQDevPreferenceUtil.getLastCommand()).collect(monitor);
									
							// TODO: store keywords + restart util plugin
							Writer writer = new FileWriter(new File(
									"C:/Users/Robert Adam/Desktop/tester/KeywordList.txt"));
							writer.write(list.getSaveableFormat());
							writer.close();
							
						} catch (/* MalformedURLException | */ SQDevCollectionException
								| IOException e) {
							SQDevInfobox info = new SQDevInfobox("Failed at updating keywords", e);
							info.open();
							
							e.printStackTrace();
							return Status.CANCEL_STATUS;
						} finally {
							if (!PlatformUI.getWorkbench().getDisplay().isDisposed()) {
								PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
									
									@Override
									public void run() {
										if (!btn.isDisposed()) {
											btn.setEnabled(true);
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
		});
	}
	
}
