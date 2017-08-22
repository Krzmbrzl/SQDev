package raven.sqdev.editors.sqfeditor.parsing;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.Position;

import raven.sqdev.infoCollection.base.Variable;
import raven.sqdev.interfaces.IMarkerSupport;
import raven.sqdev.interfaces.ISQFParseResult;

/**
 * A basic implementation of an {@link ISQFParseResult}
 * 
 * @author Raven
 *
 */
public class SQFParseResult implements ISQFParseResult {
	
	private class Marker {
		private String type;
		private int offset;
		private int length;
		private String message;
		private int severity;
		
		public Marker(String type, int offset, int length, String message,
				int severity) {
			this.type = type;
			this.offset = offset;
			this.length = length;
			this.message = message;
			this.severity = severity;
		}
		
		public String getType() {
			return type;
		}
		
		public int getOffset() {
			return offset;
		}
		
		public int getLength() {
			return length;
		}
		
		public int getSeverity() {
			return severity;
		}
		
		public String getMessage() {
			return message;
		}
	}
	
	/**
	 * A list of local variables declared during the parsing
	 */
	private List<Variable> declaredLocalVariables;
	
	/**
	 * A list of global variables declared during the parsing
	 */
	private List<Variable> declaredGlobalVariables;
	/**
	 * A list of added markers
	 */
	private List<Marker> markerList;
	/**
	 * A list of folding areas
	 */
	private List<Position> foldingAreas;
	
	
	public SQFParseResult() {
		markerList = new ArrayList<Marker>();
		foldingAreas = new ArrayList<Position>();
	}
	
	@Override
	public void setVariables(List<Variable> localVariables,
			List<Variable> globalVariables) {
		
		declaredLocalVariables = localVariables;
		declaredGlobalVariables = globalVariables;
	}
	
	@Override
	public void addMarker(String type, int offset, int length, String message,
			int severity) {
		markerList.add(new Marker(type, offset, length, message, severity));
	}
	
	@Override
	public void addFoldingArea(Position position) {
		foldingAreas.add(position);
	}
	
	public List<Variable> getDeclaredLocalVariables() {
		return declaredLocalVariables;
	}
	
	public List<Variable> getDeclaredGlobalVariables() {
		return declaredGlobalVariables;
	}
	
	public List<Marker> getMarkerList() {
		return markerList;
	}
	
	public List<Position> getFoldingAreas() {
		return foldingAreas;
	}
	
	/**
	 * Applies the registered markers on the given <code>IMarkerSupport</code>
	 * 
	 * @param support
	 *            The <code>IMarkerSupport</code> to apply the gathered markers
	 *            to
	 */
	public void applyMarkersTo(IMarkerSupport support) {
		for (Marker currentMarker : markerList) {
			support.createMarker(currentMarker.getType(),
					currentMarker.getOffset(), currentMarker.getLength(),
					currentMarker.getMessage(), currentMarker.getSeverity());
		}
	}
	
}
