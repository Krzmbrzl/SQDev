package raven.sqdev.ui.layouts;

import org.eclipse.swt.SWT;

/**
 * The LayoutData used in combination with {@linkplain WeightedRowLayout}
 * 
 * @author Raven
 *
 */
public class WeightedRowData {

	/**
	 * The weight used to determine the amount of space that should be given to the
	 * corresponding widget
	 */
	public double weight;
	/**
	 * A flag indicating whether the widget's size should be expanded so it spans
	 * the complete, available space
	 */
	public boolean grabExcessSpace;

	/**
	 * Creates a new instance of this class
	 * 
	 * @param weight
	 *            The weight used to determine the amount of space that should be
	 *            given to the corresponding widget
	 * @param grabExcessSpace
	 *            Indicates whether the widget's size should be expanded so it spans
	 *            the complete, available space
	 */
	public WeightedRowData(double weight, boolean grabExcessSpace) {
		this.weight = weight;
		this.grabExcessSpace = grabExcessSpace;
	}

	/**
	 * Creates a new instance of this class with default values
	 */
	public WeightedRowData() {
		this(SWT.DEFAULT, false);
	}

	/**
	 * Creates a new instance of this class
	 * 
	 * @param weight
	 *            The weight used to determine the amount of space that should be
	 *            given to the corresponding widget
	 */
	public WeightedRowData(double weight) {
		this(weight, false);
	}

	/**
	 * Creates a new instance of this class
	 * 
	 * @param grabExcessHorizontalSpace
	 *            Indicates whether the widget's size should be expanded so it spans
	 *            the complete, available space
	 */
	public WeightedRowData(boolean grabExcessSpace) {
		this(SWT.DEFAULT, grabExcessSpace);
	}

}
