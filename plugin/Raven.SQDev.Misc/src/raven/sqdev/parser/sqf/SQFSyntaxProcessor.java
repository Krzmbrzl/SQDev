package raven.sqdev.parser.sqf;

import java.util.ArrayList;
import java.util.List;

import raven.sqdev.constants.ProblemMessages;
import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.misc.DataTypeList;
import raven.sqdev.misc.ESQFDataType;
import raven.sqdev.syntax.Syntax;
import raven.sqdev.syntax.SyntaxElement;

public class SQFSyntaxProcessor {

	/**
	 * The command whose syntax should be checked
	 */
	private SQFCommand operator;
	/**
	 * The potential datatypes of the left argument
	 */
	private Iterable<ESQFDataType> leftArgumentTypes;
	/**
	 * The potential datatypes of the right argument
	 */
	private Iterable<ESQFDataType> rightArgumentTypes;
	/**
	 * Indicates whether the command has been validated with the provided argument
	 * types
	 */
	private boolean validated;
	/**
	 * The error message explaining why the current configuration is invalid
	 */
	private String errorMessage;
	/**
	 * The syntax in which the current operator is used
	 */
	private Syntax activeSyntax;
	/**
	 * The relative position describing where the error marker should be placed
	 */
	private ERelativePosition markerPosition;


	public SQFSyntaxProcessor() {
	}


	public SQFSyntaxProcessor(SQFCommand command) {
		setOperator(command);
	}

	/**
	 * Sets the potential argument types of the left argument
	 * 
	 * @param types
	 *            The potential <code>EDataTypes</code>
	 */
	public void setLeftArgumentTypes(Iterable<ESQFDataType> types) {
		leftArgumentTypes = types;
		validated = false;
	}

	/**
	 * Sets the potential argument types of the right argument
	 * 
	 * @param types
	 *            The potential <code>EDataTypes</code>
	 */
	public void setRightArgumentTypes(Iterable<ESQFDataType> types) {
		rightArgumentTypes = types;
		validated = false;
	}

	/**
	 * Validates the given command with the provided argument types
	 */
	protected void validate() {
		validated = true;

		// check possible argument constellations the command accepts
		boolean canBeBinary = operator.isBinaryOperator();
		boolean canBeUnary = operator.isUnaryOperator();
		boolean canBeNular = operator.isNularOperator();

		// check arguments to determine which constellation can be achieved with
		// given arguments
		if (leftArgumentTypes != null && rightArgumentTypes != null) {
			canBeUnary = false;
			canBeNular = false;
		} else {
			if (leftArgumentTypes == null && rightArgumentTypes != null) {
				canBeBinary = false;
				canBeNular = false;
			} else {
				if (leftArgumentTypes == null && rightArgumentTypes == null) {
					canBeBinary = false;
					canBeUnary = false;
				}
			}
		}

		if (!(canBeBinary || canBeUnary || canBeNular)) {
			// the command can't be used with the given amount of arguments
			if (leftArgumentTypes != null && rightArgumentTypes != null) {
				// tried to use as binary operator
				errorMessage = ProblemMessages.operatorIsNotBinary(operator.getKeyword());

				activeSyntax = null;

				return;
			}

			if (rightArgumentTypes != null) {
				if (operator.isBinaryOperator()) {
					// command is binary but only one arg provided
					errorMessage = ProblemMessages.missingArgLeft(operator.getKeyword());

					activeSyntax = null;

					markerPosition = ERelativePosition.CENTER;
				} else {
					// operator is nular but has one argument provided
					errorMessage = ProblemMessages.operatorIsNular(operator.getKeyword());

					activeSyntax = null;

					markerPosition = ERelativePosition.CENTER;
				}

				return;
			}

			// command must be nular but there must be an argument provided
			errorMessage = ProblemMessages.operatorIsNotNular(operator.getKeyword());

			activeSyntax = null;

			markerPosition = ERelativePosition.RIGHT;

			return;
		}

		List<Syntax> possibleSyntaxes = new ArrayList<Syntax>(operator.getSyntaxes());

		DataTypeList validProvidedLeftTypes = new DataTypeList();
		DataTypeList validGeneralLeftTypes = new DataTypeList();
		DataTypeList validGeneralRightTypes = new DataTypeList();

		for (int i = 0; i < possibleSyntaxes.size(); i++) {
			Syntax currentSyntax = possibleSyntaxes.get(i);

			// sort out syntaxes that can't be matched anyway
			if (currentSyntax.isBinary() && !canBeBinary) {
				possibleSyntaxes.set(i, null);
				continue;
			}
			if (currentSyntax.isUnary() && !canBeUnary) {
				possibleSyntaxes.set(i, null);
				continue;
			}
			if (currentSyntax.isNular() && !canBeNular) {
				possibleSyntaxes.set(i, null);
				continue;
			}

			if (currentSyntax.isBinary()) {
				// directly check the left arg
				DataTypeList validLeftArgTypes = getTypesForSide(currentSyntax, true);
				boolean matched = false;

				// copy the possible types to store-list
				validGeneralLeftTypes.addAllUnique(validLeftArgTypes);

				// compare the provided types with the valid type
				for (ESQFDataType currentType : leftArgumentTypes) {
					if (validLeftArgTypes.containsExchangableType(currentType, true)) {
						matched = true;
						validProvidedLeftTypes.addUnique(currentType);
					}
				}

				if (!matched) {
					possibleSyntaxes.set(i, null);
				}
			}

			if (canBeNular && currentSyntax.isNular()) {
				// syntax has matched
				errorMessage = null;
				activeSyntax = currentSyntax;

				return;
			}
		}

		if (leftArgumentTypes != null && validProvidedLeftTypes.isEmpty()) {
			// left argument is invalid
			errorMessage = ProblemMessages.expectedTypeButGot(validGeneralLeftTypes, leftArgumentTypes);

			activeSyntax = null;

			markerPosition = ERelativePosition.LEFT;
			return;
		}

		// check the provided right argument
		for (Syntax currentSyntax : possibleSyntaxes) {
			if (currentSyntax == null) {
				continue;
			}

			DataTypeList validRightTypes = getTypesForSide(currentSyntax, false);

			// store valid types in list
			validGeneralRightTypes.addAllUnique(validRightTypes);

			// check provided arguments
			for (ESQFDataType currentType : rightArgumentTypes) {
				if (validRightTypes.containsExchangableType(currentType, true)) {
					// The syntax has matched completely
					errorMessage = null;

					if (activeSyntax == null) {
						// Store the matched syntax
						activeSyntax = currentSyntax;
					} else {
						// There was a matching syntax before -> can't decide
						// which one is used
						activeSyntax = null;

						return;
					}
				}
			}
		}

		if (activeSyntax != null) {
			// There has been a matching syntax
			return;
		}

		// TODO: note the left arg ctx in the error msg if possible
		// The right argument is invalid as the program reached this part of the
		// code
		errorMessage = ProblemMessages.expectedTypeButGot(validGeneralRightTypes, rightArgumentTypes);

		activeSyntax = null;

		markerPosition = ERelativePosition.RIGHT;
	}

	/**
	 * Checks whether it is valid to use the given command with the provided
	 * argument types
	 */
	public boolean isValid() {
		return getErrorMessage() == null;
	}

	/**
	 * Gets the error message stating why the current configuration of command and
	 * argument types is invalid
	 * 
	 * @return The error message or <code>null</code> if there are no erros
	 */
	public String getErrorMessage() {
		if (!validated) {
			validate();
		}

		return errorMessage;
	}

	/**
	 * Gets the relative position the error marker should be drawn at. The command
	 * itself is used as the center point
	 */
	public ERelativePosition getErrorMarkerPosition() {
		if (isValid()) {
			return ERelativePosition.NONE;
		}

		return markerPosition;
	}

	/**
	 * Gets the possible argument types for one side of the given syntax
	 * 
	 * @param syntax
	 *            The syntax to retrieve the types from
	 * @param leftSide
	 *            Whether the left side argument should be retrieved
	 * @return A list of possible data types
	 */
	private DataTypeList getTypesForSide(Syntax syntax, boolean leftSide) {
		SyntaxElement element = syntax
				.getElement((leftSide) ? 0 : ((syntax.isBinary()) ? 2 : (syntax.isNular()) ? 0 : 1));

		if (!element.isLeafElement()) {
			throw new SQDevCoreException("Expected element from the syntax for command \"" + syntax.getCommandName()
					+ "\" to be a leaf element");
		}

		DataTypeList dataTypes = new DataTypeList();

		for (String currentType : element.toString().split(DataTypeList.TYPE_SEPERATOR)) {
			if (currentType.isEmpty()) {
				continue;
			}

			ESQFDataType type = ESQFDataType.resolve(currentType);

			if (type == null) {
				try {
					throw new SQDevException("Unable to resolve type \"" + currentType + "\"");
				} catch (SQDevException e) {
					e.printStackTrace();
				}
			} else {
				if (!dataTypes.contains(type)) {
					dataTypes.add(type);
				}
			}
		}

		return dataTypes;
	}

	/**
	 * Gets the return values according to the command and the syntax it is used in.
	 * If the used syntax is invalid this function will return all of the command's
	 * return values
	 */
	public DataTypeList getReturnValues() {
		if (activeSyntax == null) {
			return operator.getAllReturnTypes();
		} else {
			return operator.getReturnTypes(activeSyntax);
		}
	}

	/**
	 * Resets this processor for another usage
	 */
	public void reset() {
		operator = null;
		leftArgumentTypes = null;
		rightArgumentTypes = null;
		validated = false;
		errorMessage = null;
		activeSyntax = null;
		markerPosition = null;
	}

	/**
	 * Sets the operator to be processed by this processor
	 * 
	 * @param operator
	 *            The operator to process
	 */
	public void setOperator(SQFCommand operator) {
		assert (operator != null);
		this.operator = operator;
	}
}
