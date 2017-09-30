package raven.sqdev.sqdevFile;

import java.util.ArrayList;

/**
 * Contains all valid annotations that can be used in a {@linkplain #SDevFile}
 * 
 * @author Raven
 * 		
 */
public enum ESQDevFileAnnotation {
	/**
	 * Defines a file that should be ignored during export
	 */
	IGNORE {
		@Override
		public String toString() {
			return "ignore";
		}
		
		@Override
		public String getDescription() {
			return "Defines a file that should be ignored during export";
		}
	},
	/**
	 * Defines a file that should not get deleted during the clean of an export
	 */
	PRESERVE {
		@Override
		public String toString() {
			return "preserve";
		}
		
		@Override
		public String getDescription() {
			return "Defines a file that should not get deleted during the clean of an export";
		}
	};
	
	/**
	 * The values of this annotation
	 */
	protected ArrayList<String> values;
	
	/**
	 * Checks if the given line contains a valid annotation in a SQDevFile.<br>
	 * 
	 * @param inputLine
	 *            The line to be checked
	 * @return <code>True</code> if it's an annotation
	 */
	public static boolean isAnnotation(String inputLine) {
		inputLine = inputLine.trim();
		if (!inputLine.startsWith("@")) {
			// An annotation has to start with "@"
			return false;
		}
		
		String possibleAnnotation = (inputLine.contains(" "))
				? inputLine.substring(1, inputLine.indexOf(" ")) : inputLine.substring(1);
				
		for (ESQDevFileAnnotation current : ESQDevFileAnnotation.values()) {
			if (current.toString().equals(possibleAnnotation)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Gets the list of values of this annotation
	 */
	public ArrayList<String> getValues() {
		return new ArrayList<String>(values);
	}
	
	/**
	 * Sets the list of values of this annotation
	 * 
	 * @param values
	 */
	public void setValues(ArrayList<String> values) {
		this.values = new ArrayList<String>(values);
	}
	
	/**
	 * Adds a value to the list
	 * 
	 * @param value
	 *            The value to add
	 */
	public void addValue(String value) {
		if (values == null) {
			values = new ArrayList<String>();
		}
		values.add(value);
	}
	
	/**
	 * Gets the description for this annotation
	 */
	public abstract String getDescription();
}
