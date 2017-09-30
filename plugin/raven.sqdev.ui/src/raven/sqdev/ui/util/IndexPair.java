package raven.sqdev.ui.util;

import org.eclipse.core.runtime.Assert;

/**
 * A class storing two indices and provides some utility for them
 * 
 * @author Raven
 *
 */
public class IndexPair {
	
	/**
	 * The start index
	 */
	private int startIndex;
	/**
	 * The stop index
	 */
	private int stopIndex;
	
	
	/**
	 * Creates a new <code>IndexPair</code> with the default value 0 for both
	 * indices
	 */
	public IndexPair() {
		initialize(0);
	}
	
	/**
	 * Creates a new <code>IndexPair</code> with the given values. The values
	 * have to be valid indices
	 * 
	 * @param startIndex
	 *            The value for the start index
	 * @param stopIndex
	 *            The value for the stop index (has to be greater or equal to
	 *            the start one)
	 */
	public IndexPair(int startIndex, int stopIndex) {
		Assert.isTrue(startIndex > 0 && stopIndex > 0, "Indices must not be less than zero");
		Assert.isTrue(stopIndex >= startIndex,
				"The stop index has to be greater than the start one!");
		
		this.startIndex = startIndex;
		this.stopIndex = stopIndex;
	}
	
	/**
	 * Sets the start index
	 * 
	 * @param value
	 *            The new index value
	 */
	public void setStartIndex(int value) {
		Assert.isTrue(value >= 0, "Index has to be greater 0");
		
		startIndex = value;
	}
	
	/**
	 * Gets the value o the start index
	 */
	public int getStartIndex() {
		return startIndex;
	}
	
	/**
	 * Increases the value of the start index by 1 if the resulting index is
	 * less or equal to the stop index
	 */
	public void increaseStartIndex() {
		if (startIndex < stopIndex) {
			startIndex++;
		}
	}
	
	/**
	 * Increases the value of the start index by the given amount if the
	 * resulting index is less or equal to the stop index
	 * 
	 * @param amount
	 *            The amount that should be added to the index (has to be
	 *            positive)
	 */
	public void increaseStartIndex(int amount) {
		Assert.isTrue(amount >= 0, "Can't increase with negative number!");
		
		if (startIndex + amount <= stopIndex) {
			startIndex += amount;
		}
	}
	
	/**
	 * Decreases the value of the start index by 1 if the resulting index is
	 * still greater or equal to 0
	 */
	public void decreaseStartIndex() {
		if (startIndex > 0) {
			startIndex--;
		}
	}
	
	/**
	 * Decreases the value of the start index by the given amount if the
	 * resulting index is still greater or equal to 0
	 * 
	 * @param amount
	 *            The amount the index should be decreased. Has to be a positive
	 *            number
	 */
	public void decreaseStartIndex(int amount) {
		Assert.isTrue(amount >= 0, "Can't decrease with negative number");
		
		if (startIndex - amount >= 0) {
			startIndex = startIndex - amount;
		} else {
			// only decrease to 0
			startIndex = 0;
		}
	}
	
	/**
	 * Sets the value for the stop index.
	 * 
	 * @param value
	 *            The new value for the stop index. Has to be greater or equal
	 *            to the start index
	 */
	public void setStopIndex(int value) {
		Assert.isTrue(value >= 0, "Index has to be greater 0");
		Assert.isTrue(value >= startIndex, "The stop index has to be greater than the start one");
		
		stopIndex = value;
	}
	
	/**
	 * Gets the value of the stop index
	 */
	public int getStopIndex() {
		return stopIndex;
	}
	
	/**
	 * Gets the difference between the two indices
	 */
	public int getDifference() {
		return getStopIndex() - getStartIndex();
	}
	
	/**
	 * Increases the value of the stop index by 1
	 */
	public void increaseStopIndex() {
		stopIndex++;
	}
	
	/**
	 * Increases the value of the stop index by the given amount
	 * 
	 * @param amount
	 *            The amount that should be added to the index (has to be
	 *            positive)
	 */
	public void increaseStopIndex(int amount) {
		Assert.isTrue(amount >= 0, "Can't increase with negative number!");
		
		stopIndex += amount;
	}
	
	/**
	 * Decreases the value of the stop index by 1 if the resulting index is
	 * still greater or equal to the start index
	 */
	public void decreaseStopIndex() {
		if (stopIndex > startIndex) {
			stopIndex--;
		}
	}
	
	/**
	 * Decreases the value of the stop index by the given amount if the
	 * resulting index is still greater or equal to the start index
	 * 
	 * @param amount
	 *            The amount the index should be decreased. Has to be a positive
	 *            number
	 */
	public void decreaseStopIndex(int amount) {
		Assert.isTrue(amount >= 0, "Can't decrease with negative number");
		
		if (stopIndex - amount >= startIndex) {
			stopIndex = stopIndex - amount;
		}
	}
	
	/**
	 * Initializes the pair by setting both indices to the given value
	 * 
	 * @param value
	 *            The start value for both indices. Has to be greater or equal
	 *            to 0
	 */
	public void initialize(int value) {
		Assert.isTrue(value >= 0, "Value has to be > 0");
		
		startIndex = value;
		stopIndex = value;
	}
	
	/**
	 * Decreases both indices by the given amount
	 * 
	 * @param amount
	 *            The amount the indices should be decreased
	 */
	public void decrease(int amount) {
		if (amount < 0) {
			// Negative decrease is increase
			increase(-amount);
		} else {
			decreaseStartIndex(amount);
			decreaseStopIndex(amount);
		}
	}
	
	/**
	 * Inccreases both indices by the given amount
	 * 
	 * @param amount
	 *            The amount the indices should be increased (Has to be a
	 *            positive number)
	 */
	public void increase(int amount) {
		if (amount < 0) {
			decrease(-amount);
		} else {
			increaseStartIndex(amount);
			increaseStopIndex(amount);
		}
	}
	
	/**
	 * Checks whether the given index is contained in this IndexPair
	 * 
	 * @param index
	 *            The index to check
	 */
	public boolean contains(int index) {
		return (index >= getStartIndex() && index < getStopIndex());
	}
	
	@Override
	public String toString() {
		return "Start: " + getStartIndex() + " - Stop: " + getStopIndex();
	}
}
