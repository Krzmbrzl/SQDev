package raven.sqdev.editors;

import java.util.ArrayList;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.text.AbstractInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IInformationControlExtension2;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.PlatformUI;

import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.interfaces.IAdditionalProposalInformation;
import raven.sqdev.interfaces.IProposalInformationCategory;
import raven.sqdev.misc.AbstractAdditionalProposalInformation;
import raven.sqdev.misc.SQDevComposite;
import raven.sqdev.misc.StringProposalInformationCategory;
import raven.sqdev.miscellaneous.AdditionalKeywordProposalInformation;

/**
 * A basic information control that can show content inside a shell. The
 * information control can be created in two styles:
 * <ul>
 * <li>non-resizable tooltip with optional status</li>
 * <li>resizable tooltip with optional tool bar</li>
 * </ul>
 * Additionally it can present either a status line containing a status text or
 * a toolbar containing toolbar buttons.
 * <p>
 * Subclasses must either override
 * {@link IInformationControl#setInformation(String)} or implement
 * {@link IInformationControlExtension2}. They should also extend
 * {@link #computeTrim()} if they create a content area with additional trim
 * (e.g. scrollbars) and override
 * {@link #getInformationPresenterControlCreator()}.
 * </p>
 *
 * @since 3.4
 */
public class BasicInformationControl extends AbstractInformationControl
		implements IInformationControlExtension2 {
	
	/**
	 * The <code>TabFolder</code> used to display information
	 */
	private TabFolder folder;
	/**
	 * The additional info that should be displayed
	 */
	private IAdditionalProposalInformation info;
	/**
	 * The listener that watchs for hover events over the info popup
	 */
	private static Listener mouseHoverListener;
	
	/**
	 * The compsoite uniquely used for this info popup
	 *
	 */
	public class InfoComposite extends SQDevComposite {
		public InfoComposite(Composite parent, int style) {
			super(parent, style);
		}
	}
	
	
	/**
	 * Creates a basic information control with the given shell as parent.
	 *
	 * @param parentShell
	 *            the parent of this control's shell
	 * @param isResizable
	 *            <code>true</code> if the control should be resizable
	 */
	public BasicInformationControl(Shell parentShell) {
		super(parentShell, new ToolBarManager());
		
		create();
	}
	
	@Override
	public boolean hasContents() {
		return (info == null || folder != null);
	}
	
	@Override
	protected void createContent(Composite parent) {
		InfoComposite comp = new InfoComposite(parent, SWT.NONE);
		comp.setBackground(parent.getBackground());
		comp.setForeground(parent.getForeground());
		
		parent.setLayout(new FillLayout());
		comp.setLayout(new FillLayout());
		
		folder = new TabFolder(comp, SWT.TOP);
		
		// inherit color scheme
		folder.setForeground(comp.getForeground());
		folder.setBackground(comp.getBackground());
	}
	
	/**
	 * Adds an <code>TabItem</code> to the <code>TabFolder</code> used to
	 * display information
	 * 
	 * @param name
	 *            The displayed name of the <code>TabItem</code>
	 * @param control
	 *            The control behind the
	 * @return The created <code>TabItem</code>
	 */
	protected TabItem addTabItem(String name, Control control) {
		if (name == null || control == null) {
			// don't add null elements
			return null;
		}
		
		TabItem item = new TabItem(folder, SWT.NULL);
		item.setText(name);
		item.setControl(control);
		
		return item;
	}
	
	/**
	 * Sets the info for this control
	 * 
	 * @param info
	 *            The additional info that will be displayed
	 */
	protected void setInfo(IAdditionalProposalInformation info) {
		this.info = info;
		
		updateTabFolder();
	}
	
	private void updateTabFolder() {
		String[] categoryNames = info.getCategoryNames();
		Control[] categoryControls = info.getCategoryControls(folder);
		
		for (int i = 0; i < info.getCategoryCount(); i++) {
			addTabItem(categoryNames[i], categoryControls[i]);
		}
	}
	
	@Override
	public IInformationControlCreator getInformationPresenterControlCreator() {
		return new IInformationControlCreator() {
			
			@Override
			public IInformationControl createInformationControl(Shell parent) {
				return new BasicInformationControl(parent);
			}
		};
	}
	
	@Override
	public void setInput(Object input) {
		if (input instanceof IAdditionalProposalInformation) {
			setInfo((IAdditionalProposalInformation) input);
			
			if (input instanceof AdditionalKeywordProposalInformation) {
				// add the respective toolbar action this info does provide
				
				Action action = ((AdditionalKeywordProposalInformation) input).getToolbarAction();
				
				if (action != null) {
					// add action if available
					getToolBarManager().add(action);
					
					getToolBarManager().update(false);
				}
			}
		} else {
			setInfo(new AbstractAdditionalProposalInformation(true) {
				
				@Override
				protected ArrayList<IProposalInformationCategory> computeCategories(
						ArrayList<IProposalInformationCategory> categories) {
					categories.add(
							new StringProposalInformationCategory("General", input.toString()));
					
					return categories;
				}
			});
		}
	}
	
	@Override
	public void setVisible(boolean visible) {		
		// add a MouseListener
		configureMouseListener();
		
		super.setVisible(visible);
		
		configureScrolledComposite(folder);
	}
	
	/**
	 * Configures the minSize of the first occurance of a
	 * <code>ScrolledComposite</code> in the children of the given parenr
	 * <code>Composite</code>
	 * 
	 * @param parent
	 *            The parent <code>Composite</code> that eventually holds a
	 *            <code>ScrolledComposite</code> that should be configured
	 */
	private void configureScrolledComposite(Composite parent) {
		for (Control currentControl : parent.getChildren()) {
			if (currentControl instanceof ScrolledComposite) {
				ScrolledComposite scroller = (ScrolledComposite) currentControl;
				
				if (scroller.getContent() != null) {
					Control content = scroller.getContent();
					
					// adjust the scroller to the proper size
					int border = scroller.getBorderWidth();
					int width = scroller.getSize().x - 2 * border;
					int height = content.computeSize(scroller.getSize().x, SWT.DEFAULT, true).y
							+ getToolBarManager().createControl(getShell()).getSize().y;
					
					scroller.setMinSize(width, height);
					
					ScrollBar scrollBar = scroller.getVerticalBar();
					
					if (scrollBar != null) {
						// if there is a vertical scrollBar adjust the
						// scroller's minWidth to make sure the horizontal
						// Scrollbar does not have to appear when the vertical
						// on does
						scroller.setMinWidth(scroller.getMinWidth() - scrollBar.getSize().x);
					}
				}
			} else {
				if (currentControl instanceof Composite) {
					// pass it through
					configureScrolledComposite((Composite) currentControl);
				}
			}
		}
	}
	
	/**
	 * Configures a MouseListener that processesHoverEvents of the mouse in
	 * order to activate the respective info popup.<br>
	 * If it has already been configured before it will do nothing
	 */
	private void configureMouseListener() {
		if (mouseHoverListener != null) {
			// has already been configured
			return;
		}
		mouseHoverListener = new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				if (event.widget instanceof InfoComposite) {
					((InfoComposite) event.widget).setFocus();
				} else {
					if (event.widget instanceof Composite) {
						Composite currentComp = (Composite) event.widget;
						
						boolean doFocus = false;
						
						while (currentComp != null) {
							Class<?> enclosing = currentComp.getClass().getEnclosingClass();
							
							if (enclosing != null
									&& enclosing.equals(BasicInformationControl.class)) {
								// the defining compsoite has been identified
								break;
							}
							
							if (currentComp instanceof Shell) {
								if (currentComp.equals(getParentShell())) {
									doFocus = true;
									break;
								}
							}
							
							currentComp = currentComp.getParent();
						}
						
						if (currentComp != null && !doFocus) {
							// the given widget is part of the one for this info
							// popup
							// emulate mouseClick
							event.widget.notifyListeners(SWT.MouseDown, new Event());
							event.widget.notifyListeners(SWT.MouseUp, new Event());
						} else {
							if (doFocus) {
								((Composite) event.widget).setFocus();
							}
						}
					}
				}
			}
		};
		
		PlatformUI.getWorkbench().getDisplay().addFilter(SWT.MouseMove, mouseHoverListener);
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		if (mouseHoverListener != null) {
			// remove the listener
			PlatformUI.getWorkbench().getDisplay().removeFilter(SWT.MouseMove, mouseHoverListener);
			
			mouseHoverListener = null;
		}
	}
	
	/**
	 * Gets highest parent shell of this InformationControl.<br>
	 * If the topmost parent is not a <code>Shell</code> an exception is thrown
	 * 
	 * @return The respective <code>Shell</code>
	 */
	public Shell getParentShell() {
		Composite comp = getShell();
		
		if (comp.getParent() != null) {
			while (comp.getParent().getParent() != null) {
				comp = comp.getParent();
			}
		}
		
		if (!(comp instanceof Shell)) {
			throw new SQDevCoreException("Expected shell!");
		}
		
		return (Shell) comp;
	}
}
