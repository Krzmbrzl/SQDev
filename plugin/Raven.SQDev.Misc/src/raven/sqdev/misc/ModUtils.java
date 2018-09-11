package raven.sqdev.misc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

public class ModUtils {

	/**
	 * Gets all functions defined in the mod with the given name
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
			ConfigClass config = null;

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
					functions.add(Function.from(current));
				}
			}
		} catch (IOException | RapificationException | ConfigException e) {
			e.printStackTrace();
		}
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
	 * Gets all vanilla functions
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
