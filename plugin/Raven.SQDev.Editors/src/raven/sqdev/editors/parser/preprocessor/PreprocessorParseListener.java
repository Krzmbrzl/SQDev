package raven.sqdev.editors.parser.preprocessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.ui.part.FileEditorInput;

import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.editors.IMacroSupport;
import raven.sqdev.editors.Macro;
import raven.sqdev.editors.parser.preprocessor.PreprocessorParser.DefineContext;
import raven.sqdev.editors.parser.preprocessor.PreprocessorParser.ErrorContext;
import raven.sqdev.editors.parser.preprocessor.PreprocessorParser.IncludeContext;

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
	private List<IPath> includedFiles;
	
	
	/**
	 * Creates a new instance of this listener
	 * 
	 * @param editor
	 *            The editor this listener should report to
	 */
	public PreprocessorParseListener(BasicCodeEditor editor) {
		Assert.isNotNull(editor);
		
		this.editor = editor;
		
		includedFiles = new ArrayList<IPath>();
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
	private PreprocessorParseListener(BasicCodeEditor editor, List<IPath> files) {
		Assert.isNotNull(editor);
		
		this.editor = editor;
		
		includedFiles = files;
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
		int start = ctx.file.getStartIndex();
		int length = ctx.file.getStopIndex() - ctx.file.getStartIndex() + 1;
		
		if (!(editor.getEditorInput() instanceof FileEditorInput)) {
			editor.createMarker(IMarker.PROBLEM, start, length, "Can't evaluate include-instrution",
					IMarker.SEVERITY_WARNING);
			// TODO: log
		} else {
			String strFilePath = ctx.file.getText().substring(1, ctx.file.getText().length() - 1);
			
			if (strFilePath.startsWith("\\")) {
				// TODO: start from ArmA main directory
			} else {
				IPath originFile = ((FileEditorInput) editor.getEditorInput()).getPath();
				
				if (includedFiles.contains(originFile)) {
					// report cycle in hierarchy
					reportError(start, length, CYCLE_IN_HIERARCHY_MSG);
					return;
				} else {
					// add origin file
					includedFiles.add(originFile);
				}
				
				IPath root = originFile.removeLastSegments(1);
				
				while (strFilePath.startsWith("..\\")) {
					strFilePath = strFilePath.substring(3);
					
					root = root.removeLastSegments(1);
				}
				
				IPath filePath = root.append(strFilePath);
				File file = filePath.toFile();
				
				// Check if path exists
				if (file.exists()) {
					if (!file.isFile()) {
						// must be a file
						reportError(start, length, "Reference is not a file");
					} else {
						try {
							ANTLRErrorListener errorListener = new BaseErrorListener() {
								@Override
								public void syntaxError(Recognizer<?, ?> recognizer,
										Object offendingSymbol, int line, int charPositionInline,
										String msg, RecognitionException e) {
									reportError(start, length, "Errors while parsing \""
											+ file.getPath() + "\": " + msg);
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
							walker.walk(new PreprocessorParseListener(editor, includedFiles) {
								@Override
								protected void reportError(int o, int l, String msg) {
									if (msg.equals(CYCLE_IN_HIERARCHY_MSG)) {
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
							reportError(start, length, "Failed at parsing referenced file");
							
							e.printStackTrace();
						}
					}
				} else {
					reportError(start, length, ctx.file.getText() + " does not exist");
				}
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
