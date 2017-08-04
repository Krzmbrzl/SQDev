package raven.sqdev.editors.sqfeditor.parsing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.swt.SWT;

import raven.sqdev.constants.ProblemMessages;
import raven.sqdev.editors.sqfeditor.SQF_Editor;
import raven.sqdev.editors.sqfeditor.Variable;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.ArrayContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.AssignmentContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.BinaryExpressionContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.CodeContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.CommonErrorContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.InlineCodeContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.MacroContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.NularExpressionContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.NularOperatorContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.ParenthesisContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.PrimaryExpressionContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.StartContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.StatementContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.StringContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.UnaryExpressionContext;
import raven.sqdev.exceptions.SQDevCoreException;
import raven.sqdev.exceptions.SQDevEditorException;
import raven.sqdev.infoCollection.base.SQFCommand;
import raven.sqdev.misc.DataTypeList;
import raven.sqdev.misc.EDataType;
import raven.sqdev.misc.Macro;
import raven.sqdev.util.SQDevInfobox;

/**
 * This class can be registered as an ANTLR-listener while traversing the parse
 * tree. It will extract information such as variable declarations and it will
 * also perform the syntax check
 * 
 * @author Raven
 *
 */
public class SQFParseListener extends SQFBaseListener {
	
	/**
	 * The invoking state indicating that this context object has been created
	 * by this listener and therefore is not part of the original parseTree
	 */
	protected static final int CREATED_STATE = -10;
	
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
	private BufferedTokenStream stream;
	/**
	 * A map mapping ParserRuleContext objects to the respective return values
	 * in order to access them faster and in order to be able to handle context
	 * specific return values (The value is set right after teh expression has
	 * been parsed therefore the contextual information about which syntaxa has
	 * been used is still available at this time)
	 */
	protected Map<ParseTree, DataTypeList> resolvedReturnValues;
	
	/**
	 * Creates a new instance of this listener.
	 * 
	 * @param editor
	 *            The editor the created listener should be configured to
	 * @param currentStream
	 *            The <code>CommonTokenStream</code> associated with the
	 *            respective parse tree
	 */
	public SQFParseListener(SQF_Editor editor,
			BufferedTokenStream currentStream) {
		Assert.isNotNull(editor);
		Assert.isNotNull(currentStream);
		
		this.editor = editor;
		this.stream = currentStream;
		
		localVariables = new ArrayList<Variable>();
		globalVariables = new ArrayList<Variable>();
		
		resolvedReturnValues = new HashMap<ParseTree, DataTypeList>();
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
					throw new SQDevEditorException(
							"Unexpected child count in assignment");
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
	public void exitStart(StartContext ctx) {
		// end of document has been reached
		
		// set editors variables
		editor.setVariables(localVariables, globalVariables);
	}
	
	@Override
	public void exitCode(CodeContext ctx) {
		// Make sure semicolon is used when necessary
		StatementContext openStatement = null;
		
		for (int i = 0; i < ctx.getChildCount(); i++) {
			ParseTree currentChild = ctx.getChild(i);
			
			if (openStatement != null) {
				if (currentChild instanceof TerminalNodeImpl
						&& currentChild.getText().equals(";")) {
					// reset -> semicolon terminates statement
					openStatement = null;
				} else {
					if (currentChild instanceof StatementContext) {
						// a statement before the previous statement has been
						// closed -> create error
						editor.createMarker(IMarker.PROBLEM,
								openStatement.getStop().getStopIndex(), 1,
								"Missing ';' at \""
										+ openStatement.getStop().getText()
										+ "\"",
								IMarker.SEVERITY_ERROR);
						
						// update the currently open statement
						openStatement = (StatementContext) currentChild;
					}
				}
			} else {
				if (currentChild instanceof StatementContext) {
					// found the context that was handed over to this method
					openStatement = (StatementContext) currentChild;
					
					if (openStatement.getText().equals(";")
							|| endsWithMacro(openStatement)) {
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
		
		IDocument doc = editor.getDocumentProvider()
				.getDocument(editor.getEditorInput());
		
		// don't fold if the code is only one line long
		try {
			if (doc == null || doc.getLineOfOffset(start) == doc
					.getLineOfOffset(stop)) {
				return;
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
			
			SQDevInfobox info = new SQDevInfobox(
					"Error in code folding framework!", e);
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
					((TerminalNode) ctx.children.get(ctx.children.size() - 1))
							.getSymbol().getTokenIndex());
			
			if (hiddenTokens != null) {
				// include possible WS/comments/etc
				for (Token currentToken : hiddenTokens) {
					offset += currentToken.getText().length();
				}
			}
		}
		
		// add foldable area
		editor.addFoldingArea(new Position(start,
				stop - start + ctx.getStop().getText().length() + offset));
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
	public void enterBinaryExpression(BinaryExpressionContext ctx) {
		if (ctx.getChildCount() != 3) {
			if (ctx.getChild(0) instanceof PrimaryExpressionContext) {
				// it's actually a unary expression
				return;
			} else {
				// Because binary operators used with a unary syntax are still
				// labelled binaryExpression
				
				if (ctx.getChildCount() == 2) {
					// binary operator used as a unary operator
					// assemble new rule context
					UnaryExpressionContext unaryCtx = new UnaryExpressionContext(
							ctx, 0);
					unaryCtx.addChild((TerminalNode) ctx.getChild(0));
					unaryCtx.addChild((RuleContext) ctx.getChild(1));
					unaryCtx.start = ctx.start;
					unaryCtx.stop = ctx.stop;
					
					// process rule
					enterUnaryExpression(unaryCtx);
				}
			}
		} else {
			String operatorName = ctx.getChild(1).getText();
			
			extractVariableDeclaration(operatorName, ctx.getChild(2));
		}
	}
	
	@Override
	public void exitBinaryExpression(BinaryExpressionContext ctx) {
		if (ctx.getChildCount() != 3) {
			if (ctx.getChild(0) instanceof PrimaryExpressionContext) {
				// it's actually a unary expression
				return;
			} else {
				// Because binary operators used with a unary/nular syntax are
				// still labelled binaryExpression
				
				if (ctx.getChildCount() == 2) {
					// binary operator used as a unary operator
					// assemble new rule context
					UnaryExpressionContext unaryCtx = new UnaryExpressionContext(
							ctx, 0);
					unaryCtx.addChild((TerminalNode) ctx.getChild(0));
					unaryCtx.addChild((RuleContext) ctx.getChild(1));
					unaryCtx.start = ctx.start;
					unaryCtx.stop = ctx.stop;
					unaryCtx.invokingState = CREATED_STATE;
					// process rule
					exitUnaryExpression(unaryCtx);
				} else {
					// binary operator used as an operand
					// assemble new rule context
					NularOperatorContext nularCtx = new NularOperatorContext(
							new NularExpressionContext());
					nularCtx.addChild((TerminalNode) ctx.getChild(0));
					nularCtx.start = ctx.start;
					nularCtx.stop = ctx.stop;
					
					// process rule
					exitNularOperator(nularCtx);
					
					return;
				}
			}
		}
		
		String operatorName = ctx.getChild(1).getText();
		
		SQFCommand operator = resolveOperator(editor.getBinaryOperators(),
				operatorName);
		
		if (operator != null) {
			DataTypeList leftTypes = getReturnValues(ctx.getChild(0));
			DataTypeList rightTypes = getReturnValues(ctx.getChild(2));
			
			SQFSyntaxProcessor processor = new SQFSyntaxProcessor(operator);
			processor.setLeftArgumentTypes(leftTypes.toArray());
			processor.setRightArgumentTypes(rightTypes.toArray());
			
			if (!processor.isValid()) {
				String errorMsg = processor.getErrorMessage();
				int[] offsets;
				
				switch (processor.getErrorMarkerPosition()) {
					case CENTER:
						offsets = getStartOffsetAndLength(ctx.getChild(1));
						break;
					case LEFT:
						offsets = getStartOffsetAndLength(ctx.getChild(0));
						break;
					case RIGHT:
						offsets = getStartOffsetAndLength(ctx.getChild(2));
						break;
					case NONE:
					default:
						offsets = new int[] { 0, 1 };
						System.err.println(
								"Unexpected marker position for binary expression");
						// TODO: log
						
				}
				
				editor.createMarker(IMarker.PROBLEM, offsets[0], offsets[1],
						errorMsg, IMarker.SEVERITY_ERROR);
			}
			
			// map the resolved processor to the ctx object
			resolvedReturnValues.put(ctx, processor.getReturnValues());
		}
	}
	
	@Override
	public void enterUnaryExpression(UnaryExpressionContext ctx) {
		if (ctx.getChildCount() != 2) {
			// it's actually a nular expression
			return;
		}
		
		String operatorName = ctx.getChild(0).getText();
		
		extractVariableDeclaration(operatorName, ctx.getChild(1));
	}
	
	@Override
	public void exitUnaryExpression(UnaryExpressionContext ctx) {
		if (ctx.getChildCount() != 2) {
			// it's actually a nular expression
			return;
		}
		
		String operatorName = ctx.getChild(0).getText();
		
		SQFCommand operator = resolveOperator(operatorName);
		
		if (operator != null) {
			DataTypeList argumentTypes = getReturnValues(ctx.getChild(1));
			
			SQFSyntaxProcessor processor = new SQFSyntaxProcessor(operator);
			
			processor.setRightArgumentTypes(argumentTypes.toArray());
			
			if (!processor.isValid()) {
				String errorMsg = processor.getErrorMessage();
				int[] offsets;
				
				if (processor
						.getErrorMarkerPosition() == ERelativPosition.RIGHT) {
					// error has to be on the right argument
					offsets = getStartOffsetAndLength(ctx.getChild(1));
				} else {
					// error has to be on the operator itself
					offsets = getStartOffsetAndLength(ctx.getChild(0));
				}
				
				editor.createMarker(IMarker.PROBLEM, offsets[0], offsets[1],
						errorMsg, IMarker.SEVERITY_ERROR);
			}
			
			// Map the resolved processor
			if (ctx.invokingState != CREATED_STATE) {
				resolvedReturnValues.put(ctx, processor.getReturnValues());
			} else {
				// If the ctx has been self-created map the processor to the
				// actual parseTree element
				resolvedReturnValues.put(ctx.getParent(),
						processor.getReturnValues());
			}
		} else {
			// check if operator is a macro
			Macro macro = resolveMacro(editor.getMacros(), operatorName);
			
			if (macro != null) {
				resolvedReturnValues.put(ctx,
						new DataTypeList(EDataType.ANYTHING));
			}
		}
	}
	
	@Override
	public void exitNularOperator(NularOperatorContext ctx) {
		int start = ctx.getStart().getStartIndex();
		int length = ctx.getStart().getStopIndex()
				- ctx.getStart().getStartIndex() + 1;
		String msg = null;
		
		String operatorName = ctx.getText();
		
		SQFCommand operator = resolveOperator(editor.getNularOperators(),
				operatorName);
		
		if (operator == null) {
			if (!isDefinedMacro(operatorName)
					&& !isDefinedLocalVariable(operatorName)) {
				if (!isOperator(operatorName)) {
					if (operatorName.startsWith("_")) {
						// must be an unknown local variable
						msg = ProblemMessages
								.undefinedLocalVariable(operatorName);
					} else {
						// must be a globl variable
						globalVariables.add(new Variable(operatorName));
					}
				} else {
					msg = ProblemMessages.operatorIsNotNular(operatorName);
				}
			}
		} else {
			SQFSyntaxProcessor processor = new SQFSyntaxProcessor(operator);
			if (!processor.isValid()) {
				msg = processor.getErrorMessage();
			}
			
			// map the resolved operator to the ctx object
			resolvedReturnValues.put(ctx, processor.getReturnValues());
		}
		
		if (msg != null) {
			editor.createMarker(IMarker.PROBLEM, start, length, msg,
					IMarker.SEVERITY_ERROR);
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
	protected SQFCommand resolveOperator(List<SQFCommand> commandList,
			String commandName) {
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
				|| resolveOperator(editor.getUnaryOperators(),
						operatorName) != null
				|| resolveOperator(editor.getBinaryOperators(),
						operatorName) != null;
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
	 * element. this function buffers all return types it finds in order to
	 * allow quick access if needed again
	 * 
	 * @param element
	 *            The element to check
	 * @return A <code>DataTypeList</code> of all possible return types
	 */
	protected DataTypeList getReturnValues(ParseTree element) {
		if (!resolvedReturnValues.containsKey(element)) {
			resolvedReturnValues.put(element, doGetReturnValues(element));
		}
		
		return resolvedReturnValues.get(element);
	}
	
	/**
	 * Gets all possible return values for the given <code>ParseTree</code>
	 * element. This function should not be called at any other point than
	 * {@link #getReturnValues(ParseTree)} because it can't handle the return
	 * values of commands as those are processed elsewhere and then bufferedn
	 * into {@link #resolvedReturnValues} which is then accessed by
	 * {@link #getReturnValues(ParseTree)}
	 * 
	 * @param element
	 *            The element to check
	 * @return A <code>DataTypeList</code> of all possible return types
	 */
	private DataTypeList doGetReturnValues(ParseTree element) {
		// Check cache first
		if (resolvedReturnValues.containsKey(element)) {
			return resolvedReturnValues.get(element);
		}
		
		if (element.getClass().equals(MacroContext.class)) {
			return new DataTypeList(EDataType.ANYTHING);
		}
		
		if (element.getClass().equals(AssignmentContext.class)) {
			return new DataTypeList(EDataType.NOTHING);
		}
		
		if (element.getClass().equals(CodeContext.class)
				|| element.getClass().equals(InlineCodeContext.class)) {
			return new DataTypeList(EDataType.CODE);
		}
		
		if (element.getClass().equals(ArrayContext.class)) {
			return new DataTypeList(EDataType.ARRAY);
		}
		
		if (element.getClass().equals(ParenthesisContext.class)) {
			if (element.getChildCount() != 3) {
				// no args in parenthesis
				return new DataTypeList(EDataType.NOTHING);
			}
			
			return doGetReturnValues(element.getChild(1));
		}
		
		/*
		 * if (element.getClass().equals(BinaryExpressionContext.class)) { if
		 * (element.getChildCount() != 3) { // resolve for primary expression
		 * return getReturnValues(element.getChild(0)); }
		 * 
		 * String operatorName = element.getChild(1).getText();
		 * 
		 * return getOperatorReturnValues(operatorName); }
		 */
		
		if (element instanceof TerminalNodeImpl) {
			switch (((TerminalNodeImpl) element).getSymbol().getType()) {
				case SQFParser.NUMBER:
					return new DataTypeList(EDataType.NUMBER);
				case SQFParser.STRING:
					return new DataTypeList(EDataType.STRING);
				default:
					String varName = element.getText().toLowerCase();
					
					// must be a variable | Can't be a command as the return
					// value of a command would already been handled by
					// getReturnValue()
					if (!varName.startsWith("_")) {
						// is global variable
						boolean found = false;
						
						for (Variable currentVariable : globalVariables) {
							if (currentVariable.getKeyword().toLowerCase()
									.equals(varName)) {
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
			return new DataTypeList(EDataType.ANYTHING);
		}
		
		if (element instanceof CommonErrorContext) {
			// return values of errors are undefined
			return new DataTypeList(EDataType.ANYTHING);
		}
		
		if (element.getChildCount() != 1) {
			// TODO: gets reached on unbalanced braces
			throw new SQDevCoreException(
					"Unexpected program flow in SQF syntax checking");
		} else {
			return doGetReturnValues(element.getChild(0));
		}
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
		if (node == null || node.getClass().equals(cl) || node == null) {
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
		if (node == null || node.getClass().equals(cl) || node == null) {
			return node;
		}
		
		if (node.getChildCount() > 0) {
			return getRightNodeOfClass(node.getChild(node.getChildCount() - 1),
					cl);
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
		TerminalNodeImpl startNode = (TerminalNodeImpl) getLeftNodeOfClass(node,
				TerminalNodeImpl.class);
		TerminalNodeImpl endNode = (TerminalNodeImpl) getRightNodeOfClass(node,
				TerminalNodeImpl.class);
		
		if (startNode == null) {
			SQDevInfobox info = new SQDevInfobox(
					"Error while searching for the start index of \""
							+ node.getText() + "\"",
					SWT.ICON_ERROR);
			
			info.open(false);
			
			return new int[] { 0, 0 };
		} else {
			if (endNode == null) {
				SQDevInfobox info = new SQDevInfobox(
						"Error while searching for the end index of \""
								+ node.getText() + "\"",
						SWT.ICON_ERROR);
				
				info.open(false);
				
				return new int[] { startNode.getSymbol().getStartIndex(), 1 };
			}
		}
		
		return new int[] { startNode.getSymbol().getStartIndex(),
				endNode.getSymbol().getStopIndex()
						- startNode.getSymbol().getStartIndex() + 1 };
	}
	
	/**
	 * Extracts a variable declaration out of an operator and it's respective
	 * argument
	 * 
	 * @param operatorName
	 *            The name of the operator declaring the variable (can be
	 *            "private" or "params")
	 * @param argument
	 *            The argument node the operator receives
	 */
	protected void extractVariableDeclaration(String operatorName,
			ParseTree argument) {
		List<Variable> declaredVariables = new ArrayList<Variable>();
		
		// Get variable declarations via "params" and "private"
		switch (operatorName.toLowerCase()) {
			case "params":
				ArrayContext array = (ArrayContext) getLeftNodeOfClass(argument,
						ArrayContext.class);
				
				if (array == null) {
					// some weird shit is going on -> warn about it
					int offsets[] = getStartOffsetAndLength(argument);
					
					editor.createMarker(IMarker.PROBLEM, offsets[0], offsets[1],
							ProblemMessages.failedVarProcessingExpectedArray(),
							IMarker.SEVERITY_WARNING);
					
					break;
				}
				
				for (int i = 1; i < array.getChildCount() - 1; i++) {
					ParseTree currentElement = array.getChild(i);
					
					if (currentElement instanceof TerminalNodeImpl) {
						if (((TerminalNodeImpl) currentElement).getSymbol()
								.getType() == SQFParser.COMMA) {
							continue;
						}
						
						getVariableDeclaration(
								(TerminalNodeImpl) currentElement,
								declaredVariables, true);
					} else {
						boolean wrongType = true;
						ArrayContext arrayNode = (ArrayContext) getLeftNodeOfClass(
								currentElement, ArrayContext.class);
						
						if (arrayNode != null) {
							TerminalNodeImpl stringNode = (TerminalNodeImpl) getLeftNodeOfClass(
									arrayNode.getChild(1),
									TerminalNodeImpl.class);
							
							if (stringNode != null) {
								wrongType = false;
								getVariableDeclaration(stringNode,
										declaredVariables, true);
							}
						} else {
							StringContext stringNode = (StringContext) getLeftNodeOfClass(
									currentElement, StringContext.class);
							
							if (stringNode != null) {
								// The variable is just declared via a String
								// representing it's name
								wrongType = false;
								
								TerminalNodeImpl terminalNode = (TerminalNodeImpl) getLeftNodeOfClass(
										stringNode, TerminalNodeImpl.class);
								
								getVariableDeclaration(terminalNode,
										declaredVariables, true);
							}
						}
						
						if (wrongType) {
							int offsets[] = getStartOffsetAndLength(
									currentElement);
							
							editor.createMarker(IMarker.PROBLEM, offsets[0],
									offsets[1],
									ProblemMessages.expectedTypes(
											new EDataType[] { EDataType.STRING,
													EDataType.ARRAY }),
									IMarker.SEVERITY_ERROR);
						}
					}
				}
				break;
			case "private":
				if (argument.getChild(0) instanceof ArrayContext) {
					for (int i = 1; i < argument.getChild(0).getChildCount()
							- 1; i++) {
						ParseTree currentElement = argument.getChild(0)
								.getChild(i);
						
						currentElement = getLeftNodeOfClass(currentElement,
								TerminalNodeImpl.class);
						
						if (currentElement instanceof TerminalNodeImpl) {
							if (((TerminalNodeImpl) currentElement).getSymbol()
									.getType() == SQFParser.COMMA) {
								continue;
							}
							
							getVariableDeclaration(
									(TerminalNodeImpl) currentElement,
									declaredVariables, false);
						} else {
							int offsets[] = getStartOffsetAndLength(
									currentElement);
							
							editor.createMarker(IMarker.PROBLEM, offsets[0],
									offsets[1],
									ProblemMessages.expectedTypes(
											new EDataType[] { EDataType.STRING,
													EDataType.ARRAY }),
									IMarker.SEVERITY_ERROR);
						}
					}
				} else {
					if (argument.getChild(0) instanceof StringContext) {
						// get varaible dec from the terminal node of the string
						getVariableDeclaration((TerminalNodeImpl) argument
								.getChild(0).getChild(0), declaredVariables,
								false);
					} else {
						int offsets[] = getStartOffsetAndLength(argument);
						
						editor.createMarker(IMarker.PROBLEM, offsets[0],
								offsets[1],
								ProblemMessages.expectedTypes(new EDataType[] {
										EDataType.STRING, EDataType.ARRAY }),
								IMarker.SEVERITY_ERROR);
					}
				}
				
				break;
			
			case "for":
				StringContext varString = (StringContext) getLeftNodeOfClass(
						argument, StringContext.class);
				
				if (varString != null) {
					// get name without quotes
					String varName = varString.getText().substring(1,
							varString.getText().length() - 1);
					
					if (varName.isEmpty()) {
						// may not be empty -> create error
						int[] offsets = getStartOffsetAndLength(varString);
						
						editor.createMarker(IMarker.PROBLEM, offsets[0],
								offsets[1],
								ProblemMessages.stringMayNotBeEmpty(),
								IMarker.SEVERITY_ERROR);
					} else {
						if (!varName.startsWith("_")) {
							// can only declare local variable -> create error
							int[] offsets = getStartOffsetAndLength(varString);
							
							editor.createMarker(IMarker.PROBLEM, offsets[0],
									offsets[1],
									ProblemMessages
											.canOnlyDeclareLocalVariable(),
									IMarker.SEVERITY_ERROR);
						} else {
							localVariables.add(new Variable(varName));
						}
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
	 * @param allowEmpty
	 *            Whether an empty String is a valid input at this point
	 */
	private void getVariableDeclaration(TerminalNodeImpl node,
			List<Variable> varlist, boolean allowEmpty) {
		int start = node.symbol.getStartIndex();
		int length = node.symbol.getStopIndex() - node.symbol.getStartIndex()
				+ 1;
		
		if (node.symbol.getType() == SQFParser.STRING) {
			String varName = node.getText();
			// remove quotes
			varName = varName.substring(1, varName.length() - 1);
			
			if (varName.isEmpty()) {
				if (allowEmpty) {
					// empty String is fine
					return;
				} else {
					// empty String is invalid
					editor.createMarker(IMarker.PROBLEM, start, length,
							ProblemMessages.stringMayNotBeEmpty(),
							IMarker.SEVERITY_ERROR);
				}
			}
			
			if (varName.contains(" ")) {
				editor.createMarker(IMarker.PROBLEM, start, length,
						ProblemMessages.variableMayNotContainBlank(),
						IMarker.SEVERITY_ERROR);
			} else {
				if (varName.startsWith("_")) {
					varlist.add(new Variable(varName));
				} else {
					editor.createMarker(IMarker.PROBLEM, start, length,
							ProblemMessages.canOnlyDeclareLocalVariable(),
							IMarker.SEVERITY_ERROR);
				}
			}
		} else {
			editor.createMarker(IMarker.PROBLEM, start, length,
					ProblemMessages.expectedTypes(new EDataType[] {
							EDataType.STRING, EDataType.ARRAY }),
					IMarker.SEVERITY_ERROR);
		}
	}
}
