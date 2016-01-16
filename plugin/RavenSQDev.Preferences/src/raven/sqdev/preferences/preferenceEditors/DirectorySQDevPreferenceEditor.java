package raven.sqdev.preferences.preferenceEditors;

import java.io.File;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import raven.sqdev.preferences.pages.EStatus;
import raven.sqdev.preferences.pages.ISQDevPreferencePage;
import raven.sqdev.preferences.util.SQDevChangeEvent;

public class DirectorySQDevPreferenceEditor extends AbstractSQDevPreferenceEditor
		implements VerifyListener {
		
	/**
	 * The label used by this editor
	 */
	private Label label;
	
	/**
	 * The TextField used to show the current selected path to the user
	 */
	private Text pathText;
	
	/**
	 * The initial text of {@linkplain #pathText}.<br>
	 * Initial value is empty.
	 */
	private String initialText;
	
	/**
	 * The button to open the <code>FileDialog</code>
	 */
	private Button browseButton;
	
	/**
	 * The text used on the created button. Default value is
	 * "<code>Browse...</code>"
	 */
	private String buttonText = "Browse...";
	
	/**
	 * Creates a new <code>SQDevPreferenceEditor</code><br>
	 * <b>This constructor can only be used if the given container is an instance
	 * of <code>ISQDevPreferencePage</code> or one of it's parents is. </b>
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
	public DirectorySQDevPreferenceEditor(String preferenceKey, String labelText, String tooltip,
			Composite container) {
		super(preferenceKey, labelText, tooltip, container);
	}
	
	/**
	 * Creates a new <code>SQDevPreferenceEditor</code><br>
	 * <b>This constructor can only be used if the given container is an instance
	 * of <code>ISQDevPreferencePage</code> or one of it's parents is. </b>
	 * 
	 * @param preferenceKey
	 *            The key of the preference to work on
	 * @param labelText
	 *            The text of the label
	 * @param container
	 *            The container the GUI elements should be placed in
	 */
	public DirectorySQDevPreferenceEditor(String preferenceKey, String labelText,
			Composite container) {
		super(preferenceKey, labelText, "", container);
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
	public DirectorySQDevPreferenceEditor(String preferenceKey, String labelText,
			Composite container, String tooltip, ISQDevPreferencePage page) {
		super(preferenceKey, labelText, tooltip, container, page);
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
	public DirectorySQDevPreferenceEditor(String preferenceKey, String labelText,
			Composite container, ISQDevPreferencePage page) {
		super(preferenceKey, labelText, "", container, page);
	}
	
	@Override
	public boolean doSave() {
		if (isValid()) {
			getPreferenceStore().setValue(getPreferenceKey(), pathText.getText());
			
			// update save status
			updateSaveStatus();
			
			return true;
		} else {
			MessageDialog.open(MessageDialog.ERROR,
					PlatformUI.getWorkbench().getDisplay().getActiveShell(),
					"Failed at saving " + getLabelText().substring(1, getLabelText().length() - 1),
					"Couldn't save " + getLabelText().substring(1, getLabelText().length() - 1)
							+ " because it is in an invalid state!",
					SWT.NULL);
					
			return false;
		}
	}
	
	@Override
	public void doLoad() {
		// get the stored path
		load(getPreferenceStore().getString(getPreferenceKey()));
	}
	
	/**
	 * Loads the given text in the UI.<br>
	 * If you want to load the value from the preference use
	 * {@linkplain #doLoad()}
	 * 
	 * @param text
	 *            The text to display<br>
	 */
	public void load(String text) {		
		if (pathText == null) {
			setInitialText(text);
		} else {
			if (pathText.isDisposed()) {
				return;
			}
			
			pathText.setText(text);
			// evaluate the new input
			evaluateInput();
			updateSaveStatus();
		}
		
		// notify about change
		changed(new SQDevChangeEvent(SQDevChangeEvent.SQDEV_VALUE_LOADED));
	}
	
	@Override
	public void doLoadDefault() {
		load(getPreferenceStore().getDefaultString(getPreferenceKey()));
	}
	
	@Override
	public void evaluateInput() {
		String input = pathText.getText();
		File inputFile = new File(input);
		EStatus status = EStatus.OK;
		
		// check if the directory exists
		if (!inputFile.exists()) {
			status = EStatus.ERROR;
			status.setHint("The given directory does not exist!");
			setStatus(status);
			return;
		}
		
		// check if given file is a directory
		if (!inputFile.isDirectory()) {
			status = EStatus.ERROR;
			status.setHint("The given path is no directory!");
			return;
		}
		
		setStatus(status);
	}
	
	@Override
	public int getComponentCount() {
		return 3;
	}
	
	@Override
	public void setLabelText(String text) {
		super.setLabelText(text);
		
		if (label != null) {
			// update label if it has already been created
			label.setText(text);
			
			label.pack(true);
		}
	};
	
	@Override
	public void createComponents(Composite container) {
		label = new Label(container, SWT.BOLD);
		label.setText(getLabelText());
		
		// check that initialText is not null
		if (getInitialText() == null) {
			setInitialText("");
		}
		
		pathText = new Text(container, SWT.SINGLE | SWT.READ_ONLY | SWT.BORDER);
		pathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		pathText.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		pathText.setToolTipText(getTooltip());
		pathText.addVerifyListener(this);
		load(initialText);
		
		browseButton = new Button(container, SWT.PUSH);
		browseButton.setText(getButtonText());
		browseButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseUp(MouseEvent e) {
				DirectoryDialog chooser = new DirectoryDialog(
						PlatformUI.getWorkbench().getDisplay().getActiveShell());
						
				// start chooser on the typed in path if it exists
				if (new File(pathText.getText()).exists()) {
					chooser.setFilterPath(pathText.getText());
				}
				
				String path = chooser.open();
				
				if (path != null) {
					// display selected path
					load(path);
				}
			}
		});
		
		createEmptyComponents();
	}
	
	public String getButtonText() {
		return buttonText;
	}
	
	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
		
		if (browseButton != null) {
			browseButton.setText(buttonText);
			
			browseButton.pack();
		}
	}
	
	public String getInitialText() {
		return initialText;
	}
	
	public void setInitialText(String initialText) {
		this.initialText = initialText;
	}
	
	@Override
	public boolean needsSave() {
		return willNeedSave(pathText.getText());
	}
	
	/**
	 * Checks if the editor needs to be saved when it will change to this
	 * content
	 * 
	 * @param content
	 *            The new content
	 * @return
	 */
	public boolean willNeedSave(String content) {
		if (content.equals(getPreferenceStore().getString(getPreferenceKey()))) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Indicates graphically whether the editor has a value that differs from
	 * the value of the preference this editor is working on
	 * 
	 * @param newText
	 *            The text that represents the value of this editor and that
	 *            will be compared to the preference's value
	 */
	public void updateSaveStatus(String newText) {
		// mark the editor if it needs to be saved
		String labelText = getLabelText();
		
		if (willNeedSave(newText)) {
			if (!labelText.endsWith("*")) {
				labelText += "*";
			}
		} else {
			if (labelText.endsWith("*")) {
				labelText = labelText.substring(0, labelText.length() - 1);
			}
		}
		
		setLabelText(labelText);
	}
	
	/**
	 * Indicates graphically whether the editor has a value that differs from
	 * the value of the preference this editor is working on
	 */
	public void updateSaveStatus() {
		updateSaveStatus(pathText.getText());
	}
	
	@Override
	public void verifyText(VerifyEvent e) {
		updateSaveStatus(e.text);
		
		// evaluate the new value
		evaluateInput();
		
		// notify about change
		changed(new SQDevChangeEvent(SQDevChangeEvent.SQDEV_VALUE_CHANGED));
	}
	
}
