package raven.sqdev.ui.widgets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

import raven.sqdev.ui.layouts.CheckboxListLayout;
import raven.sqdev.ui.layouts.DynamicGridLayout;
import raven.sqdev.ui.layouts.WeightedRowData;
import raven.sqdev.ui.layouts.WeightedRowLayout;

/**
 * A custom widget that is basically a fancy wrapper for a list of checkboxes
 * 
 * @author Raven
 *
 */
public class CheckboxList extends LayedOutComposite {

	/**
	 * The widget used to display the title-text. If using the minimal version of
	 * this class, this is <code>null</code>
	 */
	Label titleWidget;
	/**
	 * The {@linkplain Composite} holding the {@link #titleWidget} and the
	 * search-text-field. If using the minimal version of this class, this is
	 * <code>null</code>
	 */
	Composite topComp;
	/**
	 * The {@linkplain Composite} holding the two radio buttons that allow switching
	 * between displaying all or only selected checkboxes. If using the minimal
	 * version of this class, this is <code>null</code>
	 */
	Composite switchComp;
	/**
	 * The {@linkplain ScrolledComposite} holding the {@link #checkBoxComp} that in
	 * turn holds all of the displayed checkboxes
	 */
	ScrolledComposite scrComp;
	/**
	 * The {@linkplain Composite} holding all checkboxes
	 */
	Composite checkBoxComp;
	/**
	 * The search field allowing to filter the displayed checkboxes. If using the
	 * minimal version of this class, this is <code>null</code>
	 */
	DefaultText searchField;

	/**
	 * A switch indicating whether only the selected checkboxes should be displayed
	 */
	boolean showOnlySelected;
	/**
	 * A {@linkplain Collection} holding all labels that are being displayed as
	 * checkboxes on this widget
	 */
	Collection<String> checkBoxLabels;
	/**
	 * A {@linkplain Collection} holding all labels whose corresponding checkboxes
	 * are currently selected
	 */
	Collection<String> selectedLabels;


	/**
	 * Creates a new instance of this class
	 * 
	 * @param parent
	 *            The parent for this object
	 * @param style
	 *            The SWT-style for this object
	 * @param vScroll
	 *            Whether to use vertical scrolling whenever the content is too much
	 *            to display on the current size. Otherwise horizontal scrolling
	 *            will be used instead.
	 * @param minimal
	 *            Whether to use the minimal version of this object (that is without
	 *            the two top-bars containing the title-text, a search field and an
	 *            option to only show selected checkboxes.
	 */
	public CheckboxList(Composite parent, int style, boolean vScroll, boolean minimal) {
		super(parent, style);

		checkBoxLabels = new ArrayList<>(0);
		selectedLabels = new ArrayList<String>(0);
		showOnlySelected = false;

		initialize(vScroll, minimal);
	}

	/**
	 * Creates a new instance of this class
	 * 
	 * @param parent
	 *            The parent for this object
	 * @param style
	 *            The SWT-style for this object
	 * @param vScroll
	 *            Whether to use vertical scrolling whenever the content is too much
	 *            to display on the current size. Otherwise horizontal scrolling
	 *            will be used instead.
	 */
	public CheckboxList(Composite parent, int style, boolean vScroll) {
		this(parent, style, vScroll, false);
	}

	/**
	 * Creates a new instance of this class
	 * 
	 * @param parent
	 *            The parent for this object
	 * @param style
	 *            The SWT-style for this object
	 */
	public CheckboxList(Composite parent, int style) {
		this(parent, style, true, false);
	}

	/**
	 * Initializes all components of this object
	 * 
	 * @param vScroll
	 *            Whether to use vertical scrolling whenever the content is too much
	 *            to display on the current size. Otherwise horizontal scrolling
	 *            will be used instead.
	 * @param minimal
	 *            Whether to use the minimal version of this object (that is without
	 *            the two top-bars containing the title-text, a search field and an
	 *            option to only show selected checkboxes.
	 */
	protected void initialize(boolean vScroll, boolean minimal) {
		if (!minimal) {
			this.doSetLayout(new CheckboxListLayout());
			createTopBar();
			createSwitchBar();
		} else {
			this.doSetLayout(new FillLayout());
		}
		createCheckboxList(vScroll);
	}

	/**
	 * Creates the top bar holding the title-text and the search-field
	 */
	protected void createTopBar() {
		topComp = new Composite(this, SWT.NONE);

		topComp.setLayout(new WeightedRowLayout(false, 5, 5, 3));

		ScrolledComposite scroller = new ScrolledComposite(topComp, SWT.H_SCROLL);

		Composite centerer = new Composite(scroller, SWT.NONE);
		centerer.setLayout(new GridLayout(1, true));

		titleWidget = new Label(centerer, SWT.NONE);
		titleWidget.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

		scroller.setContent(centerer);
		scroller.setExpandHorizontal(true);
		scroller.setExpandVertical(true);
		scroller.setLayoutData(new WeightedRowData(0.8, true));

		searchField = new DefaultText(topComp, SWT.BORDER);
		searchField.setDefaultText("Enter search string...");
		searchField.setLayoutData(new WeightedRowData(0.2, true));
		searchField.setToolTipText("Enter a search string and start the search by hitting return.");

		// add key-listener that will start the search on hitting return
		searchField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.character == '\r') {
					e.doit = false;
					updateCheckBoxList();
				}
			}
		});
	}

	/**
	 * Creates the "switch-bar" which holds the options to display selected
	 * checkboxes only
	 */
	protected void createSwitchBar() {
		switchComp = new Composite(this, SWT.NONE);

		switchComp.setLayout(new GridLayout(2, true));


		Button showAllButton = new Button(switchComp, SWT.RADIO);
		showAllButton.setText("All");
		showAllButton.setSelection(true);
		showAllButton.setToolTipText("Displays all available options");

		Button showSelectedButton = new Button(switchComp, SWT.RADIO);
		showSelectedButton.setText("Selected");
		showSelectedButton.setSelection(false);
		showSelectedButton.setToolTipText("Displays the currently selected options only");

		SelectionListener buttonListener = new SelectionAdapter() {

			Widget lastSelected = showAllButton;

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.widget == lastSelected) {
					return;
				}

				if (e.widget == showAllButton) {
					showAllButton.setSelection(true);
					showSelectedButton.setSelection(false);
					lastSelected = showAllButton;
					showOnlySelected = false;
				} else {
					showAllButton.setSelection(false);
					showSelectedButton.setSelection(true);
					lastSelected = showSelectedButton;
					showOnlySelected = true;
				}

				// update displayed checkboxes
				updateCheckBoxList();
			}
		};

		showAllButton.addSelectionListener(buttonListener);
		showSelectedButton.addSelectionListener(buttonListener);
	}

	/**
	 * Creates the actual list of checkboxes
	 * 
	 * @param vScroll
	 *            Whether to use vertical scrolling whenever the content is too much
	 *            to display on the current size. Otherwise horizontal scrolling
	 *            will be used instead.
	 */
	protected void createCheckboxList(boolean vScroll) {
		scrComp = new ScrolledComposite(this, SWT.H_SCROLL | SWT.V_SCROLL);

		checkBoxComp = new Composite(scrComp, SWT.BORDER);
		DynamicGridLayout dynLayout = new DynamicGridLayout(vScroll, 15, 15, 8, 8);
		checkBoxComp.setLayout(dynLayout);

		scrComp.setContent(checkBoxComp);
		scrComp.setExpandHorizontal(true);
		scrComp.setExpandVertical(true);
		scrComp.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				Rectangle rect = scrComp.getClientArea();
				scrComp.setMinSize(dynLayout.computeSize(checkBoxComp, rect.width, rect.height, false));
			}
		});
	}

	/**
	 * Updates the checkboxes displayed in this list applying all potentially
	 * necessary filtering
	 */
	protected void updateCheckBoxList() {
		// dispose all checkboxes
		for (Control current : checkBoxComp.getChildren()) {
			current.dispose();
		}

		List<String> newSelectedLabels = new ArrayList<>();

		// create new checkboxes
		for (String currentLabel : checkBoxLabels) {
			boolean isSelected = selectedLabels.contains(currentLabel);

			if (isSelected) {
				newSelectedLabels.add(currentLabel);
			}

			String searchString = searchField != null ? searchField.getText().toLowerCase() : "";

			if ((!showOnlySelected || isSelected) && currentLabel.toLowerCase().contains(searchString)) {
				Button btn = new Button(checkBoxComp, SWT.CHECK);
				btn.setText(currentLabel);
				btn.setSelection(isSelected);
				btn.setEnabled(this.isEnabled());

				// keep track of selections
				btn.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						if (btn.getSelection()) {
							selectedLabels.add(currentLabel);
						} else {
							selectedLabels.remove(currentLabel);
						}

						// notify listener about selection-change
						Event event = new Event();
						event.item = btn;
						event.type = SWT.Selection;
						event.display = Display.getCurrent();
						event.doit = true;
						event.text = currentLabel;
						event.time = e.time;
						event.widget = CheckboxList.this;
						event.data = btn.getSelection();
						notifyListeners(SWT.Selection, event);
					}
				});
			}
		}

		selectedLabels = newSelectedLabels;
		checkBoxComp.layout();
	}

	/**
	 * Sets the text that is being displayed at the top of this widget. If this
	 * object has been created as a minimal-version, this does nothing
	 * 
	 * @param text
	 *            The text to display
	 */
	public void setText(String text) {
		if (titleWidget != null && !titleWidget.isDisposed()) {
			titleWidget.setText(text);
			topComp.layout();

			((ScrolledComposite) titleWidget.getParent().getParent()).setMinSize(new GC(titleWidget).textExtent(text));
		}
	}

	/**
	 * Sets the labels that should be represented using checkboxes
	 * 
	 * @param labels
	 *            The labels to display
	 */
	public void setLabels(Collection<String> labels) {
		this.checkBoxLabels = labels;
		updateCheckBoxList();
	}

	/**
	 * Gets the labels of the currently selected checkboxes as an
	 * {@linkplain ArrayList}
	 */
	public ArrayList<String> getSelection() {
		return new ArrayList<>(selectedLabels);
	}

	/**
	 * Clears the current selection
	 */
	public void clearSelection() {
		selectedLabels.clear();
		updateCheckBoxList();
	}

	/**
	 * Sets the selection for this list without sending a change-event. Note that
	 * providing any labels that have not previously been set via
	 * {@link #setLabels(Collection)} will not have any effect.
	 * 
	 * @param selection
	 *            The labels of the checkboxes that should be selected
	 */
	public void setSelection(Collection<String> selection) {
		this.selectedLabels = selection;
		updateCheckBoxList();
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		
		for(Control current : getChildren()) {
			propagateEnablement(current, enabled);
		}
	}
	
	private void propagateEnablement(Control ctrl, boolean enabled) {
		ctrl.setEnabled(enabled);
		
		if(ctrl instanceof Composite) {
			for(Control current : ((Composite)ctrl).getChildren()) {
				propagateEnablement(current, enabled);
			}
		}
	}

	public static void main(String[] args) {
		Display display = new Display();

		Shell shell = new Shell(display);

		shell.setLayout(new FillLayout());

		CheckboxList list = new CheckboxList(shell, SWT.NONE, true, false);

		list.setText("This is quite a long and verbose test that is intended to overflow the normal text area");

		Collection<String> labels = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			labels.add("Label " + (i + 1));
		}

		list.setLabels(labels);

		list.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				System.out.println("Selection changed for " + event.text + " - " + event.data);
			}
		});
		
		list.setEnabled(false);

		shell.open();
		shell.forceFocus();

		while (!shell.isDisposed()) {
			display.readAndDispatch();
			display.sleep();
		}

		display.dispose();
	}

}