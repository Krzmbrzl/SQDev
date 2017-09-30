package raven.sqdev.misc;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import raven.sqdev.interfaces.IAdditionalProposalInformation;
import raven.sqdev.interfaces.IProposalInformationCategory;

/**
 * An abstract implementation of an <code>IAdditionalProposalInformation</code>
 * that provides a framework for subClasses
 * 
 * @author Raven
 * 
 */
public abstract class AbstractAdditionalProposalInformation
		implements IAdditionalProposalInformation {
	
	/**
	 * A list of categories this info consists of
	 */
	private ArrayList<IProposalInformationCategory> categoryList;
	
	/**
	 * Indicating whether the categories are computed or not
	 */
	private boolean isComputed = false;
	
	
	/**
	 * Creates an instance of this
	 * <code>AbstractAdditionalProposalInformation</code>
	 */
	public AbstractAdditionalProposalInformation() {
		this(false);
	}
	
	/**
	 * Creates an instance of this
	 * <code>AbstractAdditionalProposalInformation</code>
	 * 
	 * @param compute
	 *            Whether or not this constructor should directly invoke the
	 *            computation of categories
	 */
	public AbstractAdditionalProposalInformation(boolean compute) {
		if (compute) {
			doComputeCategories();
		}
	}
	
	@Override
	public final int getCategoryCount() {
		checkComputation();
		
		return (categoryList != null) ? categoryList.size() : 0;
	}
	
	@Override
	public final String[] getCategoryNames() {
		checkComputation();
		
		String[] names = new String[categoryList.size()];
		
		for (int i = 0; i < names.length; i++) {
			names[i] = categoryList.get(i).getName();
		}
		
		return names;
	}
	
	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * The Controls returned by this implementation contain a
	 * <code>ScrollableComposite</code> whose minSize has to be set properly
	 */
	@Override
	public final Control[] getCategoryControls(Composite parent) {
		checkComputation();
		
		Control[] controls = new Control[categoryList.size()];
		
		for (int i = 0; i < controls.length; i++) {
			// create the parent Control for the categories
			ScrolledComposite scroller = new ScrolledComposite(parent,
					SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
			//TODO: removing these styles solves the sizing problem (GTK 3) but also removes the ScrollBars completely
			scroller.setAlwaysShowScrollBars(true);
			
			Composite comp = new Composite(scroller, SWT.NONE);
			
			// set GridLayout as the default Layout
			comp.setLayout(new GridLayout());
			comp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			
			if (parent.getLayout() instanceof GridLayout) {
				scroller.setLayoutData(
						new GridData(SWT.FILL, SWT.FILL, true, true));
			}
			
			// inherit color scheme
			comp.setForeground(parent.getForeground());
			comp.setBackground(parent.getBackground());
			
			scroller.setContent(comp);
			scroller.setExpandHorizontal(true);
			scroller.setExpandVertical(true);
			
			categoryList.get(i).getControl(comp);
			
			comp.computeSize(parent.getSize().x, SWT.DEFAULT);
			
			scroller.setMinSize(comp.getSize());
			
			controls[i] = scroller;
		}
		
		return controls;
	}
	
	/**
	 * Forces the computation of the categories representing this info
	 */
	public final void doComputeCategories() {
		categoryList = computeCategories(
				new ArrayList<IProposalInformationCategory>());
		
		isComputed = true;
	}
	
	public boolean isEmpty() {
		checkComputation();
		
		return categoryList.size() == 0;
	}
	
	/**
	 * Checks whether the categories have yet been computed. If not it will
	 * compute them.
	 */
	protected final void checkComputation() {
		if (!isComputed) {
			doComputeCategories();
		}
	}
	
	/**
	 * Computes and creates the categories for this info
	 * 
	 * @param categories
	 *            The list to be filled with the created categories
	 * 
	 * @return The list of created categories
	 */
	protected abstract ArrayList<IProposalInformationCategory> computeCategories(
			ArrayList<IProposalInformationCategory> categories);
	
}
