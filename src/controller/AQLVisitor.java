package controller;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import ast.logic.Log;
import ast.logic.Set;
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
import ast.loop.Loop;
import ast.loop.Variable;
import controller.helper.ConditionUtils;
import controller.helper.StringParser;
import controller.helper.URIParts;
import gen.AQLParser;
import gen.AQLParserBaseVisitor;

public class AQLVisitor extends AQLParserBaseVisitor<Node> {
    private Map<String, Object> environment = new HashMap<>();

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
    JSONObject params = null;
    if (ctx.withBlock() != null) { 
      params = this.visitWithBlock(ctx.withBlock()).getReqBody(); 
    }
    return new GetReq(URIparts.getHead(), URIparts.getBody(), URIparts.getTail(), params);
  }

  @Override
  public DelReq visitDelReq(AQLParser.DelReqContext ctx) {
    URIParts URIparts = new URIParts(ctx.dynamicURI());
    JSONObject params = null;
    if (ctx.withBlock() != null) { 
      params = this.visitWithBlock(ctx.withBlock()).getReqBody(); 
    }
    return new DelReq(URIparts.getHead(), URIparts.getBody(), URIparts.getTail(), params);
  }

  @Override
  public PutReq visitPutReq(AQLParser.PutReqContext ctx) {
    URIParts URIparts = new URIParts(ctx.dynamicURI());
    JSONObject params = null;
    if (ctx.withBlock() != null) { 
      params = this.visitWithBlock(ctx.withBlock()).getReqBody(); 
    }
    return new PutReq(URIparts.getHead(), URIparts.getBody(), URIparts.getTail(), params);
  }

  @Override
  public PostReq visitPostReq(AQLParser.PostReqContext ctx) {
    URIParts URIparts = new URIParts(ctx.dynamicURI());
    JSONObject params = null;
    if (ctx.withBlock() != null) { 
      params = this.visitWithBlock(ctx.withBlock()).getReqBody(); 
    }
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

    @Override
    public Loop visitLoop(AQLParser.LoopContext ctx) {
        Variable loopControlVariable = new Variable(ctx.VARIABLE(0).getText(), new JSONObject());
        GetReq iterable = null;
        if (ctx.getReq() != null) {
            iterable = this.visitGetReq(ctx.getReq());
        } else if (ctx.dynamicVar() != null) {
            iterable = (GetReq) ctx.dynamicVar().accept(this);
        } else if (ctx.VARIABLE(1) != null){
            iterable = (GetReq) ctx.VARIABLE(1).accept(this);
        }
        // TODO: To be uncommented once SET is ready
        // if (iterable == null) throw new IllegalArgumentException("FOR EACH: Iterable is Null or undefined");
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
        
        Object leftOperand = ConditionUtils.evaluateOperand(ctx.getChild(0), this);
        String operator = ctx.getChild(1).getText();
        Object rightOperand = ConditionUtils.evaluateOperand(ctx.getChild(2), this);

        if (leftOperand == null || !ConditionUtils.isValidOperator(operator) || rightOperand == null) {
            throw new IllegalArgumentException("Invalid Conditional Statement at: " + ctx.getText());
        }
        
        return new Condition(leftOperand, operator, rightOperand);
    }

    @Override
    public Node visitLog(AQLParser.LogContext ctx) {
        if(ctx.getChildCount() != 2) {
            throw new IllegalArgumentException("Invalid log statement");
        }
        String message;
        if (ctx.VARIABLE() != null) {
            String variableName = ctx.VARIABLE().getText();
            Object value = environment.get(variableName);
            message = value != null ? value.toString() : "undefined";
        } else if (ctx.dynamicVar() != null) {
            message = getDynamicVar(ctx.dynamicVar());
        } else if (ctx.value() != null) {
            message = ctx.value().getText();
        } else {
            message = "Error in log statement";
        }

        return new Log(message);
    }
    @Override
    public Node visitSet(AQLParser.SetContext ctx) {
        String variableName = ctx.VARIABLE().getText();
        Object value = null;
        if (ctx.request() != null) {
            if(ctx.request().getReq().getText().startsWith("GET")) {
                GetReq getReq = visitGetReq(ctx.request().getReq());
                value = getReq;
            } else if(ctx.request().delReq() != null) {
                DelReq delReq = visitDelReq(ctx.request().delReq());
                value = delReq;
            } else if(ctx.request().putReq() != null) {
                PutReq putReq = visitPutReq(ctx.request().putReq());
                value = putReq;
            } else if(ctx.request().postReq() != null) {
                PostReq postReq = visitPostReq(ctx.request().postReq());
                value = postReq;
            }
            System.out.println(value);
        } else if (ctx.value() != null) {
            System.out.println("Value: " + ctx.value().getText());
            value = ctx.value().getText();
        } else {
            throw new IllegalArgumentException("Error in set statement");
        }
        environment.put(variableName, value);
        if(value instanceof IRequest) {
            return new Set((IRequest) value, variableName);
        } else if (value instanceof String) {
            return new Set((String) value, variableName);
        } else if (value instanceof Integer) {
            return new Set((Integer) value, variableName);
        }
        throw new IllegalArgumentException("Error in set statement");
    }

    public String getDynamicVar(AQLParser.DynamicVarContext ctx) {
        StringBuilder fullDynamicVar = new StringBuilder();
        if (ctx.VARIABLE().size() == 2) {
            String objectName = ctx.VARIABLE(0).getText();
            String propertyName = ctx.VARIABLE(1).getText();

            fullDynamicVar.append(objectName).append(".").append(propertyName);
        }

        if (ctx.getText().startsWith("{") && ctx.getText().endsWith("}")) {
            return "{" + fullDynamicVar.toString() + "}";
        } else {
            return fullDynamicVar.toString();
        }
    }

    private String getValue(AQLParser.ValueContext ctx) {
        if (ctx.NUMBER() != null) {
            return ctx.NUMBER().getText();
        } else if (ctx.string() != null) {
            String str = ctx.string().getText();
            return str.substring(1, str.length() - 1);
        }
        return "";
    }
    
}
