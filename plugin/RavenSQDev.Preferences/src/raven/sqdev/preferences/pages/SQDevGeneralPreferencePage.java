package raven.sqdev.preferences.pages;

import java.io.File;
import java.io.IOException;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IWorkbench;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.misc.SQDevPreferenceUtil;
import raven.sqdev.preferences.preferenceEditors.BooleanSQDevPreferenceEditor;
import raven.sqdev.preferences.preferenceEditors.ComboSQDevPreferenceEditor;
import raven.sqdev.preferences.preferenceEditors.DirectorySQDevPreferenceEditor;
import raven.sqdev.util.Util;

/**
 * The preferencePage that contains all general settings for this plugin
 * 
 * @author Raven
 * 
 */
public class SQDevGeneralPreferencePage extends SQDevPreferencePage {

	public SQDevGeneralPreferencePage() {
		super();
	}

	@Override
	public void init(IWorkbench workbench) {
		setDescription("General SQDev preferences");

		// preferences concerning the ArmA directories
		Group armaDirs = createGroup("ArmA directories");

		createDescription(armaDirs, "Here the location of your ArmA folders are specified");

		String exeName;
		String docTooltip;

		if (System.getProperty("os.name").toLowerCase().contains("windows")) {
			exeName = "arma3.exe";
			docTooltip = "The path to the directory that contains the \"Arma 3\" (and \"Arma 3 - "
					+ "Other Profiles\") directory (Normally this is your documents directory)";
		} else {
			exeName = "arma3.i386";
			docTooltip = "The path to the directory that contains the \"Arma 3\" (and \"Arma 3 - "
					+ "Other profiles\") directory (Normally this is \"$HOME$/.local/share/"
					+ "bohemiainteractive/arma3/GameDocuments/\"";
		}

		DirectorySQDevPreferenceEditor dirEditor = new DirectorySQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_INFO_ARMA_MAIN_DIRECTORY, "&Program:",
				"The path to the directory in which your " + exeName + " is located", armaDirs);
		dirEditor.addFileToMatch(exeName);
		addPreferenceEditor(dirEditor);

		final String armaFolder = "Arma 3";
		DirectorySQDevPreferenceEditor docDirEditor = new DirectorySQDevPreferenceEditor(
				SQDevPreferenceConstants.SQDEV_INFO_ARMA_DOCUMENTS_DIRECTORY, "&Documents:", docTooltip, armaDirs);
		docDirEditor.addFolderToMatch(armaFolder);
		addPreferenceEditor(docDirEditor);

		addPreferenceEditor(new DirectorySQDevPreferenceEditor(SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_RPT_PATH,
				"&RPTs:", "The path to the folder ArmA is storing it's RPTs (Affects the same "
						+ "preference as the box on the Views page)",
				armaDirs));

		// infos
		Group defaults = createGroup("Defaults");

		createDescription(defaults, "Some defaults that will be used by the plugin");

		addPreferenceEditor(new BooleanSQDevPreferenceEditor(SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_AUTOEXPORT,
				"&Default auto-export:", "Indicating whether auto-export is enabled by default "
						+ "(can be overwritten by each project individually)",
				defaults));

		addPreferenceEditor(new ComboSQDevPreferenceEditor(SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_TERRAIN,
				"&Default terrain:", Util.getTerrains(), true,
				"Defines the default terrain newly created projects will be located on per"
						+ " default (Can be overwritten by each project individually)",
				defaults));

		addPreferenceEditor(new ComboSQDevPreferenceEditor(SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_PROFILE,
				"&Default profile:", Util.getProfiles().toArray(new String[Util.getProfiles().size()]), false,
				"Defines the default profile newly created projects will be associated with"
						+ " per default (Can be overwritten by each project individually)",
				defaults));

		// compability
		Group compability = createGroup("Various compability options/actions");
		createDescription(compability,
				"Preferences and/or actions connected to any kind of compability related issues");

		Button createDummyFilesButton = new Button(compability, SWT.PUSH);
		createDummyFilesButton.setText("Create dummy ArmA-infrastructure");
		createDummyFilesButton.setToolTipText("Creates the folder infrastructure ArmA would create "
				+ "(and is needed for this plugin to work), this option is intended for machines"
				+ " that don't have ArmA installed!");

		createDummyFilesButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				// create dummy dirs
				final String base = System.getProperty("user.home") + File.separator + ".ArmaDummyFiles";

				final File baseDir = new File(base);

				if (!baseDir.exists()) {
					baseDir.mkdir();
				}

				final File main = new File(base + File.separator + "Base");
				final File documents = new File(base + File.separator + "Documents");
				final File armaDir = new File(documents, armaFolder);
				final File rpt = new File(base + File.separator + "RPT");

				if (!main.exists()) {
					main.mkdirs();
				}

				final File exe = new File(main, exeName);

				if (!exe.exists()) {
					try {
						exe.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
						SQDevInfobox info = new SQDevInfobox("Couldn't create dummy exe", e);
						info.open(false);
					}
				}

				if (!documents.exists()) {
					documents.mkdirs();
				}
				if (!armaDir.exists()) {
					armaDir.mkdir();
				}

				if (!rpt.exists()) {
					rpt.mkdirs();
				}

				IPreferenceStore prefStore = SQDevPreferenceUtil.getPreferenceStore(); // TODO: is Null

				// disbale auto-export
				prefStore.setValue(SQDevPreferenceConstants.SQDEV_INFO_DEFAULT_AUTOEXPORT, false);

				// load the dummy-dirs into the preferences
				prefStore.setValue(SQDevPreferenceConstants.SQDEV_INFO_ARMA_MAIN_DIRECTORY, main.getAbsolutePath());
				prefStore.setValue(SQDevPreferenceConstants.SQDEV_INFO_ARMA_DOCUMENTS_DIRECTORY,
						documents.getAbsolutePath());
				prefStore.setValue(SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_RPT_PATH, rpt.getAbsolutePath());
			}
		});

	}
}
