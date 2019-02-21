package raven.sqdev.constants;

import raven.sqdev.misc.ESQFDataType;

/**
 * A class providing all problem messages that can be displayed in the editor
 * 
 * @author Raven
 *
 */
public class ProblemMessages {

	/**
	 * Creates the error message stating that the left hand argument of the given
	 * operator is missing
	 * 
	 * @param operatorName
	 *            The name of the operator
	 * @return The created error message
	 */
	public static final String missingArgLeft(String operatorName) {
		return "Missing argument on left-hand side at \"" + operatorName + "\"";
	}

	/**
	 * Creates the error message stating that the given operator is a nular operator
	 * and can therefore not be used with arguments
	 * 
	 * @param operatorName
	 *            The name of the operator
	 * @return The created error message
	 */
	public static final String operatorIsNular(String operatorName) {
		return "\"'" + operatorName + "\" is a nular expression (No args!)";
	}

	/**
	 * Creates the error message stating that the operator was wrongly used as a
	 * nular operator
	 * 
	 * @param operatorName
	 *            The name of the operator
	 * @return The created error message
	 */
	public static final String operatorIsNotNular(String operatorName) {
		return "The operator \"" + operatorName + "\" can not be used as a nular operator!";
	}

	/**
	 * Creates the error message stating that the operator was wrongly used as a
	 * unary operator
	 * 
	 * @param operatorName
	 *            The name of the operator
	 * @return The created error message
	 */
	public static final String operatorIsNotUnary(String operatorName) {
		return "The operator \"" + operatorName + "\" can not be used as a unary operator!";
	}

	/**
	 * Creates the error message stating that the operator was wrongly used as a
	 * binary operator
	 * 
	 * @param operatorName
	 *            The name of the operator
	 * @return The created error message
	 */
	public static final String operatorIsNotBinary(String operatorName) {
		return "The operator \"" + operatorName + "\" can not be used as a binary operator!";
	}

	/**
	 * Creates the error message stating that the given operator is unknown
	 * 
	 * @param operatorName
	 *            The name of the operator
	 * @return The created error message
	 */
	public static final String unknownOperator(String operatorName) {
		return "Unknown operator \"" + operatorName + "\"";
	}

	/**
	 * Creates the error message stating that the referenced local variable is not
	 * defined.
	 * 
	 * @param varName
	 *            The name of the variable
	 * @return The created error message
	 */
	public static final String undefinedLocalVariable(String varName) {
		return "Undefined local variable \"" + varName + "\"";
	}

	/**
	 * Gets the error message that the variable declarations couldn't be processed
	 * because an array was expected but not given
	 */
	public static final String failedVarProcessingExpectedArray() {
		return "Can't process variable declaration because an array was expected!";
	}

	/**
	 * An error message stating that a variable name must no contain a blank!
	 */
	public static final String variableMayNotContainBlank() {
		return "A variable name may not contain a blank!";
	}

	/**
	 * An error message stating that there is a type mismatch
	 * 
	 * @param type
	 *            The type that was expected
	 * @param different
	 *            The type that has been obtained
	 * @return The generated error message
	 */
	public static final String expectedTypeButGotDifferent(String type, String different) {
		return "Expected type \"" + type + "\" but got \"" + different + "\"!";
	}

	/**
	 * An error message stating that there is a type mismatch
	 * 
	 * @param type
	 *            The type that was expected
	 * @return The generated error message
	 */
	public static final String expectedType(String type) {
		return "Expected type \"" + type + "\"!";
	}

	/**
	 * Gets the error message that a set of types was expected but a different set
	 * of types has been given
	 * 
	 * @param expected
	 *            The set of expected types
	 * @param got
	 *            The set of given types
	 * @return the generated error message
	 */
	public static final String expectedTypeButGot(Iterable<ESQFDataType> expected, Iterable<ESQFDataType> got) {
		StringBuilder builder = new StringBuilder("Expected type ");
		boolean multipleTypes = false;

		for (ESQFDataType currentType : expected) {
			if (!multipleTypes) {
				builder.append("\"" + currentType + "\"");
				multipleTypes = true;
			} else {
				builder.append(", \"" + currentType + "\"");
			}
		}

		// replace last comma by "or"
		int commaIndex = builder.lastIndexOf(",");
		if (commaIndex >= 0) {
			builder.replace(commaIndex, commaIndex + 1, " or");
		}

		builder.append(" but got ");

		multipleTypes = false;
		for (ESQFDataType currentType : got) {
			if (!multipleTypes) {
				builder.append("\"" + currentType + "\"");
				multipleTypes = true;
			} else {
				builder.append(", \"" + currentType + "\"");
			}
		}

		// replace last comma by "or"
		commaIndex = builder.lastIndexOf(",");
		if (commaIndex >= 0) {
			builder.replace(commaIndex, commaIndex + 1, " or");
		}

		builder.append("!");

		return builder.toString();
	}

	/**
	 * An error message stating that there is a type mismatch
	 * 
	 * @param type
	 *            The type that was expected (the first String representation will
	 *            be used)
	 * @return The generated error message
	 */
	public static final String expectedType(ESQFDataType type) {
		return expectedType(type.toString());
	}

	/**
	 * An error message stating that there is a type mismatch
	 * 
	 * @param types
	 *            A list of types that were expected
	 * @return The generated error message
	 */
	public static final String expectedTypes(ESQFDataType[] types) {
		StringBuilder builder = new StringBuilder("Expected ");

		for (int i = 0; i < types.length; i++) {
			ESQFDataType currentType = types[i];

			if (i == 0) {
				builder.append("\"" + currentType + "\"");
			} else {
				if (i == types.length - 1) {
					builder.append(" or \"" + currentType + "\"");
				} else {
					builder.append(", \"" + currentType + "\"");
				}
			}
		}

		builder.append("!");

		return builder.toString();
	}

	/**
	 * The error message that there can only be a local variable declared here
	 */
	public static final String canOnlyDeclareLocalVariable() {
		return "Only a local variable can be declared at this point!";
	}

	/**
	 * The error message that the given String may not be empty
	 */
	public static final String stringMayNotBeEmpty() {
		return "This String must not be empty!";
	}

	/**
	 * The error message that there is no whitespace allowed at that point
	 */
	public static final String noWhitespaceAllowed() {
		return "Whitespace is not allowed in this context!";
	}

	/**
	 * The error message stating that the given operator is case sensitive
	 * 
	 * @param correct
	 *            The correct way to spell that operator
	 */
	public static final String isCaseSensitive(String correct) {
		return "This operator is case-sensitive! It has to be \"" + correct + "\"!";
	}

	/**
	 * The error message stating that the reference is not a file
	 */
	public static final String referenceNotAFile() {
		return "Reference is not a file!";
	}

	/**
	 * The error message stating that there is a cycle in hierarchy
	 */
	public static final String cycleInHierarchy() {
		return "Cycle in hierarchy!";
	}

	/**
	 * The error message stating that only a bachslash can be used
	 */
	public static final String backslashHasToBeUsed() {
		return "Only a backslash can be used in this context";
	}

	/**
	 * The error message stating that there is a missing semicolon
	 * 
	 * @param after
	 *            The ID after which the semicolon was expected
	 */
	public static final String missingSemicolon(String after) {
		return "Missing ';' after \"" + after + "\"";
	}

	/**
	 * The error message stating that there is a missing comma
	 * 
	 * @param at
	 *            The ID after which the semicolon was expected
	 */
	public static final String missingComma(String at) {
		return "Missing ',' at \"" + at + "\"";
	}

	/**
	 * The error message stating that the given token is misplaced and that it
	 * should either be deleted or moved elsewhere.
	 * 
	 * @param token
	 *            The misplaced token
	 */
	public static final String misplacedToken(String token) {
		return "Misplaced token \"" + token + "\". Delete or move it!";
	}

	/**
	 * The error message stating that there is an unclosed opening character pair
	 * 
	 * @param opener
	 *            The opening character that doesn't get closed
	 */
	public static final String unclosedOpener(char opener) {
		char openMark, closeMark;

		if (opener != '"') {
			openMark = closeMark = '"';
		} else {
			openMark = closeMark = '\'';
		}

		return "Unclosed opening character " + openMark + opener + closeMark;
	}

	/**
	 * The error message stating that there is an invalid closing character
	 * 
	 * @param opener
	 *            The invalid closing character
	 */
	public static final String invalidClosingCharacter(char closer) {
		char openMark, closeMark;

		if (closer != '"') {
			openMark = closeMark = '"';
		} else {
			openMark = closeMark = '\'';
		}

		return "Invalid closing character " + openMark + closer + closeMark;
	}

	/**
	 * The error message stating that an invalid expression in the given context has
	 * been found
	 * 
	 * @param context
	 *            The context in which the invalid expression occurred
	 */
	public static final String invalidExpression(String context) {
		return "Invalid expression in context of  \"" + context + "\"";
	}

	/**
	 * The error message stating that "private" is the only permitted modifier for
	 * an assignment
	 */
	public static final String privateIsOnlyValidModifierForAssignments() {
		return "\"private\" is the only permitted modifier for assignments";
	}

	/**
	 * Error message stating that private variables have to be local
	 */
	public static final String privateVariablesMustBeLocal() {
		return "Private variables have to be local (starting with \"_\")";
	}

	/**
	 * Error message stating that the given keyword is reserved
	 */
	public static final String reservedKeyword(String keyword) {
		return "\"" + keyword + "\" is a reserved keyword!";
	}

	/**
	 * The error message stating that the expected array size has not been matched
	 * 
	 * @param expected
	 *            The expected array length
	 * @param got
	 *            The actual array length
	 * @return The constructed error message
	 */
	public static final String expectedArrayLength(int expected, int got) {
		return "Expected array with exactly " + expected + " elements but got " + got;
	}

	/**
	 * The error message stating that the minimal array size has not been reached
	 * 
	 * @param minimum
	 *            The expected minimal array length
	 * @param got
	 *            The actual array length
	 * @return The constructed error message
	 */
	public static final String expectedMinimumArrayLength(int minimum, int got) {
		return "Expected array with at least " + minimum + " elements but got only " + got;
	}
	
	/**
	 * The error message stating that the maximal array size has been exceeded
	 * 
	 * @param maximum
	 *            The expected maximal array length
	 * @param got
	 *            The actual array length
	 * @return The constructed error message
	 */
	public static final String expectedMaximalArrayLength(int maximum, int got) {
		return "Expected array with at most " + maximum + " elements but got " + got;
	}

	/**
	 * The error message for internal errors
	 */
	public static final String internalError() {
		return "!!! Internal error !!!";
	}
}
