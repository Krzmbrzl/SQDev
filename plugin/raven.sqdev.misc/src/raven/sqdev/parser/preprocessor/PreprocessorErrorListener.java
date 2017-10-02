package raven.sqdev.parser.preprocessor;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

import raven.sqdev.parser.misc.BasicErrorListener;

public class PreprocessorErrorListener extends BasicErrorListener {
	/**
	 * The offset from where the parsing will be started
	 */
	private int initialOffset;


	/**
	 * Creates a new instance of this class
	 * 
	 * @param initialOffset
	 *            The offset from where the parsing will be started
	 */
	public PreprocessorErrorListener(int initialOffset) {
		super();

		this.initialOffset = initialOffset;
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
			int charPositionInline, String msg, RecognitionException e) {
		if (line < 1 || !(offendingSymbol instanceof Token)) {
			return;
		}

		Token offendingToken = (Token) offendingSymbol;

		int length = (offendingToken.getType() == Token.EOF) ? 0 : offendingToken.getText().length();

		super.reportError(offendingToken.getStartIndex() + initialOffset, length, msg);
	}
}
