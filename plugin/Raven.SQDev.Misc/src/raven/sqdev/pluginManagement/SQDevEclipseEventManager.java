package raven.sqdev.pluginManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.PlatformUI;

/**
 * A manager for general eclipse events where certain behaviour patterns can be
 * registered for a certain event
 * 
 * @author Raven
 * 		
 */
public class SQDevEclipseEventManager {
	/**
	 * The manager instance
	 */
	private static SQDevEclipseEventManager manager;
	
	private List<Job> jobs;
	
	private SQDevEclipseEventManager() {
		jobs = new ArrayList<Job>(0);
		
		PlatformUI.getWorkbench().addWorkbenchListener(new IWorkbenchListener() {
			
			@Override
			public boolean preShutdown(IWorkbench workbench, boolean forced) {
				AtomicBoolean shutDown = new AtomicBoolean(true);
				
				for (Job currentJob : jobs) {
					if (currentJob != null && currentJob.getState() == Job.RUNNING) {
						// inform about currently running jobs and
						// offers to terminate them
						PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
							
							@Override
							public void run() {
								MessageBox box = new MessageBox(PlatformUI.getWorkbench()
										.getActiveWorkbenchWindow().getShell(),
										SWT.ICON_WARNING | SWT.YES | SWT.NO);
										
								box.setText("Running Job");
								box.setMessage("The Job \"" + currentJob.getName()
										+ "\" is still running!\n\n"
										+ " Do you want to cancel this job now?");
										
								int result = box.open();
								
								switch (result) {
									case SWT.YES:
										boolean running = false;
										
										for (Job current : jobs) {
											running = !current.cancel();
										}
										
										// if one job is running then
										// kill all after 3 secs
										
										if (running) {
											try {
												currentJob.getThread().join(3000);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}
											
											// kill Thread after max. 3
											// secs
											for (Job current : jobs) {
												current.getThread().interrupt();
											}
										}
										
										break;
										
									default:
										// don't close
										shutDown.set(false);
								}
							}
						});
						
						break;
					}
				}
				
				return shutDown.get();
			}
			
			@Override
			public void postShutdown(IWorkbench workbench) {
			}
		});
	}
	
	/**
	 * Gets the SQDevManager for eclipse events
	 * 
	 * @return The <SQDevEclipseEventManager</code>
	 */
	public static SQDevEclipseEventManager getManager() {
		if (manager == null) {
			manager = new SQDevEclipseEventManager();
		}
		
		return manager;
	}
	
	/**
	 * Adds a job to the list of jobs being checked when eclipse is closed.<br>
	 * If any running jobs are registered while closing eclipse a popup will
	 * appear asking if these jobs should be terminated. If so a cancel request
	 * will be send to them and then the jobs have 3secs to terminate themselve.
	 * After this time their Thread will be interrupted
	 * 
	 * @param job
	 *            The job to register
	 */
	public void registerCloseSuspendingJob(Job job) {
		// add the job
		jobs.add(job);
		
		// check for null jobs and remove them
		while (jobs.contains(null)) {
			jobs.remove(null);
		}
	}
}
