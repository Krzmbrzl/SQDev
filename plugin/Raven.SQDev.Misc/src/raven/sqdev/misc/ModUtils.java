package raven.sqdev.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.SWT;

import raven.config.CfgFunctions;
import raven.config.ConfigClass;
import raven.config.ConfigException;
import raven.config.ConfigFunction;
import raven.config.RapificationException;
import raven.misc.ByteReader;
import raven.misc.TextReader;
import raven.pbo.PBO;
import raven.pbo.PBOEntry;
import raven.sqdev.infoCollection.base.Function;
import raven.sqdev.interfaces.IStreamProvider;

public class ModUtils {
	/**
	 * Gets all functions defined in the mod with the given name. If possible this
	 * method will read the functions from chache instead from file.
	 * 
	 * @param modName
	 *            The name of the respective mod or <code>null</code> if all found
	 *            mods should be processed
	 * @return A {@linkplain Set} containing all functions defined in the mod's
	 *         config
	 */
	public static Set<Function> getFunctionsFor(String modName) {
		Set<Function> functions = new HashSet<>();

		List<File> modFolders = new ArrayList<>();
		getModFolders(new File(SQDevPreferenceUtil.getArmaProgramDirectory()), modFolders);

		if (modName != null) {
			if (!modName.startsWith("@")) {
				modName = "@" + modName.toLowerCase();
			} else {
				modName = modName.toLowerCase();
			}
		}

		for (File currentFolder : modFolders) {
			if (modName != null && !currentFolder.getName().toLowerCase().equals(modName)) {
				continue;
			}

			File addonFolder = new File(currentFolder, "addons");

			for (File currentAddon : addonFolder.listFiles()) {
				if (currentAddon.getName().toLowerCase().endsWith(".pbo")) {
					try {
						PBO pbo = new PBO(currentAddon);

						getFunctionsFor(pbo, functions);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return functions;
	}

	/**
	 * Gets all functions defined inside the given PBO
	 * 
	 * @param pbo
	 *            The {@linkplain PBO} to extract the functions from
	 * @param functions
	 *            The {@linkplain Collection} to which the found functions should be
	 *            added
	 */
	public static void getFunctionsFor(PBO pbo, Collection<Function> functions) {
		try {
			// check if the cache is applicable
			if (isFunctionsCacheApplicable(pbo.toFile())) {
				// retrieve functions from cache
				functions.addAll(getCachedFunctions(pbo.toFile()));
				return;
			}

			ConfigClass config = null;
			final String pboPrefix = pbo.getPrefix();
			SQFFunctionDescriptionProvider descriptionProvider = new SQFFunctionDescriptionProvider(
					new IStreamProvider() {

						@Override
						public InputStream getStreamFor(String path) throws IOException {
							if (pboPrefix != null && path.toLowerCase().startsWith("\\" + pboPrefix.toLowerCase())
									&& path.length() >= pboPrefix.length() + 2) {
								path = path.substring(pboPrefix.length() + 2);
							}

							PBOEntry entry = pbo.getEntry(path);

							if (entry == null) {
								return null;
							} else {
								entry.toStream();
								return entry.toStream();
							}
						}
					});

			PBOEntry configEntry = pbo.getEntry("config.bin");
			if (configEntry != null) {
				config = ConfigClass.fromRapifiedFile(new ByteReader(configEntry.toStream()));
			} else {
				configEntry = pbo.getEntry("config.cpp");
				if (configEntry == null) {
					configEntry = pbo.getEntry("config.hpp");

					if (configEntry == null) {
						// there seems to be no config-entry for this mod
						return;
					}

					config = ConfigClass.fromTextFile(new TextReader(configEntry.toStream()));
				}
			}

			ConfigClass functionsConfig = config.getSubclass(CfgFunctions.NAME, false);

			if (functionsConfig != null) {
				CfgFunctions cfg = new CfgFunctions(functionsConfig);
				cfg.init();

				for (ConfigFunction current : cfg.getDefinedFunctions().values()) {
					functions.add(Function.from(current, descriptionProvider.getDescription(current)));
					// functions.add(Function.from(current, null));
				}
			}

			// cache read functions
			cacheFunctions(pbo.toFile(), functions);
		} catch (IOException | RapificationException | ConfigException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks whether there is an applicable functions-cache-file for the given one
	 * 
	 * @param file
	 *            The file to search a cache for
	 * @return Whether or not there is an applicable cache
	 */
	protected static boolean isFunctionsCacheApplicable(File file) {
		if (!file.exists()) {
			return false;
		}

		File cacheFile = getFunctionsCacheFile(file);

		return cacheFile.exists() && cacheFile.lastModified() > file.lastModified();
	}

	/**
	 * Reads the functions from the functions-cache for the given file
	 * 
	 * @param file
	 *            The file whose cached functions should be returned
	 * @return The respective functions or an empty list if something went wrong
	 *         (though an empty list doesn't mean that something has gone wrong)
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	protected static Collection<Function> getCachedFunctions(File file) throws FileNotFoundException, IOException {
		File cacheFile = getFunctionsCacheFile(file);

		if (!cacheFile.exists()) {
			throw new IllegalArgumentException("No functions cache available for " + file.getAbsolutePath() + "!");
		}

		ObjectInputStream in = new ObjectInputStream(new FileInputStream(cacheFile));


		Collection<Function> functions;
		try {
			functions = (Collection<Function>) in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

			SQDevInfobox info = new SQDevInfobox(
					"Failed at de-serializing functions-cache for " + file.getAbsolutePath(), e);
			info.open(false);

			// create empty list as fall-back
			functions = new ArrayList<>();
		} catch (NotSerializableException e) {
			// delete cache-file
			cacheFile.delete();

			// create empty list as fall-back
			functions = new ArrayList<>();
		}

		in.close();

		return functions;
	}

	/**
	 * Writes the given collection of functions in a functions-cache associated with
	 * the given file
	 * 
	 * @param file
	 *            The {@linkplain File} to associate the created cache with
	 * @param functions
	 *            The collection of functions to cache
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	protected static void cacheFunctions(File file, Collection<Function> functions)
			throws FileNotFoundException, IOException {
		if (!(functions instanceof Serializable)) {
			throw new IllegalArgumentException("The given collection must be serializable");
		}

		File cacheFile = getFunctionsCacheFile(file);

		// make sure cache-dir exists
		if (!cacheFile.getParentFile().exists()) {
			if (!cacheFile.getParentFile().mkdir()) {
				SQDevInfobox info = new SQDevInfobox("Failed at creating folder for functions-cache!", SWT.ICON_ERROR);
				info.open(false);

				return;
			}
		}

		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(cacheFile));

		try {
			out.writeObject(functions);
		} catch (NotSerializableException e) {
			throw new IllegalArgumentException("The given collection must be serializable!", e);
		} finally {
			out.close();
		}
	}

	/**
	 * Gets the functions-cache-file that is corresponding to the given file
	 * 
	 * @param file
	 *            The file whose cache-file should be obtained
	 * @return The respective file. Note that this file may not exist (yet)
	 */
	protected static File getFunctionsCacheFile(File file) {
		return new File(System.getProperty("user.home") + File.separator + ".SQDevFunctionsCache" + File.separator
				+ file.getAbsolutePath().replace(File.separator, "-").replaceAll("^[A-Za-z]:", "") + ".ser");
	}

	/**
	 * Finds all potential mod-directories inside. A folder is considered a
	 * mod-directory if its name starts with an '@' and it contains a sub-folder
	 * named "addons" (case-sensitive).
	 * 
	 * @param baseDir
	 *            The directory that should be searched (including it's
	 *            sub-directories
	 * @param modFolders
	 *            A {@linkplain Collection} to which all found folders should be
	 *            added
	 */
	public static void getModFolders(File baseDir, Collection<File> modFolders) {
		for (File currentFile : baseDir.listFiles()) {
			if (currentFile.isDirectory()) {
				if (currentFile.getName().startsWith("@")) {
					// potential mod folder -> check for "addons"-subdir
					File addonsDir = new File(currentFile, "addons");

					if (addonsDir.exists() && addonsDir.isDirectory()) {
						modFolders.add(currentFile);
					}
				} else {
					// do a recursive search
					getModFolders(null, modFolders);
				}
			}
		}
	}

	/**
	 * Gets all vanilla functions
	 * 
	 * @param functions
	 *            The {@linkplain Collection} to add the found functions to
	 */
	public static void getVanillaFunctions(Collection<Function> functions) {
		getVanillaFunctions(new File(SQDevPreferenceUtil.getArmaProgramDirectory()), functions);
	}

	/**
	 * Gets all vanilla functions. If available this method will read the requested
	 * functions from cache instead of from file.
	 * 
	 * @param file
	 *            The {@linkplain File} to search for functions. If this is a file
	 *            it is checked whether it is a {@linkplain PBO} that contains
	 *            functions. If it is a folder, all its sub-files are being checked.
	 *            All folders starting with "@" are ignored.
	 * @param functions
	 *            The {@linkplain Collection} to add the found functions to
	 */
	public static void getVanillaFunctions(File file, Collection<Function> functions) {
		if (!file.exists()) {
			return;
		}

		if (file.isDirectory()) {
			if (!file.getName().startsWith("@")) {
				for (File currentFile : file.listFiles()) {
					if (!currentFile.getName().startsWith("@")) {
						getVanillaFunctions(currentFile, functions);
					}
				}
			}
		} else {
			if (!file.isFile() || !file.getName().toLowerCase().endsWith(".pbo")) {
				return;
			}

			try {
				getFunctionsFor(new PBO(file), functions);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
