package raven.sqdev.sqdevFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.exceptions.SQDevFileIsInvalidException;
import raven.sqdev.exceptions.SQDevFileNoSuchAttributeException;

/**
 * This enum is for supporting multiple versions of the sqdev-file format
 * specification
 * 
 * @author Raven
 *
 */
public enum ESQDevFileVersion {
	V_1_0 {
		@Override
		public String getVersion() {
			return "1.0";
		}

		@Override
		public String getFormatDescription(boolean insertable) {
			StringBuilder builder = new StringBuilder();

			builder.append("Each statement has to be on its own line\n");
			builder.append(
					"Attributes assign a value via \"=\" (WS gets removed automatically) and may only be specified once\n");
			builder.append(
					"Annotations start with a \"@\", assign their value by encapsulating them in quotation marks and may be used multiple times\n");
			builder.append("Attributes and Annotations are case-insensitive\n");
			builder.append("Singleline comments (starting with '//') are supported.");

			return insertable ? "// " + builder.toString().replace("\n", "\n// ") : builder.toString();
		}

		@Override
		public void validate(CharSequence input, ISQDevFileErrorListener listener, ESQDevFileType type) {
			String[] lines = input.toString().split("\n");

			int offset = 0;
			for (String currentLine : lines) {
				int lineLength = currentLine.length();

				currentLine = getCommentMatcher(currentLine).replaceAll("");

				currentLine = currentLine.trim();

				if (currentLine.isEmpty() || currentLine.equals(getHeader())) {
					offset += lineLength + 1;
					continue;
				}

				Matcher matcher = Pattern.compile("@(\\w+)\\h*\"(.*?)\"").matcher(currentLine);
				if (matcher.matches()) {
					// annotation
					String strAnnotation = matcher.group(1);
					String value = matcher.group(2);

					ESQDevFileAnnotation annotation = ESQDevFileAnnotation.resolve(strAnnotation);

					if (annotation == null) {
						if (!listener.error(offset + matcher.start(1), strAnnotation.length(),
								"Unknown SQDevFileAnnotation: \"" + strAnnotation + "\"!")) {
							// listener indicated to stop validating
							return;
						}
					} else {
						// save current value of the annotation
						List<String> prevValues = null;
						if (annotation.hasValues()) {
							prevValues = annotation.getValues();
						}

						annotation.clear();
						annotation.addValue(value);
						String errorMsg = annotation.validate(type);

						if (errorMsg != null) {
							if (!listener.error(offset + matcher.start(2), value.length() + 2,
									"Invalid value for " + annotation + ": " + errorMsg)) {
								// listener indicated to stop validating

								// restore annotation's value
								annotation.setValues(prevValues);

								return;
							}
						}

						// restore annotation's value
						annotation.setValues(prevValues);
					}

					offset += lineLength + 1;
					continue;
				}

				matcher.usePattern(Pattern.compile("(\\w+)\\h*=\\h*(.*)"));
				if (matcher.matches()) {
					// attribute
					String strAttribute = matcher.group(1);
					String value = matcher.group(2);

					ESQDevFileAttribute attribute = ESQDevFileAttribute.resolve(strAttribute);

					if (attribute == null) {
						if (!listener.error(offset + matcher.start(1), strAttribute.length(),
								"Unknown SQDevFileAttribute: \"" + strAttribute + "\"!")) {
							// listener indicated to stop validating
							return;
						}
					} else {
						// store attribute's current value
						String prevValue = attribute.getValue();

						attribute.setValue(value);
						String errorMsg = attribute.validate(type);

						if (errorMsg != null) {
							if (!listener.error(offset + matcher.start(2), value.length(),
									"Invalid value for " + attribute + ": " + errorMsg)) {
								// listener indicated to stop validating

								// restore attribute's value
								attribute.setValue(prevValue);

								return;
							}
						}

						// restore attribute's value
						attribute.setValue(prevValue);
					}

					offset += lineLength + 1;
					continue;
				}

				// neither comment, attribute nor annotation
				if (!listener.error(offset, lineLength,
						"This line does not properly specify an attribute nor an annotation and it is no comment either!")) {
					// listener indicated to stop validating
					return;
				}

				offset += lineLength + 1;
			}
		}

		@Override
		public Matcher getCommentMatcher(CharSequence input) {
			return Pattern.compile("(?:\\n|^)\\h*//.*|(?:[^\"]*(?:\".*?\"))+(//.*)").matcher(input);
		}

		@Override
		public Matcher getAttributeMatcher(ESQDevFileAttribute attribute, CharSequence input) {
			return Pattern.compile(
					"(?:(?:\\n|^)\\s*" + Pattern.quote(attribute.toString())
							+ "\\h*=\\h*)((?:[^/\\n]|/[^/\\n])+)(?://.*)?",
					Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(input);
		}

		@Override
		public Matcher getAnnotationMatcher(ESQDevFileAnnotation annotation, CharSequence input) {
			return Pattern.compile("(?:(?:\\n|^)\\h*@" + Pattern.quote(annotation.toString()) + ")\\h*\"([^\"]*)\".*?",
					Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE).matcher(input);
		}

		@Override
		public CharSequence addAnnotation(ESQDevFileAnnotation annotation, CharSequence content) {
			Matcher matcher = getAnnotationMatcher(annotation, content);

			StringBuilder builder = new StringBuilder();
			boolean firstLine = true;
			for (String currentValue : annotation.getValues()) {
				String line = "@" + annotation + " \"" + currentValue + "\"";
				String linePattern = "@" + Pattern.quote(annotation.toString()) + "\\h*\"" + Pattern.quote(currentValue)
						+ "\"";

				if (Pattern.compile(linePattern).matcher(content).find()) {
					// the annotation is already defined with that exact value -> skip
					continue;
				}

				builder.append((firstLine ? "" : "\n") + line);
				firstLine = false;
			}

			if (builder.length() == 0) {
				return content;
			}

			if (matcher.find()) {
				// Another value for this annotation is already set -> search for last match and
				// add after that
				MatchResult result = matcher.toMatchResult();

				// make sure that the matcher will find the last occurrence
				while (matcher.find()) {
					result = matcher.toMatchResult();
				}

				CharSequence fragment1 = content.subSequence(0, result.end());
				CharSequence fragment2 = content.subSequence(result.end(), content.length());

				content = fragment1 + "\n" + builder + fragment2;
			} else {
				// search for other annotations and insert after them
				matcher = Pattern.compile(".*(\\n|^)\\h*@\\w+\\h*\"[^\"]*\".*").matcher(content);
				if (matcher.find()) {
					CharSequence fragment1 = content.subSequence(0, matcher.end());
					CharSequence fragment2 = content.subSequence(matcher.end(), content.length());

					content = fragment1 + "\n" + builder + fragment2;
				} else {
					// append to end of content, leaving a blank line in between
					content = content + "\n\n" + builder;
				}
			}

			return content;
		}

		@Override
		public CharSequence addAttribute(ESQDevFileAttribute attribute, CharSequence content) {
			Matcher matcher = getAttributeMatcher(attribute, content);

			if (matcher.find()) {
				// This attribute already exists -> overwrite value
				content = content.subSequence(0, matcher.start(matcher.groupCount())) + attribute.getValue()
						+ content.subSequence(matcher.end(matcher.groupCount()), content.length());
			} else {
				// Search for last attribute and add it in the next line
				matcher = Pattern.compile("(\\n|^)\\h*\\w+\\h*=.*").matcher(content);

				if (matcher.find()) {
					MatchResult result = matcher.toMatchResult();

					// make sure that the matcher will find the last occurrence
					while (matcher.find()) {
						result = matcher.toMatchResult();
					}

					// append after last attribute
					CharSequence fragment1 = content.subSequence(0, result.end());
					CharSequence fragment2 = content.subSequence(result.end(), content.length());

					content = fragment1 + "\n" + attribute + " = " + attribute.getValue() + fragment2;
				} else {
					// append to end of content, leaving a blank line in between
					content = content + "\n\n" + attribute + " = " + attribute.getValue();
				}
			}

			return content;
		}
	};

	/**
	 * Gets the proper version object for the header line
	 * 
	 * @param headerLine
	 *            The first line of the content
	 * @return The respective version object or <code>null</code> if none could be
	 *         found
	 */
	public static ESQDevFileVersion getVersion(String headerLine) {
		if (headerLine == null) {
			return null;
		}

		for (ESQDevFileVersion currentVersion : ESQDevFileVersion.values()) {
			if (currentVersion.getHeader().equals(headerLine)) {
				return currentVersion;
			}
		}

		return null;
	}

	/**
	 * Gets the proper version object for the given file (according to its header)
	 * 
	 * @param input
	 *            The input file to determine the version for
	 * @return The respective version object or <code>null</code> if none could be
	 *         found
	 * @throws IOException
	 */
	public static ESQDevFileVersion getVersion(File input) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(input));
		String header = reader.readLine();
		reader.close();

		if (header == null) {
			return null;
		}

		return getVersion(header);
	}


	/**
	 * Gets the most recent version
	 */
	public static ESQDevFileVersion newest() {
		return values()[values().length - 1];
	}

	@Override
	public String toString() {
		return getVersion();
	}

	/**
	 * Gets the header line that is characteristic for this version
	 */
	public String getHeader() {
		return "sqdev-file-version=" + getVersion();
	}

	/**
	 * Gets a preamble containing the header and the format description for this
	 * specific version. The returned sequence can be inserted at the beginning of a
	 * new or empty SQDevFile.
	 */
	public String getPreamble() {
		return getHeader() + "\n" + getFormatDescription(true);
	}

	/**
	 * Checks whether the given input is valid in context of this version
	 * 
	 * @param input
	 *            The {@linkplain CharSequence} to validate
	 * @param The
	 *            {@linkplain ESQDevFileType} to validate the input as
	 */
	public boolean isValid(CharSequence input, ESQDevFileType type) {
		if (validatedInput.containsKey(input)) {
			return validatedInput.get(input);
		}

		AtomicBoolean valid = new AtomicBoolean(true);

		validate(input, new ISQDevFileErrorListener() {

			@Override
			public boolean error(int start, int length, String errorMsg) {
				valid.set(false);

				return false;
			}
		}, type);

		validatedInput.put(input, valid.get());

		return valid.get();
	}

	/**
	 * Processes the given {@link ESQDevFileAttribute} by setting its value to the
	 * one specified in the given input or its default value if it is not specified
	 * in the given input. The previously set value will be cleared beforehand
	 * 
	 * @param attribute
	 *            The {@linkplain ESQDevFileAttribute} whose value is to be set
	 * @param input
	 *            The input to work on
	 * 
	 * @throws SQDevFileNoSuchAttributeException
	 *             If the attribute is not specified in the given input and the
	 *             attribute does not have a default value
	 */
	public void processAttribute(ESQDevFileAttribute attribute, CharSequence input)
			throws SQDevFileNoSuchAttributeException, SQDevFileIsInvalidException {
		ensureValidity(input);
		input = preprocess(input);

		Matcher matcher = getAttributeMatcher(attribute, input);

		if (matcher.find()) {
			attribute.setValue(matcher.group(matcher.groupCount()).trim());
		} else {
			if (attribute.hasDefault()) {
				attribute.setValue(attribute.getDefault());
			} else {
				throw new SQDevFileNoSuchAttributeException(
						"The attribute " + attribute + " is not specified and does not have a default value!");
			}
		}
	}

	/**
	 * Processes the given {@link ESQDevFileAnnotation} by setting its values to the
	 * ones specified in the given input. Any previously set values will be cleared
	 * beforehand.
	 * 
	 * @param attribute
	 *            The {@linkplain ESQDevFileAnnotation} whose values are to be
	 *            determined
	 * @param input
	 *            The input to work on
	 * @throws SQDevFileIsInvalidException
	 */
	public void processAnnotation(ESQDevFileAnnotation annotation, CharSequence input)
			throws SQDevFileIsInvalidException {
		ensureValidity(input);
		input = preprocess(input);

		annotation.clear();

		Matcher matcher = getAnnotationMatcher(annotation, input);

		while (matcher.find()) {
			annotation.addValue(matcher.group(matcher.groupCount()));
		}
	}

	/**
	 * Checks whether the given input contains a specification for the respective
	 * annotation
	 * 
	 * @param annotation
	 *            The annotation to search for
	 * @param input
	 *            The input to search
	 * @return Whether or not the annotation is used in the given input
	 * @throws SQDevFileIsInvalidException
	 */
	public boolean contains(ESQDevFileAnnotation annotation, CharSequence input) throws SQDevFileIsInvalidException {
		ensureValidity(input);
		input = preprocess(input);

		return getAnnotationMatcher(annotation, input).find();
	}

	/**
	 * Checks whether the given input contains a specification for the respective
	 * attribute
	 * 
	 * @param attribute
	 *            The attribute to search for
	 * @param input
	 *            The input to search
	 * @return Whether or not the attribute is used in the given input
	 * @throws SQDevFileIsInvalidException
	 */
	public boolean contains(ESQDevFileAttribute attribute, CharSequence input) throws SQDevFileIsInvalidException {
		ensureValidity(input);
		input = preprocess(input);

		return getAttributeMatcher(attribute, input).find();
	}

	/**
	 * Transforms the given input so that it can be inserted into an SQDevFile using
	 * the current file format version
	 * 
	 * @param input
	 *            The input to transform
	 * @return The transformed input
	 */
	public CharSequence makeInsertable(CharSequence input) {
		return "// " + Pattern.compile("\\n", Pattern.MULTILINE).matcher(input).replaceAll("\n// ");
	}

	/**
	 * Makes sure the given input is valid
	 * 
	 * @param input
	 *            The input to check
	 * @throws SQDevFileIsInvalidException
	 *             If the given input is not valid
	 */
	protected void ensureValidity(CharSequence input) throws SQDevFileIsInvalidException {
		if (!isValid(input, ESQDevFileType.NULLTYPE)) {
			throw new SQDevFileIsInvalidException("The given input is invalid!");
		}
	}

	/**
	 * Preprocess the given input by removing all comments from it
	 * 
	 * @param input
	 *            The input to preprocess
	 * @return The preprocessed input
	 */
	protected CharSequence preprocess(CharSequence input) {
		return getCommentMatcher(input).replaceAll("");
	}

	/**
	 * Gets the version as a String representation
	 */
	public abstract String getVersion();

	/**
	 * Gets a description of the SQDevFile-format specification corresponding to the
	 * current version.
	 * 
	 * @param insertable
	 *            A flag indicating whether the description should be formatted in a
	 *            way so that it can be directly inserted into a SQDevFile without
	 *            causing any errors.
	 * @return The respective description
	 */
	public abstract String getFormatDescription(boolean insertable);

	/**
	 * Starts validating the SQDevFile and notifying listeners about encountered
	 * errors
	 * 
	 * @param input
	 *            The input to validate
	 * @param listener
	 *            The {@linkplain ISQDevFileErrorListener} to report all encountered
	 *            errors to
	 * 
	 * @param type
	 *            The {@linkplain ESQDevFileType} to validate the given input as
	 */
	public abstract void validate(CharSequence input, ISQDevFileErrorListener listener, ESQDevFileType type);

	/**
	 * Gets a matcher for that will detect all comments in the given input
	 * 
	 * @param input
	 *            The input to create this matcher for
	 * @return The respective matcher
	 */
	public abstract Matcher getCommentMatcher(CharSequence input);

	/**
	 * Gets a matcher that will detect the given attribute in the provided input.
	 * The returned matcher will always detect the complete line and the value of
	 * the found attribute will be contained in the last group.
	 * 
	 * @param attribute
	 *            The attribute to match
	 * @param input
	 *            The input to create this matcher for
	 * @return The respective matcher
	 */
	public abstract Matcher getAttributeMatcher(ESQDevFileAttribute attribute, CharSequence input);

	/**
	 * Gets a matcher that will detect the given annotation in the provided input.
	 * The returned matcher will always detect the complete line and the value of
	 * the annotation (without quotes) will be found in the last group.
	 * 
	 * @param attribute
	 *            The annotation to match
	 * @param input
	 *            The input to create this matcher for
	 * @return The respective matcher
	 */
	public abstract Matcher getAnnotationMatcher(ESQDevFileAnnotation annotation, CharSequence input);

	/**
	 * Adds the given annotation with all its values to the given content. While
	 * doing so this method will try to add it after all previously added
	 * annotations instead of simply inserting it at the end of the content.
	 * 
	 * @param annotation
	 *            The annotation to add
	 * @param content
	 *            The {@linkplain CharSequence} to add the annotation to
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws SQDevException
	 */
	public abstract CharSequence addAnnotation(ESQDevFileAnnotation annotation, CharSequence content);

	/**
	 * Adds the given attribute (with its set value) to the given content. While
	 * doing so this method will try to add it after all previously added attributes
	 * instead of simply inserting it at the end of the content. If the given
	 * attribute is already contained in the content, its value will be overwritten
	 * accordingly.
	 * 
	 * @param attribute
	 *            The attribute to add
	 * @param content
	 *            The {@linkplain CharSequence} to add the attribute to
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws SQDevException
	 */
	public abstract CharSequence addAttribute(ESQDevFileAttribute attribute, CharSequence content);

	/**
	 * A static {@linkplain HashMap} containing the input-sequences that have
	 * already been validated before and their validation-state. This is intended to
	 * use in order to remove redundant validations of the same input sequence.
	 */
	static HashMap<CharSequence, Boolean> validatedInput = new HashMap<>();
}
