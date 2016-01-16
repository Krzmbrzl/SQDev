package raven.sqdev.preferences.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import raven.sqdev.preferences.pages.ISQDevPreferencePage;

public class SQDevPreferenceComposite extends Composite {
	
	private ISQDevPreferencePage page;

	/**
	 * Constructs a new instance of this class given its parent and a style
	 * value describing its behavior and appearance.
	 * <p>
	 * The style value is either one of the style constants defined in class
	 * <code>SWT</code> which is applicable to instances of this class, or must
	 * be built by <em>bitwise OR</em>'ing together (that is, using the
	 * <code>int</code> "|" operator) two or more of those <code>SWT</code>
	 * style constants. The class description lists the style constants that are
	 * applicable to the class. Style bits are also inherited from superclasses.
	 * </p>
	 *
	 * @param parent
	 *            a widget which will be the parent of the new instance (cannot
	 *            be null)
	 * @param style
	 *            the style of widget to construct
	 * @param page
	 *            The <code>ISQDevPreferencePage</code> this composite belongs
	 *            to
	 *
	 * @exception IllegalArgumentException
	 *                <ul>
	 *                <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
	 *                </ul>
	 * @exception SWTException
	 *                <ul>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the parent</li>
	 *                </ul>
	 *
	 * @see SWT#NO_BACKGROUND
	 * @see SWT#NO_FOCUS
	 * @see SWT#NO_MERGE_PAINTS
	 * @see SWT#NO_REDRAW_RESIZE
	 * @see SWT#NO_RADIO_GROUP
	 * @see SWT#EMBEDDED
	 * @see SWT#DOUBLE_BUFFERED
	 * @see Widget#getStyle
	 */
	public SQDevPreferenceComposite(Composite parent, int style, ISQDevPreferencePage page) {
		super(parent, style);
		setPage(page);
	}
	
	/**
	 * Gets the <code>ISQDevPreferencePage</code> this composite belongs to
	 * @return
	 */
	public ISQDevPreferencePage getPage() {
		return page;
	}

	private void setPage(ISQDevPreferencePage page) {
		this.page = page;
	}
	
}
