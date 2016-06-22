package raven.sqdev.editors.stringtableParsing;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class StringTableLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INFO_TAG=1, OPEN_CLOSE=2, OPEN=3, CLOSE=4, EQUALS=5, PROJECT=6, NAME=7, 
		PKG=8, CONTAINER=9, KEY=10, IDENTIFIER=11, STRING=12, ID=13, WS=14, ANY=15;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"INFO_TAG", "'</'", "'<'", "'>'", "'='", "PROJECT", "NAME", "PKG", "CONTAINER", 
		"KEY", "IDENTIFIER", "STRING", "ID", "WS", "ANY"
	};
	public static final String[] ruleNames = {
		"INFO_TAG", "OPEN_CLOSE", "OPEN", "CLOSE", "EQUALS", "PROJECT", "NAME", 
		"PKG", "CONTAINER", "KEY", "IDENTIFIER", "STRING", "ID", "WS", "ANY", 
		"A", "C", "D", "E", "G", "I", "J", "K", "M", "N", "O", "P", "R", "T", 
		"Y"
	};


	public StringTableLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "StringTable.g4"; }

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
		case 13: WS_action(_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: _channel = HIDDEN;  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\21\u00af\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2"+
		"\3\2\3\2\3\2\7\2D\n\2\f\2\16\2G\13\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3"+
		"\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\7\r}\n\r\f\r\16\r\u0080\13\r\3"+
		"\r\3\r\3\16\6\16\u0085\n\16\r\16\16\16\u0086\3\17\6\17\u008a\n\17\r\17"+
		"\16\17\u008b\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3"+
		"\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3"+
		"\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\4E~ \3\3\1\5\4\1\7\5\1\t\6"+
		"\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35"+
		"\20\2\37\21\1!\2\1#\2\1%\2\1\'\2\1)\2\1+\2\1-\2\1/\2\1\61\2\1\63\2\1\65"+
		"\2\1\67\2\19\2\1;\2\1=\2\1\3\2\23\5\2\62;C\\c|\5\2\13\f\17\17\"\"\4\2"+
		"CCcc\4\2EEee\4\2FFff\4\2GGgg\4\2IIii\4\2KKkk\4\2LLll\4\2MMmm\4\2OOoo\4"+
		"\2PPpp\4\2QQqq\4\2RRrr\4\2TTtt\4\2VVvv\4\2[[{{\u00a3\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\3?\3\2\2\2\5K\3\2\2\2\7N\3\2\2\2\tP\3"+
		"\2\2\2\13R\3\2\2\2\rT\3\2\2\2\17\\\3\2\2\2\21a\3\2\2\2\23i\3\2\2\2\25"+
		"s\3\2\2\2\27w\3\2\2\2\31z\3\2\2\2\33\u0084\3\2\2\2\35\u0089\3\2\2\2\37"+
		"\u008f\3\2\2\2!\u0091\3\2\2\2#\u0093\3\2\2\2%\u0095\3\2\2\2\'\u0097\3"+
		"\2\2\2)\u0099\3\2\2\2+\u009b\3\2\2\2-\u009d\3\2\2\2/\u009f\3\2\2\2\61"+
		"\u00a1\3\2\2\2\63\u00a3\3\2\2\2\65\u00a5\3\2\2\2\67\u00a7\3\2\2\29\u00a9"+
		"\3\2\2\2;\u00ab\3\2\2\2=\u00ad\3\2\2\2?@\7>\2\2@A\7A\2\2AE\3\2\2\2BD\13"+
		"\2\2\2CB\3\2\2\2DG\3\2\2\2EF\3\2\2\2EC\3\2\2\2FH\3\2\2\2GE\3\2\2\2HI\7"+
		"A\2\2IJ\7@\2\2J\4\3\2\2\2KL\7>\2\2LM\7\61\2\2M\6\3\2\2\2NO\7>\2\2O\b\3"+
		"\2\2\2PQ\7@\2\2Q\n\3\2\2\2RS\7?\2\2S\f\3\2\2\2TU\5\67\34\2UV\59\35\2V"+
		"W\5\65\33\2WX\5-\27\2XY\5\'\24\2YZ\5#\22\2Z[\5;\36\2[\16\3\2\2\2\\]\5"+
		"\63\32\2]^\5!\21\2^_\5\61\31\2_`\5\'\24\2`\20\3\2\2\2ab\5\67\34\2bc\5"+
		"!\21\2cd\5#\22\2de\5/\30\2ef\5!\21\2fg\5)\25\2gh\5\'\24\2h\22\3\2\2\2"+
		"ij\5#\22\2jk\5\65\33\2kl\5\63\32\2lm\5;\36\2mn\5!\21\2no\5+\26\2op\5\63"+
		"\32\2pq\5\'\24\2qr\59\35\2r\24\3\2\2\2st\5/\30\2tu\5\'\24\2uv\5=\37\2"+
		"v\26\3\2\2\2wx\5+\26\2xy\5%\23\2y\30\3\2\2\2z~\7$\2\2{}\13\2\2\2|{\3\2"+
		"\2\2}\u0080\3\2\2\2~\177\3\2\2\2~|\3\2\2\2\177\u0081\3\2\2\2\u0080~\3"+
		"\2\2\2\u0081\u0082\7$\2\2\u0082\32\3\2\2\2\u0083\u0085\t\2\2\2\u0084\u0083"+
		"\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\34\3\2\2\2\u0088\u008a\t\3\2\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2"+
		"\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e"+
		"\b\17\2\2\u008e\36\3\2\2\2\u008f\u0090\13\2\2\2\u0090 \3\2\2\2\u0091\u0092"+
		"\t\4\2\2\u0092\"\3\2\2\2\u0093\u0094\t\5\2\2\u0094$\3\2\2\2\u0095\u0096"+
		"\t\6\2\2\u0096&\3\2\2\2\u0097\u0098\t\7\2\2\u0098(\3\2\2\2\u0099\u009a"+
		"\t\b\2\2\u009a*\3\2\2\2\u009b\u009c\t\t\2\2\u009c,\3\2\2\2\u009d\u009e"+
		"\t\n\2\2\u009e.\3\2\2\2\u009f\u00a0\t\13\2\2\u00a0\60\3\2\2\2\u00a1\u00a2"+
		"\t\f\2\2\u00a2\62\3\2\2\2\u00a3\u00a4\t\r\2\2\u00a4\64\3\2\2\2\u00a5\u00a6"+
		"\t\16\2\2\u00a6\66\3\2\2\2\u00a7\u00a8\t\17\2\2\u00a88\3\2\2\2\u00a9\u00aa"+
		"\t\20\2\2\u00aa:\3\2\2\2\u00ab\u00ac\t\21\2\2\u00ac<\3\2\2\2\u00ad\u00ae"+
		"\t\22\2\2\u00ae>\3\2\2\2\7\2E~\u0086\u008b";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}