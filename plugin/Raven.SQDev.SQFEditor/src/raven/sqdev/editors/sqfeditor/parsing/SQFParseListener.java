package raven.sqdev.editors.sqfeditor.parsing;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;

import raven.sqdev.constants.ProblemMessages;
import raven.sqdev.editors.Macro;
import raven.sqdev.editors.sqfeditor.SQF_Editor;
import raven.sqdev.editors.sqfeditor.Variable;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.ArrayContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.AssignmentContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.BinaryExpressionContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.CodeContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.InlineCodeContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.MacroContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.MacroExpressionContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.NularExpressionContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.ParenthesisContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.PrimaryExpressionContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.StatementContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.UnaryExpressionContext;
import raven.sqdev.exceptions.SQDevEditorException;
import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.misc.EDataType;
import raven.sqdev.syntax.Syntax;
import raven.sqdev.util.SQDevInfobox;

public class SQFParseListener extends SQFBaseListener {

	/**
	 * The editor this listener should report to
	 */
	private SQF_Editor editor;

	/**
	 * A list of found localVariables
	 */
	private List<Variable> localVariables;
	/**
	 * A list of found global variables
	 */
	private List<Variable> globalVariables;
	/**
	 * The respective <code>CommonTokenStream</code> associated with the parse
	 * tree this listener corresponds to
	 */
	private CommonTokenStream stream;

	/**
	 * Creates a new instance of this listener.
	 * 
	 * @param editor
	 *            The editor the created listener should be configured to
	 * @param stream
	 *            The <code>CommonTokenStream</code> associated with the
	 *            respective parse tree
	 */
	public SQFParseListener(SQF_Editor editor, CommonTokenStream stream) {
		Assert.isNotNull(editor);
		Assert.isNotNull(stream);

		this.editor = editor;
		this.stream = stream;

		localVariables = new ArrayList<Variable>();
		globalVariables = new ArrayList<Variable>();
	}

	/**
	 * Gets the editor this listener is configured on
	 */
	public SQF_Editor getConfiguredEditor() {
		return editor;
	}

	@Override
	public void exitAssignment(AssignmentContext ctx) {
		String variableName = null;

		switch (ctx.getChildCount()) {
		case 3:
			variableName = ctx.getChild(0).getText();
			break;

		case 4:
			variableName = ctx.getChild(1).getText();
			break;

		default:
			try {
				throw new SQDevEditorException("Unexpected child count in assignment");
			} catch (SQDevEditorException e) {
				// TODO: log
				e.printStackTrace();
			}
		}

		Variable variable = new Variable(variableName);

		if (variable.isLocal()) {
			if (!localVariables.contains(variable)) {
				localVariables.add(variable);
			}
		} else {
			if (!globalVariables.contains(variable)) {
				globalVariables.add(variable);
			}
		}
	}

	@Override
	public void exitCode(CodeContext ctx) {
		if (ctx.getParent() == null) {
			// end of document has been reached

			// set editors variables
			editor.setVariables(localVariables, globalVariables);
		}

		// Make sure semicolon is used when necessary
		StatementContext openStatement = null;

		for (int i = 0; i < ctx.getChildCount(); i++) {
			ParseTree currentChild = ctx.getChild(i);

			if (openStatement != null) {
				if (currentChild instanceof TerminalNodeImpl && currentChild.getText().equals(";")) {
					// reset -> semicolon terminates statement
					openStatement = null;
				} else {
					if (currentChild instanceof StatementContext) {
						// a statement before the previous statement has been
						// closed -> create error
						editor.createMarker(IMarker.PROBLEM, openStatement.getStop().getStopIndex(), 1,
								"Missing ';' at \"" + openStatement.getStop().getText() + "\"",
								IMarker.SEVERITY_ERROR);

						// update the currently open statement
						openStatement = (StatementContext) currentChild;
					}
				}
			} else {
				if (currentChild instanceof StatementContext) {
					// found the context that was handed over to this method
					openStatement = (StatementContext) currentChild;

					if (openStatement.getText().equals(";") || endsWithMacro(openStatement)) {
						// empty statements are allowed
						openStatement = null;
					}
				}
			}
		}
	}

	@Override
	public void exitInlineCode(InlineCodeContext ctx) {
		int start = ctx.getStart().getStartIndex();
		int stop = ctx.getStop().getStopIndex();

		IDocument doc = editor.getDocumentProvider().getDocument(editor.getEditorInput());

		// don't fold if the code is only one line long
		try {
			if (doc == null || doc.getLineOfOffset(start) == doc.getLineOfOffset(stop)) {
				return;
			}
		} catch (BadLocationException e) {
			e.printStackTrace();

			SQDevInfobox info = new SQDevInfobox("Error in code folding framework!", e);
			info.open(false);

			return;
		}

		ParserRuleContext parent = ctx.getParent();
		ParserRuleContext previous = ctx;

		while (!(parent instanceof CodeContext)) {
			previous = parent;
			parent = parent.getParent();
		}

		int offset = 0;

		if (parent.children.size() - 1 > parent.children.indexOf(previous)) {
			// there is a trailing semicolon that has to be folded as well
			offset = 1;

			// check for hidden tokens between bracket and semicolon
			List<Token> hiddenTokens = stream.getHiddenTokensToRight(
					((TerminalNode) ctx.children.get(ctx.children.size() - 1)).getSymbol().getTokenIndex());

			if (hiddenTokens != null) {
				// include possible WS/comments/etc
				for (Token currentToken : hiddenTokens) {
					offset += currentToken.getText().length();
				}
			}
		}

		// add foldable area
		editor.addFoldingArea(new Position(start, stop - start + ctx.getStop().getText().length() + offset));
	}

	/**
	 * Checks wheather the given <code>RuleContext</code> is a
	 * 
	 * @param node
	 *            The node to check
	 */
	private boolean endsWithMacro(ParseTree node) {
		if (node.getChildCount() == 0) {
			return false;
		}

		ParseTree endNode = node.getChild(node.getChildCount() - 1);

		if (endNode instanceof MacroContext) {
			return true;
		} else {
			return endsWithMacro(endNode);
		}
	}

	@Override
	public void exitBinaryExpression(BinaryExpressionContext ctx) {
		if (ctx.getChildCount() != 3) {
			// it's actually a unary expression
			return;
		}

		String operatorName = ctx.getChild(1).getText();

		SQFCommand operator = resolveOperator(editor.getBinaryOperators(), operatorName);

		if (operator != null) {
			EDataType[] leftTypes = getReturnValues(ctx.getChild(0));
			EDataType[] rightTypes = getReturnValues(ctx.getChild(2));

			List<Syntax> syntaxes = operator.getSyntaxes();

			SQFSyntaxMatcher matcher = new SQFSyntaxMatcher(syntaxes.toArray(new Syntax[syntaxes.size()]),
					editor);

			matcher.applyLeftArgument(leftTypes, getStartOffsetAndLength(ctx.getChild(0)));
			matcher.applyRightArgument(rightTypes, getStartOffsetAndLength(ctx.getChild(2)));
		}
	}

	@Override
	public void enterUnaryExpression(UnaryExpressionContext ctx) {
		if (ctx.getChildCount() != 2) {
			// it's actually a nular expression
			return;
		}

		int start = ctx.getStart().getStartIndex();
		int length = ctx.getStart().getStopIndex() - ctx.getStart().getStartIndex() + 1;

		String operatorName = ctx.getChild(0).getText();

		List<Variable> declaredVariables = new ArrayList<Variable>();

		// Get variable declarations via "params" and "private"
		switch (operatorName.toLowerCase()) {
		case "params":
			if (!(ctx.getChild(1) instanceof ArrayContext)) {
				// some weird shit is going on -> warn about it
				editor.createMarker(IMarker.PROBLEM, start, length,
						ProblemMessages.failedVarProcessingExpectedArray(), IMarker.SEVERITY_WARNING);
			}

			for (int i = 1; i < ctx.getChild(1).getChildCount() - 1; i++) {
				ParseTree currentElement = ctx.getChild(1).getChild(i);

				if (currentElement instanceof TerminalNodeImpl) {
					if (((TerminalNodeImpl) currentElement).getSymbol().getType() == SQFParser.COMMA) {
						continue;
					}

					getVariableDeclaration((TerminalNodeImpl) currentElement, declaredVariables);
				} else {
					boolean wrongType = true;
					ArrayContext arrayNode = (ArrayContext) getLeftNodeOfClass(currentElement,
							ArrayContext.class);

					if (arrayNode != null) {
						TerminalNodeImpl stringNode = (TerminalNodeImpl) getLeftNodeOfClass(
								arrayNode.getChild(1), TerminalNodeImpl.class);

						if (stringNode != null) {
							wrongType = false;
							getVariableDeclaration(stringNode, declaredVariables);
						}
					}

					if (wrongType) {
						int offsets[] = getStartOffsetAndLength(currentElement);

						editor.createMarker(IMarker.PROBLEM, offsets[0], offsets[1],
								ProblemMessages
										.expectedTypes(new EDataType[] { EDataType.STRING, EDataType.ARRAY }),
								IMarker.SEVERITY_ERROR);
					}
				}
			}
			break;
		case "private":
			if (ctx.getChild(1) instanceof ArrayContext) {
				for (int i = 1; i < ctx.getChild(1).getChildCount() - 1; i++) {
					ParseTree currentElement = ctx.getChild(1).getChild(i);

					currentElement = getLeftNodeOfClass(currentElement, TerminalNodeImpl.class);

					if (currentElement instanceof TerminalNodeImpl) {
						if (((TerminalNodeImpl) currentElement).getSymbol().getType() == SQFParser.COMMA) {
							continue;
						}

						getVariableDeclaration((TerminalNodeImpl) currentElement, declaredVariables);
					} else {
						int offsets[] = getStartOffsetAndLength(currentElement);

						editor.createMarker(IMarker.PROBLEM, offsets[0], offsets[1],
								ProblemMessages
										.expectedTypes(new EDataType[] { EDataType.STRING, EDataType.ARRAY }),
								IMarker.SEVERITY_ERROR);
					}
				}
			} else {
				TerminalNodeImpl node = (TerminalNodeImpl) getLeftNodeOfClass(ctx.getChild(1),
						TerminalNodeImpl.class);

				if (node instanceof TerminalNodeImpl) {
					getVariableDeclaration(node, declaredVariables);
				} else {
					int offsets[] = getStartOffsetAndLength(ctx.getChild(1));

					editor.createMarker(IMarker.PROBLEM, offsets[0], offsets[1],
							ProblemMessages
									.expectedTypes(new EDataType[] { EDataType.STRING, EDataType.ARRAY }),
							IMarker.SEVERITY_ERROR);
				}
			}
		}
		localVariables.addAll(declaredVariables);
	}

	/**
	 * Extracts a variable name out of a TerminalNode
	 * 
	 * @param node
	 *            The node to extract the variable name from
	 * @param varlist
	 *            The list of variables a successfull declaration should be
	 *            added to
	 */
	private void getVariableDeclaration(TerminalNodeImpl node, List<Variable> varlist) {
		int start = node.symbol.getStartIndex();
		int length = node.symbol.getStopIndex() - node.symbol.getStartIndex() + 1;

		if (node.symbol.getType() == SQFParser.STRING) {
			String varName = node.getText();
			// remove quotes
			varName = varName.substring(1, varName.length() - 1);

			if (varName.contains(" ")) {
				editor.createMarker(IMarker.PROBLEM, start, length,
						ProblemMessages.variableMayNotContainBlank(), IMarker.SEVERITY_ERROR);
			} else {

				if (varName.startsWith("_")) {
					varlist.add(new Variable(varName));
				} else {
					editor.createMarker(IMarker.PROBLEM, start, length,
							ProblemMessages.canOnlyDeclareLocalVariable(), IMarker.SEVERITY_ERROR);
				}
			}
		} else {
			editor.createMarker(IMarker.PROBLEM, start, length,
					ProblemMessages.expectedTypes(new EDataType[] { EDataType.STRING, EDataType.ARRAY }),
					IMarker.SEVERITY_ERROR);
		}
	}

	@Override
	public void exitUnaryExpression(UnaryExpressionContext ctx) {
		if (ctx.getChildCount() != 2) {
			// it's actually a nular expression
			return;
		}

		String operatorName = ctx.getChild(0).getText();

		SQFCommand operator = resolveOperator(editor.getUnaryOperators(), operatorName);

		if (operator != null) {
			EDataType[] argumentTypes = getReturnValues(ctx.getChild(1));

			List<Syntax> syntaxes = operator.getSyntaxes();

			SQFSyntaxMatcher matcher = new SQFSyntaxMatcher(syntaxes.toArray(new Syntax[syntaxes.size()]),
					editor);

			matcher.applyRightArgument(argumentTypes, getStartOffsetAndLength(ctx.getChild(1)));
		} else {
			int start = -1;
			int length = -1;
			String msg = null;

			// check if operator is a macro
			Macro macro = resolveMacro(editor.getMacros(), operatorName);

			if (macro == null) {
				if (resolveOperator(editor.getBinaryOperators(), operatorName) != null) {
					// binary operator
					start = ctx.getStart().getStartIndex();
					length = ctx.getStart().getStopIndex() - ctx.getStart().getStartIndex() + 1;
					msg = ProblemMessages.missingArgLeft(operatorName);
				} else {
					if (resolveOperator(editor.getNularOperators(), operatorName) != null) {
						// nular operator
						start = ctx.getStart().getStartIndex();
						length = ctx.getStart().getStopIndex() - ctx.getStart().getStartIndex() + 1;
						msg = ProblemMessages.operatorIsNular(operatorName);
					} else {
						start = ctx.getStart().getStartIndex();
						length = ctx.getStart().getStopIndex() - ctx.getStart().getStartIndex() + 1;
						msg = ProblemMessages.unknownOperator(operatorName);
					}
				}
			}

			if (msg != null) {
				editor.createMarker(IMarker.PROBLEM, start, length, msg, IMarker.SEVERITY_ERROR);
			}

			return;
		}
	}

	@Override
	public void exitNularExpression(NularExpressionContext ctx) {
		String operatorName = ctx.getText();

		SQFCommand operator = resolveOperator(editor.getNularOperators(), operatorName);

		if (operator == null) {
			int start = ctx.getStart().getStartIndex();
			int length = ctx.getStart().getStopIndex() - ctx.getStart().getStartIndex() + 1;
			String msg = null;

			if (!isDefinedMacro(operatorName) && !isDefinedLocalVariable(operatorName)) {
				if (!isOperator(operatorName)) {
					if (operatorName.startsWith("_")) {
						msg = ProblemMessages.undefinedLocalVariable(operatorName);
					}
				} else {
					msg = ProblemMessages.operatorIsNotNular(operatorName);
				}
			}

			if (msg != null) {
				editor.createMarker(IMarker.PROBLEM, start, length, msg, IMarker.SEVERITY_ERROR);
			}
		}
	}

	/**
	 * Retrieves the <code>SQFCommand</code> out of the list that has the given
	 * name. The search is case-<b>in</b>sensitive!
	 * 
	 * @param commandList
	 *            The list of commands to search through
	 * @param commandName
	 *            The command name to search for
	 * @return The respective <code>SQFCommand</code> or <code>null</code> if
	 *         none could be found
	 */
	protected SQFCommand resolveOperator(List<SQFCommand> commandList, String commandName) {
		commandName = commandName.toLowerCase();

		for (SQFCommand currentCommand : commandList) {
			if (currentCommand.getKeyword().toLowerCase().equals(commandName)) {
				return currentCommand;
			}
		}

		return null;
	}

	/**
	 * Retrieves the <code>SQFCommand</code> out of the list that has the given
	 * name. The search is case-<b>in</b>sensitive!
	 * 
	 * @param operatorName
	 *            The operator name to search for
	 * @return The respective <code>SQFCommand</code> or <code>null</code> if
	 *         none could be found
	 */
	protected SQFCommand resolveOperator(String operatorName) {
		List<SQFCommand> allOperators = editor.getNularOperators();
		allOperators.addAll(editor.getUnaryOperators());
		allOperators.addAll(editor.getBinaryOperators());

		return resolveOperator(allOperators, operatorName);
	}

	/**
	 * Finds the <code>Macro</code> in the given list that has the given name.
	 * The search is case-sensitive!
	 * 
	 * @param macroList
	 *            The list of macros to search through
	 * @param macroName
	 *            The name of the <code>Macro</code> to find
	 * @return The found <code>Macro</code> or <code>null</code> if none could
	 *         be found
	 */
	protected Macro resolveMacro(List<Macro> macroList, String macroName) {
		for (Macro currentMacro : macroList) {
			if (currentMacro.getKeyword().equals(macroName)) {
				return currentMacro;
			}
		}

		return null;
	}

	/**
	 * Checks whether there is a defined macro with the given name
	 * (case-sensitive)
	 * 
	 * @param macroName
	 *            The macro name to search for
	 */
	protected boolean isDefinedMacro(String macroName) {
		return resolveMacro(editor.getMacros(), macroName) != null;
	}

	/**
	 * Checks whether there is an operator with the given name
	 * (case-insensitive)
	 * 
	 * @param operatorName
	 *            The name to search for
	 */
	protected boolean isOperator(String operatorName) {
		return resolveOperator(editor.getNularOperators(), operatorName) != null
				|| resolveOperator(editor.getUnaryOperators(), operatorName) != null
				|| resolveOperator(editor.getBinaryOperators(), operatorName) != null;
	}

	/**
	 * Checks whether there is a defined local variable of the given name
	 * (case-insensitive)
	 * 
	 * @param varName
	 *            The variable name to search for
	 */
	protected boolean isDefinedLocalVariable(String varName) {
		varName = varName.toLowerCase();

		for (Variable currentVariable : editor.getMagicVariables()) {
			if (currentVariable.getKeyword().toLowerCase().equals(varName)) {
				return true;
			}
		}
		for (Variable currentVariable : localVariables) {
			if (currentVariable.getKeyword().toLowerCase().equals(varName)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Gets all possible return values for the given <code>ParseTree</code>
	 * element
	 * 
	 * @param element
	 *            The element to check
	 * @return An array of all possible return types
	 */
	protected EDataType[] getReturnValues(ParseTree element) {
		if (element.getClass().equals(MacroExpressionContext.class)
				|| element.getClass().equals(MacroContext.class)) {
			return new EDataType[] { EDataType.ANYTHING };
		}

		if (element.getClass().equals(AssignmentContext.class)) {
			return new EDataType[] { EDataType.NOTHING };
		}

		if (element.getClass().equals(CodeContext.class)
				|| element.getClass().equals(InlineCodeContext.class)) {
			return new EDataType[] { EDataType.CODE };
		}

		if (element.getClass().equals(ArrayContext.class)) {
			return new EDataType[] { EDataType.ARRAY };
		}

		if (element.getClass().equals(ParenthesisContext.class)) {
			if (element.getChildCount() != 3) {
				// no args in parenthesis
				return new EDataType[] { EDataType.NOTHING };
			}

			return getReturnValues(element.getChild(1));
		}

		if (element.getClass().equals(BinaryExpressionContext.class)) {
			if (element.getChildCount() != 3) {
				// resolve for primary expression
				return getReturnValues(element.getChild(0));
			}

			String operatorName = element.getChild(1).getText();

			return getOperatorReturnValues(operatorName);
		}

		if (element.getClass().equals(PrimaryExpressionContext.class)) {
			if (element.getChildCount() == 1) {
				if (element instanceof TerminalNodeImpl) {
					switch (((TerminalNodeImpl) element).symbol.getType()) {
					case SQFParser.NUMBER:
						return new EDataType[] { EDataType.NUMBER };
					case SQFParser.STRING:
						return new EDataType[] { EDataType.STRING };
					default:
						// must be a common error with no argument -> undefined;
						// error elsewhere
						return new EDataType[] { EDataType.ANYTHING };
					}
				} else {
					return getReturnValues(element.getChild(0));
				}
			}

			if (element.getChild(0) instanceof TerminalNodeImpl) {
				// parenthesis, code or array
				switch (((TerminalNodeImpl) element.getChild(0)).symbol.getType()) {
				case SQFParser.C_B_O:
					return new EDataType[] { EDataType.CODE };
				case SQFParser.S_B_O:
					return new EDataType[] { EDataType.ARRAY };
				default:
					if (element.getChild(1) instanceof TerminalNode) {
						// Patrenthesis with no arguments
						return new EDataType[] { EDataType.NOTHING };
					} else {
						// retrieve return type of argument
						return getReturnValues(element.getChild(1));
					}
				}
			}
		}

		if (element instanceof TerminalNodeImpl) {
			switch (((TerminalNodeImpl) element).getSymbol().getType()) {
			case SQFParser.NUMBER:
				return new EDataType[] { EDataType.NUMBER };
			case SQFParser.STRING:
				return new EDataType[] { EDataType.STRING };
			default:
				SQFCommand operator = resolveOperator(element.getText());

				if (operator != null) {
					return getOperatorReturnValues(operator);
				} else {
					String varName = element.getText().toLowerCase();

					// not a known operator -> must be a variable
					if (!varName.startsWith("_")) {
						// is global variable
						boolean found = false;

						for (Variable currentVariable : globalVariables) {
							if (currentVariable.getKeyword().toLowerCase().equals(varName)) {
								found = true;
								break;
							}
						}

						if (!found) {
							// assume it's declared somewhere else
							// TODO: potential error
							globalVariables.add(new Variable(varName));
						}
					}
				}

				// a variable can be anything
				return new EDataType[] { EDataType.ANYTHING };
			}
		} else {
			// can either be a nular or unary expression; either way the
			// operator is
			// the first node
			return getReturnValues(element.getChild(0));
		}

	}

	/**
	 * Gets all possible return types for the command with the given name
	 * 
	 * @param operatorName
	 *            the name of the operator
	 * @return An array of return types
	 */
	protected EDataType[] getOperatorReturnValues(String operatorName) {
		System.out.println(operatorName);

		List<SQFCommand> allOperators = new ArrayList<SQFCommand>(editor.getNularOperators());
		allOperators.addAll(editor.getUnaryOperators());
		allOperators.addAll(editor.getBinaryOperators());

		SQFCommand operator = resolveOperator(allOperators, operatorName);

		if (operator == null) {
			// most likely a variable
			return new EDataType[] { EDataType.ANYTHING };
		}

		return getOperatorReturnValues(operator);
	}

	/**
	 * Gets all possible return types for the given command
	 * 
	 * @param operator
	 *            The respective command
	 * @return An array of return types
	 */
	protected EDataType[] getOperatorReturnValues(SQFCommand operator) {
		if (operator == null) {
			// TODO: log
			System.err.println("Null operator in SQFparseListener!");

			return new EDataType[] { EDataType.NOTHING };
		}

		if (!operator.hasReturnValue()) {
			return new EDataType[] { EDataType.NOTHING };
		}

		List<EDataType> returnTypes = new ArrayList<EDataType>();

		for (String currentReturnType : operator.getReturnType().split(SQFCommand.TYPE_SEPERATOR)) {
			if (currentReturnType.isEmpty()) {
				continue;
			}

			EDataType type = EDataType.resolve(currentReturnType);

			if (type == null) {
				// TODO: log
				System.err.println("Unsupported data type \"" + currentReturnType + "\"!");
			} else {
				if (!returnTypes.contains(type)) {
					returnTypes.add(type);
				}
			}
		}

		if (returnTypes.isEmpty()) {
			returnTypes.add(EDataType.NOTHING);

			// TODO: log
			System.err.print("Inserted Nothing data types as none could be found!");
		}

		return returnTypes.toArray(new EDataType[returnTypes.size()]);
	}

	/**
	 * Searches if the left hand path of this ParseTree node contains a node of
	 * the given class
	 * 
	 * @param node
	 *            The node to search through
	 * @param cl
	 *            The class to search for
	 * @return The respective node or <code>null</code> if none could be found
	 */
	protected ParseTree getLeftNodeOfClass(ParseTree node, Class<?> cl) {
		if (node.getClass().equals(cl) || node == null) {
			return node;
		}

		if (node.getChildCount() > 0) {
			return getLeftNodeOfClass(node.getChild(0), cl);
		} else {
			return null;
		}
	}

	/**
	 * Searches if the right hand path of this ParseTree node contains a node of
	 * the given class
	 * 
	 * @param node
	 *            The node to search through
	 * @param cl
	 *            The class to search for
	 * @return The respective node or <code>null</code> if none could be found
	 */
	protected ParseTree getRightNodeOfClass(ParseTree node, Class<?> cl) {
		if (node.getClass().equals(cl) || node == null) {
			return node;
		}

		if (node.getChildCount() > 0) {
			return getLeftNodeOfClass(node.getChild(node.getChildCount() - 1), cl);
		} else {
			return null;
		}
	}

	/**
	 * Gets the start offset and the length of the given node
	 * 
	 * @param node
	 *            The node's whose offset should be determined
	 * @return An two-dimensional array containing the start offset and the
	 *         length
	 */
	protected int[] getStartOffsetAndLength(ParseTree node) {
		TerminalNodeImpl startNode = (TerminalNodeImpl) getLeftNodeOfClass(node, TerminalNodeImpl.class);
		TerminalNodeImpl endNode = (TerminalNodeImpl) getRightNodeOfClass(node, TerminalNodeImpl.class);

		return new int[] { startNode.getSymbol().getStartIndex(),
				endNode.getSymbol().getStopIndex() - startNode.getSymbol().getStartIndex() + 1 };
	}
}
