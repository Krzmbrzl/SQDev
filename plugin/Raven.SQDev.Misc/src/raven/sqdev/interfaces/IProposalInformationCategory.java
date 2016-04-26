package raven.sqdev.interfaces;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * An interface for a information category used in the
 * <code>BasicCodeEditor</code>-framework
 * 
 * @author Raven
 * 		
 */
public interface IProposalInformationCategory {
	
	/**
	 * Gets the name of this category
	 */
	public String getName();
	
	/**
	 * Gets the <code>Control</code> representing this category.<br>
	 * <br>
	 * The created <code>Control</code> will usally overtake the foreground and
	 * background color from the parent
	 * 
	 * @param parent
	 *            The parent of the created control. This <code>Control</code>
	 *            is meant to directly group widgets on it
	 */
	public Control getControl(Composite parent);
}
