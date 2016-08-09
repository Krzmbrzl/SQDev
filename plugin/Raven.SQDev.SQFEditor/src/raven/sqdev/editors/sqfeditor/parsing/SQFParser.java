// Generated from SQF.g4 by ANTLR 4.5.3
package raven.sqdev.editors.sqfeditor.parsing;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SQFParser extends Parser {
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
		ANY=33, BINARY_OPERATOR=34, MACRO_EXPRESSION=35;
	public static final int
		RULE_code = 0, RULE_preprocessor = 1, RULE_statement = 2, RULE_assignment = 3, 
		RULE_binaryExpression = 4, RULE_expression = 5, RULE_unaryExpression = 6, 
		RULE_nularExpression = 7, RULE_macro = 8;
	public static final String[] ruleNames = {
		"code", "preprocessor", "statement", "assignment", "binaryExpression", 
		"expression", "unaryExpression", "nularExpression", "macro"
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
		"LINEBREAK", "WS", "ANY", "BINARY_OPERATOR", "MACRO_EXPRESSION"
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

	@Override
	public String getGrammarFileName() { return "SQF.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SQFParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CodeContext extends ParserRuleContext {
		public List<MacroContext> macro() {
			return getRuleContexts(MacroContext.class);
		}
		public MacroContext macro(int i) {
			return getRuleContext(MacroContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<PreprocessorContext> preprocessor() {
			return getRuleContexts(PreprocessorContext.class);
		}
		public PreprocessorContext preprocessor(int i) {
			return getRuleContext(PreprocessorContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(SQFParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(SQFParser.SEMICOLON, i);
		}
		public TerminalNode EOF() { return getToken(SQFParser.EOF, 0); }
		public CodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitCode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitCode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeContext code() throws RecognitionException {
		CodeContext _localctx = new CodeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_code);
		int _la;
		try {
			setState(32);
			switch (_input.LA(1)) {
			case NOT:
			case ADDITION_OPERATOR:
			case OPENING_ROUND_BRACKET:
			case OPENING_SQUARE_BRACKET:
			case OPENING_CURLY_BRACKET:
			case NUMBER:
			case STRING:
			case ID:
			case PREPRO:
			case BINARY_OPERATOR:
			case MACRO_EXPRESSION:
				enterOuterAlt(_localctx, 1);
				{
				setState(27); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(27);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						setState(18);
						macro();
						setState(20);
						_la = _input.LA(1);
						if (_la==SEMICOLON) {
							{
							setState(19);
							match(SEMICOLON);
							}
						}

						}
						break;
					case 2:
						{
						setState(22);
						statement();
						setState(24);
						_la = _input.LA(1);
						if (_la==SEMICOLON) {
							{
							setState(23);
							match(SEMICOLON);
							}
						}

						}
						break;
					case 3:
						{
						setState(26);
						preprocessor();
						}
						break;
					}
					}
					setState(29); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << ADDITION_OPERATOR) | (1L << OPENING_ROUND_BRACKET) | (1L << OPENING_SQUARE_BRACKET) | (1L << OPENING_CURLY_BRACKET) | (1L << NUMBER) | (1L << STRING) | (1L << ID) | (1L << PREPRO) | (1L << BINARY_OPERATOR) | (1L << MACRO_EXPRESSION))) != 0) );
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(31);
				match(EOF);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PreprocessorContext extends ParserRuleContext {
		public List<TerminalNode> PREPRO() { return getTokens(SQFParser.PREPRO); }
		public TerminalNode PREPRO(int i) {
			return getToken(SQFParser.PREPRO, i);
		}
		public PreprocessorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preprocessor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterPreprocessor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitPreprocessor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitPreprocessor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreprocessorContext preprocessor() throws RecognitionException {
		PreprocessorContext _localctx = new PreprocessorContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_preprocessor);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(35); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(34);
					match(PREPRO);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(37); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(41);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(39);
				assignment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(40);
				binaryExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(SQFParser.EQUALS, 0); }
		public List<TerminalNode> ID() { return getTokens(SQFParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SQFParser.ID, i);
		}
		public List<MacroContext> macro() {
			return getRuleContexts(MacroContext.class);
		}
		public MacroContext macro(int i) {
			return getRuleContext(MacroContext.class,i);
		}
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(43);
				match(ID);
				}
				break;
			}
			setState(48);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(46);
				match(ID);
				}
				break;
			case MACRO_EXPRESSION:
				{
				setState(47);
				macro();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(50);
			match(EQUALS);
			setState(53);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(51);
				binaryExpression();
				}
				break;
			case 2:
				{
				setState(52);
				macro();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode BINARY_OPERATOR() { return getToken(SQFParser.BINARY_OPERATOR, 0); }
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public BinaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterBinaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitBinaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitBinaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryExpressionContext binaryExpression() throws RecognitionException {
		BinaryExpressionContext _localctx = new BinaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_binaryExpression);
		try {
			setState(60);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				expression(0);
				setState(56);
				match(BINARY_OPERATOR);
				setState(57);
				binaryExpression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayContext extends ExpressionContext {
		public TerminalNode OPENING_SQUARE_BRACKET() { return getToken(SQFParser.OPENING_SQUARE_BRACKET, 0); }
		public TerminalNode CLOSING_SQUARE_BRACKET() { return getToken(SQFParser.CLOSING_SQUARE_BRACKET, 0); }
		public List<BinaryExpressionContext> binaryExpression() {
			return getRuleContexts(BinaryExpressionContext.class);
		}
		public BinaryExpressionContext binaryExpression(int i) {
			return getRuleContext(BinaryExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SQFParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SQFParser.COMMA, i);
		}
		public ArrayContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanExpressionContext extends ExpressionContext {
		public TerminalNode NOT() { return getToken(SQFParser.NOT, 0); }
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode COMPARATOR() { return getToken(SQFParser.COMPARATOR, 0); }
		public TerminalNode CONFIG_OPERATOR() { return getToken(SQFParser.CONFIG_OPERATOR, 0); }
		public TerminalNode AND() { return getToken(SQFParser.AND, 0); }
		public TerminalNode OR() { return getToken(SQFParser.OR, 0); }
		public BooleanExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterBooleanExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitBooleanExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitBooleanExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MacroExpressionContext extends ExpressionContext {
		public MacroContext macro() {
			return getRuleContext(MacroContext.class,0);
		}
		public MacroExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterMacroExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitMacroExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitMacroExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ElseExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ELSE() { return getToken(SQFParser.ELSE, 0); }
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public ElseExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterElseExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitElseExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitElseExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ModifyExpressionContext extends ExpressionContext {
		public TerminalNode ADDITION_OPERATOR() { return getToken(SQFParser.ADDITION_OPERATOR, 0); }
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public ModifyExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterModifyExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitModifyExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitModifyExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NularContext extends ExpressionContext {
		public NularExpressionContext nularExpression() {
			return getRuleContext(NularExpressionContext.class,0);
		}
		public NularContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterNular(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitNular(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitNular(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParentheseContext extends ExpressionContext {
		public TerminalNode OPENING_ROUND_BRACKET() { return getToken(SQFParser.OPENING_ROUND_BRACKET, 0); }
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public TerminalNode CLOSING_ROUND_BRACKET() { return getToken(SQFParser.CLOSING_ROUND_BRACKET, 0); }
		public ParentheseContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterParenthese(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitParenthese(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitParenthese(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InlineCodeContext extends ExpressionContext {
		public TerminalNode OPENING_CURLY_BRACKET() { return getToken(SQFParser.OPENING_CURLY_BRACKET, 0); }
		public TerminalNode CLOSING_CURLY_BRACKET() { return getToken(SQFParser.CLOSING_CURLY_BRACKET, 0); }
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public InlineCodeContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterInlineCode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitInlineCode(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitInlineCode(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArithmeticExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode EXP() { return getToken(SQFParser.EXP, 0); }
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public TerminalNode MULTIPLICATION_OPERATOR() { return getToken(SQFParser.MULTIPLICATION_OPERATOR, 0); }
		public TerminalNode MODULO_OPERATOR() { return getToken(SQFParser.MODULO_OPERATOR, 0); }
		public TerminalNode ADDITION_OPERATOR() { return getToken(SQFParser.ADDITION_OPERATOR, 0); }
		public TerminalNode MIN_MAX_OPERATOR() { return getToken(SQFParser.MIN_MAX_OPERATOR, 0); }
		public ArithmeticExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterArithmeticExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitArithmeticExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitArithmeticExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryContext extends ExpressionContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public UnaryContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitUnary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitUnary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				_localctx = new UnaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(63);
				unaryExpression();
				}
				break;
			case 2:
				{
				_localctx = new NularContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(64);
				nularExpression();
				}
				break;
			case 3:
				{
				_localctx = new ParentheseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(65);
				match(OPENING_ROUND_BRACKET);
				setState(66);
				binaryExpression();
				setState(67);
				match(CLOSING_ROUND_BRACKET);
				}
				break;
			case 4:
				{
				_localctx = new InlineCodeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(69);
				match(OPENING_CURLY_BRACKET);
				setState(71);
				_la = _input.LA(1);
				if (((((_la - -1)) & ~0x3f) == 0 && ((1L << (_la - -1)) & ((1L << (EOF - -1)) | (1L << (NOT - -1)) | (1L << (ADDITION_OPERATOR - -1)) | (1L << (OPENING_ROUND_BRACKET - -1)) | (1L << (OPENING_SQUARE_BRACKET - -1)) | (1L << (OPENING_CURLY_BRACKET - -1)) | (1L << (NUMBER - -1)) | (1L << (STRING - -1)) | (1L << (ID - -1)) | (1L << (PREPRO - -1)) | (1L << (BINARY_OPERATOR - -1)) | (1L << (MACRO_EXPRESSION - -1)))) != 0)) {
					{
					setState(70);
					code();
					}
				}

				setState(73);
				match(CLOSING_CURLY_BRACKET);
				}
				break;
			case 5:
				{
				_localctx = new ArrayContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(74);
				match(OPENING_SQUARE_BRACKET);
				setState(83);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << ADDITION_OPERATOR) | (1L << OPENING_ROUND_BRACKET) | (1L << OPENING_SQUARE_BRACKET) | (1L << OPENING_CURLY_BRACKET) | (1L << NUMBER) | (1L << STRING) | (1L << ID) | (1L << BINARY_OPERATOR) | (1L << MACRO_EXPRESSION))) != 0)) {
					{
					setState(75);
					binaryExpression();
					setState(80);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(76);
						match(COMMA);
						setState(77);
						binaryExpression();
						}
						}
						setState(82);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(85);
				match(CLOSING_SQUARE_BRACKET);
				}
				break;
			case 6:
				{
				_localctx = new BooleanExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(86);
				match(NOT);
				setState(87);
				binaryExpression();
				}
				break;
			case 7:
				{
				_localctx = new ModifyExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(88);
				match(ADDITION_OPERATOR);
				setState(89);
				binaryExpression();
				}
				break;
			case 8:
				{
				_localctx = new MacroExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(90);
				macro();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(116);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(114);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(93);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(94);
						match(EXP);
						setState(95);
						binaryExpression();
						}
						break;
					case 2:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(96);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(97);
						_la = _input.LA(1);
						if ( !(_la==MULTIPLICATION_OPERATOR || _la==MODULO_OPERATOR) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(98);
						binaryExpression();
						}
						break;
					case 3:
						{
						_localctx = new ArithmeticExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(99);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(100);
						_la = _input.LA(1);
						if ( !(_la==ADDITION_OPERATOR || _la==MIN_MAX_OPERATOR) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(101);
						binaryExpression();
						}
						break;
					case 4:
						{
						_localctx = new ElseExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(102);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(103);
						match(ELSE);
						setState(104);
						binaryExpression();
						}
						break;
					case 5:
						{
						_localctx = new BooleanExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(105);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(106);
						_la = _input.LA(1);
						if ( !(_la==COMPARATOR || _la==CONFIG_OPERATOR) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(107);
						binaryExpression();
						}
						break;
					case 6:
						{
						_localctx = new BooleanExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(108);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(109);
						match(AND);
						setState(110);
						binaryExpression();
						}
						break;
					case 7:
						{
						_localctx = new BooleanExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(111);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(112);
						match(OR);
						setState(113);
						binaryExpression();
						}
						break;
					}
					} 
				}
				setState(118);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class UnaryExpressionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SQFParser.ID, 0); }
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public TerminalNode BINARY_OPERATOR() { return getToken(SQFParser.BINARY_OPERATOR, 0); }
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitUnaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitUnaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_unaryExpression);
		try {
			setState(123);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				match(ID);
				setState(120);
				binaryExpression();
				}
				break;
			case BINARY_OPERATOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(121);
				match(BINARY_OPERATOR);
				setState(122);
				binaryExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NularExpressionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SQFParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(SQFParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(SQFParser.STRING, 0); }
		public NularExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nularExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterNularExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitNularExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitNularExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NularExpressionContext nularExpression() throws RecognitionException {
		NularExpressionContext _localctx = new NularExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_nularExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER) | (1L << STRING) | (1L << ID))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MacroContext extends ParserRuleContext {
		public TerminalNode MACRO_EXPRESSION() { return getToken(SQFParser.MACRO_EXPRESSION, 0); }
		public List<TerminalNode> OPENING_ROUND_BRACKET() { return getTokens(SQFParser.OPENING_ROUND_BRACKET); }
		public TerminalNode OPENING_ROUND_BRACKET(int i) {
			return getToken(SQFParser.OPENING_ROUND_BRACKET, i);
		}
		public List<TerminalNode> CLOSING_ROUND_BRACKET() { return getTokens(SQFParser.CLOSING_ROUND_BRACKET); }
		public TerminalNode CLOSING_ROUND_BRACKET(int i) {
			return getToken(SQFParser.CLOSING_ROUND_BRACKET, i);
		}
		public MacroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterMacro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitMacro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitMacro(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MacroContext macro() throws RecognitionException {
		MacroContext _localctx = new MacroContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_macro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(MACRO_EXPRESSION);
			setState(136);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(128);
				match(OPENING_ROUND_BRACKET);
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << SEMICOLON) | (1L << EQUALS) | (1L << ELSE) | (1L << OR) | (1L << AND) | (1L << NOT) | (1L << EXP) | (1L << COMPARATOR) | (1L << CONFIG_OPERATOR) | (1L << ADDITION_OPERATOR) | (1L << MIN_MAX_OPERATOR) | (1L << MULTIPLICATION_OPERATOR) | (1L << MODULO_OPERATOR) | (1L << OPENING_SQUARE_BRACKET) | (1L << CLOSING_SQUARE_BRACKET) | (1L << OPENING_CURLY_BRACKET) | (1L << CLOSING_CURLY_BRACKET) | (1L << NUMBER) | (1L << STRING) | (1L << ID) | (1L << PREPRO) | (1L << IFDEF) | (1L << IFNDEF) | (1L << PRE_ELSE) | (1L << ENDIF) | (1L << DOC) | (1L << COMMENT) | (1L << LINEBREAK) | (1L << WS) | (1L << ANY) | (1L << BINARY_OPERATOR) | (1L << MACRO_EXPRESSION))) != 0)) {
					{
					{
					setState(129);
					_la = _input.LA(1);
					if ( _la <= 0 || (_la==OPENING_ROUND_BRACKET || _la==CLOSING_ROUND_BRACKET) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					setState(134);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(135);
				match(CLOSING_ROUND_BRACKET);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 15);
		case 1:
			return precpred(_ctx, 14);
		case 2:
			return precpred(_ctx, 13);
		case 3:
			return precpred(_ctx, 12);
		case 4:
			return precpred(_ctx, 11);
		case 5:
			return precpred(_ctx, 10);
		case 6:
			return precpred(_ctx, 9);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3%\u008d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2"+
		"\5\2\27\n\2\3\2\3\2\5\2\33\n\2\3\2\6\2\36\n\2\r\2\16\2\37\3\2\5\2#\n\2"+
		"\3\3\6\3&\n\3\r\3\16\3\'\3\4\3\4\5\4,\n\4\3\5\5\5/\n\5\3\5\3\5\5\5\63"+
		"\n\5\3\5\3\5\3\5\5\58\n\5\3\6\3\6\3\6\3\6\3\6\5\6?\n\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\5\7J\n\7\3\7\3\7\3\7\3\7\3\7\7\7Q\n\7\f\7\16\7T\13"+
		"\7\5\7V\n\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7^\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7u\n\7\f"+
		"\7\16\7x\13\7\3\b\3\b\3\b\3\b\5\b~\n\b\3\t\3\t\3\n\3\n\3\n\7\n\u0085\n"+
		"\n\f\n\16\n\u0088\13\n\3\n\5\n\u008b\n\n\3\n\2\3\f\13\2\4\6\b\n\f\16\20"+
		"\22\2\7\3\2\17\20\3\2\r\16\3\2\13\f\3\2\27\31\3\2\21\22\u00a3\2\"\3\2"+
		"\2\2\4%\3\2\2\2\6+\3\2\2\2\b.\3\2\2\2\n>\3\2\2\2\f]\3\2\2\2\16}\3\2\2"+
		"\2\20\177\3\2\2\2\22\u0081\3\2\2\2\24\26\5\22\n\2\25\27\7\4\2\2\26\25"+
		"\3\2\2\2\26\27\3\2\2\2\27\36\3\2\2\2\30\32\5\6\4\2\31\33\7\4\2\2\32\31"+
		"\3\2\2\2\32\33\3\2\2\2\33\36\3\2\2\2\34\36\5\4\3\2\35\24\3\2\2\2\35\30"+
		"\3\2\2\2\35\34\3\2\2\2\36\37\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 #\3\2\2"+
		"\2!#\7\2\2\3\"\35\3\2\2\2\"!\3\2\2\2#\3\3\2\2\2$&\7\32\2\2%$\3\2\2\2&"+
		"\'\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(\5\3\2\2\2),\5\b\5\2*,\5\n\6\2+)\3\2"+
		"\2\2+*\3\2\2\2,\7\3\2\2\2-/\7\31\2\2.-\3\2\2\2./\3\2\2\2/\62\3\2\2\2\60"+
		"\63\7\31\2\2\61\63\5\22\n\2\62\60\3\2\2\2\62\61\3\2\2\2\63\64\3\2\2\2"+
		"\64\67\7\5\2\2\658\5\n\6\2\668\5\22\n\2\67\65\3\2\2\2\67\66\3\2\2\28\t"+
		"\3\2\2\29:\5\f\7\2:;\7$\2\2;<\5\n\6\2<?\3\2\2\2=?\5\f\7\2>9\3\2\2\2>="+
		"\3\2\2\2?\13\3\2\2\2@A\b\7\1\2A^\5\16\b\2B^\5\20\t\2CD\7\21\2\2DE\5\n"+
		"\6\2EF\7\22\2\2F^\3\2\2\2GI\7\25\2\2HJ\5\2\2\2IH\3\2\2\2IJ\3\2\2\2JK\3"+
		"\2\2\2K^\7\26\2\2LU\7\23\2\2MR\5\n\6\2NO\7\3\2\2OQ\5\n\6\2PN\3\2\2\2Q"+
		"T\3\2\2\2RP\3\2\2\2RS\3\2\2\2SV\3\2\2\2TR\3\2\2\2UM\3\2\2\2UV\3\2\2\2"+
		"VW\3\2\2\2W^\7\24\2\2XY\7\t\2\2Y^\5\n\6\2Z[\7\r\2\2[^\5\n\6\2\\^\5\22"+
		"\n\2]@\3\2\2\2]B\3\2\2\2]C\3\2\2\2]G\3\2\2\2]L\3\2\2\2]X\3\2\2\2]Z\3\2"+
		"\2\2]\\\3\2\2\2^v\3\2\2\2_`\f\21\2\2`a\7\n\2\2au\5\n\6\2bc\f\20\2\2cd"+
		"\t\2\2\2du\5\n\6\2ef\f\17\2\2fg\t\3\2\2gu\5\n\6\2hi\f\16\2\2ij\7\6\2\2"+
		"ju\5\n\6\2kl\f\r\2\2lm\t\4\2\2mu\5\n\6\2no\f\f\2\2op\7\b\2\2pu\5\n\6\2"+
		"qr\f\13\2\2rs\7\7\2\2su\5\n\6\2t_\3\2\2\2tb\3\2\2\2te\3\2\2\2th\3\2\2"+
		"\2tk\3\2\2\2tn\3\2\2\2tq\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2w\r\3\2"+
		"\2\2xv\3\2\2\2yz\7\31\2\2z~\5\n\6\2{|\7$\2\2|~\5\n\6\2}y\3\2\2\2}{\3\2"+
		"\2\2~\17\3\2\2\2\177\u0080\t\5\2\2\u0080\21\3\2\2\2\u0081\u008a\7%\2\2"+
		"\u0082\u0086\7\21\2\2\u0083\u0085\n\6\2\2\u0084\u0083\3\2\2\2\u0085\u0088"+
		"\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0089\3\2\2\2\u0088"+
		"\u0086\3\2\2\2\u0089\u008b\7\22\2\2\u008a\u0082\3\2\2\2\u008a\u008b\3"+
		"\2\2\2\u008b\23\3\2\2\2\26\26\32\35\37\"\'+.\62\67>IRU]tv}\u0086\u008a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}