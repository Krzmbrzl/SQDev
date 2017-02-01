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
			setState(62);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					setState(60);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						setState(55);
						match(R_B_O);
						setState(56);
						macroArgument();
						setState(57);
						match(R_B_C);
						}
						break;
					case 2:
						{
						setState(59);
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
				setState(64);
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
		enterRule(_localctx, 8, RULE_statement);
		try {
			setState(68);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				assignment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				binaryExpression(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(67);
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
			setState(71);
			_la = _input.LA(1);
			if (_la==PRIVATE) {
				{
				setState(70);
				match(PRIVATE);
				}
			}

			setState(75);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(73);
				match(ID);
				}
				break;
			case MACRO_NAME:
				{
				setState(74);
				macro();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(77);
			match(EQUALS);
			setState(80);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(78);
				binaryExpression(0);
				}
				break;
			case 2:
				{
				setState(79);
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
			setState(89);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(83);
				match(BINARY_OPERATOR);
				setState(84);
				primaryExpression();
				}
				break;
			case 2:
				{
				setState(85);
				match(OPERATOR_PRECEDENCE_ADD);
				setState(86);
				primaryExpression();
				}
				break;
			case 3:
				{
				setState(87);
				match(BINARY_OPERATOR);
				}
				break;
			case 4:
				{
				setState(88);
				primaryExpression();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(117);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(115);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(91);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(92);
						match(POWER);
						setState(93);
						binaryExpression(13);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(94);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(95);
						match(OPERATOR_PRECEDENCE_MULTIPLY);
						setState(96);
						binaryExpression(12);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(97);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(98);
						match(OPERATOR_PRECEDENCE_ADD);
						setState(99);
						binaryExpression(11);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(100);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(101);
						match(ELSE);
						setState(102);
						binaryExpression(10);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(103);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(104);
						match(BINARY_OPERATOR);
						setState(105);
						binaryExpression(9);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(106);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(107);
						match(COMPARE_PRECEDENCE_OPERATOR);
						setState(108);
						binaryExpression(8);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(109);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(110);
						match(AND);
						setState(111);
						binaryExpression(7);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(112);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(113);
						match(OR);
						setState(114);
						binaryExpression(6);
						}
						break;
					}
					} 
				}
				setState(119);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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
		public MacroContext macro() {
			return getRuleContext(MacroContext.class,0);
		}
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public NularExpressionContext nularExpression() {
			return getRuleContext(NularExpressionContext.class,0);
		}
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
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

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_primaryExpression);
		try {
			setState(123);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				macro();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(121);
				unaryExpression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(122);
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
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				_localctx = new NularOperatorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(125);
				operator();
				}
				break;
			case 2:
				_localctx = new NumberContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(126);
				match(NUMBER);
				}
				break;
			case 3:
				_localctx = new StringContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(127);
				match(STRING);
				}
				break;
			case 4:
				_localctx = new InlineCodeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(128);
				match(C_B_O);
				setState(129);
				code();
				setState(130);
				match(C_B_C);
				}
				break;
			case 5:
				_localctx = new ArrayContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(132);
				match(S_B_O);
				setState(141);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPERATOR_PRECEDENCE_ADD) | (1L << PUCTUATION_OTHER) | (1L << PRIVATE) | (1L << NUMBER) | (1L << ID) | (1L << BINARY_OPERATOR) | (1L << STRING) | (1L << C_B_O) | (1L << S_B_O) | (1L << R_B_O) | (1L << MACRO_NAME))) != 0)) {
					{
					setState(133);
					binaryExpression(0);
					setState(138);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(134);
						match(COMMA);
						setState(135);
						binaryExpression(0);
						}
						}
						setState(140);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(143);
				match(S_B_C);
				}
				break;
			case 6:
				_localctx = new ParenthesisContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(144);
				match(R_B_O);
				setState(146);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPERATOR_PRECEDENCE_ADD) | (1L << PUCTUATION_OTHER) | (1L << PRIVATE) | (1L << NUMBER) | (1L << ID) | (1L << BINARY_OPERATOR) | (1L << STRING) | (1L << C_B_O) | (1L << S_B_O) | (1L << R_B_O) | (1L << MACRO_NAME))) != 0)) {
					{
					setState(145);
					binaryExpression(0);
					}
				}

				setState(148);
				match(R_B_C);
				}
				break;
			case 7:
				_localctx = new ErrorContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(149);
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
		int _la;
		try {
			setState(186);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				match(C_B_O);
				setState(153);
				code();
				notifyErrorListeners("Missing closing '}'");
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(156);
				match(C_B_O);
				setState(157);
				code();
				setState(158);
				match(C_B_C);
				setState(159);
				match(C_B_C);
				notifyErrorListeners("Too many curly brackets!");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(162);
				match(S_B_O);
				setState(164);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(163);
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
				setState(167);
				match(S_B_O);
				setState(169);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPERATOR_PRECEDENCE_ADD) | (1L << PUCTUATION_OTHER) | (1L << PRIVATE) | (1L << NUMBER) | (1L << ID) | (1L << BINARY_OPERATOR) | (1L << STRING) | (1L << C_B_O) | (1L << S_B_O) | (1L << R_B_O) | (1L << MACRO_NAME))) != 0)) {
					{
					setState(168);
					binaryExpression(0);
					}
				}

				setState(171);
				match(S_B_C);
				setState(172);
				match(S_B_C);
				notifyErrorListeners("Too many square brackets!");
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(174);
				match(R_B_O);
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
				notifyErrorListeners("Missing closing ')'");
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(179);
				match(R_B_O);
				setState(181);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPERATOR_PRECEDENCE_ADD) | (1L << PUCTUATION_OTHER) | (1L << PRIVATE) | (1L << NUMBER) | (1L << ID) | (1L << BINARY_OPERATOR) | (1L << STRING) | (1L << C_B_O) | (1L << S_B_O) | (1L << R_B_O) | (1L << MACRO_NAME))) != 0)) {
					{
					setState(180);
					binaryExpression(0);
					}
				}

				setState(183);
				match(R_B_C);
				setState(184);
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
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
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
		enterRule(_localctx, 20, RULE_unaryExpression);
		try {
			setState(193);
			switch (_input.LA(1)) {
			case PUCTUATION_OTHER:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(188);
				operator();
				setState(189);
				primaryExpression();
				}
				break;
			case PRIVATE:
				enterOuterAlt(_localctx, 2);
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
		enterRule(_localctx, 22, RULE_operator);
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
			setState(199);
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\35\u00cc\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\3\3\3\5\3\"\n\3\7\3$\n\3"+
		"\f\3\16\3\'\13\3\3\3\5\3*\n\3\3\4\3\4\3\4\3\4\3\4\7\4\61\n\4\f\4\16\4"+
		"\64\13\4\3\4\3\4\5\48\n\4\3\5\3\5\3\5\3\5\3\5\7\5?\n\5\f\5\16\5B\13\5"+
		"\3\6\3\6\3\6\5\6G\n\6\3\7\5\7J\n\7\3\7\3\7\5\7N\n\7\3\7\3\7\3\7\5\7S\n"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\\\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b"+
		"v\n\b\f\b\16\by\13\b\3\t\3\t\3\t\5\t~\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\7\n\u008b\n\n\f\n\16\n\u008e\13\n\5\n\u0090\n\n\3\n\3"+
		"\n\3\n\5\n\u0095\n\n\3\n\3\n\5\n\u0099\n\n\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00a7\n\13\3\13\3\13\3\13\5\13"+
		"\u00ac\n\13\3\13\3\13\3\13\3\13\3\13\5\13\u00b3\n\13\3\13\3\13\3\13\5"+
		"\13\u00b8\n\13\3\13\3\13\3\13\5\13\u00bd\n\13\3\f\3\f\3\f\3\f\3\f\5\f"+
		"\u00c4\n\f\3\r\3\r\5\r\u00c8\n\r\3\16\3\16\3\16\3@\3\16\17\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\2\3\4\2\f\f\33\33\u00eb\2\34\3\2\2\2\4%\3\2\2\2"+
		"\6+\3\2\2\2\b@\3\2\2\2\nF\3\2\2\2\fI\3\2\2\2\16[\3\2\2\2\20}\3\2\2\2\22"+
		"\u0098\3\2\2\2\24\u00bc\3\2\2\2\26\u00c3\3\2\2\2\30\u00c7\3\2\2\2\32\u00c9"+
		"\3\2\2\2\34\35\5\4\3\2\35\36\7\2\2\3\36\3\3\2\2\2\37!\5\n\6\2 \"\7\13"+
		"\2\2! \3\2\2\2!\"\3\2\2\2\"$\3\2\2\2#\37\3\2\2\2$\'\3\2\2\2%#\3\2\2\2"+
		"%&\3\2\2\2&)\3\2\2\2\'%\3\2\2\2(*\5\n\6\2)(\3\2\2\2)*\3\2\2\2*\5\3\2\2"+
		"\2+\67\7\35\2\2,-\7\32\2\2-\62\5\b\5\2./\7\f\2\2/\61\5\b\5\2\60.\3\2\2"+
		"\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\65\3\2\2\2\64\62\3\2\2"+
		"\2\65\66\7\33\2\2\668\3\2\2\2\67,\3\2\2\2\678\3\2\2\28\7\3\2\2\29:\7\32"+
		"\2\2:;\5\b\5\2;<\7\33\2\2<?\3\2\2\2=?\n\2\2\2>9\3\2\2\2>=\3\2\2\2?B\3"+
		"\2\2\2@A\3\2\2\2@>\3\2\2\2A\t\3\2\2\2B@\3\2\2\2CG\5\f\7\2DG\5\16\b\2E"+
		"G\7\13\2\2FC\3\2\2\2FD\3\2\2\2FE\3\2\2\2G\13\3\2\2\2HJ\7\16\2\2IH\3\2"+
		"\2\2IJ\3\2\2\2JM\3\2\2\2KN\7\23\2\2LN\5\6\4\2MK\3\2\2\2ML\3\2\2\2NO\3"+
		"\2\2\2OR\7\r\2\2PS\5\16\b\2QS\5\6\4\2RP\3\2\2\2RQ\3\2\2\2S\r\3\2\2\2T"+
		"U\b\b\1\2UV\7\24\2\2V\\\5\20\t\2WX\7\4\2\2X\\\5\20\t\2Y\\\7\24\2\2Z\\"+
		"\5\20\t\2[T\3\2\2\2[W\3\2\2\2[Y\3\2\2\2[Z\3\2\2\2\\w\3\2\2\2]^\f\16\2"+
		"\2^_\7\n\2\2_v\5\16\b\17`a\f\r\2\2ab\7\3\2\2bv\5\16\b\16cd\f\f\2\2de\7"+
		"\4\2\2ev\5\16\b\rfg\f\13\2\2gh\7\t\2\2hv\5\16\b\fij\f\n\2\2jk\7\24\2\2"+
		"kv\5\16\b\13lm\f\t\2\2mn\7\b\2\2nv\5\16\b\nop\f\b\2\2pq\7\7\2\2qv\5\16"+
		"\b\trs\f\7\2\2st\7\6\2\2tv\5\16\b\bu]\3\2\2\2u`\3\2\2\2uc\3\2\2\2uf\3"+
		"\2\2\2ui\3\2\2\2ul\3\2\2\2uo\3\2\2\2ur\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3"+
		"\2\2\2x\17\3\2\2\2yw\3\2\2\2z~\5\6\4\2{~\5\26\f\2|~\5\22\n\2}z\3\2\2\2"+
		"}{\3\2\2\2}|\3\2\2\2~\21\3\2\2\2\177\u0099\5\30\r\2\u0080\u0099\7\22\2"+
		"\2\u0081\u0099\7\25\2\2\u0082\u0083\7\26\2\2\u0083\u0084\5\4\3\2\u0084"+
		"\u0085\7\27\2\2\u0085\u0099\3\2\2\2\u0086\u008f\7\30\2\2\u0087\u008c\5"+
		"\16\b\2\u0088\u0089\7\f\2\2\u0089\u008b\5\16\b\2\u008a\u0088\3\2\2\2\u008b"+
		"\u008e\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u0090\3\2"+
		"\2\2\u008e\u008c\3\2\2\2\u008f\u0087\3\2\2\2\u008f\u0090\3\2\2\2\u0090"+
		"\u0091\3\2\2\2\u0091\u0099\7\31\2\2\u0092\u0094\7\32\2\2\u0093\u0095\5"+
		"\16\b\2\u0094\u0093\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\u0099\7\33\2\2\u0097\u0099\5\24\13\2\u0098\177\3\2\2\2\u0098\u0080\3"+
		"\2\2\2\u0098\u0081\3\2\2\2\u0098\u0082\3\2\2\2\u0098\u0086\3\2\2\2\u0098"+
		"\u0092\3\2\2\2\u0098\u0097\3\2\2\2\u0099\23\3\2\2\2\u009a\u009b\7\26\2"+
		"\2\u009b\u009c\5\4\3\2\u009c\u009d\b\13\1\2\u009d\u00bd\3\2\2\2\u009e"+
		"\u009f\7\26\2\2\u009f\u00a0\5\4\3\2\u00a0\u00a1\7\27\2\2\u00a1\u00a2\7"+
		"\27\2\2\u00a2\u00a3\b\13\1\2\u00a3\u00bd\3\2\2\2\u00a4\u00a6\7\30\2\2"+
		"\u00a5\u00a7\5\16\b\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8"+
		"\3\2\2\2\u00a8\u00bd\b\13\1\2\u00a9\u00ab\7\30\2\2\u00aa\u00ac\5\16\b"+
		"\2\u00ab\u00aa\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae"+
		"\7\31\2\2\u00ae\u00af\7\31\2\2\u00af\u00bd\b\13\1\2\u00b0\u00b2\7\32\2"+
		"\2\u00b1\u00b3\5\16\b\2\u00b2\u00b1\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b4\u00bd\b\13\1\2\u00b5\u00b7\7\32\2\2\u00b6\u00b8\5"+
		"\16\b\2\u00b7\u00b6\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9"+
		"\u00ba\7\33\2\2\u00ba\u00bb\7\33\2\2\u00bb\u00bd\b\13\1\2\u00bc\u009a"+
		"\3\2\2\2\u00bc\u009e\3\2\2\2\u00bc\u00a4\3\2\2\2\u00bc\u00a9\3\2\2\2\u00bc"+
		"\u00b0\3\2\2\2\u00bc\u00b5\3\2\2\2\u00bd\25\3\2\2\2\u00be\u00bf\5\30\r"+
		"\2\u00bf\u00c0\5\20\t\2\u00c0\u00c4\3\2\2\2\u00c1\u00c2\7\16\2\2\u00c2"+
		"\u00c4\5\20\t\2\u00c3\u00be\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\27\3\2\2"+
		"\2\u00c5\u00c8\7\23\2\2\u00c6\u00c8\5\32\16\2\u00c7\u00c5\3\2\2\2\u00c7"+
		"\u00c6\3\2\2\2\u00c8\31\3\2\2\2\u00c9\u00ca\7\5\2\2\u00ca\33\3\2\2\2\34"+
		"!%)\62\67>@FIMR[uw}\u008c\u008f\u0094\u0098\u00a6\u00ab\u00b2\u00b7\u00bc"+
		"\u00c3\u00c7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}