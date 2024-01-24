package utils;

import gen.AQLParser;
import gen.AQLParserBaseVisitor;

public class AQLPrintVisitor extends AQLParserBaseVisitor<Void> {

    // TBD update type from void
    @Override
    public Void visitProgram(AQLParser.ProgramContext ctx) {
        System.out.println("Program:");
        return this.visitChildren(ctx);
    }

    @Override
    public Void visitStatement(AQLParser.StatementContext ctx) {
        System.out.println("Statement: " + ctx.getText());
        return this.visitChildren(ctx);
    }

    @Override
    public Void visitLoop(AQLParser.LoopContext ctx) {
        System.out.println("Loop: " + ctx.getText());
        return this.visitChildren(ctx);
    }

    // @Override
    // public Void visitLoopBasic(AQLParser.LoopBasicContext ctx) {
    //     System.out.println("Loop Basic: " + ctx.getText());
    //     return this.visitChildren(ctx);
    // }

    @Override
    public Void visitSet(AQLParser.SetContext ctx) {
        System.out.println("Set: " + ctx.getText());
        return this.visitChildren(ctx);
    }

    @Override
    public Void visitGetReq(AQLParser.GetReqContext ctx) {
        System.out.println("GET Request: " + ctx.getText());
        return this.visitChildren(ctx);
    }

    @Override
    public Void visitPostReq(AQLParser.PostReqContext ctx) {
        System.out.println("POST Request: " + ctx.getText());
        return this.visitChildren(ctx);
    }

    @Override
    public Void visitPutReq(AQLParser.PutReqContext ctx) {
        System.out.println("PUT Request: " + ctx.getText());
        return this.visitChildren(ctx);
    }

    @Override
    public Void visitDelReq(AQLParser.DelReqContext ctx) {
        System.out.println("DELETE Request: " + ctx.getText());
        return this.visitChildren(ctx);
    }

    @Override
    public Void visitOnElse(AQLParser.OnElseContext ctx) {
        System.out.println("On Else: " + ctx.getText());
        return this.visitChildren(ctx);
    }

    @Override
    public Void visitWithBlock(AQLParser.WithBlockContext ctx) {
        System.out.println("With Block: " + ctx.getText());
        return this.visitChildren(ctx);
    }

    @Override
    public Void visitLog(AQLParser.LogContext ctx) {
        System.out.println("Log: " + ctx.getText());
        return this.visitChildren(ctx);
    }

}
