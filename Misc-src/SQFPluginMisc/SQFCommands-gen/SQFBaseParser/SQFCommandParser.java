// Generated from C:\Users\Robert Adam\Documents\Antlr\SQFCommand.g4 by ANTLR 4.1
package SQFBaseParser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SQFCommandParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__2=1, T__1=2, T__0=3, ID=4, WS=5;
	public static final String[] tokenNames = {
		"<INVALID>", "'\n'", "'='", "'alternative:'", "ID", "WS"
	};
	public static final int
		RULE_start = 0, RULE_commandSyntax = 1, RULE_command = 2, RULE_alternative = 3, 
		RULE_commandName = 4, RULE_type = 5, RULE_parameter = 6;
	public static final String[] ruleNames = {
		"start", "commandSyntax", "command", "alternative", "commandName", "type", 
		"parameter"
	};

	@Override
	public String getGrammarFileName() { return "SQFCommand.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public SQFCommandParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public CommandSyntaxContext commandSyntax(int i) {
			return getRuleContext(CommandSyntaxContext.class,i);
		}
		public List<CommandSyntaxContext> commandSyntax() {
			return getRuleContexts(CommandSyntaxContext.class);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFCommandListener ) ((SQFCommandListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFCommandListener ) ((SQFCommandListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFCommandVisitor ) return ((SQFCommandVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(14); commandSyntax();
				setState(15); match(1);
				}
				}
				setState(21);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class CommandSyntaxContext extends ParserRuleContext {
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
		public CommandNameContext commandName() {
			return getRuleContext(CommandNameContext.class,0);
		}
		public CommandSyntaxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commandSyntax; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFCommandListener ) ((SQFCommandListener)listener).enterCommandSyntax(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFCommandListener ) ((SQFCommandListener)listener).exitCommandSyntax(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFCommandVisitor ) return ((SQFCommandVisitor<? extends T>)visitor).visitCommandSyntax(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandSyntaxContext commandSyntax() throws RecognitionException {
		CommandSyntaxContext _localctx = new CommandSyntaxContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_commandSyntax);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22); commandName();
			setState(23); command();
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
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public AlternativeContext alternative() {
			return getRuleContext(AlternativeContext.class,0);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFCommandListener ) ((SQFCommandListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFCommandListener ) ((SQFCommandListener)listener).exitCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFCommandVisitor ) return ((SQFCommandVisitor<? extends T>)visitor).visitCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_command);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(25); type();
				setState(26); match(2);
				}
				break;
			}
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(30); parameter();
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(37);
			_la = _input.LA(1);
			if (_la==3) {
				{
				setState(36); alternative();
				}
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

	public static class AlternativeContext extends ParserRuleContext {
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
		public AlternativeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alternative; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFCommandListener ) ((SQFCommandListener)listener).enterAlternative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFCommandListener ) ((SQFCommandListener)listener).exitAlternative(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFCommandVisitor ) return ((SQFCommandVisitor<? extends T>)visitor).visitAlternative(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlternativeContext alternative() throws RecognitionException {
		AlternativeContext _localctx = new AlternativeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_alternative);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39); match(3);
			setState(40); command();
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

	public static class CommandNameContext extends ParserRuleContext {
		public Token name;
		public TerminalNode ID() { return getToken(SQFCommandParser.ID, 0); }
		public CommandNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commandName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFCommandListener ) ((SQFCommandListener)listener).enterCommandName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFCommandListener ) ((SQFCommandListener)listener).exitCommandName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFCommandVisitor ) return ((SQFCommandVisitor<? extends T>)visitor).visitCommandName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandNameContext commandName() throws RecognitionException {
		CommandNameContext _localctx = new CommandNameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_commandName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42); ((CommandNameContext)_localctx).name = match(ID);
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SQFCommandParser.ID, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFCommandListener ) ((SQFCommandListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFCommandListener ) ((SQFCommandListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFCommandVisitor ) return ((SQFCommandVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44); match(ID);
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

	public static class ParameterContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SQFCommandParser.ID, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQFCommandListener ) ((SQFCommandListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQFCommandListener ) ((SQFCommandListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQFCommandVisitor ) return ((SQFCommandVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46); match(ID);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\7\63\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\7\2\24\n\2\f"+
		"\2\16\2\27\13\2\3\3\3\3\3\3\3\4\3\4\3\4\5\4\37\n\4\3\4\7\4\"\n\4\f\4\16"+
		"\4%\13\4\3\4\5\4(\n\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\2\t\2\4"+
		"\6\b\n\f\16\2\2/\2\25\3\2\2\2\4\30\3\2\2\2\6\36\3\2\2\2\b)\3\2\2\2\n,"+
		"\3\2\2\2\f.\3\2\2\2\16\60\3\2\2\2\20\21\5\4\3\2\21\22\7\3\2\2\22\24\3"+
		"\2\2\2\23\20\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\3\3"+
		"\2\2\2\27\25\3\2\2\2\30\31\5\n\6\2\31\32\5\6\4\2\32\5\3\2\2\2\33\34\5"+
		"\f\7\2\34\35\7\4\2\2\35\37\3\2\2\2\36\33\3\2\2\2\36\37\3\2\2\2\37#\3\2"+
		"\2\2 \"\5\16\b\2! \3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$\'\3\2\2\2%"+
		"#\3\2\2\2&(\5\b\5\2\'&\3\2\2\2\'(\3\2\2\2(\7\3\2\2\2)*\7\5\2\2*+\5\6\4"+
		"\2+\t\3\2\2\2,-\7\6\2\2-\13\3\2\2\2./\7\6\2\2/\r\3\2\2\2\60\61\7\6\2\2"+
		"\61\17\3\2\2\2\6\25\36#\'";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}