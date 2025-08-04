// Generated from Sense.g4 by ANTLR 4.13.1

package com.grammar.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SenseParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, INT=30, FLOAT=31, BOOLEAN=32, 
		STRING=33, FUNCTION=34, ID=35, ID_PATH=36, MACROARG=37, NAMESPACE=38, 
		LINECOMMENT=39, BLOCKCOMMENT=40, SPACES=41, SCRIPT=42, REGEX=43;
	public static final int
		RULE_sense = 0, RULE_nsSentence = 1, RULE_importSentence = 2, RULE_scriptSentence = 3, 
		RULE_primeSentence = 4, RULE_macroSentence = 5, RULE_productionSentence = 6, 
		RULE_exclusion = 7, RULE_annotation = 8, RULE_assigns = 9, RULE_assign = 10, 
		RULE_criteria = 11, RULE_catExpr = 12, RULE_catUnit = 13, RULE_catBranch = 14, 
		RULE_cat = 15, RULE_wordAttrExpr = 16, RULE_wordAttrBranch = 17, RULE_wordAttr = 18, 
		RULE_wordAttrUnit = 19, RULE_value = 20, RULE_array = 21, RULE_dict = 22, 
		RULE_dictItem = 23, RULE_expr = 24, RULE_unit = 25, RULE_idList = 26, 
		RULE_qualifiedName = 27, RULE_namespace = 28, RULE_rep = 29, RULE_repUnit = 30, 
		RULE_branch = 31;
	private static String[] makeRuleNames() {
		return new String[] {
			"sense", "nsSentence", "importSentence", "scriptSentence", "primeSentence", 
			"macroSentence", "productionSentence", "exclusion", "annotation", "assigns", 
			"assign", "criteria", "catExpr", "catUnit", "catBranch", "cat", "wordAttrExpr", 
			"wordAttrBranch", "wordAttr", "wordAttrUnit", "value", "array", "dict", 
			"dictItem", "expr", "unit", "idList", "qualifiedName", "namespace", "rep", 
			"repUnit", "branch"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'namespace'", "';'", "'import'", "'::'", "','", "':'", "'<'", 
			"'>'", "'('", "')'", "'!'", "'@'", "'='", "'['", "']'", "'&'", "'|'", 
			"'*'", "'{'", "'}'", "'^'", "'=='", "'!='", "'<*'", "'.'", "'${'", "'~'", 
			"'+'", "'?'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "INT", "FLOAT", "BOOLEAN", "STRING", 
			"FUNCTION", "ID", "ID_PATH", "MACROARG", "NAMESPACE", "LINECOMMENT", 
			"BLOCKCOMMENT", "SPACES", "SCRIPT", "REGEX"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "Sense.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SenseParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SenseContext extends ParserRuleContext {
		public NsSentenceContext nsSentence() {
			return getRuleContext(NsSentenceContext.class,0);
		}
		public TerminalNode EOF() { return getToken(SenseParser.EOF, 0); }
		public List<ImportSentenceContext> importSentence() {
			return getRuleContexts(ImportSentenceContext.class);
		}
		public ImportSentenceContext importSentence(int i) {
			return getRuleContext(ImportSentenceContext.class,i);
		}
		public List<ScriptSentenceContext> scriptSentence() {
			return getRuleContexts(ScriptSentenceContext.class);
		}
		public ScriptSentenceContext scriptSentence(int i) {
			return getRuleContext(ScriptSentenceContext.class,i);
		}
		public List<PrimeSentenceContext> primeSentence() {
			return getRuleContexts(PrimeSentenceContext.class);
		}
		public PrimeSentenceContext primeSentence(int i) {
			return getRuleContext(PrimeSentenceContext.class,i);
		}
		public List<MacroSentenceContext> macroSentence() {
			return getRuleContexts(MacroSentenceContext.class);
		}
		public MacroSentenceContext macroSentence(int i) {
			return getRuleContext(MacroSentenceContext.class,i);
		}
		public List<ProductionSentenceContext> productionSentence() {
			return getRuleContexts(ProductionSentenceContext.class);
		}
		public ProductionSentenceContext productionSentence(int i) {
			return getRuleContext(ProductionSentenceContext.class,i);
		}
		public SenseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sense; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterSense(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitSense(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitSense(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SenseContext sense() throws RecognitionException {
		SenseContext _localctx = new SenseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_sense);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			nsSentence();
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(65);
				importSentence();
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 51539611776L) != 0)) {
				{
				setState(75);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(71);
					scriptSentence();
					}
					break;
				case 2:
					{
					setState(72);
					primeSentence();
					}
					break;
				case 3:
					{
					setState(73);
					macroSentence();
					}
					break;
				case 4:
					{
					setState(74);
					productionSentence();
					}
					break;
				}
				}
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(80);
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

	@SuppressWarnings("CheckReturnValue")
	public static class NsSentenceContext extends ParserRuleContext {
		public NamespaceContext namespace() {
			return getRuleContext(NamespaceContext.class,0);
		}
		public NsSentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nsSentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterNsSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitNsSentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitNsSentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NsSentenceContext nsSentence() throws RecognitionException {
		NsSentenceContext _localctx = new NsSentenceContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_nsSentence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__0);
			setState(83);
			namespace();
			setState(84);
			match(T__1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ImportSentenceContext extends ParserRuleContext {
		public NamespaceContext namespace() {
			return getRuleContext(NamespaceContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(SenseParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SenseParser.ID, i);
		}
		public ImportSentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importSentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterImportSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitImportSentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitImportSentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportSentenceContext importSentence() throws RecognitionException {
		ImportSentenceContext _localctx = new ImportSentenceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_importSentence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(T__2);
			{
			setState(87);
			namespace();
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(88);
				match(T__3);
				setState(89);
				match(ID);
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(90);
					match(T__4);
					setState(91);
					match(ID);
					}
					}
					setState(96);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
			setState(99);
			match(T__1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ScriptSentenceContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(SenseParser.FUNCTION, 0); }
		public TerminalNode SCRIPT() { return getToken(SenseParser.SCRIPT, 0); }
		public ScriptSentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scriptSentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterScriptSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitScriptSentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitScriptSentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScriptSentenceContext scriptSentence() throws RecognitionException {
		ScriptSentenceContext _localctx = new ScriptSentenceContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_scriptSentence);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(FUNCTION);
			setState(102);
			match(T__5);
			setState(103);
			match(SCRIPT);
			setState(104);
			match(T__1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class PrimeSentenceContext extends ParserRuleContext {
		public Token name;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(SenseParser.ID, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public CriteriaContext criteria() {
			return getRuleContext(CriteriaContext.class,0);
		}
		public ExclusionContext exclusion() {
			return getRuleContext(ExclusionContext.class,0);
		}
		public PrimeSentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primeSentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterPrimeSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitPrimeSentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitPrimeSentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimeSentenceContext primeSentence() throws RecognitionException {
		PrimeSentenceContext _localctx = new PrimeSentenceContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_primeSentence);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(106);
					annotation();
					}
					} 
				}
				setState(111);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(112);
				criteria();
				}
			}

			setState(115);
			match(T__6);
			setState(116);
			((PrimeSentenceContext)_localctx).name = match(ID);
			setState(117);
			match(T__7);
			setState(118);
			match(T__5);
			setState(119);
			expr();
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(120);
				exclusion();
				}
			}

			setState(123);
			match(T__1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class MacroSentenceContext extends ParserRuleContext {
		public Token name;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(SenseParser.ID, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public IdListContext idList() {
			return getRuleContext(IdListContext.class,0);
		}
		public MacroSentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macroSentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterMacroSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitMacroSentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitMacroSentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MacroSentenceContext macroSentence() throws RecognitionException {
		MacroSentenceContext _localctx = new MacroSentenceContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_macroSentence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(125);
				annotation();
				}
				}
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(131);
			((MacroSentenceContext)_localctx).name = match(ID);
			setState(132);
			match(T__8);
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(133);
				idList();
				}
			}

			setState(136);
			match(T__9);
			setState(137);
			match(T__5);
			setState(138);
			expr();
			setState(139);
			match(T__1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ProductionSentenceContext extends ParserRuleContext {
		public Token name;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ID() { return getToken(SenseParser.ID, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public ExclusionContext exclusion() {
			return getRuleContext(ExclusionContext.class,0);
		}
		public ProductionSentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_productionSentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterProductionSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitProductionSentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitProductionSentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProductionSentenceContext productionSentence() throws RecognitionException {
		ProductionSentenceContext _localctx = new ProductionSentenceContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_productionSentence);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(141);
				annotation();
				}
				}
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(147);
			((ProductionSentenceContext)_localctx).name = match(ID);
			setState(148);
			match(T__5);
			setState(149);
			expr();
			setState(151);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(150);
				exclusion();
				}
			}

			setState(153);
			match(T__1);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExclusionContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExclusionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exclusion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterExclusion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitExclusion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitExclusion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExclusionContext exclusion() throws RecognitionException {
		ExclusionContext _localctx = new ExclusionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_exclusion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(T__10);
			setState(156);
			expr();
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

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SenseParser.ID, 0); }
		public AssignsContext assigns() {
			return getRuleContext(AssignsContext.class,0);
		}
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitAnnotation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitAnnotation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(T__11);
			setState(159);
			match(ID);
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(160);
				assigns();
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

	@SuppressWarnings("CheckReturnValue")
	public static class AssignsContext extends ParserRuleContext {
		public List<AssignContext> assign() {
			return getRuleContexts(AssignContext.class);
		}
		public AssignContext assign(int i) {
			return getRuleContext(AssignContext.class,i);
		}
		public AssignsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assigns; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterAssigns(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitAssigns(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitAssigns(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignsContext assigns() throws RecognitionException {
		AssignsContext _localctx = new AssignsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_assigns);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(T__8);
			setState(164);
			assign();
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(165);
				match(T__4);
				setState(166);
				assign();
				}
				}
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(172);
			match(T__9);
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

	@SuppressWarnings("CheckReturnValue")
	public static class AssignContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SenseParser.ID, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public AssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitAssign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignContext assign() throws RecognitionException {
		AssignContext _localctx = new AssignContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_assign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(ID);
			setState(175);
			match(T__12);
			setState(176);
			value();
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

	@SuppressWarnings("CheckReturnValue")
	public static class CriteriaContext extends ParserRuleContext {
		public CatExprContext catExpr() {
			return getRuleContext(CatExprContext.class,0);
		}
		public CriteriaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_criteria; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterCriteria(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitCriteria(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitCriteria(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CriteriaContext criteria() throws RecognitionException {
		CriteriaContext _localctx = new CriteriaContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_criteria);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(T__11);
			setState(179);
			match(T__13);
			setState(180);
			catExpr();
			setState(181);
			match(T__14);
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

	@SuppressWarnings("CheckReturnValue")
	public static class CatExprContext extends ParserRuleContext {
		public CatExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catExpr; }
	 
		public CatExprContext() { }
		public void copyFrom(CatExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CatExprAndContext extends CatExprContext {
		public List<CatUnitContext> catUnit() {
			return getRuleContexts(CatUnitContext.class);
		}
		public CatUnitContext catUnit(int i) {
			return getRuleContext(CatUnitContext.class,i);
		}
		public CatExprAndContext(CatExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterCatExprAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitCatExprAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitCatExprAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CatExprOrContext extends CatExprContext {
		public List<CatBranchContext> catBranch() {
			return getRuleContexts(CatBranchContext.class);
		}
		public CatBranchContext catBranch(int i) {
			return getRuleContext(CatBranchContext.class,i);
		}
		public CatExprOrContext(CatExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterCatExprOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitCatExprOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitCatExprOr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CatExprUnitContext extends CatExprContext {
		public CatUnitContext catUnit() {
			return getRuleContext(CatUnitContext.class,0);
		}
		public CatExprUnitContext(CatExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterCatExprUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitCatExprUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitCatExprUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CatExprContext catExpr() throws RecognitionException {
		CatExprContext _localctx = new CatExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_catExpr);
		int _la;
		try {
			setState(198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new CatExprAndContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(183);
				catUnit();
				setState(186); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(184);
					match(T__15);
					setState(185);
					catUnit();
					}
					}
					setState(188); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__15 );
				}
				break;
			case 2:
				_localctx = new CatExprOrContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(190);
				catBranch();
				setState(193); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(191);
					match(T__16);
					setState(192);
					catBranch();
					}
					}
					setState(195); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__16 );
				}
				break;
			case 3:
				_localctx = new CatExprUnitContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(197);
				catUnit();
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

	@SuppressWarnings("CheckReturnValue")
	public static class CatUnitContext extends ParserRuleContext {
		public Token not;
		public CatContext cat() {
			return getRuleContext(CatContext.class,0);
		}
		public CatUnitContext catUnit() {
			return getRuleContext(CatUnitContext.class,0);
		}
		public CatExprContext catExpr() {
			return getRuleContext(CatExprContext.class,0);
		}
		public CatUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterCatUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitCatUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitCatUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CatUnitContext catUnit() throws RecognitionException {
		CatUnitContext _localctx = new CatUnitContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_catUnit);
		try {
			setState(207);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__17:
			case ID:
			case ID_PATH:
			case MACROARG:
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				cat();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
				((CatUnitContext)_localctx).not = match(T__10);
				setState(202);
				catUnit();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 3);
				{
				setState(203);
				match(T__8);
				setState(204);
				catExpr();
				setState(205);
				match(T__9);
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

	@SuppressWarnings("CheckReturnValue")
	public static class CatBranchContext extends ParserRuleContext {
		public List<CatUnitContext> catUnit() {
			return getRuleContexts(CatUnitContext.class);
		}
		public CatUnitContext catUnit(int i) {
			return getRuleContext(CatUnitContext.class,i);
		}
		public CatBranchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catBranch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterCatBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitCatBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitCatBranch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CatBranchContext catBranch() throws RecognitionException {
		CatBranchContext _localctx = new CatBranchContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_catBranch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			catUnit();
			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__15) {
				{
				{
				setState(210);
				match(T__15);
				setState(211);
				catUnit();
				}
				}
				setState(216);
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

	@SuppressWarnings("CheckReturnValue")
	public static class CatContext extends ParserRuleContext {
		public Token catName;
		public TerminalNode ID() { return getToken(SenseParser.ID, 0); }
		public TerminalNode ID_PATH() { return getToken(SenseParser.ID_PATH, 0); }
		public TerminalNode MACROARG() { return getToken(SenseParser.MACROARG, 0); }
		public WordAttrExprContext wordAttrExpr() {
			return getRuleContext(WordAttrExprContext.class,0);
		}
		public CatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterCat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitCat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitCat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CatContext cat() throws RecognitionException {
		CatContext _localctx = new CatContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_cat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			((CatContext)_localctx).catName = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 240518430720L) != 0)) ) {
				((CatContext)_localctx).catName = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(218);
				match(T__18);
				setState(219);
				wordAttrExpr();
				setState(220);
				match(T__19);
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

	@SuppressWarnings("CheckReturnValue")
	public static class WordAttrExprContext extends ParserRuleContext {
		public WordAttrExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wordAttrExpr; }
	 
		public WordAttrExprContext() { }
		public void copyFrom(WordAttrExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WordAttrExprOrContext extends WordAttrExprContext {
		public List<WordAttrBranchContext> wordAttrBranch() {
			return getRuleContexts(WordAttrBranchContext.class);
		}
		public WordAttrBranchContext wordAttrBranch(int i) {
			return getRuleContext(WordAttrBranchContext.class,i);
		}
		public WordAttrExprOrContext(WordAttrExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterWordAttrExprOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitWordAttrExprOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitWordAttrExprOr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WordAttrExprAndContext extends WordAttrExprContext {
		public List<WordAttrUnitContext> wordAttrUnit() {
			return getRuleContexts(WordAttrUnitContext.class);
		}
		public WordAttrUnitContext wordAttrUnit(int i) {
			return getRuleContext(WordAttrUnitContext.class,i);
		}
		public WordAttrExprAndContext(WordAttrExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterWordAttrExprAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitWordAttrExprAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitWordAttrExprAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WordAttrExprUnitContext extends WordAttrExprContext {
		public WordAttrUnitContext wordAttrUnit() {
			return getRuleContext(WordAttrUnitContext.class,0);
		}
		public WordAttrExprUnitContext(WordAttrExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterWordAttrExprUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitWordAttrExprUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitWordAttrExprUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WordAttrExprContext wordAttrExpr() throws RecognitionException {
		WordAttrExprContext _localctx = new WordAttrExprContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_wordAttrExpr);
		int _la;
		try {
			setState(236);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				_localctx = new WordAttrExprAndContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(224);
				wordAttrUnit();
				setState(227); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(225);
					match(T__15);
					setState(226);
					wordAttrUnit();
					}
					}
					setState(229); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__15 );
				}
				break;
			case 2:
				_localctx = new WordAttrExprOrContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
				wordAttrBranch();
				{
				setState(232);
				match(T__16);
				setState(233);
				wordAttrBranch();
				}
				}
				break;
			case 3:
				_localctx = new WordAttrExprUnitContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(235);
				wordAttrUnit();
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

	@SuppressWarnings("CheckReturnValue")
	public static class WordAttrBranchContext extends ParserRuleContext {
		public List<WordAttrUnitContext> wordAttrUnit() {
			return getRuleContexts(WordAttrUnitContext.class);
		}
		public WordAttrUnitContext wordAttrUnit(int i) {
			return getRuleContext(WordAttrUnitContext.class,i);
		}
		public WordAttrBranchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wordAttrBranch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterWordAttrBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitWordAttrBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitWordAttrBranch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WordAttrBranchContext wordAttrBranch() throws RecognitionException {
		WordAttrBranchContext _localctx = new WordAttrBranchContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_wordAttrBranch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			wordAttrUnit();
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__15) {
				{
				{
				setState(239);
				match(T__15);
				setState(240);
				wordAttrUnit();
				}
				}
				setState(245);
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

	@SuppressWarnings("CheckReturnValue")
	public static class WordAttrContext extends ParserRuleContext {
		public Token attrName;
		public Token attrValue;
		public TerminalNode ID() { return getToken(SenseParser.ID, 0); }
		public List<TerminalNode> MACROARG() { return getTokens(SenseParser.MACROARG); }
		public TerminalNode MACROARG(int i) {
			return getToken(SenseParser.MACROARG, i);
		}
		public TerminalNode STRING() { return getToken(SenseParser.STRING, 0); }
		public WordAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wordAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterWordAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitWordAttr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitWordAttr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WordAttrContext wordAttr() throws RecognitionException {
		WordAttrContext _localctx = new WordAttrContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_wordAttr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			((WordAttrContext)_localctx).attrName = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==MACROARG) ) {
				((WordAttrContext)_localctx).attrName = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(249);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(247);
				match(T__12);
				setState(248);
				((WordAttrContext)_localctx).attrValue = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==STRING || _la==MACROARG) ) {
					((WordAttrContext)_localctx).attrValue = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class WordAttrUnitContext extends ParserRuleContext {
		public Token not;
		public WordAttrContext wordAttr() {
			return getRuleContext(WordAttrContext.class,0);
		}
		public WordAttrUnitContext wordAttrUnit() {
			return getRuleContext(WordAttrUnitContext.class,0);
		}
		public WordAttrExprContext wordAttrExpr() {
			return getRuleContext(WordAttrExprContext.class,0);
		}
		public WordAttrUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_wordAttrUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterWordAttrUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitWordAttrUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitWordAttrUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WordAttrUnitContext wordAttrUnit() throws RecognitionException {
		WordAttrUnitContext _localctx = new WordAttrUnitContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_wordAttrUnit);
		try {
			setState(258);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
			case MACROARG:
				enterOuterAlt(_localctx, 1);
				{
				setState(251);
				wordAttr();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(252);
				((WordAttrUnitContext)_localctx).not = match(T__10);
				setState(253);
				wordAttrUnit();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 3);
				{
				setState(254);
				match(T__8);
				setState(255);
				wordAttrExpr();
				setState(256);
				match(T__9);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(SenseParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(SenseParser.FLOAT, 0); }
		public TerminalNode BOOLEAN() { return getToken(SenseParser.BOOLEAN, 0); }
		public TerminalNode STRING() { return getToken(SenseParser.STRING, 0); }
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public DictContext dict() {
			return getRuleContext(DictContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_value);
		try {
			setState(266);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(260);
				match(INT);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(261);
				match(FLOAT);
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 3);
				{
				setState(262);
				match(BOOLEAN);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(263);
				match(STRING);
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 5);
				{
				setState(264);
				array();
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 6);
				{
				setState(265);
				dict();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			match(T__13);
			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 16106668032L) != 0)) {
				{
				setState(269);
				value();
				setState(274);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(270);
					match(T__4);
					setState(271);
					value();
					}
					}
					setState(276);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(279);
			match(T__14);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DictContext extends ParserRuleContext {
		public List<DictItemContext> dictItem() {
			return getRuleContexts(DictItemContext.class);
		}
		public DictItemContext dictItem(int i) {
			return getRuleContext(DictItemContext.class,i);
		}
		public DictContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dict; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterDict(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitDict(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitDict(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DictContext dict() throws RecognitionException {
		DictContext _localctx = new DictContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_dict);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			match(T__18);
			setState(293);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING || _la==ID) {
				{
				setState(282);
				dictItem();
				setState(287);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(283);
						match(T__4);
						setState(284);
						dictItem();
						}
						} 
					}
					setState(289);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
				}
				setState(291);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__4) {
					{
					setState(290);
					match(T__4);
					}
				}

				}
			}

			setState(295);
			match(T__19);
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

	@SuppressWarnings("CheckReturnValue")
	public static class DictItemContext extends ParserRuleContext {
		public Token key;
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode ID() { return getToken(SenseParser.ID, 0); }
		public TerminalNode STRING() { return getToken(SenseParser.STRING, 0); }
		public DictItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dictItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterDictItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitDictItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitDictItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DictItemContext dictItem() throws RecognitionException {
		DictItemContext _localctx = new DictItemContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_dictItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			((DictItemContext)_localctx).key = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==ID) ) {
				((DictItemContext)_localctx).key = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(298);
			match(T__5);
			setState(299);
			value();
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

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprSequenceContext extends ExprContext {
		public List<RepUnitContext> repUnit() {
			return getRuleContexts(RepUnitContext.class);
		}
		public RepUnitContext repUnit(int i) {
			return getRuleContext(RepUnitContext.class,i);
		}
		public ExprSequenceContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterExprSequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitExprSequence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitExprSequence(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprUnitContext extends ExprContext {
		public RepUnitContext repUnit() {
			return getRuleContext(RepUnitContext.class,0);
		}
		public ExprUnitContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterExprUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitExprUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitExprUnit(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprChoiceContext extends ExprContext {
		public List<BranchContext> branch() {
			return getRuleContexts(BranchContext.class);
		}
		public BranchContext branch(int i) {
			return getRuleContext(BranchContext.class,i);
		}
		public ExprChoiceContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterExprChoice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitExprChoice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitExprChoice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_expr);
		int _la;
		try {
			setState(315);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				_localctx = new ExprSequenceContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(301);
				repUnit();
				setState(303); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(302);
					repUnit();
					}
					}
					setState(305); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 13666852291200L) != 0) );
				}
				break;
			case 2:
				_localctx = new ExprChoiceContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(307);
				branch();
				setState(310); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(308);
					match(T__16);
					setState(309);
					branch();
					}
					}
					setState(312); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__16 );
				}
				break;
			case 3:
				_localctx = new ExprUnitContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(314);
				repUnit();
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

	@SuppressWarnings("CheckReturnValue")
	public static class UnitContext extends ParserRuleContext {
		public UnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unit; }
	 
		public UnitContext() { }
		public void copyFrom(UnitContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnitCharListContext extends UnitContext {
		public TerminalNode STRING() { return getToken(SenseParser.STRING, 0); }
		public UnitCharListContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterUnitCharList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitUnitCharList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitUnitCharList(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnitParenthesisContext extends UnitContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnitParenthesisContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterUnitParenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitUnitParenthesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitUnitParenthesis(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnitLiteralContext extends UnitContext {
		public TerminalNode STRING() { return getToken(SenseParser.STRING, 0); }
		public UnitLiteralContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterUnitLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitUnitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitUnitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnitPrimeAssignContext extends UnitContext {
		public AssignsContext assigns() {
			return getRuleContext(AssignsContext.class,0);
		}
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public UnitPrimeAssignContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterUnitPrimeAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitUnitPrimeAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitUnitPrimeAssign(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnitTagsContext extends UnitContext {
		public List<TerminalNode> ID() { return getTokens(SenseParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SenseParser.ID, i);
		}
		public UnitTagsContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterUnitTags(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitUnitTags(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitUnitTags(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnitScriptFunctionContext extends UnitContext {
		public TerminalNode FUNCTION() { return getToken(SenseParser.FUNCTION, 0); }
		public UnitScriptFunctionContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterUnitScriptFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitUnitScriptFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitUnitScriptFunction(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnitNoskipContext extends UnitContext {
		public UnitNoskipContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterUnitNoskip(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitUnitNoskip(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitUnitNoskip(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnitStatementAssignContext extends UnitContext {
		public AssignsContext assigns() {
			return getRuleContext(AssignsContext.class,0);
		}
		public UnitStatementAssignContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterUnitStatementAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitUnitStatementAssign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitUnitStatementAssign(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnitRegexContext extends UnitContext {
		public TerminalNode REGEX() { return getToken(SenseParser.REGEX, 0); }
		public UnitRegexContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterUnitRegex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitUnitRegex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitUnitRegex(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnitLookaheadLiteralContext extends UnitContext {
		public Token op;
		public TerminalNode STRING() { return getToken(SenseParser.STRING, 0); }
		public UnitLookaheadLiteralContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterUnitLookaheadLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitUnitLookaheadLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitUnitLookaheadLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnitMacroArgContext extends UnitContext {
		public TerminalNode MACROARG() { return getToken(SenseParser.MACROARG, 0); }
		public UnitMacroArgContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterUnitMacroArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitUnitMacroArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitUnitMacroArg(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnitMacroContext extends UnitContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public UnitMacroContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterUnitMacro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitUnitMacro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitUnitMacro(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnitPrimeContext extends UnitContext {
		public Token alias;
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode ID() { return getToken(SenseParser.ID, 0); }
		public UnitPrimeContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterUnitPrime(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitUnitPrime(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitUnitPrime(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnitScriptContext extends UnitContext {
		public TerminalNode SCRIPT() { return getToken(SenseParser.SCRIPT, 0); }
		public UnitScriptContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterUnitScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitUnitScript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitUnitScript(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnitReferenceContext extends UnitContext {
		public Token alias;
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public TerminalNode ID() { return getToken(SenseParser.ID, 0); }
		public UnitReferenceContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterUnitReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitUnitReference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitUnitReference(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnitAnyCharContext extends UnitContext {
		public UnitAnyCharContext(UnitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterUnitAnyChar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitUnitAnyChar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitUnitAnyChar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnitContext unit() throws RecognitionException {
		UnitContext _localctx = new UnitContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_unit);
		int _la;
		try {
			setState(383);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				_localctx = new UnitLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(317);
				match(STRING);
				}
				break;
			case 2:
				_localctx = new UnitCharListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(318);
				match(T__20);
				setState(319);
				match(STRING);
				}
				break;
			case 3:
				_localctx = new UnitLookaheadLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(320);
				((UnitLookaheadLiteralContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__21 || _la==T__22) ) {
					((UnitLookaheadLiteralContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(321);
				match(STRING);
				}
				break;
			case 4:
				_localctx = new UnitReferenceContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(322);
				qualifiedName();
				setState(325);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__12) {
					{
					setState(323);
					match(T__12);
					setState(324);
					((UnitReferenceContext)_localctx).alias = match(ID);
					}
				}

				}
				break;
			case 5:
				_localctx = new UnitMacroContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(327);
				match(T__13);
				setState(328);
				qualifiedName();
				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__8) {
					{
					setState(329);
					match(T__8);
					setState(330);
					expr();
					setState(335);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__4) {
						{
						{
						setState(331);
						match(T__4);
						setState(332);
						expr();
						}
						}
						setState(337);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(338);
					match(T__9);
					}
				}

				setState(342);
				match(T__14);
				}
				break;
			case 6:
				_localctx = new UnitPrimeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(344);
				match(T__6);
				setState(345);
				qualifiedName();
				setState(346);
				match(T__7);
				setState(349);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__12) {
					{
					setState(347);
					match(T__12);
					setState(348);
					((UnitPrimeContext)_localctx).alias = match(ID);
					}
				}

				}
				break;
			case 7:
				_localctx = new UnitPrimeAssignContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(351);
				match(T__23);
				setState(354);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
				case NAMESPACE:
					{
					setState(352);
					qualifiedName();
					}
					break;
				case T__24:
					{
					setState(353);
					match(T__24);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(356);
				assigns();
				setState(357);
				match(T__7);
				}
				break;
			case 8:
				_localctx = new UnitStatementAssignContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(359);
				match(T__23);
				setState(360);
				assigns();
				setState(361);
				match(T__7);
				}
				break;
			case 9:
				_localctx = new UnitParenthesisContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(363);
				match(T__8);
				setState(364);
				expr();
				setState(365);
				match(T__9);
				}
				break;
			case 10:
				_localctx = new UnitAnyCharContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(367);
				match(T__24);
				}
				break;
			case 11:
				_localctx = new UnitTagsContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(368);
				match(T__25);
				setState(369);
				match(ID);
				setState(374);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__16) {
					{
					{
					setState(370);
					match(T__16);
					setState(371);
					match(ID);
					}
					}
					setState(376);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(377);
				match(T__19);
				}
				break;
			case 12:
				_localctx = new UnitMacroArgContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(378);
				match(MACROARG);
				}
				break;
			case 13:
				_localctx = new UnitScriptFunctionContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(379);
				match(FUNCTION);
				}
				break;
			case 14:
				_localctx = new UnitScriptContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(380);
				match(SCRIPT);
				}
				break;
			case 15:
				_localctx = new UnitRegexContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(381);
				match(REGEX);
				}
				break;
			case 16:
				_localctx = new UnitNoskipContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(382);
				match(T__26);
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

	@SuppressWarnings("CheckReturnValue")
	public static class IdListContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(SenseParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SenseParser.ID, i);
		}
		public IdListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterIdList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitIdList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitIdList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdListContext idList() throws RecognitionException {
		IdListContext _localctx = new IdListContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_idList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(385);
			match(ID);
			setState(390);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(386);
				match(T__4);
				setState(387);
				match(ID);
				}
				}
				setState(392);
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

	@SuppressWarnings("CheckReturnValue")
	public static class QualifiedNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SenseParser.ID, 0); }
		public NamespaceContext namespace() {
			return getRuleContext(NamespaceContext.class,0);
		}
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterQualifiedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitQualifiedName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitQualifiedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_qualifiedName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(393);
				namespace();
				setState(394);
				match(T__3);
				}
				break;
			}
			setState(398);
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

	@SuppressWarnings("CheckReturnValue")
	public static class NamespaceContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SenseParser.ID, 0); }
		public TerminalNode NAMESPACE() { return getToken(SenseParser.NAMESPACE, 0); }
		public NamespaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterNamespace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitNamespace(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitNamespace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceContext namespace() throws RecognitionException {
		NamespaceContext _localctx = new NamespaceContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_namespace);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NAMESPACE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RepContext extends ParserRuleContext {
		public RepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rep; }
	 
		public RepContext() { }
		public void copyFrom(RepContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RepZeroOrOneContext extends RepContext {
		public RepZeroOrOneContext(RepContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterRepZeroOrOne(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitRepZeroOrOne(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitRepZeroOrOne(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RepOneOrMoreContext extends RepContext {
		public RepOneOrMoreContext(RepContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterRepOneOrMore(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitRepOneOrMore(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitRepOneOrMore(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RepRangeContext extends RepContext {
		public Token minCount;
		public Token sep;
		public Token maxCount;
		public List<TerminalNode> INT() { return getTokens(SenseParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(SenseParser.INT, i);
		}
		public RepRangeContext(RepContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterRepRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitRepRange(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitRepRange(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RepPreferContext extends RepContext {
		public RepPreferContext(RepContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterRepPrefer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitRepPrefer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitRepPrefer(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RepZeroOrMoreContext extends RepContext {
		public RepZeroOrMoreContext(RepContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterRepZeroOrMore(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitRepZeroOrMore(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitRepZeroOrMore(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepContext rep() throws RecognitionException {
		RepContext _localctx = new RepContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_rep);
		int _la;
		try {
			setState(419);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__27:
				_localctx = new RepOneOrMoreContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(402);
				match(T__27);
				}
				break;
			case T__17:
				_localctx = new RepZeroOrMoreContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(403);
				match(T__17);
				}
				break;
			case T__28:
				_localctx = new RepZeroOrOneContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(404);
				match(T__28);
				}
				break;
			case T__15:
				_localctx = new RepPreferContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(405);
				match(T__15);
				}
				break;
			case T__18:
				_localctx = new RepRangeContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(406);
				match(T__18);
				setState(416);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case INT:
					{
					setState(407);
					((RepRangeContext)_localctx).minCount = match(INT);
					setState(412);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__4) {
						{
						setState(408);
						((RepRangeContext)_localctx).sep = match(T__4);
						setState(410);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==INT) {
							{
							setState(409);
							((RepRangeContext)_localctx).maxCount = match(INT);
							}
						}

						}
					}

					}
					break;
				case T__4:
					{
					setState(414);
					match(T__4);
					setState(415);
					((RepRangeContext)_localctx).maxCount = match(INT);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(418);
				match(T__19);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RepUnitContext extends ParserRuleContext {
		public UnitContext unit() {
			return getRuleContext(UnitContext.class,0);
		}
		public RepContext rep() {
			return getRuleContext(RepContext.class,0);
		}
		public RepUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterRepUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitRepUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitRepUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepUnitContext repUnit() throws RecognitionException {
		RepUnitContext _localctx = new RepUnitContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_repUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
			unit();
			setState(423);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 806158336L) != 0)) {
				{
				setState(422);
				rep();
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

	@SuppressWarnings("CheckReturnValue")
	public static class BranchContext extends ParserRuleContext {
		public List<RepUnitContext> repUnit() {
			return getRuleContexts(RepUnitContext.class);
		}
		public RepUnitContext repUnit(int i) {
			return getRuleContext(RepUnitContext.class,i);
		}
		public BranchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_branch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).enterBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SenseListener ) ((SenseListener)listener).exitBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SenseVisitor ) return ((SenseVisitor<? extends T>)visitor).visitBranch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BranchContext branch() throws RecognitionException {
		BranchContext _localctx = new BranchContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_branch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(425);
				repUnit();
				}
				}
				setState(428); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 13666852291200L) != 0) );
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
		"\u0004\u0001+\u01af\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0001\u0000\u0001\u0000\u0005\u0000C\b\u0000"+
		"\n\u0000\f\u0000F\t\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0005\u0000L\b\u0000\n\u0000\f\u0000O\t\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002]\b\u0002"+
		"\n\u0002\f\u0002`\t\u0002\u0003\u0002b\b\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0005\u0004l\b\u0004\n\u0004\f\u0004o\t\u0004\u0001\u0004\u0003\u0004"+
		"r\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004z\b\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0005\u0005\u007f\b\u0005\n\u0005\f\u0005\u0082\t\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0003\u0005\u0087\b\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0005\u0006\u008f\b\u0006\n"+
		"\u0006\f\u0006\u0092\t\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006\u0098\b\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0003\b\u00a2\b\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0005\t\u00a8\b\t\n\t\f\t\u00ab\t\t\u0001\t\u0001\t"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0004\f\u00bb\b\f\u000b\f\f"+
		"\f\u00bc\u0001\f\u0001\f\u0001\f\u0004\f\u00c2\b\f\u000b\f\f\f\u00c3\u0001"+
		"\f\u0003\f\u00c7\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0003\r\u00d0\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00d5"+
		"\b\u000e\n\u000e\f\u000e\u00d8\t\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0003\u000f\u00df\b\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0004\u0010\u00e4\b\u0010\u000b\u0010\f\u0010\u00e5\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00ed"+
		"\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u00f2\b\u0011"+
		"\n\u0011\f\u0011\u00f5\t\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0003"+
		"\u0012\u00fa\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0103\b\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u010b"+
		"\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u0111"+
		"\b\u0015\n\u0015\f\u0015\u0114\t\u0015\u0003\u0015\u0116\b\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0005"+
		"\u0016\u011e\b\u0016\n\u0016\f\u0016\u0121\t\u0016\u0001\u0016\u0003\u0016"+
		"\u0124\b\u0016\u0003\u0016\u0126\b\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0004"+
		"\u0018\u0130\b\u0018\u000b\u0018\f\u0018\u0131\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0004\u0018\u0137\b\u0018\u000b\u0018\f\u0018\u0138\u0001"+
		"\u0018\u0003\u0018\u013c\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0146"+
		"\b\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0005\u0019\u014e\b\u0019\n\u0019\f\u0019\u0151\t\u0019\u0001\u0019"+
		"\u0001\u0019\u0003\u0019\u0155\b\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u015e\b\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0163\b\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u0175\b\u0019\n\u0019"+
		"\f\u0019\u0178\t\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0003\u0019\u0180\b\u0019\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0005\u001a\u0185\b\u001a\n\u001a\f\u001a\u0188\t\u001a\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u018d\b\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u019b"+
		"\b\u001d\u0003\u001d\u019d\b\u001d\u0001\u001d\u0001\u001d\u0003\u001d"+
		"\u01a1\b\u001d\u0001\u001d\u0003\u001d\u01a4\b\u001d\u0001\u001e\u0001"+
		"\u001e\u0003\u001e\u01a8\b\u001e\u0001\u001f\u0004\u001f\u01ab\b\u001f"+
		"\u000b\u001f\f\u001f\u01ac\u0001\u001f\u0000\u0000 \u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \""+
		"$&(*,.02468:<>\u0000\u0006\u0002\u0000\u0012\u0012#%\u0002\u0000##%%\u0002"+
		"\u0000!!%%\u0002\u0000!!##\u0001\u0000\u0016\u0017\u0002\u0000##&&\u01db"+
		"\u0000@\u0001\u0000\u0000\u0000\u0002R\u0001\u0000\u0000\u0000\u0004V"+
		"\u0001\u0000\u0000\u0000\u0006e\u0001\u0000\u0000\u0000\bm\u0001\u0000"+
		"\u0000\u0000\n\u0080\u0001\u0000\u0000\u0000\f\u0090\u0001\u0000\u0000"+
		"\u0000\u000e\u009b\u0001\u0000\u0000\u0000\u0010\u009e\u0001\u0000\u0000"+
		"\u0000\u0012\u00a3\u0001\u0000\u0000\u0000\u0014\u00ae\u0001\u0000\u0000"+
		"\u0000\u0016\u00b2\u0001\u0000\u0000\u0000\u0018\u00c6\u0001\u0000\u0000"+
		"\u0000\u001a\u00cf\u0001\u0000\u0000\u0000\u001c\u00d1\u0001\u0000\u0000"+
		"\u0000\u001e\u00d9\u0001\u0000\u0000\u0000 \u00ec\u0001\u0000\u0000\u0000"+
		"\"\u00ee\u0001\u0000\u0000\u0000$\u00f6\u0001\u0000\u0000\u0000&\u0102"+
		"\u0001\u0000\u0000\u0000(\u010a\u0001\u0000\u0000\u0000*\u010c\u0001\u0000"+
		"\u0000\u0000,\u0119\u0001\u0000\u0000\u0000.\u0129\u0001\u0000\u0000\u0000"+
		"0\u013b\u0001\u0000\u0000\u00002\u017f\u0001\u0000\u0000\u00004\u0181"+
		"\u0001\u0000\u0000\u00006\u018c\u0001\u0000\u0000\u00008\u0190\u0001\u0000"+
		"\u0000\u0000:\u01a3\u0001\u0000\u0000\u0000<\u01a5\u0001\u0000\u0000\u0000"+
		">\u01aa\u0001\u0000\u0000\u0000@D\u0003\u0002\u0001\u0000AC\u0003\u0004"+
		"\u0002\u0000BA\u0001\u0000\u0000\u0000CF\u0001\u0000\u0000\u0000DB\u0001"+
		"\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000EM\u0001\u0000\u0000\u0000"+
		"FD\u0001\u0000\u0000\u0000GL\u0003\u0006\u0003\u0000HL\u0003\b\u0004\u0000"+
		"IL\u0003\n\u0005\u0000JL\u0003\f\u0006\u0000KG\u0001\u0000\u0000\u0000"+
		"KH\u0001\u0000\u0000\u0000KI\u0001\u0000\u0000\u0000KJ\u0001\u0000\u0000"+
		"\u0000LO\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000MN\u0001\u0000"+
		"\u0000\u0000NP\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000PQ\u0005"+
		"\u0000\u0000\u0001Q\u0001\u0001\u0000\u0000\u0000RS\u0005\u0001\u0000"+
		"\u0000ST\u00038\u001c\u0000TU\u0005\u0002\u0000\u0000U\u0003\u0001\u0000"+
		"\u0000\u0000VW\u0005\u0003\u0000\u0000Wa\u00038\u001c\u0000XY\u0005\u0004"+
		"\u0000\u0000Y^\u0005#\u0000\u0000Z[\u0005\u0005\u0000\u0000[]\u0005#\u0000"+
		"\u0000\\Z\u0001\u0000\u0000\u0000]`\u0001\u0000\u0000\u0000^\\\u0001\u0000"+
		"\u0000\u0000^_\u0001\u0000\u0000\u0000_b\u0001\u0000\u0000\u0000`^\u0001"+
		"\u0000\u0000\u0000aX\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000"+
		"bc\u0001\u0000\u0000\u0000cd\u0005\u0002\u0000\u0000d\u0005\u0001\u0000"+
		"\u0000\u0000ef\u0005\"\u0000\u0000fg\u0005\u0006\u0000\u0000gh\u0005*"+
		"\u0000\u0000hi\u0005\u0002\u0000\u0000i\u0007\u0001\u0000\u0000\u0000"+
		"jl\u0003\u0010\b\u0000kj\u0001\u0000\u0000\u0000lo\u0001\u0000\u0000\u0000"+
		"mk\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000nq\u0001\u0000\u0000"+
		"\u0000om\u0001\u0000\u0000\u0000pr\u0003\u0016\u000b\u0000qp\u0001\u0000"+
		"\u0000\u0000qr\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000st\u0005"+
		"\u0007\u0000\u0000tu\u0005#\u0000\u0000uv\u0005\b\u0000\u0000vw\u0005"+
		"\u0006\u0000\u0000wy\u00030\u0018\u0000xz\u0003\u000e\u0007\u0000yx\u0001"+
		"\u0000\u0000\u0000yz\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000"+
		"{|\u0005\u0002\u0000\u0000|\t\u0001\u0000\u0000\u0000}\u007f\u0003\u0010"+
		"\b\u0000~}\u0001\u0000\u0000\u0000\u007f\u0082\u0001\u0000\u0000\u0000"+
		"\u0080~\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081"+
		"\u0083\u0001\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0083"+
		"\u0084\u0005#\u0000\u0000\u0084\u0086\u0005\t\u0000\u0000\u0085\u0087"+
		"\u00034\u001a\u0000\u0086\u0085\u0001\u0000\u0000\u0000\u0086\u0087\u0001"+
		"\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u0089\u0005"+
		"\n\u0000\u0000\u0089\u008a\u0005\u0006\u0000\u0000\u008a\u008b\u00030"+
		"\u0018\u0000\u008b\u008c\u0005\u0002\u0000\u0000\u008c\u000b\u0001\u0000"+
		"\u0000\u0000\u008d\u008f\u0003\u0010\b\u0000\u008e\u008d\u0001\u0000\u0000"+
		"\u0000\u008f\u0092\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000"+
		"\u0000\u0090\u0091\u0001\u0000\u0000\u0000\u0091\u0093\u0001\u0000\u0000"+
		"\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0093\u0094\u0005#\u0000\u0000"+
		"\u0094\u0095\u0005\u0006\u0000\u0000\u0095\u0097\u00030\u0018\u0000\u0096"+
		"\u0098\u0003\u000e\u0007\u0000\u0097\u0096\u0001\u0000\u0000\u0000\u0097"+
		"\u0098\u0001\u0000\u0000\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099"+
		"\u009a\u0005\u0002\u0000\u0000\u009a\r\u0001\u0000\u0000\u0000\u009b\u009c"+
		"\u0005\u000b\u0000\u0000\u009c\u009d\u00030\u0018\u0000\u009d\u000f\u0001"+
		"\u0000\u0000\u0000\u009e\u009f\u0005\f\u0000\u0000\u009f\u00a1\u0005#"+
		"\u0000\u0000\u00a0\u00a2\u0003\u0012\t\u0000\u00a1\u00a0\u0001\u0000\u0000"+
		"\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2\u0011\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a4\u0005\t\u0000\u0000\u00a4\u00a9\u0003\u0014\n\u0000"+
		"\u00a5\u00a6\u0005\u0005\u0000\u0000\u00a6\u00a8\u0003\u0014\n\u0000\u00a7"+
		"\u00a5\u0001\u0000\u0000\u0000\u00a8\u00ab\u0001\u0000\u0000\u0000\u00a9"+
		"\u00a7\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa"+
		"\u00ac\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ac"+
		"\u00ad\u0005\n\u0000\u0000\u00ad\u0013\u0001\u0000\u0000\u0000\u00ae\u00af"+
		"\u0005#\u0000\u0000\u00af\u00b0\u0005\r\u0000\u0000\u00b0\u00b1\u0003"+
		"(\u0014\u0000\u00b1\u0015\u0001\u0000\u0000\u0000\u00b2\u00b3\u0005\f"+
		"\u0000\u0000\u00b3\u00b4\u0005\u000e\u0000\u0000\u00b4\u00b5\u0003\u0018"+
		"\f\u0000\u00b5\u00b6\u0005\u000f\u0000\u0000\u00b6\u0017\u0001\u0000\u0000"+
		"\u0000\u00b7\u00ba\u0003\u001a\r\u0000\u00b8\u00b9\u0005\u0010\u0000\u0000"+
		"\u00b9\u00bb\u0003\u001a\r\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000\u00bb"+
		"\u00bc\u0001\u0000\u0000\u0000\u00bc\u00ba\u0001\u0000\u0000\u0000\u00bc"+
		"\u00bd\u0001\u0000\u0000\u0000\u00bd\u00c7\u0001\u0000\u0000\u0000\u00be"+
		"\u00c1\u0003\u001c\u000e\u0000\u00bf\u00c0\u0005\u0011\u0000\u0000\u00c0"+
		"\u00c2\u0003\u001c\u000e\u0000\u00c1\u00bf\u0001\u0000\u0000\u0000\u00c2"+
		"\u00c3\u0001\u0000\u0000\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c3"+
		"\u00c4\u0001\u0000\u0000\u0000\u00c4\u00c7\u0001\u0000\u0000\u0000\u00c5"+
		"\u00c7\u0003\u001a\r\u0000\u00c6\u00b7\u0001\u0000\u0000\u0000\u00c6\u00be"+
		"\u0001\u0000\u0000\u0000\u00c6\u00c5\u0001\u0000\u0000\u0000\u00c7\u0019"+
		"\u0001\u0000\u0000\u0000\u00c8\u00d0\u0003\u001e\u000f\u0000\u00c9\u00ca"+
		"\u0005\u000b\u0000\u0000\u00ca\u00d0\u0003\u001a\r\u0000\u00cb\u00cc\u0005"+
		"\t\u0000\u0000\u00cc\u00cd\u0003\u0018\f\u0000\u00cd\u00ce\u0005\n\u0000"+
		"\u0000\u00ce\u00d0\u0001\u0000\u0000\u0000\u00cf\u00c8\u0001\u0000\u0000"+
		"\u0000\u00cf\u00c9\u0001\u0000\u0000\u0000\u00cf\u00cb\u0001\u0000\u0000"+
		"\u0000\u00d0\u001b\u0001\u0000\u0000\u0000\u00d1\u00d6\u0003\u001a\r\u0000"+
		"\u00d2\u00d3\u0005\u0010\u0000\u0000\u00d3\u00d5\u0003\u001a\r\u0000\u00d4"+
		"\u00d2\u0001\u0000\u0000\u0000\u00d5\u00d8\u0001\u0000\u0000\u0000\u00d6"+
		"\u00d4\u0001\u0000\u0000\u0000\u00d6\u00d7\u0001\u0000\u0000\u0000\u00d7"+
		"\u001d\u0001\u0000\u0000\u0000\u00d8\u00d6\u0001\u0000\u0000\u0000\u00d9"+
		"\u00de\u0007\u0000\u0000\u0000\u00da\u00db\u0005\u0013\u0000\u0000\u00db"+
		"\u00dc\u0003 \u0010\u0000\u00dc\u00dd\u0005\u0014\u0000\u0000\u00dd\u00df"+
		"\u0001\u0000\u0000\u0000\u00de\u00da\u0001\u0000\u0000\u0000\u00de\u00df"+
		"\u0001\u0000\u0000\u0000\u00df\u001f\u0001\u0000\u0000\u0000\u00e0\u00e3"+
		"\u0003&\u0013\u0000\u00e1\u00e2\u0005\u0010\u0000\u0000\u00e2\u00e4\u0003"+
		"&\u0013\u0000\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e4\u00e5\u0001\u0000"+
		"\u0000\u0000\u00e5\u00e3\u0001\u0000\u0000\u0000\u00e5\u00e6\u0001\u0000"+
		"\u0000\u0000\u00e6\u00ed\u0001\u0000\u0000\u0000\u00e7\u00e8\u0003\"\u0011"+
		"\u0000\u00e8\u00e9\u0005\u0011\u0000\u0000\u00e9\u00ea\u0003\"\u0011\u0000"+
		"\u00ea\u00ed\u0001\u0000\u0000\u0000\u00eb\u00ed\u0003&\u0013\u0000\u00ec"+
		"\u00e0\u0001\u0000\u0000\u0000\u00ec\u00e7\u0001\u0000\u0000\u0000\u00ec"+
		"\u00eb\u0001\u0000\u0000\u0000\u00ed!\u0001\u0000\u0000\u0000\u00ee\u00f3"+
		"\u0003&\u0013\u0000\u00ef\u00f0\u0005\u0010\u0000\u0000\u00f0\u00f2\u0003"+
		"&\u0013\u0000\u00f1\u00ef\u0001\u0000\u0000\u0000\u00f2\u00f5\u0001\u0000"+
		"\u0000\u0000\u00f3\u00f1\u0001\u0000\u0000\u0000\u00f3\u00f4\u0001\u0000"+
		"\u0000\u0000\u00f4#\u0001\u0000\u0000\u0000\u00f5\u00f3\u0001\u0000\u0000"+
		"\u0000\u00f6\u00f9\u0007\u0001\u0000\u0000\u00f7\u00f8\u0005\r\u0000\u0000"+
		"\u00f8\u00fa\u0007\u0002\u0000\u0000\u00f9\u00f7\u0001\u0000\u0000\u0000"+
		"\u00f9\u00fa\u0001\u0000\u0000\u0000\u00fa%\u0001\u0000\u0000\u0000\u00fb"+
		"\u0103\u0003$\u0012\u0000\u00fc\u00fd\u0005\u000b\u0000\u0000\u00fd\u0103"+
		"\u0003&\u0013\u0000\u00fe\u00ff\u0005\t\u0000\u0000\u00ff\u0100\u0003"+
		" \u0010\u0000\u0100\u0101\u0005\n\u0000\u0000\u0101\u0103\u0001\u0000"+
		"\u0000\u0000\u0102\u00fb\u0001\u0000\u0000\u0000\u0102\u00fc\u0001\u0000"+
		"\u0000\u0000\u0102\u00fe\u0001\u0000\u0000\u0000\u0103\'\u0001\u0000\u0000"+
		"\u0000\u0104\u010b\u0005\u001e\u0000\u0000\u0105\u010b\u0005\u001f\u0000"+
		"\u0000\u0106\u010b\u0005 \u0000\u0000\u0107\u010b\u0005!\u0000\u0000\u0108"+
		"\u010b\u0003*\u0015\u0000\u0109\u010b\u0003,\u0016\u0000\u010a\u0104\u0001"+
		"\u0000\u0000\u0000\u010a\u0105\u0001\u0000\u0000\u0000\u010a\u0106\u0001"+
		"\u0000\u0000\u0000\u010a\u0107\u0001\u0000\u0000\u0000\u010a\u0108\u0001"+
		"\u0000\u0000\u0000\u010a\u0109\u0001\u0000\u0000\u0000\u010b)\u0001\u0000"+
		"\u0000\u0000\u010c\u0115\u0005\u000e\u0000\u0000\u010d\u0112\u0003(\u0014"+
		"\u0000\u010e\u010f\u0005\u0005\u0000\u0000\u010f\u0111\u0003(\u0014\u0000"+
		"\u0110\u010e\u0001\u0000\u0000\u0000\u0111\u0114\u0001\u0000\u0000\u0000"+
		"\u0112\u0110\u0001\u0000\u0000\u0000\u0112\u0113\u0001\u0000\u0000\u0000"+
		"\u0113\u0116\u0001\u0000\u0000\u0000\u0114\u0112\u0001\u0000\u0000\u0000"+
		"\u0115\u010d\u0001\u0000\u0000\u0000\u0115\u0116\u0001\u0000\u0000\u0000"+
		"\u0116\u0117\u0001\u0000\u0000\u0000\u0117\u0118\u0005\u000f\u0000\u0000"+
		"\u0118+\u0001\u0000\u0000\u0000\u0119\u0125\u0005\u0013\u0000\u0000\u011a"+
		"\u011f\u0003.\u0017\u0000\u011b\u011c\u0005\u0005\u0000\u0000\u011c\u011e"+
		"\u0003.\u0017\u0000\u011d\u011b\u0001\u0000\u0000\u0000\u011e\u0121\u0001"+
		"\u0000\u0000\u0000\u011f\u011d\u0001\u0000\u0000\u0000\u011f\u0120\u0001"+
		"\u0000\u0000\u0000\u0120\u0123\u0001\u0000\u0000\u0000\u0121\u011f\u0001"+
		"\u0000\u0000\u0000\u0122\u0124\u0005\u0005\u0000\u0000\u0123\u0122\u0001"+
		"\u0000\u0000\u0000\u0123\u0124\u0001\u0000\u0000\u0000\u0124\u0126\u0001"+
		"\u0000\u0000\u0000\u0125\u011a\u0001\u0000\u0000\u0000\u0125\u0126\u0001"+
		"\u0000\u0000\u0000\u0126\u0127\u0001\u0000\u0000\u0000\u0127\u0128\u0005"+
		"\u0014\u0000\u0000\u0128-\u0001\u0000\u0000\u0000\u0129\u012a\u0007\u0003"+
		"\u0000\u0000\u012a\u012b\u0005\u0006\u0000\u0000\u012b\u012c\u0003(\u0014"+
		"\u0000\u012c/\u0001\u0000\u0000\u0000\u012d\u012f\u0003<\u001e\u0000\u012e"+
		"\u0130\u0003<\u001e\u0000\u012f\u012e\u0001\u0000\u0000\u0000\u0130\u0131"+
		"\u0001\u0000\u0000\u0000\u0131\u012f\u0001\u0000\u0000\u0000\u0131\u0132"+
		"\u0001\u0000\u0000\u0000\u0132\u013c\u0001\u0000\u0000\u0000\u0133\u0136"+
		"\u0003>\u001f\u0000\u0134\u0135\u0005\u0011\u0000\u0000\u0135\u0137\u0003"+
		">\u001f\u0000\u0136\u0134\u0001\u0000\u0000\u0000\u0137\u0138\u0001\u0000"+
		"\u0000\u0000\u0138\u0136\u0001\u0000\u0000\u0000\u0138\u0139\u0001\u0000"+
		"\u0000\u0000\u0139\u013c\u0001\u0000\u0000\u0000\u013a\u013c\u0003<\u001e"+
		"\u0000\u013b\u012d\u0001\u0000\u0000\u0000\u013b\u0133\u0001\u0000\u0000"+
		"\u0000\u013b\u013a\u0001\u0000\u0000\u0000\u013c1\u0001\u0000\u0000\u0000"+
		"\u013d\u0180\u0005!\u0000\u0000\u013e\u013f\u0005\u0015\u0000\u0000\u013f"+
		"\u0180\u0005!\u0000\u0000\u0140\u0141\u0007\u0004\u0000\u0000\u0141\u0180"+
		"\u0005!\u0000\u0000\u0142\u0145\u00036\u001b\u0000\u0143\u0144\u0005\r"+
		"\u0000\u0000\u0144\u0146\u0005#\u0000\u0000\u0145\u0143\u0001\u0000\u0000"+
		"\u0000\u0145\u0146\u0001\u0000\u0000\u0000\u0146\u0180\u0001\u0000\u0000"+
		"\u0000\u0147\u0148\u0005\u000e\u0000\u0000\u0148\u0154\u00036\u001b\u0000"+
		"\u0149\u014a\u0005\t\u0000\u0000\u014a\u014f\u00030\u0018\u0000\u014b"+
		"\u014c\u0005\u0005\u0000\u0000\u014c\u014e\u00030\u0018\u0000\u014d\u014b"+
		"\u0001\u0000\u0000\u0000\u014e\u0151\u0001\u0000\u0000\u0000\u014f\u014d"+
		"\u0001\u0000\u0000\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150\u0152"+
		"\u0001\u0000\u0000\u0000\u0151\u014f\u0001\u0000\u0000\u0000\u0152\u0153"+
		"\u0005\n\u0000\u0000\u0153\u0155\u0001\u0000\u0000\u0000\u0154\u0149\u0001"+
		"\u0000\u0000\u0000\u0154\u0155\u0001\u0000\u0000\u0000\u0155\u0156\u0001"+
		"\u0000\u0000\u0000\u0156\u0157\u0005\u000f\u0000\u0000\u0157\u0180\u0001"+
		"\u0000\u0000\u0000\u0158\u0159\u0005\u0007\u0000\u0000\u0159\u015a\u0003"+
		"6\u001b\u0000\u015a\u015d\u0005\b\u0000\u0000\u015b\u015c\u0005\r\u0000"+
		"\u0000\u015c\u015e\u0005#\u0000\u0000\u015d\u015b\u0001\u0000\u0000\u0000"+
		"\u015d\u015e\u0001\u0000\u0000\u0000\u015e\u0180\u0001\u0000\u0000\u0000"+
		"\u015f\u0162\u0005\u0018\u0000\u0000\u0160\u0163\u00036\u001b\u0000\u0161"+
		"\u0163\u0005\u0019\u0000\u0000\u0162\u0160\u0001\u0000\u0000\u0000\u0162"+
		"\u0161\u0001\u0000\u0000\u0000\u0163\u0164\u0001\u0000\u0000\u0000\u0164"+
		"\u0165\u0003\u0012\t\u0000\u0165\u0166\u0005\b\u0000\u0000\u0166\u0180"+
		"\u0001\u0000\u0000\u0000\u0167\u0168\u0005\u0018\u0000\u0000\u0168\u0169"+
		"\u0003\u0012\t\u0000\u0169\u016a\u0005\b\u0000\u0000\u016a\u0180\u0001"+
		"\u0000\u0000\u0000\u016b\u016c\u0005\t\u0000\u0000\u016c\u016d\u00030"+
		"\u0018\u0000\u016d\u016e\u0005\n\u0000\u0000\u016e\u0180\u0001\u0000\u0000"+
		"\u0000\u016f\u0180\u0005\u0019\u0000\u0000\u0170\u0171\u0005\u001a\u0000"+
		"\u0000\u0171\u0176\u0005#\u0000\u0000\u0172\u0173\u0005\u0011\u0000\u0000"+
		"\u0173\u0175\u0005#\u0000\u0000\u0174\u0172\u0001\u0000\u0000\u0000\u0175"+
		"\u0178\u0001\u0000\u0000\u0000\u0176\u0174\u0001\u0000\u0000\u0000\u0176"+
		"\u0177\u0001\u0000\u0000\u0000\u0177\u0179\u0001\u0000\u0000\u0000\u0178"+
		"\u0176\u0001\u0000\u0000\u0000\u0179\u0180\u0005\u0014\u0000\u0000\u017a"+
		"\u0180\u0005%\u0000\u0000\u017b\u0180\u0005\"\u0000\u0000\u017c\u0180"+
		"\u0005*\u0000\u0000\u017d\u0180\u0005+\u0000\u0000\u017e\u0180\u0005\u001b"+
		"\u0000\u0000\u017f\u013d\u0001\u0000\u0000\u0000\u017f\u013e\u0001\u0000"+
		"\u0000\u0000\u017f\u0140\u0001\u0000\u0000\u0000\u017f\u0142\u0001\u0000"+
		"\u0000\u0000\u017f\u0147\u0001\u0000\u0000\u0000\u017f\u0158\u0001\u0000"+
		"\u0000\u0000\u017f\u015f\u0001\u0000\u0000\u0000\u017f\u0167\u0001\u0000"+
		"\u0000\u0000\u017f\u016b\u0001\u0000\u0000\u0000\u017f\u016f\u0001\u0000"+
		"\u0000\u0000\u017f\u0170\u0001\u0000\u0000\u0000\u017f\u017a\u0001\u0000"+
		"\u0000\u0000\u017f\u017b\u0001\u0000\u0000\u0000\u017f\u017c\u0001\u0000"+
		"\u0000\u0000\u017f\u017d\u0001\u0000\u0000\u0000\u017f\u017e\u0001\u0000"+
		"\u0000\u0000\u01803\u0001\u0000\u0000\u0000\u0181\u0186\u0005#\u0000\u0000"+
		"\u0182\u0183\u0005\u0005\u0000\u0000\u0183\u0185\u0005#\u0000\u0000\u0184"+
		"\u0182\u0001\u0000\u0000\u0000\u0185\u0188\u0001\u0000\u0000\u0000\u0186"+
		"\u0184\u0001\u0000\u0000\u0000\u0186\u0187\u0001\u0000\u0000\u0000\u0187"+
		"5\u0001\u0000\u0000\u0000\u0188\u0186\u0001\u0000\u0000\u0000\u0189\u018a"+
		"\u00038\u001c\u0000\u018a\u018b\u0005\u0004\u0000\u0000\u018b\u018d\u0001"+
		"\u0000\u0000\u0000\u018c\u0189\u0001\u0000\u0000\u0000\u018c\u018d\u0001"+
		"\u0000\u0000\u0000\u018d\u018e\u0001\u0000\u0000\u0000\u018e\u018f\u0005"+
		"#\u0000\u0000\u018f7\u0001\u0000\u0000\u0000\u0190\u0191\u0007\u0005\u0000"+
		"\u0000\u01919\u0001\u0000\u0000\u0000\u0192\u01a4\u0005\u001c\u0000\u0000"+
		"\u0193\u01a4\u0005\u0012\u0000\u0000\u0194\u01a4\u0005\u001d\u0000\u0000"+
		"\u0195\u01a4\u0005\u0010\u0000\u0000\u0196\u01a0\u0005\u0013\u0000\u0000"+
		"\u0197\u019c\u0005\u001e\u0000\u0000\u0198\u019a\u0005\u0005\u0000\u0000"+
		"\u0199\u019b\u0005\u001e\u0000\u0000\u019a\u0199\u0001\u0000\u0000\u0000"+
		"\u019a\u019b\u0001\u0000\u0000\u0000\u019b\u019d\u0001\u0000\u0000\u0000"+
		"\u019c\u0198\u0001\u0000\u0000\u0000\u019c\u019d\u0001\u0000\u0000\u0000"+
		"\u019d\u01a1\u0001\u0000\u0000\u0000\u019e\u019f\u0005\u0005\u0000\u0000"+
		"\u019f\u01a1\u0005\u001e\u0000\u0000\u01a0\u0197\u0001\u0000\u0000\u0000"+
		"\u01a0\u019e\u0001\u0000\u0000\u0000\u01a1\u01a2\u0001\u0000\u0000\u0000"+
		"\u01a2\u01a4\u0005\u0014\u0000\u0000\u01a3\u0192\u0001\u0000\u0000\u0000"+
		"\u01a3\u0193\u0001\u0000\u0000\u0000\u01a3\u0194\u0001\u0000\u0000\u0000"+
		"\u01a3\u0195\u0001\u0000\u0000\u0000\u01a3\u0196\u0001\u0000\u0000\u0000"+
		"\u01a4;\u0001\u0000\u0000\u0000\u01a5\u01a7\u00032\u0019\u0000\u01a6\u01a8"+
		"\u0003:\u001d\u0000\u01a7\u01a6\u0001\u0000\u0000\u0000\u01a7\u01a8\u0001"+
		"\u0000\u0000\u0000\u01a8=\u0001\u0000\u0000\u0000\u01a9\u01ab\u0003<\u001e"+
		"\u0000\u01aa\u01a9\u0001\u0000\u0000\u0000\u01ab\u01ac\u0001\u0000\u0000"+
		"\u0000\u01ac\u01aa\u0001\u0000\u0000\u0000\u01ac\u01ad\u0001\u0000\u0000"+
		"\u0000\u01ad?\u0001\u0000\u0000\u00001DKM^amqy\u0080\u0086\u0090\u0097"+
		"\u00a1\u00a9\u00bc\u00c3\u00c6\u00cf\u00d6\u00de\u00e5\u00ec\u00f3\u00f9"+
		"\u0102\u010a\u0112\u0115\u011f\u0123\u0125\u0131\u0138\u013b\u0145\u014f"+
		"\u0154\u015d\u0162\u0176\u017f\u0186\u018c\u019a\u019c\u01a0\u01a3\u01a7"+
		"\u01ac";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}