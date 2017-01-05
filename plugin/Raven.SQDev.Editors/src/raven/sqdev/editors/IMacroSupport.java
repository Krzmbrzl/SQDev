package raven.sqdev.editors;

import java.util.List;

/**
 * An interface describing a class that provides macro-support
 * 
 * @author Raven
 *
 */
public interface IMacroSupport {
	
	/**
	 * Adds the given macros to this <code>IMacroSupport</code>
	 * 
	 * @param macros
	 *            The collection of <code>Macros</code> to add
	 * @param update
	 *            Whether to allow update of the editor
	 * @return Whether the macros have been updated
	 */
	public boolean setMacros(List<Macro> macros, boolean update);
	
	/**
	 * Gets the collection of macros defined in this editor
	 */
	public List<Macro> getMacros();
}
