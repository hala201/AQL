package controller;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.json.JSONObject;

import ast.Node;
import ast.Program;
import ast.Statement;
import ast.api.DelReq;
import ast.api.GetReq;
import ast.api.PutReq;
import ast.api.PostReq;
import ast.api.Params;
import ast.api.Request;
import ast.api.WithBlock;
import controller.helper.*;
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
    } else if (ctx.putReq() != null) {
       return new Request(this.visitPutReq(ctx.putReq()));
    } else if (ctx.postReq() != null) {
       return new Request(this.visitPostReq(ctx.postReq()));
    }
    throw new IllegalArgumentException("Unsupported request type");
  }

  
    
  @Override
  public GetReq visitGetReq(AQLParser.GetReqContext ctx) {
      URIParts URIparts = URIParts.parseAndExtractURI(ctx.dynamicURI());
      JSONObject params = null;
      if (ctx.withBlock() != null) { params = this.visitWithBlock(ctx.withBlock()).getParams().getContent(); }
      return new GetReq(URIparts.getHead(), URIparts.getBody(), URIparts.getTail(), params);
  }

  @Override
  public DelReq visitDelReq(AQLParser.DelReqContext ctx) {
        URIParts URIparts = URIParts.parseAndExtractURI(ctx.dynamicURI());
      JSONObject params = null;
      if (ctx.withBlock() != null) { params = this.visitWithBlock(ctx.withBlock()).getParams().getContent(); }
      return new DelReq(URIparts.getHead(), URIparts.getBody(), URIparts.getTail(), params);
  }

    @Override
    public PutReq visitPutReq(AQLParser.PutReqContext ctx) {
        URIParts URIparts = URIParts.parseAndExtractURI(ctx.dynamicURI());
        JSONObject params = null;
        if (ctx.withBlock() != null) { params = this.visitWithBlock(ctx.withBlock()).getParams().getContent(); }
        return new PutReq(URIparts.getHead(), URIparts.getBody(), URIparts.getTail(), params);
    }

    @Override
    public PostReq visitPostReq(AQLParser.PostReqContext ctx) {
        URIParts URIparts = URIParts.parseAndExtractURI(ctx.dynamicURI());
        JSONObject params = null;
        if (ctx.withBlock() != null) { params = this.visitWithBlock(ctx.withBlock()).getParams().getContent(); }
        return new PostReq(URIparts.getHead(), URIparts.getBody(), URIparts.getTail(), params);
    }
  
@Override
public WithBlock visitWithBlock(AQLParser.WithBlockContext ctx) {
        if (ctx.getChildCount() != 4) { 
            throw new IllegalStateException("Invalid WITH block");
        }
        if (!ctx.getChild(1).getText().equals("{")) {
            throw new IllegalStateException("Invalid WITH block: missing {");
        }
        if (!ctx.getChild(2).getClass().equals(AQLParser.ParamsContext.class)) {
            throw new IllegalStateException("Invalid WITH block: expected a JSON object as parameters");
        }
        if (!ctx.getChild(3).getText().equals("}")) {
            throw new IllegalStateException("Invalid WITH block: missing }");
        }
        
        return new WithBlock(this.visitParams(ctx.params()));
}

  @Override
  public Params visitParams(AQLParser.ParamsContext ctx) {
      JSONObject params = new JSONObject();
      for (int i = 0; i < ctx.getChildCount(); i++) {
          String childText = ctx.getChild(i).getText();
          if (childText.equals(",") || childText.equals(":")) {
              continue;
          }
          String[] parts = childText.split(":");
          if (parts.length == 2) { 
              String key = StringParser.stripQuotes(parts[0].trim());
              String valueText = parts[1].trim();
              Object value = valueText.startsWith("\"") || valueText.startsWith("\'") ? StringParser.stripQuotes(valueText) : StringParser.parseValue(valueText);
              params.put(key, value);
          }
      }
      if (params.isEmpty()) {
        throw new IllegalStateException("Invalid parameters: expected a JSON Object");
      }
      return new Params(params);
  }

}