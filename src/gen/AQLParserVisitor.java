// Generated from AQLParser.g4 by ANTLR 4.13.1
package gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AQLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AQLParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AQLParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(AQLParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(AQLParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop(AQLParser.LoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(AQLParser.SetContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#log}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLog(AQLParser.LogContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#onElse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOnElse(AQLParser.OnElseContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#withBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithBlock(AQLParser.WithBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#request}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequest(AQLParser.RequestContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#getReq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetReq(AQLParser.GetReqContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#postReq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostReq(AQLParser.PostReqContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#putReq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPutReq(AQLParser.PutReqContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#delReq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelReq(AQLParser.DelReqContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(AQLParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(AQLParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(AQLParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#dynamicURI}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDynamicURI(AQLParser.DynamicURIContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#dynamicVar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDynamicVar(AQLParser.DynamicVarContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(AQLParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link AQLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(AQLParser.ValueContext ctx);
}