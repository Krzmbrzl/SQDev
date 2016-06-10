package raven.sqdev.editors.stringtableParsing;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class StringTableParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INFO_TAG=1, OPEN_CLOSE=2, OPEN=3, CLOSE=4, EQUALS=5, PROJECT=6, NAME=7, 
		PKG=8, CONTAINER=9, KEY=10, IDENTIFIER=11, STRING=12, ID=13, WS=14, ANY=15;
	public static final String[] tokenNames = {
		"<INVALID>", "INFO_TAG", "'</'", "'<'", "'>'", "'='", "PROJECT", "NAME", 
		"PKG", "CONTAINER", "KEY", "IDENTIFIER", "STRING", "ID", "WS", "ANY"
	};
	public static final int
		RULE_content = 0, RULE_project = 1, RULE_projectStartTag = 2, RULE_projectEndTag = 3, 
		RULE_pkg = 4, RULE_packageStartTag = 5, RULE_packageEndTag = 6, RULE_container = 7, 
		RULE_containerStartTag = 8, RULE_containerEndTag = 9, RULE_key = 10, RULE_keyStartTag = 11, 
		RULE_keyCloseTag = 12, RULE_language = 13, RULE_languageOpenTag = 14, 
		RULE_languageCloseTag = 15;
	public static final String[] ruleNames = {
		"content", "project", "projectStartTag", "projectEndTag", "pkg", "packageStartTag", 
		"packageEndTag", "container", "containerStartTag", "containerEndTag", 
		"key", "keyStartTag", "keyCloseTag", "language", "languageOpenTag", "languageCloseTag"
	};

	@Override
	public String getGrammarFileName() { return "StringTable.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public StringTableParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ContentContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(StringTableParser.EOF, 0); }
		public TerminalNode INFO_TAG() { return getToken(StringTableParser.INFO_TAG, 0); }
		public ProjectContext project() {
			return getRuleContext(ProjectContext.class,0);
		}
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).exitContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof StringTableVisitor ) return ((StringTableVisitor<? extends T>)visitor).visitContent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_content);
		int _la;
		try {
			setState(37);
			switch (_input.LA(1)) {
			case INFO_TAG:
			case OPEN:
				enterOuterAlt(_localctx, 1);
				{
				setState(33);
				_la = _input.LA(1);
				if (_la==INFO_TAG) {
					{
					setState(32); match(INFO_TAG);
					}
				}

				setState(35); project();
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(36); match(EOF);
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

	public static class ProjectContext extends ParserRuleContext {
		public List<ContainerContext> container() {
			return getRuleContexts(ContainerContext.class);
		}
		public ProjectEndTagContext projectEndTag() {
			return getRuleContext(ProjectEndTagContext.class,0);
		}
		public List<KeyContext> key() {
			return getRuleContexts(KeyContext.class);
		}
		public ContainerContext container(int i) {
			return getRuleContext(ContainerContext.class,i);
		}
		public ProjectStartTagContext projectStartTag() {
			return getRuleContext(ProjectStartTagContext.class,0);
		}
		public KeyContext key(int i) {
			return getRuleContext(KeyContext.class,i);
		}
		public List<PkgContext> pkg() {
			return getRuleContexts(PkgContext.class);
		}
		public PkgContext pkg(int i) {
			return getRuleContext(PkgContext.class,i);
		}
		public ProjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_project; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).enterProject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).exitProject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof StringTableVisitor ) return ((StringTableVisitor<? extends T>)visitor).visitProject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProjectContext project() throws RecognitionException {
		ProjectContext _localctx = new ProjectContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_project);
		int _la;
		try {
			setState(66);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(39); projectStartTag();
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPEN) {
					{
					{
					setState(40); pkg();
					}
					}
					setState(45);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(46); projectEndTag();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(48); projectStartTag();
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPEN) {
					{
					{
					setState(49); container();
					}
					}
					setState(54);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(55); projectEndTag();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(57); projectStartTag();
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPEN) {
					{
					{
					setState(58); key();
					}
					}
					setState(63);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(64); projectEndTag();
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

	public static class ProjectStartTagContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(StringTableParser.EQUALS, 0); }
		public TerminalNode OPEN() { return getToken(StringTableParser.OPEN, 0); }
		public TerminalNode NAME() { return getToken(StringTableParser.NAME, 0); }
		public TerminalNode CLOSE() { return getToken(StringTableParser.CLOSE, 0); }
		public TerminalNode PROJECT() { return getToken(StringTableParser.PROJECT, 0); }
		public TerminalNode STRING() { return getToken(StringTableParser.STRING, 0); }
		public ProjectStartTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_projectStartTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).enterProjectStartTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).exitProjectStartTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof StringTableVisitor ) return ((StringTableVisitor<? extends T>)visitor).visitProjectStartTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProjectStartTagContext projectStartTag() throws RecognitionException {
		ProjectStartTagContext _localctx = new ProjectStartTagContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_projectStartTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68); match(OPEN);
			setState(69); match(PROJECT);
			setState(70); match(NAME);
			setState(71); match(EQUALS);
			setState(72); match(STRING);
			setState(73); match(CLOSE);
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

	public static class ProjectEndTagContext extends ParserRuleContext {
		public TerminalNode CLOSE() { return getToken(StringTableParser.CLOSE, 0); }
		public TerminalNode OPEN_CLOSE() { return getToken(StringTableParser.OPEN_CLOSE, 0); }
		public TerminalNode PROJECT() { return getToken(StringTableParser.PROJECT, 0); }
		public ProjectEndTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_projectEndTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).enterProjectEndTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).exitProjectEndTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof StringTableVisitor ) return ((StringTableVisitor<? extends T>)visitor).visitProjectEndTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProjectEndTagContext projectEndTag() throws RecognitionException {
		ProjectEndTagContext _localctx = new ProjectEndTagContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_projectEndTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75); match(OPEN_CLOSE);
			setState(76); match(PROJECT);
			setState(77); match(CLOSE);
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

	public static class PkgContext extends ParserRuleContext {
		public List<ContainerContext> container() {
			return getRuleContexts(ContainerContext.class);
		}
		public PackageStartTagContext packageStartTag() {
			return getRuleContext(PackageStartTagContext.class,0);
		}
		public PackageEndTagContext packageEndTag() {
			return getRuleContext(PackageEndTagContext.class,0);
		}
		public ContainerContext container(int i) {
			return getRuleContext(ContainerContext.class,i);
		}
		public PkgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pkg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).enterPkg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).exitPkg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof StringTableVisitor ) return ((StringTableVisitor<? extends T>)visitor).visitPkg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PkgContext pkg() throws RecognitionException {
		PkgContext _localctx = new PkgContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_pkg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79); packageStartTag();
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPEN) {
				{
				{
				setState(80); container();
				}
				}
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(86); packageEndTag();
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

	public static class PackageStartTagContext extends ParserRuleContext {
		public TerminalNode PKG() { return getToken(StringTableParser.PKG, 0); }
		public TerminalNode EQUALS() { return getToken(StringTableParser.EQUALS, 0); }
		public TerminalNode OPEN() { return getToken(StringTableParser.OPEN, 0); }
		public TerminalNode NAME() { return getToken(StringTableParser.NAME, 0); }
		public TerminalNode CLOSE() { return getToken(StringTableParser.CLOSE, 0); }
		public TerminalNode STRING() { return getToken(StringTableParser.STRING, 0); }
		public PackageStartTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageStartTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).enterPackageStartTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).exitPackageStartTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof StringTableVisitor ) return ((StringTableVisitor<? extends T>)visitor).visitPackageStartTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageStartTagContext packageStartTag() throws RecognitionException {
		PackageStartTagContext _localctx = new PackageStartTagContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_packageStartTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88); match(OPEN);
			setState(89); match(PKG);
			setState(90); match(NAME);
			setState(91); match(EQUALS);
			setState(92); match(STRING);
			setState(93); match(CLOSE);
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

	public static class PackageEndTagContext extends ParserRuleContext {
		public TerminalNode PKG() { return getToken(StringTableParser.PKG, 0); }
		public TerminalNode CLOSE() { return getToken(StringTableParser.CLOSE, 0); }
		public TerminalNode OPEN_CLOSE() { return getToken(StringTableParser.OPEN_CLOSE, 0); }
		public PackageEndTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageEndTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).enterPackageEndTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).exitPackageEndTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof StringTableVisitor ) return ((StringTableVisitor<? extends T>)visitor).visitPackageEndTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageEndTagContext packageEndTag() throws RecognitionException {
		PackageEndTagContext _localctx = new PackageEndTagContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_packageEndTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95); match(OPEN_CLOSE);
			setState(96); match(PKG);
			setState(97); match(CLOSE);
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

	public static class ContainerContext extends ParserRuleContext {
		public ContainerEndTagContext containerEndTag() {
			return getRuleContext(ContainerEndTagContext.class,0);
		}
		public List<KeyContext> key() {
			return getRuleContexts(KeyContext.class);
		}
		public ContainerStartTagContext containerStartTag() {
			return getRuleContext(ContainerStartTagContext.class,0);
		}
		public KeyContext key(int i) {
			return getRuleContext(KeyContext.class,i);
		}
		public ContainerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_container; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).enterContainer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).exitContainer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof StringTableVisitor ) return ((StringTableVisitor<? extends T>)visitor).visitContainer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContainerContext container() throws RecognitionException {
		ContainerContext _localctx = new ContainerContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_container);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99); containerStartTag();
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPEN) {
				{
				{
				setState(100); key();
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(106); containerEndTag();
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

	public static class ContainerStartTagContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(StringTableParser.EQUALS, 0); }
		public TerminalNode OPEN() { return getToken(StringTableParser.OPEN, 0); }
		public TerminalNode NAME() { return getToken(StringTableParser.NAME, 0); }
		public TerminalNode CLOSE() { return getToken(StringTableParser.CLOSE, 0); }
		public TerminalNode STRING() { return getToken(StringTableParser.STRING, 0); }
		public TerminalNode CONTAINER() { return getToken(StringTableParser.CONTAINER, 0); }
		public ContainerStartTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_containerStartTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).enterContainerStartTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).exitContainerStartTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof StringTableVisitor ) return ((StringTableVisitor<? extends T>)visitor).visitContainerStartTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContainerStartTagContext containerStartTag() throws RecognitionException {
		ContainerStartTagContext _localctx = new ContainerStartTagContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_containerStartTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108); match(OPEN);
			setState(109); match(CONTAINER);
			setState(110); match(NAME);
			setState(111); match(EQUALS);
			setState(112); match(STRING);
			setState(113); match(CLOSE);
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

	public static class ContainerEndTagContext extends ParserRuleContext {
		public TerminalNode CLOSE() { return getToken(StringTableParser.CLOSE, 0); }
		public TerminalNode OPEN_CLOSE() { return getToken(StringTableParser.OPEN_CLOSE, 0); }
		public TerminalNode CONTAINER() { return getToken(StringTableParser.CONTAINER, 0); }
		public ContainerEndTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_containerEndTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).enterContainerEndTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).exitContainerEndTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof StringTableVisitor ) return ((StringTableVisitor<? extends T>)visitor).visitContainerEndTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContainerEndTagContext containerEndTag() throws RecognitionException {
		ContainerEndTagContext _localctx = new ContainerEndTagContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_containerEndTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); match(OPEN_CLOSE);
			setState(116); match(CONTAINER);
			setState(117); match(CLOSE);
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

	public static class KeyContext extends ParserRuleContext {
		public KeyStartTagContext keyStartTag() {
			return getRuleContext(KeyStartTagContext.class,0);
		}
		public LanguageContext language(int i) {
			return getRuleContext(LanguageContext.class,i);
		}
		public List<LanguageContext> language() {
			return getRuleContexts(LanguageContext.class);
		}
		public KeyCloseTagContext keyCloseTag() {
			return getRuleContext(KeyCloseTagContext.class,0);
		}
		public KeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_key; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).enterKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).exitKey(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof StringTableVisitor ) return ((StringTableVisitor<? extends T>)visitor).visitKey(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyContext key() throws RecognitionException {
		KeyContext _localctx = new KeyContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_key);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119); keyStartTag();
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPEN) {
				{
				{
				setState(120); language();
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(126); keyCloseTag();
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

	public static class KeyStartTagContext extends ParserRuleContext {
		public TerminalNode KEY() { return getToken(StringTableParser.KEY, 0); }
		public TerminalNode EQUALS() { return getToken(StringTableParser.EQUALS, 0); }
		public TerminalNode OPEN() { return getToken(StringTableParser.OPEN, 0); }
		public TerminalNode CLOSE() { return getToken(StringTableParser.CLOSE, 0); }
		public TerminalNode STRING() { return getToken(StringTableParser.STRING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(StringTableParser.IDENTIFIER, 0); }
		public KeyStartTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyStartTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).enterKeyStartTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).exitKeyStartTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof StringTableVisitor ) return ((StringTableVisitor<? extends T>)visitor).visitKeyStartTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyStartTagContext keyStartTag() throws RecognitionException {
		KeyStartTagContext _localctx = new KeyStartTagContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_keyStartTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128); match(OPEN);
			setState(129); match(KEY);
			setState(130); match(IDENTIFIER);
			setState(131); match(EQUALS);
			setState(132); match(STRING);
			setState(133); match(CLOSE);
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

	public static class KeyCloseTagContext extends ParserRuleContext {
		public TerminalNode KEY() { return getToken(StringTableParser.KEY, 0); }
		public TerminalNode CLOSE() { return getToken(StringTableParser.CLOSE, 0); }
		public TerminalNode OPEN_CLOSE() { return getToken(StringTableParser.OPEN_CLOSE, 0); }
		public KeyCloseTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyCloseTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).enterKeyCloseTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).exitKeyCloseTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof StringTableVisitor ) return ((StringTableVisitor<? extends T>)visitor).visitKeyCloseTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeyCloseTagContext keyCloseTag() throws RecognitionException {
		KeyCloseTagContext _localctx = new KeyCloseTagContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_keyCloseTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135); match(OPEN_CLOSE);
			setState(136); match(KEY);
			setState(137); match(CLOSE);
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

	public static class LanguageContext extends ParserRuleContext {
		public LanguageCloseTagContext languageCloseTag() {
			return getRuleContext(LanguageCloseTagContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(StringTableParser.ID); }
		public List<TerminalNode> EQUALS() { return getTokens(StringTableParser.EQUALS); }
		public TerminalNode ANY(int i) {
			return getToken(StringTableParser.ANY, i);
		}
		public TerminalNode EQUALS(int i) {
			return getToken(StringTableParser.EQUALS, i);
		}
		public TerminalNode ID(int i) {
			return getToken(StringTableParser.ID, i);
		}
		public List<TerminalNode> ANY() { return getTokens(StringTableParser.ANY); }
		public LanguageOpenTagContext languageOpenTag() {
			return getRuleContext(LanguageOpenTagContext.class,0);
		}
		public LanguageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_language; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).enterLanguage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).exitLanguage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof StringTableVisitor ) return ((StringTableVisitor<? extends T>)visitor).visitLanguage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LanguageContext language() throws RecognitionException {
		LanguageContext _localctx = new LanguageContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_language);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(139); languageOpenTag();
			setState(143);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=1 && _alt!=-1 ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(140);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUALS) | (1L << ID) | (1L << ANY))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					}
					} 
				}
				setState(145);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			setState(146); languageCloseTag();
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

	public static class LanguageOpenTagContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(StringTableParser.ID, 0); }
		public TerminalNode OPEN() { return getToken(StringTableParser.OPEN, 0); }
		public TerminalNode CLOSE() { return getToken(StringTableParser.CLOSE, 0); }
		public LanguageOpenTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_languageOpenTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).enterLanguageOpenTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).exitLanguageOpenTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof StringTableVisitor ) return ((StringTableVisitor<? extends T>)visitor).visitLanguageOpenTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LanguageOpenTagContext languageOpenTag() throws RecognitionException {
		LanguageOpenTagContext _localctx = new LanguageOpenTagContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_languageOpenTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148); match(OPEN);
			setState(149); match(ID);
			setState(150); match(CLOSE);
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

	public static class LanguageCloseTagContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(StringTableParser.ID, 0); }
		public TerminalNode CLOSE() { return getToken(StringTableParser.CLOSE, 0); }
		public TerminalNode OPEN_CLOSE() { return getToken(StringTableParser.OPEN_CLOSE, 0); }
		public LanguageCloseTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_languageCloseTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).enterLanguageCloseTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof StringTableListener ) ((StringTableListener)listener).exitLanguageCloseTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof StringTableVisitor ) return ((StringTableVisitor<? extends T>)visitor).visitLanguageCloseTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LanguageCloseTagContext languageCloseTag() throws RecognitionException {
		LanguageCloseTagContext _localctx = new LanguageCloseTagContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_languageCloseTag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152); match(OPEN_CLOSE);
			setState(153); match(ID);
			setState(154); match(CLOSE);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\21\u009f\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\5\2"+
		"$\n\2\3\2\3\2\5\2(\n\2\3\3\3\3\7\3,\n\3\f\3\16\3/\13\3\3\3\3\3\3\3\3\3"+
		"\7\3\65\n\3\f\3\16\38\13\3\3\3\3\3\3\3\3\3\7\3>\n\3\f\3\16\3A\13\3\3\3"+
		"\3\3\5\3E\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\7\6"+
		"T\n\6\f\6\16\6W\13\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\7\th\n\t\f\t\16\tk\13\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\7\f|\n\f\f\f\16\f\177\13\f\3\f\3\f\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\7\17\u0090\n\17\f"+
		"\17\16\17\u0093\13\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\u0091\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\3\5\2\7\7\17"+
		"\17\21\21\u0099\2\'\3\2\2\2\4D\3\2\2\2\6F\3\2\2\2\bM\3\2\2\2\nQ\3\2\2"+
		"\2\fZ\3\2\2\2\16a\3\2\2\2\20e\3\2\2\2\22n\3\2\2\2\24u\3\2\2\2\26y\3\2"+
		"\2\2\30\u0082\3\2\2\2\32\u0089\3\2\2\2\34\u008d\3\2\2\2\36\u0096\3\2\2"+
		"\2 \u009a\3\2\2\2\"$\7\3\2\2#\"\3\2\2\2#$\3\2\2\2$%\3\2\2\2%(\5\4\3\2"+
		"&(\7\2\2\3\'#\3\2\2\2\'&\3\2\2\2(\3\3\2\2\2)-\5\6\4\2*,\5\n\6\2+*\3\2"+
		"\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\60\3\2\2\2/-\3\2\2\2\60\61\5\b\5\2"+
		"\61E\3\2\2\2\62\66\5\6\4\2\63\65\5\20\t\2\64\63\3\2\2\2\658\3\2\2\2\66"+
		"\64\3\2\2\2\66\67\3\2\2\2\679\3\2\2\28\66\3\2\2\29:\5\b\5\2:E\3\2\2\2"+
		";?\5\6\4\2<>\5\26\f\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@B\3\2\2"+
		"\2A?\3\2\2\2BC\5\b\5\2CE\3\2\2\2D)\3\2\2\2D\62\3\2\2\2D;\3\2\2\2E\5\3"+
		"\2\2\2FG\7\5\2\2GH\7\b\2\2HI\7\t\2\2IJ\7\7\2\2JK\7\16\2\2KL\7\6\2\2L\7"+
		"\3\2\2\2MN\7\4\2\2NO\7\b\2\2OP\7\6\2\2P\t\3\2\2\2QU\5\f\7\2RT\5\20\t\2"+
		"SR\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2VX\3\2\2\2WU\3\2\2\2XY\5\16\b"+
		"\2Y\13\3\2\2\2Z[\7\5\2\2[\\\7\n\2\2\\]\7\t\2\2]^\7\7\2\2^_\7\16\2\2_`"+
		"\7\6\2\2`\r\3\2\2\2ab\7\4\2\2bc\7\n\2\2cd\7\6\2\2d\17\3\2\2\2ei\5\22\n"+
		"\2fh\5\26\f\2gf\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2jl\3\2\2\2ki\3\2"+
		"\2\2lm\5\24\13\2m\21\3\2\2\2no\7\5\2\2op\7\13\2\2pq\7\t\2\2qr\7\7\2\2"+
		"rs\7\16\2\2st\7\6\2\2t\23\3\2\2\2uv\7\4\2\2vw\7\13\2\2wx\7\6\2\2x\25\3"+
		"\2\2\2y}\5\30\r\2z|\5\34\17\2{z\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2"+
		"\2~\u0080\3\2\2\2\177}\3\2\2\2\u0080\u0081\5\32\16\2\u0081\27\3\2\2\2"+
		"\u0082\u0083\7\5\2\2\u0083\u0084\7\f\2\2\u0084\u0085\7\r\2\2\u0085\u0086"+
		"\7\7\2\2\u0086\u0087\7\16\2\2\u0087\u0088\7\6\2\2\u0088\31\3\2\2\2\u0089"+
		"\u008a\7\4\2\2\u008a\u008b\7\f\2\2\u008b\u008c\7\6\2\2\u008c\33\3\2\2"+
		"\2\u008d\u0091\5\36\20\2\u008e\u0090\t\2\2\2\u008f\u008e\3\2\2\2\u0090"+
		"\u0093\3\2\2\2\u0091\u0092\3\2\2\2\u0091\u008f\3\2\2\2\u0092\u0094\3\2"+
		"\2\2\u0093\u0091\3\2\2\2\u0094\u0095\5 \21\2\u0095\35\3\2\2\2\u0096\u0097"+
		"\7\5\2\2\u0097\u0098\7\17\2\2\u0098\u0099\7\6\2\2\u0099\37\3\2\2\2\u009a"+
		"\u009b\7\4\2\2\u009b\u009c\7\17\2\2\u009c\u009d\7\6\2\2\u009d!\3\2\2\2"+
		"\f#\'-\66?DUi}\u0091";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}