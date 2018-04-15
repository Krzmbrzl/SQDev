package raven.sqdev.parser.preprocessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import raven.sqdev.constants.ProblemMessages;
import raven.sqdev.misc.Macro;
import raven.sqdev.misc.SQDevPreferenceUtil;
import raven.sqdev.parser.preprocessor.PreprocessorParser.DefineContext;
import raven.sqdev.parser.preprocessor.PreprocessorParser.ErrorContext;
import raven.sqdev.parser.preprocessor.PreprocessorParser.IncludeContext;

public class PreprocessorParseListener extends PreprocessorBaseListener {
	
	/**
	 * All available preprocessor instructions
	 */
	public static final String[] PREPROCESSOR_INSTRUCTIONS = new String[] {
			"define", "undef", "include", "ifdef", "ifndef", "else", "endif" };
	
	/**
	 * The error message displayed when a cycle in hierarchy is detected
	 */
	public static final String CYCLE_IN_HIERARCHY_MSG = "Cycle in hierarchy!";
	
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
	 * The path used as the origin for any relative path references in the
	 * parsed file
	 */
	private IPath origin;
	/**
	 * The parseResult of this parser
	 */
	private PreprocessorParseResult result;
	
	
	/**
	 * Creates a new instance of this listener
	 * 
	 * @param origin
	 *            The path used as the origin for any relative path references
	 *            in the parsed file
	 */
	public PreprocessorParseListener(IPath origin) {
		Assert.isNotNull(origin);
		
		this.origin = origin;
		
		includedFiles = new Stack<IPath>();
		
		// indicate that the values have not yet been set
		includedFileStart = -1;
		includedFileLength = -1;
		
		result = new PreprocessorParseResult();
		
		// add default macros
		result.addMacro(new Macro("__LINE__"));
		result.addMacro(new Macro("__FILE__"));
		
		result = new PreprocessorParseResult();
	}
	
	/**
	 * Creates a new instance of this listener
	 * 
	 * @param origin
	 *            The path used as the origin for any relative path references
	 *            in the parsed file
	 * @param files
	 *            The files that have already been visited (via the '#include'
	 *            instruction)
	 */
	private PreprocessorParseListener(IPath origin, Stack<IPath> files,
			int start, int length) {
		Assert.isNotNull(origin);
		
		this.origin = origin;
		
		includedFiles = files;
		
		includedFileStart = start;
		includedFileLength = length;
		
		result = new PreprocessorParseResult();
	}
	
	@Override
	public void exitDefine(DefineContext ctx) {
		String name = ctx.name.getText();
		
		// add defined macro
		result.addMacro(new Macro(name));
	}
	
	@Override
	public void exitInclude(IncludeContext ctx) {
		if (includedFileStart == -1 && includedFileLength == -1) {
			// only use the values of the topmost file (The one the user is
			// looking at)
			includedFileStart = ctx.file.getStartIndex();
			includedFileLength = ctx.file.getStopIndex()
					- ctx.file.getStartIndex() + 1;
		}
		
		// Get latest origin file
		IPath originFile;
		
		if (includedFiles.size() == 0) {
			originFile = origin;
			
			// add the original file to the list of "included" files
			includedFiles.push(originFile);
			
			// use the new start + length values for new include instruction
			includedFileStart = ctx.file.getStartIndex();
			includedFileLength = ctx.file.getStopIndex()
					- ctx.file.getStartIndex() + 1;
		} else {
			originFile = includedFiles.peek();
		}
		
		String strFilePath = ctx.file.getText().substring(1,
				ctx.file.getText().length() - 1);
		IPath root;
		
		if (strFilePath.contains("/")) {
			// backslashes have to be used
			reportError(ctx.file.getStartIndex() + strFilePath.indexOf("/") + 1,
					1, ProblemMessages.backslashHasToBeUsed());
			
			// Abort because of syntax error
			return;
		}
		
		// In unix systems the backslash has to be replaced
		strFilePath = strFilePath.replace("\\", File.separator);
		
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
				reportError(includedFileStart, includedFileLength,
						ProblemMessages.referenceNotAFile());
			} else {
				if (includedFiles.contains(filePath)) {
					// report cycle in hierarchy
					reportError(includedFileStart, includedFileLength,
							ProblemMessages.cycleInHierarchy());
					
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
								Object offendingSymbol, int line,
								int charPositionInline, String msg,
								RecognitionException e) {
							reportError(includedFileStart, includedFileLength,
									"Errors while parsing \"" + file.getPath()
											+ "\": " + msg);
						}
					};
					
					ANTLRInputStream in = new ANTLRInputStream(
							new FileInputStream(file));
					
					PreprocessorLexer lexer = new PreprocessorLexer(in);
					lexer.removeErrorListeners();
					lexer.addErrorListener(errorListener);
					
					CommonTokenStream tokens = new CommonTokenStream(lexer);
					
					PreprocessorParser parser = new PreprocessorParser(tokens);
					parser.removeErrorListeners();
					parser.addErrorListener(errorListener);
					
					ParseTreeWalker walker = new ParseTreeWalker();
					
					PreprocessorParseListener innerListener = new PreprocessorParseListener(
							origin, includedFiles, includedFileStart,
							includedFileLength) {
						@Override
						protected void reportError(int start, int length,
								String msg) {
							
							if (msg.equals(CYCLE_IN_HIERARCHY_MSG)
									&& includedFiles.size() <= 2) {
								// Don't blame sub-file for cycle in
								// hierarchy
								super.reportError(start, length, msg);
							} else {
								super.reportError(start, length,
										"Errors while parsing \""
												+ file.getPath() + "\": "
												+ msg);
							}
						}
					};
					
					walker.walk(innerListener, parser.start());
					
					result.mergeWith(innerListener.getParseResult());
					
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
	
	@Override
	public void exitError(ErrorContext ctx) {
		// error alt has matched -> report error
		String instruction = ctx.instruction.getText();
		
		if (ctx.instruction.getType() == 15) {
			// error node
			return;
		}
		
		for (String currentInstruction : PREPROCESSOR_INSTRUCTIONS) {
			if (instruction.equals(currentInstruction)) {
				// There must WS in between or it wouldn't have matched "Error"
				int start = ctx.getStart().getStopIndex();
				int length = ctx.instruction.getStartIndex() - start;
				
				reportError(start, length,
						ProblemMessages.noWhitespaceAllowed());
				
				return;
			} else {
				if (instruction.toLowerCase()
						.equals(currentInstruction.toLowerCase())) {
					// written in wrong case
					reportError(ctx.instruction.getStartIndex(),
							ctx.instruction.getStopIndex()
									- ctx.instruction.getStartIndex() + 1,
							ProblemMessages
									.isCaseSensitive(currentInstruction));
					
					return;
				}
			}
		}
		
		// must be an unknown operator
		reportError(
				ctx.instruction.getStartIndex(), ctx.instruction.getStopIndex()
						- ctx.instruction.getStartIndex() + 1,
				ProblemMessages.unknownOperator(instruction));
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
		result.addMarker(IMarker.PROBLEM, offset, length, msg,
				IMarker.SEVERITY_ERROR);
	}
	
	/**
	 * Gets the list of macros that have been defined during preprocessing
	 */
	public Map<String, Macro> getDefinedMacros() {
		return result.getMacros();
	}
	
	/**
	 * Gest ts the parse result of this listener
	 */
	public PreprocessorParseResult getParseResult() {
		return result;
	}
}
