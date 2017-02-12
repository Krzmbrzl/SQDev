package raven.sqdev.interfaces;

import raven.sqdev.exceptions.BadSyntaxException;

/**
 * An interface for classes that are saveable in a (human) readable textFile
 * 
 * @author Raven
 * 
 */
public interface ISaveable {
	
	/**
	 * Gets the class's String representation that should be used in order to
	 * save this object's values in a file that can later on be used to reload
	 * the object
	 * 
	 * @return The saveable String representation of this object
	 */
	public String getSaveableFormat();
	
	/**
	 * Will recreate this object according to the savedFormat given.
	 * 
	 * @param savedFormat
	 *            The String representation this object should adapt to. Has to
	 *            be return <code>true</code> with
	 *            <code>isSaveFormat(String format)</code>
	 * @return <code>True</code> if the recreation was successful.
	 *         <code>False</code> otherwise
	 * @throws BadSyntaxException
	 *             If the input is invalid
	 */
	public boolean recreateFrom(String savedFormat) throws BadSyntaxException;
	
	/**
	 * Checks if the given String is in the proper format to use
	 * <code>recreateFrom(String savedFormat)</code> with
	 * 
	 * @param format
	 *            The String to test
	 * @return <code>True</code> if the given String is in the proper format,
	 *         <code>False</code> otherwise.
	 */
	public boolean isSaveFormat(String format);
	
}
