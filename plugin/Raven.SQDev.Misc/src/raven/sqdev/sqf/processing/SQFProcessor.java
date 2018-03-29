package raven.sqdev.sqf.processing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IMarker;

import dataStructures.ISQFTreeListener;
import dataStructures.ITokenSource;
import dataStructures.IndexTreeElement;
import dataStructures.SQFToken;
import dataStructures.TokenBuffer;
import raven.sqdev.constants.ProblemMessages;
import raven.sqdev.exceptions.ValidationException;
import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.infoCollection.base.Variable;
import raven.sqdev.interfaces.ISQFInformation;
import raven.sqdev.interfaces.ITreeProcessingResult;
import raven.sqdev.misc.DataTypeList;
import raven.sqdev.misc.EDataType;
import raven.sqdev.parser.misc.TreeProcessingResult;
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
	protected ISQFInformation parseInformation;
	/**
	 * A map of all resolved return values
	 */
	protected Map<IndexTreeElement, DataTypeList> resolvedReturnValues;


	public SQFProcessor(ISQFInformation info, TreeProcessingResult result) {
		declaredVariables = new HashSet<>();
		this.result = result;
		resolvedReturnValues = new HashMap<>();
	}

	@Override
	public void nularExpression(SQFToken expression) {
		final String operator = expression.getText();

	}

	@Override
	public void unaryExpression(SQFToken expression, IndexTreeElement node) {
		final String operator = expression.getText();

	}

	@Override
	public void binaryExpression(SQFToken expression, IndexTreeElement node) {
		final String operatorName = expression.getText();

		switch (operatorName) {
		case "=":
			// assignment
			assignment(node);
			break;

		default:
			// "normal" binary operator
			SQFCommand operator = parseInformation.getBinaryOperators().get(operatorName.toLowerCase());

			if (operator != null) {
				DataTypeList leftTypes = getReturnValues(node.getChildren().get(0));
				DataTypeList rightTypes = getReturnValues(node.getChildren().get(1));

				// TODO: consider using static processor for all statements
				SQFSyntaxProcessor processor = new SQFSyntaxProcessor(operator);
				processor.setLeftArgumentTypes(leftTypes.toArray());
				processor.setRightArgumentTypes(rightTypes.toArray());

				if (!processor.isValid()) {
					int[] positionData;

					try {
						switch (processor.getErrorMarkerPosition()) {
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

					error(positionData[0], positionData[1], processor.getErrorMessage());
				}

				// map the resolved processor to the respective node
				resolvedReturnValues.put(node, processor.getReturnValues());
			} else {
				// apparently it is not a binary operator -> shouldn't even be reachable
				error(expression, ProblemMessages.operatorIsNotBinary(operatorName));
			}

			break;
		}
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void finished() {
		// add all found variables to the result
		Iterator<String> varIterator = declaredVariables.iterator();

		while (varIterator.hasNext()) {
			Variable var = new Variable(varIterator.next());

			if (var.isLocal()) {
				result.getDeclaredLocalVariables().put(var.getKeyword().toLowerCase(), var);
			} else {
				result.getDeclaredGlobalVariables().put(var.getKeyword().toLowerCase(), var);
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

			String varOperator = tokenBuffer.get(index).getText();
			if (!varOperator.toLowerCase().equals("private")) {
				// only private is allowed as a modifier
				error(tokenBuffer.get(node.getIndex()), ProblemMessages.privateIsOnlyValidModifierForAssignments());
			}

			variableNode = variableNode.getChildren().get(0);

			if (variableNode.getIndex() < 0) {
				// something's really f*cked up
				error(node.getChildren().get(0), ProblemMessages.internalError());

				return;
			}
		}

		String varName = tokenBuffer.get(variableNode.getIndex()).getText();
		if (!varName.startsWith("_") && isPrivate) {
			// trying to declare global variable as private
			error(variableNode, ProblemMessages.privateVariablesMustBeLocal());
		}
		declaredVariables.add(varName.toLowerCase());
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
	 * Gets the possible return values of the given
	 * 
	 * @param node
	 * @return
	 */
	protected DataTypeList getReturnValues(IndexTreeElement node) {
		// Check whether the return value for that node has already been determined ->
		// Especially used for context sensitive return values of operators
		if (resolvedReturnValues.containsKey(node)) {
			return resolvedReturnValues.get(node);
		}

		if (node.getIndex() > 0) {
			SQFToken token = tokenBuffer.get(node.getIndex());

			switch (token.operatorType()) {
			case MACRO:
				return ANYTHING;
			case BINARY:
				SQFCommand operator = parseInformation.getBinaryOperators().get(token.getText().toLowerCase());
				if (operator == null) {
					// If it is not recognized it will be handled elsewhere
					return ANYTHING;
				} else {
					return operator.getAllReturnTypes();
				}
			case NULAR:
				operator = parseInformation.getNularOperators().get(token.getText().toLowerCase());
				if (operator == null) {
					// If it is not recognized it will be handled elsewhere
					return ANYTHING;
				} else {
					return operator.getAllReturnTypes();
				}
			case UNARY:
				operator = parseInformation.getUnaryOperators().get(token.getText().toLowerCase());
				if (operator == null) {
					// If it is not recognized it will be handled elsewhere
					return ANYTHING;
				} else {
					return operator.getAllReturnTypes();
				}
			case OTHER:
				// check token type
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
					// Shouldn't be reached
					error(token, ProblemMessages.internalError());
					return ANYTHING;

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
	 * Gets the result of this validation
	 */
	public ITreeProcessingResult getResult() {
		return result;
	}
}
