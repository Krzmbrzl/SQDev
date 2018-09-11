package raven.sqdev.sqdevFile;

import java.util.ArrayList;

import raven.sqdev.util.SQDevInformation;
import raven.sqdev.util.SQDevPath;

/**
 * An enum representing all possible names for a .sqdev file
 * 
 * @author Raven
 * 
 */
public enum ESQDevFileType {
	/**
	 * The name of the sqdev-file that contains information about the linking to the
	 * ArmA folder
	 */
	LINK {
		@Override
		public String toString() {
			return "link";
		}

		@Override
		public String getInitialInput() {
			// clear the initial content before recreating it
			initialContent = ESQDevFileVersion.newest().getHeader() + "\n"
					+ ESQDevFileVersion.newest().makeInsertable(
							"This link file contains the information needed to perform the workspace-Arma-linking")
					+ "\n" + ESQDevFileVersion.newest().getFormatDescription(true);

			SQDevPath exportPath = new SQDevPath(
					info.isMp() ? SQDevPath.MPMISSIONS_FOLDER_PLACEHOLDER : SQDevPath.MISSIONS_FOLDER_PLACEHOLDER,
					info.getProfile());

			// add attributes
			addAttribute(ESQDevFileAttribute.PROFILE, info.getProfile(), ESQDevFileVersion.newest());
			addAttribute(ESQDevFileAttribute.EXPORTDIRECTORY, exportPath.toPlaceholderString(),
					ESQDevFileVersion.newest());
			addAttribute(ESQDevFileAttribute.AUTOEXPORT, String.valueOf(info.getAutoExport()),
					ESQDevFileVersion.newest());
			addAttribute(ESQDevFileAttribute.TERRAIN, info.getTerrain(), ESQDevFileVersion.newest());

			// add annotations
			addAnnotation(ESQDevFileAnnotation.IGNORE, filesToIgnore.toArray(new String[filesToIgnore.size()]),
					ESQDevFileVersion.newest());
			addAnnotation(ESQDevFileAnnotation.PRESERVE, filesToPreserve.toArray(new String[filesToPreserve.size()]),
					ESQDevFileVersion.newest());

			return initialContent.toString();
		}
	},
	/**
	 * The sqdev-file that contains project-specific information
	 */
	PROJECT {
		@Override
		public String toString() {
			return "project";
		}

		@Override
		public String getInitialInput() {
			// clear the initial content before recreating it
			initialContent = ESQDevFileVersion.newest().getHeader() + "\n"
					+ ESQDevFileVersion.newest()
							.makeInsertable("This sqdev-file contains the project specific information")
					+ "\n" + ESQDevFileVersion.newest().getFormatDescription(true);

			return initialContent.toString();
		}

	},
	/**
	 * A type that can be used as a "null-entry". It will be equal to all other
	 * types
	 */
	NULLTYPE {

		@Override
		public String getInitialInput() {
			return "";
		}

	};

	private ESQDevFileType() {
		initialContent = "";
		filesToIgnore = new ArrayList<String>();
		filesToPreserve = new ArrayList<String>();

		// ignore SQDev-files
		addFileToIgnore(ESQDevFileAnnotation.REGEX_PREFIX + ".*\\.sqdev");
		// ignore hidden files and folders
		addFileToIgnore(ESQDevFileAnnotation.REGEX_PREFIX + "\\..*");
	}

	protected SQDevInformation info;

	/**
	 * A String containing the initial content of this fileType
	 */
	protected CharSequence initialContent;

	/**
	 * A list of files/folders that should be ignored during project export
	 */
	protected ArrayList<String> filesToIgnore;

	/**
	 * A list of files/folders that should not get deleted during cleanup for
	 * exporting a project
	 */
	protected ArrayList<String> filesToPreserve;

	public abstract String getInitialInput();

	/**
	 * Will find the respective instance of this enum
	 * 
	 * @param type
	 *            The type of the enum that should be found. Has to be the return
	 *            value of one of the toString()-methods of this enum
	 * @return The respective instance of this enum or <code>null</code> if the type
	 *         couldn't be resolved
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
	 * @param version
	 *            The file format version to use
	 */
	protected void addAttribute(ESQDevFileAttribute attr, String value, ESQDevFileVersion version) {
		if (value == null || value.equals(SQDevInformation.NOT_SET)) {
			// the respective attribute is not set
			return;
		}

		attr.setValue(value);

		initialContent = version.addAttribute(attr, initialContent);
	}

	/**
	 * Adds an annotation to the initial content
	 * 
	 * @param annotation
	 *            The annotation
	 * @param value
	 *            The values of this annotation
	 * @param version
	 *            The file format version to use
	 */
	protected void addAnnotation(ESQDevFileAnnotation annotation, String[] values, ESQDevFileVersion version) {
		if (values == null) {
			// the respective annotation is not set
			return;
		}

		annotation.setValues(values);

		initialContent = version.addAnnotation(annotation, initialContent);
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
		return (isInformationSet()) ? info : new SQDevInformation();
	}

	/**
	 * Sets the information
	 * 
	 * @param info
	 *            The new information
	 */
	public void setInformation(SQDevInformation info) {
		this.info = info;
	}

	/**
	 * Checks if the given entry is equal to this one. Note that if either this or
	 * the given entry is the {@link #NULLTYPE} then this method will return true.
	 * 
	 * @param o
	 *            The object to check
	 * @return Whether the given object is equal to this one
	 */
	public boolean isEquals(Object o) {
		if (!(o instanceof ESQDevFileType)) {
			return false;
		}

		ESQDevFileType other = (ESQDevFileType) o;

		if (this == ESQDevFileType.NULLTYPE || other == ESQDevFileType.NULLTYPE) {
			return true;
		} else {
			return super.equals(other);
		}
	}
}
