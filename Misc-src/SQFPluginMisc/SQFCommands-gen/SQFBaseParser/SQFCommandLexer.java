// Generated from C:\Users\Robert Adam\Documents\Antlr\SQFCommand.g4 by ANTLR 4.1
package SQFBaseParser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SQFCommandLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__2=1, T__1=2, T__0=3, ID=4, WS=5;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'\n'", "'='", "'alternative:'", "ID", "WS"
	};
	public static final String[] ruleNames = {
		"T__2", "T__1", "T__0", "ID", "WS"
	};


	public SQFCommandLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SQFCommand.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 4: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\7<\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\7\5\"\n\5\f\5\16\5%\13\5\3\5"+
		"\3\5\3\5\3\5\7\5+\n\5\f\5\16\5.\13\5\3\5\3\5\6\5\62\n\5\r\5\16\5\63\3"+
		"\6\6\6\67\n\6\r\6\16\68\3\6\3\6\4#,\7\3\3\1\5\4\1\7\5\1\t\6\1\13\7\2\3"+
		"\2\5\6\2\62;C\\aac|\7\2..\61\61==]]__\5\2\13\13\17\17\"\"B\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\3\r\3\2\2\2\5\17\3\2"+
		"\2\2\7\21\3\2\2\2\t\61\3\2\2\2\13\66\3\2\2\2\r\16\7\f\2\2\16\4\3\2\2\2"+
		"\17\20\7?\2\2\20\6\3\2\2\2\21\22\7c\2\2\22\23\7n\2\2\23\24\7v\2\2\24\25"+
		"\7g\2\2\25\26\7t\2\2\26\27\7p\2\2\27\30\7c\2\2\30\31\7v\2\2\31\32\7k\2"+
		"\2\32\33\7x\2\2\33\34\7g\2\2\34\35\7<\2\2\35\b\3\2\2\2\36\62\t\2\2\2\37"+
		"#\7]\2\2 \"\13\2\2\2! \3\2\2\2\"%\3\2\2\2#$\3\2\2\2#!\3\2\2\2$&\3\2\2"+
		"\2%#\3\2\2\2&\62\7_\2\2\'\62\t\3\2\2(,\7*\2\2)+\13\2\2\2*)\3\2\2\2+.\3"+
		"\2\2\2,-\3\2\2\2,*\3\2\2\2-/\3\2\2\2.,\3\2\2\2/\60\7+\2\2\60\62\7A\2\2"+
		"\61\36\3\2\2\2\61\37\3\2\2\2\61\'\3\2\2\2\61(\3\2\2\2\62\63\3\2\2\2\63"+
		"\61\3\2\2\2\63\64\3\2\2\2\64\n\3\2\2\2\65\67\t\4\2\2\66\65\3\2\2\2\67"+
		"8\3\2\2\28\66\3\2\2\289\3\2\2\29:\3\2\2\2:;\b\6\2\2;\f\3\2\2\2\b\2#,\61"+
		"\638";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}