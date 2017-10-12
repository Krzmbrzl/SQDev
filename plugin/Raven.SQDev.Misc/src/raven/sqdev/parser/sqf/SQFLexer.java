// Generated from SQF.g4 by ANTLR 4.5.3

package raven.sqdev.parser.sqf;

import java.util.List;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class SQFLexer extends Lexer {
	static {
		RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION);
	}
	
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int OPERATOR_PRECEDENCE_MULTIPLY = 1,
			OPERATOR_PRECEDENCE_ADD = 2, PUCTUATION_OTHER = 3, OR = 4, AND = 5,
			COMPARE_PRECEDENCE_OPERATOR = 6, ELSE = 7, POWER = 8, SEMICOLON = 9,
			COMMA = 10, EQUALS = 11, PRIVATE = 12, MACRO_DECLARATION = 13,
			WHITESPACE = 14, COMMENT = 15, NUMBER = 16, ID = 17,
			BINARY_OPERATOR = 18, STRING = 19, C_B_O = 20, C_B_C = 21,
			S_B_O = 22, S_B_C = 23, R_B_O = 24, R_B_C = 25, OTHER = 26;
	public static String[] modeNames = { "DEFAULT_MODE" };
	
	public static final String[] ruleNames = { "OPERATOR_PRECEDENCE_MULTIPLY",
			"OPERATOR_PRECEDENCE_ADD", "PUCTUATION_OTHER", "OR", "AND",
			"COMPARE_PRECEDENCE_OPERATOR", "ELSE", "POWER", "SEMICOLON",
			"COMMA", "EQUALS", "PRIVATE", "MACRO_DECLARATION", "WHITESPACE",
			"COMMENT", "NUMBER", "ID", "BINARY_OPERATOR", "STRING", "C_B_O",
			"C_B_C", "S_B_O", "S_B_C", "R_B_O", "R_B_C", "OTHER", "LETTER",
			"INT", "A", "E", "P", "L", "R", "S", "T", "I", "V" };
	
	private static final String[] _LITERAL_NAMES = { null, null, null, "'!'",
			"'||'", "'&&'", null, null, "'^'", "';'", "','", "'='", null, null,
			null, null, null, null, "':'", null, "'{'", "'}'", "'['", "']'",
			"'('", "')'" };
	private static final String[] _SYMBOLIC_NAMES = { null,
			"OPERATOR_PRECEDENCE_MULTIPLY", "OPERATOR_PRECEDENCE_ADD",
			"PUCTUATION_OTHER", "OR", "AND", "COMPARE_PRECEDENCE_OPERATOR",
			"ELSE", "POWER", "SEMICOLON", "COMMA", "EQUALS", "PRIVATE",
			"MACRO_DECLARATION", "WHITESPACE", "COMMENT", "NUMBER", "ID",
			"BINARY_OPERATOR", "STRING", "C_B_O", "C_B_C", "S_B_O", "S_B_C",
			"R_B_O", "R_B_C", "OTHER" };
	public static final Vocabulary VOCABULARY = new VocabularyImpl(
			_LITERAL_NAMES, _SYMBOLIC_NAMES);
	
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
	protected List<String> unaryOperators;
	protected List<String> macroNames;
	
	
	public SQFLexer(CharStream input, List<String> binaryOperators,
			List<String> unaryOperators, List<String> macroNames) {
		this(input);
		
		// make operators lowercase
		for (int i = 0; i < binaryOperators.size(); i++) {
			binaryOperators.set(i, binaryOperators.get(i).toLowerCase());
		}
		for (int i = 0; i < unaryOperators.size(); i++) {
			unaryOperators.set(i, unaryOperators.get(i).toLowerCase());
		}
		
		this.binaryOperators = binaryOperators;
		this.unaryOperators = unaryOperators;
		this.macroNames = macroNames;
	}
	
	
	public SQFLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this, _ATN, _decisionToDFA,
				_sharedContextCache);
	}
	
	@Override
	public String getGrammarFileName() {
		return "SQF.g4";
	}
	
	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}
	
	@Override
	public String getSerializedATN() {
		return _serializedATN;
	}
	
	@Override
	public String[] getModeNames() {
		return modeNames;
	}
	
	@Override
	public ATN getATN() {
		return _ATN;
	}
	
	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
			case 16:
				ID_action((RuleContext) _localctx, actionIndex);
				break;
		}
	}
	
	private void ID_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
			case 0:
				
				if (macroNames.contains(getText())) {
					// it's not an ID but a macro name
					setType(SQFParser.MACRO_NAME);
				} else {
					if (binaryOperators.contains(getText().toLowerCase())) {
						// it's not an ID but a binary operator
						setType(SQFParser.BINARY_OPERATOR);
					} else {
						if (unaryOperators.contains(getText().toLowerCase())) {
							// it's not an ID but a unary operator
							setType(SQFParser.UNARY_OPERATOR);
						}
					}
				}
				
				break;
		}
	}
	
	public static final String _serializedATN = "\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\34\u0166\b\1\4\2"
			+ "\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"
			+ "\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"
			+ "\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"
			+ "\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"
			+ " \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\2\3\2\5\2R\n\2\3\3\3"
			+ "\3\3\3\3\3\3\3\3\3\3\3\5\3[\n\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3"
			+ "\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7p\n\7\3\b\3\b\3\b\3\b\3\b\3"
			+ "\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16"
			+ "\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0094"
			+ "\n\16\3\16\7\16\u0097\n\16\f\16\16\16\u009a\13\16\3\16\3\16\3\16\3\16"
			+ "\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00a7\n\16\f\16\16\16\u00aa\13"
			+ "\16\3\16\5\16\u00ad\n\16\3\16\3\16\3\17\6\17\u00b2\n\17\r\17\16\17\u00b3"
			+ "\3\17\3\17\3\20\3\20\3\20\3\20\7\20\u00bc\n\20\f\20\16\20\u00bf\13\20"
			+ "\3\20\5\20\u00c2\n\20\3\20\3\20\3\20\3\20\7\20\u00c8\n\20\f\20\16\20\u00cb"
			+ "\13\20\3\20\3\20\5\20\u00cf\n\20\3\20\3\20\3\21\6\21\u00d4\n\21\r\21\16"
			+ "\21\u00d5\3\21\6\21\u00d9\n\21\r\21\16\21\u00da\3\21\3\21\6\21\u00df\n"
			+ "\21\r\21\16\21\u00e0\3\21\3\21\6\21\u00e5\n\21\r\21\16\21\u00e6\3\21\3"
			+ "\21\3\21\5\21\u00ec\n\21\3\21\3\21\6\21\u00f0\n\21\r\21\16\21\u00f1\5"
			+ "\21\u00f4\n\21\3\21\3\21\5\21\u00f8\n\21\3\21\6\21\u00fb\n\21\r\21\16"
			+ "\21\u00fc\3\21\6\21\u0100\n\21\r\21\16\21\u0101\3\21\3\21\6\21\u0106\n"
			+ "\21\r\21\16\21\u0107\3\21\3\21\6\21\u010c\n\21\r\21\16\21\u010d\3\21\3"
			+ "\21\3\21\5\21\u0113\n\21\3\21\3\21\6\21\u0117\n\21\r\21\16\21\u0118\5"
			+ "\21\u011b\n\21\5\21\u011d\n\21\3\22\3\22\3\22\6\22\u0122\n\22\r\22\16"
			+ "\22\u0123\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\7\24\u012e\n\24\f\24"
			+ "\16\24\u0131\13\24\3\24\3\24\3\24\3\24\3\24\7\24\u0138\n\24\f\24\16\24"
			+ "\u013b\13\24\3\24\5\24\u013e\n\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3"
			+ "\30\3\31\3\31\3\32\3\32\3\33\6\33\u014d\n\33\r\33\16\33\u014e\3\34\3\34"
			+ "\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3"
			+ "&\3&\6\u0098\u00bd\u00c9\u014e\2\'\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"
			+ "\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"
			+ "/\31\61\32\63\33\65\34\67\29\2;\2=\2?\2A\2C\2E\2G\2I\2K\2\3\2\25\5\2\'"
			+ "\',,\61\61\4\2--//\4\2>>@@\3\2\f\f\5\2\13\f\17\17\"\"\3\3\f\f\3\2$$\3"
			+ "\2))\4\2C\\c|\3\2\62;\4\2CCcc\4\2GGgg\4\2RRrr\4\2NNnn\4\2TTtt\4\2UUuu"
			+ "\4\2VVvv\4\2KKkk\4\2XXxx\u018a\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"
			+ "\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"
			+ "\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"
			+ "\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2"
			+ "+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2"
			+ "\3Q\3\2\2\2\5Z\3\2\2\2\7\\\3\2\2\2\t^\3\2\2\2\13a\3\2\2\2\ro\3\2\2\2\17"
			+ "q\3\2\2\2\21v\3\2\2\2\23x\3\2\2\2\25z\3\2\2\2\27|\3\2\2\2\31~\3\2\2\2"
			+ "\33\u00ac\3\2\2\2\35\u00b1\3\2\2\2\37\u00ce\3\2\2\2!\u00f3\3\2\2\2#\u0121"
			+ "\3\2\2\2%\u0127\3\2\2\2\'\u013d\3\2\2\2)\u013f\3\2\2\2+\u0141\3\2\2\2"
			+ "-\u0143\3\2\2\2/\u0145\3\2\2\2\61\u0147\3\2\2\2\63\u0149\3\2\2\2\65\u014c"
			+ "\3\2\2\2\67\u0150\3\2\2\29\u0152\3\2\2\2;\u0154\3\2\2\2=\u0156\3\2\2\2"
			+ "?\u0158\3\2\2\2A\u015a\3\2\2\2C\u015c\3\2\2\2E\u015e\3\2\2\2G\u0160\3"
			+ "\2\2\2I\u0162\3\2\2\2K\u0164\3\2\2\2MR\t\2\2\2NO\7o\2\2OP\7q\2\2PR\7f"
			+ "\2\2QM\3\2\2\2QN\3\2\2\2R\4\3\2\2\2S[\t\3\2\2TU\7o\2\2UV\7k\2\2V[\7p\2"
			+ "\2WX\7o\2\2XY\7c\2\2Y[\7z\2\2ZS\3\2\2\2ZT\3\2\2\2ZW\3\2\2\2[\6\3\2\2\2"
			+ "\\]\7#\2\2]\b\3\2\2\2^_\7~\2\2_`\7~\2\2`\n\3\2\2\2ab\7(\2\2bc\7(\2\2c"
			+ "\f\3\2\2\2de\7?\2\2ep\7?\2\2fg\7#\2\2gp\7?\2\2hp\t\4\2\2ij\7>\2\2jp\7"
			+ "?\2\2kl\7@\2\2lp\7?\2\2mn\7@\2\2np\7@\2\2od\3\2\2\2of\3\2\2\2oh\3\2\2"
			+ "\2oi\3\2\2\2ok\3\2\2\2om\3\2\2\2p\16\3\2\2\2qr\5=\37\2rs\5A!\2st\5E#\2"
			+ "tu\5=\37\2u\20\3\2\2\2vw\7`\2\2w\22\3\2\2\2xy\7=\2\2y\24\3\2\2\2z{\7."
			+ "\2\2{\26\3\2\2\2|}\7?\2\2}\30\3\2\2\2~\177\5? \2\177\u0080\5C\"\2\u0080"
			+ "\u0081\5I%\2\u0081\u0082\5K&\2\u0082\u0083\5;\36\2\u0083\u0084\5G$\2\u0084"
			+ "\u0085\5=\37\2\u0085\32\3\2\2\2\u0086\u0087\7%\2\2\u0087\u0088\7k\2\2"
			+ "\u0088\u0089\7h\2\2\u0089\u008a\7f\2\2\u008a\u008b\7g\2\2\u008b\u0094"
			+ "\7h\2\2\u008c\u008d\7%\2\2\u008d\u008e\7k\2\2\u008e\u008f\7h\2\2\u008f"
			+ "\u0090\7p\2\2\u0090\u0091\7f\2\2\u0091\u0092\7g\2\2\u0092\u0094\7h\2\2"
			+ "\u0093\u0086\3\2\2\2\u0093\u008c\3\2\2\2\u0094\u0098\3\2\2\2\u0095\u0097"
			+ "\13\2\2\2\u0096\u0095\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0099\3\2\2\2"
			+ "\u0098\u0096\3\2\2\2\u0099\u009b\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u009c"
			+ "\7%\2\2\u009c\u009d\7g\2\2\u009d\u009e\7p\2\2\u009e\u009f\7f\2\2\u009f"
			+ "\u00a0\7k\2\2\u00a0\u00ad\7h\2\2\u00a1\u00a8\7%\2\2\u00a2\u00a7\n\5\2"
			+ "\2\u00a3\u00a4\7\"\2\2\u00a4\u00a5\7^\2\2\u00a5\u00a7\7\f\2\2\u00a6\u00a2"
			+ "\3\2\2\2\u00a6\u00a3\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8"
			+ "\u00a9\3\2\2\2\u00a9\u00ab\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ad\7\f"
			+ "\2\2\u00ac\u0093\3\2\2\2\u00ac\u00a1\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"
			+ "\u00af\b\16\2\2\u00af\34\3\2\2\2\u00b0\u00b2\t\6\2\2\u00b1\u00b0\3\2\2"
			+ "\2\u00b2\u00b3\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5"
			+ "\3\2\2\2\u00b5\u00b6\b\17\2\2\u00b6\36\3\2\2\2\u00b7\u00b8\7\61\2\2\u00b8"
			+ "\u00b9\7\61\2\2\u00b9\u00bd\3\2\2\2\u00ba\u00bc\13\2\2\2\u00bb\u00ba\3"
			+ "\2\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00be\3\2\2\2\u00bd\u00bb\3\2\2\2\u00be"
			+ "\u00c1\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0\u00c2\t\7\2\2\u00c1\u00c0\3\2"
			+ "\2\2\u00c2\u00cf\3\2\2\2\u00c3\u00c4\7\61\2\2\u00c4\u00c5\7,\2\2\u00c5"
			+ "\u00c9\3\2\2\2\u00c6\u00c8\13\2\2\2\u00c7\u00c6\3\2\2\2\u00c8\u00cb\3"
			+ "\2\2\2\u00c9\u00ca\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca\u00cc\3\2\2\2\u00cb"
			+ "\u00c9\3\2\2\2\u00cc\u00cd\7,\2\2\u00cd\u00cf\7\61\2\2\u00ce\u00b7\3\2"
			+ "\2\2\u00ce\u00c3\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\b\20\2\2\u00d1"
			+ " \3\2\2\2\u00d2\u00d4\59\35\2\u00d3\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2"
			+ "\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00f4\3\2\2\2\u00d7\u00d9"
			+ "\59\35\2\u00d8\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00d8\3\2\2\2\u00da"
			+ "\u00db\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00de\7\60\2\2\u00dd\u00df\5"
			+ "9\35\2\u00de\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0"
			+ "\u00e1\3\2\2\2\u00e1\u00f4\3\2\2\2\u00e2\u00e4\7\60\2\2\u00e3\u00e5\5"
			+ "9\35\2\u00e4\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6"
			+ "\u00e7\3\2\2\2\u00e7\u00f4\3\2\2\2\u00e8\u00e9\7\62\2\2\u00e9\u00ec\7"
			+ "z\2\2\u00ea\u00ec\7&\2\2\u00eb\u00e8\3\2\2\2\u00eb\u00ea\3\2\2\2\u00ec"
			+ "\u00ef\3\2\2\2\u00ed\u00f0\59\35\2\u00ee\u00f0\5\67\34\2\u00ef\u00ed\3"
			+ "\2\2\2\u00ef\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1"
			+ "\u00f2\3\2\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00d3\3\2\2\2\u00f3\u00d8\3\2"
			+ "\2\2\u00f3\u00e2\3\2\2\2\u00f3\u00eb\3\2\2\2\u00f4\u011c\3\2\2\2\u00f5"
			+ "\u00f7\7g\2\2\u00f6\u00f8\7/\2\2\u00f7\u00f6\3\2\2\2\u00f7\u00f8\3\2\2"
			+ "\2\u00f8\u011a\3\2\2\2\u00f9\u00fb\59\35\2\u00fa\u00f9\3\2\2\2\u00fb\u00fc"
			+ "\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u011b\3\2\2\2\u00fe"
			+ "\u0100\59\35\2\u00ff\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u00ff\3\2"
			+ "\2\2\u0101\u0102\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0105\7\60\2\2\u0104"
			+ "\u0106\59\35\2\u0105\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0105\3\2"
			+ "\2\2\u0107\u0108\3\2\2\2\u0108\u011b\3\2\2\2\u0109\u010b\7\60\2\2\u010a"
			+ "\u010c\59\35\2\u010b\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010b\3\2"
			+ "\2\2\u010d\u010e\3\2\2\2\u010e\u011b\3\2\2\2\u010f\u0110\7\62\2\2\u0110"
			+ "\u0113\7z\2\2\u0111\u0113\7&\2\2\u0112\u010f\3\2\2\2\u0112\u0111\3\2\2"
			+ "\2\u0113\u0116\3\2\2\2\u0114\u0117\59\35\2\u0115\u0117\5\67\34\2\u0116"
			+ "\u0114\3\2\2\2\u0116\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0116\3\2"
			+ "\2\2\u0118\u0119\3\2\2\2\u0119\u011b\3\2\2\2\u011a\u00fa\3\2\2\2\u011a"
			+ "\u00ff\3\2\2\2\u011a\u0109\3\2\2\2\u011a\u0112\3\2\2\2\u011b\u011d\3\2"
			+ "\2\2\u011c\u00f5\3\2\2\2\u011c\u011d\3\2\2\2\u011d\"\3\2\2\2\u011e\u0122"
			+ "\5\67\34\2\u011f\u0122\59\35\2\u0120\u0122\7a\2\2\u0121\u011e\3\2\2\2"
			+ "\u0121\u011f\3\2\2\2\u0121\u0120\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0121"
			+ "\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\b\22\3\2"
			+ "\u0126$\3\2\2\2\u0127\u0128\7<\2\2\u0128&\3\2\2\2\u0129\u012f\7$\2\2\u012a"
			+ "\u012e\n\b\2\2\u012b\u012c\7$\2\2\u012c\u012e\7$\2\2\u012d\u012a\3\2\2"
			+ "\2\u012d\u012b\3\2\2\2\u012e\u0131\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130"
			+ "\3\2\2\2\u0130\u0132\3\2\2\2\u0131\u012f\3\2\2\2\u0132\u013e\7$\2\2\u0133"
			+ "\u0139\7)\2\2\u0134\u0138\n\t\2\2\u0135\u0136\7)\2\2\u0136\u0138\7)\2"
			+ "\2\u0137\u0134\3\2\2\2\u0137\u0135\3\2\2\2\u0138\u013b\3\2\2\2\u0139\u0137"
			+ "\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u013c\3\2\2\2\u013b\u0139\3\2\2\2\u013c"
			+ "\u013e\7)\2\2\u013d\u0129\3\2\2\2\u013d\u0133\3\2\2\2\u013e(\3\2\2\2\u013f"
			+ "\u0140\7}\2\2\u0140*\3\2\2\2\u0141\u0142\7\177\2\2\u0142,\3\2\2\2\u0143"
			+ "\u0144\7]\2\2\u0144.\3\2\2\2\u0145\u0146\7_\2\2\u0146\60\3\2\2\2\u0147"
			+ "\u0148\7*\2\2\u0148\62\3\2\2\2\u0149\u014a\7+\2\2\u014a\64\3\2\2\2\u014b"
			+ "\u014d\13\2\2\2\u014c\u014b\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u014f\3"
			+ "\2\2\2\u014e\u014c\3\2\2\2\u014f\66\3\2\2\2\u0150\u0151\t\n\2\2\u0151"
			+ "8\3\2\2\2\u0152\u0153\t\13\2\2\u0153:\3\2\2\2\u0154\u0155\t\f\2\2\u0155"
			+ "<\3\2\2\2\u0156\u0157\t\r\2\2\u0157>\3\2\2\2\u0158\u0159\t\16\2\2\u0159"
			+ "@\3\2\2\2\u015a\u015b\t\17\2\2\u015bB\3\2\2\2\u015c\u015d\t\20\2\2\u015d"
			+ "D\3\2\2\2\u015e\u015f\t\21\2\2\u015fF\3\2\2\2\u0160\u0161\t\22\2\2\u0161"
			+ "H\3\2\2\2\u0162\u0163\t\23\2\2\u0163J\3\2\2\2\u0164\u0165\t\24\2\2\u0165"
			+ "L\3\2\2\2*\2QZo\u0093\u0098\u00a6\u00a8\u00ac\u00b3\u00bd\u00c1\u00c9"
			+ "\u00ce\u00d5\u00da\u00e0\u00e6\u00eb\u00ef\u00f1\u00f3\u00f7\u00fc\u0101"
			+ "\u0107\u010d\u0112\u0116\u0118\u011a\u011c\u0121\u0123\u012d\u012f\u0137"
			+ "\u0139\u013d\u014e\4\b\2\2\3\22\2";
	public static final ATN _ATN = new ATNDeserializer()
			.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}