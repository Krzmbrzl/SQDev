package raven.sqdev.ui.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

/**
 * A {@linkplain Text}-widget that can display a default text if its empty and
 * not in focus
 * 
 * @author Raven
 *
 */
public class DefaultText extends Text implements FocusListener {

	/**
	 * The normal text color
	 */
	Color textColor;
	/**
	 * The color for the default text
	 */
	Color defaultTextColor;
	/**
	 * The default text to display
	 */
	String defaultText;
	/**
	 * The previously set text
	 */
	String prevText;
	/**
	 * A flag indicating whether the default text is currently showing
	 */
	boolean isShowingDefaultText;

	/**
	 * Constructs a new instance of this class given its parent and a style value
	 * describing its behavior and appearance.
	 * <p>
	 * The style value is either one of the style constants defined in class
	 * <code>SWT</code> which is applicable to instances of this class, or must be
	 * built by <em>bitwise OR</em>'ing together (that is, using the
	 * <code>int</code> "|" operator) two or more of those <code>SWT</code> style
	 * constants. The class description lists the style constants that are
	 * applicable to the class. Style bits are also inherited from superclasses.
	 * </p>
	 *
	 * @param parent
	 *            a composite control which will be the parent of the new instance
	 *            (cannot be null)
	 * @param style
	 *            the style of control to construct
	 *
	 * @exception IllegalArgumentException
	 *                <ul>
	 *                <li>ERROR_NULL_ARGUMENT - if the parent is null</li>
	 *                </ul>
	 * @exception SWTException
	 *                <ul>
	 *                <li>ERROR_THREAD_INVALID_ACCESS - if not called from the
	 *                thread that created the parent</li>
	 *                <li>ERROR_INVALID_SUBCLASS - if this class is not an allowed
	 *                subclass</li>
	 *                </ul>
	 *
	 * @see SWT#SINGLE
	 * @see SWT#MULTI
	 * @see SWT#READ_ONLY
	 * @see SWT#WRAP
	 * @see SWT#LEFT
	 * @see SWT#RIGHT
	 * @see SWT#CENTER
	 * @see SWT#PASSWORD
	 * @see SWT#SEARCH
	 * @see SWT#ICON_SEARCH
	 * @see SWT#ICON_CANCEL
	 * @see Widget#checkSubclass
	 * @see Widget#getStyle
	 */
	public DefaultText(Composite parent, int style) {
		super(parent, style);

		textColor = Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);
		defaultTextColor = Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);

		prevText = "";

		this.addFocusListener(this);
	}

	@Override
	protected void checkSubclass() {
		// do nothing -> Allow sub-classing
	}

	/**
	 * Sets the default text for this widget and displays it if the conditions are
	 * right
	 * 
	 * @param defaultText
	 *            The text to use as the default-text
	 */
	public void setDefaultText(String defaultText) {
		this.defaultText = defaultText;

		if (!isShowingDefaultText && super.getText().isEmpty()) {
			setForeground(defaultTextColor);
			setText(defaultText);
			isShowingDefaultText = true;
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (isShowingDefaultText) {
			this.setForeground(textColor);
			this.setText("");
			isShowingDefaultText = false;
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (super.getText().isEmpty()) {
			this.setForeground(defaultTextColor);
			this.setText(defaultText);
			isShowingDefaultText = true;
		}
	}

	@Override
	public Point computeSize(int wHint, int hHint) {
		if (isShowingDefaultText) {
			Point p = super.computeSize(SWT.DEFAULT, hHint);

			Point extend = new GC(this).stringExtent(defaultText);

			return new Point(extend.x + this.getBorderWidth() * 2, Math.max(extend.y, p.y));
		} else {
			return super.computeSize(wHint, hHint);
		}
	}

	@Override
	public String getText() {
		return isShowingDefaultText ? "" : super.getText();
	}

}
