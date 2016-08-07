package raven.sqdev.editors;

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
	/**
	 * The editor this listener resports to
	 */
	private BasicCodeEditor editor;
	
	/**
	 * Create an instance of this error listener
	 * 
	 * @param editor
	 *            The editor the syntax errors should be reported to
	 */
	public BasicErrorListener(BasicCodeEditor editor) {
		Assert.isNotNull(editor);
		this.editor = editor;
	}
	
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
			int charPositionInline, String msg, RecognitionException e) {
		if (line < 1 || !(offendingSymbol instanceof Token)) {
			return;
		}
		
		Token offendingToken = (Token) offendingSymbol;
		
		int length = (offendingToken.getType() == Token.EOF) ? 0
				: offendingToken.getText().length();
		
		reportError(offendingToken.getStartIndex(), length, msg);
	}
	
	/**
	 * Reports an error to the respective editor
	 * 
	 * @param offset
	 *            The offset of the error start
	 * @param length
	 *            The length of the error
	 * @param msg
	 *            The error message
	 */
	public void reportError(int offset, int length, String msg) {
		editor.createMarker(IMarker.PROBLEM, offset, length, msg, IMarker.SEVERITY_ERROR);
	}
}
