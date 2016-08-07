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
		IF=1, IFN=2, ELSE=3, ENDIF=4, DEFINE=5, UNDEFINE=6, INCLUDE=7, UNKNOWN=8, 
		NUMBER=9, STRING=10, ID=11, ESC_LINEBREAK=12, LINEBREAK=13, WS=14, COMMENT=15, 
		ANY=16;
	public static final int
		RULE_start = 0, RULE_preprocessing = 1, RULE_ifBlock = 2, RULE_command = 3;
	public static final String[] ruleNames = {
		"start", "preprocessing", "ifBlock", "command"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'#ifdef'", "'#ifndef'", "'#else'", "'#endif'", "'#define'", "'#undef'", 
		"'#include'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "IF", "IFN", "ELSE", "ENDIF", "DEFINE", "UNDEFINE", "INCLUDE", "UNKNOWN", 
		"NUMBER", "STRING", "ID", "ESC_LINEBREAK", "LINEBREAK", "WS", "COMMENT", 
		"ANY"
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
		public List<PreprocessingContext> preprocessing() {
			return getRuleContexts(PreprocessingContext.class);
		}
		public PreprocessingContext preprocessing(int i) {
			return getRuleContext(PreprocessingContext.class,i);
		}
		public List<TerminalNode> ID() { return getTokens(PreprocessorParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PreprocessorParser.ID, i);
		}
		public List<TerminalNode> STRING() { return getTokens(PreprocessorParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(PreprocessorParser.STRING, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(PreprocessorParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(PreprocessorParser.NUMBER, i);
		}
		public List<TerminalNode> LINEBREAK() { return getTokens(PreprocessorParser.LINEBREAK); }
		public TerminalNode LINEBREAK(int i) {
			return getToken(PreprocessorParser.LINEBREAK, i);
		}
		public List<TerminalNode> ESC_LINEBREAK() { return getTokens(PreprocessorParser.ESC_LINEBREAK); }
		public TerminalNode ESC_LINEBREAK(int i) {
			return getToken(PreprocessorParser.ESC_LINEBREAK, i);
		}
		public TerminalNode EOF() { return getToken(PreprocessorParser.EOF, 0); }
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
			setState(15);
			switch (_input.LA(1)) {
			case IF:
			case IFN:
			case DEFINE:
			case UNDEFINE:
			case INCLUDE:
			case UNKNOWN:
			case NUMBER:
			case STRING:
			case ID:
			case ESC_LINEBREAK:
			case LINEBREAK:
				enterOuterAlt(_localctx, 1);
				{
				setState(10); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					setState(10);
					switch (_input.LA(1)) {
					case IF:
					case IFN:
					case DEFINE:
					case UNDEFINE:
					case INCLUDE:
					case UNKNOWN:
						{
						setState(8);
						preprocessing();
						}
						break;
					case NUMBER:
					case STRING:
					case ID:
					case ESC_LINEBREAK:
					case LINEBREAK:
						{
						setState(9);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUMBER) | (1L << STRING) | (1L << ID) | (1L << ESC_LINEBREAK) | (1L << LINEBREAK))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(12); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << IFN) | (1L << DEFINE) | (1L << UNDEFINE) | (1L << INCLUDE) | (1L << UNKNOWN) | (1L << NUMBER) | (1L << STRING) | (1L << ID) | (1L << ESC_LINEBREAK) | (1L << LINEBREAK))) != 0) );
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(14);
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

	public static class PreprocessingContext extends ParserRuleContext {
		public IfBlockContext ifBlock() {
			return getRuleContext(IfBlockContext.class,0);
		}
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
		public PreprocessingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preprocessing; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).enterPreprocessing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).exitPreprocessing(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PreprocessorVisitor ) return ((PreprocessorVisitor<? extends T>)visitor).visitPreprocessing(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreprocessingContext preprocessing() throws RecognitionException {
		PreprocessingContext _localctx = new PreprocessingContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_preprocessing);
		try {
			setState(19);
			switch (_input.LA(1)) {
			case IF:
			case IFN:
				enterOuterAlt(_localctx, 1);
				{
				setState(17);
				ifBlock();
				}
				break;
			case DEFINE:
			case UNDEFINE:
			case INCLUDE:
			case UNKNOWN:
				enterOuterAlt(_localctx, 2);
				{
				setState(18);
				command();
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

	public static class IfBlockContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(PreprocessorParser.ID, 0); }
		public List<TerminalNode> LINEBREAK() { return getTokens(PreprocessorParser.LINEBREAK); }
		public TerminalNode LINEBREAK(int i) {
			return getToken(PreprocessorParser.LINEBREAK, i);
		}
		public TerminalNode ENDIF() { return getToken(PreprocessorParser.ENDIF, 0); }
		public TerminalNode IF() { return getToken(PreprocessorParser.IF, 0); }
		public TerminalNode IFN() { return getToken(PreprocessorParser.IFN, 0); }
		public TerminalNode EOF() { return getToken(PreprocessorParser.EOF, 0); }
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(PreprocessorParser.ELSE, 0); }
		public IfBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).enterIfBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PreprocessorListener ) ((PreprocessorListener)listener).exitIfBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PreprocessorVisitor ) return ((PreprocessorVisitor<? extends T>)visitor).visitIfBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfBlockContext ifBlock() throws RecognitionException {
		IfBlockContext _localctx = new IfBlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_ifBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			_la = _input.LA(1);
			if ( !(_la==IF || _la==IFN) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(22);
			match(ID);
			setState(23);
			match(LINEBREAK);
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DEFINE) | (1L << UNDEFINE) | (1L << INCLUDE) | (1L << UNKNOWN))) != 0)) {
				{
				{
				setState(24);
				command();
				}
				}
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(38);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(30);
				match(ELSE);
				setState(31);
				match(LINEBREAK);
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DEFINE) | (1L << UNDEFINE) | (1L << INCLUDE) | (1L << UNKNOWN))) != 0)) {
					{
					{
					setState(32);
					command();
					}
					}
					setState(37);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(40);
			match(ENDIF);
			setState(41);
			_la = _input.LA(1);
			if ( !(_la==EOF || _la==LINEBREAK) ) {
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

	public static class CommandContext extends ParserRuleContext {
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
	 
		public CommandContext() { }
		public void copyFrom(CommandContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UndefineContext extends CommandContext {
		public Token name;
		public TerminalNode UNDEFINE() { return getToken(PreprocessorParser.UNDEFINE, 0); }
		public TerminalNode ID() { return getToken(PreprocessorParser.ID, 0); }
		public TerminalNode LINEBREAK() { return getToken(PreprocessorParser.LINEBREAK, 0); }
		public TerminalNode EOF() { return getToken(PreprocessorParser.EOF, 0); }
		public UndefineContext(CommandContext ctx) { copyFrom(ctx); }
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
	public static class DefineContext extends CommandContext {
		public Token name;
		public TerminalNode DEFINE() { return getToken(PreprocessorParser.DEFINE, 0); }
		public TerminalNode ID() { return getToken(PreprocessorParser.ID, 0); }
		public List<TerminalNode> LINEBREAK() { return getTokens(PreprocessorParser.LINEBREAK); }
		public TerminalNode LINEBREAK(int i) {
			return getToken(PreprocessorParser.LINEBREAK, i);
		}
		public TerminalNode EOF() { return getToken(PreprocessorParser.EOF, 0); }
		public DefineContext(CommandContext ctx) { copyFrom(ctx); }
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
	public static class IncludeContext extends CommandContext {
		public Token file;
		public TerminalNode INCLUDE() { return getToken(PreprocessorParser.INCLUDE, 0); }
		public TerminalNode STRING() { return getToken(PreprocessorParser.STRING, 0); }
		public TerminalNode LINEBREAK() { return getToken(PreprocessorParser.LINEBREAK, 0); }
		public TerminalNode EOF() { return getToken(PreprocessorParser.EOF, 0); }
		public IncludeContext(CommandContext ctx) { copyFrom(ctx); }
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
	public static class ErrorContext extends CommandContext {
		public TerminalNode UNKNOWN() { return getToken(PreprocessorParser.UNKNOWN, 0); }
		public List<TerminalNode> LINEBREAK() { return getTokens(PreprocessorParser.LINEBREAK); }
		public TerminalNode LINEBREAK(int i) {
			return getToken(PreprocessorParser.LINEBREAK, i);
		}
		public TerminalNode EOF() { return getToken(PreprocessorParser.EOF, 0); }
		public ErrorContext(CommandContext ctx) { copyFrom(ctx); }
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

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_command);
		int _la;
		try {
			int _alt;
			setState(66);
			switch (_input.LA(1)) {
			case DEFINE:
				_localctx = new DefineContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(43);
				match(DEFINE);
				setState(44);
				((DefineContext)_localctx).name = match(ID);
				setState(48);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(45);
						_la = _input.LA(1);
						if ( _la <= 0 || (_la==LINEBREAK) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						}
						} 
					}
					setState(50);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
				}
				setState(51);
				_la = _input.LA(1);
				if ( !(_la==EOF || _la==LINEBREAK) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case UNDEFINE:
				_localctx = new UndefineContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				match(UNDEFINE);
				setState(53);
				((UndefineContext)_localctx).name = match(ID);
				setState(54);
				_la = _input.LA(1);
				if ( !(_la==EOF || _la==LINEBREAK) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case INCLUDE:
				_localctx = new IncludeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(55);
				match(INCLUDE);
				setState(56);
				((IncludeContext)_localctx).file = match(STRING);
				setState(57);
				_la = _input.LA(1);
				if ( !(_la==EOF || _la==LINEBREAK) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case UNKNOWN:
				_localctx = new ErrorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(58);
				match(UNKNOWN);
				setState(62);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(59);
						_la = _input.LA(1);
						if ( _la <= 0 || (_la==LINEBREAK) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						}
						} 
					}
					setState(64);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				}
				setState(65);
				_la = _input.LA(1);
				if ( !(_la==EOF || _la==LINEBREAK) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\22G\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\6\2\r\n\2\r\2\16\2\16\3\2\5\2\22\n\2\3\3\3"+
		"\3\5\3\26\n\3\3\4\3\4\3\4\3\4\7\4\34\n\4\f\4\16\4\37\13\4\3\4\3\4\3\4"+
		"\7\4$\n\4\f\4\16\4\'\13\4\5\4)\n\4\3\4\3\4\3\4\3\5\3\5\3\5\7\5\61\n\5"+
		"\f\5\16\5\64\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5?\n\5\f\5\16"+
		"\5B\13\5\3\5\5\5E\n\5\3\5\4\62@\2\6\2\4\6\b\2\6\3\2\13\17\3\2\3\4\3\3"+
		"\17\17\3\2\17\17N\2\21\3\2\2\2\4\25\3\2\2\2\6\27\3\2\2\2\bD\3\2\2\2\n"+
		"\r\5\4\3\2\13\r\t\2\2\2\f\n\3\2\2\2\f\13\3\2\2\2\r\16\3\2\2\2\16\f\3\2"+
		"\2\2\16\17\3\2\2\2\17\22\3\2\2\2\20\22\7\2\2\3\21\f\3\2\2\2\21\20\3\2"+
		"\2\2\22\3\3\2\2\2\23\26\5\6\4\2\24\26\5\b\5\2\25\23\3\2\2\2\25\24\3\2"+
		"\2\2\26\5\3\2\2\2\27\30\t\3\2\2\30\31\7\r\2\2\31\35\7\17\2\2\32\34\5\b"+
		"\5\2\33\32\3\2\2\2\34\37\3\2\2\2\35\33\3\2\2\2\35\36\3\2\2\2\36(\3\2\2"+
		"\2\37\35\3\2\2\2 !\7\5\2\2!%\7\17\2\2\"$\5\b\5\2#\"\3\2\2\2$\'\3\2\2\2"+
		"%#\3\2\2\2%&\3\2\2\2&)\3\2\2\2\'%\3\2\2\2( \3\2\2\2()\3\2\2\2)*\3\2\2"+
		"\2*+\7\6\2\2+,\t\4\2\2,\7\3\2\2\2-.\7\7\2\2.\62\7\r\2\2/\61\n\5\2\2\60"+
		"/\3\2\2\2\61\64\3\2\2\2\62\63\3\2\2\2\62\60\3\2\2\2\63\65\3\2\2\2\64\62"+
		"\3\2\2\2\65E\t\4\2\2\66\67\7\b\2\2\678\7\r\2\28E\t\4\2\29:\7\t\2\2:;\7"+
		"\f\2\2;E\t\4\2\2<@\7\n\2\2=?\n\5\2\2>=\3\2\2\2?B\3\2\2\2@A\3\2\2\2@>\3"+
		"\2\2\2AC\3\2\2\2B@\3\2\2\2CE\t\4\2\2D-\3\2\2\2D\66\3\2\2\2D9\3\2\2\2D"+
		"<\3\2\2\2E\t\3\2\2\2\f\f\16\21\25\35%(\62@D";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}