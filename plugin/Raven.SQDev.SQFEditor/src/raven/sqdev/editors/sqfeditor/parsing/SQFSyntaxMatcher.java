package raven.sqdev.editors.sqfeditor.parsing;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.Assert;

import raven.sqdev.constants.ProblemMessages;
import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.exceptions.SQDevException;
import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.misc.EDataType;
import raven.sqdev.syntax.Syntax;
import raven.sqdev.syntax.SyntaxElement;

public class SQFSyntaxMatcher {

	private static final boolean LEFT = true;
	private static final boolean RIGHT = false;

	/**
	 * The syntaxes to match against
	 */
	private Syntax[] syntaxes;

	/**
	 * The editor to report errors to
	 */
	private BasicCodeEditor editor;

	public SQFSyntaxMatcher(Syntax[] syntaxes, BasicCodeEditor editor) {
		Assert.isNotNull(syntaxes);
		Assert.isNotNull(editor);

		this.syntaxes = syntaxes;
		this.editor = editor;
	}

	/**
	 * Tries to apply the given argument types to the syntax on the right side
	 * of the command
	 * 
	 * @param argumentTypes
	 *            The set of types to test against the syntax
	 * @param offsets
	 *            An array containing the start offset of the right argument and
	 *            it's length in the editor
	 * @return Whether the types could be applied successfully
	 */
	public boolean applyRightArgument(EDataType[] argumentTypes, int[] offsets) {
		return applyArgument(argumentTypes, RIGHT, offsets);
	}

	/**
	 * Tries to apply the given argument types to the syntax on the left side of
	 * the command
	 * 
	 * @param argumentTypes
	 *            The set of types to test against the syntax
	 * @param offsets
	 *            An array containing the start offset of the left argument and
	 *            it's length in the editor
	 * @return Whether the types could be applied successfully
	 */
	public boolean applyLeftArgument(EDataType[] argumentTypes, int[] offsets) {
		return applyArgument(argumentTypes, LEFT, offsets);
	}

	/**
	 * Tests the argument at the given index against the given set of types
	 * 
	 * @param argumentTypes
	 *            The set of types to test
	 * @param leftSide
	 *            Whether the left side argument should be tested
	 * @param offsets
	 *            An array containing the start offset of the respective
	 *            argument and it's length in the editor
	 * @return Whether the types could be applied successfully
	 */
	public boolean applyArgument(EDataType[] argumentTypes, boolean leftSide, int[] offsets) {		
		List<EDataType> possibleTypes = new ArrayList<EDataType>();

		for (Syntax currentSyntax : syntaxes) {
			if(leftSide && !currentSyntax.isBinary()) {
				// non-binary syntaxes can not have left arguments
				continue;
			}
			
			List<EDataType> currentlyPossibleTypes = getTypesForSide(currentSyntax, leftSide);

			for (EDataType currentType : argumentTypes) {
				if (currentlyPossibleTypes.contains(currentType)) {
					return true;
				} else {
					for (EDataType validType : currentlyPossibleTypes) {
						if (validType.canBeReplacedBy(currentType)) {
							return true;
						}else {
							if(currentType.canBeReplacedBy(validType)) {
								// TODO: potential error
								return true;
							}
						}
					}
				}
			}

			for (EDataType currentType : currentlyPossibleTypes) {
				if (!possibleTypes.contains(currentType)) {
					possibleTypes.add(currentType);
				}
			}
		}

		// create error marker
		editor.createMarker(IMarker.PROBLEM, offsets[0],
				offsets[1], ProblemMessages
						.ExpectedTypeButGot(possibleTypes.toArray(new EDataType[possibleTypes.size()]), argumentTypes),
				IMarker.SEVERITY_ERROR);

		return false;
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
	private List<EDataType> getTypesForSide(Syntax syntax, boolean leftSide) {
		SyntaxElement element = syntax.getElement((leftSide) ? 0 : ((syntax.isBinary()) ? 2 : 1));

		if (!element.isLeafElement()) {
			throw new SQDevCoreException("Expected element from the syntax for command \"" + syntax.getCommandName()
					+ "\" to be a leaf element");
		}

		List<EDataType> dataTypes = new ArrayList<EDataType>();

		for (String currentType : element.toString().split(SQFCommand.TYPE_SEPERATOR)) {
			if (currentType.isEmpty()) {
				continue;
			}

			EDataType type = EDataType.resolve(currentType);

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
}
