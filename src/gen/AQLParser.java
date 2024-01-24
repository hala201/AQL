// Generated from AQLParser.g4 by ANTLR 4.13.1
package gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class AQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FOR_EACH=1, IN=2, SET=3, AS=4, GET=5, POST=6, PUT=7, DELETE=8, WITH=9, 
		ON=10, ON_ERROR=11, ON_SUCCESS=12, ELSE=13, LOG=14, EQUAL=15, NOT_EQUAL=16, 
		LESS=17, GREATER=18, LESS_EQUAL=19, GREATER_EQUAL=20, OPEN_BRACE=21, CLOSE_BRACE=22, 
		COLON=23, COMMA=24, DOT=25, QUOTEDOUBLE=26, QUOTESINGLE=27, URI=28, URI_TAIL=29, 
		VARIABLE=30, ALPHANUM=31, NUMBER=32, WS=33;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_loop = 2, RULE_set = 3, RULE_log = 4, 
		RULE_onElse = 5, RULE_withBlock = 6, RULE_request = 7, RULE_getReq = 8, 
		RULE_postReq = 9, RULE_putReq = 10, RULE_delReq = 11, RULE_onSuccess = 12, 
		RULE_onError = 13, RULE_condition = 14, RULE_params = 15, RULE_param = 16, 
		RULE_dynamicURI = 17, RULE_dynamicVar = 18, RULE_string = 19, RULE_value = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "loop", "set", "log", "onElse", "withBlock", 
			"request", "getReq", "postReq", "putReq", "delReq", "onSuccess", "onError", 
			"condition", "params", "param", "dynamicURI", "dynamicVar", "string", 
			"value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'FOR EACH'", "'IN'", "'SET'", "'AS'", "'GET'", "'POST'", "'PUT'", 
			"'DELETE'", "'WITH'", "'ON'", "'ON ERROR'", "'ON SUCCESS'", "'ELSE'", 
			"'LOG'", "'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'{'", "'}'", 
			"':'", "','", "'.'", "'\"'", "'''"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "FOR_EACH", "IN", "SET", "AS", "GET", "POST", "PUT", "DELETE", 
			"WITH", "ON", "ON_ERROR", "ON_SUCCESS", "ELSE", "LOG", "EQUAL", "NOT_EQUAL", 
			"LESS", "GREATER", "LESS_EQUAL", "GREATER_EQUAL", "OPEN_BRACE", "CLOSE_BRACE", 
			"COLON", "COMMA", "DOT", "QUOTEDOUBLE", "QUOTESINGLE", "URI", "URI_TAIL", 
			"VARIABLE", "ALPHANUM", "NUMBER", "WS"
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
	public String getGrammarFileName() { return "AQLParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(42);
				statement();
				}
				}
				setState(45); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 16874L) != 0) );
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
	public static class StatementContext extends ParserRuleContext {
		public RequestContext request() {
			return getRuleContext(RequestContext.class,0);
		}
		public LoopContext loop() {
			return getRuleContext(LoopContext.class,0);
		}
		public LogContext log() {
			return getRuleContext(LogContext.class,0);
		}
		public SetContext set() {
			return getRuleContext(SetContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(51);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case GET:
			case POST:
			case PUT:
			case DELETE:
				enterOuterAlt(_localctx, 1);
				{
				setState(47);
				request();
				}
				break;
			case FOR_EACH:
				enterOuterAlt(_localctx, 2);
				{
				setState(48);
				loop();
				}
				break;
			case LOG:
				enterOuterAlt(_localctx, 3);
				{
				setState(49);
				log();
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 4);
				{
				setState(50);
				set();
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
	public static class LoopContext extends ParserRuleContext {
		public TerminalNode FOR_EACH() { return getToken(AQLParser.FOR_EACH, 0); }
		public TerminalNode IN() { return getToken(AQLParser.IN, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(AQLParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(AQLParser.CLOSE_BRACE, 0); }
		public List<DynamicVarContext> dynamicVar() {
			return getRuleContexts(DynamicVarContext.class);
		}
		public DynamicVarContext dynamicVar(int i) {
			return getRuleContext(DynamicVarContext.class,i);
		}
		public List<TerminalNode> VARIABLE() { return getTokens(AQLParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(AQLParser.VARIABLE, i);
		}
		public RequestContext request() {
			return getRuleContext(RequestContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public LoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopContext loop() throws RecognitionException {
		LoopContext _localctx = new LoopContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_loop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(FOR_EACH);
			setState(56);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(54);
				dynamicVar();
				}
				break;
			case 2:
				{
				setState(55);
				match(VARIABLE);
				}
				break;
			}
			setState(58);
			match(IN);
			setState(62);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(59);
				request();
				}
				break;
			case 2:
				{
				setState(60);
				dynamicVar();
				}
				break;
			case 3:
				{
				setState(61);
				match(VARIABLE);
				}
				break;
			}
			setState(64);
			match(OPEN_BRACE);
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(65);
				statement();
				}
				}
				setState(68); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 16874L) != 0) );
			setState(70);
			match(CLOSE_BRACE);
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
	public static class SetContext extends ParserRuleContext {
		public TerminalNode SET() { return getToken(AQLParser.SET, 0); }
		public TerminalNode AS() { return getToken(AQLParser.AS, 0); }
		public TerminalNode VARIABLE() { return getToken(AQLParser.VARIABLE, 0); }
		public RequestContext request() {
			return getRuleContext(RequestContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public SetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitSet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetContext set() throws RecognitionException {
		SetContext _localctx = new SetContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_set);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(SET);
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case GET:
			case POST:
			case PUT:
			case DELETE:
				{
				setState(73);
				request();
				}
				break;
			case OPEN_BRACE:
			case VARIABLE:
			case NUMBER:
				{
				setState(74);
				value();
				}
				break;
			case QUOTEDOUBLE:
			case QUOTESINGLE:
				{
				setState(75);
				string();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(78);
			match(AS);
			setState(79);
			match(VARIABLE);
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
	public static class LogContext extends ParserRuleContext {
		public TerminalNode LOG() { return getToken(AQLParser.LOG, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public LogContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_log; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterLog(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitLog(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitLog(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogContext log() throws RecognitionException {
		LogContext _localctx = new LogContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_log);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(LOG);
			setState(84);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
			case VARIABLE:
			case NUMBER:
				{
				setState(82);
				value();
				}
				break;
			case QUOTEDOUBLE:
			case QUOTESINGLE:
				{
				setState(83);
				string();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class OnElseContext extends ParserRuleContext {
		public TerminalNode ON() { return getToken(AQLParser.ON, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<TerminalNode> OPEN_BRACE() { return getTokens(AQLParser.OPEN_BRACE); }
		public TerminalNode OPEN_BRACE(int i) {
			return getToken(AQLParser.OPEN_BRACE, i);
		}
		public List<TerminalNode> CLOSE_BRACE() { return getTokens(AQLParser.CLOSE_BRACE); }
		public TerminalNode CLOSE_BRACE(int i) {
			return getToken(AQLParser.CLOSE_BRACE, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(AQLParser.ELSE, 0); }
		public OnElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_onElse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterOnElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitOnElse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitOnElse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OnElseContext onElse() throws RecognitionException {
		OnElseContext _localctx = new OnElseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_onElse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(ON);
			setState(87);
			condition();
			setState(88);
			match(OPEN_BRACE);
			setState(90); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(89);
				statement();
				}
				}
				setState(92); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 16874L) != 0) );
			setState(94);
			match(CLOSE_BRACE);
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(95);
				match(ELSE);
				setState(96);
				match(OPEN_BRACE);
				setState(98); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(97);
					statement();
					}
					}
					setState(100); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 16874L) != 0) );
				setState(102);
				match(CLOSE_BRACE);
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
	public static class WithBlockContext extends ParserRuleContext {
		public TerminalNode WITH() { return getToken(AQLParser.WITH, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(AQLParser.OPEN_BRACE, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(AQLParser.CLOSE_BRACE, 0); }
		public WithBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_withBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterWithBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitWithBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitWithBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WithBlockContext withBlock() throws RecognitionException {
		WithBlockContext _localctx = new WithBlockContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_withBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(WITH);
			setState(107);
			match(OPEN_BRACE);
			setState(108);
			params();
			setState(109);
			match(CLOSE_BRACE);
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
	public static class RequestContext extends ParserRuleContext {
		public GetReqContext getReq() {
			return getRuleContext(GetReqContext.class,0);
		}
		public PostReqContext postReq() {
			return getRuleContext(PostReqContext.class,0);
		}
		public PutReqContext putReq() {
			return getRuleContext(PutReqContext.class,0);
		}
		public DelReqContext delReq() {
			return getRuleContext(DelReqContext.class,0);
		}
		public RequestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_request; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterRequest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitRequest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitRequest(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RequestContext request() throws RecognitionException {
		RequestContext _localctx = new RequestContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_request);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case GET:
				{
				setState(111);
				getReq();
				}
				break;
			case POST:
				{
				setState(112);
				postReq();
				}
				break;
			case PUT:
				{
				setState(113);
				putReq();
				}
				break;
			case DELETE:
				{
				setState(114);
				delReq();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class GetReqContext extends ParserRuleContext {
		public TerminalNode GET() { return getToken(AQLParser.GET, 0); }
		public DynamicURIContext dynamicURI() {
			return getRuleContext(DynamicURIContext.class,0);
		}
		public WithBlockContext withBlock() {
			return getRuleContext(WithBlockContext.class,0);
		}
		public List<OnSuccessContext> onSuccess() {
			return getRuleContexts(OnSuccessContext.class);
		}
		public OnSuccessContext onSuccess(int i) {
			return getRuleContext(OnSuccessContext.class,i);
		}
		public List<OnErrorContext> onError() {
			return getRuleContexts(OnErrorContext.class);
		}
		public OnErrorContext onError(int i) {
			return getRuleContext(OnErrorContext.class,i);
		}
		public List<OnElseContext> onElse() {
			return getRuleContexts(OnElseContext.class);
		}
		public OnElseContext onElse(int i) {
			return getRuleContext(OnElseContext.class,i);
		}
		public GetReqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_getReq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterGetReq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitGetReq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitGetReq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GetReqContext getReq() throws RecognitionException {
		GetReqContext _localctx = new GetReqContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_getReq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(GET);
			setState(118);
			dynamicURI();
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(119);
				withBlock();
				}
			}

			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7168L) != 0)) {
				{
				setState(125);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ON_SUCCESS:
					{
					setState(122);
					onSuccess();
					}
					break;
				case ON_ERROR:
					{
					setState(123);
					onError();
					}
					break;
				case ON:
					{
					setState(124);
					onElse();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(129);
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
	public static class PostReqContext extends ParserRuleContext {
		public TerminalNode POST() { return getToken(AQLParser.POST, 0); }
		public DynamicURIContext dynamicURI() {
			return getRuleContext(DynamicURIContext.class,0);
		}
		public WithBlockContext withBlock() {
			return getRuleContext(WithBlockContext.class,0);
		}
		public List<OnSuccessContext> onSuccess() {
			return getRuleContexts(OnSuccessContext.class);
		}
		public OnSuccessContext onSuccess(int i) {
			return getRuleContext(OnSuccessContext.class,i);
		}
		public List<OnErrorContext> onError() {
			return getRuleContexts(OnErrorContext.class);
		}
		public OnErrorContext onError(int i) {
			return getRuleContext(OnErrorContext.class,i);
		}
		public List<OnElseContext> onElse() {
			return getRuleContexts(OnElseContext.class);
		}
		public OnElseContext onElse(int i) {
			return getRuleContext(OnElseContext.class,i);
		}
		public PostReqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postReq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterPostReq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitPostReq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitPostReq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostReqContext postReq() throws RecognitionException {
		PostReqContext _localctx = new PostReqContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_postReq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(POST);
			setState(131);
			dynamicURI();
			setState(133);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(132);
				withBlock();
				}
			}

			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7168L) != 0)) {
				{
				setState(138);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ON_SUCCESS:
					{
					setState(135);
					onSuccess();
					}
					break;
				case ON_ERROR:
					{
					setState(136);
					onError();
					}
					break;
				case ON:
					{
					setState(137);
					onElse();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(142);
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
	public static class PutReqContext extends ParserRuleContext {
		public TerminalNode PUT() { return getToken(AQLParser.PUT, 0); }
		public DynamicURIContext dynamicURI() {
			return getRuleContext(DynamicURIContext.class,0);
		}
		public WithBlockContext withBlock() {
			return getRuleContext(WithBlockContext.class,0);
		}
		public List<OnSuccessContext> onSuccess() {
			return getRuleContexts(OnSuccessContext.class);
		}
		public OnSuccessContext onSuccess(int i) {
			return getRuleContext(OnSuccessContext.class,i);
		}
		public List<OnErrorContext> onError() {
			return getRuleContexts(OnErrorContext.class);
		}
		public OnErrorContext onError(int i) {
			return getRuleContext(OnErrorContext.class,i);
		}
		public List<OnElseContext> onElse() {
			return getRuleContexts(OnElseContext.class);
		}
		public OnElseContext onElse(int i) {
			return getRuleContext(OnElseContext.class,i);
		}
		public PutReqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_putReq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterPutReq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitPutReq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitPutReq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PutReqContext putReq() throws RecognitionException {
		PutReqContext _localctx = new PutReqContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_putReq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(PUT);
			setState(144);
			dynamicURI();
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(145);
				withBlock();
				}
			}

			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7168L) != 0)) {
				{
				setState(151);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ON_SUCCESS:
					{
					setState(148);
					onSuccess();
					}
					break;
				case ON_ERROR:
					{
					setState(149);
					onError();
					}
					break;
				case ON:
					{
					setState(150);
					onElse();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(155);
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
	public static class DelReqContext extends ParserRuleContext {
		public TerminalNode DELETE() { return getToken(AQLParser.DELETE, 0); }
		public DynamicURIContext dynamicURI() {
			return getRuleContext(DynamicURIContext.class,0);
		}
		public WithBlockContext withBlock() {
			return getRuleContext(WithBlockContext.class,0);
		}
		public List<OnSuccessContext> onSuccess() {
			return getRuleContexts(OnSuccessContext.class);
		}
		public OnSuccessContext onSuccess(int i) {
			return getRuleContext(OnSuccessContext.class,i);
		}
		public List<OnErrorContext> onError() {
			return getRuleContexts(OnErrorContext.class);
		}
		public OnErrorContext onError(int i) {
			return getRuleContext(OnErrorContext.class,i);
		}
		public List<OnElseContext> onElse() {
			return getRuleContexts(OnElseContext.class);
		}
		public OnElseContext onElse(int i) {
			return getRuleContext(OnElseContext.class,i);
		}
		public DelReqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delReq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterDelReq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitDelReq(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitDelReq(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DelReqContext delReq() throws RecognitionException {
		DelReqContext _localctx = new DelReqContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_delReq);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(DELETE);
			setState(157);
			dynamicURI();
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(158);
				withBlock();
				}
			}

			setState(166);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 7168L) != 0)) {
				{
				setState(164);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ON_SUCCESS:
					{
					setState(161);
					onSuccess();
					}
					break;
				case ON_ERROR:
					{
					setState(162);
					onError();
					}
					break;
				case ON:
					{
					setState(163);
					onElse();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(168);
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
	public static class OnSuccessContext extends ParserRuleContext {
		public TerminalNode ON_SUCCESS() { return getToken(AQLParser.ON_SUCCESS, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(AQLParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(AQLParser.CLOSE_BRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public OnSuccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_onSuccess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterOnSuccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitOnSuccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitOnSuccess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OnSuccessContext onSuccess() throws RecognitionException {
		OnSuccessContext _localctx = new OnSuccessContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_onSuccess);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(ON_SUCCESS);
			setState(170);
			match(OPEN_BRACE);
			setState(172); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(171);
				statement();
				}
				}
				setState(174); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 16874L) != 0) );
			setState(176);
			match(CLOSE_BRACE);
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
	public static class OnErrorContext extends ParserRuleContext {
		public TerminalNode ON_ERROR() { return getToken(AQLParser.ON_ERROR, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(AQLParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(AQLParser.CLOSE_BRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public OnErrorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_onError; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterOnError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitOnError(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitOnError(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OnErrorContext onError() throws RecognitionException {
		OnErrorContext _localctx = new OnErrorContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_onError);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(ON_ERROR);
			setState(179);
			match(OPEN_BRACE);
			setState(181); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(180);
				statement();
				}
				}
				setState(183); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 16874L) != 0) );
			setState(185);
			match(CLOSE_BRACE);
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
	public static class ConditionContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode EQUAL() { return getToken(AQLParser.EQUAL, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(AQLParser.NOT_EQUAL, 0); }
		public TerminalNode LESS() { return getToken(AQLParser.LESS, 0); }
		public TerminalNode GREATER() { return getToken(AQLParser.GREATER, 0); }
		public TerminalNode LESS_EQUAL() { return getToken(AQLParser.LESS_EQUAL, 0); }
		public TerminalNode GREATER_EQUAL() { return getToken(AQLParser.GREATER_EQUAL, 0); }
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			value();
			setState(188);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2064384L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(189);
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
	public static class ParamsContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AQLParser.COMMA, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			param();
			setState(196);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(192);
				match(COMMA);
				setState(193);
				param();
				}
				}
				setState(198);
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
	public static class ParamContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(AQLParser.VARIABLE, 0); }
		public TerminalNode COLON() { return getToken(AQLParser.COLON, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(VARIABLE);
			setState(200);
			match(COLON);
			setState(203);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
			case VARIABLE:
			case NUMBER:
				{
				setState(201);
				value();
				}
				break;
			case QUOTEDOUBLE:
			case QUOTESINGLE:
				{
				setState(202);
				string();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class DynamicURIContext extends ParserRuleContext {
		public TerminalNode URI() { return getToken(AQLParser.URI, 0); }
		public List<DynamicVarContext> dynamicVar() {
			return getRuleContexts(DynamicVarContext.class);
		}
		public DynamicVarContext dynamicVar(int i) {
			return getRuleContext(DynamicVarContext.class,i);
		}
		public List<TerminalNode> URI_TAIL() { return getTokens(AQLParser.URI_TAIL); }
		public TerminalNode URI_TAIL(int i) {
			return getToken(AQLParser.URI_TAIL, i);
		}
		public DynamicURIContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dynamicURI; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterDynamicURI(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitDynamicURI(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitDynamicURI(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DynamicURIContext dynamicURI() throws RecognitionException {
		DynamicURIContext _localctx = new DynamicURIContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_dynamicURI);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(URI);
			setState(215);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(206);
					dynamicVar();
					setState(210);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==URI_TAIL) {
						{
						{
						setState(207);
						match(URI_TAIL);
						}
						}
						setState(212);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					} 
				}
				setState(217);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
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
	public static class DynamicVarContext extends ParserRuleContext {
		public List<TerminalNode> VARIABLE() { return getTokens(AQLParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(AQLParser.VARIABLE, i);
		}
		public TerminalNode DOT() { return getToken(AQLParser.DOT, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(AQLParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(AQLParser.CLOSE_BRACE, 0); }
		public DynamicVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dynamicVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterDynamicVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitDynamicVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitDynamicVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DynamicVarContext dynamicVar() throws RecognitionException {
		DynamicVarContext _localctx = new DynamicVarContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_dynamicVar);
		try {
			setState(226);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIABLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				match(VARIABLE);
				setState(219);
				match(DOT);
				setState(220);
				match(VARIABLE);
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(221);
				match(OPEN_BRACE);
				setState(222);
				match(VARIABLE);
				setState(223);
				match(DOT);
				setState(224);
				match(VARIABLE);
				setState(225);
				match(CLOSE_BRACE);
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
	public static class StringContext extends ParserRuleContext {
		public List<TerminalNode> QUOTEDOUBLE() { return getTokens(AQLParser.QUOTEDOUBLE); }
		public TerminalNode QUOTEDOUBLE(int i) {
			return getToken(AQLParser.QUOTEDOUBLE, i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> QUOTESINGLE() { return getTokens(AQLParser.QUOTESINGLE); }
		public TerminalNode QUOTESINGLE(int i) {
			return getToken(AQLParser.QUOTESINGLE, i);
		}
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_string);
		int _la;
		try {
			setState(244);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case QUOTEDOUBLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				match(QUOTEDOUBLE);
				setState(230); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(229);
					value();
					}
					}
					setState(232); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 5370806272L) != 0) );
				setState(234);
				match(QUOTEDOUBLE);
				}
				break;
			case QUOTESINGLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(236);
				match(QUOTESINGLE);
				setState(238); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(237);
					value();
					}
					}
					setState(240); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 5370806272L) != 0) );
				setState(242);
				match(QUOTESINGLE);
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
		public TerminalNode NUMBER() { return getToken(AQLParser.NUMBER, 0); }
		public DynamicVarContext dynamicVar() {
			return getRuleContext(DynamicVarContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(AQLParser.VARIABLE, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AQLParserListener ) ((AQLParserListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AQLParserVisitor ) return ((AQLParserVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_value);
		try {
			setState(249);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(246);
				match(NUMBER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(247);
				dynamicVar();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(248);
				match(VARIABLE);
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
		"\u0004\u0001!\u00fc\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0001\u0000\u0004\u0000"+
		",\b\u0000\u000b\u0000\f\u0000-\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u00014\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0003"+
		"\u00029\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003"+
		"\u0002?\b\u0002\u0001\u0002\u0001\u0002\u0004\u0002C\b\u0002\u000b\u0002"+
		"\f\u0002D\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003M\b\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004U\b\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0004\u0005[\b\u0005\u000b\u0005"+
		"\f\u0005\\\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0004\u0005"+
		"c\b\u0005\u000b\u0005\f\u0005d\u0001\u0005\u0001\u0005\u0003\u0005i\b"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007t\b\u0007\u0001"+
		"\b\u0001\b\u0001\b\u0003\by\b\b\u0001\b\u0001\b\u0001\b\u0005\b~\b\b\n"+
		"\b\f\b\u0081\t\b\u0001\t\u0001\t\u0001\t\u0003\t\u0086\b\t\u0001\t\u0001"+
		"\t\u0001\t\u0005\t\u008b\b\t\n\t\f\t\u008e\t\t\u0001\n\u0001\n\u0001\n"+
		"\u0003\n\u0093\b\n\u0001\n\u0001\n\u0001\n\u0005\n\u0098\b\n\n\n\f\n\u009b"+
		"\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00a0\b\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u00a5\b\u000b\n\u000b\f\u000b"+
		"\u00a8\t\u000b\u0001\f\u0001\f\u0001\f\u0004\f\u00ad\b\f\u000b\f\f\f\u00ae"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0004\r\u00b6\b\r\u000b\r\f\r"+
		"\u00b7\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u00c3\b\u000f\n\u000f"+
		"\f\u000f\u00c6\t\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0003\u0010\u00cc\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011"+
		"\u00d1\b\u0011\n\u0011\f\u0011\u00d4\t\u0011\u0005\u0011\u00d6\b\u0011"+
		"\n\u0011\f\u0011\u00d9\t\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u00e3"+
		"\b\u0012\u0001\u0013\u0001\u0013\u0004\u0013\u00e7\b\u0013\u000b\u0013"+
		"\f\u0013\u00e8\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0004\u0013"+
		"\u00ef\b\u0013\u000b\u0013\f\u0013\u00f0\u0001\u0013\u0001\u0013\u0003"+
		"\u0013\u00f5\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u00fa"+
		"\b\u0014\u0001\u0014\u0000\u0000\u0015\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(\u0000\u0001\u0001"+
		"\u0000\u000f\u0014\u0113\u0000+\u0001\u0000\u0000\u0000\u00023\u0001\u0000"+
		"\u0000\u0000\u00045\u0001\u0000\u0000\u0000\u0006H\u0001\u0000\u0000\u0000"+
		"\bQ\u0001\u0000\u0000\u0000\nV\u0001\u0000\u0000\u0000\fj\u0001\u0000"+
		"\u0000\u0000\u000es\u0001\u0000\u0000\u0000\u0010u\u0001\u0000\u0000\u0000"+
		"\u0012\u0082\u0001\u0000\u0000\u0000\u0014\u008f\u0001\u0000\u0000\u0000"+
		"\u0016\u009c\u0001\u0000\u0000\u0000\u0018\u00a9\u0001\u0000\u0000\u0000"+
		"\u001a\u00b2\u0001\u0000\u0000\u0000\u001c\u00bb\u0001\u0000\u0000\u0000"+
		"\u001e\u00bf\u0001\u0000\u0000\u0000 \u00c7\u0001\u0000\u0000\u0000\""+
		"\u00cd\u0001\u0000\u0000\u0000$\u00e2\u0001\u0000\u0000\u0000&\u00f4\u0001"+
		"\u0000\u0000\u0000(\u00f9\u0001\u0000\u0000\u0000*,\u0003\u0002\u0001"+
		"\u0000+*\u0001\u0000\u0000\u0000,-\u0001\u0000\u0000\u0000-+\u0001\u0000"+
		"\u0000\u0000-.\u0001\u0000\u0000\u0000.\u0001\u0001\u0000\u0000\u0000"+
		"/4\u0003\u000e\u0007\u000004\u0003\u0004\u0002\u000014\u0003\b\u0004\u0000"+
		"24\u0003\u0006\u0003\u00003/\u0001\u0000\u0000\u000030\u0001\u0000\u0000"+
		"\u000031\u0001\u0000\u0000\u000032\u0001\u0000\u0000\u00004\u0003\u0001"+
		"\u0000\u0000\u000058\u0005\u0001\u0000\u000069\u0003$\u0012\u000079\u0005"+
		"\u001e\u0000\u000086\u0001\u0000\u0000\u000087\u0001\u0000\u0000\u0000"+
		"9:\u0001\u0000\u0000\u0000:>\u0005\u0002\u0000\u0000;?\u0003\u000e\u0007"+
		"\u0000<?\u0003$\u0012\u0000=?\u0005\u001e\u0000\u0000>;\u0001\u0000\u0000"+
		"\u0000><\u0001\u0000\u0000\u0000>=\u0001\u0000\u0000\u0000?@\u0001\u0000"+
		"\u0000\u0000@B\u0005\u0015\u0000\u0000AC\u0003\u0002\u0001\u0000BA\u0001"+
		"\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000\u0000"+
		"DE\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000FG\u0005\u0016\u0000"+
		"\u0000G\u0005\u0001\u0000\u0000\u0000HL\u0005\u0003\u0000\u0000IM\u0003"+
		"\u000e\u0007\u0000JM\u0003(\u0014\u0000KM\u0003&\u0013\u0000LI\u0001\u0000"+
		"\u0000\u0000LJ\u0001\u0000\u0000\u0000LK\u0001\u0000\u0000\u0000MN\u0001"+
		"\u0000\u0000\u0000NO\u0005\u0004\u0000\u0000OP\u0005\u001e\u0000\u0000"+
		"P\u0007\u0001\u0000\u0000\u0000QT\u0005\u000e\u0000\u0000RU\u0003(\u0014"+
		"\u0000SU\u0003&\u0013\u0000TR\u0001\u0000\u0000\u0000TS\u0001\u0000\u0000"+
		"\u0000U\t\u0001\u0000\u0000\u0000VW\u0005\n\u0000\u0000WX\u0003\u001c"+
		"\u000e\u0000XZ\u0005\u0015\u0000\u0000Y[\u0003\u0002\u0001\u0000ZY\u0001"+
		"\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000"+
		"\\]\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^h\u0005\u0016\u0000"+
		"\u0000_`\u0005\r\u0000\u0000`b\u0005\u0015\u0000\u0000ac\u0003\u0002\u0001"+
		"\u0000ba\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000db\u0001\u0000"+
		"\u0000\u0000de\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000\u0000fg\u0005"+
		"\u0016\u0000\u0000gi\u0001\u0000\u0000\u0000h_\u0001\u0000\u0000\u0000"+
		"hi\u0001\u0000\u0000\u0000i\u000b\u0001\u0000\u0000\u0000jk\u0005\t\u0000"+
		"\u0000kl\u0005\u0015\u0000\u0000lm\u0003\u001e\u000f\u0000mn\u0005\u0016"+
		"\u0000\u0000n\r\u0001\u0000\u0000\u0000ot\u0003\u0010\b\u0000pt\u0003"+
		"\u0012\t\u0000qt\u0003\u0014\n\u0000rt\u0003\u0016\u000b\u0000so\u0001"+
		"\u0000\u0000\u0000sp\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000"+
		"sr\u0001\u0000\u0000\u0000t\u000f\u0001\u0000\u0000\u0000uv\u0005\u0005"+
		"\u0000\u0000vx\u0003\"\u0011\u0000wy\u0003\f\u0006\u0000xw\u0001\u0000"+
		"\u0000\u0000xy\u0001\u0000\u0000\u0000y\u007f\u0001\u0000\u0000\u0000"+
		"z~\u0003\u0018\f\u0000{~\u0003\u001a\r\u0000|~\u0003\n\u0005\u0000}z\u0001"+
		"\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000}|\u0001\u0000\u0000\u0000"+
		"~\u0081\u0001\u0000\u0000\u0000\u007f}\u0001\u0000\u0000\u0000\u007f\u0080"+
		"\u0001\u0000\u0000\u0000\u0080\u0011\u0001\u0000\u0000\u0000\u0081\u007f"+
		"\u0001\u0000\u0000\u0000\u0082\u0083\u0005\u0006\u0000\u0000\u0083\u0085"+
		"\u0003\"\u0011\u0000\u0084\u0086\u0003\f\u0006\u0000\u0085\u0084\u0001"+
		"\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086\u008c\u0001"+
		"\u0000\u0000\u0000\u0087\u008b\u0003\u0018\f\u0000\u0088\u008b\u0003\u001a"+
		"\r\u0000\u0089\u008b\u0003\n\u0005\u0000\u008a\u0087\u0001\u0000\u0000"+
		"\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008a\u0089\u0001\u0000\u0000"+
		"\u0000\u008b\u008e\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000"+
		"\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u0013\u0001\u0000\u0000"+
		"\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008f\u0090\u0005\u0007\u0000"+
		"\u0000\u0090\u0092\u0003\"\u0011\u0000\u0091\u0093\u0003\f\u0006\u0000"+
		"\u0092\u0091\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000"+
		"\u0093\u0099\u0001\u0000\u0000\u0000\u0094\u0098\u0003\u0018\f\u0000\u0095"+
		"\u0098\u0003\u001a\r\u0000\u0096\u0098\u0003\n\u0005\u0000\u0097\u0094"+
		"\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000\u0000\u0000\u0097\u0096"+
		"\u0001\u0000\u0000\u0000\u0098\u009b\u0001\u0000\u0000\u0000\u0099\u0097"+
		"\u0001\u0000\u0000\u0000\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u0015"+
		"\u0001\u0000\u0000\u0000\u009b\u0099\u0001\u0000\u0000\u0000\u009c\u009d"+
		"\u0005\b\u0000\u0000\u009d\u009f\u0003\"\u0011\u0000\u009e\u00a0\u0003"+
		"\f\u0006\u0000\u009f\u009e\u0001\u0000\u0000\u0000\u009f\u00a0\u0001\u0000"+
		"\u0000\u0000\u00a0\u00a6\u0001\u0000\u0000\u0000\u00a1\u00a5\u0003\u0018"+
		"\f\u0000\u00a2\u00a5\u0003\u001a\r\u0000\u00a3\u00a5\u0003\n\u0005\u0000"+
		"\u00a4\u00a1\u0001\u0000\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000"+
		"\u00a4\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a8\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000"+
		"\u00a7\u0017\u0001\u0000\u0000\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000"+
		"\u00a9\u00aa\u0005\f\u0000\u0000\u00aa\u00ac\u0005\u0015\u0000\u0000\u00ab"+
		"\u00ad\u0003\u0002\u0001\u0000\u00ac\u00ab\u0001\u0000\u0000\u0000\u00ad"+
		"\u00ae\u0001\u0000\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00ae"+
		"\u00af\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0"+
		"\u00b1\u0005\u0016\u0000\u0000\u00b1\u0019\u0001\u0000\u0000\u0000\u00b2"+
		"\u00b3\u0005\u000b\u0000\u0000\u00b3\u00b5\u0005\u0015\u0000\u0000\u00b4"+
		"\u00b6\u0003\u0002\u0001\u0000\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b6"+
		"\u00b7\u0001\u0000\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b7"+
		"\u00b8\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9"+
		"\u00ba\u0005\u0016\u0000\u0000\u00ba\u001b\u0001\u0000\u0000\u0000\u00bb"+
		"\u00bc\u0003(\u0014\u0000\u00bc\u00bd\u0007\u0000\u0000\u0000\u00bd\u00be"+
		"\u0003(\u0014\u0000\u00be\u001d\u0001\u0000\u0000\u0000\u00bf\u00c4\u0003"+
		" \u0010\u0000\u00c0\u00c1\u0005\u0018\u0000\u0000\u00c1\u00c3\u0003 \u0010"+
		"\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c3\u00c6\u0001\u0000\u0000"+
		"\u0000\u00c4\u00c2\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000"+
		"\u0000\u00c5\u001f\u0001\u0000\u0000\u0000\u00c6\u00c4\u0001\u0000\u0000"+
		"\u0000\u00c7\u00c8\u0005\u001e\u0000\u0000\u00c8\u00cb\u0005\u0017\u0000"+
		"\u0000\u00c9\u00cc\u0003(\u0014\u0000\u00ca\u00cc\u0003&\u0013\u0000\u00cb"+
		"\u00c9\u0001\u0000\u0000\u0000\u00cb\u00ca\u0001\u0000\u0000\u0000\u00cc"+
		"!\u0001\u0000\u0000\u0000\u00cd\u00d7\u0005\u001c\u0000\u0000\u00ce\u00d2"+
		"\u0003$\u0012\u0000\u00cf\u00d1\u0005\u001d\u0000\u0000\u00d0\u00cf\u0001"+
		"\u0000\u0000\u0000\u00d1\u00d4\u0001\u0000\u0000\u0000\u00d2\u00d0\u0001"+
		"\u0000\u0000\u0000\u00d2\u00d3\u0001\u0000\u0000\u0000\u00d3\u00d6\u0001"+
		"\u0000\u0000\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d5\u00ce\u0001"+
		"\u0000\u0000\u0000\u00d6\u00d9\u0001\u0000\u0000\u0000\u00d7\u00d5\u0001"+
		"\u0000\u0000\u0000\u00d7\u00d8\u0001\u0000\u0000\u0000\u00d8#\u0001\u0000"+
		"\u0000\u0000\u00d9\u00d7\u0001\u0000\u0000\u0000\u00da\u00db\u0005\u001e"+
		"\u0000\u0000\u00db\u00dc\u0005\u0019\u0000\u0000\u00dc\u00e3\u0005\u001e"+
		"\u0000\u0000\u00dd\u00de\u0005\u0015\u0000\u0000\u00de\u00df\u0005\u001e"+
		"\u0000\u0000\u00df\u00e0\u0005\u0019\u0000\u0000\u00e0\u00e1\u0005\u001e"+
		"\u0000\u0000\u00e1\u00e3\u0005\u0016\u0000\u0000\u00e2\u00da\u0001\u0000"+
		"\u0000\u0000\u00e2\u00dd\u0001\u0000\u0000\u0000\u00e3%\u0001\u0000\u0000"+
		"\u0000\u00e4\u00e6\u0005\u001a\u0000\u0000\u00e5\u00e7\u0003(\u0014\u0000"+
		"\u00e6\u00e5\u0001\u0000\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000"+
		"\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e8\u00e9\u0001\u0000\u0000\u0000"+
		"\u00e9\u00ea\u0001\u0000\u0000\u0000\u00ea\u00eb\u0005\u001a\u0000\u0000"+
		"\u00eb\u00f5\u0001\u0000\u0000\u0000\u00ec\u00ee\u0005\u001b\u0000\u0000"+
		"\u00ed\u00ef\u0003(\u0014\u0000\u00ee\u00ed\u0001\u0000\u0000\u0000\u00ef"+
		"\u00f0\u0001\u0000\u0000\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000\u00f0"+
		"\u00f1\u0001\u0000\u0000\u0000\u00f1\u00f2\u0001\u0000\u0000\u0000\u00f2"+
		"\u00f3\u0005\u001b\u0000\u0000\u00f3\u00f5\u0001\u0000\u0000\u0000\u00f4"+
		"\u00e4\u0001\u0000\u0000\u0000\u00f4\u00ec\u0001\u0000\u0000\u0000\u00f5"+
		"\'\u0001\u0000\u0000\u0000\u00f6\u00fa\u0005 \u0000\u0000\u00f7\u00fa"+
		"\u0003$\u0012\u0000\u00f8\u00fa\u0005\u001e\u0000\u0000\u00f9\u00f6\u0001"+
		"\u0000\u0000\u0000\u00f9\u00f7\u0001\u0000\u0000\u0000\u00f9\u00f8\u0001"+
		"\u0000\u0000\u0000\u00fa)\u0001\u0000\u0000\u0000\"-38>DLT\\dhsx}\u007f"+
		"\u0085\u008a\u008c\u0092\u0097\u0099\u009f\u00a4\u00a6\u00ae\u00b7\u00c4"+
		"\u00cb\u00d2\u00d7\u00e2\u00e8\u00f0\u00f4\u00f9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}