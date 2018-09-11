package raven.sqdev.sqdevFile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
		public String getName() {
			return "ignore";
		}

		@Override
		public String getDescription() {
			return "Defines a file that should be ignored during export";
		}

		@Override
		public String validate(ESQDevFileType type) {
			if (!canBeUsedIn(type)) {
				return "This annotation may not be used in the " + type + "-sqdev file!";
			}

			for (String currentValue : getValues()) {
				if (currentValue == null || currentValue.trim().isEmpty()) {
					return "A value has to be specified!";
				}
			}

			return null;
		}

		@Override
		public boolean canBeUsedIn(ESQDevFileType type) {
			return type.isEquals(ESQDevFileType.LINK);
		}
	},
	/**
	 * Defines a file that should not get deleted during the clean of an export
	 */
	PRESERVE {
		@Override
		public String getName() {
			return "preserve";
		}

		@Override
		public String getDescription() {
			return "Defines a file that should not get deleted during the clean of an export";
		}

		@Override
		public String validate(ESQDevFileType type) {
			if (!canBeUsedIn(type)) {
				return "This annotation may not be used in the " + type + "-sqdev file!";
			}

			for (String currentValue : getValues()) {
				if (currentValue == null || currentValue.trim().isEmpty()) {
					return "A value has to be specified!";
				}
			}

			return null;
		}

		@Override
		public boolean canBeUsedIn(ESQDevFileType type) {
			return type.isEquals(ESQDevFileType.LINK);
		}
	},
	MOD {

		@Override
		public String getDescription() {
			return "Indicates that the specified mod is used in this project";
		}

		@Override
		public String validate(ESQDevFileType type) {
			if (!canBeUsedIn(type)) {
				return "This annotation may not be used in the " + type + "-sqdev file!";
			}

			return null;
		}

		@Override
		public boolean canBeUsedIn(ESQDevFileType type) {
			return type.isEquals(ESQDevFileType.PROJECT);
		}

		@Override
		public String getName() {
			return "mod";
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
	 * Checks whether this annotation has any set values
	 */
	public boolean hasValues() {
		return values != null && values.size() > 0;
	}

	/**
	 * Sets the list of values of this annotation
	 * 
	 * @param values
	 *            The values to set - May be <code>null</code>
	 */
	public void setValues(Iterable<String> values) {
		if (values == null) {
			this.values = null;
			return;
		}

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
	 * Gets a pattern that can be used in order to match some input against all the
	 * values of this annotation. If this annotation contains the values "one" and
	 * "two" the resulting pattern will be "one|two". If a value is not prefixed by
	 * {@link #REGEX_PREFIX}, it is quoted via {@linkplain Pattern#quote(String)}
	 * before compilation and if it is, the prefix will be removed but the rest of
	 * the value will not be quoted.
	 * 
	 * @param flags
	 *            Flags that are passed to {@linkplain Pattern#compile(String, int)}
	 *            in order to influence the compiled pattern.
	 * @param prefix
	 *            A prefix that will be pre-appended to each value
	 * @see Pattern
	 */
	public Pattern getMatchPattern(int flags, String prefix) {
		StringBuilder pattern = new StringBuilder();

		if (prefix.length() > 0) {
			pattern.append(prefix + "(");
		}

		for (int i = 0; i < getValues().size(); i++) {
			String currentValue = values.get(i);

			if (currentValue == null || currentValue.trim().isEmpty() || currentValue.equals(REGEX_PREFIX)) {
				// non-existing or empty values (or an expression that would lead to an empty
				// RegEx
				continue;
			}

			if (currentValue.startsWith(REGEX_PREFIX)) {
				// the value is to be interpreted as a regular expression
				// remove the prefix though
				currentValue = currentValue.substring(REGEX_PREFIX.length());
			} else {
				// quote the input as it must not be interpreted as a regular expression
				currentValue = Pattern.quote(currentValue);
			}

			if (i + 1 == values.size()) {
				// no '|' for the last element
				pattern.append(currentValue);
			} else {
				pattern.append(currentValue + "|");
			}
		}

		if (prefix.length() > 0) {
			pattern.append(")");
		}

		return Pattern.compile(pattern.toString(), flags);
	}

	/**
	 * Gets a pattern that can be used in order to match some input against all the
	 * values of this annotation. If this annotation contains the values "one" and
	 * "two" the resulting pattern will be "one|two". If a value is not prefixed by
	 * {@link #REGEX_PREFIX}, it is quoted via {@linkplain Pattern#quote(String)}
	 * before compilation and if it is, the prefix will be removed but the rest of
	 * the value will not be quoted.
	 * 
	 * @param flags
	 *            Flags that are passed to {@linkplain Pattern#compile(String, int)}
	 *            in order to influence the compiled pattern.
	 * @see Pattern
	 */
	public Pattern getMatchPattern(int flags) {
		return getMatchPattern(flags, "");
	}

	/**
	 * Gets a pattern that can be used in order to match some input against all the
	 * values of this annotation. If this annotation contains the values "one" and
	 * "two" the resulting pattern will be "one|two". If a value is not prefixed by
	 * {@link #REGEX_PREFIX}, it is quoted via {@linkplain Pattern#quote(String)}
	 * before compilation and if it is, the prefix will be removed but the rest of
	 * the value will not be quoted.
	 * 
	 * in order to influence the compiled pattern.
	 * 
	 * @param prefix
	 *            A prefix that will be pre-appended to each value
	 * @see Pattern
	 */
	public Pattern getMatchPattern(String prefix) {
		return getMatchPattern(0, prefix);
	}

	/**
	 * Gets a pattern that can be used in order to match some input against all the
	 * values of this annotation. If this annotation contains the values "one" and
	 * "two" the resulting pattern will be "one|two". If a value is not prefixed by
	 * {@link #REGEX_PREFIX}, it is quoted via {@linkplain Pattern#quote(String)}
	 * before compilation and if it is, the prefix will be removed but the rest of
	 * the value will not be quoted.
	 */
	public Pattern getMatchPattern() {
		return getMatchPattern(0, "");
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
	 * @param type
	 *            The {@linkplain ESQDevFileType} this annotation is being used in
	 * 
	 * @return The error message describing what's wrong or <code>null</code> if
	 *         everything's alright
	 */
	public abstract String validate(ESQDevFileType type);

	/**
	 * Clears all set values
	 */
	public void clear() {
		if (values != null) {
			values.clear();
		}
	}

	/**
	 * Checks whether this annotation can be used inside a sqdev-file of the given
	 * type
	 * 
	 * @param type
	 *            The {@linkplain ESQDevFileType} to check
	 * @return Whether this annotation can be used in it or not
	 */
	public abstract boolean canBeUsedIn(ESQDevFileType type);
	
	/**
	 * Gets the name of this annotation
	 */
	public abstract String getName();
	
	@Override
	public String toString() {
		return getName();
	}

	/**
	 * The prefix indicating that the given value (after the prefix) should be
	 * interpreted as a regular expression
	 */
	public static final String REGEX_PREFIX = "<RegEx>";
}
