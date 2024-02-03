package controller;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import ast.Node;
import ast.Program;
import ast.Statement;
import ast.api.DelReq;
import ast.api.GetReq;
import ast.api.Request;
import gen.AQLParser;
import gen.AQLParserBaseVisitor;

public class AQLVisitor extends AQLParserBaseVisitor<Node> {

  @Override
  public Program visitProgram(AQLParser.ProgramContext ctx) {
    List<Statement> tasks = new ArrayList<>();
    for (AQLParser.StatementContext task : ctx.statement()) {
          tasks.add(this.visitStatement(task));
    }
    return new Program(tasks);
  }
  
  @Override
  public Statement visitStatement(AQLParser.StatementContext ctx) {
    return new Statement(this.visitRequest(ctx.request()));
  }

  @Override
  public Request visitRequest(AQLParser.RequestContext ctx) {
    
    if (ctx.getReq() != null) {
      return new Request(this.visitGetReq(ctx.getReq()));
    } else if (ctx.delReq() != null) {
      return new Request(this.visitDelReq(ctx.delReq()));
    } 
  // else if (ctx.putReq() != null) {
  //     return new Request(this.visitPutReq(ctx.putReq()));
  // } else (ctx.postReq() != null) {
  //     return new Request(this.visitPostReq(ctx.postReq()));
  // }
    throw new IllegalArgumentException("Unsupported request type");
  }

  private static class URIParts {
    String head;
    List<String> body;
    List<String> tail;

    public URIParts(String head, List<String> body, List<String> tail) {
          this.head = head;
          this.body = body;
          this.tail = tail;
    }
  }

  private URIParts parseAndExtractURI(AQLParser.DynamicURIContext ctx) {
      String head = ctx.URI().getText();
      List<String> body = new ArrayList<>();
      List<String> tail = new ArrayList<>();
      for (AQLParser.DynamicVarContext var : ctx.dynamicVar()) {
          body.add(var.getText());
      }
      for (TerminalNode uri : ctx.URI_TAIL()) {
          tail.add(uri.getText());
      }
      return new URIParts(head, body, tail);
  }
    
  @Override
  public GetReq visitGetReq(AQLParser.GetReqContext ctx) {
      URIParts URIparts = this.parseAndExtractURI(ctx.dynamicURI());
      return new GetReq(URIparts.head, URIparts.body, URIparts.tail);
  }

  @Override
  public DelReq visitDelReq(AQLParser.DelReqContext ctx) {
      URIParts URIparts = this.parseAndExtractURI(ctx.dynamicURI());
      return new DelReq(URIparts.head, URIparts.body, URIparts.tail);
  }
  
}


