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
		COMMENT=15, NUMBER=16, ID=17, STRING=18, C_B_O=19, C_B_C=20, S_B_O=21, 
		S_B_C=22, R_B_O=23, R_B_C=24, OTHER=25, BINARY_OPERATOR=26, MACRO_NAME=27;
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
		"'='", null, null, null, null, null, null, null, "'{'", "'}'", "'['", 
		"']'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "OPERATOR_PRECEDENCE_MULTIPLY", "OPERATOR_PRECEDENCE_ADD", "PUCTUATION_OTHER", 
		"OR", "AND", "COMPARE_PRECEDENCE_OPERATOR", "ELSE", "POWER", "SEMICOLON", 
		"COMMA", "EQUALS", "PRIVATE", "MACRO_DECLARATION", "WHITESPACE", "COMMENT", 
		"NUMBER", "ID", "STRING", "C_B_O", "C_B_C", "S_B_O", "S_B_C", "R_B_O", 
		"R_B_C", "OTHER", "BINARY_OPERATOR", "MACRO_NAME"
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
			case STRING:
			case C_B_O:
			case S_B_O:
			case R_B_O:
			case BINARY_OPERATOR:
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
		try {
			int _alt;
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
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(38);
						match(COMMA);
						setState(39);
						macroArgument();
						}
						} 
					}
					setState(44);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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
						_la = _input.LA(1);
						if ( _la <= 0 || (_la==COMMA || _la==R_B_C) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						}
						break;
					case 2:
						{
						setState(50);
						match(R_B_O);
						setState(51);
						macroArgument();
						setState(52);
						match(R_B_C);
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
		public List<BinaryExpressionContext> binaryExpression() {
			return getRuleContexts(BinaryExpressionContext.class);
		}
		public BinaryExpressionContext binaryExpression(int i) {
			return getRuleContext(BinaryExpressionContext.class,i);
		}
		public TerminalNode POWER() { return getToken(SQFParser.POWER, 0); }
		public TerminalNode OPERATOR_PRECEDENCE_MULTIPLY() { return getToken(SQFParser.OPERATOR_PRECEDENCE_MULTIPLY, 0); }
		public TerminalNode OPERATOR_PRECEDENCE_ADD() { return getToken(SQFParser.OPERATOR_PRECEDENCE_ADD, 0); }
		public TerminalNode ELSE() { return getToken(SQFParser.ELSE, 0); }
		public TerminalNode BINARY_OPERATOR() { return getToken(SQFParser.BINARY_OPERATOR, 0); }
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
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_binaryExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(77);
			primaryExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(105);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(103);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(79);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(80);
						match(POWER);
						setState(81);
						binaryExpression(9);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(82);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(83);
						match(OPERATOR_PRECEDENCE_MULTIPLY);
						setState(84);
						binaryExpression(8);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(85);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(86);
						match(OPERATOR_PRECEDENCE_ADD);
						setState(87);
						binaryExpression(7);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(88);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(89);
						match(ELSE);
						setState(90);
						binaryExpression(6);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(91);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(92);
						match(BINARY_OPERATOR);
						setState(93);
						binaryExpression(5);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(94);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(95);
						match(COMPARE_PRECEDENCE_OPERATOR);
						setState(96);
						binaryExpression(4);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(97);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(98);
						match(AND);
						setState(99);
						binaryExpression(3);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_binaryExpression);
						setState(100);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(101);
						match(OR);
						setState(102);
						binaryExpression(2);
						}
						break;
					}
					} 
				}
				setState(107);
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
			setState(136);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new UnaryOperatorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				unaryExpression();
				}
				break;
			case 2:
				_localctx = new NularOperatorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				nularExpression();
				}
				break;
			case 3:
				_localctx = new MacroExpressionContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(110);
				macro();
				}
				break;
			case 4:
				_localctx = new NumberContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(111);
				match(NUMBER);
				}
				break;
			case 5:
				_localctx = new StringContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(112);
				match(STRING);
				}
				break;
			case 6:
				_localctx = new InlineCodeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(113);
				match(C_B_O);
				setState(115);
				_la = _input.LA(1);
				if (((((_la - -1)) & ~0x3f) == 0 && ((1L << (_la - -1)) & ((1L << (EOF - -1)) | (1L << (OPERATOR_PRECEDENCE_ADD - -1)) | (1L << (PUCTUATION_OTHER - -1)) | (1L << (SEMICOLON - -1)) | (1L << (PRIVATE - -1)) | (1L << (NUMBER - -1)) | (1L << (ID - -1)) | (1L << (STRING - -1)) | (1L << (C_B_O - -1)) | (1L << (S_B_O - -1)) | (1L << (R_B_O - -1)) | (1L << (BINARY_OPERATOR - -1)) | (1L << (MACRO_NAME - -1)))) != 0)) {
					{
					setState(114);
					code();
					}
				}

				setState(117);
				match(C_B_C);
				}
				break;
			case 7:
				_localctx = new ArrayContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(118);
				match(S_B_O);
				setState(127);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPERATOR_PRECEDENCE_ADD) | (1L << PUCTUATION_OTHER) | (1L << PRIVATE) | (1L << NUMBER) | (1L << ID) | (1L << STRING) | (1L << C_B_O) | (1L << S_B_O) | (1L << R_B_O) | (1L << BINARY_OPERATOR) | (1L << MACRO_NAME))) != 0)) {
					{
					setState(119);
					binaryExpression(0);
					setState(124);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(120);
						match(COMMA);
						setState(121);
						binaryExpression(0);
						}
						}
						setState(126);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(129);
				match(S_B_C);
				}
				break;
			case 8:
				_localctx = new ParenthesisContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(130);
				match(R_B_O);
				setState(132);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPERATOR_PRECEDENCE_ADD) | (1L << PUCTUATION_OTHER) | (1L << PRIVATE) | (1L << NUMBER) | (1L << ID) | (1L << STRING) | (1L << C_B_O) | (1L << S_B_O) | (1L << R_B_O) | (1L << BINARY_OPERATOR) | (1L << MACRO_NAME))) != 0)) {
					{
					setState(131);
					binaryExpression(0);
					}
				}

				setState(134);
				match(R_B_C);
				}
				break;
			case 9:
				_localctx = new ErrorContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(135);
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
			setState(174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(138);
				match(C_B_O);
				setState(140);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(139);
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
				setState(143);
				match(C_B_O);
				setState(145);
				_la = _input.LA(1);
				if (((((_la - -1)) & ~0x3f) == 0 && ((1L << (_la - -1)) & ((1L << (EOF - -1)) | (1L << (OPERATOR_PRECEDENCE_ADD - -1)) | (1L << (PUCTUATION_OTHER - -1)) | (1L << (SEMICOLON - -1)) | (1L << (PRIVATE - -1)) | (1L << (NUMBER - -1)) | (1L << (ID - -1)) | (1L << (STRING - -1)) | (1L << (C_B_O - -1)) | (1L << (S_B_O - -1)) | (1L << (R_B_O - -1)) | (1L << (BINARY_OPERATOR - -1)) | (1L << (MACRO_NAME - -1)))) != 0)) {
					{
					setState(144);
					code();
					}
				}

				setState(147);
				match(C_B_C);
				setState(148);
				match(C_B_C);
				notifyErrorListeners("Too many curly brackets!");
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(150);
				match(S_B_O);
				setState(152);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
				case 1:
					{
					setState(151);
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
				setState(155);
				match(S_B_O);
				setState(157);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPERATOR_PRECEDENCE_ADD) | (1L << PUCTUATION_OTHER) | (1L << PRIVATE) | (1L << NUMBER) | (1L << ID) | (1L << STRING) | (1L << C_B_O) | (1L << S_B_O) | (1L << R_B_O) | (1L << BINARY_OPERATOR) | (1L << MACRO_NAME))) != 0)) {
					{
					setState(156);
					binaryExpression(0);
					}
				}

				setState(159);
				match(S_B_C);
				setState(160);
				match(S_B_C);
				notifyErrorListeners("Too many square brackets!");
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(162);
				match(R_B_O);
				setState(164);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(163);
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
				setState(167);
				match(R_B_O);
				setState(169);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPERATOR_PRECEDENCE_ADD) | (1L << PUCTUATION_OTHER) | (1L << PRIVATE) | (1L << NUMBER) | (1L << ID) | (1L << STRING) | (1L << C_B_O) | (1L << S_B_O) | (1L << R_B_O) | (1L << BINARY_OPERATOR) | (1L << MACRO_NAME))) != 0)) {
					{
					setState(168);
					binaryExpression(0);
					}
				}

				setState(171);
				match(R_B_C);
				setState(172);
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
			setState(176);
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
			setState(185);
			switch (_input.LA(1)) {
			case OPERATOR_PRECEDENCE_ADD:
			case PUCTUATION_OTHER:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(178);
				operator();
				setState(179);
				primaryExpression();
				}
				break;
			case BINARY_OPERATOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(181);
				match(BINARY_OPERATOR);
				setState(182);
				primaryExpression();
				}
				break;
			case PRIVATE:
				enterOuterAlt(_localctx, 3);
				{
				setState(183);
				match(PRIVATE);
				setState(184);
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
			setState(189);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(187);
				match(ID);
				}
				break;
			case OPERATOR_PRECEDENCE_ADD:
			case PUCTUATION_OTHER:
				enterOuterAlt(_localctx, 2);
				{
				setState(188);
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
			setState(191);
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
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 3);
		case 6:
			return precpred(_ctx, 2);
		case 7:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\35\u00c4\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\3\2\3\2\5\2\35\n\2\6\2\37\n\2\r\2\16\2 \3\2\5\2"+
		"$\n\2\3\3\3\3\3\3\3\3\3\3\7\3+\n\3\f\3\16\3.\13\3\3\3\3\3\5\3\62\n\3\3"+
		"\4\3\4\3\4\3\4\3\4\7\49\n\4\f\4\16\4<\13\4\3\5\3\5\3\5\5\5A\n\5\3\6\5"+
		"\6D\n\6\3\6\3\6\5\6H\n\6\3\6\3\6\3\6\5\6M\n\6\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\7\7j\n\7\f\7\16\7m\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b"+
		"v\n\b\3\b\3\b\3\b\3\b\3\b\7\b}\n\b\f\b\16\b\u0080\13\b\5\b\u0082\n\b\3"+
		"\b\3\b\3\b\5\b\u0087\n\b\3\b\3\b\5\b\u008b\n\b\3\t\3\t\5\t\u008f\n\t\3"+
		"\t\3\t\3\t\5\t\u0094\n\t\3\t\3\t\3\t\3\t\3\t\5\t\u009b\n\t\3\t\3\t\3\t"+
		"\5\t\u00a0\n\t\3\t\3\t\3\t\3\t\3\t\5\t\u00a7\n\t\3\t\3\t\3\t\5\t\u00ac"+
		"\n\t\3\t\3\t\3\t\5\t\u00b1\n\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\5\13\u00bc\n\13\3\f\3\f\5\f\u00c0\n\f\3\r\3\r\3\r\4,:\3\f\16\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\2\4\4\2\f\f\32\32\3\2\4\5\u00e5\2#\3\2\2\2\4"+
		"%\3\2\2\2\6:\3\2\2\2\b@\3\2\2\2\nC\3\2\2\2\fN\3\2\2\2\16\u008a\3\2\2\2"+
		"\20\u00b0\3\2\2\2\22\u00b2\3\2\2\2\24\u00bb\3\2\2\2\26\u00bf\3\2\2\2\30"+
		"\u00c1\3\2\2\2\32\34\5\b\5\2\33\35\7\13\2\2\34\33\3\2\2\2\34\35\3\2\2"+
		"\2\35\37\3\2\2\2\36\32\3\2\2\2\37 \3\2\2\2 \36\3\2\2\2 !\3\2\2\2!$\3\2"+
		"\2\2\"$\7\2\2\3#\36\3\2\2\2#\"\3\2\2\2$\3\3\2\2\2%\61\7\35\2\2&\'\7\31"+
		"\2\2\',\5\6\4\2()\7\f\2\2)+\5\6\4\2*(\3\2\2\2+.\3\2\2\2,-\3\2\2\2,*\3"+
		"\2\2\2-/\3\2\2\2.,\3\2\2\2/\60\7\32\2\2\60\62\3\2\2\2\61&\3\2\2\2\61\62"+
		"\3\2\2\2\62\5\3\2\2\2\639\n\2\2\2\64\65\7\31\2\2\65\66\5\6\4\2\66\67\7"+
		"\32\2\2\679\3\2\2\28\63\3\2\2\28\64\3\2\2\29<\3\2\2\2:;\3\2\2\2:8\3\2"+
		"\2\2;\7\3\2\2\2<:\3\2\2\2=A\5\n\6\2>A\5\f\7\2?A\7\13\2\2@=\3\2\2\2@>\3"+
		"\2\2\2@?\3\2\2\2A\t\3\2\2\2BD\7\16\2\2CB\3\2\2\2CD\3\2\2\2DG\3\2\2\2E"+
		"H\7\23\2\2FH\5\4\3\2GE\3\2\2\2GF\3\2\2\2HI\3\2\2\2IL\7\r\2\2JM\5\f\7\2"+
		"KM\5\4\3\2LJ\3\2\2\2LK\3\2\2\2M\13\3\2\2\2NO\b\7\1\2OP\5\16\b\2Pk\3\2"+
		"\2\2QR\f\n\2\2RS\7\n\2\2Sj\5\f\7\13TU\f\t\2\2UV\7\3\2\2Vj\5\f\7\nWX\f"+
		"\b\2\2XY\7\4\2\2Yj\5\f\7\tZ[\f\7\2\2[\\\7\t\2\2\\j\5\f\7\b]^\f\6\2\2^"+
		"_\7\34\2\2_j\5\f\7\7`a\f\5\2\2ab\7\b\2\2bj\5\f\7\6cd\f\4\2\2de\7\7\2\2"+
		"ej\5\f\7\5fg\f\3\2\2gh\7\6\2\2hj\5\f\7\4iQ\3\2\2\2iT\3\2\2\2iW\3\2\2\2"+
		"iZ\3\2\2\2i]\3\2\2\2i`\3\2\2\2ic\3\2\2\2if\3\2\2\2jm\3\2\2\2ki\3\2\2\2"+
		"kl\3\2\2\2l\r\3\2\2\2mk\3\2\2\2n\u008b\5\24\13\2o\u008b\5\22\n\2p\u008b"+
		"\5\4\3\2q\u008b\7\22\2\2r\u008b\7\24\2\2su\7\25\2\2tv\5\2\2\2ut\3\2\2"+
		"\2uv\3\2\2\2vw\3\2\2\2w\u008b\7\26\2\2x\u0081\7\27\2\2y~\5\f\7\2z{\7\f"+
		"\2\2{}\5\f\7\2|z\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0082"+
		"\3\2\2\2\u0080~\3\2\2\2\u0081y\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083"+
		"\3\2\2\2\u0083\u008b\7\30\2\2\u0084\u0086\7\31\2\2\u0085\u0087\5\f\7\2"+
		"\u0086\u0085\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008b"+
		"\7\32\2\2\u0089\u008b\5\20\t\2\u008an\3\2\2\2\u008ao\3\2\2\2\u008ap\3"+
		"\2\2\2\u008aq\3\2\2\2\u008ar\3\2\2\2\u008as\3\2\2\2\u008ax\3\2\2\2\u008a"+
		"\u0084\3\2\2\2\u008a\u0089\3\2\2\2\u008b\17\3\2\2\2\u008c\u008e\7\25\2"+
		"\2\u008d\u008f\5\2\2\2\u008e\u008d\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090"+
		"\3\2\2\2\u0090\u00b1\b\t\1\2\u0091\u0093\7\25\2\2\u0092\u0094\5\2\2\2"+
		"\u0093\u0092\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096"+
		"\7\26\2\2\u0096\u0097\7\26\2\2\u0097\u00b1\b\t\1\2\u0098\u009a\7\27\2"+
		"\2\u0099\u009b\5\f\7\2\u009a\u0099\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009c"+
		"\3\2\2\2\u009c\u00b1\b\t\1\2\u009d\u009f\7\27\2\2\u009e\u00a0\5\f\7\2"+
		"\u009f\u009e\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2"+
		"\7\30\2\2\u00a2\u00a3\7\30\2\2\u00a3\u00b1\b\t\1\2\u00a4\u00a6\7\31\2"+
		"\2\u00a5\u00a7\5\f\7\2\u00a6\u00a5\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8"+
		"\3\2\2\2\u00a8\u00b1\b\t\1\2\u00a9\u00ab\7\31\2\2\u00aa\u00ac\5\f\7\2"+
		"\u00ab\u00aa\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae"+
		"\7\32\2\2\u00ae\u00af\7\32\2\2\u00af\u00b1\b\t\1\2\u00b0\u008c\3\2\2\2"+
		"\u00b0\u0091\3\2\2\2\u00b0\u0098\3\2\2\2\u00b0\u009d\3\2\2\2\u00b0\u00a4"+
		"\3\2\2\2\u00b0\u00a9\3\2\2\2\u00b1\21\3\2\2\2\u00b2\u00b3\5\26\f\2\u00b3"+
		"\23\3\2\2\2\u00b4\u00b5\5\26\f\2\u00b5\u00b6\5\16\b\2\u00b6\u00bc\3\2"+
		"\2\2\u00b7\u00b8\7\34\2\2\u00b8\u00bc\5\16\b\2\u00b9\u00ba\7\16\2\2\u00ba"+
		"\u00bc\5\16\b\2\u00bb\u00b4\3\2\2\2\u00bb\u00b7\3\2\2\2\u00bb\u00b9\3"+
		"\2\2\2\u00bc\25\3\2\2\2\u00bd\u00c0\7\23\2\2\u00be\u00c0\5\30\r\2\u00bf"+
		"\u00bd\3\2\2\2\u00bf\u00be\3\2\2\2\u00c0\27\3\2\2\2\u00c1\u00c2\t\3\2"+
		"\2\u00c2\31\3\2\2\2\35\34 #,\618:@CGLiku~\u0081\u0086\u008a\u008e\u0093"+
		"\u009a\u009f\u00a6\u00ab\u00b0\u00bb\u00bf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}