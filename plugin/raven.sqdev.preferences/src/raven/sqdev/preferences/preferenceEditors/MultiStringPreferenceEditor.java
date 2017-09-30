package raven.sqdev.preferences.preferenceEditors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import raven.sqdev.misc.SQDevPreferenceUtil;
import raven.sqdev.pluginManagement.ResourceManager;
import raven.sqdev.preferences.pages.ISQDevPreferencePage;

/**
 * This preference editor is used to manage the storage of multiple Strings in
 * one preference.
 * 
 * @author Raven
 *
 */
public class MultiStringPreferenceEditor extends AbstractSQDevPreferenceEditor {
	
	/**
	 * The maximum amount of lines being displayed without a scrollbar in the
	 * text fields of this editor
	 */
	public static final int MAX_LINE_DISPLAY = 3;
	
	/**
	 * The delete icon
	 */
	private static Image deleteIcon;
	/**
	 * The plus icon
	 */
	private static Image plusIcon;
	
	/**
	 * The value of this editor
	 */
	private String editorValue;
	
	/**
	 * Creates a new <code>SQDevPreferenceEditor</code><br>
	 * <b>This constructor can only be used if the given container is an
	 * instance of <code>ISQDevPreferenceComposite</code> or one of it's parents
	 * is. </b>
	 * 
	 * @param preferenceKey
	 *            The key of the preference to work on
	 * @param labelText
	 *            The text of the label
	 * @param container
	 *            The container the GUI elements should be placed in
	 */
	public MultiStringPreferenceEditor(String preferenceKey, String labelText,
			Composite container) {
		super(preferenceKey, labelText, container);
	}
	
	/**
	 * Creates a new <code>SQDevPreferenceEditor</code><br>
	 * <b>This constructor can only be used if the given container is an
	 * instance of <code>ISQDevPreferenceComposite</code> or one of it's parents
	 * is. </b>
	 * 
	 * @param preferenceKey
	 *            The key of the preference to work on
	 * @param labelText
	 *            The text of the label
	 * @param tooltip
	 *            The tooltip that will be displayed on the editor's preference
	 *            value-field
	 * @param container
	 *            The container the GUI elements should be placed in
	 */
	public MultiStringPreferenceEditor(String preferenceKey, String labelText, String tooltip,
			Composite container) {
		super(preferenceKey, labelText, tooltip, container);
	}
	
	/**
	 * Creates a new <code>SQDevPreferenceEditor</code>
	 * 
	 * @param preferenceKey
	 *            The key of the preference to work on
	 * @param labelText
	 *            The text of the label
	 * @param container
	 *            The container the GUI elements should be placed in
	 * @param page
	 *            The <code>ISQDevPreferencePage</code> this editor is apllied
	 *            to
	 */
	public MultiStringPreferenceEditor(String preferenceKey, String labelText, Composite container,
			ISQDevPreferencePage page) {
		super(preferenceKey, labelText, container, page);
	}
	
	/**
	 * Creates a new <code>SQDevPreferenceEditor</code>
	 * 
	 * @param preferenceKey
	 *            The key of the preference to work on
	 * @param labelText
	 *            The text of the label
	 * @param tooltip
	 *            The tooltip that will be displayed on the editor's preference
	 *            value-field
	 * @param container
	 *            The container the GUI elements should be placed in
	 * @param page
	 *            The <code>ISQDevPreferencePage</code> this editor is apllied
	 *            to
	 */
	public MultiStringPreferenceEditor(String preferenceKey, String labelText, String tooltip,
			Composite container, ISQDevPreferencePage page) {
		super(preferenceKey, labelText, tooltip, container, page);
	}
	
	
	@Override
	public boolean needsSave() {
		return willNeedSave(editorValue);
	}
	
	@Override
	public void doLoad() {
		load(getPreferenceStore().getString(getPreferenceKey()));
	}
	
	private void load(String content) {
		editorValue = content;
		
		evaluateInput();
		updateSaveStatus(editorValue);
	}
	
	@Override
	public void doLoadDefault() {
		load(getPreferenceStore().getDefaultString(getPreferenceKey()));
	}
	
	@Override
	public void evaluateInput() {
	}
	
	@Override
	public int getComponentCount() {
		return 2;
	}
	
	@Override
	public void createComponents(Composite container) {
		// create the nameLabel
		label = new Label(container, SWT.NONE);
		setLabelText(getLabelText());
		label.setToolTipText(getTooltip());
		
		Button openStringSelection = new Button(container, SWT.PUSH);
		openStringSelection.setToolTipText(getTooltip());
		openStringSelection.setText("See / Edit Values");
		
		openStringSelection.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				Control parent = container;
				
				while (!(parent instanceof Shell)) {
					parent = parent.getParent();
				}
				
				// open selection window
				openStringSelection((Shell) parent);
			}
		});
	}
	
	@Override
	public boolean willNeedSave(String content) {
		return !getPreferenceStore().getString(getPreferenceKey()).equals(content);
	}
	
	@Override
	public boolean doSave() {
		if (super.doSave()) {
			getPreferenceStore().setValue(getPreferenceKey(), editorValue);
			
			// update save status
			updateSaveStatus(editorValue);
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Opens the window in which the user can edit the values of the stored
	 * Strings
	 */
	private void openStringSelection(Shell parentShell) {
		Shell shell = new Shell(parentShell, SWT.TITLE | SWT.BORDER | SWT.RESIZE | SWT.CLOSE);
		shell.setText(getLabelText().substring(1, getLabelText().length() - 1));
		shell.setSize(400, 300);
		shell.setLayout(new GridLayout(1, true));
		
		// create container with actual fields to edit this editor's value
		ScrolledComposite scroller = new ScrolledComposite(shell, SWT.V_SCROLL);
		scroller.setExpandHorizontal(true);
		scroller.setExpandVertical(true);
		scroller.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Composite container = new Composite(scroller, SWT.NONE);
		scroller.setContent(container);
		
		container.setLayout(new GridLayout(2, false));
		
		class ContainerUpdater {
			public ContainerUpdater() {
				// Update container and adjust scroller
				container.pack(true);
				
				scroller.setMinSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			}
		}
		
		GridData deleteButtonData = new GridData(SWT.CENTER, SWT.CENTER, false, false);
		String deleteButtonTooltip = "Delete this entry";
		
		// load current values
		for (String currentString : editorValue.split(SQDevPreferenceUtil.STRING_SEPERATOR)) {
			
			if (currentString.isEmpty()) {
				continue;
			}
			
			// create text field + load value
			Text textField = createTextWidget(container);
			textField.setText(currentString);
			
			// Make sure text field does not get larger than intended
			if (textField.getLineCount() > MAX_LINE_DISPLAY) {
				((GridData) textField.getLayoutData()).heightHint = textField.getLineHeight()
						* MAX_LINE_DISPLAY;
				
				container.pack();
			}
			
			// create delete button
			Button deleteButton = new Button(container, SWT.PUSH);
			deleteButton.setImage(getDeleteIcon());
			deleteButton.setToolTipText(deleteButtonTooltip);
			deleteButton.setLayoutData(deleteButtonData);
			
			Point buttonSize = deleteButton.computeSize(SWT.DEFAULT, SWT.DEFAULT);
			Point textFieldSize = textField.computeSize(SWT.DEFAULT, SWT.DEFAULT);
			
			if (buttonSize.y > textFieldSize.y) {
				// make sure the button is not taller than the text field
				textFieldSize.y = buttonSize.y;
				
				textField.setSize(textFieldSize);
			}
			
			// configure button
			deleteButton.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseUp(MouseEvent e) {
					textField.dispose();
					deleteButton.dispose();
					
					// update the container
					new ContainerUpdater();
				}
			});
		}
		
		// add the add-Button
		Button addButton = new Button(container, SWT.PUSH);
		addButton.setImage(getPlusIcon());
		addButton.setToolTipText("Add a new entry");
		
		addButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				Text newText = createTextWidget(container);
				newText.moveAbove(addButton);
				
				Button newDeleteButton = new Button(container, SWT.PUSH);
				newDeleteButton.setImage(getDeleteIcon());
				newDeleteButton.setToolTipText(deleteButtonTooltip);
				newDeleteButton.moveAbove(addButton);
				
				// configure delete button
				newDeleteButton.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseUp(MouseEvent e) {
						newText.dispose();
						newDeleteButton.dispose();
						
						new ContainerUpdater();
					}
				});
				
				new ContainerUpdater();
			}
		});
		
		
		// create container with OK and Cancel button
		Composite bottom = new Composite(shell, SWT.NONE);
		bottom.setLayoutData(new GridData(SWT.FILL, SWT.DOWN, true, false));
		bottom.setLayout(new GridLayout(2, false));
		
		Composite spacer = new Composite(bottom, SWT.NONE);
		
		Composite subContainer = new Composite(bottom, SWT.NONE);
		subContainer.setLayoutData(new GridData(SWT.DEFAULT, SWT.DEFAULT, false, false));
		FillLayout subLayout = new FillLayout();
		subLayout.spacing = 4;
		subContainer.setLayout(subLayout);
		
		// Create OK button
		Button okButton = new Button(subContainer, SWT.PUSH);
		okButton.setText("OK");
		okButton.setToolTipText("Close this dialog and apply new values");
		
		// magnify button text size
		FontData buttonFontData = okButton.getFont().getFontData()[0];
		buttonFontData.setHeight((int) (buttonFontData.getHeight() * 1.3));
		Font buttonFont = new Font(PlatformUI.getWorkbench().getDisplay(), buttonFontData);
		
		okButton.setFont(buttonFont);
		
		
		okButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				StringBuilder builder = new StringBuilder();
				for (Control currentControl : container.getChildren()) {
					if (currentControl instanceof Text) {
						if (!((Text) currentControl).getText().isEmpty()) {
							builder.append(((Text) currentControl).getText());
							builder.append(SQDevPreferenceUtil.STRING_SEPERATOR);
						}
					}
				}
				
				// set new value
				load((builder.length() > 0) ? builder.toString().substring(0,
						builder.length() - SQDevPreferenceUtil.STRING_SEPERATOR.length()) : builder.toString());
				
				shell.dispose();
			}
		});
		
		// Create cancel button
		Button cancelButton = new Button(subContainer, SWT.PUSH);
		cancelButton.setText("Cancel");
		cancelButton.setToolTipText("Close this dialog and discard changes");
		
		cancelButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
			}
		});
		
		cancelButton.setFont(buttonFont);
		
		// set spacer layout data
		GridData spacerData = new GridData(SWT.FILL, SWT.DEFAULT, true, false);
		spacerData.heightHint = cancelButton.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
		spacer.setLayoutData(spacerData);
		
		
		shell.open();
	}
	
	/**
	 * Creates a <code>Teyt</code> that will be used to enter the values of this
	 * editor
	 * 
	 * @param parent
	 *            The parent <code>Composite</code> the <code>Text</code> should
	 *            be created on
	 * @return The created Widget
	 */
	private Text createTextWidget(Composite parent) {
		Text textField = new Text(parent, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
		textField.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false));
		
		textField.addVerifyListener(new VerifyListener() {
			
			@Override
			public void verifyText(VerifyEvent e) {
				String modifiedText;
				
				if (e.text.length() >= e.end - e.start) {
					modifiedText = e.text;
				} else {
					modifiedText = textField.getText().substring(e.start, e.end);
				}
				
				if (modifiedText.contains("\n") && textField.getLineCount() <= MAX_LINE_DISPLAY) {
					int lineHeight = textField.getLineHeight();
					int newHeight = 0;
					
					boolean isDeletion = e.character == '\b' || e.character == 127
							|| (e.character == 0 && e.text.isEmpty());
					
					for (int i = 0; i < Math.min(MAX_LINE_DISPLAY - textField.getLineCount(),
							modifiedText.chars().filter(ch -> ch == '\n').count()); i++) {
						newHeight += (isDeletion) ? -lineHeight : lineHeight;
					}
					
					newHeight += textField.getLineCount() * lineHeight;
					
					((GridData) textField.getLayoutData()).heightHint = newHeight;
					
					parent.pack(true);
				}
			}
		});
		
		return textField;
		
	}
	
	/**
	 * Gets the delete icon
	 */
	private Image getDeleteIcon() {
		if (deleteIcon == null) {
			return new Image(PlatformUI.getWorkbench().getDisplay(), ResourceManager.getManager()
					.getInternalResourceStream(ResourceManager.REMOVE_ICON));
		} else {
			return deleteIcon;
		}
	}
	
	/**
	 * Gets the plus icon
	 */
	private Image getPlusIcon() {
		if (plusIcon == null) {
			return new Image(PlatformUI.getWorkbench().getDisplay(), ResourceManager.getManager()
					.getInternalResourceStream(ResourceManager.PLUS_ICON));
		} else {
			return plusIcon;
		}
	}
	
}
