// Generated from SQF.g4 by ANTLR 4.5.3

	package raven.sqdev.parser.sqf;

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
		C_B_C=21, S_B_O=22, S_B_C=23, R_B_O=24, R_B_C=25, OTHER=26, MACRO_NAME=27, 
		UNARY_OPERATOR=28;
	public static final int
		RULE_start = 0, RULE_code = 1, RULE_macro = 2, RULE_macroArgument = 3, 
		RULE_statement = 4, RULE_assignment = 5, RULE_binaryExpression = 6, RULE_primaryExpression = 7, 
		RULE_nularExpression = 8, RULE_commonError = 9, RULE_unaryExpression = 10, 
		RULE_operator = 11, RULE_punctuation = 12;
	public static final String[] ruleNames = {
		"start", "code", "macro", "macroArgument", "statement", "assignment", 
		"binaryExpression", "primaryExpression", "nularExpression", "commonError", 
		"unaryExpression", "operator", "punctuation"
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
		"S_B_C", "R_B_O", "R_B_C", "OTHER", "MACRO_NAME", "UNARY_OPERATOR"
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
	public static class StartContext extends ParserRuleContext {
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public TerminalNode EOF() { return getToken(SQFParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			code();
			setState(27);
			match(EOF);
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
		enterRule(_localctx, 2, RULE_code);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(29);
					statement();
					setState(31);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						setState(30);
						((CodeContext)_localctx).semicolon = match(SEMICOLON);
						}
						break;
					}
					}
					} 
				}
				setState(37);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(39);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(38);
				statement();
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
		enterRule(_localctx, 4, RULE_macro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(MACRO_NAME);
			setState(53);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(42);
				match(R_B_O);
				setState(43);
				macroArgument();
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(44);
					match(COMMA);
					setState(45);
					macroArgument();
					}
					}
					setState(50);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(51);
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
		enterRule(_localctx, 6, RULE_macroArgument);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					setState(67);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						setState(55);
						match(R_B_O);
						setState(56);
						macroArgument();
						setState(61);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(57);
							match(COMMA);
							setState(58);
							macroArgument();
							}
							}
							setState(63);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(64);
						match(R_B_C);
						}
						break;
					case 2:
						{
						setState(66);
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
				setState(71);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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
		enterRule(_localctx, 8, RULE_statement);
		try {
			setState(75);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				assignment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				binaryExpression(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(74);
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
		enterRule(_localctx, 10, RULE_assignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			_la = _input.LA(1);
			if (_la==PRIVATE) {
				{
				setState(77);
				match(PRIVATE);
				}
			}

			setState(82);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(80);
				match(ID);
				}
				break;
			case MACRO_NAME:
				{
				setState(81);
				macro();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(84);
			match(EQUALS);
			setState(87);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(85);
				binaryExpression(0);
				}
				break;
			case 2:
				{
				setState(86);
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
		public TerminalNode BINARY_OPERATOR() { return getToken(SQFParser.BINARY_OPERATOR, 0); }
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public TerminalNode OPERATOR_PRECEDENCE_ADD() { return getToken(SQFParser.OPERATOR_PRECEDENCE_ADD, 0); }
		public List<BinaryExpressionContext> binaryExpression() {
			return getRuleContexts(BinaryExpressionContext.class);
		}
		public BinaryExpressionContext binaryExpression(int i) {
			return getRuleContext(BinaryExpressionContext.class,i);
		}
		public TerminalNode POWER() { return getToken(SQFParser.POWER, 0); }
		public TerminalNode OPERATOR_PRECEDENCE_MULTIPLY() { return getToken(SQFParser.OPERATOR_PRECEDENCE_MULTIPLY, 0); }
		public TerminalNode ELSE() { return getToken(SQFParser.ELSE, 0); }
		public TerminalNode COMPARE_PRECEDENCE_OPERATOR() { return getToken(SQFParser.COMPARE_PRECEDENCE_OPERATOR, 0); }
		public TerminalNode AND() { return getToken(SQFParser.AND, 0); }
		public TerminalNode OR() { return getToken(SQFParser.OR, 0); }
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
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_binaryExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(90);
				match(BINARY_OPERATOR);
				setState(91);
				primaryExpression(false);
				}
				break;
			case 2:
				{
				setState(92);
				match(OPERATOR_PRECEDENCE_ADD);
				setState(93);
				primaryExpression(false);
				}
				break;
			case 3:
				{
				setState(94);
				match(BINARY_OPERATOR);
				}
				break;
			case 4:
				{
				setState(95);
				primaryExpression(false);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(124);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(122);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(98);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(99);
						match(POWER);
						setState(100);
						binaryExpression(13);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(101);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(102);
						match(OPERATOR_PRECEDENCE_MULTIPLY);
						setState(103);
						binaryExpression(12);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(104);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(105);
						match(OPERATOR_PRECEDENCE_ADD);
						setState(106);
						binaryExpression(11);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(107);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(108);
						match(ELSE);
						setState(109);
						binaryExpression(10);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(110);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(111);
						match(BINARY_OPERATOR);
						setState(112);
						binaryExpression(9);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(113);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(114);
						match(COMPARE_PRECEDENCE_OPERATOR);
						setState(115);
						binaryExpression(8);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(116);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(117);
						match(AND);
						setState(118);
						binaryExpression(7);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(119);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(120);
						match(OR);
						setState(121);
						binaryExpression(6);
						}
						break;
					}
					} 
				}
				setState(126);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
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
		public boolean allowBinaryAlts;
		public MacroContext macro() {
			return getRuleContext(MacroContext.class,0);
		}
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public NularExpressionContext nularExpression() {
			return getRuleContext(NularExpressionContext.class,0);
		}
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState, boolean allowBinaryAlts) {
			super(parent, invokingState);
			this.allowBinaryAlts = allowBinaryAlts;
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).enterPrimaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFListener ) ((SQFListener)listener).exitPrimaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFVisitor ) return ((SQFVisitor<? extends T>)visitor).visitPrimaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression(boolean allowBinaryAlts) throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState(), allowBinaryAlts);
		enterRule(_localctx, 14, RULE_primaryExpression);
		try {
			setState(130);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				macro();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				unaryExpression(allowBinaryAlts);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(129);
				nularExpression();
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
		public NularExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nularExpression; }
	 
		public NularExpressionContext() { }
		public void copyFrom(NularExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayContext extends NularExpressionContext {
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
		public ArrayContext(NularExpressionContext ctx) { copyFrom(ctx); }
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
	public static class ParenthesisContext extends NularExpressionContext {
		public TerminalNode R_B_O() { return getToken(SQFParser.R_B_O, 0); }
		public TerminalNode R_B_C() { return getToken(SQFParser.R_B_C, 0); }
		public BinaryExpressionContext binaryExpression() {
			return getRuleContext(BinaryExpressionContext.class,0);
		}
		public ParenthesisContext(NularExpressionContext ctx) { copyFrom(ctx); }
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
	public static class NularOperatorContext extends NularExpressionContext {
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public NularOperatorContext(NularExpressionContext ctx) { copyFrom(ctx); }
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
	public static class NumberContext extends NularExpressionContext {
		public TerminalNode NUMBER() { return getToken(SQFParser.NUMBER, 0); }
		public NumberContext(NularExpressionContext ctx) { copyFrom(ctx); }
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
	public static class ErrorContext extends NularExpressionContext {
		public CommonErrorContext commonError() {
			return getRuleContext(CommonErrorContext.class,0);
		}
		public ErrorContext(NularExpressionContext ctx) { copyFrom(ctx); }
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
	public static class StringContext extends NularExpressionContext {
		public TerminalNode STRING() { return getToken(SQFParser.STRING, 0); }
		public StringContext(NularExpressionContext ctx) { copyFrom(ctx); }
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
	public static class InlineCodeContext extends NularExpressionContext {
		public TerminalNode C_B_O() { return getToken(SQFParser.C_B_O, 0); }
		public CodeContext code() {
			return getRuleContext(CodeContext.class,0);
		}
		public TerminalNode C_B_C() { return getToken(SQFParser.C_B_C, 0); }
		public InlineCodeContext(NularExpressionContext ctx) { copyFrom(ctx); }
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

	public final NularExpressionContext nularExpression() throws RecognitionException {
		NularExpressionContext _localctx = new NularExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_nularExpression);
		int _la;
		try {
			setState(157);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				_localctx = new NularOperatorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(132);
				operator();
				}
				break;
			case 2:
				_localctx = new NumberContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(133);
				match(NUMBER);
				}
				break;
			case 3:
				_localctx = new StringContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(134);
				match(STRING);
				}
				break;
			case 4:
				_localctx = new InlineCodeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(135);
				match(C_B_O);
				setState(136);
				code();
				setState(137);
				match(C_B_C);
				}
				break;
			case 5:
				_localctx = new ArrayContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(139);
				match(S_B_O);
				setState(148);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
				case 1:
					{
					setState(140);
					binaryExpression(0);
					setState(145);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(141);
						match(COMMA);
						setState(142);
						binaryExpression(0);
						}
						}
						setState(147);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				}
				setState(150);
				match(S_B_C);
				}
				break;
			case 6:
				_localctx = new ParenthesisContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(151);
				match(R_B_O);
				setState(153);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(152);
					binaryExpression(0);
					}
					break;
				}
				setState(155);
				match(R_B_C);
				}
				break;
			case 7:
				_localctx = new ErrorContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(156);
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
		enterRule(_localctx, 18, RULE_commonError);
		try {
			setState(193);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(159);
				match(C_B_O);
				setState(160);
				code();
				notifyErrorListeners("Missing closing '}'");
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				match(C_B_O);
				setState(164);
				code();
				setState(165);
				match(C_B_C);
				setState(166);
				match(C_B_C);
				notifyErrorListeners("Too many curly brackets!");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(169);
				match(S_B_O);
				setState(171);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(170);
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
				setState(174);
				match(S_B_O);
				setState(176);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(175);
					binaryExpression(0);
					}
					break;
				}
				setState(178);
				match(S_B_C);
				setState(179);
				match(S_B_C);
				notifyErrorListeners("Too many square brackets!");
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(181);
				match(R_B_O);
				setState(183);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(182);
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
				setState(186);
				match(R_B_O);
				setState(188);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(187);
					binaryExpression(0);
					}
					break;
				}
				setState(190);
				match(R_B_C);
				setState(191);
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

	public static class UnaryExpressionContext extends ParserRuleContext {
		public boolean allowBinaryAlts;
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public TerminalNode UNARY_OPERATOR() { return getToken(SQFParser.UNARY_OPERATOR, 0); }
		public TerminalNode PUCTUATION_OTHER() { return getToken(SQFParser.PUCTUATION_OTHER, 0); }
		public TerminalNode BINARY_OPERATOR() { return getToken(SQFParser.BINARY_OPERATOR, 0); }
		public TerminalNode PRIVATE() { return getToken(SQFParser.PRIVATE, 0); }
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState, boolean allowBinaryAlts) {
			super(parent, invokingState);
			this.allowBinaryAlts = allowBinaryAlts;
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

	public final UnaryExpressionContext unaryExpression(boolean allowBinaryAlts) throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState(), allowBinaryAlts);
		enterRule(_localctx, 20, RULE_unaryExpression);
		int _la;
		try {
			setState(202);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				_la = _input.LA(1);
				if ( !(_la==PUCTUATION_OTHER || _la==UNARY_OPERATOR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(196);
				primaryExpression(true);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(197);
				if (!(_localctx.allowBinaryAlts)) throw new FailedPredicateException(this, "$allowBinaryAlts");
				setState(198);
				match(BINARY_OPERATOR);
				setState(199);
				primaryExpression(true);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(200);
				match(PRIVATE);
				setState(201);
				primaryExpression(false);
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
		enterRule(_localctx, 22, RULE_operator);
		try {
			setState(206);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(204);
				match(ID);
				}
				break;
			case PUCTUATION_OTHER:
				enterOuterAlt(_localctx, 2);
				{
				setState(205);
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
		enterRule(_localctx, 24, RULE_punctuation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(PUCTUATION_OTHER);
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
		case 6:
			return binaryExpression_sempred((BinaryExpressionContext)_localctx, predIndex);
		case 10:
			return unaryExpression_sempred((UnaryExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean binaryExpression_sempred(BinaryExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 12);
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 7);
		case 6:
			return precpred(_ctx, 6);
		case 7:
			return precpred(_ctx, 5);
		}
		return true;
	}
	private boolean unaryExpression_sempred(UnaryExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8:
			return _localctx.allowBinaryAlts;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\36\u00d5\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\3\3\3\5\3\"\n\3\7\3$\n\3"+
		"\f\3\16\3\'\13\3\3\3\5\3*\n\3\3\4\3\4\3\4\3\4\3\4\7\4\61\n\4\f\4\16\4"+
		"\64\13\4\3\4\3\4\5\48\n\4\3\5\3\5\3\5\3\5\7\5>\n\5\f\5\16\5A\13\5\3\5"+
		"\3\5\3\5\7\5F\n\5\f\5\16\5I\13\5\3\6\3\6\3\6\5\6N\n\6\3\7\5\7Q\n\7\3\7"+
		"\3\7\5\7U\n\7\3\7\3\7\3\7\5\7Z\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bc\n"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b}\n\b\f\b\16\b\u0080\13\b\3\t\3\t\3\t"+
		"\5\t\u0085\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\7\n\u0092\n"+
		"\n\f\n\16\n\u0095\13\n\5\n\u0097\n\n\3\n\3\n\3\n\5\n\u009c\n\n\3\n\3\n"+
		"\5\n\u00a0\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\5\13\u00ae\n\13\3\13\3\13\3\13\5\13\u00b3\n\13\3\13\3\13\3\13\3"+
		"\13\3\13\5\13\u00ba\n\13\3\13\3\13\3\13\5\13\u00bf\n\13\3\13\3\13\3\13"+
		"\5\13\u00c4\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00cd\n\f\3\r\3\r\5\r"+
		"\u00d1\n\r\3\16\3\16\3\16\3G\3\16\17\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\2\4\4\2\f\f\33\33\4\2\5\5\36\36\u00f6\2\34\3\2\2\2\4%\3\2\2\2\6+\3\2"+
		"\2\2\bG\3\2\2\2\nM\3\2\2\2\fP\3\2\2\2\16b\3\2\2\2\20\u0084\3\2\2\2\22"+
		"\u009f\3\2\2\2\24\u00c3\3\2\2\2\26\u00cc\3\2\2\2\30\u00d0\3\2\2\2\32\u00d2"+
		"\3\2\2\2\34\35\5\4\3\2\35\36\7\2\2\3\36\3\3\2\2\2\37!\5\n\6\2 \"\7\13"+
		"\2\2! \3\2\2\2!\"\3\2\2\2\"$\3\2\2\2#\37\3\2\2\2$\'\3\2\2\2%#\3\2\2\2"+
		"%&\3\2\2\2&)\3\2\2\2\'%\3\2\2\2(*\5\n\6\2)(\3\2\2\2)*\3\2\2\2*\5\3\2\2"+
		"\2+\67\7\35\2\2,-\7\32\2\2-\62\5\b\5\2./\7\f\2\2/\61\5\b\5\2\60.\3\2\2"+
		"\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\65\3\2\2\2\64\62\3\2\2"+
		"\2\65\66\7\33\2\2\668\3\2\2\2\67,\3\2\2\2\678\3\2\2\28\7\3\2\2\29:\7\32"+
		"\2\2:?\5\b\5\2;<\7\f\2\2<>\5\b\5\2=;\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2"+
		"\2\2@B\3\2\2\2A?\3\2\2\2BC\7\33\2\2CF\3\2\2\2DF\n\2\2\2E9\3\2\2\2ED\3"+
		"\2\2\2FI\3\2\2\2GH\3\2\2\2GE\3\2\2\2H\t\3\2\2\2IG\3\2\2\2JN\5\f\7\2KN"+
		"\5\16\b\2LN\7\13\2\2MJ\3\2\2\2MK\3\2\2\2ML\3\2\2\2N\13\3\2\2\2OQ\7\16"+
		"\2\2PO\3\2\2\2PQ\3\2\2\2QT\3\2\2\2RU\7\23\2\2SU\5\6\4\2TR\3\2\2\2TS\3"+
		"\2\2\2UV\3\2\2\2VY\7\r\2\2WZ\5\16\b\2XZ\5\6\4\2YW\3\2\2\2YX\3\2\2\2Z\r"+
		"\3\2\2\2[\\\b\b\1\2\\]\7\24\2\2]c\5\20\t\2^_\7\4\2\2_c\5\20\t\2`c\7\24"+
		"\2\2ac\5\20\t\2b[\3\2\2\2b^\3\2\2\2b`\3\2\2\2ba\3\2\2\2c~\3\2\2\2de\f"+
		"\16\2\2ef\7\n\2\2f}\5\16\b\17gh\f\r\2\2hi\7\3\2\2i}\5\16\b\16jk\f\f\2"+
		"\2kl\7\4\2\2l}\5\16\b\rmn\f\13\2\2no\7\t\2\2o}\5\16\b\fpq\f\n\2\2qr\7"+
		"\24\2\2r}\5\16\b\13st\f\t\2\2tu\7\b\2\2u}\5\16\b\nvw\f\b\2\2wx\7\7\2\2"+
		"x}\5\16\b\tyz\f\7\2\2z{\7\6\2\2{}\5\16\b\b|d\3\2\2\2|g\3\2\2\2|j\3\2\2"+
		"\2|m\3\2\2\2|p\3\2\2\2|s\3\2\2\2|v\3\2\2\2|y\3\2\2\2}\u0080\3\2\2\2~|"+
		"\3\2\2\2~\177\3\2\2\2\177\17\3\2\2\2\u0080~\3\2\2\2\u0081\u0085\5\6\4"+
		"\2\u0082\u0085\5\26\f\2\u0083\u0085\5\22\n\2\u0084\u0081\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0084\u0083\3\2\2\2\u0085\21\3\2\2\2\u0086\u00a0\5\30\r"+
		"\2\u0087\u00a0\7\22\2\2\u0088\u00a0\7\25\2\2\u0089\u008a\7\26\2\2\u008a"+
		"\u008b\5\4\3\2\u008b\u008c\7\27\2\2\u008c\u00a0\3\2\2\2\u008d\u0096\7"+
		"\30\2\2\u008e\u0093\5\16\b\2\u008f\u0090\7\f\2\2\u0090\u0092\5\16\b\2"+
		"\u0091\u008f\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094"+
		"\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u008e\3\2\2\2\u0096"+
		"\u0097\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u00a0\7\31\2\2\u0099\u009b\7"+
		"\32\2\2\u009a\u009c\5\16\b\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c"+
		"\u009d\3\2\2\2\u009d\u00a0\7\33\2\2\u009e\u00a0\5\24\13\2\u009f\u0086"+
		"\3\2\2\2\u009f\u0087\3\2\2\2\u009f\u0088\3\2\2\2\u009f\u0089\3\2\2\2\u009f"+
		"\u008d\3\2\2\2\u009f\u0099\3\2\2\2\u009f\u009e\3\2\2\2\u00a0\23\3\2\2"+
		"\2\u00a1\u00a2\7\26\2\2\u00a2\u00a3\5\4\3\2\u00a3\u00a4\b\13\1\2\u00a4"+
		"\u00c4\3\2\2\2\u00a5\u00a6\7\26\2\2\u00a6\u00a7\5\4\3\2\u00a7\u00a8\7"+
		"\27\2\2\u00a8\u00a9\7\27\2\2\u00a9\u00aa\b\13\1\2\u00aa\u00c4\3\2\2\2"+
		"\u00ab\u00ad\7\30\2\2\u00ac\u00ae\5\16\b\2\u00ad\u00ac\3\2\2\2\u00ad\u00ae"+
		"\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00c4\b\13\1\2\u00b0\u00b2\7\30\2\2"+
		"\u00b1\u00b3\5\16\b\2\u00b2\u00b1\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4"+
		"\3\2\2\2\u00b4\u00b5\7\31\2\2\u00b5\u00b6\7\31\2\2\u00b6\u00c4\b\13\1"+
		"\2\u00b7\u00b9\7\32\2\2\u00b8\u00ba\5\16\b\2\u00b9\u00b8\3\2\2\2\u00b9"+
		"\u00ba\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00c4\b\13\1\2\u00bc\u00be\7"+
		"\32\2\2\u00bd\u00bf\5\16\b\2\u00be\u00bd\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"\u00c0\3\2\2\2\u00c0\u00c1\7\33\2\2\u00c1\u00c2\7\33\2\2\u00c2\u00c4\b"+
		"\13\1\2\u00c3\u00a1\3\2\2\2\u00c3\u00a5\3\2\2\2\u00c3\u00ab\3\2\2\2\u00c3"+
		"\u00b0\3\2\2\2\u00c3\u00b7\3\2\2\2\u00c3\u00bc\3\2\2\2\u00c4\25\3\2\2"+
		"\2\u00c5\u00c6\t\3\2\2\u00c6\u00cd\5\20\t\2\u00c7\u00c8\6\f\n\3\u00c8"+
		"\u00c9\7\24\2\2\u00c9\u00cd\5\20\t\2\u00ca\u00cb\7\16\2\2\u00cb\u00cd"+
		"\5\20\t\2\u00cc\u00c5\3\2\2\2\u00cc\u00c7\3\2\2\2\u00cc\u00ca\3\2\2\2"+
		"\u00cd\27\3\2\2\2\u00ce\u00d1\7\23\2\2\u00cf\u00d1\5\32\16\2\u00d0\u00ce"+
		"\3\2\2\2\u00d0\u00cf\3\2\2\2\u00d1\31\3\2\2\2\u00d2\u00d3\7\5\2\2\u00d3"+
		"\33\3\2\2\2\35!%)\62\67?EGMPTYb|~\u0084\u0093\u0096\u009b\u009f\u00ad"+
		"\u00b2\u00b9\u00be\u00c3\u00cc\u00d0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}