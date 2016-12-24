// Generated from SQF.g4 by ANTLR 4.5.3

	package raven.sqdev.editors.sqfeditor.parsing;
	
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
		OPERATOR_PRECEDENCE_MULTIPLY=1, OPERATOR_PRECEDENCE_ADD=2, PUCTUATION_OTHER=3, 
		OR=4, AND=5, COMPARE_PRECEDENCE_OPERATOR=6, ELSE=7, POWER=8, SEMICOLON=9, 
		COMMA=10, EQUALS=11, PRIVATE=12, MACRO_DECLARATION=13, WHITESPACE=14, 
		COMMENT=15, NUMBER=16, ID=17, STRING=18, C_B_O=19, C_B_C=20, S_B_O=21, 
		S_B_C=22, R_B_O=23, R_B_C=24, OTHER=25;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"OPERATOR_PRECEDENCE_MULTIPLY", "OPERATOR_PRECEDENCE_ADD", "PUCTUATION_OTHER", 
		"OR", "AND", "COMPARE_PRECEDENCE_OPERATOR", "ELSE", "POWER", "SEMICOLON", 
		"COMMA", "EQUALS", "PRIVATE", "MACRO_DECLARATION", "WHITESPACE", "COMMENT", 
		"NUMBER", "ID", "STRING", "C_B_O", "C_B_C", "S_B_O", "S_B_C", "R_B_O", 
		"R_B_C", "OTHER", "LETTER", "INT", "A", "E", "P", "L", "R", "S", "T", 
		"I", "V"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, "'!'", "'||'", "'&&'", null, null, "'^'", "';'", "','", 
		"'='", null, null, null, null, null, null, null, "'{'", "'}'", "'['", 
		"']'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "OPERATOR_PRECEDENCE_MULTIPLY", "OPERATOR_PRECEDENCE_ADD", "PUCTUATION_OTHER", 
		"OR", "AND", "COMPARE_PRECEDENCE_OPERATOR", "ELSE", "POWER", "SEMICOLON", 
		"COMMA", "EQUALS", "PRIVATE", "MACRO_DECLARATION", "WHITESPACE", "COMMENT", 
		"NUMBER", "ID", "STRING", "C_B_O", "C_B_C", "S_B_O", "S_B_C", "R_B_O", 
		"R_B_C", "OTHER"
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


		
		protected List<String> binaryOperators;
		protected List<String> macroNames;
		
		
		public SQFLexer(CharStream input, List<String> binaryOperators, List<String> macroNames) {
			this(input);
			
			// make operators lowercase
			for(int i=0; i<binaryOperators.size(); i++) {
				binaryOperators.set(i, binaryOperators.get(i).toLowerCase());
			}
			
			this.binaryOperators = binaryOperators;
			this.macroNames = macroNames;
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
		case 16:
			ID_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void ID_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

				if (macroNames.contains(getText())) {
					// it's not an ID but a macro name'
					setType(SQFParser.MACRO_NAME);
				} else {
					if (binaryOperators.contains(getText().toLowerCase())) {
						// it's not an ID but a binary operator'
						setType(SQFParser.BINARY_OPERATOR);
					}
				}

			break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\33\u0123\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\2\5\2P\n\2\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\5\3Y\n\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7n\n\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0092\n\16"+
		"\3\16\7\16\u0095\n\16\f\16\16\16\u0098\13\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00a5\n\16\f\16\16\16\u00a8\13\16"+
		"\3\16\5\16\u00ab\n\16\3\16\3\16\3\17\6\17\u00b0\n\17\r\17\16\17\u00b1"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\7\20\u00ba\n\20\f\20\16\20\u00bd\13\20"+
		"\3\20\5\20\u00c0\n\20\3\20\3\20\3\20\3\20\7\20\u00c6\n\20\f\20\16\20\u00c9"+
		"\13\20\3\20\3\20\5\20\u00cd\n\20\3\20\3\20\3\21\6\21\u00d2\n\21\r\21\16"+
		"\21\u00d3\3\21\3\21\6\21\u00d8\n\21\r\21\16\21\u00d9\5\21\u00dc\n\21\3"+
		"\22\3\22\3\22\6\22\u00e1\n\22\r\22\16\22\u00e2\3\22\3\22\3\23\3\23\3\23"+
		"\3\23\7\23\u00eb\n\23\f\23\16\23\u00ee\13\23\3\23\3\23\3\23\3\23\3\23"+
		"\7\23\u00f5\n\23\f\23\16\23\u00f8\13\23\3\23\5\23\u00fb\n\23\3\24\3\24"+
		"\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\6\32\u010a\n\32"+
		"\r\32\16\32\u010b\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3"+
		" \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\6\u0096\u00bb\u00c7\u010b\2&\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\2\67\29\2;\2=\2?\2A\2"+
		"C\2E\2G\2I\2\3\2\25\4\2\'\',,\4\2--//\4\2>>@@\3\2\f\f\5\2\13\f\17\17\""+
		"\"\3\3\f\f\3\2$$\3\2))\4\2C\\c|\3\2\62;\4\2CCcc\4\2GGgg\4\2RRrr\4\2NN"+
		"nn\4\2TTtt\4\2UUuu\4\2VVvv\4\2KKkk\4\2XXxx\u0134\2\3\3\2\2\2\2\5\3\2\2"+
		"\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3"+
		"\2\2\2\3O\3\2\2\2\5X\3\2\2\2\7Z\3\2\2\2\t\\\3\2\2\2\13_\3\2\2\2\rm\3\2"+
		"\2\2\17o\3\2\2\2\21t\3\2\2\2\23v\3\2\2\2\25x\3\2\2\2\27z\3\2\2\2\31|\3"+
		"\2\2\2\33\u00aa\3\2\2\2\35\u00af\3\2\2\2\37\u00cc\3\2\2\2!\u00d1\3\2\2"+
		"\2#\u00e0\3\2\2\2%\u00fa\3\2\2\2\'\u00fc\3\2\2\2)\u00fe\3\2\2\2+\u0100"+
		"\3\2\2\2-\u0102\3\2\2\2/\u0104\3\2\2\2\61\u0106\3\2\2\2\63\u0109\3\2\2"+
		"\2\65\u010d\3\2\2\2\67\u010f\3\2\2\29\u0111\3\2\2\2;\u0113\3\2\2\2=\u0115"+
		"\3\2\2\2?\u0117\3\2\2\2A\u0119\3\2\2\2C\u011b\3\2\2\2E\u011d\3\2\2\2G"+
		"\u011f\3\2\2\2I\u0121\3\2\2\2KP\t\2\2\2LM\7o\2\2MN\7q\2\2NP\7f\2\2OK\3"+
		"\2\2\2OL\3\2\2\2P\4\3\2\2\2QY\t\3\2\2RS\7o\2\2ST\7k\2\2TY\7p\2\2UV\7o"+
		"\2\2VW\7c\2\2WY\7z\2\2XQ\3\2\2\2XR\3\2\2\2XU\3\2\2\2Y\6\3\2\2\2Z[\7#\2"+
		"\2[\b\3\2\2\2\\]\7~\2\2]^\7~\2\2^\n\3\2\2\2_`\7(\2\2`a\7(\2\2a\f\3\2\2"+
		"\2bc\7?\2\2cn\7?\2\2de\7#\2\2en\7?\2\2fn\t\4\2\2gh\7>\2\2hn\7?\2\2ij\7"+
		"@\2\2jn\7?\2\2kl\7@\2\2ln\7@\2\2mb\3\2\2\2md\3\2\2\2mf\3\2\2\2mg\3\2\2"+
		"\2mi\3\2\2\2mk\3\2\2\2n\16\3\2\2\2op\5;\36\2pq\5? \2qr\5C\"\2rs\5;\36"+
		"\2s\20\3\2\2\2tu\7`\2\2u\22\3\2\2\2vw\7=\2\2w\24\3\2\2\2xy\7.\2\2y\26"+
		"\3\2\2\2z{\7?\2\2{\30\3\2\2\2|}\5=\37\2}~\5A!\2~\177\5G$\2\177\u0080\5"+
		"I%\2\u0080\u0081\59\35\2\u0081\u0082\5E#\2\u0082\u0083\5;\36\2\u0083\32"+
		"\3\2\2\2\u0084\u0085\7%\2\2\u0085\u0086\7k\2\2\u0086\u0087\7h\2\2\u0087"+
		"\u0088\7f\2\2\u0088\u0089\7g\2\2\u0089\u0092\7h\2\2\u008a\u008b\7%\2\2"+
		"\u008b\u008c\7k\2\2\u008c\u008d\7h\2\2\u008d\u008e\7p\2\2\u008e\u008f"+
		"\7f\2\2\u008f\u0090\7g\2\2\u0090\u0092\7h\2\2\u0091\u0084\3\2\2\2\u0091"+
		"\u008a\3\2\2\2\u0092\u0096\3\2\2\2\u0093\u0095\13\2\2\2\u0094\u0093\3"+
		"\2\2\2\u0095\u0098\3\2\2\2\u0096\u0097\3\2\2\2\u0096\u0094\3\2\2\2\u0097"+
		"\u0099\3\2\2\2\u0098\u0096\3\2\2\2\u0099\u009a\7%\2\2\u009a\u009b\7g\2"+
		"\2\u009b\u009c\7p\2\2\u009c\u009d\7f\2\2\u009d\u009e\7k\2\2\u009e\u00ab"+
		"\7h\2\2\u009f\u00a6\7%\2\2\u00a0\u00a5\n\5\2\2\u00a1\u00a2\7\"\2\2\u00a2"+
		"\u00a3\7^\2\2\u00a3\u00a5\7\f\2\2\u00a4\u00a0\3\2\2\2\u00a4\u00a1\3\2"+
		"\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\u00a9\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00ab\7\f\2\2\u00aa\u0091\3\2"+
		"\2\2\u00aa\u009f\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\b\16\2\2\u00ad"+
		"\34\3\2\2\2\u00ae\u00b0\t\6\2\2\u00af\u00ae\3\2\2\2\u00b0\u00b1\3\2\2"+
		"\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4"+
		"\b\17\2\2\u00b4\36\3\2\2\2\u00b5\u00b6\7\61\2\2\u00b6\u00b7\7\61\2\2\u00b7"+
		"\u00bb\3\2\2\2\u00b8\u00ba\13\2\2\2\u00b9\u00b8\3\2\2\2\u00ba\u00bd\3"+
		"\2\2\2\u00bb\u00bc\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd"+
		"\u00bb\3\2\2\2\u00be\u00c0\t\7\2\2\u00bf\u00be\3\2\2\2\u00c0\u00cd\3\2"+
		"\2\2\u00c1\u00c2\7\61\2\2\u00c2\u00c3\7,\2\2\u00c3\u00c7\3\2\2\2\u00c4"+
		"\u00c6\13\2\2\2\u00c5\u00c4\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7\u00c8\3"+
		"\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00ca\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca"+
		"\u00cb\7,\2\2\u00cb\u00cd\7\61\2\2\u00cc\u00b5\3\2\2\2\u00cc\u00c1\3\2"+
		"\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\b\20\2\2\u00cf \3\2\2\2\u00d0\u00d2"+
		"\5\67\34\2\u00d1\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d1\3\2\2\2"+
		"\u00d3\u00d4\3\2\2\2\u00d4\u00db\3\2\2\2\u00d5\u00d7\7\60\2\2\u00d6\u00d8"+
		"\5\67\34\2\u00d7\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00d7\3\2\2\2"+
		"\u00d9\u00da\3\2\2\2\u00da\u00dc\3\2\2\2\u00db\u00d5\3\2\2\2\u00db\u00dc"+
		"\3\2\2\2\u00dc\"\3\2\2\2\u00dd\u00e1\5\65\33\2\u00de\u00e1\5\67\34\2\u00df"+
		"\u00e1\7a\2\2\u00e0\u00dd\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00df\3\2"+
		"\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3"+
		"\u00e4\3\2\2\2\u00e4\u00e5\b\22\3\2\u00e5$\3\2\2\2\u00e6\u00ec\7$\2\2"+
		"\u00e7\u00eb\n\b\2\2\u00e8\u00e9\7$\2\2\u00e9\u00eb\7$\2\2\u00ea\u00e7"+
		"\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb\u00ee\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ec"+
		"\u00ed\3\2\2\2\u00ed\u00ef\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ef\u00fb\7$"+
		"\2\2\u00f0\u00f6\7)\2\2\u00f1\u00f5\n\t\2\2\u00f2\u00f3\7)\2\2\u00f3\u00f5"+
		"\7)\2\2\u00f4\u00f1\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6"+
		"\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f9\3\2\2\2\u00f8\u00f6\3\2"+
		"\2\2\u00f9\u00fb\7)\2\2\u00fa\u00e6\3\2\2\2\u00fa\u00f0\3\2\2\2\u00fb"+
		"&\3\2\2\2\u00fc\u00fd\7}\2\2\u00fd(\3\2\2\2\u00fe\u00ff\7\177\2\2\u00ff"+
		"*\3\2\2\2\u0100\u0101\7]\2\2\u0101,\3\2\2\2\u0102\u0103\7_\2\2\u0103."+
		"\3\2\2\2\u0104\u0105\7*\2\2\u0105\60\3\2\2\2\u0106\u0107\7+\2\2\u0107"+
		"\62\3\2\2\2\u0108\u010a\13\2\2\2\u0109\u0108\3\2\2\2\u010a\u010b\3\2\2"+
		"\2\u010b\u010c\3\2\2\2\u010b\u0109\3\2\2\2\u010c\64\3\2\2\2\u010d\u010e"+
		"\t\n\2\2\u010e\66\3\2\2\2\u010f\u0110\t\13\2\2\u01108\3\2\2\2\u0111\u0112"+
		"\t\f\2\2\u0112:\3\2\2\2\u0113\u0114\t\r\2\2\u0114<\3\2\2\2\u0115\u0116"+
		"\t\16\2\2\u0116>\3\2\2\2\u0117\u0118\t\17\2\2\u0118@\3\2\2\2\u0119\u011a"+
		"\t\20\2\2\u011aB\3\2\2\2\u011b\u011c\t\21\2\2\u011cD\3\2\2\2\u011d\u011e"+
		"\t\22\2\2\u011eF\3\2\2\2\u011f\u0120\t\23\2\2\u0120H\3\2\2\2\u0121\u0122"+
		"\t\24\2\2\u0122J\3\2\2\2\33\2OXm\u0091\u0096\u00a4\u00a6\u00aa\u00b1\u00bb"+
		"\u00bf\u00c7\u00cc\u00d3\u00d9\u00db\u00e0\u00e2\u00ea\u00ec\u00f4\u00f6"+
		"\u00fa\u010b\4\b\2\2\3\22\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}