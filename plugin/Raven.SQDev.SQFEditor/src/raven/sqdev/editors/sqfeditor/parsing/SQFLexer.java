// Generated from SQF.g4 by ANTLR 4.5.3
package raven.sqdev.editors.sqfeditor.parsing;

	import java.util.ArrayList;
	import java.util.List;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SQFLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMA=1, SEMICOLON=2, EQUALS=3, ELSE=4, OR=5, AND=6, NOT=7, EXP=8, COMPARATOR=9, 
		CONFIG_OPERATOR=10, ADDITION_OPERATOR=11, MIN_MAX_OPERATOR=12, MULTIPLICATION_OPERATOR=13, 
		MODULO_OPERATOR=14, OPENING_ROUND_BRACKET=15, CLOSING_ROUND_BRACKET=16, 
		OPENING_SQUARE_BRACKET=17, CLOSING_SQUARE_BRACKET=18, OPENING_CURLY_BRACKET=19, 
		CLOSING_CURLY_BRACKET=20, NUMBER=21, STRING=22, ID=23, PREPRO=24, IFDEF=25, 
		IFNDEF=26, PRE_ELSE=27, ENDIF=28, DOC=29, COMMENT=30, LINEBREAK=31, WS=32, 
		ANY=33;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"COMMA", "SEMICOLON", "EQUALS", "ELSE", "OR", "AND", "NOT", "EXP", "COMPARATOR", 
		"CONFIG_OPERATOR", "ADDITION_OPERATOR", "MIN_MAX_OPERATOR", "MULTIPLICATION_OPERATOR", 
		"MODULO_OPERATOR", "OPENING_ROUND_BRACKET", "CLOSING_ROUND_BRACKET", "OPENING_SQUARE_BRACKET", 
		"CLOSING_SQUARE_BRACKET", "OPENING_CURLY_BRACKET", "CLOSING_CURLY_BRACKET", 
		"NUMBER", "STRING", "ID", "PREPRO", "IFDEF", "IFNDEF", "PRE_ELSE", "ENDIF", 
		"DOC", "COMMENT", "LINEBREAK", "WS", "INT", "FLOAT", "LETTER", "A", "D", 
		"E", "F", "I", "L", "M", "N", "O", "R", "S", "T", "X", "ANY"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "';'", "'='", null, null, null, null, "'^'", null, "'>>'", 
		null, null, null, null, "'('", "')'", "'['", "']'", "'{'", "'}'", null, 
		null, null, null, "'#ifdef'", "'#ifndef'", "'#else'", "'#endif'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMA", "SEMICOLON", "EQUALS", "ELSE", "OR", "AND", "NOT", "EXP", 
		"COMPARATOR", "CONFIG_OPERATOR", "ADDITION_OPERATOR", "MIN_MAX_OPERATOR", 
		"MULTIPLICATION_OPERATOR", "MODULO_OPERATOR", "OPENING_ROUND_BRACKET", 
		"CLOSING_ROUND_BRACKET", "OPENING_SQUARE_BRACKET", "CLOSING_SQUARE_BRACKET", 
		"OPENING_CURLY_BRACKET", "CLOSING_CURLY_BRACKET", "NUMBER", "STRING", 
		"ID", "PREPRO", "IFDEF", "IFNDEF", "PRE_ELSE", "ENDIF", "DOC", "COMMENT", 
		"LINEBREAK", "WS", "ANY"
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


		/**
		 * The id for the hidden whitespace token channel
		 */
		public static final int WHITESPACE_CHANNEL = 1;
		/**
		 * The id for the hidden comment token channel
		 */
		public static final int COMMENT_CHANNEL = 2;
		/**
		 * The id for the hidden documentation token channel
		 */
		public static final int DOCUMENTATION_CHANNEL = 3;
		
		/**
	         * The list of binary operators (has to be in lowercase!)
	         */
		private List<String> binaryOperators;
	        /**
	         * The list of defined macros (case-sensitive)
	         */
	        private List<String> macroExpressions = new ArrayList<String>();
		
		/**
		 * Creates an instance of this lexer. Afterwards <code>setParser</code> <b>has to be called</b>
		 * @param in The inputStream to work on
		 * @param binaryOperators A list of binary operators the lexer should match. <b>These operators have
		 *  to be all lowercase in order to get matched!</b>
	         * @param macros A list of defined macro-names (<b>all lowercase!</b>)
		 */
		public SQFLexer(CharStream in, List<String> binaryOperators, List<String> macros) {
			this(in);
			this.binaryOperators = binaryOperators;
	                this.macroExpressions = macros;
		}


	public SQFLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SQF.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 22:
			ID_action((RuleContext)_localctx, actionIndex);
			break;
		case 28:
			DOC_action((RuleContext)_localctx, actionIndex);
			break;
		case 29:
			COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 30:
			LINEBREAK_action((RuleContext)_localctx, actionIndex);
			break;
		case 31:
			WS_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void ID_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

			      	if(binaryOperators.contains(getText().toLowerCase())) {
			      		setType(SQFParser.BINARY_OPERATOR);
			      	} else {
			                if(macroExpressions.contains(getText())) {
			                    setType(SQFParser.MACRO_EXPRESSION);
			                }
			          }
			      
			break;
		}
	}
	private void DOC_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			_channel = DOCUMENTATION_CHANNEL;
			break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			_channel = COMMENT_CHANNEL;
			break;
		}
	}
	private void LINEBREAK_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			_channel = WHITESPACE_CHANNEL;
			break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			_channel = WHITESPACE_CHANNEL;
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2#\u018a\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\3\2\3\2\3\3\3\3\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\5\6v\n\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\5\7~\n\7\3\b\3\b\3\b\3\b\3\b\5\b\u0085\n\b\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0092\n\n\3\13\3\13\3\13\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00a1\n\r\3\16\3\16\3\17\3\17\3\17\3"+
		"\17\3\17\5\17\u00aa\n\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24"+
		"\3\24\3\25\3\25\3\26\3\26\5\26\u00ba\n\26\3\27\3\27\3\27\3\27\7\27\u00c0"+
		"\n\27\f\27\16\27\u00c3\13\27\3\27\3\27\3\27\3\27\3\27\7\27\u00ca\n\27"+
		"\f\27\16\27\u00cd\13\27\3\27\5\27\u00d0\n\27\3\30\3\30\3\30\6\30\u00d5"+
		"\n\30\r\30\16\30\u00d6\3\30\3\30\3\31\3\31\5\31\u00dd\n\31\3\31\7\31\u00e0"+
		"\n\31\f\31\16\31\u00e3\13\31\3\31\5\31\u00e6\n\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\7\31\u00f1\n\31\f\31\16\31\u00f4\13\31\5\31"+
		"\u00f6\n\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\7\31\u0100\n\31\f"+
		"\31\16\31\u0103\13\31\5\31\u0105\n\31\5\31\u0107\n\31\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3"+
		"\36\3\36\7\36\u012a\n\36\f\36\16\36\u012d\13\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\37\3\37\3\37\3\37\7\37\u0138\n\37\f\37\16\37\u013b\13\37\3\37\3"+
		"\37\3\37\3\37\3\37\7\37\u0142\n\37\f\37\16\37\u0145\13\37\3\37\3\37\5"+
		"\37\u0149\n\37\3\37\3\37\3 \3 \3 \6 \u0150\n \r \16 \u0151\3 \3 \3!\6"+
		"!\u0157\n!\r!\16!\u0158\3!\3!\3\"\6\"\u015e\n\"\r\"\16\"\u015f\3#\3#\3"+
		"#\3#\3#\3#\5#\u0168\n#\3$\6$\u016b\n$\r$\16$\u016c\3%\3%\3&\3&\3\'\3\'"+
		"\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3"+
		"\62\3\62\6\u00e1\u012b\u0139\u0143\2\63\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27"+
		"-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C\2E\2G\2I\2K\2M\2O\2Q"+
		"\2S\2U\2W\2Y\2[\2]\2_\2a\2c#\3\2\30\4\2>>@@\4\2--//\4\2,,\61\61\3\2$$"+
		"\3\2))\4\2\f\f\17\17\4\2\13\13\"\"\3\2\62;\4\2C\\c|\4\2CCcc\4\2FFff\4"+
		"\2GGgg\4\2HHhh\4\2KKkk\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2TTtt\4\2UUu"+
		"u\4\2VVvv\4\2ZZzz\u019f\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2c\3\2\2"+
		"\2\3e\3\2\2\2\5g\3\2\2\2\7i\3\2\2\2\tk\3\2\2\2\13u\3\2\2\2\r}\3\2\2\2"+
		"\17\u0084\3\2\2\2\21\u0086\3\2\2\2\23\u0091\3\2\2\2\25\u0093\3\2\2\2\27"+
		"\u0096\3\2\2\2\31\u00a0\3\2\2\2\33\u00a2\3\2\2\2\35\u00a9\3\2\2\2\37\u00ab"+
		"\3\2\2\2!\u00ad\3\2\2\2#\u00af\3\2\2\2%\u00b1\3\2\2\2\'\u00b3\3\2\2\2"+
		")\u00b5\3\2\2\2+\u00b9\3\2\2\2-\u00cf\3\2\2\2/\u00d4\3\2\2\2\61\u0106"+
		"\3\2\2\2\63\u0108\3\2\2\2\65\u010f\3\2\2\2\67\u0117\3\2\2\29\u011d\3\2"+
		"\2\2;\u0124\3\2\2\2=\u0148\3\2\2\2?\u014f\3\2\2\2A\u0156\3\2\2\2C\u015d"+
		"\3\2\2\2E\u0167\3\2\2\2G\u016a\3\2\2\2I\u016e\3\2\2\2K\u0170\3\2\2\2M"+
		"\u0172\3\2\2\2O\u0174\3\2\2\2Q\u0176\3\2\2\2S\u0178\3\2\2\2U\u017a\3\2"+
		"\2\2W\u017c\3\2\2\2Y\u017e\3\2\2\2[\u0180\3\2\2\2]\u0182\3\2\2\2_\u0184"+
		"\3\2\2\2a\u0186\3\2\2\2c\u0188\3\2\2\2ef\7.\2\2f\4\3\2\2\2gh\7=\2\2h\6"+
		"\3\2\2\2ij\7?\2\2j\b\3\2\2\2kl\5M\'\2lm\5S*\2mn\5]/\2no\5M\'\2o\n\3\2"+
		"\2\2pq\7~\2\2qv\7~\2\2rs\5Y-\2st\5[.\2tv\3\2\2\2up\3\2\2\2ur\3\2\2\2v"+
		"\f\3\2\2\2wx\7(\2\2x~\7(\2\2yz\5I%\2z{\5W,\2{|\5K&\2|~\3\2\2\2}w\3\2\2"+
		"\2}y\3\2\2\2~\16\3\2\2\2\177\u0085\7#\2\2\u0080\u0081\5W,\2\u0081\u0082"+
		"\5Y-\2\u0082\u0083\5_\60\2\u0083\u0085\3\2\2\2\u0084\177\3\2\2\2\u0084"+
		"\u0080\3\2\2\2\u0085\20\3\2\2\2\u0086\u0087\7`\2\2\u0087\22\3\2\2\2\u0088"+
		"\u0092\t\2\2\2\u0089\u008a\7>\2\2\u008a\u0092\7?\2\2\u008b\u008c\7@\2"+
		"\2\u008c\u0092\7?\2\2\u008d\u008e\7?\2\2\u008e\u0092\7?\2\2\u008f\u0090"+
		"\7#\2\2\u0090\u0092\7?\2\2\u0091\u0088\3\2\2\2\u0091\u0089\3\2\2\2\u0091"+
		"\u008b\3\2\2\2\u0091\u008d\3\2\2\2\u0091\u008f\3\2\2\2\u0092\24\3\2\2"+
		"\2\u0093\u0094\7@\2\2\u0094\u0095\7@\2\2\u0095\26\3\2\2\2\u0096\u0097"+
		"\t\3\2\2\u0097\30\3\2\2\2\u0098\u0099\5U+\2\u0099\u009a\5Q)\2\u009a\u009b"+
		"\5W,\2\u009b\u00a1\3\2\2\2\u009c\u009d\5U+\2\u009d\u009e\5I%\2\u009e\u009f"+
		"\5a\61\2\u009f\u00a1\3\2\2\2\u00a0\u0098\3\2\2\2\u00a0\u009c\3\2\2\2\u00a1"+
		"\32\3\2\2\2\u00a2\u00a3\t\4\2\2\u00a3\34\3\2\2\2\u00a4\u00aa\7\'\2\2\u00a5"+
		"\u00a6\5U+\2\u00a6\u00a7\5Y-\2\u00a7\u00a8\5K&\2\u00a8\u00aa\3\2\2\2\u00a9"+
		"\u00a4\3\2\2\2\u00a9\u00a5\3\2\2\2\u00aa\36\3\2\2\2\u00ab\u00ac\7*\2\2"+
		"\u00ac \3\2\2\2\u00ad\u00ae\7+\2\2\u00ae\"\3\2\2\2\u00af\u00b0\7]\2\2"+
		"\u00b0$\3\2\2\2\u00b1\u00b2\7_\2\2\u00b2&\3\2\2\2\u00b3\u00b4\7}\2\2\u00b4"+
		"(\3\2\2\2\u00b5\u00b6\7\177\2\2\u00b6*\3\2\2\2\u00b7\u00ba\5C\"\2\u00b8"+
		"\u00ba\5E#\2\u00b9\u00b7\3\2\2\2\u00b9\u00b8\3\2\2\2\u00ba,\3\2\2\2\u00bb"+
		"\u00c1\7$\2\2\u00bc\u00bd\7$\2\2\u00bd\u00c0\7$\2\2\u00be\u00c0\n\5\2"+
		"\2\u00bf\u00bc\3\2\2\2\u00bf\u00be\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00bf"+
		"\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4"+
		"\u00d0\7$\2\2\u00c5\u00cb\7)\2\2\u00c6\u00c7\7)\2\2\u00c7\u00ca\7)\2\2"+
		"\u00c8\u00ca\n\6\2\2\u00c9\u00c6\3\2\2\2\u00c9\u00c8\3\2\2\2\u00ca\u00cd"+
		"\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ce\3\2\2\2\u00cd"+
		"\u00cb\3\2\2\2\u00ce\u00d0\7)\2\2\u00cf\u00bb\3\2\2\2\u00cf\u00c5\3\2"+
		"\2\2\u00d0.\3\2\2\2\u00d1\u00d5\5G$\2\u00d2\u00d5\5C\"\2\u00d3\u00d5\7"+
		"a\2\2\u00d4\u00d1\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d3\3\2\2\2\u00d5"+
		"\u00d6\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\3\2"+
		"\2\2\u00d8\u00d9\b\30\2\2\u00d9\60\3\2\2\2\u00da\u00dd\5\63\32\2\u00db"+
		"\u00dd\5\65\33\2\u00dc\u00da\3\2\2\2\u00dc\u00db\3\2\2\2\u00dd\u00e1\3"+
		"\2\2\2\u00de\u00e0\13\2\2\2\u00df\u00de\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1"+
		"\u00e2\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2"+
		"\2\2\u00e4\u00e6\5\67\34\2\u00e5\u00e4\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6"+
		"\u00e7\3\2\2\2\u00e7\u00e8\59\35\2\u00e8\u0107\3\2\2\2\u00e9\u00ea\7%"+
		"\2\2\u00ea\u0104\5/\30\2\u00eb\u00f5\5/\30\2\u00ec\u00ed\7*\2\2\u00ed"+
		"\u00f2\5/\30\2\u00ee\u00ef\7.\2\2\u00ef\u00f1\5/\30\2\u00f0\u00ee\3\2"+
		"\2\2\u00f1\u00f4\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3"+
		"\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f5\u00ec\3\2\2\2\u00f5\u00f6\3\2"+
		"\2\2\u00f6\u0101\3\2\2\2\u00f7\u00f8\7\"\2\2\u00f8\u00f9\7^\2\2\u00f9"+
		"\u0100\7\f\2\2\u00fa\u00fb\7\"\2\2\u00fb\u00fc\7^\2\2\u00fc\u00fd\7\17"+
		"\2\2\u00fd\u0100\7\f\2\2\u00fe\u0100\n\7\2\2\u00ff\u00f7\3\2\2\2\u00ff"+
		"\u00fa\3\2\2\2\u00ff\u00fe\3\2\2\2\u0100\u0103\3\2\2\2\u0101\u00ff\3\2"+
		"\2\2\u0101\u0102\3\2\2\2\u0102\u0105\3\2\2\2\u0103\u0101\3\2\2\2\u0104"+
		"\u00eb\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0107\3\2\2\2\u0106\u00dc\3\2"+
		"\2\2\u0106\u00e9\3\2\2\2\u0107\62\3\2\2\2\u0108\u0109\7%\2\2\u0109\u010a"+
		"\7k\2\2\u010a\u010b\7h\2\2\u010b\u010c\7f\2\2\u010c\u010d\7g\2\2\u010d"+
		"\u010e\7h\2\2\u010e\64\3\2\2\2\u010f\u0110\7%\2\2\u0110\u0111\7k\2\2\u0111"+
		"\u0112\7h\2\2\u0112\u0113\7p\2\2\u0113\u0114\7f\2\2\u0114\u0115\7g\2\2"+
		"\u0115\u0116\7h\2\2\u0116\66\3\2\2\2\u0117\u0118\7%\2\2\u0118\u0119\7"+
		"g\2\2\u0119\u011a\7n\2\2\u011a\u011b\7u\2\2\u011b\u011c\7g\2\2\u011c8"+
		"\3\2\2\2\u011d\u011e\7%\2\2\u011e\u011f\7g\2\2\u011f\u0120\7p\2\2\u0120"+
		"\u0121\7f\2\2\u0121\u0122\7k\2\2\u0122\u0123\7h\2\2\u0123:\3\2\2\2\u0124"+
		"\u0125\7\61\2\2\u0125\u0126\7,\2\2\u0126\u0127\7,\2\2\u0127\u012b\3\2"+
		"\2\2\u0128\u012a\13\2\2\2\u0129\u0128\3\2\2\2\u012a\u012d\3\2\2\2\u012b"+
		"\u012c\3\2\2\2\u012b\u0129\3\2\2\2\u012c\u012e\3\2\2\2\u012d\u012b\3\2"+
		"\2\2\u012e\u012f\7,\2\2\u012f\u0130\7\61\2\2\u0130\u0131\3\2\2\2\u0131"+
		"\u0132\b\36\3\2\u0132<\3\2\2\2\u0133\u0134\7\61\2\2\u0134\u0135\7\61\2"+
		"\2\u0135\u0139\3\2\2\2\u0136\u0138\13\2\2\2\u0137\u0136\3\2\2\2\u0138"+
		"\u013b\3\2\2\2\u0139\u013a\3\2\2\2\u0139\u0137\3\2\2\2\u013a\u013c\3\2"+
		"\2\2\u013b\u0139\3\2\2\2\u013c\u0149\5? \2\u013d\u013e\7\61\2\2\u013e"+
		"\u013f\7,\2\2\u013f\u0143\3\2\2\2\u0140\u0142\13\2\2\2\u0141\u0140\3\2"+
		"\2\2\u0142\u0145\3\2\2\2\u0143\u0144\3\2\2\2\u0143\u0141\3\2\2\2\u0144"+
		"\u0146\3\2\2\2\u0145\u0143\3\2\2\2\u0146\u0147\7,\2\2\u0147\u0149\7\61"+
		"\2\2\u0148\u0133\3\2\2\2\u0148\u013d\3\2\2\2\u0149\u014a\3\2\2\2\u014a"+
		"\u014b\b\37\4\2\u014b>\3\2\2\2\u014c\u0150\7\f\2\2\u014d\u014e\7\17\2"+
		"\2\u014e\u0150\7\f\2\2\u014f\u014c\3\2\2\2\u014f\u014d\3\2\2\2\u0150\u0151"+
		"\3\2\2\2\u0151\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0153\3\2\2\2\u0153"+
		"\u0154\b \5\2\u0154@\3\2\2\2\u0155\u0157\t\b\2\2\u0156\u0155\3\2\2\2\u0157"+
		"\u0158\3\2\2\2\u0158\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015a\3\2"+
		"\2\2\u015a\u015b\b!\6\2\u015bB\3\2\2\2\u015c\u015e\t\t\2\2\u015d\u015c"+
		"\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160"+
		"D\3\2\2\2\u0161\u0162\5C\"\2\u0162\u0163\7\60\2\2\u0163\u0164\5C\"\2\u0164"+
		"\u0168\3\2\2\2\u0165\u0166\7\60\2\2\u0166\u0168\5C\"\2\u0167\u0161\3\2"+
		"\2\2\u0167\u0165\3\2\2\2\u0168F\3\2\2\2\u0169\u016b\t\n\2\2\u016a\u0169"+
		"\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016a\3\2\2\2\u016c\u016d\3\2\2\2\u016d"+
		"H\3\2\2\2\u016e\u016f\t\13\2\2\u016fJ\3\2\2\2\u0170\u0171\t\f\2\2\u0171"+
		"L\3\2\2\2\u0172\u0173\t\r\2\2\u0173N\3\2\2\2\u0174\u0175\t\16\2\2\u0175"+
		"P\3\2\2\2\u0176\u0177\t\17\2\2\u0177R\3\2\2\2\u0178\u0179\t\20\2\2\u0179"+
		"T\3\2\2\2\u017a\u017b\t\21\2\2\u017bV\3\2\2\2\u017c\u017d\t\22\2\2\u017d"+
		"X\3\2\2\2\u017e\u017f\t\23\2\2\u017fZ\3\2\2\2\u0180\u0181\t\24\2\2\u0181"+
		"\\\3\2\2\2\u0182\u0183\t\25\2\2\u0183^\3\2\2\2\u0184\u0185\t\26\2\2\u0185"+
		"`\3\2\2\2\u0186\u0187\t\27\2\2\u0187b\3\2\2\2\u0188\u0189\13\2\2\2\u0189"+
		"d\3\2\2\2$\2u}\u0084\u0091\u00a0\u00a9\u00b9\u00bf\u00c1\u00c9\u00cb\u00cf"+
		"\u00d4\u00d6\u00dc\u00e1\u00e5\u00f2\u00f5\u00ff\u0101\u0104\u0106\u012b"+
		"\u0139\u0143\u0148\u014f\u0151\u0158\u015f\u0167\u016c\7\3\30\2\3\36\3"+
		"\3\37\4\3 \5\3!\6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}