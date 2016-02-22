package raven.sqdev.util;

import java.io.FileNotFoundException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import raven.sqdev.exceptions.IllegalAccessStateException;
import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.exceptions.SQDevFileIsInvalidException;
import raven.sqdev.sqdevFile.ESQDevFileAttribute;
import raven.sqdev.sqdevFile.ESQDevFileType;
import raven.sqdev.sqdevFile.SQDevFile;

/**
 * A class containing general util methods
 * 
 * @author Raven
 * 		
 */
public class Util {
	
	/**
	 * Gets the export location for the given project assuming that the given
	 * project is a valid SQDev project
	 * 
	 * @param project
	 *            The project the export path should be returned for
	 * @return
	 */
	public static Path getExportPathFor(IProject project) {
		// it has to be an SQDevProject
		Assert.isTrue(isSQDevProject(project));
		
		IFile file = project
				.getFile(ESQDevFileType.LINK.toString() + EFileType.SQDEV.getExtension());
		
		IPath path = file.getRawLocation().makeAbsolute();
				
		SQDevFile linkFile = null;
			try {
				// create the respective SQDev file
				linkFile = new SQDevFile(path);
			} catch (FileNotFoundException | IllegalAccessStateException e) {
				throw new SQDevCoreException(e);
			}
		
		Path exportPath;
		try {
			// create the folder name including the map name
			String projectFolderName = project.getName() + "."
					+ linkFile.parseAttribute(ESQDevFileAttribute.MAP).getValue();
					
			// get the mission directory
			String expPath = linkFile.parseAttribute(ESQDevFileAttribute.EXPORTDIRECTORY).getValue();
			
			// create the path according to the gathered path and name
			exportPath = new Path(expPath + "/" + projectFolderName);
		} catch (SQDevFileIsInvalidException e) {
			throw new SQDevCoreException(e);
		}
		
		return exportPath;
	}
	
	/**
	 * Finds out whether the given project is a valid SQDev project.<br>
	 * A project is considered a SQDev project if it contains a link.sqdev
	 * 
	 * @param project
	 *            The project to be checked
	 * @return
	 */
	public static boolean isSQDevProject(IProject project) {
		IFile testFile = project
				.getFile(ESQDevFileType.LINK.toString() + EFileType.SQDEV.getExtension());
				
		return testFile.exists();
	}
	
}
