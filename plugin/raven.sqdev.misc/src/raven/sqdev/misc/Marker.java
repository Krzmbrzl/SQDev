package raven.sqdev.misc;

/**
 * A container for all information associated with a marker
 * @author Raven
 *
 */
public class Marker {
	
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
