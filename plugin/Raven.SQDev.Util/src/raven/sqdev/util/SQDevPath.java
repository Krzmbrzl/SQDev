package raven.sqdev.util;

import java.util.regex.Pattern;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import raven.sqdev.misc.SQDevPreferenceUtil;

/**
 * A custom path representation that is capable of processing placeholders as
 * parts of file paths
 * 
 * @author Raven
 *
 */
public class SQDevPath {

	/**
	 * The delimiter that is used to encapsulate placeholders in paths
	 */
	public static final String PLACEHOLDER_ENCAPSULATOR = "$";

	/**
	 * The placeholder used for the profile name.
	 */
	public static final String PROFILE_PLACEHOLDER_PLAIN = "PROFILE";
	/**
	 * The placeholder used for the profile name. It is already encapsulated by
	 * {@link #PLACEHOLDER_ENCAPSULATOR}
	 */
	public static final String PROFILE_PLACEHOLDER = PLACEHOLDER_ENCAPSULATOR + PROFILE_PLACEHOLDER_PLAIN
			+ PLACEHOLDER_ENCAPSULATOR;
	/**
	 * The placeholder used for the user's home folder.
	 */
	public static final String HOME_FOLDER_PLACEHOLDER_PLAIN = "HOME";
	/**
	 * The placeholder used for the user's home folder. It is already encapsulated
	 * by {@link #PLACEHOLDER_ENCAPSULATOR}
	 */
	public static final String HOME_FOLDER_PLACEHOLDER = PLACEHOLDER_ENCAPSULATOR + HOME_FOLDER_PLACEHOLDER_PLAIN
			+ PLACEHOLDER_ENCAPSULATOR;
	/**
	 * The placeholder used for the Arma missions folder.
	 */
	public static final String MISSIONS_FOLDER_PLACEHOLDER_PLAIN = "MISSIONS";
	/**
	 * The placeholder used for the Arma missions folder. It is already encapsulated
	 * by {@link #PLACEHOLDER_ENCAPSULATOR}
	 */
	public static final String MISSIONS_FOLDER_PLACEHOLDER = PLACEHOLDER_ENCAPSULATOR
			+ MISSIONS_FOLDER_PLACEHOLDER_PLAIN + PLACEHOLDER_ENCAPSULATOR;
	/**
	 * The placeholder used for the Arma mpMissions folder.
	 */
	public static final String MPMISSIONS_FOLDER_PLACEHOLDER_PLAIN = "MP_MISSIONS";
	/**
	 * The placeholder used for the Arma mpMissions folder. It is already
	 * encapsulated by {@link #PLACEHOLDER_ENCAPSULATOR}
	 */
	public static final String MPMISSIONS_FOLDER_PLACEHOLDER = PLACEHOLDER_ENCAPSULATOR
			+ MPMISSIONS_FOLDER_PLACEHOLDER_PLAIN + PLACEHOLDER_ENCAPSULATOR;
	/**
	 * The placeholder used for the Arma documents directory.
	 */
	public static final String ARMA_DOCUMENTS_DIRECTORY_PLACEHOLDER_PLAIN = "ARMA_DOCUMENTS";
	/**
	 * The placeholder used for the Arma documents directory. It is already
	 * encapsulated by {@link #PLACEHOLDER_ENCAPSULATOR}
	 */
	public static final String ARMA_DOCUMENTS_DIRECTORY_PLACEHOLDER = PLACEHOLDER_ENCAPSULATOR
			+ ARMA_DOCUMENTS_DIRECTORY_PLACEHOLDER_PLAIN + PLACEHOLDER_ENCAPSULATOR;
	/**
	 * The placeholder used for the Arma programs directory.
	 */
	public static final String ARMA_PROGRAM_DIRECTORY_PLACEHOLDER_PLAIN = "ARMA_PROGRAM";
	/**
	 * The placeholder used for the Arma programs directory. It is already
	 * encapsulated by {@link #PLACEHOLDER_ENCAPSULATOR}
	 */
	public static final String ARMA_PROGRAM_DIRECTORY_PLACEHOLDER = PLACEHOLDER_ENCAPSULATOR
			+ ARMA_PROGRAM_DIRECTORY_PLACEHOLDER_PLAIN + PLACEHOLDER_ENCAPSULATOR;


	/**
	 * The raw path with placeholders
	 */
	protected String rawPath;

	/**
	 * The actual path represented by this object
	 */
	protected IPath filePath;


	public SQDevPath(String path, String profileName) {
		profileName = profileName.trim();

		if (profileName.isEmpty()) {
			throw new IllegalArgumentException("Profile name must not be empty!");
		}

		initialize(path, profileName);
	}

	protected void initialize(String inputPath, String profileName) {
		// use OS-independent string representation
		inputPath = new Path(inputPath).toString();

		final IPath documentsDir = new Path(SQDevPreferenceUtil.getArmaDocumentsDirectory());
		final IPath missionsDir = Util.getMissionsDirectory(profileName);
		final IPath mpMissionsDir = Util.getMpMissionsDirectory(profileName);
		final IPath programDir = new Path(SQDevPreferenceUtil.getArmaProgramDirectory());
		final IPath homeDir = new Path(System.getProperty("user.home"));

		// replace case-insensitive on Windows
		String prefix = (System.getProperty("os.name").toLowerCase().contains("windows") ? "(?i)" : "");

		// abstract path by using placeholders
		inputPath = inputPath.replaceAll(prefix + Pattern.quote(mpMissionsDir.toString()),
				MPMISSIONS_FOLDER_PLACEHOLDER.replace("$", "\\$"));
		inputPath = inputPath.replaceAll(prefix + Pattern.quote(missionsDir.toString()),
				MISSIONS_FOLDER_PLACEHOLDER.replace("$", "\\$"));
		inputPath = inputPath.replaceAll(prefix + Pattern.quote(documentsDir.toString()),
				ARMA_DOCUMENTS_DIRECTORY_PLACEHOLDER.replace("$", "\\$"));
		inputPath = inputPath.replaceAll(prefix + Pattern.quote(programDir.toString()),
				ARMA_PROGRAM_DIRECTORY_PLACEHOLDER.replace("$", "\\$"));
		inputPath = inputPath.replaceAll(prefix + Pattern.quote(homeDir.toString()),
				HOME_FOLDER_PLACEHOLDER.replace("$", "\\$"));

		this.rawPath = inputPath;

		// replace all placeholders with their actual value
		inputPath = inputPath.replaceAll(prefix + Pattern.quote(MPMISSIONS_FOLDER_PLACEHOLDER),
				mpMissionsDir.toString().replace("$", "\\$"));
		inputPath = inputPath.replaceAll(prefix + Pattern.quote(MISSIONS_FOLDER_PLACEHOLDER),
				missionsDir.toString().replace("$", "\\$"));
		inputPath = inputPath.replaceAll(prefix + Pattern.quote(ARMA_DOCUMENTS_DIRECTORY_PLACEHOLDER),
				documentsDir.toString().replace("$", "\\$"));
		inputPath = inputPath.replaceAll(prefix + Pattern.quote(ARMA_PROGRAM_DIRECTORY_PLACEHOLDER),
				programDir.toString().replace("$", "\\$"));
		inputPath = inputPath.replaceAll(prefix + Pattern.quote(HOME_FOLDER_PLACEHOLDER),
				homeDir.toString().replace("$", "\\$"));

		this.filePath = new Path(inputPath);
	}

	/**
	 * Creates a {@linkplain IPath} from this object. Note that this path does not
	 * contain any placeholders anymore
	 */
	public IPath toPath() {
		// return copy
		return Path.fromPortableString(filePath.toPortableString());
	}

	/**
	 * Gets the String representation of this path that contains all placeholders
	 */
	public String toPlaceholderString() {
		return rawPath;
	}

	@Override
	public String toString() {
		return rawPath;
	}

	/**
	 * Checks whether the path represented by this object is syntactically valid
	 */
	public boolean isValid() {
		return filePath.isValidPath(filePath.toOSString());
	}

	/**
	 * Appends the given segment at the end of this path
	 * 
	 * @param segment
	 *            The segment to append
	 */
	public void append(String segment) {
		filePath.append(segment);
		rawPath += (segment.startsWith("/") ? "" : "/") + segment;
	}
}
