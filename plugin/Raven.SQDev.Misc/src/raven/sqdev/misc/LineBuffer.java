package raven.sqdev.misc;

import org.eclipse.core.runtime.Assert;

/**
 * A class serving as a line buffer of the given size
 * 
 * @author Raven
 *
 */
public class LineBuffer {
	
	/**
	 * The size of this buffer
	 */
	private int size;
	
	/**
	 * The actual buffer
	 */
	private String[] buffer;
	
	/**
	 * The index of the next element in the buffer
	 */
	private int storeIndex;
	
	/**
	 * The index of the line that will be returned if {@link #previousLine()} is
	 * invoked
	 */
	private int lineReturnIndex;
	
	/**
	 * Indicates whether the lines have been returned
	 */
	private boolean allLinesReturned;
	
	/**
	 * Whether or not the buffer is requested to return lines in forward
	 * direction
	 */
	private boolean forward;
	
	/**
	 * Indicating whether it is allowed to get the next line
	 */
	private boolean canGetNextLine;
	
	/**
	 * Creates a new instance of this <code>LineBuffer</code> of the given size
	 * 
	 * @param size
	 *            The size of this buffer (e.g how many lines it should buffer)
	 */
	public LineBuffer(int size) {
		Assert.isTrue(size > 0, "Size must be greater than 0!");
		
		this.size = size;
		
		buffer = new String[size];
		storeIndex = 0;
		lineReturnIndex = 0;
		
		allLinesReturned = false;
		forward = false;
		canGetNextLine = false;
	}
	
	/**
	 * Gets the size of this buffer
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Gets the actual buffer
	 */
	public String[] getBuffer() {
		return buffer;
	}
	
	/**
	 * Adds the given line to the buffer. Adding a line to the buffer will
	 * effectively reset the buffer and with that the line that will be returned
	 * by {@link #previousLine()}
	 * 
	 * @param line
	 *            The line to add to the buffer
	 */
	public void addLine(String line) {
		buffer[getIndex()] = line;
	}
	
	/**
	 * Gets the previous line of the buffer depending on the current state of
	 * it. Calling this method again will return the line prior to the line
	 * obtained before. If the end of the buffer is reached <code>null</code> is
	 * returned
	 * 
	 * @return The respective line or <code>null</code>
	 */
	public String previousLine() {
		if (allLinesReturned && !forward) {
			return null;
		}
		
		int index = getPreviousLineReturnIndex();
		
		if (index == this.storeIndex) {
			allLinesReturned = true;
		}
		
		canGetNextLine = true;
		
		return buffer[index];
	}
	
	/**
	 * Gets the next line in the buffer depending on the buffer's state. If the
	 * end of the buffer is reached <code>null</code> will be returned
	 * 
	 * @return The respective line or <code>null</code>
	 */
	public String nextLine() {
		if (!canGetNextLine || (allLinesReturned && forward)) {
			return null;
		}
		
		int index = getNextLineReturnIndex();
		
		if (storeIndex - index == 1 || (index == size - 1 && storeIndex == 0)) {
			allLinesReturned = true;
		}
		
		return buffer[index];
	}
	
	/**
	 * Checks whether there is a previous line that can be returned
	 */
	public boolean hasPreviousLine() {
		boolean hasPrevious = previousLine() != null;
		
		nextLine(); // reset buffer
		
		return hasPrevious;
	}
	
	/**
	 * Checks whether there is a next line that can be returned
	 */
	public boolean hasNextLine() {
		boolean hasNext = nextLine() != null;
		
		previousLine(); // reset buffer
		
		return hasNext;
	}
	
	/**
	 * Resets this buffer so that {@link #previousLine()} will return the most
	 * recently added line. This does not clear the buffer!
	 */
	public void reset() {
		if (storeIndex == 0) {
			lineReturnIndex = size - 1;
		} else {
			lineReturnIndex = storeIndex - 1;
		}
		
		allLinesReturned = false;
	}
	
	/**
	 * Clears and resets this buffer
	 */
	public void clear() {
		for (int i = 0; i < size; i++) {
			buffer[i] = null;
		}
		
		storeIndex = 0;
		lineReturnIndex = 0;
		
		allLinesReturned = false;
	}
	
	/**
	 * Checks whether this buffer contains the given line
	 * 
	 * @param str
	 *            The line to search for
	 * @return Whether the line is contained
	 */
	public boolean contains(String str) {
		// store buffer state
		int lineReturnIndex = this.lineReturnIndex;
		boolean allLinesReturned = this.allLinesReturned;
		boolean forward = this.forward;
		
		boolean contains = pointAt(str);
		
		reset();
		
		// restore buffer to previous state
		this.lineReturnIndex = lineReturnIndex;
		this.allLinesReturned = allLinesReturned;
		this.forward = forward;
		
		return contains;
	}
	
	/**
	 * Makes the buffer point at the given line.
	 * 
	 * @param line
	 *            The line within the buffer it should point to
	 * @return Whether the action could be performed successfully (if the line
	 *         is contained in the buffer)
	 */
	public boolean pointAt(String line) {
		reset();
		
		for (int i = 0; i < getSize(); i++) {
			String compare = previousLine();
			
			if (compare == null) {
				reset();
				return false;
			} else {
				if (compare.equals(line)) {
					return true;
				}
			}
		}
		
		reset();
		
		return false;
	}
	
	/**
	 * Gets the current index and increases the index by 1 afterwards. If the
	 * index exceeds the length it will be set to 0. By invoking this method the
	 * lineReturnIndex will be set to the index's value
	 * 
	 * @return The new index
	 */
	private int getIndex() {
		if (storeIndex == size) {
			storeIndex = 0;
		}
		
		// reset return index and flag
		lineReturnIndex = storeIndex;
		allLinesReturned = false;
		canGetNextLine = false;
		
		
		return storeIndex++;
	}
	
	/**
	 * Gets the index of the line that has to be returned next. The given index
	 * will point to the previous line in the buffer. At the same time it
	 * decreases the index by 1. If the index is -1 will be set to the buffer
	 * size minus one
	 * 
	 * @return The new index
	 */
	private int getPreviousLineReturnIndex() {
		if (lineReturnIndex == -1) {
			lineReturnIndex = size - 1;
		}
		
		if (forward) {
			forward = false;
			allLinesReturned = false;
			
			// decrease index by 2 in order to point to the correct element
			getPreviousLineReturnIndex();
			getPreviousLineReturnIndex();
			
			return getPreviousLineReturnIndex();
		}
		
		return lineReturnIndex--;
	}
	
	/**
	 * Gets the index of the line that has to be returned next. The given index
	 * will point to the next line in the buffer. At the same time it increases
	 * the index by 1. If the index is equal to the size of the buffer it will
	 * be set to 0
	 * 
	 * @return The new index
	 */
	private int getNextLineReturnIndex() {
		if (lineReturnIndex == size) {
			lineReturnIndex = 0;
		}
		
		if (!forward) {
			forward = true;
			allLinesReturned = false;
			
			// Increase index by 2 in order to point to the correct position
			getNextLineReturnIndex();
			getNextLineReturnIndex();
			
			return getNextLineReturnIndex();
		}
		
		return lineReturnIndex++;
	}
	
}
