package raven.sqdev.parser.sqf;

/**
 * This enum contains values for describing a relative position
 * 
 * @author Raven
 *
 */
public enum ERelativePosition {
	/**
	 * This enum describes a centered postion
	 */
	CENTER,
	/**
	 * This enum describes a position to the left
	 */
	LEFT,
	/**
	 * This enum describes a position on the right
	 */
	RIGHT,
	/**
	 * This enum describes the absence of a relative position and is therefore
	 * to be treated as a <code>null</code> type
	 */
	NONE
}
