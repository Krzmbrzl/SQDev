package raven.sqdev.editors;

/**
 * An interface describing a class that provides macro-support
 * 
 * @author Raven
 *
 */
public interface IMacroSupport {
	
	/**
	 * Adds a macro to this <code>IMacroSupport</code>
	 * 
	 * @param macro
	 *            The <code>Macro</code> to add
	 */
	public void addMacro(Macro macro);
	
	/**
	 * Removes the given macro
	 * 
	 * @param macro
	 *            The <code>Macro</code> to remove
	 */
	public void removeMacro(Macro macro);
}
