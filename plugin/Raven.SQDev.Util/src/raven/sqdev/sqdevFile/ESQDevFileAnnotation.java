package raven.sqdev.sqdevFile;

import java.util.ArrayList;
import java.util.List;

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

		@Override
		public String validate() {
			for (String currentValue : getValues()) {
				if (currentValue == null || currentValue.trim().isEmpty()) {
					return "A value has to be specified!";
				}
			}

			return null;
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

		@Override
		public String validate() {
			for (String currentValue : getValues()) {
				if (currentValue == null || currentValue.trim().isEmpty()) {
					return "A value has to be specified!";
				}
			}

			return null;
		}
	};

	/**
	 * The values of this annotation
	 */
	protected List<String> values;

	/**
	 * This method is deprecated. Use
	 * {@linkplain ESQDevFileVersion#getAnnotationMatcher(ESQDevFileAnnotation, CharSequence)}
	 * instead.<br>
	 * <br>
	 * 
	 * Checks if the given line contains a valid annotation in a SQDevFile.<br>
	 * 
	 * @param inputLine
	 *            The line to be checked
	 * @return <code>True</code> if it's an annotation
	 */
	@Deprecated
	public static boolean isAnnotation(String inputLine) {
		inputLine = inputLine.trim();
		if (!inputLine.startsWith("@")) {
			// An annotation has to start with "@"
			return false;
		}

		String possibleAnnotation = (inputLine.contains(" ")) ? inputLine.substring(1, inputLine.indexOf(" "))
				: inputLine.substring(1);

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
		return (values == null ? new ArrayList<>() : new ArrayList<String>(values));
	}

	/**
	 * Sets the list of values of this annotation
	 * 
	 * @param values
	 */
	public void setValues(Iterable<String> values) {
		this.values = new ArrayList<>();

		for (String current : values) {
			this.values.add(current);
		}
	}

	/**
	 * Sets the list of values of this annotation
	 * 
	 * @param values
	 */
	public void setValues(String[] values) {
		this.values = new ArrayList<>();

		for (String current : values) {
			this.values.add(current);
		}
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
	 * Tries to resolve the given String to an {@link ESQDevFileAnnotation}. The
	 * resolving is case-insensitive.
	 * 
	 * @param attr
	 *            The String to resolve
	 * @return The respective annotation or <code>null</code> if none could be found
	 */
	public static ESQDevFileAnnotation resolve(String attr) {
		attr = attr.toLowerCase();

		for (ESQDevFileAnnotation currentAnnotation : ESQDevFileAnnotation.values()) {
			if (currentAnnotation.toString().toLowerCase().equals(attr)) {
				return currentAnnotation;
			}
		}

		return null;
	}

	/**
	 * Gets the description for this annotation
	 */
	public abstract String getDescription();

	/**
	 * Validates the currently set value
	 * 
	 * @return The error message describing what's wrong or <code>null</code> if
	 *         everything's alright
	 */
	public abstract String validate();

	/**
	 * Clears all set values
	 */
	public void clear() {
		if (values != null) {
			values.clear();
		}
	}
}
