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
		IF=1, IFN=2, ELSE=3, ENDIF=4, DEFINE=5, UNDEFINE=6, INCLUDE=7, UNKNOWN=8, 
		NUMBER=9, STRING=10, ID=11, ESC_LINEBREAK=12, LINEBREAK=13, WS=14, COMMENT=15, 
		ANY=16;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"IF", "IFN", "ELSE", "ENDIF", "DEFINE", "UNDEFINE", "INCLUDE", "UNKNOWN", 
		"NUMBER", "STRING", "ID", "ESC_LINEBREAK", "LINEBREAK", "WS", "COMMENT", 
		"INT", "FLOAT", "LETTER", "ANY"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'#ifdef'", "'#ifndef'", "'#else'", "'#endif'", "'#define'", "'#undef'", 
		"'#include'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "IF", "IFN", "ELSE", "ENDIF", "DEFINE", "UNDEFINE", "INCLUDE", "UNKNOWN", 
		"NUMBER", "STRING", "ID", "ESC_LINEBREAK", "LINEBREAK", "WS", "COMMENT", 
		"ANY"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\22\u00ca\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\5\t`\n\t\3\t\3\t\3\n\3\n\5\nf\n\n\3\13"+
		"\3\13\3\13\3\13\7\13l\n\13\f\13\16\13o\13\13\3\13\3\13\3\13\3\13\3\13"+
		"\7\13v\n\13\f\13\16\13y\13\13\3\13\5\13|\n\13\3\f\3\f\3\f\6\f\u0081\n"+
		"\f\r\f\16\f\u0082\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u008c\n\r\3\16\3\16"+
		"\3\16\6\16\u0091\n\16\r\16\16\16\u0092\3\17\6\17\u0096\n\17\r\17\16\17"+
		"\u0097\3\17\3\17\3\20\3\20\3\20\3\20\7\20\u00a0\n\20\f\20\16\20\u00a3"+
		"\13\20\3\20\3\20\3\20\3\20\3\20\7\20\u00aa\n\20\f\20\16\20\u00ad\13\20"+
		"\3\20\3\20\5\20\u00b1\n\20\3\20\3\20\3\21\6\21\u00b6\n\21\r\21\16\21\u00b7"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00c0\n\22\3\23\6\23\u00c3\n\23\r"+
		"\23\16\23\u00c4\3\24\3\24\3\24\3\24\4\u00a1\u00ab\2\25\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\2#\2%\2\'"+
		"\22\3\2\7\3\2$$\3\2))\4\2\13\13\"\"\3\2\62;\4\2C\\c|\u00da\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2\'\3\2\2\2\3)\3\2\2\2\5\60\3"+
		"\2\2\2\78\3\2\2\2\t>\3\2\2\2\13E\3\2\2\2\rM\3\2\2\2\17T\3\2\2\2\21]\3"+
		"\2\2\2\23e\3\2\2\2\25{\3\2\2\2\27\u0080\3\2\2\2\31\u008b\3\2\2\2\33\u0090"+
		"\3\2\2\2\35\u0095\3\2\2\2\37\u00b0\3\2\2\2!\u00b5\3\2\2\2#\u00bf\3\2\2"+
		"\2%\u00c2\3\2\2\2\'\u00c6\3\2\2\2)*\7%\2\2*+\7k\2\2+,\7h\2\2,-\7f\2\2"+
		"-.\7g\2\2./\7h\2\2/\4\3\2\2\2\60\61\7%\2\2\61\62\7k\2\2\62\63\7h\2\2\63"+
		"\64\7p\2\2\64\65\7f\2\2\65\66\7g\2\2\66\67\7h\2\2\67\6\3\2\2\289\7%\2"+
		"\29:\7g\2\2:;\7n\2\2;<\7u\2\2<=\7g\2\2=\b\3\2\2\2>?\7%\2\2?@\7g\2\2@A"+
		"\7p\2\2AB\7f\2\2BC\7k\2\2CD\7h\2\2D\n\3\2\2\2EF\7%\2\2FG\7f\2\2GH\7g\2"+
		"\2HI\7h\2\2IJ\7k\2\2JK\7p\2\2KL\7g\2\2L\f\3\2\2\2MN\7%\2\2NO\7w\2\2OP"+
		"\7p\2\2PQ\7f\2\2QR\7g\2\2RS\7h\2\2S\16\3\2\2\2TU\7%\2\2UV\7k\2\2VW\7p"+
		"\2\2WX\7e\2\2XY\7n\2\2YZ\7w\2\2Z[\7f\2\2[\\\7g\2\2\\\20\3\2\2\2]_\7%\2"+
		"\2^`\5\35\17\2_^\3\2\2\2_`\3\2\2\2`a\3\2\2\2ab\5\27\f\2b\22\3\2\2\2cf"+
		"\5!\21\2df\5#\22\2ec\3\2\2\2ed\3\2\2\2f\24\3\2\2\2gm\7$\2\2hi\7$\2\2i"+
		"l\7$\2\2jl\n\2\2\2kh\3\2\2\2kj\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2n"+
		"p\3\2\2\2om\3\2\2\2p|\7$\2\2qw\7)\2\2rs\7)\2\2sv\7)\2\2tv\n\3\2\2ur\3"+
		"\2\2\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2xz\3\2\2\2yw\3\2\2\2z|\7"+
		")\2\2{g\3\2\2\2{q\3\2\2\2|\26\3\2\2\2}\u0081\5%\23\2~\u0081\5!\21\2\177"+
		"\u0081\7a\2\2\u0080}\3\2\2\2\u0080~\3\2\2\2\u0080\177\3\2\2\2\u0081\u0082"+
		"\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\30\3\2\2\2\u0084"+
		"\u0085\7\"\2\2\u0085\u0086\7^\2\2\u0086\u008c\7\f\2\2\u0087\u0088\7\""+
		"\2\2\u0088\u0089\7^\2\2\u0089\u008a\7\17\2\2\u008a\u008c\7\f\2\2\u008b"+
		"\u0084\3\2\2\2\u008b\u0087\3\2\2\2\u008c\32\3\2\2\2\u008d\u0091\7\f\2"+
		"\2\u008e\u008f\7\17\2\2\u008f\u0091\7\f\2\2\u0090\u008d\3\2\2\2\u0090"+
		"\u008e\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2"+
		"\2\2\u0093\34\3\2\2\2\u0094\u0096\t\4\2\2\u0095\u0094\3\2\2\2\u0096\u0097"+
		"\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0099\3\2\2\2\u0099"+
		"\u009a\b\17\2\2\u009a\36\3\2\2\2\u009b\u009c\7\61\2\2\u009c\u009d\7\61"+
		"\2\2\u009d\u00a1\3\2\2\2\u009e\u00a0\13\2\2\2\u009f\u009e\3\2\2\2\u00a0"+
		"\u00a3\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a4\3\2"+
		"\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00b1\5\33\16\2\u00a5\u00a6\7\61\2\2\u00a6"+
		"\u00a7\7,\2\2\u00a7\u00ab\3\2\2\2\u00a8\u00aa\13\2\2\2\u00a9\u00a8\3\2"+
		"\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac"+
		"\u00ae\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00af\7,\2\2\u00af\u00b1\7\61"+
		"\2\2\u00b0\u009b\3\2\2\2\u00b0\u00a5\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2"+
		"\u00b3\b\20\2\2\u00b3 \3\2\2\2\u00b4\u00b6\t\5\2\2\u00b5\u00b4\3\2\2\2"+
		"\u00b6\u00b7\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\"\3"+
		"\2\2\2\u00b9\u00ba\5!\21\2\u00ba\u00bb\7\60\2\2\u00bb\u00bc\5!\21\2\u00bc"+
		"\u00c0\3\2\2\2\u00bd\u00be\7\60\2\2\u00be\u00c0\5!\21\2\u00bf\u00b9\3"+
		"\2\2\2\u00bf\u00bd\3\2\2\2\u00c0$\3\2\2\2\u00c1\u00c3\t\6\2\2\u00c2\u00c1"+
		"\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5"+
		"&\3\2\2\2\u00c6\u00c7\13\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\b\24\2"+
		"\2\u00c9(\3\2\2\2\26\2_ekmuw{\u0080\u0082\u008b\u0090\u0092\u0097\u00a1"+
		"\u00ab\u00b0\u00b7\u00bf\u00c4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}