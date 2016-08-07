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
import org.eclipse.jface.text.Position;

import raven.sqdev.editors.sqfeditor.SQF_Editor;
import raven.sqdev.editors.sqfeditor.Variable;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.AssignmentContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.CodeContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.InlineCodeContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.PreprocessorContext;
import raven.sqdev.editors.sqfeditor.parsing.SQFParser.StatementContext;
import raven.sqdev.exceptions.SQDevEditorException;

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
				if (currentChild instanceof TerminalNodeImpl
						&& currentChild.getText().equals(";")) {
					// reset -> semicolon terminates statement
					openStatement = null;
				} else {
					if (currentChild instanceof StatementContext) {
						// a statement before the previous statement has been
						// closed -> create error
						editor.createMarker(IMarker.PROBLEM, openStatement.getStop().getStopIndex(),
								1, "Missing ';' at \"" + openStatement.getStop().getText() + "\"",
								IMarker.SEVERITY_ERROR);
						
						// update the currently open statement
						openStatement = (StatementContext) currentChild;
					}
				}
			} else {
				if (currentChild instanceof StatementContext) {
					// found the context that was handed over to this method
					openStatement = (StatementContext) currentChild;
				}
			}
		}
	}
	
	@Override
	public void exitInlineCode(InlineCodeContext ctx) {
		int start = ctx.getStart().getStartIndex();
		int stop = ctx.getStop().getStopIndex();
		
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
					((TerminalNode) ctx.children.get(ctx.children.size() - 1)).getSymbol()
							.getTokenIndex());
			
			if (hiddenTokens != null) {
				// include possible WS/comments/etc
				for (Token currentToken : hiddenTokens) {
					offset += currentToken.getText().length();
				}
			}
		}
		
		// add foldable area
		editor.addFoldingArea(
				new Position(start, stop - start + ctx.getStop().getText().length() + offset));
	}
	
	@Override
	public void exitPreprocessor(PreprocessorContext ctx) {
		// StringBuilder input = new StringBuilder();
		//
		// for (int i = 0; i < ctx.getChildCount(); i++) {
		// ParseTree current = ctx.getChild(i);
		//
		// input.append(current.getText());
		//
		// List<Token> hiddenTokens = null;
		//
		// if (current instanceof TerminalNodeImpl) {
		// hiddenTokens = stream.getHiddenTokensToRight(
		// ((TerminalNodeImpl) current).getSymbol().getTokenIndex());
		// } else {
		// if (current instanceof ParserRuleContext) {
		// hiddenTokens = stream.getHiddenTokensToRight(
		// ((ParserRuleContext) current).getStop().getTokenIndex());
		// }
		// }
		//
		// if (hiddenTokens != null) {
		// for (Token currentToken : hiddenTokens) {
		// input.append(currentToken.getText());
		// }
		// }
		// }
		//
		// ANTLRInputStream in = new ANTLRInputStream(input.toString());
		//
		// PreprocessorLexer lexer = new PreprocessorLexer(in);
		//
		// CommonTokenStream tokens = new CommonTokenStream(lexer);
		//
		// PreprocessorParser parser = new PreprocessorParser(tokens);
		//
		// parser.removeErrorListeners();
		// parser.addErrorListener(
		// new PreprocessorErrorListener(editor,
		// ctx.getStart().getStartIndex()));
		//
		// ParseTreeWalker walker = new ParseTreeWalker();
		//
		// walker.walk(new PreprocessorParseListener(editor), parser.start());
	}
}
