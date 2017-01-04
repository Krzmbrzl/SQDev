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
		OPERATOR_PRECEDENCE_MULTIPLY=1, OPERATOR_PRECEDENCE_ADD=2, PUCTUATION_OTHER=3, 
		OR=4, AND=5, COMPARE_PRECEDENCE_OPERATOR=6, ELSE=7, POWER=8, SEMICOLON=9, 
		COMMA=10, EQUALS=11, PRIVATE=12, MACRO_DECLARATION=13, WHITESPACE=14, 
		COMMENT=15, NUMBER=16, ID=17, BINARY_OPERATOR=18, STRING=19, C_B_O=20, 
		C_B_C=21, S_B_O=22, S_B_C=23, R_B_O=24, R_B_C=25, OTHER=26, MACRO_NAME=27;
	public static final int
		RULE_code = 0, RULE_macro = 1, RULE_macroArgument = 2, RULE_statement = 3, 
		RULE_assignment = 4, RULE_binaryExpression = 5, RULE_primaryExpression = 6, 
		RULE_commonError = 7, RULE_nularExpression = 8, RULE_unaryExpression = 9, 
		RULE_operator = 10, RULE_punctuation = 11;
	public static final String[] ruleNames = {
		"code", "macro", "macroArgument", "statement", "assignment", "binaryExpression", 
		"primaryExpression", "commonError", "nularExpression", "unaryExpression", 
		"operator", "punctuation"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, "'!'", "'||'", "'&&'", null, null, "'^'", "';'", "','", 
		"'='", null, null, null, null, null, null, "':'", null, "'{'", "'}'", 
		"'['", "']'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "OPERATOR_PRECEDENCE_MULTIPLY", "OPERATOR_PRECEDENCE_ADD", "PUCTUATION_OTHER", 
		"OR", "AND", "COMPARE_PRECEDENCE_OPERATOR", "ELSE", "POWER", "SEMICOLON", 
		"COMMA", "EQUALS", "PRIVATE", "MACRO_DECLARATION", "WHITESPACE", "COMMENT", 
		"NUMBER", "ID", "BINARY_OPERATOR", "STRING", "C_B_O", "C_B_C", "S_B_O", 
		"S_B_C", "R_B_O", "R_B_C", "OTHER", "MACRO_NAME"
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
		public Token semicolon;
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
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
		try {
			int _alt;
			setState(33);
			switch (_input.LA(1)) {
			case OPERATOR_PRECEDENCE_ADD:
			case PUCTUATION_OTHER:
			case SEMICOLON:
			case PRIVATE:
			case NUMBER:
			case ID:
			case BINARY_OPERATOR:
			case STRING:
			case C_B_O:
			case S_B_O:
			case R_B_O:
			case MACRO_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(28); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(24);
						statement();
						setState(26);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
						case 1:
							{
							setState(25);
							((CodeContext)_localctx).semicolon = match(SEMICOLON);
							}
							break;
						}
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(30); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
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

	public static class MacroContext extends ParserRuleContext {
		public TerminalNode MACRO_NAME() { return getToken(SQFParser.MACRO_NAME, 0); }
		public TerminalNode R_B_O() { return getToken(SQFParser.R_B_O, 0); }
		public List<MacroArgumentContext> macroArgument() {
			return getRuleContexts(MacroArgumentContext.class);
		}
		public MacroArgumentContext macroArgument(int i) {
			return getRuleContext(MacroArgumentContext.class,i);
		}
		public TerminalNode R_B_C() { return getToken(SQFParser.R_B_C, 0); }
		public List<TerminalNode> COMMA() { return getTokens(SQFParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SQFParser.COMMA, i);
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
		enterRule(_localctx, 2, RULE_macro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(MACRO_NAME);
			setState(47);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(36);
				match(R_B_O);
				setState(37);
				macroArgument();
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(38);
					match(COMMA);
					setState(39);
					macroArgument();
					}
					}
					setState(44);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(45);
				match(R_B_C);
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

	public static class MacroArgumentContext extends ParserRuleContext {
		public List<TerminalNode> R_B_O() { return getTokens(SQFParser.R_B_O); }
		public TerminalNode R_B_O(int i) {
			return getToken(SQFParser.R_B_O, i);
		}
		public List<MacroArgumentContext> macroArgument() {
			return getRuleContexts(MacroArgumentContext.class);
		}
		public MacroArgumentContext macroArgument(int i) {
			return getRuleContext(MacroArgumentContext.class,i);
		}
		public List<TerminalNode> R_B_C() { return getTokens(SQFParser.R_B_C); }
		public TerminalNode R_B_C(int i) {
			return getToken(SQFParser.R_B_C, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SQFParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SQFParser.COMMA, i);
		}
		public MacroArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macroArgument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterMacroArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitMacroArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitMacroArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MacroArgumentContext macroArgument() throws RecognitionException {
		MacroArgumentContext _localctx = new MacroArgumentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_macroArgument);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					setState(54);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						setState(49);
						match(R_B_O);
						setState(50);
						macroArgument();
						setState(51);
						match(R_B_C);
						}
						break;
					case 2:
						{
						setState(53);
						_la = _input.LA(1);
						if ( _la <= 0 || (_la==COMMA || _la==R_B_C) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(58);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
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

	public static class StatementContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(SQFParser.SEMICOLON, 0); }
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
		enterRule(_localctx, 6, RULE_statement);
		try {
			setState(62);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				assignment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				binaryExpression(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(61);
				match(SEMICOLON);
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
		public TerminalNode ID() { return getToken(SQFParser.ID, 0); }
		public List<MacroContext> macro() {
			return getRuleContexts(MacroContext.class);
		}
		public MacroContext macro(int i) {
			return getRuleContext(MacroContext.class,i);
		}
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public TerminalNode PRIVATE() { return getToken(SQFParser.PRIVATE, 0); }
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
		enterRule(_localctx, 8, RULE_assignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			_la = _input.LA(1);
			if (_la==PRIVATE) {
				{
				setState(64);
				match(PRIVATE);
				}
			}

			setState(69);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(67);
				match(ID);
				}
				break;
			case MACRO_NAME:
				{
				setState(68);
				macro();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(71);
			match(EQUALS);
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(72);
				binaryExpression(0);
				}
				break;
			case 2:
				{
				setState(73);
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
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public TerminalNode POWER() { return getToken(SQFParser.POWER, 0); }
		public List<BinaryExpressionContext> binaryExpression() {
			return getRuleContexts(BinaryExpressionContext.class);
		}
		public BinaryExpressionContext binaryExpression(int i) {
			return getRuleContext(BinaryExpressionContext.class,i);
		}
		public TerminalNode OPERATOR_PRECEDENCE_MULTIPLY() { return getToken(SQFParser.OPERATOR_PRECEDENCE_MULTIPLY, 0); }
		public TerminalNode OPERATOR_PRECEDENCE_ADD() { return getToken(SQFParser.OPERATOR_PRECEDENCE_ADD, 0); }
		public TerminalNode BINARY_OPERATOR() { return getToken(SQFParser.BINARY_OPERATOR, 0); }
		public TerminalNode COMPARE_PRECEDENCE_OPERATOR() { return getToken(SQFParser.COMPARE_PRECEDENCE_OPERATOR, 0); }
		public TerminalNode AND() { return getToken(SQFParser.AND, 0); }
		public TerminalNode OR() { return getToken(SQFParser.OR, 0); }
		public TerminalNode ELSE() { return getToken(SQFParser.ELSE, 0); }
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
		return binaryExpression(0);
	}

	private BinaryExpressionContext binaryExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BinaryExpressionContext _localctx = new BinaryExpressionContext(_ctx, _parentState);
		BinaryExpressionContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_binaryExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(77);
				primaryExpression();
				setState(78);
				match(POWER);
				setState(79);
				binaryExpression(9);
				}
				break;
			case 2:
				{
				setState(81);
				primaryExpression();
				setState(82);
				match(OPERATOR_PRECEDENCE_MULTIPLY);
				setState(83);
				binaryExpression(8);
				}
				break;
			case 3:
				{
				setState(85);
				primaryExpression();
				setState(86);
				match(OPERATOR_PRECEDENCE_ADD);
				setState(87);
				binaryExpression(7);
				}
				break;
			case 4:
				{
				setState(89);
				primaryExpression();
				setState(90);
				match(BINARY_OPERATOR);
				setState(91);
				binaryExpression(5);
				}
				break;
			case 5:
				{
				setState(93);
				primaryExpression();
				setState(94);
				match(COMPARE_PRECEDENCE_OPERATOR);
				setState(95);
				binaryExpression(4);
				}
				break;
			case 6:
				{
				setState(97);
				primaryExpression();
				setState(98);
				match(AND);
				setState(99);
				binaryExpression(3);
				}
				break;
			case 7:
				{
				setState(101);
				primaryExpression();
				setState(102);
				match(OR);
				setState(103);
				binaryExpression(2);
				}
				break;
			case 8:
				{
				setState(105);
				primaryExpression();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(113);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BinaryExpressionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
					setState(108);
					if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
					setState(109);
					match(ELSE);
					setState(110);
					binaryExpression(7);
					}
					} 
				}
				setState(115);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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

	public static class PrimaryExpressionContext extends ParserRuleContext {
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
	 
		public PrimaryExpressionContext() { }
		public void copyFrom(PrimaryExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NularOperatorContext extends PrimaryExpressionContext {
		public NularExpressionContext nularExpression() {
			return getRuleContext(NularExpressionContext.class,0);
		}
		public NularOperatorContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterNularOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitNularOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitNularOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayContext extends PrimaryExpressionContext {
		public TerminalNode S_B_O() { return getToken(SQFParser.S_B_O, 0); }
		public TerminalNode S_B_C() { return getToken(SQFParser.S_B_C, 0); }
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
		public ArrayContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
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
	public static class ParenthesisContext extends PrimaryExpressionContext {
		public TerminalNode R_B_O() { return getToken(SQFParser.R_B_O, 0); }
		public TerminalNode R_B_C() { return getToken(SQFParser.R_B_C, 0); }
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public ParenthesisContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterParenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitParenthesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitParenthesis(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryOperatorContext extends PrimaryExpressionContext {
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public UnaryOperatorContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterUnaryOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitUnaryOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitUnaryOperator(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberContext extends PrimaryExpressionContext {
		public TerminalNode NUMBER() { return getToken(SQFParser.NUMBER, 0); }
		public NumberContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MacroExpressionContext extends PrimaryExpressionContext {
		public MacroContext macro() {
			return getRuleContext(MacroContext.class,0);
		}
		public MacroExpressionContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
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
	public static class ErrorContext extends PrimaryExpressionContext {
		public CommonErrorContext commonError() {
			return getRuleContext(CommonErrorContext.class,0);
		}
		public ErrorContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitError(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitError(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringContext extends PrimaryExpressionContext {
		public TerminalNode STRING() { return getToken(SQFParser.STRING, 0); }
		public StringContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InlineCodeContext extends PrimaryExpressionContext {
		public TerminalNode C_B_O() { return getToken(SQFParser.C_B_O, 0); }
		public TerminalNode C_B_C() { return getToken(SQFParser.C_B_C, 0); }
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public InlineCodeContext(PrimaryExpressionContext ctx) { copyFrom(ctx); }
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

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_primaryExpression);
		int _la;
		try {
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new MacroExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(116);
				macro();
				}
				break;
			case 2:
				_localctx = new UnaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(117);
				unaryExpression();
				}
				break;
			case 3:
				_localctx = new NularOperatorContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(118);
				nularExpression();
				}
				break;
			case 4:
				_localctx = new NumberContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(119);
				match(NUMBER);
				}
				break;
			case 5:
				_localctx = new StringContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(120);
				match(STRING);
				}
				break;
			case 6:
				_localctx = new InlineCodeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(121);
				match(C_B_O);
				setState(123);
				_la = _input.LA(1);
				if (((((_la - -1)) & ~0x3f) == 0 && ((1L << (_la - -1)) & ((1L << (EOF - -1)) | (1L << (OPERATOR_PRECEDENCE_ADD - -1)) | (1L << (PUCTUATION_OTHER - -1)) | (1L << (SEMICOLON - -1)) | (1L << (PRIVATE - -1)) | (1L << (NUMBER - -1)) | (1L << (ID - -1)) | (1L << (BINARY_OPERATOR - -1)) | (1L << (STRING - -1)) | (1L << (C_B_O - -1)) | (1L << (S_B_O - -1)) | (1L << (R_B_O - -1)) | (1L << (MACRO_NAME - -1)))) != 0)) {
					{
					setState(122);
					code();
					}
				}

				setState(125);
				match(C_B_C);
				}
				break;
			case 7:
				_localctx = new ArrayContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(126);
				match(S_B_O);
				setState(135);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPERATOR_PRECEDENCE_ADD) | (1L << PUCTUATION_OTHER) | (1L << PRIVATE) | (1L << NUMBER) | (1L << ID) | (1L << BINARY_OPERATOR) | (1L << STRING) | (1L << C_B_O) | (1L << S_B_O) | (1L << R_B_O) | (1L << MACRO_NAME))) != 0)) {
					{
					setState(127);
					binaryExpression(0);
					setState(132);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(128);
						match(COMMA);
						setState(129);
						binaryExpression(0);
						}
						}
						setState(134);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(137);
				match(S_B_C);
				}
				break;
			case 8:
				_localctx = new ParenthesisContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(138);
				match(R_B_O);
				setState(140);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPERATOR_PRECEDENCE_ADD) | (1L << PUCTUATION_OTHER) | (1L << PRIVATE) | (1L << NUMBER) | (1L << ID) | (1L << BINARY_OPERATOR) | (1L << STRING) | (1L << C_B_O) | (1L << S_B_O) | (1L << R_B_O) | (1L << MACRO_NAME))) != 0)) {
					{
					setState(139);
					binaryExpression(0);
					}
				}

				setState(142);
				match(R_B_C);
				}
				break;
			case 9:
				_localctx = new ErrorContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(143);
				commonError();
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

	public static class CommonErrorContext extends ParserRuleContext {
		public TerminalNode C_B_O() { return getToken(SQFParser.C_B_O, 0); }
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public List<TerminalNode> C_B_C() { return getTokens(SQFParser.C_B_C); }
		public TerminalNode C_B_C(int i) {
			return getToken(SQFParser.C_B_C, i);
		}
		public TerminalNode S_B_O() { return getToken(SQFParser.S_B_O, 0); }
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public List<TerminalNode> S_B_C() { return getTokens(SQFParser.S_B_C); }
		public TerminalNode S_B_C(int i) {
			return getToken(SQFParser.S_B_C, i);
		}
		public TerminalNode R_B_O() { return getToken(SQFParser.R_B_O, 0); }
		public List<TerminalNode> R_B_C() { return getTokens(SQFParser.R_B_C); }
		public TerminalNode R_B_C(int i) {
			return getToken(SQFParser.R_B_C, i);
		}
		public CommonErrorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commonError; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterCommonError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitCommonError(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitCommonError(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommonErrorContext commonError() throws RecognitionException {
		CommonErrorContext _localctx = new CommonErrorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_commonError);
		int _la;
		try {
			setState(182);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(146);
				match(C_B_O);
				setState(148);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(147);
					code();
					}
					break;
				}
				notifyErrorListeners("Missing closing '}'");
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				match(C_B_O);
				setState(153);
				_la = _input.LA(1);
				if (((((_la - -1)) & ~0x3f) == 0 && ((1L << (_la - -1)) & ((1L << (EOF - -1)) | (1L << (OPERATOR_PRECEDENCE_ADD - -1)) | (1L << (PUCTUATION_OTHER - -1)) | (1L << (SEMICOLON - -1)) | (1L << (PRIVATE - -1)) | (1L << (NUMBER - -1)) | (1L << (ID - -1)) | (1L << (BINARY_OPERATOR - -1)) | (1L << (STRING - -1)) | (1L << (C_B_O - -1)) | (1L << (S_B_O - -1)) | (1L << (R_B_O - -1)) | (1L << (MACRO_NAME - -1)))) != 0)) {
					{
					setState(152);
					code();
					}
				}

				setState(155);
				match(C_B_C);
				setState(156);
				match(C_B_C);
				notifyErrorListeners("Too many curly brackets!");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(158);
				match(S_B_O);
				setState(160);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(159);
					binaryExpression(0);
					}
					break;
				}
				notifyErrorListeners("Missing closing ']'");
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(163);
				match(S_B_O);
				setState(165);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPERATOR_PRECEDENCE_ADD) | (1L << PUCTUATION_OTHER) | (1L << PRIVATE) | (1L << NUMBER) | (1L << ID) | (1L << BINARY_OPERATOR) | (1L << STRING) | (1L << C_B_O) | (1L << S_B_O) | (1L << R_B_O) | (1L << MACRO_NAME))) != 0)) {
					{
					setState(164);
					binaryExpression(0);
					}
				}

				setState(167);
				match(S_B_C);
				setState(168);
				match(S_B_C);
				notifyErrorListeners("Too many square brackets!");
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(170);
				match(R_B_O);
				setState(172);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(171);
					binaryExpression(0);
					}
					break;
				}
				notifyErrorListeners("Missing closing ')'");
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(175);
				match(R_B_O);
				setState(177);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPERATOR_PRECEDENCE_ADD) | (1L << PUCTUATION_OTHER) | (1L << PRIVATE) | (1L << NUMBER) | (1L << ID) | (1L << BINARY_OPERATOR) | (1L << STRING) | (1L << C_B_O) | (1L << S_B_O) | (1L << R_B_O) | (1L << MACRO_NAME))) != 0)) {
					{
					setState(176);
					binaryExpression(0);
					}
				}

				setState(179);
				match(R_B_C);
				setState(180);
				match(R_B_C);
				notifyErrorListeners("Too many parentheses!");
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

	public static class NularExpressionContext extends ParserRuleContext {
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
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
		enterRule(_localctx, 16, RULE_nularExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			operator();
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

	public static class UnaryExpressionContext extends ParserRuleContext {
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public TerminalNode BINARY_OPERATOR() { return getToken(SQFParser.BINARY_OPERATOR, 0); }
		public TerminalNode PRIVATE() { return getToken(SQFParser.PRIVATE, 0); }
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
		enterRule(_localctx, 18, RULE_unaryExpression);
		try {
			setState(193);
			switch (_input.LA(1)) {
			case OPERATOR_PRECEDENCE_ADD:
			case PUCTUATION_OTHER:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(186);
				operator();
				setState(187);
				primaryExpression();
				}
				break;
			case BINARY_OPERATOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				match(BINARY_OPERATOR);
				setState(190);
				primaryExpression();
				}
				break;
			case PRIVATE:
				enterOuterAlt(_localctx, 3);
				{
				setState(191);
				match(PRIVATE);
				setState(192);
				primaryExpression();
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

	public static class OperatorContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SQFParser.ID, 0); }
		public PunctuationContext punctuation() {
			return getRuleContext(PunctuationContext.class,0);
		}
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_operator);
		try {
			setState(197);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				match(ID);
				}
				break;
			case OPERATOR_PRECEDENCE_ADD:
			case PUCTUATION_OTHER:
				enterOuterAlt(_localctx, 2);
				{
				setState(196);
				punctuation();
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

	public static class PunctuationContext extends ParserRuleContext {
		public TerminalNode OPERATOR_PRECEDENCE_ADD() { return getToken(SQFParser.OPERATOR_PRECEDENCE_ADD, 0); }
		public TerminalNode PUCTUATION_OTHER() { return getToken(SQFParser.PUCTUATION_OTHER, 0); }
		public PunctuationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_punctuation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterPunctuation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitPunctuation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitPunctuation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PunctuationContext punctuation() throws RecognitionException {
		PunctuationContext _localctx = new PunctuationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_punctuation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			_la = _input.LA(1);
			if ( !(_la==OPERATOR_PRECEDENCE_ADD || _la==PUCTUATION_OTHER) ) {
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return binaryExpression_sempred((BinaryExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean binaryExpression_sempred(BinaryExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\35\u00cc\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\3\2\3\2\5\2\35\n\2\6\2\37\n\2\r\2\16\2 \3\2\5\2"+
		"$\n\2\3\3\3\3\3\3\3\3\3\3\7\3+\n\3\f\3\16\3.\13\3\3\3\3\3\5\3\62\n\3\3"+
		"\4\3\4\3\4\3\4\3\4\7\49\n\4\f\4\16\4<\13\4\3\5\3\5\3\5\5\5A\n\5\3\6\5"+
		"\6D\n\6\3\6\3\6\5\6H\n\6\3\6\3\6\3\6\5\6M\n\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7m\n\7\3\7\3\7\3\7\7\7r\n\7\f\7\16\7u\13"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b~\n\b\3\b\3\b\3\b\3\b\3\b\7\b\u0085"+
		"\n\b\f\b\16\b\u0088\13\b\5\b\u008a\n\b\3\b\3\b\3\b\5\b\u008f\n\b\3\b\3"+
		"\b\5\b\u0093\n\b\3\t\3\t\5\t\u0097\n\t\3\t\3\t\3\t\5\t\u009c\n\t\3\t\3"+
		"\t\3\t\3\t\3\t\5\t\u00a3\n\t\3\t\3\t\3\t\5\t\u00a8\n\t\3\t\3\t\3\t\3\t"+
		"\3\t\5\t\u00af\n\t\3\t\3\t\3\t\5\t\u00b4\n\t\3\t\3\t\3\t\5\t\u00b9\n\t"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00c4\n\13\3\f\3\f\5"+
		"\f\u00c8\n\f\3\r\3\r\3\r\3:\3\f\16\2\4\6\b\n\f\16\20\22\24\26\30\2\4\4"+
		"\2\f\f\33\33\3\2\4\5\u00ed\2#\3\2\2\2\4%\3\2\2\2\6:\3\2\2\2\b@\3\2\2\2"+
		"\nC\3\2\2\2\fl\3\2\2\2\16\u0092\3\2\2\2\20\u00b8\3\2\2\2\22\u00ba\3\2"+
		"\2\2\24\u00c3\3\2\2\2\26\u00c7\3\2\2\2\30\u00c9\3\2\2\2\32\34\5\b\5\2"+
		"\33\35\7\13\2\2\34\33\3\2\2\2\34\35\3\2\2\2\35\37\3\2\2\2\36\32\3\2\2"+
		"\2\37 \3\2\2\2 \36\3\2\2\2 !\3\2\2\2!$\3\2\2\2\"$\7\2\2\3#\36\3\2\2\2"+
		"#\"\3\2\2\2$\3\3\2\2\2%\61\7\35\2\2&\'\7\32\2\2\',\5\6\4\2()\7\f\2\2)"+
		"+\5\6\4\2*(\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-/\3\2\2\2.,\3\2\2\2"+
		"/\60\7\33\2\2\60\62\3\2\2\2\61&\3\2\2\2\61\62\3\2\2\2\62\5\3\2\2\2\63"+
		"\64\7\32\2\2\64\65\5\6\4\2\65\66\7\33\2\2\669\3\2\2\2\679\n\2\2\28\63"+
		"\3\2\2\28\67\3\2\2\29<\3\2\2\2:;\3\2\2\2:8\3\2\2\2;\7\3\2\2\2<:\3\2\2"+
		"\2=A\5\n\6\2>A\5\f\7\2?A\7\13\2\2@=\3\2\2\2@>\3\2\2\2@?\3\2\2\2A\t\3\2"+
		"\2\2BD\7\16\2\2CB\3\2\2\2CD\3\2\2\2DG\3\2\2\2EH\7\23\2\2FH\5\4\3\2GE\3"+
		"\2\2\2GF\3\2\2\2HI\3\2\2\2IL\7\r\2\2JM\5\f\7\2KM\5\4\3\2LJ\3\2\2\2LK\3"+
		"\2\2\2M\13\3\2\2\2NO\b\7\1\2OP\5\16\b\2PQ\7\n\2\2QR\5\f\7\13Rm\3\2\2\2"+
		"ST\5\16\b\2TU\7\3\2\2UV\5\f\7\nVm\3\2\2\2WX\5\16\b\2XY\7\4\2\2YZ\5\f\7"+
		"\tZm\3\2\2\2[\\\5\16\b\2\\]\7\24\2\2]^\5\f\7\7^m\3\2\2\2_`\5\16\b\2`a"+
		"\7\b\2\2ab\5\f\7\6bm\3\2\2\2cd\5\16\b\2de\7\7\2\2ef\5\f\7\5fm\3\2\2\2"+
		"gh\5\16\b\2hi\7\6\2\2ij\5\f\7\4jm\3\2\2\2km\5\16\b\2lN\3\2\2\2lS\3\2\2"+
		"\2lW\3\2\2\2l[\3\2\2\2l_\3\2\2\2lc\3\2\2\2lg\3\2\2\2lk\3\2\2\2ms\3\2\2"+
		"\2no\f\b\2\2op\7\t\2\2pr\5\f\7\tqn\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2"+
		"\2t\r\3\2\2\2us\3\2\2\2v\u0093\5\4\3\2w\u0093\5\24\13\2x\u0093\5\22\n"+
		"\2y\u0093\7\22\2\2z\u0093\7\25\2\2{}\7\26\2\2|~\5\2\2\2}|\3\2\2\2}~\3"+
		"\2\2\2~\177\3\2\2\2\177\u0093\7\27\2\2\u0080\u0089\7\30\2\2\u0081\u0086"+
		"\5\f\7\2\u0082\u0083\7\f\2\2\u0083\u0085\5\f\7\2\u0084\u0082\3\2\2\2\u0085"+
		"\u0088\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u008a\3\2"+
		"\2\2\u0088\u0086\3\2\2\2\u0089\u0081\3\2\2\2\u0089\u008a\3\2\2\2\u008a"+
		"\u008b\3\2\2\2\u008b\u0093\7\31\2\2\u008c\u008e\7\32\2\2\u008d\u008f\5"+
		"\f\7\2\u008e\u008d\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\3\2\2\2\u0090"+
		"\u0093\7\33\2\2\u0091\u0093\5\20\t\2\u0092v\3\2\2\2\u0092w\3\2\2\2\u0092"+
		"x\3\2\2\2\u0092y\3\2\2\2\u0092z\3\2\2\2\u0092{\3\2\2\2\u0092\u0080\3\2"+
		"\2\2\u0092\u008c\3\2\2\2\u0092\u0091\3\2\2\2\u0093\17\3\2\2\2\u0094\u0096"+
		"\7\26\2\2\u0095\u0097\5\2\2\2\u0096\u0095\3\2\2\2\u0096\u0097\3\2\2\2"+
		"\u0097\u0098\3\2\2\2\u0098\u00b9\b\t\1\2\u0099\u009b\7\26\2\2\u009a\u009c"+
		"\5\2\2\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2\2\2\u009d"+
		"\u009e\7\27\2\2\u009e\u009f\7\27\2\2\u009f\u00b9\b\t\1\2\u00a0\u00a2\7"+
		"\30\2\2\u00a1\u00a3\5\f\7\2\u00a2\u00a1\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3"+
		"\u00a4\3\2\2\2\u00a4\u00b9\b\t\1\2\u00a5\u00a7\7\30\2\2\u00a6\u00a8\5"+
		"\f\7\2\u00a7\u00a6\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00aa\7\31\2\2\u00aa\u00ab\7\31\2\2\u00ab\u00b9\b\t\1\2\u00ac\u00ae\7"+
		"\32\2\2\u00ad\u00af\5\f\7\2\u00ae\u00ad\3\2\2\2\u00ae\u00af\3\2\2\2\u00af"+
		"\u00b0\3\2\2\2\u00b0\u00b9\b\t\1\2\u00b1\u00b3\7\32\2\2\u00b2\u00b4\5"+
		"\f\7\2\u00b3\u00b2\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"\u00b6\7\33\2\2\u00b6\u00b7\7\33\2\2\u00b7\u00b9\b\t\1\2\u00b8\u0094\3"+
		"\2\2\2\u00b8\u0099\3\2\2\2\u00b8\u00a0\3\2\2\2\u00b8\u00a5\3\2\2\2\u00b8"+
		"\u00ac\3\2\2\2\u00b8\u00b1\3\2\2\2\u00b9\21\3\2\2\2\u00ba\u00bb\5\26\f"+
		"\2\u00bb\23\3\2\2\2\u00bc\u00bd\5\26\f\2\u00bd\u00be\5\16\b\2\u00be\u00c4"+
		"\3\2\2\2\u00bf\u00c0\7\24\2\2\u00c0\u00c4\5\16\b\2\u00c1\u00c2\7\16\2"+
		"\2\u00c2\u00c4\5\16\b\2\u00c3\u00bc\3\2\2\2\u00c3\u00bf\3\2\2\2\u00c3"+
		"\u00c1\3\2\2\2\u00c4\25\3\2\2\2\u00c5\u00c8\7\23\2\2\u00c6\u00c8\5\30"+
		"\r\2\u00c7\u00c5\3\2\2\2\u00c7\u00c6\3\2\2\2\u00c8\27\3\2\2\2\u00c9\u00ca"+
		"\t\3\2\2\u00ca\31\3\2\2\2\35\34 #,\618:@CGLls}\u0086\u0089\u008e\u0092"+
		"\u0096\u009b\u00a2\u00a7\u00ae\u00b3\u00b8\u00c3\u00c7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}