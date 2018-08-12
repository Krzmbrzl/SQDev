package raven.sqdev.preferences.pages;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ExpandEvent;
import org.eclipse.swt.events.ExpandListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.exceptions.BadSyntaxException;
import raven.sqdev.exceptions.SQDevCollectionException;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.infoCollection.SQFCommandCollector;
import raven.sqdev.infoCollection.base.ELocality;
import raven.sqdev.infoCollection.base.KeywordList;
import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.misc.DataTypeList;
import raven.sqdev.misc.EDataType;
import raven.sqdev.misc.SQDevInfobox;
import raven.sqdev.misc.SQDevPreferenceUtil;
import raven.sqdev.pluginManagement.ResourceManager;
import raven.sqdev.pluginManagement.SQDevEclipseEventManager;
import raven.sqdev.preferences.preferenceEditors.BooleanSQDevPreferenceEditor;
import raven.sqdev.preferences.preferenceEditors.ValueSQDevPreferenceEditor;
import raven.sqdev.syntax.Syntax;
import raven.sqdev.util.Util;

public class SQDevMiscPreferencePage extends SQDevPreferencePage {
	/**
	 * The job used for updating the keywords
	 */
	private static Job collectionJob;
	/**
	 * The button corresponding to the update function
	 */
	private Button updateButton;
	/**
	 * The list storing previously processed commands
	 */
	private KeywordList commandList;
	/**
	 * A flag indicating that all failed commands should be skipped
	 */
	private boolean skipAllFailures;
	/**
	 * A StringBuilder containing all URLs of the keywords that failed updating on
	 * their own
	 */
	private StringBuilder failedKeywordURLs;

	public SQDevMiscPreferencePage() {
		super();
		failedKeywordURLs = new StringBuilder();
		skipAllFailures = false;
	}

	@Override
	public void init(IWorkbench workbench) {
		setDescription("Miscellaneous preferences about the plugin");

		// SQF keyword collection
		updateButton = new Button(createContainer(), SWT.PUSH);
		updateButton.setToolTipText("Updates the SQF commands according to the BIKI. This may take a while");
		updateButton.setEnabled(collectionJob == null || collectionJob.getResult() != null);

		// set text according to status
		if (!updateButton.isEnabled()) {
			updateButton.setText("Updating commands...");
		} else {
			updateButton.setText("Update commands");
		}

		updateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				// change button status
				updateButton.setEnabled(false);
				updateButton.setText("Updating commands...");
				updateButton.pack(true);

				updateKeywords();
			}
		});

		Button resetButton = new Button(createContainer(), SWT.PUSH);
		resetButton.setText("Reset Commands");

		if (ResourceManager.getManager().isOnBackup("SQFKeywords.txt")) {
			resetButton.setEnabled(false);
		} else {
			resetButton.setToolTipText("Resets the commands to the backup version");

			resetButton.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseUp(MouseEvent e) {
					// backup keywords
					ResourceManager.getManager().switchToBackup("SQFKeywords.txt");

					resetButton.setEnabled(false);
				}
			});
		}

		// preferences about the command collection
		Group keywordGroup = createGroup("Command collection/updating");

		createDescription(keywordGroup, "Preferences about the command update process");

		addPreferenceEditor(new ValueSQDevPreferenceEditor(SQDevPreferenceConstants.SQDEV_COLLECTION_API_ADRESS,
				"&BIKI API:", "The adress to the BIKI API. If there is no urgent need do not change this value!",
				keywordGroup));

		addPreferenceEditor(new ValueSQDevPreferenceEditor(SQDevPreferenceConstants.SQDEV_COLLECTION_API_MAINPAGE,
				"&Main Page Name:", "The name of the main page that lists the SQF commands."
						+ " If there is no urgent need do not change this value!",
				keywordGroup));


		// all preferences about user prompts
		Group promptGroup = createGroup("User prompt");

		createDescription(promptGroup, "Preferences about when to prompt the user to do something");

		addPreferenceEditor(new BooleanSQDevPreferenceEditor(SQDevPreferenceConstants.SQDEV_PROMPT_ALWAYS_SAVE_ON_EXIT,
				"&Always save on exit:",
				"Whether unsaved preferences should get saved automatically when clicking \"OK\" without a popup asking for it",
				promptGroup));

		addPreferenceEditor(new BooleanSQDevPreferenceEditor(SQDevPreferenceConstants.SQDEV_PROMPT_ASK_FOR_DELETION,
				"&Validate deletions:", "Whether the plugin should prompt the user to validate a deletion he caused",
				promptGroup));
	}

	/**
	 * Schedules the keyword update job
	 */
	private void updateKeywords() {
		commandList = new KeywordList();

		updateKeywords(null);
	}

	/**
	 * Schedules the keyword update Job
	 * 
	 * @param firstCommand
	 *            The command to start with
	 * @param lastCommand
	 *            The command to stop at
	 * @param previous
	 *            A list of already gathered commands
	 * @param repeat
	 *            The URL that should be repeated. <code>Null</code> if none
	 */
	private void updateKeywords(URL repeat) {
		collectionJob = new Job("Updating keywords") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Updating keywords", 1);

				try {
					// gather keywords from the wiki
					SQFCommandCollector collector = new SQFCommandCollector(SQDevPreferenceUtil.getAPIAdress(),
							SQDevPreferenceUtil.getAPIMainPage());

					KeywordList list = collector.collect(monitor, repeat);

					list.addKeywords(commandList.getKeywords().values()); // add previously gathered commands

					if (monitor.isCanceled()) {
						// ask whether to save the list
						SQDevInfobox info = new SQDevInfobox("The keyword update has been interrupted.\n"
								+ "Do you wish to store the current keywords?"
								+ " (This will override the current keword list and may"
								+ " leed to an incomplete list)", SWT.ICON_QUESTION | SWT.YES | SWT.NO);

						if (info.open() != SWT.YES) {
							// don't save
							return Status.OK_STATUS;
						}
					}

					// save the keywords
					monitor.done();
					monitor.beginTask("Storing keywords...", IProgressMonitor.UNKNOWN);

					ResourceManager manager = ResourceManager.getManager();
					manager.updateResource(ResourceManager.KEYWORDS_RESOURCE, list.getSaveableFormat());

					if (failedKeywordURLs.length() > 0) {
						// inform user about all failed keywords
						SQDevInfobox info = new SQDevInfobox( "The commands corresponding to the following URLs could not be processed properly. "
								+ "If you didn't enter them manually the plugin won't know about them at all!\n"
								+ "Please consider creating a bug report for that.\n\n"
								+ failedKeywordURLs.toString().trim(), SWT.ICON_WARNING);
						
						info.open();
					}

					// tell the user about restart
					SQDevInfobox info = new SQDevInfobox("In order for the new keywords to take effect"
							+ " all respective editors will be restarted", SWT.ICON_INFORMATION);

					if (info.open() != SWT.OK) {
						return Status.OK_STATUS;
					}

					// restart all editors in order to overtake the changes
					Util.restartAllEditors();
				} catch (IOException | SQDevException e) {
					if (e instanceof SQDevCollectionException
							&& ((SQDevCollectionException) e).getFailedKeywordURL() != null) {
						SQDevCollectionException ex = (SQDevCollectionException) e;

						commandList.addKeywords(ex.getPreviouslyProcessedKeywords().getKeywords().values());

						failedKeywordURLs.append(ex.getFailedKeywordURL().toString() + "\n");

						if (skipAllFailures) {
							updateKeywords();
						}

						openKeywordFailDialog(ex.getMessage(), ex.getFailedKeywordURL());

						// SQDevInfobox info = new SQDevInfobox(
						// "Failed at updating keywords at \"" + ((ex.getFailedKeywordURL() == null) ?
						// "Internal"
						// : ex.getFailedKeywordURL().toString()) + "\"",
						// ex, "Do you want to retry this command?");
						// info.addStyle(SWT.CANCEL);
						//
						// int result = info.open();
						//
						// switch (result) {
						// case SWT.YES:
						// updateKeywords(ex.getFailedKeywordURL());
						// break;
						//
						// case SWT.NO:
						// updateKeywords(null);
						// break;
						// }

					} else {
						SQDevInfobox info = new SQDevInfobox("Failed at updating keywords!", e);
						info.open();
					}

					e.printStackTrace();
					return Status.CANCEL_STATUS;
				} finally {
					if (!PlatformUI.getWorkbench().getDisplay().isDisposed()) {
						// reset button status
						PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {

							@Override
							public void run() {
								if (updateButton != null && !updateButton.isDisposed()) {
									updateButton.setText("Update keywords");
									updateButton.setEnabled(true);
									updateButton.pack(true);
								}
							}
						});
					}

					monitor.done();
				}

				return Status.OK_STATUS;
			}
		};

		// make sure eclipse is not closed with this job running
		SQDevEclipseEventManager.getManager().registerCloseSuspendingJob(collectionJob);

		collectionJob.schedule();
	}


	private void openKeywordFailDialog(String errorMessage, URL failedKeywordURL) {
		Display display = PlatformUI.getWorkbench().getDisplay();

		display.syncExec(new Runnable() {

			@Override
			public void run() {
				Shell eclipseShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

				if (eclipseShell == null) {
					// can't report error because user is not in eclipse
					// anymore
					return;
				}

				// add some extra WS to the error message
				String errorMsg = "\t" + errorMessage;
				errorMsg = errorMsg.replace("\n", "\t\n\t") + "\t";

				Shell shell = new Shell(eclipseShell);
				shell.setLayout(new GridLayout(1, true));
				shell.setImage(display.getSystemImage(SWT.ERROR));
				shell.setText("Error in command update");
				shell.setSize(810, 550);

				ScrolledComposite scroller = new ScrolledComposite(shell, SWT.H_SCROLL | SWT.V_SCROLL);
				Composite mainComp = new Composite(scroller, SWT.BORDER);
				scroller.setContent(mainComp);
				scroller.setExpandHorizontal(true);
				scroller.setExpandVertical(true);
				scroller.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

				RowLayout rLayout = new RowLayout(SWT.VERTICAL);
				rLayout.spacing = 8;
				rLayout.wrap = false;
				mainComp.setLayout(rLayout);

				// add the error message being displayed
				StyledText sTextWidget = new StyledText(mainComp, SWT.READ_ONLY | SWT.WRAP | SWT.MULTI);
				sTextWidget.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
				sTextWidget.setLayoutData(
						new RowData(shell.getSize().x - rLayout.marginLeft - rLayout.marginRight - 20, SWT.DEFAULT));

				// assemble message to display
				final String firstPart = "An error occured during the command update! It occured during processing of ";
				String msg = firstPart + failedKeywordURL.toString();

				sTextWidget.setText(msg);

				// add style range for the link
				StyleRange linkStyleRange = new StyleRange(firstPart.length(), failedKeywordURL.toString().length(),
						null, null);
				linkStyleRange.underline = true;
				linkStyleRange.underlineStyle = SWT.UNDERLINE_LINK;
				linkStyleRange.data = failedKeywordURL;

				sTextWidget.setStyleRange(linkStyleRange);

				// add logic for making the link clickable
				sTextWidget.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseUp(MouseEvent event) {
						try {
							int offset = sTextWidget.getOffsetAtLocation(new Point(event.x, event.y));
							if (offset != -1) {
								StyleRange style = null;
								try {
									style = sTextWidget.getStyleRangeAtOffset(offset);
								} catch (IllegalArgumentException e) {
									// no character under event.x, event.y
								}
								if (style != null && style.underline && style.underlineStyle == SWT.UNDERLINE_LINK
										&& style.data instanceof URL) {
									try {
										PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser()
												.openURL((URL) style.data);
									} catch (PartInitException e) {
										e.printStackTrace();
									}
								}
							}
						} catch (IllegalArgumentException e) {
							// The offset clicked location is invalid -> silently ignore and do nothing
						}
					}
				});


				ExpandBar expandBar = new ExpandBar(mainComp, SWT.NONE);
				expandBar.addExpandListener(new ExpandListener() {

					private int expansions = 0;

					@Override
					public void itemExpanded(ExpandEvent e) {
						expansions++;
						changed(e, true);
					}

					@Override
					public void itemCollapsed(ExpandEvent e) {
						expansions--;
						changed(e, false);
					}

					private void changed(ExpandEvent e, boolean expand) {
						Object data = expandBar.getLayoutData();

						if (e.item instanceof ExpandItem && e.item != null) {
							ExpandItem expItem = (ExpandItem) e.item;

							if (expItem.getControl() == null) {
								return;
							}

							RowData rowData = null;
							if (data instanceof RowData && data != null) {
								rowData = (RowData) data;
							} else {
								rowData = new RowData();
							}

							Point size = expItem.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT);

							if (expand) {
								rowData.width = Math.max(size.x, rowData.width);
								rowData.height = expandBar.getSize().y + size.y;
							} else {
								if (expansions == 0) {
									rowData.width = expandBar.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
								}
								rowData.height = expandBar.getSize().y - size.y;
							}

							expandBar.setLayoutData(rowData);

							mainComp.layout();
							scroller.setMinSize(mainComp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
						}
					}
				});

				// add detailed info about the error message
				ExpandItem errorMsgItem = new ExpandItem(expandBar, SWT.NONE);
				errorMsgItem.setText("Detailed error message");

				Composite errorComp = new Composite(expandBar, SWT.NONE);
				errorComp.setLayout(new FillLayout());

				Text errorText = new Text(errorComp, SWT.READ_ONLY | SWT.MULTI);
				errorText.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
				errorText.setText(errorMessage + "\n");
				errorMsgItem.setControl(errorComp);
				errorMsgItem.setHeight(errorComp.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);


				// add the construct for inputting the command info manually
				final int textFieldWidth = 600;

				ExpandItem inputItem = new ExpandItem(expandBar, SWT.NONE);
				inputItem.setText("Input command manually");

				Composite inputComp = new Composite(expandBar, SWT.NONE);
				inputComp.setLayout(new GridLayout(2, false));

				String nameTooltip = "The name of the command";
				Label nameLabel = new Label(inputComp, SWT.NONE);
				nameLabel.setText("Command name:");
				nameLabel.setToolTipText(nameTooltip);
				nameLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));

				Text nameText = new Text(inputComp, SWT.SINGLE | SWT.BORDER);
				GridData textData = new GridData(SWT.FILL, SWT.FILL, true, false);
				textData.widthHint = textFieldWidth;
				nameText.setLayoutData(textData);
				nameText.setToolTipText(nameTooltip);

				String wikiTooltip = "The URL leading to the command's wiki page";
				Label wikiLabel = new Label(inputComp, SWT.NONE);
				wikiLabel.setText("Wiki-Page URL:");
				wikiLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
				wikiLabel.setToolTipText(wikiTooltip);

				Text wikiText = new Text(inputComp, SWT.BORDER | SWT.SINGLE);
				wikiText.setText(failedKeywordURL.toString());
				wikiText.setLayoutData(textData);
				wikiText.setToolTipText(wikiTooltip);

				String localityTooltip = "The argument and effect locality. Use AL, AG, EL and EA to indicate local/global argument-locality and effect-locality. "
						+ "If specifying both the respective abbreviations have to se separated by whitespace.";
				Label localityLabel = new Label(inputComp, SWT.NONE);
				localityLabel.setText("Locality:");
				localityLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
				localityLabel.setToolTipText(localityTooltip);

				Text localityText = new Text(inputComp, SWT.BORDER | SWT.SINGLE);
				localityText.setLayoutData(textData);
				localityText.setToolTipText(localityTooltip);

				String syntaxTooltip = "The comman's sytax(es). Write the paramater as the expected type (e.g. hint String). "
						+ "Each syntax has to start on its own line. "
						+ "The return type of the command for each syntax has to be separated by a '-' from the syntax. Mutliple types have to be separated by '"
						+ DataTypeList.TYPE_SEPERATOR + "'. Example: hint String - Nothing";
				Label syntaxLabel = new Label(inputComp, SWT.NONE);
				syntaxLabel.setText("Syntax:");
				syntaxLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
				syntaxLabel.setToolTipText(syntaxTooltip);

				Text syntaxText = new Text(inputComp, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
				GridData multiTextData = new GridData(SWT.FILL, SWT.FILL, true, false);
				multiTextData.widthHint = textFieldWidth;
				multiTextData.heightHint = new GC(syntaxText).stringExtent("|").y * 3;
				syntaxText.setLayoutData(multiTextData);
				syntaxText.setToolTipText(syntaxTooltip);

				String rawSyntaxTooltip = "The raw syntax contains descriptive placeholders instead of actual data types. "
						+ "Each syntax has to be on its own line. Example: hint message";
				Label rawSyntaxLabel = new Label(inputComp, SWT.NONE);
				rawSyntaxLabel.setText("Raw Syntax:");
				rawSyntaxLabel.setToolTipText(rawSyntaxTooltip);

				Text rawSyntaxText = new Text(inputComp, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
				rawSyntaxText.setLayoutData(multiTextData);
				rawSyntaxText.setToolTipText(rawSyntaxTooltip);

				String descriptionTooltip = "The command's description";
				Label descriptionLabel = new Label(inputComp, SWT.NONE);
				descriptionLabel.setText("Description:");
				descriptionLabel.setToolTipText(descriptionTooltip);

				Text descriptionText = new Text(inputComp, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
				descriptionText.setText("Not provided");
				descriptionText.setLayoutData(multiTextData);
				descriptionText.setToolTipText(descriptionTooltip);

				Button createCommandButton = new Button(inputComp, SWT.PUSH);
				createCommandButton.setText("Create command");
				createCommandButton.setToolTipText(
						"This will try to convert the given information into an SQF command the plugin understands.");
				createCommandButton.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, true, 2, 1));

				createCommandButton.addListener(SWT.Selection, new Listener() {

					@Override
					public void handleEvent(Event event) {
						// reset all background colors of the text boxes to plain white
						nameText.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
						wikiText.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
						localityText.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
						syntaxText.setBackground(display.getSystemColor(SWT.COLOR_WHITE));

						// reset all tooltips
						nameText.setToolTipText(nameTooltip);
						wikiText.setToolTipText(wikiTooltip);
						localityText.setToolTipText(localityTooltip);
						syntaxText.setToolTipText(syntaxTooltip);

						// start extracting and validating
						String name = nameText.getText().trim();
						if (name.isEmpty()) {
							nameText.setBackground(display.getSystemColor(SWT.COLOR_RED));
							nameText.setToolTipText("The name may not be empty!");
							return;
						}

						SQFCommand command = new SQFCommand(name);

						String wikiAdress = wikiText.getText().trim();
						if (wikiAdress.isEmpty()) {
							wikiText.setBackground(display.getSystemColor(SWT.COLOR_RED));
							wikiText.setToolTipText("The URL may not be empty!");
							return;
						}

						URL wikiURL;
						try {
							wikiURL = new URL(wikiAdress);
						} catch (MalformedURLException e) {
							wikiText.setBackground(display.getSystemColor(SWT.COLOR_RED));
							wikiText.setToolTipText("Invalid URL: " + e.getMessage());
							return;
						}
						command.setWikiPage(wikiURL);

						String localityString = localityText.getText().trim();
						if (!localityString.isEmpty()) {
							if (!localityString.matches("(AL|AG|EL|EG)(\\s+(AL|AG|EL|EG))?")) {
								localityText.setBackground(display.getSystemColor(SWT.COLOR_RED));
								localityText.setToolTipText(
										"The specified locality does not satisfy the expected pattern!");
								return;
							}

							command.setArgumentLocality(localityString.contains("AL") ? ELocality.LOCAL
									: localityString.contains("AG") ? ELocality.GLOBAL : ELocality.UNDEFINED);
							command.setEffectLocality(localityString.contains("EL") ? ELocality.LOCAL
									: localityString.contains("EG") ? ELocality.GLOBAL : ELocality.UNDEFINED);
						}

						String syntaxString = syntaxText.getText().trim();
						if (syntaxString.isEmpty()) {
							syntaxText.setBackground(display.getSystemColor(SWT.COLOR_RED));
							syntaxText.setToolTipText("A syntax has to be specified!");
							return;
						}

						String[] strSyntaxes = syntaxString.split("\n");
						int line = 1;
						for (String currentSytaxString : strSyntaxes) {
							currentSytaxString = currentSytaxString.trim();
							if (currentSytaxString.isEmpty()) {
								continue;
							}

							if (!currentSytaxString.matches("(\\w+\\s*)+-\\s*\\w+(\\s*"
									+ Pattern.quote(DataTypeList.TYPE_SEPERATOR) + "\\s*\\w+)*")) {
								syntaxText.setBackground(display.getSystemColor(SWT.COLOR_RED));
								syntaxText.setToolTipText(
										"Syntax on line " + line + " does not fulfill the expected pattern!");
								return;
							}

							String returnValues = currentSytaxString.substring(currentSytaxString.indexOf("-") + 1)
									.trim();
							String[] values = returnValues.split(DataTypeList.TYPE_SEPERATOR);
							for (String returnValue : values) {
								if (!EDataType.isValidDataType(returnValue)) {
									syntaxText.setBackground(display.getSystemColor(SWT.COLOR_RED));
									syntaxText.setToolTipText(
											"Unknown data type \"" + returnValue + "\" on line " + line + "!");
									return;
								}
							}

							currentSytaxString = currentSytaxString.substring(0, currentSytaxString.indexOf("-"))
									.trim();
							Syntax syntax;
							try {
								syntax = Syntax.parseSyntax(currentSytaxString, name);
							} catch (BadSyntaxException e) {
								syntaxText.setBackground(display.getSystemColor(SWT.COLOR_RED));
								syntaxText.setToolTipText("Invalid syntax on line " + line + ": " + e.getMessage());
								return;
							}

							command.addSyntax(syntax);
							command.setReturnType(syntax, returnValues);

							line++;
						}

						String[] rawSyntaxes = rawSyntaxText.getText().trim().split("\n");
						for (String rawSyntax : rawSyntaxes) {
							if (rawSyntax.isEmpty()) {
								continue;
							}
							command.addRawSyntax(rawSyntax);
						}

						String description = descriptionText.getText().trim();
						if (!description.isEmpty()) {
							command.setDescription(description);
						}

						// add command to list and continue update
						commandList.addKeyword(command);
						shell.dispose();
						updateKeywords();
					}
				});


				inputItem.setControl(inputComp);
				inputItem.setHeight(inputComp.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);


				scroller.setMinSize(mainComp.computeSize(SWT.DEFAULT, SWT.DEFAULT));

				// add buttons to the bottom
				Composite comp = new Composite(shell, SWT.NONE);
				comp.setLayout(new GridLayout(2, false));
				comp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

				Composite spacer = new Composite(comp, SWT.NONE);
				spacer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

				Composite buttonComp = new Composite(comp, SWT.NONE);
				buttonComp.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, true));
				buttonComp.setLayout(new GridLayout(4, true));

				GridData buttonData = new GridData(SWT.FILL, SWT.FILL, true, false);

				Button cancelButton = new Button(buttonComp, SWT.PUSH);
				cancelButton.setText("Cancel");
				cancelButton.setToolTipText("Cancels the command update entirely");
				cancelButton.setLayoutData(buttonData);
				cancelButton.addListener(SWT.Selection, new Listener() {

					@Override
					public void handleEvent(Event event) {
						shell.dispose();
					}
				});

				Button skipButton = new Button(buttonComp, SWT.PUSH);
				skipButton.setText("Skip");
				skipButton.setToolTipText(
						"Skips the failed command. This will cause the command to be unknown to the plugin after the update!");
				skipButton.setLayoutData(buttonData);
				skipButton.addListener(SWT.Selection, new Listener() {

					@Override
					public void handleEvent(Event event) {
						shell.dispose();
						updateKeywords();
					}
				});

				Button skipAllButton = new Button(buttonComp, SWT.PUSH);
				skipAllButton.setText("Skip All");
				skipAllButton.setToolTipText(
						"Skips this command and all further commands the updates encounteres any errors");
				skipAllButton.setLayoutData(buttonData);
				skipAllButton.addListener(SWT.Selection, new Listener() {

					@Override
					public void handleEvent(Event event) {
						shell.dispose();

						skipAllFailures = true;
						updateKeywords();
					}
				});

				Button retryButton = new Button(buttonComp, SWT.PUSH);
				retryButton.setText("Retry");
				retryButton.setToolTipText("Tries processing the failed command again");
				retryButton.setLayoutData(buttonData);
				retryButton.addListener(SWT.Selection, new Listener() {

					@Override
					public void handleEvent(Event event) {
						shell.dispose();

						// remove the respective line from failedKeywordURLs
						failedKeywordURLs
								.setLength(failedKeywordURLs.length() - failedKeywordURL.toString().length() - 1);

						updateKeywords(failedKeywordURL);
					}
				});

				shell.open();
			}
		});
	}
}
