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
									continue;
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
			}
		}

		return functions;
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

}
