package raven.sqdev.editors;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.Assert;

/**
 * A basic error listener implementation that can be added to an ANTLR parser
 * that will create error markers on the editor for every syntax error reported
 * by the parser
 * 
 * @author Raven
 *
 */
public class BasicErrorListener extends BaseErrorListener {
	protected class Error {
		private int offset;
		private int length;
		private String message;
		
		public Error(int offset, int length, String message) {
			this.offset = offset;
			this.length = length;
			this.message = message;
		}
		
		public int getOffset() {
			return offset;
		}
		
		public int getLength() {
			return length;
		}
		
		public String getMessage() {
			return message;
		}
	}
	
	/**
	 * The editor this listener resports to
	 */
	private BasicCodeEditor editor;
	
	/**
	 * Indicates whether errors should be suppressed and stored instead of being
	 * reported
	 */
	private boolean suppressErrors;
	
	private List<Error> suppressedErrors;
	
	
	/**
	 * Create an instance of this error listener
	 * 
	 * @param editor
	 *            The editor the syntax errors should be reported to
	 */
	public BasicErrorListener(BasicCodeEditor editor) {
		Assert.isNotNull(editor);
		this.editor = editor;
		
		suppressedErrors = new ArrayList<Error>();
	}
	
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
			int line, int charPositionInline, String msg,
			RecognitionException e) {
		if (line < 1 || !(offendingSymbol instanceof Token)) {
			return;
		}
		
		Token offendingToken = (Token) offendingSymbol;
		
		int length = (offendingToken.getType() == Token.EOF) ? 0
				: offendingToken.getText().length();
		
		reportError(new Error(offendingToken.getStartIndex(), length, msg));
	}
	
	/**
	 * Reports an error to the respective editor
	 * 
	 * @param error
	 *            The Error to report
	 */
	public void reportError(Error error) {
		if (suppressErrors) {
			synchronized (suppressedErrors) {
				suppressedErrors.add(error);
			}
		} else {
			editor.createMarker(IMarker.PROBLEM, error.getOffset(),
					error.getLength(), error.getMessage(),
					IMarker.SEVERITY_ERROR);
		}
	}
	
	/**
	 * Reports an error to the respective editor
	 * 
	 * @param offset
	 *            The offset of the error
	 * @param length
	 *            The length of the error
	 * @param msg
	 *            The error message
	 */
	public void reportError(int offset, int length, String msg) {
		reportError(new Error(offset, length, msg));
	}
	
	/**
	 * Specifies whether errors should be directly marked or rather be
	 * suppressed and stored internally
	 * 
	 * @param suppress
	 *            Whether to suppress erros
	 */
	public void suppressErrors(boolean suppress) {
		suppressErrors = suppress;
	}
	
	/**
	 * Reports all errors that have been suppressed to this point and clears the
	 * list of suppressed errors
	 */
	public void flushSuppressedErros() {
		synchronized (suppressedErrors) {
			for (Error currentError : suppressedErrors) {
				reportError(currentError);
			}
			
			suppressedErrors.clear();
		}
	}
	
	/**
	 * Clears the list of suppressed errors
	 */
	public void clearSuppressedErrors() {
		synchronized (suppressedErrors) {
			suppressedErrors.clear();
		}
	}
}
