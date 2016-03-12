package raven.sqdev.sqdevFile;

import java.util.ArrayList;

import raven.sqdev.util.EFileType;
import raven.sqdev.util.SQDevInformation;
import raven.sqdev.util.Util;

/**
 * An enum representing all possible names for a .sqdev file
 * 
 * @author Raven
 * 		
 */
public enum ESQDevFileType {
	/**
	 * The name of the sqdev-file that contains information about the linking to
	 * the ArmA folder
	 */
	LINK {
		@Override
		public String toString() {
			return "link";
		}
		
		@Override
		public String getInitialInput() {
			// clear the initial content before recreating it
			initialContent = "";
			
			// add attributes
			addAttribute(ESQDevFileAttribute.PROFILE, info.getProfile());
			
			addAttribute(ESQDevFileAttribute.EXPORTDIRECTORY,
					Util.getMissionsDirectory(info.getProfile()).toOSString());
					
			addAttribute(ESQDevFileAttribute.AUTOEXPORT, String.valueOf(info.getAutoExport()));
			
			addAttribute(ESQDevFileAttribute.TERRAIN, info.getTerrain());
			
			// add annotations
			initialContent += "\n\n";
			addAnnotation(ESQDevFileAnnotation.IGNORE,
					filesToIgnore.toArray(new String[filesToIgnore.size()]));
					
			initialContent += "\n";
			
			addAnnotation(ESQDevFileAnnotation.PRESERVE,
					filesToPreserve.toArray(new String[filesToPreserve.size()]));
					
			return initialContent.trim() + "\n";
		}
	};
	
	private ESQDevFileType() {
		initialContent = "";
		filesToIgnore = new ArrayList<String>();
		filesToPreserve = new ArrayList<String>();
		
		addFileToIgnore(this.toString() + EFileType.SQDEV.getExtension());
		addFileToIgnore(".project");
	}
	
	protected SQDevInformation info;
	
	/**
	 * A String containing the initial content od this fileType
	 */
	protected String initialContent;
	
	/**
	 * A list of files/folders that should be ignored during project export
	 */
	protected ArrayList<String> filesToIgnore;
	
	/**
	 * A list of files/foldersy that should not get deleted during cleanup for
	 * exporting a project
	 */
	protected ArrayList<String> filesToPreserve;
	
	public abstract String getInitialInput();
	
	/**
	 * Will find the respective instance of this enum
	 * 
	 * @param type
	 *            The type of the enum that should be found. Has to be the
	 *            return value of one of the toString()-methods of this enum
	 * @return The respective instance of this enum or <code>null</code> if the
	 *         type couldn't be resolved
	 */
	public static ESQDevFileType resolve(String type) {
		for (ESQDevFileType current : ESQDevFileType.values()) {
			if (current.toString().equals(type)) {
				return current;
			}
		}
		
		return null;
	}
	
	/**
	 * Checks if the given type is contained in this enum
	 * 
	 * @param type
	 *            The type name to check
	 * @return
	 */
	public static boolean contains(String type) {
		return (resolve(type) == null) ? false : true;
	}
	
	public void addFileToIgnore(String fileName) {
		filesToIgnore.add(fileName);
	}
	
	/**
	 * Adds an attribute to the initial content
	 * 
	 * @param attr
	 *            The attribute
	 * @param value
	 *            The value of this attribute
	 */
	protected void addAttribute(ESQDevFileAttribute attr, String value) {
		initialContent += ((initialContent.endsWith("\n")) ? "" : "\n") + attr + " = " + value
				+ ";\n";
	}
	
	/**
	 * Adds an annotation to the initial content
	 * 
	 * @param annotation
	 *            The annotation
	 * @param value
	 *            The values of this annotation
	 */
	protected void addAnnotation(ESQDevFileAnnotation annotation, String[] values) {
		for (String currentValue : values) {
			initialContent += ((initialContent.endsWith("\n")) ? "" : "\n") + "@" + annotation
					+ " \"" + currentValue + "\"\n";
		}
	}
	
	/**
	 * Checks if the information has been et
	 */
	public boolean isInformationSet() {
		return info != null;
	}

	/**
	 * Gets the set information
	 */
	public SQDevInformation getInformation() {
		return (isInformationSet())? info : new SQDevInformation();
	}
	
	/**
	 * Sets the information
	 * @param info The new information
	 */
	public void setInformation(SQDevInformation info) {
		this.info = info;
	}
}
