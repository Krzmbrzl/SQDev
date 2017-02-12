// Generated from Preprocessor.g4 by ANTLR 4.5.3

	package raven.sqdev.editors.parser.preprocessor;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PreprocessorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, WS=2, PREP_PREFIX=3, NL=4, INCLUDE=5, DEFINE=6, UNDEFINE=7, 
		IF=8, IFN=9, ELSE=10, ENDIF=11, LPAREN=12, RPAREN=13, COMMA=14, STRING=15, 
		ID=16, LETTER=17, INT=18, OTHER=19;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"COMMENT", "WS", "PREP_PREFIX", "NL", "INCLUDE", "DEFINE", "UNDEFINE", 
		"IF", "IFN", "ELSE", "ENDIF", "LPAREN", "RPAREN", "COMMA", "STRING", "ID", 
		"LETTER", "INT", "OTHER"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, "'#'", "'\n'", "'#include'", "'#define'", "'#undef'", 
		"'#ifdef'", "'#ifndef'", "'#else'", "'#endif'", "'('", "')'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMENT", "WS", "PREP_PREFIX", "NL", "INCLUDE", "DEFINE", "UNDEFINE", 
		"IF", "IFN", "ELSE", "ENDIF", "LPAREN", "RPAREN", "COMMA", "STRING", "ID", 
		"LETTER", "INT", "OTHER"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public PreprocessorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Preprocessor.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\25\u00ab\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\7\2.\n\2\f\2\16\2\61\13\2\3"+
		"\2\3\2\3\2\3\2\3\2\7\28\n\2\f\2\16\2;\13\2\3\2\3\2\5\2?\n\2\3\2\3\2\3"+
		"\3\3\3\3\3\6\3F\n\3\r\3\16\3G\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\7\20\u008c\n\20\f\20\16\20\u008f\13\20\3\20"+
		"\3\20\3\20\7\20\u0094\n\20\f\20\16\20\u0097\13\20\3\20\5\20\u009a\n\20"+
		"\3\21\3\21\3\21\6\21\u009f\n\21\r\21\16\21\u00a0\3\22\3\22\3\23\6\23\u00a6"+
		"\n\23\r\23\16\23\u00a7\3\24\3\24\6/9\u008d\u0095\2\25\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25\3\2\5\5\2\13\13\17\17\"\"\5\2C\\aac|\3\2\62;\u00b6\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\3>\3\2\2\2\5E\3\2\2\2\7K\3\2\2\2\tM\3\2\2\2\13O\3\2\2\2\r"+
		"X\3\2\2\2\17`\3\2\2\2\21g\3\2\2\2\23n\3\2\2\2\25v\3\2\2\2\27|\3\2\2\2"+
		"\31\u0083\3\2\2\2\33\u0085\3\2\2\2\35\u0087\3\2\2\2\37\u0099\3\2\2\2!"+
		"\u009e\3\2\2\2#\u00a2\3\2\2\2%\u00a5\3\2\2\2\'\u00a9\3\2\2\2)*\7\61\2"+
		"\2*+\7\61\2\2+/\3\2\2\2,.\13\2\2\2-,\3\2\2\2.\61\3\2\2\2/\60\3\2\2\2/"+
		"-\3\2\2\2\60\62\3\2\2\2\61/\3\2\2\2\62?\7\f\2\2\63\64\7\61\2\2\64\65\7"+
		",\2\2\659\3\2\2\2\668\13\2\2\2\67\66\3\2\2\28;\3\2\2\29:\3\2\2\29\67\3"+
		"\2\2\2:<\3\2\2\2;9\3\2\2\2<=\7,\2\2=?\7\61\2\2>)\3\2\2\2>\63\3\2\2\2?"+
		"@\3\2\2\2@A\b\2\2\2A\4\3\2\2\2BF\t\2\2\2CD\7^\2\2DF\7\f\2\2EB\3\2\2\2"+
		"EC\3\2\2\2FG\3\2\2\2GE\3\2\2\2GH\3\2\2\2HI\3\2\2\2IJ\b\3\2\2J\6\3\2\2"+
		"\2KL\7%\2\2L\b\3\2\2\2MN\7\f\2\2N\n\3\2\2\2OP\7%\2\2PQ\7k\2\2QR\7p\2\2"+
		"RS\7e\2\2ST\7n\2\2TU\7w\2\2UV\7f\2\2VW\7g\2\2W\f\3\2\2\2XY\7%\2\2YZ\7"+
		"f\2\2Z[\7g\2\2[\\\7h\2\2\\]\7k\2\2]^\7p\2\2^_\7g\2\2_\16\3\2\2\2`a\7%"+
		"\2\2ab\7w\2\2bc\7p\2\2cd\7f\2\2de\7g\2\2ef\7h\2\2f\20\3\2\2\2gh\7%\2\2"+
		"hi\7k\2\2ij\7h\2\2jk\7f\2\2kl\7g\2\2lm\7h\2\2m\22\3\2\2\2no\7%\2\2op\7"+
		"k\2\2pq\7h\2\2qr\7p\2\2rs\7f\2\2st\7g\2\2tu\7h\2\2u\24\3\2\2\2vw\7%\2"+
		"\2wx\7g\2\2xy\7n\2\2yz\7u\2\2z{\7g\2\2{\26\3\2\2\2|}\7%\2\2}~\7g\2\2~"+
		"\177\7p\2\2\177\u0080\7f\2\2\u0080\u0081\7k\2\2\u0081\u0082\7h\2\2\u0082"+
		"\30\3\2\2\2\u0083\u0084\7*\2\2\u0084\32\3\2\2\2\u0085\u0086\7+\2\2\u0086"+
		"\34\3\2\2\2\u0087\u0088\7.\2\2\u0088\36\3\2\2\2\u0089\u008d\7$\2\2\u008a"+
		"\u008c\13\2\2\2\u008b\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008e\3"+
		"\2\2\2\u008d\u008b\3\2\2\2\u008e\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090"+
		"\u009a\7$\2\2\u0091\u0095\7)\2\2\u0092\u0094\13\2\2\2\u0093\u0092\3\2"+
		"\2\2\u0094\u0097\3\2\2\2\u0095\u0096\3\2\2\2\u0095\u0093\3\2\2\2\u0096"+
		"\u0098\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u009a\7)\2\2\u0099\u0089\3\2"+
		"\2\2\u0099\u0091\3\2\2\2\u009a \3\2\2\2\u009b\u009f\5#\22\2\u009c\u009f"+
		"\5%\23\2\u009d\u009f\7a\2\2\u009e\u009b\3\2\2\2\u009e\u009c\3\2\2\2\u009e"+
		"\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2"+
		"\2\2\u00a1\"\3\2\2\2\u00a2\u00a3\t\3\2\2\u00a3$\3\2\2\2\u00a4\u00a6\t"+
		"\4\2\2\u00a5\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7"+
		"\u00a8\3\2\2\2\u00a8&\3\2\2\2\u00a9\u00aa\13\2\2\2\u00aa(\3\2\2\2\16\2"+
		"/9>EG\u008d\u0095\u0099\u009e\u00a0\u00a7\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}