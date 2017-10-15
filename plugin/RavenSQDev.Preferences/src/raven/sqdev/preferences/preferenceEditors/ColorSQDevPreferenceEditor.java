package raven.sqdev.preferences.preferenceEditors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

import raven.sqdev.misc.ColorUtils;
import raven.sqdev.preferences.pages.ISQDevPreferencePage;
import raven.sqdev.preferences.util.SQDevChangeEvent;

/**
 * A preference editor that lets the user choose a color for the respective preference.
 * @author Raven
 *
 */
public class ColorSQDevPreferenceEditor extends AbstractSQDevPreferenceEditor {
	
	/**
	 * The button to display and choose the color
	 */
	protected Button colorButton;
	private String chosenColor;
	
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
	public ColorSQDevPreferenceEditor(String preferenceKey, String labelText, Composite container) {
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
	public ColorSQDevPreferenceEditor(String preferenceKey, String labelText, String tooltip,
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
	public ColorSQDevPreferenceEditor(String preferenceKey, String labelText, Composite container,
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
	public ColorSQDevPreferenceEditor(String preferenceKey, String labelText, String tooltip,
			Composite container, ISQDevPreferencePage page) {
		super(preferenceKey, labelText, tooltip, container, page);
	}
	
	@Override
	public boolean needsSave() {
		return willNeedSave(chosenColor);
	}
	
	@Override
	public boolean doSave() {
		if(super.doSave()) {
			getPreferenceStore().setValue(getPreferenceKey(), chosenColor);
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void doLoad() {
		load(getPreferenceStore().getString(getPreferenceKey()));
	}
	
	public void load(String str) {
		// store current selection
		chosenColor = str;
		
		if (colorButton != null && !colorButton.isDisposed()) {
			colorButton.setImage(createColorImage(getSelectedColor()));
			
			evaluateInput();
			updateSaveStatus(chosenColor);
		}
		
		changed(new SQDevChangeEvent(SQDevChangeEvent.SQDEV_VALUE_LOADED));
	}
	
	@Override
	public void doLoadDefault() {
		load(getPreferenceStore().getDefaultString(getPreferenceKey()));
	}
	
	@Override
	public void evaluateInput() {
		// There can't be wrong input
	}
	
	@Override
	public int getComponentCount() {
		return 2;
	}
	
	@Override
	public void createComponents(Composite container) {
		label = new Label(getContainer(), SWT.NULL);
		setLabelText(getLabelText());
		label.setToolTipText(getTooltip());
		
		colorButton = new Button(getContainer(), SWT.PUSH);
		colorButton.setImage(createColorImage(getSelectedColor()));
		colorButton.setToolTipText(getTooltip());
		colorButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent event) {
				ColorDialog dialog = new ColorDialog(
						PlatformUI.getWorkbench().getDisplay().getActiveShell());
				dialog.setRGB(ColorUtils.decodeRGB(chosenColor));
				RGB selectedRGB = dialog.open();
				
				if (selectedRGB != null) {
					load(ColorUtils.getRGBValuesAsString(selectedRGB));
				}
			}
		});
		
		if (getContainer().getLayout() instanceof GridLayout) {
			// make the layout's gaps a little wider
			GridLayout layout = (GridLayout) getContainer().getLayout();
			layout.horizontalSpacing = 20;
			
			getContainer().setLayout(layout);
		}else {
			if(getContainer().getLayout() == null) {
				GridLayout layout = new GridLayout();

				if (layout.horizontalSpacing < 20) {
					layout.horizontalSpacing = 20;
				}
				
				getContainer().setLayout(layout);
			}
		}
	}
	
	protected Image createColorImage(Color color) {
		int width = 50;
		int height = 13;
		
		Image image = new Image(Display.getCurrent(), width + 1, height + 1);
		
		GC gc = new GC(image);
		gc.setBackground(color);
		gc.drawRectangle(0, 0, width, height);
		gc.fillRectangle(1, 1, width - 1, height - 1);
		gc.dispose();
		
		return image;
	}
	
	@Override
	public boolean willNeedSave(String content) {
		if(content.equals(getPreferenceStore().getString(getPreferenceKey()))) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * Gets the color the user has currently chosen
	 * 
	 * @return The chosen color
	 */
	protected Color getSelectedColor() {
		return new Color(Display.getCurrent(), ColorUtils.decodeRGB(chosenColor));
	}
	
}
