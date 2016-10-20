package raven.sqdev.startup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PerspectiveAdapter;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPage;
import org.eclipse.ui.internal.registry.IActionSetDescriptor;
import org.osgi.framework.Version;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.interfaces.IVersionListener;
import raven.sqdev.misc.VersionChangeEvent;
import raven.sqdev.pluginManagement.ResourceManager;
import raven.sqdev.pluginManagement.VersionManager;
import raven.sqdev.util.FileUtil;
import raven.sqdev.util.SQDevInfobox;
import raven.sqdev.util.SQDevPreferenceUtil;

/**
 * The SQDev class that gets loaded when the workbench is initialized
 * 
 * @author Raven
 * 
 */
@SuppressWarnings("restriction")
public class SQDevStarter implements IStartup, IVersionListener {
	
	private boolean didCompampability;
	private boolean isDev;
	
	@Override
	public void earlyStartup() {
		configurePerspectiveChangeListener();
		
		// check the versions
		VersionManager.getManager().addVersionListener(this);
		VersionManager.getManager().checkVersions();
		
		if (didCompampability) {
			didCompampability = false;
			
			// open dialog to inform the user about changes
			SQDevInfobox info = new SQDevInfobox(
					"Performed some compability changes in order to match new version",
					SWT.ICON_INFORMATION);
			
			info.open(false);
		}
		
		if (isDev) {
			isDev = false;
			
			SQDevInfobox info = new SQDevInfobox(
					"You are running a development version of this plugin!\n"
							+ "Be aware that there might be some broken functions and/or other bugs "
							+ "in this software.\n\nThanks for supporting this plugin!",
					SWT.ICON_INFORMATION);
			
			info.open(false);
		}
	}
	
	@Override
	public void versionChanged(VersionChangeEvent event) {
		if (event.getNewVersion().getQualifier().startsWith("dev")) {
			isDev = true;
		}
		
		// perform compability operation and/or notifications
		switch (event.getPlugin()) {
			case EDITORS:
				break;
			case MISC:
				miscVersionChanged(event);
				break;
			case PREFERENCES:
				preferenceVersionChange(event);
				break;
			case SQFEDITOR:
				break;
			case UTIL:
				break;
			case WIZARDS:
				break;
			default:
				break;
		}
	}
	
	private void preferenceVersionChange(VersionChangeEvent event) {
		if (event.isUpdate()) {
			// inform the user about possible additions in the preferences
			SQDevInfobox info = new SQDevInfobox(
					"The preferences have been updated.\n\nMake sure you check them "
							+ "out in order to make the plugin work properly",
					SWT.ICON_INFORMATION);
			
			info.open(false);
		}
		
		if (event.getOldVersion().compareTo(new Version("0.4.1")) <= 0
				|| event.getNewVersion().getQualifier().equals("dev2")) {
			// changed parse delay from seconds to milliseconds -> use default
			// value as current value is most likely invalid
			SQDevPreferenceUtil.getPreferenceStore()
					.setToDefault(SQDevPreferenceConstants.SQDEV_EDITOR_PARSE_DELAY);
			
			SQDevPreferenceUtil.getPreferenceStore()
					.setToDefault(SQDevPreferenceConstants.SQDEV_INFO_ARMA_DOCUMENTS_DIRECTORY);
			
			didCompampability = true;
		}
	}
	
	private void miscVersionChanged(VersionChangeEvent event) {
		if (event.getOldVersion().compareTo(new Version(0, 3, 0)) < 0) {
			// update the keyword list on the hard drive as there is the new
			// syntax attribute
			ResourceManager manager = ResourceManager.getManager();
			
			try {
				manager.updateResource(ResourceManager.KEYWORDS_RESOURCE, FileUtil.readAll(
						manager.getInternalResourceStream(ResourceManager.INTERNAL_KEYWORDS)));
			} catch (IOException | SQDevException e) {
				throw new SQDevCoreException("Failed at updating local keywords", e);
			}
			
			didCompampability = true;
		}
	}
	
	/**
	 * Sets up a perspective change listener that will handle the hiding of
	 * certain toolbar items in the SQDev-perspective
	 */
	private void configurePerspectiveChangeListener() {
		Display.getDefault().asyncExec(new Runnable() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Runnable#run()
			 */
			public void run() {
				final IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow();
				if (workbenchWindow != null) {
					workbenchWindow.addPerspectiveListener(new PerspectiveAdapter() {
						@Override
						public void perspectiveActivated(IWorkbenchPage page,
								IPerspectiveDescriptor perspectiveDescriptor) {
							super.perspectiveActivated(page, perspectiveDescriptor);
							
							//TODO: relocate in init of perspective
							
							if (perspectiveDescriptor.getId()
									.equals("raven.sqdev.ui.perspectives.sqdevperspective")) {
								if (workbenchWindow.getActivePage() instanceof WorkbenchPage) {
									WorkbenchPage workbenchPage = (WorkbenchPage) workbenchWindow
											.getActivePage();
									
									List<String> preserve = new ArrayList<String>();
									preserve.add("org.eclipse.search.searchActionSet");
									preserve.add("org.eclipse.ui.actionSet.openFiles");
									
									ArrayList<IActionSetDescriptor> toRemove = new ArrayList<IActionSetDescriptor>();
									for (IActionSetDescriptor currentDescriptor : workbenchPage
											.getActionSets()) {
										if (!currentDescriptor.getId().startsWith("raven")
												&& !preserve.contains(currentDescriptor.getId())) {
											// Add the action set descriptor to
											// the list of the action sets to
											// remove
											toRemove.add(currentDescriptor);
										}
									}
									
									for (IActionSetDescriptor current : toRemove) {
										System.out.println("Hiding \"" + current.getId() + "\"");
										workbenchPage.hideActionSet(current.getId());
									}
									
									workbenchPage.updateActionBars();
								}
							}
						}
					});
				}
			}
		});
	}
	
}
