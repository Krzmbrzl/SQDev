package raven.sqdev.ui.layouts;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

public class WeightedRowLayout extends Layout {
	/**
	 * A {@linkplain Map} containing all cached sizes
	 */
	Map<Control, Point> cachedSizes;
	/**
	 * A {@linkplain Map} containing all cached locations
	 */
	Map<Control, Point> cachedLocations;
	/**
	 * The cached total size
	 */
	Point cachedTotalSize;
	/**
	 * A flag indicating whether this layout is in vertical mode
	 */
	boolean vertical;
	/**
	 * The width of the top and bottom margin
	 */
	int verticalMarginWidth;
	/**
	 * The width of the left and right margin
	 */
	int horizontalMarginWidth;
	/**
	 * The spacing between elements
	 */
	int spacing;


	/**
	 * Creates a new instance of this layout
	 * 
	 * @param vertical
	 *            Indicates whether the content should be layed out vertically
	 * @param verticalMarginWidth
	 *            The width of the top and bottom margin
	 * @param horizontalMarginWidth
	 *            The width of the left and right margin
	 * @param spacing
	 *            The spacing between elements
	 */
	public WeightedRowLayout(boolean vertical, int verticalMarginWidth, int horizontalMarginWidth, int spacing) {
		cachedSizes = new HashMap<>();
		cachedLocations = new HashMap<>();
		cachedTotalSize = new Point(0, 0);
		this.vertical = vertical;

		this.verticalMarginWidth = verticalMarginWidth;
		this.horizontalMarginWidth = horizontalMarginWidth;
		this.spacing = spacing;
	}

	/**
	 * Creates a new instance of this layout in horizontal mode
	 * 
	 * @param verticalMarginWidth
	 *            The width of the top and bottom margin
	 * @param horizontalMarginWidth
	 *            The width of the left and right margin
	 * @param spacing
	 *            The spacing between elements
	 */
	public WeightedRowLayout(int verticalMarginWidth, int horizontalMarginWidth, int spacing) {
		this(false, verticalMarginWidth, horizontalMarginWidth, spacing);
	}

	/**
	 * Creates a new instance of this layout with no spacing between elements
	 * 
	 * @param vertical
	 *            Indicates whether the content should be layed out vertically
	 * @param verticalMarginWidth
	 *            The width of the top and bottom margin
	 * @param horizontalMarginWidth
	 *            The width of the left and right margin
	 */
	public WeightedRowLayout(boolean vertical, int verticalMarginWidth, int horizontalMarginWidth) {
		this(vertical, verticalMarginWidth, horizontalMarginWidth, 0);
	}

	/**
	 * Creates a new instance of this layout in horizontal mode and with no spacing
	 * between elements
	 * 
	 * @param verticalMarginWidth
	 *            The width of the top and bottom margin
	 * @param horizontalMarginWidth
	 *            The width of the left and right margin
	 */
	public WeightedRowLayout(int verticalMarginWidth, int horizontalMarginWidth) {
		this(false, verticalMarginWidth, horizontalMarginWidth, 0);
	}

	/**
	 * Creates a new instance of this layout in horizontal mode and with no
	 * additional margins or spacing
	 * 
	 */
	public WeightedRowLayout(boolean vertical) {
		this(vertical, 0, 0, 0);
	}

	/**
	 * Creates a new instance of this layout in horizontal mode and with no
	 * additional margins or spacing
	 * 
	 */
	public WeightedRowLayout() {
		this(false, 0, 0, 0);
	}


	@Override
	protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
		if (flushCache) {
			flushCache();
		}

		if (vertical) {
			if (hHint == SWT.DEFAULT) {
				throw new IllegalArgumentException("Can't compute size in vertical mode when no height is specified!");
			}
		} else {
			if (wHint == SWT.DEFAULT) {
				throw new IllegalArgumentException("Can't compute size in horizontal mode if no width is given!");
			}
		}

		computeLayout(composite, new Point(wHint, hHint));

		return cachedSizes.get(composite);
	}

	@Override
	protected void layout(Composite composite, boolean flushCache) {
		if (flushCache) {
			flushCache();
		}

		computeLayout(composite, composite.getSize());

		// layout content according to cache
		for (Control current : composite.getChildren()) {
			current.setSize(cachedSizes.get(current));
			current.setLocation(cachedLocations.get(current));
		}
	}

	/**
	 * Computes the layout for the given composite and populates
	 * {@link #cachedLocations} and {@link #cachedSizes}. If the cached values are
	 * still applicable this method returns immediately.
	 * 
	 * @param composite
	 *            The {@linkplain Composite} to compute the layout for
	 * @param totalSize
	 *            The total size available to the content of the given composite
	 */
	protected void computeLayout(Composite composite, Point totalSize) {
		if (checkCache(composite, totalSize)) {
			return;
		} else {
			flushCache();
		}

		checkData(composite);

		adjustTotalSize(totalSize);

		int offset = vertical ? verticalMarginWidth : horizontalMarginWidth;
		int totalHeight = 2 * verticalMarginWidth;
		int totalWidth = 2 * horizontalMarginWidth;

		for (Control current : composite.getChildren()) {
			WeightedRowData data = (WeightedRowData) current.getLayoutData();

			// compute size
			Point size;
			if (vertical) {
				int weightedHeight = (int) (totalSize.y * data.weight);

				size = current.computeSize(SWT.DEFAULT, weightedHeight);
				if (data.grabExcessSpace) {
					size.y = weightedHeight;
				}
			} else {
				int weightedWidth = (int) (totalSize.x * data.weight);

				size = current.computeSize(weightedWidth, SWT.DEFAULT);
				if (data.grabExcessSpace) {
					size.x = weightedWidth;
				}
			}

			cachedSizes.put(current, size);

			// compute location
			if (vertical) {
				cachedLocations.put(current, new Point(horizontalMarginWidth, offset));

				offset += size.y;
				totalHeight += spacing + size.y;
				totalWidth = Math.max(totalWidth, size.x + 2 * horizontalMarginWidth);
			} else {
				cachedLocations.put(current, new Point(offset, verticalMarginWidth));

				offset += size.x;
				totalWidth += spacing + size.x;
				totalHeight = Math.max(totalHeight, size.y + 2 * verticalMarginWidth);
			}

			offset += spacing;
		}

		// store composite's total size
		cachedSizes.put(composite, new Point(totalWidth, totalHeight));
	}

	/**
	 * Verifies the LayoutData of all children of the given composite and sets it if
	 * it is not presented (or completes it if it is present but not complete)
	 * 
	 * @param composite
	 *            The {@linkplain Composite} whose children's LayoutData should be
	 *            checked
	 */
	protected void checkData(Composite composite) {
		double weightSum = 0;
		int compositesWithoutData = 0;

		// check for data
		for (Control currentControl : composite.getChildren()) {
			WeightedRowData data = (WeightedRowData) currentControl.getLayoutData();
			if (data != null && data.weight != SWT.DEFAULT) {
				if (data.weight < 0 || data.weight > 1) {
					throw new IllegalArgumentException("Illegal weight (greater one or less than zero)!");
				}

				weightSum += data.weight;
			} else {
				compositesWithoutData++;
			}
		}

		if (weightSum > 1) {
			throw new IllegalArgumentException("Sum of weights must not exceed one!");
		}

		// make sure all children have data associated
		if (compositesWithoutData > 0) {
			// Set weight of remaining widgets so that the sum of all weights is 1
			double distributeWeight = (1.0 - weightSum) / (double) compositesWithoutData;

			for (Control currentControl : composite.getChildren()) {
				WeightedRowData data = (WeightedRowData) currentControl.getLayoutData();
				if (data == null) {
					currentControl.setLayoutData(new WeightedRowData(distributeWeight));
				} else {
					// the data exists but its weight is SWT.DEFAULT
					data.weight = distributeWeight;
					currentControl.setLayoutData(data);
				}
			}
		}
	}

	/**
	 * Checks whether all children of the given composite have cached locations and
	 * sizes
	 * 
	 * @param composite
	 *            The {@linkplain Composite} whose children should be checked
	 * @param totalSize
	 *            The total size the cache has to be valid for
	 * @return Whether the current cache is valid for this composite
	 */
	protected boolean checkCache(Composite composite, Point totalSize) {
		if (!totalSize.equals(cachedTotalSize)) {
			if (totalSize.x != cachedTotalSize.x && totalSize.x != SWT.DEFAULT) {
				// x-values differ and the given value is not SWT.DEFAULT
				return false;
			}
			if (totalSize.y != cachedTotalSize.x && totalSize.y != SWT.DEFAULT) {
				// y-values differ and the given value is not SWT.DEFAULT
				return false;
			}
		}

		if (composite.getChildren().length != cachedSizes.keySet().size()) {
			return false;
		}

		for (Control current : composite.getChildren()) {
			if (!cachedLocations.containsKey(current) || !cachedSizes.containsKey(current)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Shrinks the total size according to the set margins if the total size allows
	 * for the margins to be placed in the first place
	 * 
	 * @param totalSize
	 *            The total size to adjust
	 */
	protected void adjustTotalSize(Point totalSize) {
		if (totalSize.x > 2 * horizontalMarginWidth) {
			totalSize.x -= 2 * horizontalMarginWidth;
		}
		if (totalSize.y > 2 * verticalMarginWidth) {
			totalSize.y -= 2 * verticalMarginWidth;
		}
	}

	/**
	 * Clears all cached values in this layout
	 */
	protected void flushCache() {
		cachedSizes.clear();
		cachedLocations.clear();
	}

}
