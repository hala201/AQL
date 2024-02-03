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
		ON=10, ERROR=11, SUCCESS=12, ELSE=13, LOG=14, EQUAL=15, NOT_EQUAL=16, 
		LESS=17, GREATER=18, LESS_EQUAL=19, GREATER_EQUAL=20, OPEN_BRACE=21, CLOSE_BRACE=22, 
		COLON=23, COMMA=24, DOT=25, QUOTEDOUBLE=26, QUOTESINGLE=27, URI=28, URI_TAIL=29, 
		VARIABLE=30, ALPHANUM=31, NUMBER=32, WS=33;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_loop = 2, RULE_set = 3, RULE_log = 4, 
		RULE_onElse = 5, RULE_withBlock = 6, RULE_request = 7, RULE_getReq = 8, 
		RULE_postReq = 9, RULE_putReq = 10, RULE_delReq = 11, RULE_condition = 12, 
		RULE_params = 13, RULE_param = 14, RULE_dynamicURI = 15, RULE_dynamicVar = 16, 
		RULE_string = 17, RULE_value = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "loop", "set", "log", "onElse", "withBlock", 
			"request", "getReq", "postReq", "putReq", "delReq", "condition", "params", 
			"param", "dynamicURI", "dynamicVar", "string", "value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'FOR EACH'", "'IN'", "'SET'", "'AS'", "'GET'", "'POST'", "'PUT'", 
			"'DELETE'", "'WITH'", "'ON'", "'ERROR'", "'SUCCESS'", "'ELSE'", "'LOG'", 
			"'=='", "'!='", "'<'", "'>'", "'<='", "'>='", "'{'", "'}'", "':'", "','", 
			"'.'", "'\"'", "'''"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "FOR_EACH", "IN", "SET", "AS", "GET", "POST", "PUT", "DELETE", 
			"WITH", "ON", "ERROR", "SUCCESS", "ELSE", "LOG", "EQUAL", "NOT_EQUAL", 
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
			setState(39); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38);
				statement();
				}
				}
				setState(41); 
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
			setState(47);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case GET:
			case POST:
			case PUT:
			case DELETE:
				enterOuterAlt(_localctx, 1);
				{
				setState(43);
				request();
				}
				break;
			case FOR_EACH:
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				loop();
				}
				break;
			case LOG:
				enterOuterAlt(_localctx, 3);
				{
				setState(45);
				log();
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 4);
				{
				setState(46);
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
			setState(49);
			match(FOR_EACH);
			setState(52);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(50);
				dynamicVar();
				}
				break;
			case 2:
				{
				setState(51);
				match(VARIABLE);
				}
				break;
			}
			setState(54);
			match(IN);
			setState(58);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(55);
				request();
				}
				break;
			case 2:
				{
				setState(56);
				dynamicVar();
				}
				break;
			case 3:
				{
				setState(57);
				match(VARIABLE);
				}
				break;
			}
			setState(60);
			match(OPEN_BRACE);
			setState(62); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(61);
				statement();
				}
				}
				setState(64); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 16874L) != 0) );
			setState(66);
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
			setState(68);
			match(SET);
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case GET:
			case POST:
			case PUT:
			case DELETE:
				{
				setState(69);
				request();
				}
				break;
			case OPEN_BRACE:
			case VARIABLE:
			case NUMBER:
				{
				setState(70);
				value();
				}
				break;
			case QUOTEDOUBLE:
			case QUOTESINGLE:
				{
				setState(71);
				string();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(74);
			match(AS);
			setState(75);
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
			setState(77);
			match(LOG);
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
			case VARIABLE:
			case NUMBER:
				{
				setState(78);
				value();
				}
				break;
			case QUOTEDOUBLE:
			case QUOTESINGLE:
				{
				setState(79);
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
			setState(82);
			match(ON);
			setState(83);
			condition();
			setState(84);
			match(OPEN_BRACE);
			setState(86); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(85);
				statement();
				}
				}
				setState(88); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 16874L) != 0) );
			setState(90);
			match(CLOSE_BRACE);
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(91);
				match(ELSE);
				setState(92);
				match(OPEN_BRACE);
				setState(94); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(93);
					statement();
					}
					}
					setState(96); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 16874L) != 0) );
				setState(98);
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
			setState(102);
			match(WITH);
			setState(103);
			match(OPEN_BRACE);
			setState(104);
			params();
			setState(105);
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
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case GET:
				{
				setState(107);
				getReq();
				}
				break;
			case POST:
				{
				setState(108);
				postReq();
				}
				break;
			case PUT:
				{
				setState(109);
				putReq();
				}
				break;
			case DELETE:
				{
				setState(110);
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
		public OnElseContext onElse() {
			return getRuleContext(OnElseContext.class,0);
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
			setState(113);
			match(GET);
			setState(114);
			dynamicURI();
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(115);
				withBlock();
				}
			}

			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ON) {
				{
				setState(118);
				onElse();
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
	public static class PostReqContext extends ParserRuleContext {
		public TerminalNode POST() { return getToken(AQLParser.POST, 0); }
		public DynamicURIContext dynamicURI() {
			return getRuleContext(DynamicURIContext.class,0);
		}
		public WithBlockContext withBlock() {
			return getRuleContext(WithBlockContext.class,0);
		}
		public OnElseContext onElse() {
			return getRuleContext(OnElseContext.class,0);
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
			setState(121);
			match(POST);
			setState(122);
			dynamicURI();
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(123);
				withBlock();
				}
			}

			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ON) {
				{
				setState(126);
				onElse();
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
	public static class PutReqContext extends ParserRuleContext {
		public TerminalNode PUT() { return getToken(AQLParser.PUT, 0); }
		public DynamicURIContext dynamicURI() {
			return getRuleContext(DynamicURIContext.class,0);
		}
		public WithBlockContext withBlock() {
			return getRuleContext(WithBlockContext.class,0);
		}
		public OnElseContext onElse() {
			return getRuleContext(OnElseContext.class,0);
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
			setState(129);
			match(PUT);
			setState(130);
			dynamicURI();
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(131);
				withBlock();
				}
			}

			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ON) {
				{
				setState(134);
				onElse();
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
	public static class DelReqContext extends ParserRuleContext {
		public TerminalNode DELETE() { return getToken(AQLParser.DELETE, 0); }
		public DynamicURIContext dynamicURI() {
			return getRuleContext(DynamicURIContext.class,0);
		}
		public WithBlockContext withBlock() {
			return getRuleContext(WithBlockContext.class,0);
		}
		public OnElseContext onElse() {
			return getRuleContext(OnElseContext.class,0);
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
			setState(137);
			match(DELETE);
			setState(138);
			dynamicURI();
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(139);
				withBlock();
				}
			}

			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ON) {
				{
				setState(142);
				onElse();
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
		public TerminalNode ERROR() { return getToken(AQLParser.ERROR, 0); }
		public TerminalNode SUCCESS() { return getToken(AQLParser.SUCCESS, 0); }
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
		enterRule(_localctx, 24, RULE_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			value();
			setState(146);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2070528L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(147);
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
		enterRule(_localctx, 26, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			param();
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(150);
				match(COMMA);
				setState(151);
				param();
				}
				}
				setState(156);
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
		enterRule(_localctx, 28, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(VARIABLE);
			setState(158);
			match(COLON);
			setState(161);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
			case VARIABLE:
			case NUMBER:
				{
				setState(159);
				value();
				}
				break;
			case QUOTEDOUBLE:
			case QUOTESINGLE:
				{
				setState(160);
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
		enterRule(_localctx, 30, RULE_dynamicURI);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(URI);
			setState(173);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(164);
					dynamicVar();
					setState(168);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==URI_TAIL) {
						{
						{
						setState(165);
						match(URI_TAIL);
						}
						}
						setState(170);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					} 
				}
				setState(175);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
		enterRule(_localctx, 32, RULE_dynamicVar);
		try {
			setState(184);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIABLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(176);
				match(VARIABLE);
				setState(177);
				match(DOT);
				setState(178);
				match(VARIABLE);
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(179);
				match(OPEN_BRACE);
				setState(180);
				match(VARIABLE);
				setState(181);
				match(DOT);
				setState(182);
				match(VARIABLE);
				setState(183);
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
		enterRule(_localctx, 34, RULE_string);
		int _la;
		try {
			setState(202);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case QUOTEDOUBLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(186);
				match(QUOTEDOUBLE);
				setState(188); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(187);
					value();
					}
					}
					setState(190); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 5370806272L) != 0) );
				setState(192);
				match(QUOTEDOUBLE);
				}
				break;
			case QUOTESINGLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				match(QUOTESINGLE);
				setState(196); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(195);
					value();
					}
					}
					setState(198); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 5370806272L) != 0) );
				setState(200);
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
		enterRule(_localctx, 36, RULE_value);
		try {
			setState(207);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(204);
				match(NUMBER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(205);
				dynamicVar();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(206);
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
		"\u0004\u0001!\u00d2\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0001\u0000\u0004\u0000(\b\u0000\u000b\u0000\f\u0000)\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u00010\b\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u00025\b\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002;\b\u0002\u0001\u0002\u0001\u0002\u0004"+
		"\u0002?\b\u0002\u000b\u0002\f\u0002@\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003I\b\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004"+
		"Q\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0004\u0005"+
		"W\b\u0005\u000b\u0005\f\u0005X\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0004\u0005_\b\u0005\u000b\u0005\f\u0005`\u0001\u0005\u0001\u0005"+
		"\u0003\u0005e\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"p\b\u0007\u0001\b\u0001\b\u0001\b\u0003\bu\b\b\u0001\b\u0003\bx\b\b\u0001"+
		"\t\u0001\t\u0001\t\u0003\t}\b\t\u0001\t\u0003\t\u0080\b\t\u0001\n\u0001"+
		"\n\u0001\n\u0003\n\u0085\b\n\u0001\n\u0003\n\u0088\b\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u008d\b\u000b\u0001\u000b\u0003\u000b\u0090"+
		"\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0005"+
		"\r\u0099\b\r\n\r\f\r\u009c\t\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u00a2\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0005"+
		"\u000f\u00a7\b\u000f\n\u000f\f\u000f\u00aa\t\u000f\u0005\u000f\u00ac\b"+
		"\u000f\n\u000f\f\u000f\u00af\t\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u00b9\b\u0010\u0001\u0011\u0001\u0011\u0004\u0011\u00bd\b\u0011\u000b"+
		"\u0011\f\u0011\u00be\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0004"+
		"\u0011\u00c5\b\u0011\u000b\u0011\f\u0011\u00c6\u0001\u0011\u0001\u0011"+
		"\u0003\u0011\u00cb\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012"+
		"\u00d0\b\u0012\u0001\u0012\u0000\u0000\u0013\u0000\u0002\u0004\u0006\b"+
		"\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$\u0000\u0001"+
		"\u0002\u0000\u000b\f\u000f\u0014\u00e1\u0000\'\u0001\u0000\u0000\u0000"+
		"\u0002/\u0001\u0000\u0000\u0000\u00041\u0001\u0000\u0000\u0000\u0006D"+
		"\u0001\u0000\u0000\u0000\bM\u0001\u0000\u0000\u0000\nR\u0001\u0000\u0000"+
		"\u0000\ff\u0001\u0000\u0000\u0000\u000eo\u0001\u0000\u0000\u0000\u0010"+
		"q\u0001\u0000\u0000\u0000\u0012y\u0001\u0000\u0000\u0000\u0014\u0081\u0001"+
		"\u0000\u0000\u0000\u0016\u0089\u0001\u0000\u0000\u0000\u0018\u0091\u0001"+
		"\u0000\u0000\u0000\u001a\u0095\u0001\u0000\u0000\u0000\u001c\u009d\u0001"+
		"\u0000\u0000\u0000\u001e\u00a3\u0001\u0000\u0000\u0000 \u00b8\u0001\u0000"+
		"\u0000\u0000\"\u00ca\u0001\u0000\u0000\u0000$\u00cf\u0001\u0000\u0000"+
		"\u0000&(\u0003\u0002\u0001\u0000\'&\u0001\u0000\u0000\u0000()\u0001\u0000"+
		"\u0000\u0000)\'\u0001\u0000\u0000\u0000)*\u0001\u0000\u0000\u0000*\u0001"+
		"\u0001\u0000\u0000\u0000+0\u0003\u000e\u0007\u0000,0\u0003\u0004\u0002"+
		"\u0000-0\u0003\b\u0004\u0000.0\u0003\u0006\u0003\u0000/+\u0001\u0000\u0000"+
		"\u0000/,\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000\u0000/.\u0001\u0000"+
		"\u0000\u00000\u0003\u0001\u0000\u0000\u000014\u0005\u0001\u0000\u0000"+
		"25\u0003 \u0010\u000035\u0005\u001e\u0000\u000042\u0001\u0000\u0000\u0000"+
		"43\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u00006:\u0005\u0002\u0000"+
		"\u00007;\u0003\u000e\u0007\u00008;\u0003 \u0010\u00009;\u0005\u001e\u0000"+
		"\u0000:7\u0001\u0000\u0000\u0000:8\u0001\u0000\u0000\u0000:9\u0001\u0000"+
		"\u0000\u0000;<\u0001\u0000\u0000\u0000<>\u0005\u0015\u0000\u0000=?\u0003"+
		"\u0002\u0001\u0000>=\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000"+
		"@>\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000"+
		"\u0000BC\u0005\u0016\u0000\u0000C\u0005\u0001\u0000\u0000\u0000DH\u0005"+
		"\u0003\u0000\u0000EI\u0003\u000e\u0007\u0000FI\u0003$\u0012\u0000GI\u0003"+
		"\"\u0011\u0000HE\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000HG\u0001"+
		"\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000JK\u0005\u0004\u0000\u0000"+
		"KL\u0005\u001e\u0000\u0000L\u0007\u0001\u0000\u0000\u0000MP\u0005\u000e"+
		"\u0000\u0000NQ\u0003$\u0012\u0000OQ\u0003\"\u0011\u0000PN\u0001\u0000"+
		"\u0000\u0000PO\u0001\u0000\u0000\u0000Q\t\u0001\u0000\u0000\u0000RS\u0005"+
		"\n\u0000\u0000ST\u0003\u0018\f\u0000TV\u0005\u0015\u0000\u0000UW\u0003"+
		"\u0002\u0001\u0000VU\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000"+
		"XV\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000"+
		"\u0000Zd\u0005\u0016\u0000\u0000[\\\u0005\r\u0000\u0000\\^\u0005\u0015"+
		"\u0000\u0000]_\u0003\u0002\u0001\u0000^]\u0001\u0000\u0000\u0000_`\u0001"+
		"\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000"+
		"ab\u0001\u0000\u0000\u0000bc\u0005\u0016\u0000\u0000ce\u0001\u0000\u0000"+
		"\u0000d[\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000e\u000b\u0001"+
		"\u0000\u0000\u0000fg\u0005\t\u0000\u0000gh\u0005\u0015\u0000\u0000hi\u0003"+
		"\u001a\r\u0000ij\u0005\u0016\u0000\u0000j\r\u0001\u0000\u0000\u0000kp"+
		"\u0003\u0010\b\u0000lp\u0003\u0012\t\u0000mp\u0003\u0014\n\u0000np\u0003"+
		"\u0016\u000b\u0000ok\u0001\u0000\u0000\u0000ol\u0001\u0000\u0000\u0000"+
		"om\u0001\u0000\u0000\u0000on\u0001\u0000\u0000\u0000p\u000f\u0001\u0000"+
		"\u0000\u0000qr\u0005\u0005\u0000\u0000rt\u0003\u001e\u000f\u0000su\u0003"+
		"\f\u0006\u0000ts\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000uw\u0001"+
		"\u0000\u0000\u0000vx\u0003\n\u0005\u0000wv\u0001\u0000\u0000\u0000wx\u0001"+
		"\u0000\u0000\u0000x\u0011\u0001\u0000\u0000\u0000yz\u0005\u0006\u0000"+
		"\u0000z|\u0003\u001e\u000f\u0000{}\u0003\f\u0006\u0000|{\u0001\u0000\u0000"+
		"\u0000|}\u0001\u0000\u0000\u0000}\u007f\u0001\u0000\u0000\u0000~\u0080"+
		"\u0003\n\u0005\u0000\u007f~\u0001\u0000\u0000\u0000\u007f\u0080\u0001"+
		"\u0000\u0000\u0000\u0080\u0013\u0001\u0000\u0000\u0000\u0081\u0082\u0005"+
		"\u0007\u0000\u0000\u0082\u0084\u0003\u001e\u000f\u0000\u0083\u0085\u0003"+
		"\f\u0006\u0000\u0084\u0083\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000"+
		"\u0000\u0000\u0085\u0087\u0001\u0000\u0000\u0000\u0086\u0088\u0003\n\u0005"+
		"\u0000\u0087\u0086\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000"+
		"\u0000\u0088\u0015\u0001\u0000\u0000\u0000\u0089\u008a\u0005\b\u0000\u0000"+
		"\u008a\u008c\u0003\u001e\u000f\u0000\u008b\u008d\u0003\f\u0006\u0000\u008c"+
		"\u008b\u0001\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d"+
		"\u008f\u0001\u0000\u0000\u0000\u008e\u0090\u0003\n\u0005\u0000\u008f\u008e"+
		"\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090\u0017"+
		"\u0001\u0000\u0000\u0000\u0091\u0092\u0003$\u0012\u0000\u0092\u0093\u0007"+
		"\u0000\u0000\u0000\u0093\u0094\u0003$\u0012\u0000\u0094\u0019\u0001\u0000"+
		"\u0000\u0000\u0095\u009a\u0003\u001c\u000e\u0000\u0096\u0097\u0005\u0018"+
		"\u0000\u0000\u0097\u0099\u0003\u001c\u000e\u0000\u0098\u0096\u0001\u0000"+
		"\u0000\u0000\u0099\u009c\u0001\u0000\u0000\u0000\u009a\u0098\u0001\u0000"+
		"\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u001b\u0001\u0000"+
		"\u0000\u0000\u009c\u009a\u0001\u0000\u0000\u0000\u009d\u009e\u0005\u001e"+
		"\u0000\u0000\u009e\u00a1\u0005\u0017\u0000\u0000\u009f\u00a2\u0003$\u0012"+
		"\u0000\u00a0\u00a2\u0003\"\u0011\u0000\u00a1\u009f\u0001\u0000\u0000\u0000"+
		"\u00a1\u00a0\u0001\u0000\u0000\u0000\u00a2\u001d\u0001\u0000\u0000\u0000"+
		"\u00a3\u00ad\u0005\u001c\u0000\u0000\u00a4\u00a8\u0003 \u0010\u0000\u00a5"+
		"\u00a7\u0005\u001d\u0000\u0000\u00a6\u00a5\u0001\u0000\u0000\u0000\u00a7"+
		"\u00aa\u0001\u0000\u0000\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000\u00a8"+
		"\u00a9\u0001\u0000\u0000\u0000\u00a9\u00ac\u0001\u0000\u0000\u0000\u00aa"+
		"\u00a8\u0001\u0000\u0000\u0000\u00ab\u00a4\u0001\u0000\u0000\u0000\u00ac"+
		"\u00af\u0001\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ad"+
		"\u00ae\u0001\u0000\u0000\u0000\u00ae\u001f\u0001\u0000\u0000\u0000\u00af"+
		"\u00ad\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005\u001e\u0000\u0000\u00b1"+
		"\u00b2\u0005\u0019\u0000\u0000\u00b2\u00b9\u0005\u001e\u0000\u0000\u00b3"+
		"\u00b4\u0005\u0015\u0000\u0000\u00b4\u00b5\u0005\u001e\u0000\u0000\u00b5"+
		"\u00b6\u0005\u0019\u0000\u0000\u00b6\u00b7\u0005\u001e\u0000\u0000\u00b7"+
		"\u00b9\u0005\u0016\u0000\u0000\u00b8\u00b0\u0001\u0000\u0000\u0000\u00b8"+
		"\u00b3\u0001\u0000\u0000\u0000\u00b9!\u0001\u0000\u0000\u0000\u00ba\u00bc"+
		"\u0005\u001a\u0000\u0000\u00bb\u00bd\u0003$\u0012\u0000\u00bc\u00bb\u0001"+
		"\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be\u00bc\u0001"+
		"\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001"+
		"\u0000\u0000\u0000\u00c0\u00c1\u0005\u001a\u0000\u0000\u00c1\u00cb\u0001"+
		"\u0000\u0000\u0000\u00c2\u00c4\u0005\u001b\u0000\u0000\u00c3\u00c5\u0003"+
		"$\u0012\u0000\u00c4\u00c3\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000"+
		"\u0000\u0000\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000"+
		"\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8\u00c9\u0005\u001b"+
		"\u0000\u0000\u00c9\u00cb\u0001\u0000\u0000\u0000\u00ca\u00ba\u0001\u0000"+
		"\u0000\u0000\u00ca\u00c2\u0001\u0000\u0000\u0000\u00cb#\u0001\u0000\u0000"+
		"\u0000\u00cc\u00d0\u0005 \u0000\u0000\u00cd\u00d0\u0003 \u0010\u0000\u00ce"+
		"\u00d0\u0005\u001e\u0000\u0000\u00cf\u00cc\u0001\u0000\u0000\u0000\u00cf"+
		"\u00cd\u0001\u0000\u0000\u0000\u00cf\u00ce\u0001\u0000\u0000\u0000\u00d0"+
		"%\u0001\u0000\u0000\u0000\u001c)/4:@HPX`dotw|\u007f\u0084\u0087\u008c"+
		"\u008f\u009a\u00a1\u00a8\u00ad\u00b8\u00be\u00c6\u00ca\u00cf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}