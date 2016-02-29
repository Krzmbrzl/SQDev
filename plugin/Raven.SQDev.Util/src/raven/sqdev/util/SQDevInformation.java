package raven.sqdev.util;

import raven.sqdev.preferences.util.SQDevPreferenceUtil;

/**
 * The class for transferring information between several SQDev classes.<br>
 * It contains various fields that can be set and does the handling with defualt
 * values for fields that are not set
 * 
 * @author Raven
 * 		
 */
public class SQDevInformation {
	
	/**
	 * The default String indocating that the requested value has not been set
	 * and does not have a default value
	 */
	public static String NOT_SET = "NotSet";
	
	/**
	 * A String holding the profile name
	 */
	protected String profile;
	
	/**
	 * A String holding the terrain
	 */
	protected String terrain;
	
	/**
	 * A Boolean holding the value for autoExport
	 */
	protected boolean autoExport;
	
	/**
	 * Indicates whether autoExport has been set manually
	 */
	protected boolean autoExportIsSet = false;
	
	/**
	 * A String containing the name
	 */
	protected String name;
	
	
	/**
	 * Checks if the profile has been set
	 * @return
	 */
	public boolean isProfileSet() {
		return profile != null;
	}
	
	/**
	 * Gets the value for the profile
	 */
	public String getProfile() {
		return (isProfileSet()) ? profile : SQDevPreferenceUtil.getDefaultProfile();
	}
	
	/**
	 * Sets the value for the profile
	 * @param profile The new profile value
	 */
	public void setProfile(String profile) {
		this.profile = profile.trim();
	}
	
	/**
	 * Checks if the value for the terrain has been set
	 * @return
	 */
	public boolean isTerrainSet() {
		return terrain != null;
	}
	
	/**
	 * Gets the value of the terrain
	 */
	public String getTerrain() {
		return (isTerrainSet()) ? terrain : SQDevPreferenceUtil.getDefaultTerrain();
	}
	
	/**
	 * Sets the value of the terrain
	 * 
	 * @param terrain
	 *            The new value of the terrain
	 */
	public void setTerrain(String terrain) {
		this.terrain = terrain.trim();
	}
	
	/**
	 * Checks if the autoExport value has been set
	 */
	public boolean isAutoExportSet() {
		return autoExportIsSet;
	}
	
	/**
	 * Gets the value of the autoExport field
	 */
	public boolean getAutoExport() {
		return (isAutoExportSet()) ? autoExport : SQDevPreferenceUtil.getAutoExportDefaultEnabled();
	}
	
	/**
	 * Sets the value of autoExport
	 * 
	 * @param autoExport
	 *            The new value of autoExport
	 */
	public void setAutoExport(boolean autoExport) {
		this.autoExport = autoExport;
		autoExportIsSet = true;
	}
	
	/**
	 * Sets the value of autoExport
	 * 
	 * @param autoExport
	 *            The new value of autoExport (Can be "true" or "false")
	 */
	public void setAutoExport(String autoExport) {
		setAutoExport(Boolean.parseBoolean(autoExport.trim()));
	}
	
	/**
	 * Checks if the name field has been set
	 */
	public boolean isNameSet() {
		return name != null;
	}
	
	/**
	 * Gets the stored name
	 */
	public String getName() {
		return (isNameSet()) ? name : NOT_SET;
	}
	
	/**
	 * Sets the stored name
	 * 
	 * @param name
	 *            The new name value
	 */
	public void setName(String name) {
		this.name = name.trim();
	}
	
}
