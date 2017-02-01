// Generated from Preprocessor.g4 by ANTLR 4.5.3

	package raven.sqdev.editors.parser.preprocessor;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PreprocessorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, WS=2, PREP_PREFIX=3, NL=4, INCLUDE=5, DEFINE=6, UNDEFINE=7, 
		IF=8, IFN=9, ELSE=10, ENDIF=11, LPAREN=12, RPAREN=13, COMMA=14, STRING=15, 
		ID=16, LETTER=17, INT=18, OTHER=19;
	public static final int
		RULE_start = 0, RULE_preprocessorStatement = 1, RULE_include = 2, RULE_define = 3, 
		RULE_macroArgs = 4, RULE_undefine = 5, RULE_prepIf = 6, RULE_error = 7, 
		RULE_other = 8;
	public static final String[] ruleNames = {
		"start", "preprocessorStatement", "include", "define", "macroArgs", "undefine", 
		"prepIf", "error", "other"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, "'#'", "'\n'", "'#include'", "'#define'", "'#undef'", 
		"'#ifdef'", "'#ifndef'", "'#else'", "'#endif'", "'('", "')'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMENT", "WS", "PREP_PREFIX", "NL", "INCLUDE", "DEFINE", "UNDEFINE", 
		"IF", "IFN", "ELSE", "ENDIF", "LPAREN", "RPAREN", "COMMA", "STRING", "ID", 
		"LETTER", "INT", "OTHER"
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
	public String getGrammarFileName() { return "Preprocessor.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PreprocessorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(PreprocessorParser.EOF, 0); }
		public List<PreprocessorStatementContext> preprocessorStatement() {
			return getRuleContexts(PreprocessorStatementContext.class);
		}
		public PreprocessorStatementContext preprocessorStatement(int i) {
			return getRuleContext(PreprocessorStatementContext.class,i);
		}
		public List<OtherContext> other() {
			return getRuleContexts(OtherContext.class);
		}
		public OtherContext other(int i) {
			return getRuleContext(OtherContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(PreprocessorParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(PreprocessorParser.NL, i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PreprocessorVisitor ) return ((PreprocessorVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMENT) | (1L << WS) | (1L << PREP_PREFIX) | (1L << NL) | (1L << INCLUDE) | (1L << DEFINE) | (1L << UNDEFINE) | (1L << IF) | (1L << IFN) | (1L << ELSE) | (1L << ENDIF) | (1L << LPAREN) | (1L << RPAREN) | (1L << COMMA) | (1L << STRING) | (1L << ID) | (1L << LETTER) | (1L << INT) | (1L << OTHER))) != 0)) {
				{
				setState(31);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(18);
					preprocessorStatement();
					setState(27);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(20); 
							_errHandler.sync(this);
							_la = _input.LA(1);
							do {
								{
								{
								setState(19);
								match(NL);
								}
								}
								setState(22); 
								_errHandler.sync(this);
								_la = _input.LA(1);
							} while ( _la==NL );
							setState(24);
							preprocessorStatement();
							}
							} 
						}
						setState(29);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
					}
					}
					break;
				case 2:
					{
					setState(30);
					other();
					}
					break;
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(36);
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

	public static class PreprocessorStatementContext extends ParserRuleContext {
		public IncludeContext include() {
			return getRuleContext(IncludeContext.class,0);
		}
		public DefineContext define() {
			return getRuleContext(DefineContext.class,0);
		}
		public UndefineContext undefine() {
			return getRuleContext(UndefineContext.class,0);
		}
		public PrepIfContext prepIf() {
			return getRuleContext(PrepIfContext.class,0);
		}
		public ErrorContext error() {
			return getRuleContext(ErrorContext.class,0);
		}
		public PreprocessorStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preprocessorStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).enterPreprocessorStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).exitPreprocessorStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PreprocessorVisitor ) return ((PreprocessorVisitor<? extends T>)visitor).visitPreprocessorStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreprocessorStatementContext preprocessorStatement() throws RecognitionException {
		PreprocessorStatementContext _localctx = new PreprocessorStatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_preprocessorStatement);
		try {
			setState(43);
			switch (_input.LA(1)) {
			case INCLUDE:
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				include();
				}
				break;
			case DEFINE:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				define();
				}
				break;
			case UNDEFINE:
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
				undefine();
				}
				break;
			case IF:
			case IFN:
				enterOuterAlt(_localctx, 4);
				{
				setState(41);
				prepIf();
				}
				break;
			case PREP_PREFIX:
				enterOuterAlt(_localctx, 5);
				{
				setState(42);
				error();
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

	public static class IncludeContext extends ParserRuleContext {
		public Token file;
		public TerminalNode INCLUDE() { return getToken(PreprocessorParser.INCLUDE, 0); }
		public TerminalNode STRING() { return getToken(PreprocessorParser.STRING, 0); }
		public IncludeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_include; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).enterInclude(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).exitInclude(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PreprocessorVisitor ) return ((PreprocessorVisitor<? extends T>)visitor).visitInclude(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IncludeContext include() throws RecognitionException {
		IncludeContext _localctx = new IncludeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_include);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(INCLUDE);
			setState(46);
			((IncludeContext)_localctx).file = match(STRING);
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

	public static class DefineContext extends ParserRuleContext {
		public Token name;
		public TerminalNode DEFINE() { return getToken(PreprocessorParser.DEFINE, 0); }
		public TerminalNode ID() { return getToken(PreprocessorParser.ID, 0); }
		public MacroArgsContext macroArgs() {
			return getRuleContext(MacroArgsContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(PreprocessorParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(PreprocessorParser.NL, i);
		}
		public List<TerminalNode> EOF() { return getTokens(PreprocessorParser.EOF); }
		public TerminalNode EOF(int i) {
			return getToken(PreprocessorParser.EOF, i);
		}
		public DefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_define; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).enterDefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).exitDefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PreprocessorVisitor ) return ((PreprocessorVisitor<? extends T>)visitor).visitDefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefineContext define() throws RecognitionException {
		DefineContext _localctx = new DefineContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_define);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(DEFINE);
			setState(49);
			((DefineContext)_localctx).name = match(ID);
			setState(51);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(50);
				macroArgs();
				}
				break;
			}
			setState(56);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(53);
					_la = _input.LA(1);
					if ( _la <= 0 || (_la==EOF || _la==NL) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
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

	public static class MacroArgsContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(PreprocessorParser.LPAREN, 0); }
		public List<TerminalNode> ID() { return getTokens(PreprocessorParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PreprocessorParser.ID, i);
		}
		public TerminalNode RPAREN() { return getToken(PreprocessorParser.RPAREN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PreprocessorParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PreprocessorParser.COMMA, i);
		}
		public MacroArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macroArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).enterMacroArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).exitMacroArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PreprocessorVisitor ) return ((PreprocessorVisitor<? extends T>)visitor).visitMacroArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MacroArgsContext macroArgs() throws RecognitionException {
		MacroArgsContext _localctx = new MacroArgsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_macroArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(LPAREN);
			setState(60);
			match(ID);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(61);
				match(COMMA);
				setState(62);
				match(ID);
				}
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(68);
			match(RPAREN);
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

	public static class UndefineContext extends ParserRuleContext {
		public TerminalNode UNDEFINE() { return getToken(PreprocessorParser.UNDEFINE, 0); }
		public TerminalNode ID() { return getToken(PreprocessorParser.ID, 0); }
		public UndefineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_undefine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).enterUndefine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).exitUndefine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PreprocessorVisitor ) return ((PreprocessorVisitor<? extends T>)visitor).visitUndefine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UndefineContext undefine() throws RecognitionException {
		UndefineContext _localctx = new UndefineContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_undefine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(UNDEFINE);
			setState(71);
			match(ID);
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

	public static class PrepIfContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(PreprocessorParser.ID, 0); }
		public TerminalNode ENDIF() { return getToken(PreprocessorParser.ENDIF, 0); }
		public TerminalNode IF() { return getToken(PreprocessorParser.IF, 0); }
		public TerminalNode IFN() { return getToken(PreprocessorParser.IFN, 0); }
		public List<PreprocessorStatementContext> preprocessorStatement() {
			return getRuleContexts(PreprocessorStatementContext.class);
		}
		public PreprocessorStatementContext preprocessorStatement(int i) {
			return getRuleContext(PreprocessorStatementContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(PreprocessorParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(PreprocessorParser.NL, i);
		}
		public TerminalNode ELSE() { return getToken(PreprocessorParser.ELSE, 0); }
		public PrepIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prepIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).enterPrepIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).exitPrepIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PreprocessorVisitor ) return ((PreprocessorVisitor<? extends T>)visitor).visitPrepIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrepIfContext prepIf() throws RecognitionException {
		PrepIfContext _localctx = new PrepIfContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_prepIf);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			_la = _input.LA(1);
			if ( !(_la==IF || _la==IFN) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(74);
			match(ID);
			setState(83);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(76); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(75);
						match(NL);
						}
						}
						setState(78); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==NL );
					setState(80);
					preprocessorStatement();
					}
					} 
				}
				setState(85);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			setState(87); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(86);
					match(NL);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(89); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(103);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(91);
				match(ELSE);
				setState(100);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(93); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(92);
							match(NL);
							}
							}
							setState(95); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==NL );
						setState(97);
						preprocessorStatement();
						}
						} 
					}
					setState(102);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				}
				}
			}

			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(105);
				match(NL);
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(111);
			match(ENDIF);
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

	public static class ErrorContext extends ParserRuleContext {
		public Token instruction;
		public TerminalNode PREP_PREFIX() { return getToken(PreprocessorParser.PREP_PREFIX, 0); }
		public TerminalNode ID() { return getToken(PreprocessorParser.ID, 0); }
		public TerminalNode STRING() { return getToken(PreprocessorParser.STRING, 0); }
		public List<TerminalNode> NL() { return getTokens(PreprocessorParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(PreprocessorParser.NL, i);
		}
		public List<TerminalNode> EOF() { return getTokens(PreprocessorParser.EOF); }
		public TerminalNode EOF(int i) {
			return getToken(PreprocessorParser.EOF, i);
		}
		public ErrorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_error; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).enterError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).exitError(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PreprocessorVisitor ) return ((PreprocessorVisitor<? extends T>)visitor).visitError(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ErrorContext error() throws RecognitionException {
		ErrorContext _localctx = new ErrorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_error);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(PREP_PREFIX);
			setState(114);
			((ErrorContext)_localctx).instruction = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==ID) ) {
				((ErrorContext)_localctx).instruction = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(118);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(115);
					_la = _input.LA(1);
					if ( _la <= 0 || (_la==EOF || _la==NL) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					} 
				}
				setState(120);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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

	public static class OtherContext extends ParserRuleContext {
		public TerminalNode NL() { return getToken(PreprocessorParser.NL, 0); }
		public TerminalNode EOF() { return getToken(PreprocessorParser.EOF, 0); }
		public OtherContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_other; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).enterOther(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).exitOther(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PreprocessorVisitor ) return ((PreprocessorVisitor<? extends T>)visitor).visitOther(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OtherContext other() throws RecognitionException {
		OtherContext _localctx = new OtherContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_other);
		int _la;
		try {
			int _alt;
			setState(128);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				match(NL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(123); 
				_errHandler.sync(this);
				_alt = 1+1;
				do {
					switch (_alt) {
					case 1+1:
						{
						{
						setState(122);
						matchWildcard();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(125); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				} while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(127);
				_la = _input.LA(1);
				if ( !(_la==EOF || _la==NL) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\25\u0085\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3"+
		"\2\6\2\27\n\2\r\2\16\2\30\3\2\7\2\34\n\2\f\2\16\2\37\13\2\3\2\7\2\"\n"+
		"\2\f\2\16\2%\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3.\n\3\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\5\5\66\n\5\3\5\7\59\n\5\f\5\16\5<\13\5\3\6\3\6\3\6\3\6\7\6B\n"+
		"\6\f\6\16\6E\13\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\6\bO\n\b\r\b\16\bP\3"+
		"\b\7\bT\n\b\f\b\16\bW\13\b\3\b\6\bZ\n\b\r\b\16\b[\3\b\3\b\6\b`\n\b\r\b"+
		"\16\ba\3\b\7\be\n\b\f\b\16\bh\13\b\5\bj\n\b\3\b\7\bm\n\b\f\b\16\bp\13"+
		"\b\3\b\3\b\3\t\3\t\3\t\7\tw\n\t\f\t\16\tz\13\t\3\n\3\n\6\n~\n\n\r\n\16"+
		"\n\177\3\n\5\n\u0083\n\n\3\n\4U\177\2\13\2\4\6\b\n\f\16\20\22\2\5\3\3"+
		"\6\6\3\2\n\13\3\2\21\22\u0090\2#\3\2\2\2\4-\3\2\2\2\6/\3\2\2\2\b\62\3"+
		"\2\2\2\n=\3\2\2\2\fH\3\2\2\2\16K\3\2\2\2\20s\3\2\2\2\22\u0082\3\2\2\2"+
		"\24\35\5\4\3\2\25\27\7\6\2\2\26\25\3\2\2\2\27\30\3\2\2\2\30\26\3\2\2\2"+
		"\30\31\3\2\2\2\31\32\3\2\2\2\32\34\5\4\3\2\33\26\3\2\2\2\34\37\3\2\2\2"+
		"\35\33\3\2\2\2\35\36\3\2\2\2\36\"\3\2\2\2\37\35\3\2\2\2 \"\5\22\n\2!\24"+
		"\3\2\2\2! \3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$&\3\2\2\2%#\3\2\2\2"+
		"&\'\7\2\2\3\'\3\3\2\2\2(.\5\6\4\2).\5\b\5\2*.\5\f\7\2+.\5\16\b\2,.\5\20"+
		"\t\2-(\3\2\2\2-)\3\2\2\2-*\3\2\2\2-+\3\2\2\2-,\3\2\2\2.\5\3\2\2\2/\60"+
		"\7\7\2\2\60\61\7\21\2\2\61\7\3\2\2\2\62\63\7\b\2\2\63\65\7\22\2\2\64\66"+
		"\5\n\6\2\65\64\3\2\2\2\65\66\3\2\2\2\66:\3\2\2\2\679\n\2\2\28\67\3\2\2"+
		"\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;\t\3\2\2\2<:\3\2\2\2=>\7\16\2\2>C\7\22"+
		"\2\2?@\7\20\2\2@B\7\22\2\2A?\3\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2DF\3"+
		"\2\2\2EC\3\2\2\2FG\7\17\2\2G\13\3\2\2\2HI\7\t\2\2IJ\7\22\2\2J\r\3\2\2"+
		"\2KL\t\3\2\2LU\7\22\2\2MO\7\6\2\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2"+
		"\2\2QR\3\2\2\2RT\5\4\3\2SN\3\2\2\2TW\3\2\2\2UV\3\2\2\2US\3\2\2\2VY\3\2"+
		"\2\2WU\3\2\2\2XZ\7\6\2\2YX\3\2\2\2Z[\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\i\3"+
		"\2\2\2]f\7\f\2\2^`\7\6\2\2_^\3\2\2\2`a\3\2\2\2a_\3\2\2\2ab\3\2\2\2bc\3"+
		"\2\2\2ce\5\4\3\2d_\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2gj\3\2\2\2hf\3"+
		"\2\2\2i]\3\2\2\2ij\3\2\2\2jn\3\2\2\2km\7\6\2\2lk\3\2\2\2mp\3\2\2\2nl\3"+
		"\2\2\2no\3\2\2\2oq\3\2\2\2pn\3\2\2\2qr\7\r\2\2r\17\3\2\2\2st\7\5\2\2t"+
		"x\t\4\2\2uw\n\2\2\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\21\3\2\2"+
		"\2zx\3\2\2\2{\u0083\7\6\2\2|~\13\2\2\2}|\3\2\2\2~\177\3\2\2\2\177\u0080"+
		"\3\2\2\2\177}\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0083\t\2\2\2\u0082{\3"+
		"\2\2\2\u0082}\3\2\2\2\u0083\23\3\2\2\2\24\30\35!#-\65:CPU[afinx\177\u0082";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}