package raven.sqdev.parser.misc;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.eclipse.core.resources.IMarker;

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
	 * The result of this parsing
	 */
	protected ParseResult result;

	/**
	 * Create an instance of this error listener
	 */
	public BasicErrorListener() {
		result = new ParseResult();
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
			int charPositionInline, String msg, RecognitionException e) {
		if (line < 1 || !(offendingSymbol instanceof Token)) {
			return;
		}

		Token offendingToken = (Token) offendingSymbol;

		int length = (offendingToken.getType() == Token.EOF) ? 0 : offendingToken.getText().length();

		reportError(offendingToken.getStartIndex(), length, msg);
	}

	/**
	 * This method is responsible for reporting the given information in form of a
	 * Marker.
	 * 
	 * @param type
	 *            type The marker's type
	 * @param offset
	 *            The marker's offset
	 * @param length
	 *            The marker's length
	 * @param message
	 *            The marker's message
	 * @param severity
	 *            The marker's severity
	 */
	protected void reportMarker(String type, int offset, int length, String message, int severity) {
		result.createMarker(type, offset, length, message, severity);
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
		reportMarker(IMarker.PROBLEM, offset, length, msg, IMarker.SEVERITY_ERROR);
	}

	/**
	 * Gets the parse result
	 */
	public ParseResult getParseResult() {
		return result;
	}
}
