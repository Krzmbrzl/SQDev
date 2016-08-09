package raven.sqdev.editors.parser.preprocessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Stack;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.part.FileEditorInput;

import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.editors.IMacroSupport;
import raven.sqdev.editors.Macro;
import raven.sqdev.editors.parser.preprocessor.PreprocessorParser.DefineContext;
import raven.sqdev.editors.parser.preprocessor.PreprocessorParser.ErrorContext;
import raven.sqdev.editors.parser.preprocessor.PreprocessorParser.IncludeContext;
import raven.sqdev.util.SQDevPreferenceUtil;

public class PreprocessorParseListener extends PreprocessorBaseListener {
	
	/**
	 * The error message displayed when a cycle in hierarchy is detected
	 */
	public static final String CYCLE_IN_HIERARCHY_MSG = "Cycle in hierarchy!";
	
	/**
	 * The editor this listener should report to
	 */
	private BasicCodeEditor editor;
	/**
	 * A list of all previously visited files (via '#include')
	 */
	private Stack<IPath> includedFiles;
	/**
	 * The start (offset) of the included file
	 */
	private int includedFileStart;
	/**
	 * The length of the included file
	 */
	private int includedFileLength;
	
	
	/**
	 * Creates a new instance of this listener
	 * 
	 * @param editor
	 *            The editor this listener should report to
	 */
	public PreprocessorParseListener(BasicCodeEditor editor) {
		Assert.isNotNull(editor);
		
		this.editor = editor;
		
		includedFiles = new Stack<IPath>();
		
		// indicate that the values have not yet been set
		includedFileStart = -1;
		includedFileLength = -1;
	}
	
	/**
	 * Creates a new instance of this listener
	 * 
	 * @param editor
	 *            The editor this listener should report to
	 * @param files
	 *            The files that have already been visited (via the '#include'
	 *            instruction)
	 */
	private PreprocessorParseListener(BasicCodeEditor editor, Stack<IPath> files, int start,
			int length) {
		Assert.isNotNull(editor);
		
		this.editor = editor;
		
		includedFiles = files;
		
		includedFileStart = start;
		includedFileLength = length;
	}
	
	@Override
	public void exitDefine(DefineContext ctx) {
		if (!(editor instanceof IMacroSupport)) {
			return;
		}
		
		String name = ctx.name.getText();
		
		// add defined macro
		((IMacroSupport) editor).addMacro(new Macro(name));
	}
	
	@Override
	public void exitInclude(IncludeContext ctx) {
		if (includedFileStart == -1 && includedFileLength == -1) {
			// only use the values of the topmost file (The one the user is
			// looking at)
			includedFileStart = ctx.file.getStartIndex();
			includedFileLength = ctx.file.getStopIndex() - ctx.file.getStartIndex() + 1;
		}
		
		if (!(editor.getEditorInput() instanceof FileEditorInput)) {
			editor.createMarker(IMarker.PROBLEM, includedFileStart, includedFileLength,
					"Can't evaluate include-instrution", IMarker.SEVERITY_WARNING);
			// TODO: log
		} else {
			// Get latest origin file
			IPath originFile;
			
			if (includedFiles.size() == 0) {
				originFile = ((FileEditorInput) editor.getEditorInput()).getPath();
				
				// add the original file to the list of "included" files
				includedFiles.push(originFile);
				
				// use the new start + length values for new include instruction
				includedFileStart = ctx.file.getStartIndex();
				includedFileLength = ctx.file.getStopIndex() - ctx.file.getStartIndex() + 1;
			} else {
				originFile = includedFiles.peek();
			}
			
			String strFilePath = ctx.file.getText().substring(1, ctx.file.getText().length() - 1);
			IPath root;
			
			if (strFilePath.startsWith("\\")) {
				root = new Path(SQDevPreferenceUtil.getArmaProgramDirectory());
			} else {
				root = originFile.removeLastSegments(1);
				
				while (strFilePath.startsWith("..\\")) {
					strFilePath = strFilePath.substring(3);
					
					root = root.removeLastSegments(1);
				}
			}
			
			IPath filePath = root.append(strFilePath);
			File file = filePath.toFile();
			
			// Check if path exists
			if (file.exists()) {
				if (!file.isFile()) {
					// must be a file
					reportError(includedFileStart, includedFileLength, "Reference is not a file");
				} else {
					if (includedFiles.contains(filePath)) {
						// report cycle in hierarchy
						reportError(includedFileStart, includedFileLength, CYCLE_IN_HIERARCHY_MSG);
						
						includedFiles.clear();
						
						return;
					} else {
						// add origin file
						includedFiles.push(filePath);
					}
					try {
						ANTLRErrorListener errorListener = new BaseErrorListener() {
							@Override
							public void syntaxError(Recognizer<?, ?> recognizer,
									Object offendingSymbol, int line, int charPositionInline,
									String msg, RecognitionException e) {
								reportError(includedFileStart, includedFileLength,
										"Errors while parsing \"" + file.getPath() + "\": " + msg);
							}
						};
						
						ANTLRInputStream in = new ANTLRInputStream(new FileInputStream(file));
						
						PreprocessorLexer lexer = new PreprocessorLexer(in);
						lexer.removeErrorListeners();
						lexer.addErrorListener(errorListener);
						
						CommonTokenStream tokens = new CommonTokenStream(lexer);
						
						PreprocessorParser parser = new PreprocessorParser(tokens);
						parser.removeErrorListeners();
						parser.addErrorListener(errorListener);
						
						ParseTreeWalker walker = new ParseTreeWalker();
						walker.walk(new PreprocessorParseListener(editor, includedFiles,
								includedFileStart, includedFileLength) {
							@Override
							protected void reportError(int start, int length, String msg) {
								
								if (msg.equals(CYCLE_IN_HIERARCHY_MSG)
										&& includedFiles.size() <= 2) {
									// Don't blame sub-file for cycle in
									// hierarchy
									super.reportError(start, length, msg);
								} else {
									super.reportError(start, length, "Errors while parsing \""
											+ file.getPath() + "\": " + msg);
								}
							}
						}, parser.start());
						
					} catch (IOException e) {
						reportError(includedFileStart, includedFileLength,
								"Failed at parsing referenced file");
						
						e.printStackTrace();
					} finally {
						// remove topmost element from stack as it has been
						// fully processed by now
						if (includedFiles.size() > 0) {
							includedFiles.pop();
						}
					}
				}
			} else {
				reportError(includedFileStart, includedFileLength,
						"\"" + file.getPath() + "\" does not exist");
			}
		}
	}
	
	@Override
	public void exitError(ErrorContext ctx) {
		// error alt has matched -> report error
		if (ctx.UNKNOWN().getText().contains(" ") || ctx.UNKNOWN().getText().contains("\t")) {
			// illegal WS in instruction
			int start = ctx.UNKNOWN().getSymbol().getStartIndex();
			int length = 0;
			boolean matchedWS = false;
			
			for (char currentChar : ctx.UNKNOWN().getText().toCharArray()) {
				if (Character.isWhitespace(currentChar)) {
					matchedWS = true;
					
					length++;
				} else {
					if (!matchedWS) {
						start++;
					} else {
						break;
					}
				}
			}
			
			reportError(start, length, "Preprocessor instructions may not contain whitespace");
		} else {
			// unknown prepprocessor command
			Token unknownToken = ctx.UNKNOWN().getSymbol();
			
			reportError(unknownToken.getStartIndex(),
					unknownToken.getStopIndex() - unknownToken.getStartIndex() + 1,
					"Unknown preprocessor instruction \"" + unknownToken.getText() + "\"");
		}
	}
	
	/**
	 * Reports an error to the editor
	 * 
	 * @param offset
	 *            The start offset of the error
	 * @param length
	 *            The length of the error passage
	 * @param msg
	 *            The error message
	 */
	protected void reportError(int offset, int length, String msg) {
		editor.createMarker(IMarker.PROBLEM, offset, length, msg, IMarker.SEVERITY_ERROR);
	}
	
}
