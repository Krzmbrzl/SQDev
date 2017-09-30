package raven.sqdev.constants;

import raven.sqdev.misc.EDataType;

/**
 * A class providing all problem messages that can be displayed in the editor
 * 
 * @author Raven
 *
 */
public class ProblemMessages {
	
	/**
	 * Creates the error message stating that the left hand argument of the
	 * given operator is missing
	 * 
	 * @param operatorName
	 *            The name of the operator
	 * @return The created error message
	 */
	public static final String missingArgLeft(String operatorName) {
		return "Missing argument on left-hand side at \"" + operatorName + "\"";
	}
	
	/**
	 * Creates the error message stating that the given operator is a nular
	 * operator and can therefore not be used with arguments
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
		return "The operator \"" + operatorName
				+ "\" can not be used as a nular operator!";
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
		return "The operator \"" + operatorName
				+ "\" can not be used as a binary operator!";
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
	 * Creates the error message stating that the referenced local variable is
	 * not defined.
	 * 
	 * @param varName
	 *            The name of the variable
	 * @return The created error message
	 */
	public static final String undefinedLocalVariable(String varName) {
		return "Undefined local variable \"" + varName + "\"";
	}
	
	/**
	 * Gets the error message that the variable declarations couldn't be
	 * processed because an array was expected but not given
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
	public static final String expectedTypeButGotDifferent(String type,
			String different) {
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
	 * Gets the error message that a set of types was expected but a different
	 * set of types has been given
	 * 
	 * @param expected
	 *            The set of expected types
	 * @param got
	 *            The set of given types
	 * @return the generated error message
	 */
	public static final String ExpectedTypeButGot(EDataType[] expected,
			EDataType[] got) {
		StringBuilder builder = new StringBuilder("Expected ");
		
		for (int i = 0; i < expected.length; i++) {
			EDataType currentType = expected[i];
			
			if (i == 0) {
				builder.append("\"" + currentType + "\"");
			} else {
				if (i == expected.length - 1) {
					builder.append(" or \"" + currentType + "\"");
				} else {
					builder.append(", \"" + currentType + "\"");
				}
			}
		}
		
		builder.append(" but got ");
		
		for (int i = 0; i < got.length; i++) {
			EDataType currentType = got[i];
			
			if (i == 0) {
				builder.append("\"" + currentType + "\"");
			} else {
				if (i == got.length - 1) {
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
	 * An error message stating that there is a type mismatch
	 * 
	 * @param type
	 *            The type that was expected (the first String representation
	 *            will be used)
	 * @return The generated error message
	 */
	public static final String expectedType(EDataType type) {
		return expectedType(type.toString());
	}
	
	/**
	 * An error message stating that there is a type mismatch
	 * 
	 * @param types
	 *            A list of types that were expected
	 * @return The generated error message
	 */
	public static final String expectedTypes(EDataType[] types) {
		StringBuilder builder = new StringBuilder("Expected ");
		
		for (int i = 0; i < types.length; i++) {
			EDataType currentType = types[i];
			
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
		return "This operator is case-sensitive! It has to be \"" + correct
				+ "\"!";
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
}
