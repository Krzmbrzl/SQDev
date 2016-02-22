package raven.sqdev.util;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;

import raven.sqdev.preferences.util.SQDevPreferenceUtil;

public class ProjectUtil {
	
	/**
	 * Indicating that the export was successfull
	 */
	public static String SUCCESS = "success";
	
	/**
	 * Indicating that the export was canceled
	 */
	public static String CANCELED = "canceled";
	
	/**
	 * Indicating that the export has failed
	 */
	public static String FAILED = "failed";
	
	public static String export(IProject project, IPath destination,
			ArrayList<String> filesToIgnore, ArrayList<String> filesToPreserve) {
		if (!new File(destination.toOSString()).exists()) {
			// check how many folders have to be created
			IPath copy = new Path(destination.toOSString());
			
			// search for the first existing container
			for (int i = 0; i < copy.segmentCount(); i++) {
				copy = copy.removeLastSegments(1);
				
				if (new File(copy.toOSString()).exists()) {
					break;
				}
			}
			
			// calculate how many files have to be created
			int diff = destination.segmentCount() - copy.segmentCount();
			
			if (diff >= 2) {
				// normally there should be only the mission folder to be
				// created
				SQDevInfobox info = new SQDevInfobox(
						"The export process would require an unusual high amounts (" + diff
								+ ") of new folders being created.\n\nDo you want to proceed?",
						SWT.ICON_QUESTION | SWT.YES | SWT.NO);
						
				if (info.open() != SWT.YES) {
					// abort the export process
					return CANCELED;
				}
			}
		}
		
		File missionFolder = new File(destination.toOSString());
		
		if (!missionFolder.exists()) {
			// create the mission folder
			missionFolder.mkdirs();
		} else {
			if (!SQDevPreferenceUtil.autoClean()) {
				SQDevInfobox info = new SQDevInfobox(
						"The directory \"" + destination.toOSString()
								+ "\" has to be cleaned in order to export the project.\n"
								+ "Any files that are not part of the project in the eclipse workspace"
								+ " will be deleted.\n\nDo you want to proceed?",
						SWT.ICON_QUESTION | SWT.YES | SWT.NO);
						
				if (info.open() != SWT.YES) {
					return CANCELED;
				}
			}
			
			// clean the directory
			for (File currentFile : missionFolder.listFiles()) {
				// delete the respective files
				if (!FileSystemUtil.deleteFilesWithException(currentFile, filesToPreserve)) {
					// report that the cleaning couldn't be performed
					SQDevInfobox info = new SQDevInfobox(
							"Failed to delete file \"" + currentFile.getAbsolutePath()
									+ "\"\nMake sure the files are not opened somewhere and try again"
									+ "\n\nCanceled the export process.",
							SWT.ICON_INFORMATION);
							
					info.open();
					
					// abort export when cleaning can't be performed
					return CANCELED;
				}
			}
		}
		
		try {
			for (IResource currentResource : project.members()) {
				File currentFile = new File(currentResource.getRawLocationURI());
				
				if (filesToIgnore.contains(currentFile.getName())) {
					// skip if this resource is specified to be ignored
					continue;
				}
				
				if (!currentFile.exists()) {
					// skip non-existant files
					continue;
				}
				
				FileSystemUtil.copyFilesWithExceptions(currentFile, destination, filesToIgnore);
			}
		} catch (CoreException e) {
			e.printStackTrace();
			
			// inform the user about the failure
			String message = "Failed at exporting \"" + project.getName() + "\"\nReason: "
					+ ((e.getMessage() == null || e.getMessage().isEmpty()) ? "Unknown"
							: e.getMessage());
							
			SQDevInfobox info = new SQDevInfobox(message, SWT.ICON_ERROR);
			
			info.open();
			
			return FAILED;
		}
		
		// indicate that the export was successfull
		return SUCCESS;
	}
}
