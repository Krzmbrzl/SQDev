package raven.sqdev.ui.views;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.IFindReplaceTarget;
import org.eclipse.jface.text.IFindReplaceTargetExtension3;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.Bullet;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.ST;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.FindReplaceAction;

import raven.sqdev.constants.SQDevPreferenceConstants;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.fileSystemListener.FileSystemChangeAdapter;
import raven.sqdev.fileSystemListener.FileSystemChangeEvent;
import raven.sqdev.fileSystemListener.FileSystemWatcher;
import raven.sqdev.interfaces.IFileSystemChangeListener;
import raven.sqdev.misc.LineBuffer;
import raven.sqdev.misc.AdvancedLineReader;
import raven.sqdev.misc.Pair;
import raven.sqdev.misc.TextUtils;
import raven.sqdev.pluginManagement.ResourceManager;
import raven.sqdev.ui.util.IndexPair;
import raven.sqdev.util.FileUtil;
import raven.sqdev.util.SQDevInfobox;
import raven.sqdev.util.SQDevPreferenceUtil;

public class RPTViewer extends ViewPart
		implements IPropertyChangeListener, IFindReplaceTarget, IFindReplaceTargetExtension3 {
	
	/**
	 * The variable that can be used inside a message that will be replaced by
	 * the corresponding number
	 */
	public static final String MSG_NUMBER_VAR = "%amount%";
	
	/**
	 * The message displayed when removing repeting lines
	 */
	public static final String MSG_LINE_REPETITION = "\t... " + MSG_NUMBER_VAR
			+ " repetition(s)...\n\n";
	
	/**
	 * The maximum size of a group of repeating lines that can be detected
	 */
	public static final int REPETITION_LOOK_AHEAD = 10;
	
	/**
	 * The regex for matching the time stamp in RPTs
	 */
	private static final String TIME_STAMP_REGEX = "[0-9][0-9]:[0-9][0-9]:[0-9][0-9]";
	
	/**
	 * The TabFolder for displaying the RPTs
	 */
	private CTabFolder tabFolder;
	/**
	 * The background color for the RPT display
	 */
	private Color backgroundColor;
	/**
	 * The fileSystemChangeListener that keeps the view up-to-date
	 */
	private IFileSystemChangeListener fileListener;
	/**
	 * A map containing all RPT files with the corresponding
	 * <code>CTabItem</code< that is displaying it's content
	 */
	private Map<File, CTabItem> displayedRPTs;
	/**
	 * The pattern used in order to display dates
	 */
	private String datePattern;
	/**
	 * The cross image corresponding for deletions
	 */
	private Image removeImage;
	/**
	 * The action for toggling the format of the RPT content
	 */
	private Action formatAction;
	/**
	 * The action used for displaying the find dialog
	 */
	private FindReplaceAction findReplaceAction;
	
	
	public RPTViewer() {
		super();
		
		SQDevPreferenceUtil.getPreferenceStore().addPropertyChangeListener(this);
		
		backgroundColor = new Color(PlatformUI.getWorkbench().getDisplay(), new RGB(247, 238, 208));
		
		displayedRPTs = new HashMap<File, CTabItem>();
		
		datePattern = "dd-MM-yy  h:mm a";
	}
	
	@Override
	public void createPartControl(Composite parent) {
		// Create the fin/replace Action
		ResourceBundle bundle = ResourceBundle.getBundle("raven.sqdev.bundles.rptViewerFindDialog");
		findReplaceAction = new FindReplaceAction(bundle, "find_replace_action_", this);
		findReplaceAction.setActionDefinitionId(IWorkbenchCommandConstants.EDIT_FIND_AND_REPLACE);
		IActionBars actionBars = getViewSite().getActionBars();
		actionBars.setGlobalActionHandler(ActionFactory.FIND.getId(), findReplaceAction);
		
		
		tabFolder = new CTabFolder(parent, SWT.TOP);
		
		Display display = PlatformUI.getWorkbench().getDisplay();
		tabFolder.setSelectionBackground(
				new Color[] { display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND),
						/*
						 * display.getSystemColor(
						 * SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT)
						 */ backgroundColor },
				new int[] { 100 }, true);
		
		Menu contextMenu = new Menu(tabFolder);
		MenuItem menuItem = new MenuItem(contextMenu, SWT.NONE);
		menuItem.setText("Test");
		tabFolder.setMenu(contextMenu);
		
		// add context menu for the tabs
		tabFolder.addMenuDetectListener(new MenuDetectListener() {
			
			@Override
			public void menuDetected(MenuDetectEvent e) {
				CTabItem targetFolderItem = tabFolder
						.getItem(tabFolder.toControl(new Point(e.x, e.y)));
				
				if (targetFolderItem != null) {
					// remove old items
					for (MenuItem currentItem : contextMenu.getItems()) {
						currentItem.dispose();
					}
					
					File targetFile = null;
					
					Iterator<Entry<File, CTabItem>> it = displayedRPTs.entrySet().iterator();
					// find respective file corresponding to the
					// targetFolderItem
					while (targetFile == null && it.hasNext()) {
						Entry<File, CTabItem> currentEntry = it.next();
						
						if (currentEntry.getValue().equals(targetFolderItem)) {
							targetFile = currentEntry.getKey();
						}
					}
					
					if (targetFile != null) {
						MenuItem removeItem = new MenuItem(contextMenu, SWT.PUSH);
						removeItem.setText("Delete RPT");
						removeItem.setImage(getRemoveImage());
						
						// has to be final in order to be accessible in the
						// listener
						final String targetFilePath = targetFile.getAbsolutePath();
						
						removeItem.addSelectionListener(new SelectionAdapter() {
							
							@Override
							public void widgetSelected(SelectionEvent e) {
								// Delete the RPT-file
								try {
									if (SQDevPreferenceUtil.promptUserValidationForDeletion()) {
										SQDevInfobox info = new SQDevInfobox(
												"Do you really want to delete this RPT ("
														+ targetFilePath + ")?",
												SWT.ICON_QUESTION | SWT.YES | SWT.NO);
										
										if (info.open() != SWT.YES) {
											// cancel if user doesn't explicitly
											// validates deletion
											return;
										}
									}
									
									Files.delete(Paths.get(targetFilePath));
								} catch (IOException e1) {
									e1.printStackTrace();
									
									SQDevInfobox info = new SQDevInfobox(
											"Failed at deleting RPT (" + targetFilePath + ")", e1);
									
									info.open(false);
								}
							}
						});
					} else {
						// throw exception and cancel menu popup
						try {
							throw new SQDevException(
									"Can't find associated RPT-file for targetFolderItem!");
						} catch (SQDevException e1) {
							e1.printStackTrace();
						} finally {
							e.doit = false;
						}
					}
				} else {
					e.doit = false;
				}
			}
		});
		
		// configure view-menu
		formatAction = new Action("Format RPT", Action.AS_CHECK_BOX) {
			
			@Override
			public void run() {
				// Save current value
				SQDevPreferenceUtil.getPreferenceStore().setValue(
						SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_FORMAT, this.isChecked());
			}
		};
		
		formatAction.setChecked(SQDevPreferenceUtil.doFormatRPTContent());
		getViewSite().getActionBars().getMenuManager().add(formatAction);
		
		
		displayRPTs();
		
		configureFileSystemListener();
	}
	
	@Override
	public void setFocus() {
		tabFolder.setFocus();
	}
	
	/**
	 * Gets a list of all paths to the RPTs
	 */
	private List<IPath> getRPTFiles() {
		List<IPath> rptPaths = new ArrayList<IPath>(0);
		
		IPath root = new Path(SQDevPreferenceUtil.getRPTDirectory());
		
		if (!root.toFile().exists()) {
			// No RPT files available
			return rptPaths;
		}
		
		for (File currentFile : root.toFile().listFiles()) {
			IPath currentPath = new Path(currentFile.getPath());
			
			if (currentPath.getFileExtension() != null
					&& currentPath.getFileExtension().toLowerCase().equals("rpt")) {
				rptPaths.add(currentPath);
			}
		}
		
		return rptPaths;
	}
	
	/**
	 * Completely recreates the displayal of the RPTs
	 */
	private void displayRPTs() {
		List<IPath> rpts = getRPTFiles();
		
		tabFolder.setRedraw(false);
		
		// clear previous items
		while (tabFolder.getItemCount() > 0) {
			tabFolder.getItem(0).dispose();
		}
		
		for (IPath currentRPT : rpts) {
			addRPT(currentRPT.toFile());
		}
		
		if (tabFolder.getItemCount() > 0) {
			tabFolder.setSelection(0);
		}
		
		tabFolder.setRedraw(true);
	}
	
	/**
	 * Gets the index of an item that corresponds with the given modification
	 * date
	 * 
	 * @param date
	 *            The modification date of the RPT the index should be obtained
	 *            for
	 * @return The index the corresponding item should have in the current
	 *         RPT-List
	 */
	private int getListIndex(Date date) {
		boolean matched = false;
		int index = 0;
		
		for (int i = 0; i < tabFolder.getItemCount(); i++) {
			CTabItem currentItem = tabFolder.getItem(i);
			
			try {
				if (date.after(new SimpleDateFormat(datePattern).parse(currentItem.getText()))) {
					index = i;
					matched = true;
					
					break;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if (!matched) {
			// append to the end of the list
			index = tabFolder.getItemCount();
		}
		
		return index;
	}
	
	/**
	 * Adds a RPT file that should be displayed
	 * 
	 * @param rptFile
	 *            The respective <code>File</code> that should be added
	 */
	private void addRPT(File rptFile) {
		Date modificationDate = new Date(rptFile.lastModified());
		
		CTabItem item = new CTabItem(tabFolder, SWT.NONE, getListIndex(modificationDate));
		
		// store pair
		displayedRPTs.put(rptFile, item);
		
		StyledText rptText = new StyledText(tabFolder, SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL);
		rptText.setBackground(backgroundColor);
		rptText.setTabs(12);
		rptText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		// add Line counter
		rptText.addLineStyleListener(new LineStyleListener() {
			public void lineGetStyle(LineStyleEvent e) {
				// Set the line number
				e.bulletIndex = rptText.getLineAtOffset(e.lineOffset);
				
				// Set the style; according to the text size
				StyleRange style = new StyleRange();
				style.metrics = new GlyphMetrics(0, 0,
						Integer.toString(rptText.getLineCount() + 1).length()
								* rptText.getFont().getFontData()[0].getHeight());
				
				// Create and set the bullet
				e.bullet = new Bullet(ST.BULLET_NUMBER, style);
			}
		});
		
		// Configure keyhandler for Ctrl+F
		rptText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == 'f' && e.stateMask == SWT.CTRL) {
					findReplaceAction.run();
				}
			}
		});
		
		item.setControl(rptText);
		item.setText(new SimpleDateFormat(datePattern).format(modificationDate));
		
		// set text of the item
		updateRPT(rptFile);
	}
	
	
	/**
	 * Updates the displayal of the given RPT (Content, date and index)
	 */
	private void updateRPT(File rptFile) {
		if (displayedRPTs.containsKey(rptFile)) {
			CTabItem item = displayedRPTs.get(rptFile);
			
			try {
				SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
				
				Date itemDate = formatter.parse(item.getText());
				Date modificationDate = formatter
						.parse(formatter.format(new Date(rptFile.lastModified())));
				
				if (!(itemDate).equals(modificationDate)) {
					
					boolean selectNew = false;
					if (tabFolder.getSelection() != null) {
						selectNew = tabFolder.getSelection().equals(item);
					}
					
					// retrieve control from old item
					Control content = item.getControl();
					
					// create new item with proper date and position
					item = new CTabItem(tabFolder, SWT.NONE, getListIndex(modificationDate));
					
					item.setText(new SimpleDateFormat(datePattern).format(modificationDate));
					
					// apply old content to the new item
					item.setControl(content);
					
					// dispose old item
					displayedRPTs.get(rptFile).dispose();
					
					// update mapping
					displayedRPTs.put(rptFile, item);
					
					if (selectNew) {
						// select the new item
						tabFolder.setSelection(item);
					}
				}
				
				FileInputStream in = new FileInputStream(rptFile);
				
				String content = FileUtil.readAll(in);
				
				in.close();
				
				// get the text widget that displays the RPT content and the
				// line number label
				StyledText textWidget = (StyledText) item.getControl();
				
				// Format content if wished
				if (SQDevPreferenceUtil.doFormatRPTContent()) {
					content = formatRPTContent(content);
				}
				
				if (!content.equals(textWidget.getText())) {
					// TODO: colorize Conflicting,Error,Warning
					
					// set new content
					textWidget.setText(content);
					
					item.getControl().setVisible(true);
				}
			} catch (ParseException | IOException e) {
				e.printStackTrace();
				
				// Dispose item and recreate it
				displayedRPTs.remove(rptFile);
				item.dispose();
				addRPT(rptFile);
			}
		} else {
			// newly create the item
			addRPT(rptFile);
		}
	}
	
	/**
	 * Removes the given RPT from the displayal
	 * 
	 * @param rptFile
	 *            The RPT to remove
	 */
	private void removeRPT(File rptFile) {
		if (displayedRPTs.containsKey(rptFile)) {
			CTabItem item = displayedRPTs.get(rptFile);
			
			// delte from list
			displayedRPTs.remove(rptFile);
			
			item.dispose();
		}
	}
	
	/**
	 * Configures a file-listener so that it will listen to changes done to the
	 * RPT directory
	 */
	private void configureFileSystemListener() {
		if (fileListener != null) {
			FileSystemWatcher.getDefault().removeFileSystemChangeListener(fileListener);
		}
		
		fileListener = new FileSystemChangeAdapter(SQDevPreferenceUtil.getRPTDirectory()) {
			
			/**
			 * The <code>Runnable</code> that will get executed in order to
			 * update the RPT displayal
			 */
			private Runnable updateList;
			
			@Override
			public void fileCreated(FileSystemChangeEvent event) {
				updateList = new Runnable() {
					
					@Override
					public void run() {
						addRPT(event.getChangedFile());
					}
				};
			}
			
			@Override
			public void fileDeleted(FileSystemChangeEvent event) {
				updateList = new Runnable() {
					
					@Override
					public void run() {
						removeRPT(event.getChangedFile());
					}
				};
			}
			
			@Override
			public void fileChanged(FileSystemChangeEvent event) {
				updateList = new Runnable() {
					
					@Override
					public void run() {
						updateRPT(event.getChangedFile());
					}
				};
			}
			
			@Override
			public void anyChange(FileSystemChangeEvent event) {
				if (new Path(event.getFullPath().toString()).getFileExtension().toLowerCase()
						.equals("rpt")) {
					// one of the above methods have already been called and
					// have set up the Runnable
					
					// execute the set runnable
					PlatformUI.getWorkbench().getDisplay().asyncExec(updateList);
				}
			}
		};
		
		FileSystemWatcher.getDefault().addFileSystemListener(fileListener);
	}
	
	/**
	 * Gets the image corresponding to removal/deletion actions (= red cross)
	 */
	private Image getRemoveImage() {
		if (removeImage == null || removeImage.isDisposed()) {
			removeImage = new Image(PlatformUI.getWorkbench().getDisplay(), ResourceManager
					.getManager().getInternalResourceStream(ResourceManager.REMOVE_ICON));
		}
		
		return removeImage;
	}
	
	/**
	 * Format the given RPT content
	 * 
	 * @param contet
	 *            The content to format
	 * @return The formatted content
	 */
	private String formatRPTContent(String content) {
		content = content.replace("\r\n", "\n");
		
		StringBuilder RPTContent = new StringBuilder(content);
		
		// initializes needed variables
		int repetitionCounter = 0;
		// This map contains all Indexpairs with a Pair containing a number and
		// a message (%amount% in the message will be replaced by the number)
		Map<IndexPair, Pair<Integer, String>> removeLines = new LinkedHashMap<IndexPair, Pair<Integer, String>>();
		IndexPair currentPair = null;
		List<String> linePrefixes = SQDevPreferenceUtil.getRPTFormatPrefixes();
		linePrefixes = linePrefixes.stream().map(String::toLowerCase).collect(Collectors.toList());
		
		LineBuffer buffer = new LineBuffer(REPETITION_LOOK_AHEAD);
		
		AdvancedLineReader reader = new AdvancedLineReader(new StringReader(content));
		String currentLine;
		
		// Start iterating through all lines
		try {
			while ((currentLine = reader.readLineWithoutPrefix(TIME_STAMP_REGEX)) != null) {
				
				if (currentLine.isEmpty()) {
					continue;
				}
				
				// check if the prefix is one to filter out
				String matchingPrefix = TextUtils.startsWithWhich(currentLine.toLowerCase().trim(),
						linePrefixes);
				
				if (matchingPrefix != null) {
					// remove respective line
					if (currentPair == null) {
						currentPair = new IndexPair();
						currentPair.initialize(reader.getLineStartOffset());
					}
					
					reader.mark();
					
					String nextLine = reader.readLineWithoutPrefix(TIME_STAMP_REGEX);
					
					// check next line -> probably needs save and reset
					if (nextLine == null
							|| !nextLine.toLowerCase().trim().startsWith(matchingPrefix)) {
						repetitionCounter++;
						
						// store end index
						currentPair.setStopIndex(reader.getLineStartOffset());
						
						// save current pair
						removeLines.put(currentPair,
								new Pair<Integer, String>(repetitionCounter,
										"\n\t(" + MSG_NUMBER_VAR + ") \"" + matchingPrefix
												+ "\" removed ...\n\n"));
						
						// reset counter and pair
						repetitionCounter = -1; // gets incremented to 0 at
												// loop's end
						currentPair = null;
					}
					
					// reset
					reader.reset();
					
					repetitionCounter++;
				} else {
					// remove repeating lines
					if (buffer.pointAt(currentLine)) {
						reader.mark();
						
						currentPair = new IndexPair();
						currentPair.initialize(reader.getLineStartOffset());
						
						// check if the following lines in the buffer match the
						// following lines in the reader
						StringBuffer repeatingPart = new StringBuffer(
								currentLine + reader.getLineDelimiter());
						
						String followingLine;
						
						while ((followingLine = reader
								.readLineWithoutPrefix(TIME_STAMP_REGEX)) != null
								&& followingLine.equals(buffer.nextLine())) {
							repeatingPart.append(followingLine + reader.getLineDelimiter());
						}
						
						reader.unreadLine(followingLine, true);
						
						if (!buffer.hasNextLine()) {
							// The part is fully repeating -> check if it is
							// repeating more often
							boolean proceed = true;
							int repetitions = 0;
							
							StringBuilder builder = new StringBuilder();
							int lineAmount = Math
									.max(TextUtils.countMatches(repeatingPart.toString(), "\n"), 1);
							
							while (proceed) {
								reader.mark();
								
								for (int i = 0; i < lineAmount; i++) {
									builder.append(
											reader.readLineWithoutPrefix(TIME_STAMP_REGEX) + "\n");
								}
								
								proceed = builder.toString().equals(repeatingPart.toString());
								builder.setLength(0);
								
								repetitions++;
							}
							reader.reset(); // reset as the last part was not
											// part of the repetition
							
							reader.readLine(); // read additional line in order
												// to get proper position
							
							currentPair.setStopIndex(reader.getLineStartOffset() - 1);
							
							removeLines.put(currentPair,
									new Pair<Integer, String>(repetitions, MSG_LINE_REPETITION));
							
							currentPair = null;
							
							reader.reset();
						} else {
							// it's no repetition
							currentPair = null;
							reader.reset();
						}
					}
				}
				
				// Store previous line
				buffer.addLine(currentLine);
			}
			
			reader.close();
			
			IndexPair[] pairs = removeLines.keySet().toArray(new IndexPair[removeLines.size()]);
			
			StringReader removeReader = new StringReader(RPTContent.toString());
			RPTContent = new StringBuilder();
			int counter = 0;
			int indexElement = 0;
			boolean messageInserted = false;
			int NLCounter = 0;
			char currentChar;
			int maxBlankLines = SQDevPreferenceUtil.getMaximumBlankLinesInRPT();
			
			removeReader.mark(1);
			
			while ((currentChar = (char) removeReader.read()) != (char) -1) {
				if (pairs.length > indexElement) {
					if (pairs[indexElement].contains(counter)) {
						if (!messageInserted) {
							Pair<Integer, String> msgInfo = removeLines.get(pairs[indexElement]);
							
							String message = ((NLCounter == 0) ? "\n" : "") + msgInfo.getSecond()
									.replace(MSG_NUMBER_VAR, String.valueOf(msgInfo.getFirst()));
							
							NLCounter = 0; // reset counter
							
							for (int i = message.length() - 1; i >= 0; i--) {
								if (NLCounter > maxBlankLines) {
									// make sure the message does not exceed
									// blank line limit
									StringBuilder messageBuilder = new StringBuilder();
									int NLs = 0;
									boolean matchedNormalChar = false;
									
									for (char currentMsgChar : message.toCharArray()) {
										if (NLs <= maxBlankLines) {
											messageBuilder.append(currentMsgChar);
										} else {
											break;
										}
										
										if (matchedNormalChar && currentMsgChar == '\n') {
											NLs++;
										} else {
											if (Character.isLetterOrDigit(currentMsgChar)) {
												matchedNormalChar = true;
											}
										}
									}
									
									message = messageBuilder.toString();
									break;
								}
								
								if (message.charAt(i) == '\n') {
									NLCounter++;
								} else {
									if (message.charAt(i) != '\r') {
										break;
									}
								}
							}
							
							RPTContent.append(message);
							
							messageInserted = true;
							
							currentChar = '\r'; // Prevent counter from being
												// reset
						}
					} else {
						if (pairs[indexElement].getStopIndex() <= counter) {
							indexElement++;
							removeReader.reset();
							messageInserted = false;
							continue;
						} else {
							// prevent too many blank lines in a row
							if (NLCounter <= maxBlankLines
									|| !(currentChar == '\n' || currentChar == '\r')) {
								RPTContent.append(currentChar);
							}
						}
					}
				} else {
					// prevent too many blank lines in a row
					if (NLCounter <= maxBlankLines
							|| !(currentChar == '\n' || currentChar == '\r')) {
						RPTContent.append(currentChar);
					}
				}
				
				if (!messageInserted) {
					// Only count NLs when not looping over removed parts
					if (currentChar == '\n') {
						NLCounter++;
					} else {
						if (currentChar != '\r') {
							// reset counter
							NLCounter = 0;
						}
					}
				}
				
				counter++;
				removeReader.mark(1);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		 * // OLD APPROACH // Remove duplicate lines with a little note
		 * Iterator<Entry<IndexPair, Pair<Integer, String>>> it =
		 * removeLines.entrySet().iterator(); int charactersRemoved = 0; while
		 * (it.hasNext()) { Entry<IndexPair, Pair<Integer, String>> currentEntry
		 * = it.next(); IndexPair pair = currentEntry.getKey(); Pair<Integer,
		 * String> currentInfoPair = currentEntry.getValue(); String message =
		 * currentInfoPair.getSecond().replace(MSG_NUMBER_VAR,
		 * String.valueOf(currentInfoPair.getFirst()));
		 * 
		 * int oldLength = RPTContent.length();
		 * 
		 * // Adjust indices so that they can be applied to the new content
		 * pair.decrease(charactersRemoved);
		 * 
		 * int startIndex = pair.getStartIndex();
		 * 
		 * // Remove lines with respective message RPTContent =
		 * RPTContent.delete(startIndex, pair.getStopIndex()).insert(startIndex,
		 * message);
		 * 
		 * // Remove previous newlines while (startIndex > 1 &&
		 * RPTContent.charAt(startIndex - 1) == '\n' &&
		 * RPTContent.charAt(startIndex - 2) == '\n') { RPTContent =
		 * RPTContent.deleteCharAt(startIndex - 1);
		 * 
		 * startIndex--; }
		 * 
		 * // Keep track on how many characters have been removed from the //
		 * original charactersRemoved += oldLength - RPTContent.length(); }
		 */
		
		return RPTContent.toString();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		FileSystemWatcher.getDefault().removeFileSystemChangeListener(fileListener);
		
		backgroundColor.dispose();
		
		if (removeImage != null) {
			removeImage.dispose();
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		switch (event.getProperty()) {
			case SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_RPT_PATH:
				configureFileSystemListener();
				displayRPTs();
				break;
			case SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_FORMAT:
				if (formatAction != null) {
					formatAction.setChecked((Boolean) event.getNewValue());
				}
				displayRPTs();
				break;
			case SQDevPreferenceConstants.SQDEV_VIEWS_RPTVIEWER_MAX_BLANK_REPETITION:
				displayRPTs();
				break;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		/*
		 * if (IFindReplaceTarget.class.equals(adapter)) { return this; }
		 */
		
		return super.getAdapter(adapter);
	}
	
	@Override
	public boolean canPerformFind() {
		return true;
	}
	
	@Override
	public int findAndSelect(int widgetOffset, String findString, boolean searchForward,
			boolean caseSensitive, boolean wholeWord) {
		// Will not be used
		return -1;
	}
	
	@Override
	public Point getSelection() {
		Point widgetSelection = getSelectedRPTTextWidget().getSelection();
		
		return new Point(widgetSelection.x, widgetSelection.y - widgetSelection.x);
	}
	
	@Override
	public String getSelectionText() {
		return getSelectedRPTTextWidget().getSelectionText();
	}
	
	@Override
	public boolean isEditable() {
		return false;
	}
	
	@Override
	public void replaceSelection(String text) {
	}
	
	/**
	 * Gets the currently selected <code>StyledText</code> that is displaying
	 * RPT content. Returns <code>null</code> if none can be found
	 */
	private StyledText getSelectedRPTTextWidget() {
		if (tabFolder.getSelection() == null) {
			return null;
		} else {
			return (StyledText) tabFolder.getSelection().getControl();
		}
	}
	
	@Override
	public int findAndSelect(int offset, String findString, boolean searchForward,
			boolean caseSensitive, boolean wholeWord, boolean regExSearch) {
		StyledText text = getSelectedRPTTextWidget();
		
		if (text == null) {
			return -1;
		}
		
		String contentToSearch = text.getText();
		
		if (offset < 0 || offset >= contentToSearch.length()) {
			// Don't allow invalid offsets
			offset = 0;
		}
		
		if (!caseSensitive) {
			findString = findString.toLowerCase();
			contentToSearch = contentToSearch.toLowerCase();
		}
		
		if (!regExSearch) {
			findString = Pattern.quote(findString);
		}
		
		if (wholeWord) {
			findString = "\\b" + findString + "\\b";
		}
		
		Matcher matcher = Pattern.compile(findString).matcher(contentToSearch);
		
		if (searchForward) {
			// Forward search
			if (matcher.find(offset)) {
				selectAndShowRPTRegion(text, matcher.start(), matcher.end());
				
				return matcher.start();
			} else {
				return -1;
			}
		} else {
			if (offset == 0) {
				// There can't be something before index 0 start searching at
				// the end of the document
				offset = contentToSearch.length();
			}
			
			// Backward search
			if (!matcher.find() || matcher.start() >= offset) {
				return -1;
			}
			
			MatchResult prevResult = matcher.toMatchResult();
			
			while (matcher.start() < offset) {
				prevResult = matcher.toMatchResult();
				
				if (!matcher.find()) {
					break;
				}
			}
			
			selectAndShowRPTRegion(text, prevResult.start(), prevResult.end());
			
			return prevResult.start();
		}
	}
	
	/**
	 * Selects the given range in the given text widget and makes sure the
	 * corresponding area is shown to the user
	 * 
	 * @param rptTextWidget
	 *            The wisget to select
	 * @param startOffset
	 *            The start of the selection
	 * @param endOffset
	 *            The end of the selection
	 */
	private void selectAndShowRPTRegion(StyledText rptTextWidget, int startOffset, int endOffset) {
		rptTextWidget.setSelection(startOffset, endOffset);
		
		rptTextWidget.showSelection();
	}
	
	@Override
	public void replaceSelection(String text, boolean regExReplace) {
	}
	
}
