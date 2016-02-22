package raven.sqdev.sqdevFile;

import java.util.ArrayList;

import raven.sqdev.preferences.util.SQDevPreferenceUtil;
import raven.sqdev.util.EFileType;

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
			// TODO: add necessary attributes with proper values
			
			// add attributes
			addAttribute(ESQDevFileAttribute.EXPORTDIRECTORY,
					SQDevPreferenceUtil.getMissionsDirectory().getAbsolutePath());
			
			addAttribute(ESQDevFileAttribute.AUTOEXPORT, autoExport);
					
			addAttribute(ESQDevFileAttribute.MAP, map);
			
			// add annotations
			initialContent += "\n\n";
			addAnnotation(ESQDevFileAnnotation.IGNORE,
					filesToIgnore.toArray(new String[filesToIgnore.size()]));
					
			initialContent += "\n";
			
			addAnnotation(ESQDevFileAnnotation.PRESERVE,
					filesToPreserve.toArray(new String[filesToPreserve.size()]));
					
			return (initialContent.endsWith("\n")) ? initialContent : initialContent + "\n";
		}
	};
	
	private ESQDevFileType() {
		initialContent = "";
		map = "Altis";
		filesToIgnore = new ArrayList<String>();
		filesToPreserve = new ArrayList<String>();
		autoExport = ESQDevFileAttribute.AUTOEXPORT.getDefault();
		
		addFileToIgnore(this.toString() + EFileType.SQDEV.getExtension());
		addFileToIgnore(".project");
	}
	
	/**
	 * A String containing the initial content od this fileType
	 */
	protected String initialContent;
	
	/**
	 * A String containing the information about the map on which the mission
	 * takes place
	 */
	protected String map;
	
	/**
	 * A String indicating if autoExport should be enabled for this project
	 */
	protected String autoExport;
	
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
	
	/**
	 * Sets the map on which the mission should take place
	 * 
	 * @param map
	 *            The name of the map
	 */
	public void setMap(String map) {
		this.map = map;
	}
	
	/**
	 * Sets whether autoExport is enabled
	 * @param autoExport may be "true" or "false" (case-sensitive!)
	 */
	public void setAutoExport(String autoExport) {
		this.autoExport = autoExport;
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
			initialContent += ((initialContent.endsWith("\n")) ? "" : "\n") + annotation + " \""
					+ currentValue + "\"\n";
		}
	}
}
