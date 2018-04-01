package raven.sqdev.sqf.processing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IMarker;

import dataStructures.ESQFOperatorType;
import dataStructures.ESQFTokentype;
import dataStructures.ISQFTreeListener;
import dataStructures.ITokenSource;
import dataStructures.IndexTreeElement;
import dataStructures.SQFToken;
import raven.sqdev.constants.ProblemMessages;
import raven.sqdev.exceptions.ValidationException;
import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.infoCollection.base.Variable;
import raven.sqdev.interfaces.ISQFInformation;
import raven.sqdev.interfaces.ITreeProcessingResult;
import raven.sqdev.misc.DataTypeList;
import raven.sqdev.misc.EDataType;
import raven.sqdev.parser.misc.TreeProcessingResult;
import raven.sqdev.parser.sqf.ERelativePosition;
import raven.sqdev.parser.sqf.SQFSyntaxProcessor;

public class SQFProcessor implements ISQFTreeListener {

	/**
	 * A {@linkplain DataTypeList} containing the datatype ANYTHING
	 */
	public static final DataTypeList ANYTHING = new DataTypeList(EDataType.ANYTHING);
	/**
	 * A {@linkplain DataTypeList} containing the datatype CODE
	 */
	public static final DataTypeList CODE = new DataTypeList(EDataType.CODE);
	/**
	 * A {@linkplain DataTypeList} containing the datatype ARRAY
	 */
	public static final DataTypeList ARRAY = new DataTypeList(EDataType.ARRAY);
	/**
	 * A {@linkplain DataTypeList} containing the datatype STRING
	 */
	public static final DataTypeList STRING = new DataTypeList(EDataType.STRING);
	/**
	 * A {@linkplain DataTypeList} containing the datatype NUMBER
	 */
	public static final DataTypeList NUMBER = new DataTypeList(EDataType.NUMBER);

	/**
	 * The buffer holding all tokens
	 */
	protected ITokenSource<SQFToken> tokenBuffer;
	/**
	 * The object containing all results of parsing and validating
	 */
	protected TreeProcessingResult result;
	/**
	 * The set of so far declared variables. All names are in lowercase
	 */
	protected HashSet<String> declaredVariables;
	/**
	 * The object holding all necessary meta-information
	 */
	protected ISQFInformation sqfInformation;
	/**
	 * A map of all resolved return values
	 */
	protected Map<IndexTreeElement, DataTypeList> resolvedReturnValues;
	/**
	 * The {@linkplain SQFSyntaxProcessor} to use internally
	 */
	protected SQFSyntaxProcessor syntaxProcessor;


	/**
	 * Creates a new instance of this processor
	 * 
	 * @param info
	 *            The {@linkplain ISQFInformation} holding all necessary meta
	 *            information about the available commands
	 * @param result
	 *            The {@linkplain TreeProcessingResult} to put all results of this
	 *            processing to
	 * @param tokenBuffer
	 *            The buffer holding all tokens to the tree that is going to be
	 *            processed
	 */
	public SQFProcessor(ISQFInformation info, TreeProcessingResult result, ITokenSource<SQFToken> tokenBuffer) {
		assert (info != null);
		assert (result != null);
		assert (tokenBuffer != null);

		declaredVariables = new HashSet<>();
		this.result = result;
		this.sqfInformation = info;
		this.tokenBuffer = tokenBuffer;
		resolvedReturnValues = new HashMap<>();
		syntaxProcessor = new SQFSyntaxProcessor();
	}

	@Override
	public void nularExpression(SQFToken expression, IndexTreeElement node) {
		assert (expression.operatorType() != ESQFOperatorType.MACRO);

		final String operatorName = expression.getText();
		final String operatorNameLower = operatorName.toLowerCase();

		switch (expression.type()) {
		case OPERATOR:
			// only operators are going to be processed in this method
			// variable validation will be handled in doGetReturnValues
			break;
		default:
			// it is most likely a primitive type like Number -> even if not: This won't be
			// handled here
			return;
		}

		SQFCommand operator = sqfInformation.getNularOperators().get(operatorNameLower);

		if (operator == null) {
			// assume any type for erroneous input
			error(expression, ProblemMessages.operatorIsNotNular(operatorName));

			resolvedReturnValues.put(node, ANYTHING);
		} else {
			resolvedReturnValues.put(node, operator.getAllReturnTypes());
		}
	}

	@Override
	public void unaryExpression(SQFToken expression, IndexTreeElement node) {
		assert (expression.operatorType() != ESQFOperatorType.MACRO);
		assert (node.getChildrenCount() == 1);

		final String operatorName = expression.getText();
		final String operatorNameLower = operatorName.toLowerCase();
		SQFCommand operator = sqfInformation.getUnaryOperators().get(operatorNameLower);
		if (operator == null) {
			// might be a binary operator used in unary syntax
			operator = sqfInformation.getBinaryOperators().get(operatorNameLower);
		}

		if (operatorNameLower.equals("private")) {
			// handle private separately
			handlePrivate(node.getChildren().get(0));
			return;
		}

		if (operator == null) {
			// if it's still null then this is not a unary operator
			error(expression, ProblemMessages.operatorIsNotUnary(operatorName));

			// assume any type for erroneous input
			resolvedReturnValues.put(node, ANYTHING);

			return;
		}

		if (!operator.isUnaryOperator()) {
			// This operator can't be used in a unary way
			error(expression, ProblemMessages.operatorIsNotUnary(operator.getKeyword()));
			return;
		}

		// check whether the given argument is of a valid type
		syntaxProcessor.setOperator(operator);
		syntaxProcessor.setRightArgumentTypes(getReturnValues(node.getChildren().get(0)));

		// buffer return value for this node
		resolvedReturnValues.put(node, syntaxProcessor.getReturnValues());

		if (!syntaxProcessor.isValid()) {
			if (syntaxProcessor.getErrorMarkerPosition() == ERelativePosition.RIGHT) {
				error(node.get(0), syntaxProcessor.getErrorMessage());
			} else {
				error(expression, syntaxProcessor.getErrorMessage());
			}
		} else {
			// check for special cases in which a variable-name extraction has to be
			// performed
			switch (operatorNameLower) {
			case "params":
				handleParams(node.getChildren().get(0));
				break;

			case "for":
				handleFor(node.getChildren().get(0));
				break;
			}
		}
	}

	@Override
	public void binaryExpression(SQFToken expression, IndexTreeElement node) {
		final String operatorName = expression.getText();

		switch (operatorName.toLowerCase()) {
		case "=":
			// assignment
			assignment(node);
			break;

		case "params":
			handleParams(node.getChildren().get(1));

		default:
			// "normal" binary operator
			SQFCommand operator = sqfInformation.getBinaryOperators().get(operatorName.toLowerCase());

			if (operator != null) {
				DataTypeList leftTypes = getReturnValues(node.getChildren().get(0));
				DataTypeList rightTypes = getReturnValues(node.getChildren().get(1));

				syntaxProcessor.setOperator(operator);
				syntaxProcessor.setLeftArgumentTypes(leftTypes);
				syntaxProcessor.setRightArgumentTypes(rightTypes);

				if (!syntaxProcessor.isValid()) {
					int[] positionData;

					try {
						switch (syntaxProcessor.getErrorMarkerPosition()) {
						case CENTER:
							positionData = new int[] { expression.start(), expression.length() };
							break;
						case LEFT:
							positionData = getNodeDimension(node.getChildren().get(0));
							break;
						case RIGHT:
							positionData = getNodeDimension(node.getChildren().get(1));
							break;
						case NONE:
						default:
							positionData = new int[] { 0, 0 };
							error(node, ProblemMessages.internalError());
							System.err.println("Unexpected marker position for binary expression");
							// TODO: log
						}
					} catch (ValidationException e) {
						e.printStackTrace();
						positionData = new int[] { 0, 0 };
						error(node, ProblemMessages.internalError());
					}

					error(positionData[0], positionData[1], syntaxProcessor.getErrorMessage());
				}

				// map the resolved processor to the respective node
				resolvedReturnValues.put(node, syntaxProcessor.getReturnValues());
			} else {
				// apparently it is not a binary operator -> shouldn't even be reachable
				error(expression, ProblemMessages.operatorIsNotBinary(operatorName));
			}

			break;
		}
	}

	@Override
	public void start() {
		// nothing to set up at start
	}

	@Override
	public void finished() {
		// add all found variables to the result
		Iterator<String> varIterator = declaredVariables.iterator();

		while (varIterator.hasNext()) {
			Variable var = new Variable(varIterator.next());

			if (var.isLocal()) {
				result.getDeclaredLocalVariables().put(var.getKeyword(), var);
			} else {
				result.getDeclaredGlobalVariables().put(var.getKeyword(), var);
			}
		}
	}

	/**
	 * This method processes any occurring assignments done by the "=" operator
	 * 
	 * @param node
	 *            The parse tree node of the assignment. The two children are
	 *            considered to be assignment arguments (there have to be two!)
	 */
	protected void assignment(IndexTreeElement node) {
		assert (node.getChildrenCount() == 2);

		IndexTreeElement variableNode = node.getChildren().get(0);

		boolean isPrivate = variableNode.hasChildren();

		if (isPrivate) {
			int index = variableNode.getIndex();

			if (index < 0) {
				// The assignment is corrupted
				error(variableNode, ProblemMessages.invalidExpression("assignment"));
			}

			SQFToken varOperatorToken = tokenBuffer.get(index);

			if (varOperatorToken.operatorType() == ESQFOperatorType.MACRO) {
				// handle macros assembling the variable name -> can't really validate -> assume
				// it's valid
				declaredVariables.add(getFullText(variableNode));
				// abort as other checks and routines don't apply to macros
				return;
			}

			if (!varOperatorToken.getText().toLowerCase().equals("private")) {
				// only private is allowed as a modifier
				error(tokenBuffer.get(node.getIndex()), ProblemMessages.privateIsOnlyValidModifierForAssignments());

				// assume for the moment that the modifier came here by accident
				isPrivate = false;
			}

			variableNode = variableNode.getChildren().get(0);

			if (variableNode.getIndex() < 0) {
				// something's really f*cked up
				error(node.getChildren().get(0), ProblemMessages.internalError());

				return;
			}
		}

		String varName = tokenBuffer.get(variableNode.getIndex()).getText();
		if (isPrivate && !varName.startsWith("_")) {
			// don't add global, private variables
			// The error is produced as soon as the unary private keyword is being processed
			return;
		}
		if (!isOperatorName(varName)) {
			declaredVariables.add(varName.toLowerCase());
		} else {
			error(tokenBuffer.get(variableNode.getIndex()), ProblemMessages.reservedKeyword(varName));
		}
	}

	/**
	 * Adds a new error marker to {@link #result}
	 * 
	 * @param start
	 *            The starting offset of the error area
	 * @param length
	 *            The length of the error area
	 * @param message
	 *            The problem message
	 */
	protected void error(int start, int length, String message) {
		addProblemMarker(IMarker.SEVERITY_ERROR, start, length, message);
	}

	/**
	 * Adds a new error marker to {@link #result}
	 * 
	 * @param token
	 *            The token corresponding to the error
	 * @param message
	 *            The problem message
	 */
	protected void error(SQFToken token, String message) {
		addProblemMarker(IMarker.SEVERITY_ERROR, token, message);
	}

	/**
	 * Adds a new error marker to {@link #result}
	 * 
	 * @param node
	 *            The tree node corresponding to the error
	 * @param message
	 *            The problem message
	 */
	protected void error(IndexTreeElement node, String message) {
		addProblemMarker(IMarker.SEVERITY_ERROR, node, message);
	}

	/**
	 * Adds a new warning marker to {@link #result}
	 * 
	 * @param start
	 *            The starting offset of the warning area
	 * @param length
	 *            The length of the warning area
	 * @param message
	 *            The problem message
	 */
	protected void warning(int start, int length, String message) {
		addProblemMarker(IMarker.SEVERITY_WARNING, start, length, message);
	}

	/**
	 * Adds a new warning marker to {@link #result}
	 * 
	 * @param token
	 *            The token corresponding to the warning
	 * @param message
	 *            The problem message
	 */
	protected void warning(SQFToken token, String message) {
		addProblemMarker(IMarker.SEVERITY_WARNING, token, message);
	}

	/**
	 * Adds a new warning marker to {@link #result}
	 * 
	 * @param node
	 *            The tree node corresponding to the warning
	 * @param message
	 *            The problem message
	 */
	protected void warning(IndexTreeElement node, String message) {
		addProblemMarker(IMarker.SEVERITY_WARNING, node, message);
	}

	/**
	 * Adds a new info marker to {@link #result}
	 * 
	 * @param start
	 *            The starting offset of the info area
	 * @param length
	 *            The length of the info area
	 * @param message
	 *            The problem message
	 */
	protected void info(int start, int length, String message) {
		addProblemMarker(IMarker.SEVERITY_INFO, start, length, message);
	}

	/**
	 * Adds a new info marker to {@link #result}
	 * 
	 * @param token
	 *            The token corresponding to the info
	 * @param message
	 *            The problem message
	 */
	protected void info(SQFToken token, String message) {
		addProblemMarker(IMarker.SEVERITY_INFO, token, message);
	}

	/**
	 * Adds a new info marker to {@link #result}
	 * 
	 * @param node
	 *            The tree node corresponding to the info
	 * @param message
	 *            The problem message
	 */
	protected void info(IndexTreeElement node, String message) {
		addProblemMarker(IMarker.SEVERITY_INFO, node, message);
	}

	/**
	 * Adds a new problem marker to {@link #result}
	 * 
	 * @param severity
	 *            The severity of the marker to be created. See {@linkplain IMarker}
	 *            for the respective constants
	 * @param start
	 *            The starting offset of the problem area
	 * @param length
	 *            The length of the problem area
	 * @param message
	 *            The problem message
	 */
	protected void addProblemMarker(int severity, int start, int length, String message) {
		result.createMarker(IMarker.PROBLEM, start, length, message, severity);
	}

	/**
	 * Adds a new problem marker to {@link #result}
	 * 
	 * @param severity
	 *            The severity of the marker to be created. See {@linkplain IMarker}
	 *            for the respective constants
	 * @param token
	 *            The token corresponding to the problem
	 * @param message
	 *            The problem message
	 */
	protected void addProblemMarker(int severity, SQFToken token, String message) {
		addProblemMarker(severity, token.start(), token.length(), message);
	}

	/**
	 * Adds a new problem marker to {@link #result}. This method will consider the
	 * whole dimension of the given node as the problem area
	 * 
	 * @param severity
	 *            The severity of the marker to be created. See {@linkplain IMarker}
	 *            for the respective constants
	 * @param node
	 *            The tree node corresponding to the problem
	 * @param message
	 *            The problem message
	 */
	protected void addProblemMarker(int severity, IndexTreeElement node, String message) {
		// verify that the given indexTreeElement has a corresponding token
		int[] dimension;
		try {
			dimension = getNodeDimension(node);
		} catch (ValidationException e) {
			e.printStackTrace();
			dimension = new int[] { 0, 0 };
			message = "Error on invalid tree node: " + message;
		}

		addProblemMarker(severity, dimension[0], dimension[1], message);
	}

	/**
	 * Gets a node's dimension which is the text area from the start of the token
	 * associated leftmost child-node and the end of the token associated with the
	 * rightmost child-node. If this node doesn't have children the token associated
	 * with this node is used in order to determine the node's dimension.
	 * 
	 * @param node
	 *            The node whose dimension should be obtained
	 * @return The dimension of the node in form of a 2D array: [start, length]
	 * @throws ValidationException
	 *             If asked to determine the dimension of an invalid node
	 */
	protected int[] getNodeDimension(IndexTreeElement node) throws ValidationException {
		if (!node.hasChildren()) {
			int index = node.getIndex();

			if (index < 0) {
				throw new ValidationException("Failed at getting node dimension!");
			}

			SQFToken token = tokenBuffer.get(node.getIndex());

			return new int[] { token.start(), token.length() };
		}

		// use the left- and rightmost child in order to determine the dimension
		return new int[] { getNodeDimension(node.getChildren().get(0))[0],
				getNodeDimension(node.getChildren().get(node.getChildrenCount() - 1))[1] };
	}

	/**
	 * Gets the possible return values of the given node. Calling this method will
	 * trigger the return-value-buffering which means that it will populate
	 * {@link #resolvedReturnValues} if there is no entry for the given node yet. If
	 * there is the previously determined return values will be returned
	 * 
	 * @param node
	 *            The node whose return values are to be determined
	 * @return The list of return values
	 */
	protected DataTypeList getReturnValues(IndexTreeElement node) {
		// Check whether the return value for that node has already been determined ->
		// Especially used for context sensitive return values of operators
		if (!resolvedReturnValues.containsKey(node)) {
			resolvedReturnValues.put(node, doGetReturnValues(node));
		}

		return resolvedReturnValues.get(node);
	}

	/**
	 * Gets all the possible return values of the given node. Calling this method
	 * directly will bypass the return-value-buffering system
	 * 
	 * @param node
	 *            The node whose return values are to be determined
	 * @return The list of return values
	 */
	protected DataTypeList doGetReturnValues(IndexTreeElement node) {
		if (node.getIndex() >= 0) {
			SQFToken token = tokenBuffer.get(node.getIndex());

			// handle primitives first
			switch (token.type()) {
			case CURLY_BRACKET_OPEN:
				return CODE;
			case ERROR_TOKEN:
				return ANYTHING;
			case NUMBER:
				return NUMBER;
			case SQUARE_BRACKET_OPEN:
				return ARRAY;
			case STRING:
				return STRING;
			case SUBSTRING:
				return STRING;
			default:
				// do nothing -> will be handled below
			}

			switch (token.operatorType()) {
			case MACRO:
				return ANYTHING;
			case BINARY:
				SQFCommand operator = sqfInformation.getBinaryOperators().get(token.getText().toLowerCase());
				if (operator == null) {
					// If it is not recognized it will be handled elsewhere
					return ANYTHING;
				} else {
					return operator.getAllReturnTypes();
				}
			case NULAR:
				operator = sqfInformation.getNularOperators().get(token.getText().toLowerCase());
				if (operator == null) {
					// If it is not recognized it will be handled elsewhere
					if (token.type() == ESQFTokentype.ID) {
						// This might be a variable
						String id = token.getText();
						if (!declaredVariables.contains(id.toLowerCase())) {
							if (id.startsWith("_")) {
								// it is an unknown local variable -> error
								error(token, ProblemMessages.undefinedLocalVariable(id));
							} else {
								// this is an implicitly declared global variable
								// TODO: potential error
								declaredVariables.add(id.toLowerCase());
							}
						}
					}
					return ANYTHING;
				} else {
					return operator.getAllReturnTypes();
				}
			case UNARY:
				operator = sqfInformation.getUnaryOperators().get(token.getText().toLowerCase());
				if (operator == null) {
					// If it is not recognized it will be handled elsewhere
					return ANYTHING;
				} else {
					return operator.getAllReturnTypes();
				}
			default:
				// Shouldn't get reached
				error(node, ProblemMessages.internalError());
				return ANYTHING;
			}
		} else {
			if (node.hasChildren()) {
				return getReturnValues(node.getChildren().get(0));
			} else {
				// Shouldn't get reached
				error(node, ProblemMessages.internalError());
				return ANYTHING;
			}
		}
	}

	/**
	 * Gets the full text of a {@linkplain IndexTreeElement} which is the text of
	 * all its associated tokens
	 * 
	 * @param node
	 *            The node whose text should be obtained
	 * @return The text corresponding to the given node
	 */
	protected String getFullText(IndexTreeElement node) {
		StringBuilder builder = new StringBuilder();

		Collection<Integer> indices = new ArrayList<>();
		getAllTokenIndices(node, indices);

		indices.stream().filter((e) -> {
			return e.intValue() >= 0;
		}).sorted().forEach((element) -> builder.append(tokenBuffer.get(element.intValue()).getText()));

		return builder.toString();
	}

	/**
	 * Gets all token indices associated with the given node and its sub-nodes
	 * 
	 * @param node
	 *            The node to process
	 * @param indices
	 *            An integer collection to store the indices in
	 */
	protected void getAllTokenIndices(IndexTreeElement node, Collection<Integer> indices) {
		indices.add(node.getIndex());

		if (node.hasChildren()) {
			node.getChildren().forEach((e) -> getAllTokenIndices(e, indices));
		}
	}

	/**
	 * Handles the argument of the "private" operator/keyword
	 * 
	 * @param node
	 *            The {@linkplain IndexTreeElement} corresponding to the argument of
	 *            "private" (or the ID of the variable private was used as a keyword
	 *            for)
	 */
	protected void handlePrivate(IndexTreeElement node) {
		assert (node.getIndex() != IndexTreeElement.INVALID);

		if (node.getIndex() < 0) {
			// the argument has to be an array of variable names
			if (!node.hasChildren()) {
				error(node, ProblemMessages.invalidExpression("private-array"));
				return;
			}

			// iterate through all children and extract declared variables
			for (IndexTreeElement currentElement : node.getChildren()) {
				if (currentElement.getIndex() < 0) {
					error(currentElement, ProblemMessages.invalidExpression("private-array"));
				} else {
					SQFToken varToken = tokenBuffer.get(currentElement.getIndex());

					if (varToken.type() != ESQFTokentype.STRING) {
						if (varToken.type() != ESQFTokentype.COMMA
								&& varToken.type() != ESQFTokentype.SQUARE_BRACKET_OPEN
								&& varToken.type() != ESQFTokentype.SQUARE_BRACKET_CLOSE) {
							// comma and square brackets are obviously allowed in an array
							error(varToken, ProblemMessages.invalidExpression("private-array-element"));
						}
					} else {
						extractVariableFromString(varToken, varToken.getText(), true);
					}
				}
			}
		} else {
			// the argument is either a String containing a variable name or an ID
			SQFToken argToken = tokenBuffer.get(node.getIndex());

			if (argToken.type() == ESQFTokentype.STRING) {
				extractVariableFromString(argToken, argToken.getText(), true);
			} else {
				if (argToken.type() == ESQFTokentype.ID) {
					String varName = argToken.getText();

					if (!varName.startsWith("_")) {
						error(argToken, ProblemMessages.privateVariablesMustBeLocal());
						return;
					}

					if (!isOperatorName(varName)) {
						declaredVariables.add(varName.toLowerCase());
					} else {
						error(argToken, ProblemMessages.reservedKeyword(varName));
					}
				} else {
					error(argToken, ProblemMessages.invalidExpression("private"));
				}
			}
		}
	}

	/**
	 * Handles the argument of the "for"-keyword
	 * 
	 * @param arg
	 *            The respective {@linkplain IndexTreeElement} corresponding to the
	 *            argument of a "for" operator
	 */
	protected void handleFor(IndexTreeElement arg) {
		// TODO
	}

	/**
	 * Handles the argument of the "params"-keyword
	 * 
	 * @param arg
	 *            The respective {@linkplain IndexTreeElement} corresponding to the
	 *            argument of a "params" operator
	 */
	protected void handleParams(IndexTreeElement arg) {
		// TODO
	}

	/**
	 * Extracts a variable from a String as it occurs with private, params and for
	 * 
	 * @param token
	 *            The token corresponding to said String
	 * @param varString
	 *            The string containing the variable name. Its first and last
	 *            character will be removed in order to "destringify" it
	 * @param mustBePrivate
	 *            Whether the extracted variable is expected to be a private
	 *            variable
	 */
	protected void extractVariableFromString(SQFToken token, String varString, boolean mustBePrivate) {
		// remove quotes
		varString = varString.substring(1, varString.length() - 1);

		if (varString.contains(" ")) {
			error(token, ProblemMessages.noWhitespaceAllowed());
			return;
		}

		if (mustBePrivate && !varString.startsWith("_")) {
			error(token, ProblemMessages.canOnlyDeclareLocalVariable());
			return;
		}

		if (!isOperatorName(varString)) {
			declaredVariables.add(varString.toLowerCase());
		} else {
			error(token, ProblemMessages.reservedKeyword(varString));
		}
	}

	/**
	 * Checks whether the given name corresponds to an operator-keyword
	 * 
	 * @param name
	 *            The name to check. It will be transformed to lowercase
	 *            automatically
	 */
	protected boolean isOperatorName(String name) {
		name = name.toLowerCase();
		return sqfInformation.getBinaryOperators().containsKey(name)
				|| sqfInformation.getUnaryOperators().containsKey(name)
				|| sqfInformation.getNularOperators().containsKey(name);
	}

	/**
	 * Gets the result of this validation
	 */
	public ITreeProcessingResult getResult() {
		return result;
	}
}
