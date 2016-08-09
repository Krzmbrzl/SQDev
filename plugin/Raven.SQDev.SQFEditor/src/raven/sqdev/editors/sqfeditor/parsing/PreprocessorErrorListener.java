package raven.sqdev.editors.sqfeditor.parsing;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;

import raven.sqdev.editors.BasicCodeEditor;
import raven.sqdev.editors.BasicErrorListener;

public class PreprocessorErrorListener extends BasicErrorListener {
	
	private int initialOffset;
	
	public PreprocessorErrorListener(BasicCodeEditor editor, int initialOffset) {
		super(editor);
		
		this.initialOffset = initialOffset;
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
		
		super.reportError(offendingToken.getStartIndex() + initialOffset, length, msg);
	}
	
}
