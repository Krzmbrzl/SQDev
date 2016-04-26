package raven.sqdev.preferences.preferenceEditors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import raven.sqdev.preferences.pages.ISQDevPreferencePage;
import raven.sqdev.preferences.util.EStatus;
import raven.sqdev.preferences.util.SQDevChangeEvent;

/**
 * A preference editor for simple String values
 * 
 * @author Raven
 * 		
 */
public class ValueSQDevPreferenceEditor extends AbstractSQDevPreferenceEditor {
	
	/**
	 * The text for the value
	 */
	private Text valueText;
	
	/**
	 * The initial content of this editor
	 */
	private String initialContent;
	
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
	public ValueSQDevPreferenceEditor(String preferenceKey, String labelText, Composite container) {
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
	public ValueSQDevPreferenceEditor(String preferenceKey, String labelText, String tooltip,
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
	public ValueSQDevPreferenceEditor(String preferenceKey, String labelText, Composite container,
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
	public ValueSQDevPreferenceEditor(String preferenceKey, String labelText, String tooltip,
			Composite container, ISQDevPreferencePage page) {
		super(preferenceKey, labelText, tooltip, container, page);
	}
	
	@Override
	public boolean needsSave() {
		return willNeedSave(valueText.getText());
	}
	
	@Override
	public void doLoad() {
		load(getPreferenceStore().getString(getPreferenceKey()));
	}
	
	@Override
	public void doLoadDefault() {
		load(getPreferenceStore().getDefaultString(getPreferenceKey()));
	}
	
	private void load(String content) {
		Assert.isNotNull(content);
		Assert.isTrue(!content.isEmpty());
		
		if (valueText == null || valueText.isDisposed()) {
			initialContent = content;
		} else {
			valueText.setText(content);
			
			evaluateInput();
			updateSaveStatus(content);
		}
		
		changed(new SQDevChangeEvent(SQDevChangeEvent.SQDEV_VALUE_LOADED));
	}
	
	@Override
	public boolean doSave() {
		super.doSave();
		
		getPreferenceStore().setValue(getPreferenceKey(), valueText.getText());
		
		updateSaveStatus(valueText.getText());
		
		return true;
	};
	
	@Override
	public void evaluateInput() {
		EStatus status = EStatus.OK;
		
		if (valueText.getText().isEmpty()) {
			status = EStatus.ERROR;
			status.setHint("Value may not be empty");
			
			setStatus(status);
			
			valueText.setBackground(
					new Color(PlatformUI.getWorkbench().getDisplay(), 255, 153, 153));
			return;
		}
		
		valueText.setBackground(
				PlatformUI.getWorkbench().getDisplay().getSystemColor(SWT.COLOR_WHITE));
		setStatus(status);
	}
	
	@Override
	public int getComponentCount() {
		return 2;
	}
	
	@Override
	public void createComponents(Composite container) {
		// name label
		label = new Label(container, SWT.NULL);
		label.setText(getLabelText());
		label.setToolTipText(getTooltip());
		
		valueText = new Text(container, SWT.SINGLE | SWT.BORDER);
		valueText.setToolTipText(getTooltip());
		valueText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		valueText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				updateSaveStatus(((Text) e.widget).getText());
				evaluateInput();
			}
		});
		
		if (initialContent == null) {
			doLoad();
		} else {
			load(initialContent);
		}
	}
	
	@Override
	public boolean willNeedSave(String content) {
		if (getPreferenceStore().getString(getPreferenceKey()).equals(content)) {
			return false;
		} else {
			return true;
		}
	}
	
}
