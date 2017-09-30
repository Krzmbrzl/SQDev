package raven.sqdev.interfaces;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * An interface for an addtional information of an ICompletionProposalExtension5
 * 
 * @author Raven
 * 		
 */
public interface IAdditionalProposalInformation {
	
	/**
	 * Gets the amount of categories this information consists of
	 */
	public int getCategoryCount();
	
	/**
	 * Gets the category's names for this information.
	 */
	public String[] getCategoryNames();
	
	/**
	 * Gets the category controls of this information. Each Control is meant to
	 * contain all the wished display information for the respective category.
	 * <br>
	 * The order of these <code>Control</code>s has to correspond to
	 * <code>getCategoryNames()</code>.<br>
	 * <br>
	 * The created controls will usally overtake the color scheme (foreground
	 * and background color) from the parent composite
	 * 
	 * @param parent
	 *            The parent of all the category controls.
	 */
	public Control[] getCategoryControls(Composite parent);
}
