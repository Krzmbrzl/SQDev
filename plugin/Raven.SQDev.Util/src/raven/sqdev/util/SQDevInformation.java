package raven.sqdev.util;

import java.io.IOException;

import raven.sqdev.exceptions.SQDevFileIsInvalidException;
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.misc.SQDevPreferenceUtil;
import raven.sqdev.sqdevFile.ESQDevFileAttribute;
import raven.sqdev.sqdevFile.SQDevFile;

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
	 * Indicating whether this information is for a MP enironment
	 */
	protected boolean mp;
	
	/**
	 * Creates a new instance of this <code>SQDevInformation</code> with default
	 * values
	 */
	public SQDevInformation() {
		// default constructor
	}
	
	/**
	 * Creates a new instance of this <code>SQDevInformation</code> and applies
	 * the values stated int eh given <code>SQDevFile</code>
	 */
	public SQDevInformation(SQDevFile file) {
		try {
			// gather information from the given file
			if (file.contains(ESQDevFileAttribute.PROFILE)) {
				this.profile = file.parseAttribute(ESQDevFileAttribute.PROFILE)
						.getValue();
			}
			
			if (file.contains(ESQDevFileAttribute.TERRAIN)) {
				this.terrain = file.parseAttribute(ESQDevFileAttribute.TERRAIN)
						.getValue();
			}
			
			if (file.contains(ESQDevFileAttribute.AUTOEXPORT)) {
				setAutoExport(
						file.parseAttribute(ESQDevFileAttribute.AUTOEXPORT)
								.getValue());
			}
		} catch (IOException | SQDevFileIsInvalidException e) {
			// report
			SQDevInfobox info = new SQDevInfobox(
					"Couldn't get information from the SQDevFile...", e);
			info.open();
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Checks if the profile has been set
	 * 
	 * @return
	 */
	public boolean isProfileSet() {
		return profile != null;
	}
	
	/**
	 * Gets the value for the profile
	 */
	public String getProfile() {
		return (isProfileSet()) ? profile
				: SQDevPreferenceUtil.getDefaultProfile();
	}
	
	/**
	 * Sets the value for the profile
	 * 
	 * @param profile
	 *            The new profile value
	 */
	public void setProfile(String profile) {
		this.profile = profile.trim();
	}
	
	/**
	 * Checks if the value for the terrain has been set
	 * 
	 * @return
	 */
	public boolean isTerrainSet() {
		return terrain != null;
	}
	
	/**
	 * Gets the value of the terrain
	 */
	public String getTerrain() {
		return (isTerrainSet()) ? terrain
				: SQDevPreferenceUtil.getDefaultTerrain();
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
		return (isAutoExportSet()) ? autoExport
				: SQDevPreferenceUtil.getAutoExportDefaultEnabled();
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
	
	/**
	 * Checks whether this info is for an MP environment
	 */
	public boolean isMp() {
		return mp;
	}
	
	public void setMp(boolean mp) {
		this.mp = mp;
	}
	
}
