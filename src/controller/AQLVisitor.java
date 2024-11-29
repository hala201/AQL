package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.json.JSONObject;

import com.sun.jdi.request.InvalidRequestStateException;

import ast.Node;
import ast.Program;
import ast.Statement;
import ast.api.DelReq;
import ast.api.GetReq;
import ast.api.Params;
import ast.api.PostReq;
import ast.api.PutReq;
import ast.api.Request;
import ast.api.WithBlock;
import ast.condition.Condition;
import ast.condition.OnElse;
import ast.logic.Log;
import ast.logic.Set;
import ast.loop.Loop;
import ast.loop.Variable;
import controller.helper.DataEvaluator;
import controller.helper.StringParser;
import controller.helper.URIParts;
import gen.AQLParser;
import gen.AQLParser.ParamContext;
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
      if (ctx.request() != null) {
          return new Statement(this.visitRequest(ctx.request()));
      }
      if (ctx.loop() != null) {
          return new Statement(this.visitLoop(ctx.loop()));
      }
      if (ctx.log() != null) {
          return new Statement(this.visitLog(ctx.log()));
      }
      if (ctx.set() != null){
          return new Statement(this.visitSet(ctx.set()));
      }
      if (ctx.onElse() != null) {
          return new Statement(this.visitOnElse(ctx.onElse()));
      }

      throw new IllegalArgumentException("Unsupported statement type");
  }

  @Override
  public Request visitRequest(AQLParser.RequestContext ctx) {
      Map<Function<AQLParser.RequestContext, Boolean>, Function<AQLParser.RequestContext, Request>> strategyMap = new HashMap<>();
      
      // use Strategy 
      strategyMap.put(c -> c.getReq() != null, c  -> new Request(this.visitGetReq(c.getReq())));
      strategyMap.put(c -> c.delReq() != null, c  -> new Request(this.visitDelReq(c.delReq())));
      strategyMap.put(c -> c.putReq() != null, c  -> new Request(this.visitPutReq(c.putReq())));
      strategyMap.put(c -> c.postReq() != null, c -> new Request(this.visitPostReq(c.postReq())));

      for (Map.Entry<Function<AQLParser.RequestContext, Boolean>, Function<AQLParser.RequestContext, Request>> entry : strategyMap.entrySet()) {
          if (entry.getKey().apply(ctx)) {
              return entry.getValue().apply(ctx);
          }
      }

      throw new IllegalStateException("Unsupported request type");
  }
  
  @Override
  public GetReq visitGetReq(AQLParser.GetReqContext ctx) {
    URIParts URIparts = new URIParts(ctx.dynamicURI());
    Params params = null;
    if (ctx.withBlock() != null) { 
      params = this.visitParams(ctx.withBlock().params());
    }
    return new GetReq(URIparts.getHead(), URIparts.getBody(), URIparts.getTail(), params);
  }

  @Override
  public DelReq visitDelReq(AQLParser.DelReqContext ctx) {
    URIParts URIparts = new URIParts(ctx.dynamicURI());
    Params params = null;
    if (ctx.withBlock() != null) { 
      params = this.visitParams(ctx.withBlock().params());
    }
    return new DelReq(URIparts.getHead(), URIparts.getBody(), URIparts.getTail(), params);
  }

  @Override
  public PutReq visitPutReq(AQLParser.PutReqContext ctx) {
    URIParts URIparts = new URIParts(ctx.dynamicURI());
    Params params = null;
    if (ctx.withBlock() != null) { 
      params = this.visitParams(ctx.withBlock().params());
    }
    return new PutReq(URIparts.getHead(), URIparts.getBody(), URIparts.getTail(), params);
  }

  @Override
  public PostReq visitPostReq(AQLParser.PostReqContext ctx) {
    URIParts URIparts = new URIParts(ctx.dynamicURI());
    Params params = null;
    if (ctx.withBlock() != null) { 
      params = this.visitParams(ctx.withBlock().params());
    }
    return new PostReq(URIparts.getHead(), URIparts.getBody(), URIparts.getTail(), params);
  }
    
  @Override
  public WithBlock visitWithBlock(AQLParser.WithBlockContext ctx) {          
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
              Object val = null;
              if (((ParamContext) ctx.getChild(i)).value() != null) {
                  val = DataEvaluator.getValue(((ParamContext) ctx.getChild(i)).value());
              }
              else if (((ParamContext) ctx.getChild(i)).dynamicVar() != null) {
                  val = DataEvaluator.getDynamicVar(((ParamContext) ctx.getChild(i)).dynamicVar());
              }
              else {
                  throw new IllegalArgumentException("Invalid parameter value");
              }
              params.put(key, val);
          }
          
      }
      if (params.isEmpty()) {
        throw new IllegalStateException("Invalid parameters: expected a JSON Object");
      }

      return new Params(params);
  }

    @Override
    public Loop visitLoop(AQLParser.LoopContext ctx) {
        Variable loopControlVariable = DataEvaluator.getVar(ctx.VARIABLE());
        Object iterable = null;
        if (ctx.getReq() != null) {
          iterable = this.visitGetReq(ctx.getReq());
        }
        else if (ctx.dynamicVar() != null) {
          iterable = DataEvaluator.getDynamicVar(ctx.dynamicVar());
        }
        List<Statement> statements = new ArrayList<>();
        for (AQLParser.StatementContext sCtx : ctx.statement()) {
            Statement statement = (Statement) sCtx.accept(this);
            statements.add(statement);
        }
        Program loopBody = new Program(statements);
        return new Loop(iterable, loopControlVariable, loopBody);
    }
    

  @Override
  public OnElse visitOnElse(AQLParser.OnElseContext ctx) {

      Condition condition = this.visitCondition(ctx.condition());
      Program onBody = this.visitProgram(ctx.program(0));
      if (onBody.getTasks().isEmpty()) {
          throw new InvalidRequestStateException("Empty ON body");
      }

      Program elseBody = null;
      if (ctx.ELSE() != null) {
          elseBody = this.visitProgram(ctx.program(1));
          if (elseBody.getTasks().isEmpty()) {
              throw new InvalidRequestStateException("Empty ELSE body");
          }
      }

      return new OnElse(condition, onBody, elseBody);
  }

    @Override
    public Condition visitCondition(AQLParser.ConditionContext ctx) {
        if (ctx.getChildCount() != 3) {
            throw new InvalidRequestStateException("Invalid condition format. " + ctx.getText());
        }
        
        Object leftOperand = DataEvaluator.getStringRepresentation(ctx.getChild(0), this);
        String operator = ctx.getChild(1).getText();
        Object rightOperand = DataEvaluator.getStringRepresentation(ctx.getChild(2), this);

        if (leftOperand == null || !DataEvaluator.isValidOperator(operator) || rightOperand == null) {
            throw new IllegalArgumentException("Invalid Conditional Statement at: " + ctx.getText());
        }
        
        return new Condition(leftOperand, operator, rightOperand);
    }

    @Override
    public Set visitSet(AQLParser.SetContext ctx) {
        Object val = null;
        if (ctx.params() instanceof AQLParser.ParamsContext) {
            val = this.visitParams(ctx.params());
        } else if (ctx.request() instanceof AQLParser.RequestContext) {
            val = this.visitRequest(ctx.request());
        } else if (ctx.value() instanceof AQLParser.ValueContext) {
            val = DataEvaluator.getValue(ctx.value());
        } else {
          throw new IllegalArgumentException("Invalid SET statement: malformed value");
        }

        String key = ctx.VARIABLE().getText();
        if (key == null) {
            throw new IllegalArgumentException("Invalid SET statement: malformed key");
        }

        return new Set(key, val);
    }
    
    @Override
    public Log visitLog(AQLParser.LogContext ctx) {
        Object val = null;

        if (ctx.value() != null) {
            val = DataEvaluator.getValue(ctx.value());
        }
        else if (ctx.dynamicVar() != null) {
            val = DataEvaluator.getDynamicVar(ctx.dynamicVar());
        }

        if (val == null) {
            throw new IllegalArgumentException("Invalid LOG: missing message to print");
        }
        
        return new Log(val);
    }

}