package raven.sqdev.interfaces;

import java.util.Map;

import raven.sqdev.misc.Macro;

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
	public boolean setMacros(Map<String, Macro> macros, boolean update);
	
	/**
	 * Gets the set of macros defined in this editor
	 */
	public Map<String, Macro> getMacros();
}
