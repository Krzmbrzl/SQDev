package raven.sqdev.editors.stringTableEditor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.FocusCellOwnerDrawHighlighter;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TableViewerFocusCellManager;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.MultiPageEditorPart;
import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.interfaces.IUpdateListener;
import raven.sqdev.misc.TreeUtils;
import raven.sqdev.pluginManagement.ResourceManager;
import raven.sqdev.util.SQDevInfobox;
import raven.sqdev.util.SQDevPreferenceUtil;

public class StringTableEditor extends MultiPageEditorPart {
	
	/**
	 * Indicates that the column this data is applied to represents a key and
	 * not a <code>Language</code>
	 */
	public static final String KEY_COLUMN = "raven.sqdev.StringTableEditor.keyRow";
	/**
	 * Indicates that the column this data is applied to represents the add
	 * column
	 */
	public static final String ADD_COLUMN = "raven.sqdev.StringTableEditor.addColumn";
	/**
	 * The TreeItem that is used for adding packages
	 */
	public static final String ADD_ITEM = "raven.sqdev.StringTableEditor.addItem";
	/**
	 * Indicates that the column this data is applied to represents the remove
	 * column
	 */
	public static final String REMOVE_COLUMN = "raven.sqdev.StringTableEditor.removeRow";
	/**
	 * Indicates that this row is an "add" row
	 */
	public static final String ADD_ROW = "raven.sqdev.StringTableEditor.addRow";
	/**
	 * Inicates an empty row
	 */
	public static final String EMPTY_ROW = "raven.sqdev.StringTableEditor.emptyRow";
	
	/**
	 * The editor used for editing the generated output
	 */
	protected StringTableXMLEditor editor;
	/**
	 * Indicates whether this editor is in dirty state
	 */
	protected boolean isDirty;
	/**
	 * The index of the user interface for editing the stringTable
	 */
	protected int GUI_INDEX = -1;
	/**
	 * The index of the actual XML editor
	 */
	protected int EDITOR_INDEX = -1;
	/**
	 * The <code>TableViewer</code> for displaying the table
	 */
	private TableViewer viewer;
	/**
	 * The tree showing the available packages and container
	 */
	private Tree packageTree;
	/**
	 * A list of all packages that were expanded in the UI-packageTree
	 */
	private List<StringTablePackage> expandedPackages;
	/**
	 * The object that was selected last in the UI-packageTree
	 */
	private Object lastSelectedObjectData;
	/**
	 * The plus icon image
	 */
	private Image plusIcon;
	/**
	 * The remove icon image
	 */
	private Image removeIcon;
	/**
	 * The minus icon image
	 */
	private Image minusIcon;
	/**
	 * A map containing all displayed languages and the index of the respective
	 * column
	 */
	private Map<Language, Integer> displayedLanguages;
	/**
	 * The context menu for adding a new row
	 */
	private Menu addRowMenu;
	/**
	 * The input list for the table
	 */
	private List<Object> tableInputList;
	/**
	 * The container that is currently edited in the table
	 */
	private StringTableContainer currentTableContainer;
	
	/**
	 * A list of packages that exist in this stringTable
	 */
	private List<StringTablePackage> packageList;
	/**
	 * A list of "old" packages
	 */
	private List<StringTablePackage> oldPackageList;
	
	
	public StringTableEditor() {
		super();
		
		displayedLanguages = new HashMap<Language, Integer>();
		tableInputList = new ArrayList<Object>();
		packageList = new ArrayList<StringTablePackage>();
		oldPackageList = new ArrayList<StringTablePackage>();
		
		addPageChangedListener(new IPageChangedListener() {
			
			@Override
			public void pageChanged(PageChangedEvent event) {
				if (event.getSelectedPage() instanceof BasicCodeEditor) {
					// The XML editor was selected
					// refresh parse
					editor.parseInput();
					
					saveTreeState();
				} else {
					if (event.getSelectedPage() instanceof SashForm) {
						// the GUI/table was selected
						if (!editor.isValid()) {
							// don't allow switching editor when there are
							// errors
							setActivePage(EDITOR_INDEX);
							
							SQDevInfobox info = new SQDevInfobox(
									"Can't change to UI as there are errors in the source code!",
									SWT.ICON_WARNING);
							
							info.open(false);
							
							return;
						}
						
						List<StringTablePackage> packages = editor
								.getPackageList();
						
						if (packages.isEmpty()) {
							setPackages(null);
						} else {
							setTableInput(null);
							setPackages(packages);
							
							restoreTreeState();
						}
					}
				}
			}
		});
	}
	
	@Override
	protected void createPages() {
		createUIPage();
		createXMLEditorPage();
	}
	
	/**
	 * Creates the page that offers the user the UI for editing the stringtable
	 */
	private void createUIPage() {
		// Create Basic SahsForm
		SashForm sashForm = new SashForm(getContainer(), SWT.HORIZONTAL);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		// The package and container tree
		createPackageTree(sashForm);
		
		// create table
		createTableViewer(sashForm);
		
		updateTableInput();
		
		sashForm.setWeights(new int[] { 1, 4 });
		
		GUI_INDEX = addPage(sashForm);
		setPageText(GUI_INDEX, "GUI");
	}
	
	/**
	 * Creates the tree for displaying packages and composites
	 * 
	 * @param parent
	 *            The parent to use
	 */
	private void createPackageTree(Composite parent) {
		packageTree = new Tree(parent, SWT.SINGLE);
		expandedPackages = new ArrayList<StringTablePackage>();
		
		TreeEditor editor = new TreeEditor(packageTree);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		editor.grabVertical = true;
		editor.minimumWidth = 50;
		
		packageTree.addListener(SWT.MouseUp, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				if (packageTree.getSelection() == null
						|| packageTree.getSelection().length == 0) {
					return;
				}
				
				TreeItem selectedItem = packageTree.getSelection()[0];
				
				// just check whether the mouse is at the correct height
				Rectangle area = selectedItem.getBounds();
				area.x = 0;
				area.width = packageTree.getSize().x;
				
				if (selectedItem == null
						|| !area.contains(new Point(event.x, event.y))) {
					// mouse event didn't occure on the respective TreeItem
					packageTree.deselectAll();
					
					return;
				}
				
				if (event.button == 1) {
					// left mouseclick
					Object data = selectedItem.getData();
					
					if (data == null) {
						return;
					}
					
					if (data instanceof StringTableContainer) {
						// display currently selected container
						setTableInput((StringTableContainer) data);
					} else {
						if (data.equals(ADD_ITEM)) {
							StringTablePackage newPackage = new StringTablePackage(
									"New package");
							
							packageList.add(packageList.size(), newPackage);
							
							updateTree();
							
							packageChanged(newPackage);
						}
					}
				} else {
					if (event.button != 3) {
						return;
					}
					// right mouseClick
					
					Menu contextMenu = new Menu(packageTree);
					
					if (selectedItem == null
							|| selectedItem.getData() == null) {
						return;
					}
					
					if (selectedItem.getData() instanceof StringTablePackage) {
						addPackageContextItems(contextMenu,
								(StringTablePackage) selectedItem.getData());
					} else {
						if (selectedItem
								.getData() instanceof StringTableContainer) {
							addContainerContextItems(contextMenu,
									(StringTableContainer) selectedItem
											.getData(),
									(StringTablePackage) selectedItem
											.getParentItem().getData());
						}
					}
					
					contextMenu.setVisible(true);
				}
			}
		});
		
		packageTree.addListener(SWT.MouseDoubleClick, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				// Clean up any previous editor control
				Control oldEditor = editor.getEditor();
				if (oldEditor != null) {
					oldEditor.dispose();
				}
				
				if (packageTree.getSelection() == null
						|| packageTree.getSelection().length == 0) {
					return;
				}
				
				// Identify the selected row
				TreeItem item = packageTree.getSelection()[0];
				if (item == null) {
					return;
				}
				
				// The control that will be the editor must be a child of the
				// Tree
				Text newEditor = new Text(packageTree, SWT.NONE);
				newEditor.setText(item.getText());
				
				newEditor.addKeyListener(new KeyAdapter() {
					
					@Override
					public void keyPressed(KeyEvent e) {
						if (e.character == '\n' || e.character == '\r') {
							Text textField = (Text) editor.getEditor();
							
							String text = textField.getText();
							
							if (!text.isEmpty() && item.getData() != null) {
								
								editor.getItem().setText(textField.getText());
								
								Object data = item.getData();
								
								if (data instanceof StringTableContainer) {
									((StringTableContainer) data).setName(text);
								} else {
									if (data instanceof StringTablePackage) {
										((StringTablePackage) data)
												.setName(text);
									}
								}
							}
							
							newEditor.dispose();
						}
					}
				});
				
				newEditor.addFocusListener(new FocusAdapter() {
					
					@Override
					public void focusLost(FocusEvent e) {
						newEditor.dispose();
					}
				});
				
				newEditor.selectAll();
				newEditor.setFocus();
				editor.setEditor(newEditor, item);
			}
		});
		
		updateTree();
	}
	
	/**
	 * Saves the current state (selection and expanded items) of the packageTree
	 */
	private void saveTreeState() {
		// save the expanded tree nodes
		expandedPackages.clear();
		for (TreeItem currentItem : packageTree.getItems()) {
			if (currentItem.getExpanded()) {
				expandedPackages
						.add((StringTablePackage) currentItem.getData());
			}
		}
		
		// save selected TreeItem
		if (packageTree.getSelection().length > 0) {
			lastSelectedObjectData = packageTree.getSelection()[0].getData();
		} else {
			lastSelectedObjectData = null;
		}
	}
	
	/**
	 * Restores the state of the packageTree (selection + expanded items)
	 */
	private void restoreTreeState() {
		// restore expanded state of packages
		for (TreeItem currentItem : packageTree.getItems()) {
			if (expandedPackages.contains(currentItem.getData())) {
				currentItem.setExpanded(true);
			}
		}
		
		// restore selection
		if (lastSelectedObjectData != null) {
			TreeItem lastSelectedItem = TreeUtils
					.findTreeItemWithData(packageTree, lastSelectedObjectData);
			
			if (lastSelectedItem != null) {
				packageTree.setSelection(lastSelectedItem);
			}
			
			if (lastSelectedItem != null && lastSelectedItem
					.getData() instanceof StringTableContainer) {
				setTableInput(
						(StringTableContainer) lastSelectedItem.getData());
			} else {
				setTableInput(null);
			}
		}
	}
	
	/**
	 * Adds the MenuItems for the package context menu
	 * 
	 * @param menu
	 *            The menu to add the items to
	 * @param pkg
	 *            The package that is affected
	 */
	private void addPackageContextItems(Menu menu, StringTablePackage pkg) {
		// add container option
		MenuItem addContainerItem = new MenuItem(menu, SWT.PUSH);
		addContainerItem.setText("Add Container");
		addContainerItem.setImage(getPlusIconImage());
		
		addContainerItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				StringTableContainer newContainer = new StringTableContainer(
						"New container");
				StringTableKey newKey = new StringTableKey("New_Key");
				newKey.setString(Language.ORIGINAL, "");
				newContainer.addKey(newKey);
				
				configureContainerListener(newContainer);
				
				pkg.addContainer(newContainer);
				
				updateTree();
				changed();
			}
		});
		
		// add separator
		new MenuItem(menu, SWT.SEPARATOR);
		
		// remove option
		MenuItem removeItem = new MenuItem(menu, SWT.PUSH);
		removeItem.setText("Delete package \"" + pkg.getName() + "\"");
		removeItem.setImage(getRemoveIconImage());
		
		removeItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				pkg.removeAllUpdateListener();
				
				packageList.remove(pkg);
				
				for (StringTableContainer currentContainer : pkg
						.getContainer()) {
					if (currentContainer.equals(currentTableContainer)) {
						// don't display a container that does no longer exist
						setTableInput(null);
						break;
					}
				}
				
				if (packageList.size() == 0) {
					StringTablePackage defaultPackage = new StringTablePackage(
							"Default package");
					packageList.add(defaultPackage);
				}
				
				updateTree();
			}
		});
	}
	
	/**
	 * Adds the MenuItems for the container context menu
	 * 
	 * @param menu
	 *            The menu to add the items to
	 * @param container
	 *            The container that is affected
	 * @param pkg
	 *            The package of the container
	 */
	private void addContainerContextItems(Menu menu,
			StringTableContainer container, StringTablePackage pkg) {
		MenuItem removeItem = new MenuItem(menu, SWT.PUSH);
		removeItem.setText("Delete container \"" + container.getName() + "\"");
		removeItem.setImage(getRemoveIconImage());
		
		removeItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				container.removeAllUpdateListener();
				
				pkg.removeContainer(container);
				
				if (currentTableContainer.equals(container)) {
					setTableInput(null);
				}
				
				updateTree();
			}
		});
	}
	
	/**
	 * Updates the input of the table according to {@link #tableInputList}
	 */
	private void updateTableInput() {
		if (viewer != null) {
			// don't show the single update steps
			viewer.getTable().setRedraw(false);
			
			// reset used languages
			displayedLanguages.clear();
			
			// remove old columns
			for (TableColumn currentColumn : viewer.getTable().getColumns()) {
				currentColumn.dispose();
			}
			
			if (tableInputList.isEmpty()) {
				viewer.getTable().clearAll();
				viewer.getTable().setEnabled(false);
				viewer.getTable().setRedraw(true);
				return;
			}
			
			createBasicColumns();
			
			List<Language> languages = new ArrayList<Language>();
			
			// find out the configured languages
			for (Object current : tableInputList) {
				if (current instanceof StringTableKey) {
					for (Language currentLanguage : ((StringTableKey) current)
							.getLanguages()) {
						if (!languages.contains(currentLanguage)) {
							languages.add(currentLanguage);
						}
					}
				}
			}
			
			// show the needed columns
			for (Language currentLanguage : languages) {
				addLanguageColumn(currentLanguage);
			}
			
			viewer.setInput(
					tableInputList.toArray(new Object[tableInputList.size()]));
			
			// update table
			viewer.getTable().setRedraw(true);
			if (!viewer.getTable().isEnabled()) {
				viewer.getTable().setEnabled(true);
				viewer.getTable().update();
			}
		}
	}
	
	/**
	 * Creates and configures the TableViewer used to display the data
	 * 
	 * @param parent
	 *            The parent composite to use
	 */
	private void createTableViewer(Composite parent) {
		viewer = new TableViewer(parent,
				SWT.SINGLE | SWT.FULL_SELECTION | SWT.HIDE_SELECTION);
		
		viewer.getTable().setLinesVisible(true);
		viewer.getTable().setHeaderVisible(true);
		
		viewer.setContentProvider(new StringTableContentProvider());
		viewer.setLabelProvider(new LabelProvider());
		
		viewer.getTable().addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				Widget item = e.item;
				
				if (item instanceof TableItem) {
					Object data = ((TableItem) item).getData();
					
					Point mousePos = PlatformUI.getWorkbench().getDisplay()
							.getCursorLocation();
					Point relPos = PlatformUI.getWorkbench().getDisplay()
							.getFocusControl().toControl(mousePos);
					
					ViewerCell cell = viewer.getCell(relPos);
					
					if (data.equals(ADD_ROW)) {
						if (cell == null) {
							return;
						}
						
						if (viewer.getTable().getColumn(cell.getColumnIndex())
								.getData().equals(KEY_COLUMN)) {
							// only open when clicked on the plus cell
							openAddRowMenu();
						}
					} else {
						if (cell == null || viewer.getTable()
								.getColumn(cell.getColumnIndex()) == null) {
							return;
						}
						
						if (data instanceof StringTableKey && viewer.getTable()
								.getColumn(cell.getColumnIndex()).getData()
								.equals(REMOVE_COLUMN)) {
							// open when clicked on a key row
							openRemoveRowMenu((StringTableKey) data);
						}
					}
				}
			}
		});
		
		TableViewerFocusCellManager focusCellManager = new TableViewerFocusCellManager(
				viewer, new FocusCellOwnerDrawHighlighter(viewer));
		
		ColumnViewerEditorActivationStrategy activationSupport = new ColumnViewerEditorActivationStrategy(
				viewer) {
			@Override
			protected boolean isEditorActivationEvent(
					ColumnViewerEditorActivationEvent event) {
				// Enable editor only with mouse double click
				if (event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION) {
					EventObject source = event.sourceEvent;
					if (source instanceof MouseEvent
							&& ((MouseEvent) source).button == 3)
						return false;
					
					return true;
				}
				
				return false;
			}
		};
		
		TableViewerEditor.create(viewer, focusCellManager, activationSupport,
				ColumnViewerEditor.TABBING_HORIZONTAL
						| ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
						| ColumnViewerEditor.TABBING_VERTICAL
						| ColumnViewerEditor.KEYBOARD_ACTIVATION);
		
		updateTableInput();
	}
	
	/**
	 * Creates the basic columns for the specified languages
	 */
	private void createBasicColumns() {
		// key column
		TableViewerColumn keyColumn = new TableViewerColumn(viewer, SWT.NONE);
		keyColumn.getColumn().setText("Key");
		keyColumn.getColumn()
				.setToolTipText("The key for usage in scripts or config");
		keyColumn.getColumn().setWidth(100);
		keyColumn.getColumn().setResizable(true);
		keyColumn.getColumn().setData(KEY_COLUMN);
		
		keyColumn.setLabelProvider(new CellLabelProvider() {
			
			@Override
			public void update(ViewerCell cell) {
				Object data = cell.getViewerRow().getItem().getData();
				
				// remove possible remaining image
				cell.setImage(null);
				
				if (data == null) {
					cell.setText("<Null value>");
					return;
				}
				
				if (data instanceof String) {
					String input = (String) data;
					
					if (input.equals(ADD_ROW)) {
						cell.setImage(getPlusIconImage());
						
						return;
					}
					
					if (input.equals(EMPTY_ROW)) {
						// nothing to display in an empty row
						return;
					}
					
					cell.setText("<Unexpected String value>");
					
					return;
				}
				
				if (data instanceof StringTableKey) {
					cell.setText(((StringTableKey) data).getKey());
				} else {
					cell.setText("Inappropriate data in row");
				}
			}
		});
		keyColumn.setEditingSupport(new StringTableCellEditingSupport(viewer));
		
		
		// column for adding a language
		TableViewerColumn addColumn = new TableViewerColumn(viewer, SWT.NONE);
		addColumn.getColumn().setToolTipText("Adds a language column");
		addColumn.getColumn().setResizable(false);
		addColumn.getColumn().setData(ADD_COLUMN);
		addColumn.getColumn().setImage(getPlusIconImage());
		addColumn.getColumn().pack();
		
		addColumn.setLabelProvider(new StringTableCellLabelProvider(viewer));
		
		addColumn.getColumn().addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				openAddColumnPopupMenu();
			}
		});
		
		
		TableViewerColumn removeRowColumn = new TableViewerColumn(viewer,
				SWT.NONE);
		removeRowColumn.getColumn().setResizable(false);
		removeRowColumn.getColumn().setWidth(50);
		removeRowColumn.getColumn().setData(REMOVE_COLUMN);
		
		removeRowColumn.setLabelProvider(new CellLabelProvider() {
			
			@Override
			public void update(ViewerCell cell) {
				if (!cell.getItem().getData().equals(ADD_ROW)
						&& !cell.getItem().getData().equals(EMPTY_ROW)) {
					cell.setImage(getMinusIcon());
				}
			}
		});
	}
	
	/**
	 * Adds a column for the given language
	 * 
	 * @param language
	 *            The <code>Language</code> the column should be created for
	 */
	private void addLanguageColumn(Language language) {
		int index = viewer.getTable().getColumnCount() - 2;
		
		Language[] langs = displayedLanguages.keySet()
				.toArray(new Language[displayedLanguages.keySet().size()]);
		
		Arrays.sort(langs);
		
		for (int i = 0; i < langs.length; i++) {
			Language currentLanguage = langs[i];
			
			if (currentLanguage.compareTo(language) > 0) {
				index = index - ((langs.length) - i);
				break; // Place has been found
			}
		}
		
		// store index for this language
		displayedLanguages.put(language, index);
		
		// add the column before the "add" column
		TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE,
				index);
		
		column.getColumn().setText(language.toString());
		column.getColumn().setToolTipText(language.getTooltip());
		column.getColumn().setWidth(200);
		column.getColumn().setResizable(true);
		column.getColumn().setMoveable(false);
		column.getColumn().setData(language);
		
		column.setEditingSupport(new StringTableCellEditingSupport(viewer));
		column.setLabelProvider(new StringTableCellLabelProvider(viewer));
		
		
		// add context menu
		column.getColumn().addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				Menu contextMenu = new Menu(viewer.getTable());
				
				MenuItem remove = new MenuItem(contextMenu, SWT.PUSH);
				remove.setText("Remove column");
				remove.setImage(getRemoveIconImage());
				
				remove.addSelectionListener(new SelectionAdapter() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						if (SQDevPreferenceUtil
								.promptUserValidationForDeletion()) {
							// Only prompt if the user set up the plugin like
							// this
							SQDevInfobox info = new SQDevInfobox(
									"This will delete the language " + language
											+ " from this stringTable (and all defined Strings for it)!\n\n"
											+ "Do you want to proceed",
									SWT.ICON_QUESTION | SWT.NO | SWT.YES);
							
							if (info.open() != SWT.YES) {
								// abort
								return;
							}
						}
						
						// dispose respective column
						viewer.getTable().getColumn(getColumnIndex(language))
								.dispose();
						
						// update input
						for (Object current : tableInputList) {
							if (current instanceof StringTableKey) {
								((StringTableKey) current).remove(language);
							}
						}
						
						displayedLanguages.remove(language);
						
						updateColumnIndices();
					}
				});
				
				contextMenu.setVisible(true);
			}
		});
		
		// add the respective language to the keys if they don't contain it
		// already
		for (Object current : tableInputList) {
			if (current instanceof StringTableKey) {
				StringTableKey currentKey = ((StringTableKey) current);
				
				if (!currentKey.containsString(language)) {
					currentKey.setString(language, "");
				}
			}
		}
	}
	
	/**
	 * Gets the plus icon as an image
	 */
	private Image getPlusIconImage() {
		if (plusIcon == null) {
			InputStream plusStream = ResourceManager.getManager()
					.getInternalResourceStream(ResourceManager.PLUS_ICON);
			
			plusIcon = new Image(PlatformUI.getWorkbench().getDisplay(),
					plusStream);
		}
		
		return plusIcon;
	}
	
	/**
	 * Gets the remove icon as an image
	 */
	private Image getRemoveIconImage() {
		if (removeIcon == null) {
			InputStream removeStream = ResourceManager.getManager()
					.getInternalResourceStream(ResourceManager.REMOVE_ICON);
			
			removeIcon = new Image(PlatformUI.getWorkbench().getDisplay(),
					removeStream);
		}
		
		return removeIcon;
	}
	
	/**
	 * Gets the minus icon as an image
	 */
	private Image getMinusIcon() {
		if (minusIcon == null) {
			InputStream minusStream = ResourceManager.getManager()
					.getInternalResourceStream(ResourceManager.MINUS_ICON);
			
			minusIcon = new Image(PlatformUI.getWorkbench().getDisplay(),
					minusStream);
		}
		
		return minusIcon;
	}
	
	/**
	 * Opens the context menu for adding a column
	 */
	private void openAddColumnPopupMenu() {
		Menu menu = new Menu(viewer.getTable());
		
		for (Language language : Language.values()) {
			if (!displayedLanguages.containsKey(language)) {
				MenuItem languageItem = new MenuItem(menu, SWT.PUSH);
				languageItem.setText("Add " + language.toString());
				languageItem.setImage(getPlusIconImage());
				
				languageItem.addSelectionListener(new SelectionAdapter() {
					
					@Override
					public void widgetSelected(SelectionEvent e) {
						addLanguageColumn(language);
					}
				});
			}
		}
		
		if (menu.getItemCount() == 0) {
			MenuItem item = new MenuItem(menu, SWT.PUSH);
			item.setText("No further languages available in ArmA...");
			
			item.setEnabled(false);
		}
		
		menu.setVisible(true);
	}
	
	private void openAddRowMenu() {
		if (addRowMenu == null) {
			addRowMenu = new Menu(viewer.getTable());
			
			MenuItem addRow = new MenuItem(addRowMenu, SWT.PUSH);
			addRow.setText("Add new key");
			addRow.setImage(getPlusIconImage());
			
			addRow.addSelectionListener(new SelectionAdapter() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					StringTableKey newKey = new StringTableKey("My_Key");
					
					tableInputList.add(tableInputList.size() - 1, newKey);
					currentTableContainer.addKey(newKey);
					
					// configure the new key with all displayed languages
					Iterator<Language> it = displayedLanguages.keySet()
							.iterator();
					while (it.hasNext()) {
						newKey.setString(it.next(), "");
					}
					
					configureKeyListener(newKey);
					
					updateTableInput();
					changed();
				}
			});
		}
		
		addRowMenu.setVisible(true);
	}
	
	/**
	 * Opens the remove row meu for the given key row
	 * 
	 * @param key
	 *            The key corresponding to the row to delete
	 */
	private void openRemoveRowMenu(StringTableKey key) {
		Menu removeMenu = new Menu(viewer.getTable());
		
		MenuItem removeItem = new MenuItem(removeMenu, SWT.PUSH);
		removeItem.setText("Remove key \"" + key.getKey() + "\"");
		removeItem.setImage(getRemoveIconImage());
		
		removeItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				key.removeAllUpdateListener();
				tableInputList.remove(key);
				currentTableContainer.removeKey(key);
				
				updateTableInput();
			}
		});
		
		
		removeMenu.setVisible(true);
	}
	
	/**
	 * Updates the indices of the displayed columns
	 */
	private void updateColumnIndices() {
		TableColumn[] columns = viewer.getTable().getColumns();
		int[] indices = viewer.getTable().getColumnOrder();
		
		for (int i = 0; i < columns.length; i++) {
			TableColumn currentColumn = columns[i];
			
			Object data = currentColumn.getData();
			
			if (data instanceof Language) {
				displayedLanguages.put((Language) data, indices[i]);
			}
		}
	}
	
	/**
	 * Gets the index for the column that is associated with the given language
	 * 
	 * @param language
	 *            The <code>Language</code> whose column index should be
	 *            obtained. The language has to be displayed at the time this
	 *            method is called
	 */
	private int getColumnIndex(Language language) {
		Assert.isTrue(displayedLanguages.containsKey(language));
		
		return displayedLanguages.get(language).intValue();
	}
	
	/**
	 * Updates the {@link #packageTree} according to the {@link #packageList}
	 */
	private void updateTree() {
		if (packageTree != null) {
			saveTreeState();
			
			packageTree.removeAll();
			
			for (StringTablePackage currentPackage : packageList) {
				// create package node
				TreeItem packageItem = new TreeItem(packageTree, SWT.NONE);
				packageItem.setText(currentPackage.getName());
				packageItem.setData(currentPackage);
				
				for (StringTableContainer currentContainer : currentPackage
						.getContainer()) {
					TreeItem containerItem = new TreeItem(packageItem,
							SWT.NONE);
					containerItem.setText(currentContainer.getName());
					containerItem.setData(currentContainer);
				}
			}
			
			// add "add" item
			TreeItem addItem = new TreeItem(packageTree, SWT.NONE);
			addItem.setImage(getPlusIconImage());
			addItem.setData(ADD_ITEM);
			
			if (packageTree.getItemCount() > 1) {
				packageTree.select(packageTree.getItem(0));
			}
			
			packageTree.update();
			
			restoreTreeState();
		}
	}
	
	/**
	 * Sets the input for the table of this editor
	 * 
	 * @param input
	 *            The <code>StringTableContainer</code> to use as an input. Can
	 *            be <code>null</code> in order to clear the table
	 */
	public void setTableInput(StringTableContainer input) {
		this.tableInputList.clear();
		currentTableContainer = input;
		
		if (input != null) {
			this.tableInputList.addAll(input.getKeys());
			this.tableInputList.add(ADD_ROW);
		}
		
		updateTableInput();
	}
	
	/**
	 * Sets the packages for this editor
	 * 
	 * @param packages
	 *            The <code>StringTablePackages</code> for this editor
	 */
	public void setPackages(Collection<StringTablePackage> packages) {
		packageList.clear();
		
		if (packages == null) {
			packages = new ArrayList<StringTablePackage>();
			StringTablePackage defaultPackage = new StringTablePackage(
					"Default package");
			
			packages.add(defaultPackage);
		}
		
		packageList.addAll(packages);
		
		boolean isSelectionContained = false;
		
		for (StringTablePackage currentPackage : packageList) {
			configurePackageListener(currentPackage);
			
			if (!isSelectionContained
					&& currentPackage.contains(currentTableContainer)) {
				isSelectionContained = true;
			}
		}
		
		if (!isSelectionContained) {
			setTableInput(null);
		}
		
		updateTree();
		changed();
	}
	
	/**
	 * Adds the needed <code>IUpdateListener</code> to the given package, it's
	 * container and the keys contained in these container
	 * 
	 * @param pkg
	 *            The <code>StringTablePackage</code> to configure
	 */
	private void configurePackageListener(StringTablePackage pkg) {
		pkg.addUpdateListener(new IUpdateListener() {
			
			@Override
			public void updated() {
				packageChanged(pkg);
			}
		});
		
		for (StringTableContainer currentContainer : pkg.getContainer()) {
			configureContainerListener(currentContainer);
		}
	}
	
	/**
	 * Adds needed <code>IUpdateListener</code> to the given container and all
	 * of it's keys
	 * 
	 * @param container
	 *            The <code>StringTableContainer</code> to configure
	 */
	private void configureContainerListener(StringTableContainer container) {
		container.addUpdateListener(new IUpdateListener() {
			
			@Override
			public void updated() {
				containerChanged(container);
			}
		});
		
		for (StringTableKey currentKey : container.getKeys()) {
			configureKeyListener(currentKey);
		}
	}
	
	/**
	 * Adds the needed <code>IUpdateListener</code> to the given key
	 * 
	 * @param key
	 *            The <code>StringTableKey</code> to configure
	 */
	private void configureKeyListener(StringTableKey key) {
		key.addUpdateListener(new IUpdateListener() {
			
			@Override
			public void updated() {
				keyChanged(key);
			}
		});
	}
	
	/**
	 * Gets called when a package has changed
	 * 
	 * @param pkg
	 *            The changed package
	 */
	private void packageChanged(StringTablePackage pkg) {
		System.out.println("Package \"" + pkg.getName() + "\" changed");
		changed();
	}
	
	/**
	 * Gets called when a container has changed
	 * 
	 * @param container
	 *            The changed container
	 */
	private void containerChanged(StringTableContainer container) {
		System.out.println("Container \"" + container.getName() + "\" changed");
		changed();
	}
	
	/**
	 * Gets called whenever a key changed
	 * 
	 * @param key
	 *            The changed key
	 */
	private void keyChanged(StringTableKey key) {
		System.out.println("Key \"" + key.getKey() + "\" changed");
		changed();
	}
	
	/**
	 * Gets called whenever the UI part of this editor has changed
	 */
	private void changed() {
		if (!oldPackageList.equals(packageList)) {
			if (!oldPackageList.isEmpty()) {
				isDirty = true;
				syncXMLPresentation();
			}
			
			oldPackageList.clear();
			// update old package list
			for (StringTablePackage currentPackage : packageList) {
				oldPackageList.add(currentPackage.clone());
			}
		}
	}
	
	/**
	 * Creates the page with the XML editor
	 */
	private void createXMLEditorPage() {
		try {
			editor = new StringTableXMLEditor();
			
			EDITOR_INDEX = addPage(editor, getEditorInput());
			setPageText(EDITOR_INDEX, "XML");
			
			// syncXMLPresentation();
		} catch (PartInitException e) {
			throw new SQDevCoreException("Couldn't create stringTableEditor",
					e);
		}
	}
	
	/**
	 * Syncs the XML representation to the current state of the GUI
	 */
	protected void syncXMLPresentation() {
		if (editor == null) {
			return;
		}
		
		// the editor was selected
		IDocument editorDocument = editor.getBasicProvider()
				.getDocument(editor.getEditorInput());
		
		IEditorInput input = getEditorInput();
		
		String projectName = "MyProject";
		
		if (input instanceof IFileEditorInput) {
			IProject project = ((IFileEditorInput) input).getFile()
					.getProject();
			
			if (project != null) {
				projectName = project.getName();
				
			}
		}
		
		StringBuilder builder = new StringBuilder(
				"<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n");
		builder.append("<Project name=\"" + projectName + "\">\n");
		
		for (StringTablePackage currentPackage : packageList) {
			builder.append(currentPackage.getXMLRepresentation() + "\n");
		}
		
		String editorInput = builder.toString().trim().replace("\n", "\n\t");
		
		editorDocument
				.set(editorInput.replaceFirst("\n\t", "\n") + "\n</Project>");
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		editor.doSave(monitor);
	}
	
	@Override
	public void doSaveAs() {
		editor.doSaveAs();
	}
	
	@Override
	public boolean isSaveAsAllowed() {
		return editor.isSaveAsAllowed();
	}
	
}
