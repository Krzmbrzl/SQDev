package raven.sqdev.editors.sqfeditor.testing;

import java.util.ArrayList;
import java.util.List;

import raven.sqdev.editors.MarkerInformation;
import raven.sqdev.editors.sqfeditor.SQF_Editor;
import raven.sqdev.infoCollection.base.Variable;
import raven.sqdev.misc.Macro;

/**
 * A test editor that is used in order to test the SQF parsing
 * 
 * @author Raven
 *
 */
public class SQF_TestEditor extends SQF_Editor {
	
	/**
	 * A list of all created markers
	 */
	private List<MarkerInformation> createdMarkers;
	
	private List<String> binaryKeywords;
	private List<String> unaryKeywords;
	private List<String> nularKeywords;
	
	public SQF_TestEditor(List<String> binaryKeywords,
			List<String> unaryKeywords, List<String> nularKeywords,
			List<Macro> macros) {
		createdMarkers = new ArrayList<MarkerInformation>();
		
		this.binaryKeywords = binaryKeywords;
		this.unaryKeywords = unaryKeywords;
		this.nularKeywords = nularKeywords;
		setMacros(macros, false);
	}
	
	@Override
	public void createMarker(String type, int offset, int length,
			String message, int severity) {
		
		createdMarkers.add(new MarkerInformation(type, -1, offset, length,
				severity, message));
	}
	
	@Override
	public List<String> getBinaryKeywords() {
		return binaryKeywords;
	}
	
	@Override
	public List<String> getUnaryKeywords() {
		return unaryKeywords;
	}
	
	@Override
	public List<String> getNularKeywords() {
		return nularKeywords;
	}
	
	@Override
	public void update(boolean reconfigure) {
		// do nothing
	}
	
	/**
	 * Cheks whether the given local variable has been reported to this editor
	 * 
	 * @param var
	 *            The Variable to check for
	 */
	public boolean containsLocalVariable(Variable var) {
		return getLocalVariables().contains(var);
	}
	
	/**
	 * Cheks whether the given global variable has been reported to this editor
	 * 
	 * @param var
	 *            The Variable to check for
	 */
	public boolean containsGlobalVariable(Variable var) {
		return getGlobalVariables().contains(var);
	}
	
	/**
	 * Cheks whether the given variable has been reported to this editor
	 * 
	 * @param var
	 *            The Variable to check for
	 */
	public boolean containsVariable(Variable var) {
		return getLocalVariables().contains(var)
				|| getGlobalVariables().contains(var);
	}
	
	/**
	 * Checks whether there are any reported markers
	 */
	public boolean containsMarker() {
		return createdMarkers.size() > 0;
	}
	
	/**
	 * Checks whether the given marker has been reported to this editor
	 * 
	 * @param marker
	 *            The marker to check for. <b>NOTE: The line attribute of this
	 *            has to be -1.</b>
	 */
	public boolean containsMarker(MarkerInformation marker) {
		return createdMarkers.contains(marker);
	}
	
	@Override
	public boolean setMacros(List<Macro> macros, boolean update) {
		macroNames.clear();
		for (Macro currentMacro : macros) {
			macroNames.add(currentMacro.getKeyword());
		}
		
		this.macros = new ArrayList<Macro>(macros);
		
		return true;
	}
	
}
