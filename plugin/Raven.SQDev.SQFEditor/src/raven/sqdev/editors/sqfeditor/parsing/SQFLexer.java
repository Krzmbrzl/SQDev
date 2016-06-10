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
		CLOSING_CURLY_BRACKET=20, NUMBER=21, STRING=22, ID=23, PREPROCESSOR=24, 
		DOC=25, COMMENT=26, WS=27;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"COMMA", "SEMICOLON", "EQUALS", "ELSE", "OR", "AND", "NOT", "EXP", "COMPARATOR", 
		"CONFIG_OPERATOR", "ADDITION_OPERATOR", "MIN_MAX_OPERATOR", "MULTIPLICATION_OPERATOR", 
		"MODULO_OPERATOR", "OPENING_ROUND_BRACKET", "CLOSING_ROUND_BRACKET", "OPENING_SQUARE_BRACKET", 
		"CLOSING_SQUARE_BRACKET", "OPENING_CURLY_BRACKET", "CLOSING_CURLY_BRACKET", 
		"NUMBER", "STRING", "ID", "PREPROCESSOR", "DOC", "COMMENT", "WS", "INT", 
		"FLOAT", "LETTER", "A", "D", "E", "F", "I", "L", "M", "N", "O", "R", "S", 
		"T", "X"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "';'", "'='", null, null, null, null, "'^'", null, "'>>'", 
		null, null, null, null, "'('", "')'", "'['", "']'", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMA", "SEMICOLON", "EQUALS", "ELSE", "OR", "AND", "NOT", "EXP", 
		"COMPARATOR", "CONFIG_OPERATOR", "ADDITION_OPERATOR", "MIN_MAX_OPERATOR", 
		"MULTIPLICATION_OPERATOR", "MODULO_OPERATOR", "OPENING_ROUND_BRACKET", 
		"CLOSING_ROUND_BRACKET", "OPENING_SQUARE_BRACKET", "CLOSING_SQUARE_BRACKET", 
		"OPENING_CURLY_BRACKET", "CLOSING_CURLY_BRACKET", "NUMBER", "STRING", 
		"ID", "PREPROCESSOR", "DOC", "COMMENT", "WS"
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
		 * The id for the hidden preprocessor token channel
		 */
		public static final int PREPROCESSOR_CHANNEL = 4;
		
		
		private List<String> binaryOperators = new ArrayList<String>();
		
		/**
		 * Creates an instance of this lexer
		 * @param in The inputStream to work on
		 * @param binaryOperators A list of binary operators the lexer should match. <b>These operators have
		 *  to be all lowercase in order to get matched!</b>
		 */
		public SQFLexer(CharStream in, List<String> binaryOperators) {
			this(in);
			this.binaryOperators = binaryOperators;
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
		case 23:
			PREPROCESSOR_action((RuleContext)_localctx, actionIndex);
			break;
		case 24:
			DOC_action((RuleContext)_localctx, actionIndex);
			break;
		case 25:
			COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 26:
			WS_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void ID_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

			      	if(binaryOperators.contains(getText().toLowerCase())) {
			      		setType(SQFParser.BINARY_OPERATOR);
			      	}
			      
			break;
		}
	}
	private void PREPROCESSOR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			_channel = PREPROCESSOR_CHANNEL;
			break;
		}
	}
	private void DOC_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			_channel = DOCUMENTATION_CHANNEL;
			break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			_channel = COMMENT_CHANNEL;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\35\u016d\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\5\6j\n\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7r\n\7\3\b\3\b\3\b\3\b\3\b\5\by"+
		"\n\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0086\n\n\3\13\3"+
		"\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0095\n\r\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\3\17\5\17\u009e\n\17\3\20\3\20\3\21\3\21\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\5\26\u00ae\n\26\3\27\3\27"+
		"\3\27\3\27\7\27\u00b4\n\27\f\27\16\27\u00b7\13\27\3\27\3\27\3\27\3\27"+
		"\3\27\7\27\u00be\n\27\f\27\16\27\u00c1\13\27\3\27\5\27\u00c4\n\27\3\30"+
		"\3\30\3\30\6\30\u00c9\n\30\r\30\16\30\u00ca\3\30\3\30\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u00dd\n\31"+
		"\3\31\7\31\u00e0\n\31\f\31\16\31\u00e3\13\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\7\31\u00f3\n\31\f\31\16"+
		"\31\u00f6\13\31\5\31\u00f8\n\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\7\31\u0102\n\31\f\31\16\31\u0105\13\31\3\31\3\31\3\31\5\31\u010a\n\31"+
		"\5\31\u010c\n\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\7\32\u0115\n\32\f"+
		"\32\16\32\u0118\13\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\7\33"+
		"\u0123\n\33\f\33\16\33\u0126\13\33\3\33\5\33\u0129\n\33\3\33\3\33\3\33"+
		"\3\33\3\33\7\33\u0130\n\33\f\33\16\33\u0133\13\33\3\33\3\33\5\33\u0137"+
		"\n\33\3\33\3\33\3\34\6\34\u013c\n\34\r\34\16\34\u013d\3\34\3\34\3\35\6"+
		"\35\u0143\n\35\r\35\16\35\u0144\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u014d"+
		"\n\36\3\37\6\37\u0150\n\37\r\37\16\37\u0151\3 \3 \3!\3!\3\"\3\"\3#\3#"+
		"\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\6\u00e1\u0116"+
		"\u0124\u0131\2-\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\2;\2=\2?\2A\2C\2E\2G\2I\2K\2M\2O\2Q\2S\2U\2W\2\3\2\30\4\2>"+
		">@@\4\2--//\4\2,,\61\61\3\2$$\3\2))\4\2\f\f\17\17\5\2\13\f\17\17\"\"\3"+
		"\2\62;\4\2C\\c|\4\2CCcc\4\2FFff\4\2GGgg\4\2HHhh\4\2KKkk\4\2NNnn\4\2OO"+
		"oo\4\2PPpp\4\2QQqq\4\2TTtt\4\2UUuu\4\2VVvv\4\2ZZzz\u0180\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"+
		"\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\3Y\3\2\2\2\5[\3\2\2\2\7]\3\2\2"+
		"\2\t_\3\2\2\2\13i\3\2\2\2\rq\3\2\2\2\17x\3\2\2\2\21z\3\2\2\2\23\u0085"+
		"\3\2\2\2\25\u0087\3\2\2\2\27\u008a\3\2\2\2\31\u0094\3\2\2\2\33\u0096\3"+
		"\2\2\2\35\u009d\3\2\2\2\37\u009f\3\2\2\2!\u00a1\3\2\2\2#\u00a3\3\2\2\2"+
		"%\u00a5\3\2\2\2\'\u00a7\3\2\2\2)\u00a9\3\2\2\2+\u00ad\3\2\2\2-\u00c3\3"+
		"\2\2\2/\u00c8\3\2\2\2\61\u010b\3\2\2\2\63\u010f\3\2\2\2\65\u0136\3\2\2"+
		"\2\67\u013b\3\2\2\29\u0142\3\2\2\2;\u014c\3\2\2\2=\u014f\3\2\2\2?\u0153"+
		"\3\2\2\2A\u0155\3\2\2\2C\u0157\3\2\2\2E\u0159\3\2\2\2G\u015b\3\2\2\2I"+
		"\u015d\3\2\2\2K\u015f\3\2\2\2M\u0161\3\2\2\2O\u0163\3\2\2\2Q\u0165\3\2"+
		"\2\2S\u0167\3\2\2\2U\u0169\3\2\2\2W\u016b\3\2\2\2YZ\7.\2\2Z\4\3\2\2\2"+
		"[\\\7=\2\2\\\6\3\2\2\2]^\7?\2\2^\b\3\2\2\2_`\5C\"\2`a\5I%\2ab\5S*\2bc"+
		"\5C\"\2c\n\3\2\2\2de\7~\2\2ej\7~\2\2fg\5O(\2gh\5Q)\2hj\3\2\2\2id\3\2\2"+
		"\2if\3\2\2\2j\f\3\2\2\2kl\7(\2\2lr\7(\2\2mn\5? \2no\5M\'\2op\5A!\2pr\3"+
		"\2\2\2qk\3\2\2\2qm\3\2\2\2r\16\3\2\2\2sy\7#\2\2tu\5M\'\2uv\5O(\2vw\5U"+
		"+\2wy\3\2\2\2xs\3\2\2\2xt\3\2\2\2y\20\3\2\2\2z{\7`\2\2{\22\3\2\2\2|\u0086"+
		"\t\2\2\2}~\7>\2\2~\u0086\7?\2\2\177\u0080\7@\2\2\u0080\u0086\7?\2\2\u0081"+
		"\u0082\7?\2\2\u0082\u0086\7?\2\2\u0083\u0084\7#\2\2\u0084\u0086\7?\2\2"+
		"\u0085|\3\2\2\2\u0085}\3\2\2\2\u0085\177\3\2\2\2\u0085\u0081\3\2\2\2\u0085"+
		"\u0083\3\2\2\2\u0086\24\3\2\2\2\u0087\u0088\7@\2\2\u0088\u0089\7@\2\2"+
		"\u0089\26\3\2\2\2\u008a\u008b\t\3\2\2\u008b\30\3\2\2\2\u008c\u008d\5K"+
		"&\2\u008d\u008e\5G$\2\u008e\u008f\5M\'\2\u008f\u0095\3\2\2\2\u0090\u0091"+
		"\5K&\2\u0091\u0092\5? \2\u0092\u0093\5W,\2\u0093\u0095\3\2\2\2\u0094\u008c"+
		"\3\2\2\2\u0094\u0090\3\2\2\2\u0095\32\3\2\2\2\u0096\u0097\t\4\2\2\u0097"+
		"\34\3\2\2\2\u0098\u009e\7\'\2\2\u0099\u009a\5K&\2\u009a\u009b\5O(\2\u009b"+
		"\u009c\5A!\2\u009c\u009e\3\2\2\2\u009d\u0098\3\2\2\2\u009d\u0099\3\2\2"+
		"\2\u009e\36\3\2\2\2\u009f\u00a0\7*\2\2\u00a0 \3\2\2\2\u00a1\u00a2\7+\2"+
		"\2\u00a2\"\3\2\2\2\u00a3\u00a4\7]\2\2\u00a4$\3\2\2\2\u00a5\u00a6\7_\2"+
		"\2\u00a6&\3\2\2\2\u00a7\u00a8\7}\2\2\u00a8(\3\2\2\2\u00a9\u00aa\7\177"+
		"\2\2\u00aa*\3\2\2\2\u00ab\u00ae\59\35\2\u00ac\u00ae\5;\36\2\u00ad\u00ab"+
		"\3\2\2\2\u00ad\u00ac\3\2\2\2\u00ae,\3\2\2\2\u00af\u00b5\7$\2\2\u00b0\u00b1"+
		"\7$\2\2\u00b1\u00b4\7$\2\2\u00b2\u00b4\n\5\2\2\u00b3\u00b0\3\2\2\2\u00b3"+
		"\u00b2\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2"+
		"\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00c4\7$\2\2\u00b9"+
		"\u00bf\7)\2\2\u00ba\u00bb\7)\2\2\u00bb\u00be\7)\2\2\u00bc\u00be\n\6\2"+
		"\2\u00bd\u00ba\3\2\2\2\u00bd\u00bc\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf\u00bd"+
		"\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2"+
		"\u00c4\7)\2\2\u00c3\u00af\3\2\2\2\u00c3\u00b9\3\2\2\2\u00c4.\3\2\2\2\u00c5"+
		"\u00c9\5=\37\2\u00c6\u00c9\59\35\2\u00c7\u00c9\7a\2\2\u00c8\u00c5\3\2"+
		"\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca"+
		"\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\b\30"+
		"\2\2\u00cd\60\3\2\2\2\u00ce\u00dc\7%\2\2\u00cf\u00d0\5G$\2\u00d0\u00d1"+
		"\5E#\2\u00d1\u00d2\5A!\2\u00d2\u00d3\5C\"\2\u00d3\u00d4\5E#\2\u00d4\u00dd"+
		"\3\2\2\2\u00d5\u00d6\5G$\2\u00d6\u00d7\5E#\2\u00d7\u00d8\5M\'\2\u00d8"+
		"\u00d9\5A!\2\u00d9\u00da\5C\"\2\u00da\u00db\5E#\2\u00db\u00dd\3\2\2\2"+
		"\u00dc\u00cf\3\2\2\2\u00dc\u00d5\3\2\2\2\u00dd\u00e1\3\2\2\2\u00de\u00e0"+
		"\13\2\2\2\u00df\u00de\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00e2\3\2\2\2"+
		"\u00e1\u00df\3\2\2\2\u00e2\u00e4\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4\u00e5"+
		"\7%\2\2\u00e5\u00e6\5C\"\2\u00e6\u00e7\5M\'\2\u00e7\u00e8\5A!\2\u00e8"+
		"\u00e9\5G$\2\u00e9\u00ea\5E#\2\u00ea\u010c\3\2\2\2\u00eb\u00ec\7%\2\2"+
		"\u00ec\u00ed\5=\37\2\u00ed\u00f7\5/\30\2\u00ee\u00ef\7*\2\2\u00ef\u00f4"+
		"\5/\30\2\u00f0\u00f1\7.\2\2\u00f1\u00f3\5/\30\2\u00f2\u00f0\3\2\2\2\u00f3"+
		"\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f8\3\2"+
		"\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00ee\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8"+
		"\u0103\3\2\2\2\u00f9\u00fa\7\"\2\2\u00fa\u00fb\7^\2\2\u00fb\u0102\7\f"+
		"\2\2\u00fc\u00fd\7\"\2\2\u00fd\u00fe\7^\2\2\u00fe\u00ff\7\17\2\2\u00ff"+
		"\u0102\7\f\2\2\u0100\u0102\n\7\2\2\u0101\u00f9\3\2\2\2\u0101\u00fc\3\2"+
		"\2\2\u0101\u0100\3\2\2\2\u0102\u0105\3\2\2\2\u0103\u0101\3\2\2\2\u0103"+
		"\u0104\3\2\2\2\u0104\u0109\3\2\2\2\u0105\u0103\3\2\2\2\u0106\u010a\7\f"+
		"\2\2\u0107\u0108\7\17\2\2\u0108\u010a\7\f\2\2\u0109\u0106\3\2\2\2\u0109"+
		"\u0107\3\2\2\2\u010a\u010c\3\2\2\2\u010b\u00ce\3\2\2\2\u010b\u00eb\3\2"+
		"\2\2\u010c\u010d\3\2\2\2\u010d\u010e\b\31\3\2\u010e\62\3\2\2\2\u010f\u0110"+
		"\7\61\2\2\u0110\u0111\7,\2\2\u0111\u0112\7,\2\2\u0112\u0116\3\2\2\2\u0113"+
		"\u0115\13\2\2\2\u0114\u0113\3\2\2\2\u0115\u0118\3\2\2\2\u0116\u0117\3"+
		"\2\2\2\u0116\u0114\3\2\2\2\u0117\u0119\3\2\2\2\u0118\u0116\3\2\2\2\u0119"+
		"\u011a\7,\2\2\u011a\u011b\7\61\2\2\u011b\u011c\3\2\2\2\u011c\u011d\b\32"+
		"\4\2\u011d\64\3\2\2\2\u011e\u011f\7\61\2\2\u011f\u0120\7\61\2\2\u0120"+
		"\u0124\3\2\2\2\u0121\u0123\13\2\2\2\u0122\u0121\3\2\2\2\u0123\u0126\3"+
		"\2\2\2\u0124\u0125\3\2\2\2\u0124\u0122\3\2\2\2\u0125\u0128\3\2\2\2\u0126"+
		"\u0124\3\2\2\2\u0127\u0129\7\17\2\2\u0128\u0127\3\2\2\2\u0128\u0129\3"+
		"\2\2\2\u0129\u012a\3\2\2\2\u012a\u0137\7\f\2\2\u012b\u012c\7\61\2\2\u012c"+
		"\u012d\7,\2\2\u012d\u0131\3\2\2\2\u012e\u0130\13\2\2\2\u012f\u012e\3\2"+
		"\2\2\u0130\u0133\3\2\2\2\u0131\u0132\3\2\2\2\u0131\u012f\3\2\2\2\u0132"+
		"\u0134\3\2\2\2\u0133\u0131\3\2\2\2\u0134\u0135\7,\2\2\u0135\u0137\7\61"+
		"\2\2\u0136\u011e\3\2\2\2\u0136\u012b\3\2\2\2\u0137\u0138\3\2\2\2\u0138"+
		"\u0139\b\33\5\2\u0139\66\3\2\2\2\u013a\u013c\t\b\2\2\u013b\u013a\3\2\2"+
		"\2\u013c\u013d\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u013f"+
		"\3\2\2\2\u013f\u0140\b\34\6\2\u01408\3\2\2\2\u0141\u0143\t\t\2\2\u0142"+
		"\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2"+
		"\2\2\u0145:\3\2\2\2\u0146\u0147\59\35\2\u0147\u0148\7\60\2\2\u0148\u0149"+
		"\59\35\2\u0149\u014d\3\2\2\2\u014a\u014b\7\60\2\2\u014b\u014d\59\35\2"+
		"\u014c\u0146\3\2\2\2\u014c\u014a\3\2\2\2\u014d<\3\2\2\2\u014e\u0150\t"+
		"\n\2\2\u014f\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u014f\3\2\2\2\u0151"+
		"\u0152\3\2\2\2\u0152>\3\2\2\2\u0153\u0154\t\13\2\2\u0154@\3\2\2\2\u0155"+
		"\u0156\t\f\2\2\u0156B\3\2\2\2\u0157\u0158\t\r\2\2\u0158D\3\2\2\2\u0159"+
		"\u015a\t\16\2\2\u015aF\3\2\2\2\u015b\u015c\t\17\2\2\u015cH\3\2\2\2\u015d"+
		"\u015e\t\20\2\2\u015eJ\3\2\2\2\u015f\u0160\t\21\2\2\u0160L\3\2\2\2\u0161"+
		"\u0162\t\22\2\2\u0162N\3\2\2\2\u0163\u0164\t\23\2\2\u0164P\3\2\2\2\u0165"+
		"\u0166\t\24\2\2\u0166R\3\2\2\2\u0167\u0168\t\25\2\2\u0168T\3\2\2\2\u0169"+
		"\u016a\t\26\2\2\u016aV\3\2\2\2\u016b\u016c\t\27\2\2\u016cX\3\2\2\2\"\2"+
		"iqx\u0085\u0094\u009d\u00ad\u00b3\u00b5\u00bd\u00bf\u00c3\u00c8\u00ca"+
		"\u00dc\u00e1\u00f4\u00f7\u0101\u0103\u0109\u010b\u0116\u0124\u0128\u0131"+
		"\u0136\u013d\u0144\u014c\u0151\7\3\30\2\3\31\3\3\32\4\3\33\5\3\34\6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}