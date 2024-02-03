// Generated from AQLParser.g4 by ANTLR 4.13.1
package gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AQLParser}.
 */
public interface AQLParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AQLParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(AQLParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(AQLParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(AQLParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(AQLParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(AQLParser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(AQLParser.LoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(AQLParser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(AQLParser.SetContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#log}.
	 * @param ctx the parse tree
	 */
	void enterLog(AQLParser.LogContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#log}.
	 * @param ctx the parse tree
	 */
	void exitLog(AQLParser.LogContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#onElse}.
	 * @param ctx the parse tree
	 */
	void enterOnElse(AQLParser.OnElseContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#onElse}.
	 * @param ctx the parse tree
	 */
	void exitOnElse(AQLParser.OnElseContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#withBlock}.
	 * @param ctx the parse tree
	 */
	void enterWithBlock(AQLParser.WithBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#withBlock}.
	 * @param ctx the parse tree
	 */
	void exitWithBlock(AQLParser.WithBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#request}.
	 * @param ctx the parse tree
	 */
	void enterRequest(AQLParser.RequestContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#request}.
	 * @param ctx the parse tree
	 */
	void exitRequest(AQLParser.RequestContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#getReq}.
	 * @param ctx the parse tree
	 */
	void enterGetReq(AQLParser.GetReqContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#getReq}.
	 * @param ctx the parse tree
	 */
	void exitGetReq(AQLParser.GetReqContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#postReq}.
	 * @param ctx the parse tree
	 */
	void enterPostReq(AQLParser.PostReqContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#postReq}.
	 * @param ctx the parse tree
	 */
	void exitPostReq(AQLParser.PostReqContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#putReq}.
	 * @param ctx the parse tree
	 */
	void enterPutReq(AQLParser.PutReqContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#putReq}.
	 * @param ctx the parse tree
	 */
	void exitPutReq(AQLParser.PutReqContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#delReq}.
	 * @param ctx the parse tree
	 */
	void enterDelReq(AQLParser.DelReqContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#delReq}.
	 * @param ctx the parse tree
	 */
	void exitDelReq(AQLParser.DelReqContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(AQLParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(AQLParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(AQLParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(AQLParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(AQLParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(AQLParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#dynamicURI}.
	 * @param ctx the parse tree
	 */
	void enterDynamicURI(AQLParser.DynamicURIContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#dynamicURI}.
	 * @param ctx the parse tree
	 */
	void exitDynamicURI(AQLParser.DynamicURIContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#dynamicVar}.
	 * @param ctx the parse tree
	 */
	void enterDynamicVar(AQLParser.DynamicVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#dynamicVar}.
	 * @param ctx the parse tree
	 */
	void exitDynamicVar(AQLParser.DynamicVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(AQLParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(AQLParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link AQLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(AQLParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link AQLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(AQLParser.ValueContext ctx);
}