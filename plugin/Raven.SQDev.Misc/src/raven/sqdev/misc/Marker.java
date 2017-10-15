package raven.sqdev.misc;

/**
 * A container for all information associated with a marker
 * 
 * @author Raven
 *
 */
public class Marker {

	private String type;
	private int offset;
	private int length;
	private String message;
	private int severity;

	public Marker(String type, int offset, int length, String message, int severity) {
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

	@Override
	public boolean equals(Object other) {
		if (!other.getClass().equals(getClass())) {
			return false;
		}

		Marker otherMarker = (Marker) other;

		if (otherMarker.getLength() != getLength()) {
			return false;
		}
		if (!otherMarker.getMessage().equals(getMessage())) {
			return false;
		}
		if (otherMarker.getOffset() != getOffset()) {
			return false;
		}
		if (otherMarker.getSeverity() != getSeverity()) {
			return false;
		}
		if (!otherMarker.getType().equals(getType())) {
			return false;
		}

		return true;
	}

	/**
	 * Creates a message describing where this Marker and the given one differ from
	 * each other. Each difference is listed in a new line (starting at line 1)
	 * 
	 * @param other
	 *            The Marker to compare to
	 * @return The resulting message. If both markers are equal <code>null</code> is
	 *         returned
	 */
	public String createDifferenceMessage(Marker other) {
		if (this.equals(other)) {
			return null;
		}

		StringBuilder builder = new StringBuilder("Markers differ in the following points:\n");

		if (!other.getType().equals(getType())) {
			builder.append("\tType: Expected \"" + getType() + "\" but got \"" + other.getType() + "\"\n");
		}
		if (other.getSeverity() != getSeverity()) {
			builder.append("\tSeverity: Expected " + getSeverity() + " but got " + other.getSeverity() + "\n");
		}
		if (other.getOffset() != getOffset()) {
			builder.append("\tOffset: Expected " + getOffset() + " but got " + other.getOffset() + "\n");
		}
		if (other.getLength() != getLength()) {
			builder.append("\tLength: Expected " + getLength() + " but got " + other.getLength() + "\n");
		}
		if (!other.getMessage().equals(getMessage())) {
			builder.append("\tMessage: Expected \"" + getMessage() + "\" but got \"" + other.getMessage() + "\"\n");
		}

		return builder.toString();
	}

}
