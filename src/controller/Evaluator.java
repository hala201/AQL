package controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONObject;

import ast.Program;
import ast.Statement;
import ast.Value;
import ast.api.DelReq;
import ast.api.GetReq;
import ast.api.Params;
import ast.api.PostReq;
import ast.api.PutReq;
import ast.api.Request;
import ast.api.WithBlock;
import ast.condition.Condition;
import ast.condition.OnElse;
import ast.handler.DelReqHandler;
import ast.handler.GetReqHandler;
import ast.handler.PostReqHandler;
import ast.handler.PutReqHandler;
import ast.logic.Log;
import ast.logic.Set;
import ast.loop.Loop;
import ast.loop.Variable;
import controller.helper.DataEvaluator;
import controller.helper.LocalScope;

public class Evaluator implements AQLVisitorType<PrintWriter, Object>{

    private final Map<String, Integer> environment = new HashMap<>();
    private final Map<Integer, Object> memory = new HashMap<>();
    private int memptr = 0;
    private APIService apiService = new APIService();
    private Stack<Map<String, Integer>> localEnvironmentStack = new Stack<>();
    // <CLASS of subclass of IRequest, subclass of IRequestHandler> 
    Map<Class<? extends IRequest>, IRequestHandler> handlers = new HashMap<>();

    public Evaluator() {
        this.handlers.put(GetReq.class, new GetReqHandler(this.apiService));
        this.handlers.put(DelReq.class, new DelReqHandler(this.apiService));
        this.handlers.put(PutReq.class, new PutReqHandler(this.apiService));
        this.handlers.put(PostReq.class, new PostReqHandler(this.apiService));
    }

    public Map<String, Integer> getEnvironment() {
        return this.environment;
    }

    public Map<Integer, Object> getMemory() {
        return this.memory;
    }

    public Map<String, Integer> getCurrentEnvironment() {
        return !this.localEnvironmentStack.isEmpty() ? this.localEnvironmentStack.peek() : this.environment;
    }

    private boolean executionHalted = false;
    @Override
    public Void visit(Program p, PrintWriter out) {
        for (Statement st : p.getTasks()) {
            if (this.executionHalted) {
                break;
            }
            try {
                st.accept(this, out);
            } catch (Exception e) {
                // if any statement threw an error, then print it out to user's console and stop here.
                this.executionHalted = true;
            }
        }
        return null;
    }

    @Override
    public Void visit(Statement s, PrintWriter out) {
        if (!this.executionHalted) {
            s.getStatement().accept(this, out);
        }
        return null;
    }

    @Override
    public Void visit(Request r, PrintWriter out) {
        r.getRequest().accept(this, out);
        return null;
    }

    // Object here are expected to be either JSONObject or JSONArray
    private Object useHandler(IRequest request, PrintWriter out) {
        IRequestHandler handler = this.handlers.get(request.getClass());
        if (handler != null) {
            try {
                if (request.getParams() != null)
                    request.getParams().accept(this, out);
                return handler.handleRequest(request, out, this.getCurrentEnvironment(), this.memory);
            } catch (Exception e) {
                out.write("Request Error: " + e.getMessage() + "\n");
                throw new RuntimeException("Request Error: " + e.getMessage());
            }
        }
        out.write("Unexpected Error: API handler couldn't recognize request type.\n");
        throw new RuntimeException("Unexpected Error: API handler couldn't recognize request type");
    }
    
    @Override
    public Object visit(GetReq gr, PrintWriter out) {
        return this.useHandler(gr, out);
    }

    @Override
    public Object visit(DelReq dr, PrintWriter out) {
        return this.useHandler(dr, out);
    }

    @Override
    public Object visit(PutReq pur, PrintWriter out) {
        return this.useHandler(pur, out);
    }

    @Override
    public Object visit(PostReq por, PrintWriter out) {
        return this.useHandler(por, out);
    }

    @Override
    public Void visit(WithBlock wb, PrintWriter out) {
        try {
            wb.getParams().accept(this, out);
        } catch (Exception e) {
            out.write("WITH Error: " + e.getMessage() + "\n");
            throw new RuntimeException("WITH: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Void visit(Params p, PrintWriter out) {
        JSONObject paramsContent = p.getContent();
        Iterator<String> keys = paramsContent.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            Object value = paramsContent.get(key);

            try {
                Object actualValue = DataEvaluator.getFromMemory(value, getCurrentEnvironment(), this.memory);
                paramsContent.put(key, actualValue);
            } catch (Exception e) {
                out.println("Error processing parameter " + key + ": " + e.getMessage());
                throw new RuntimeException("Error processing parameter " + key + ": " + e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Void visit(Loop loop, PrintWriter out) {
        LocalScope.pushScope(this.localEnvironmentStack, environment);
        Variable loopControlVariable = loop.getLoopControlVariable();
        Object iterable = null;

        if (loop.getIterable() instanceof GetReq) {
            iterable = ((GetReq) loop.getIterable()).accept(this, out);
        } else {
            iterable = DataEvaluator.getFromMemory(loop.getIterable(), this.getCurrentEnvironment(), this.memory);
        }

        if (!(iterable instanceof JSONObject || iterable instanceof JSONArray)) {
            throw new IllegalArgumentException("FOR EACH: iterable is not a JSON object or array");
        }

        JSONArray dataArray = iterable instanceof JSONObject ?
                new JSONArray().put((JSONObject) iterable) : (JSONArray) iterable;
        
        int currentMemPtr = this.memptr;
        this.memptr++;
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject entryObject = dataArray.getJSONObject(i);
            for (Map.Entry<String, Object> entry : entryObject.toMap().entrySet()) {
                JSONObject entryValue = new JSONObject().put(entry.getKey(), entry.getValue());
                loopControlVariable.setVariableContent(entryValue);

                this.memory.put(currentMemPtr, entryValue);
                this.getCurrentEnvironment().put(loopControlVariable.getVariableName(), currentMemPtr);

                //execute loop body
                Program loopBody = loop.getLoopBody();
                if (loopBody.getTasks().size() == 0) throw new IllegalArgumentException("FOR EACH: Empty loop body");
                this.visit(loopBody, out);
            }
        }
        // TBD: might be problem if have dupe variable names
        this.getCurrentEnvironment().remove(this.memory.get(currentMemPtr));
        this.memory.remove(currentMemPtr);

        LocalScope.popScope(this.localEnvironmentStack);
        return null;
    }

    @Override
    public Object visit(Value v, PrintWriter out) {
        v.accept(this, out);
        return null;
    }

    @Override
    public Void visit(OnElse oe, PrintWriter out) {
        try {

            if (oe.getOnBody() == null) {
                out.write("empty ON body");
                throw new IllegalArgumentException("empty on body");
            }
            if (oe.getCondition() == null) {
                out.write("missing condition");
                throw new IllegalArgumentException("null condition");
            }

            Boolean con = (Boolean) oe.getCondition().accept(this, out);
            if (con) {
                LocalScope.pushScope(this.localEnvironmentStack, getCurrentEnvironment());
                oe.getOnBody().accept(this, out);
                LocalScope.popScope(localEnvironmentStack);
            } else {
                LocalScope.pushScope(this.localEnvironmentStack, getCurrentEnvironment());
                if (oe.getElseBody() != null)
                    oe.getElseBody().accept(this, out);
                LocalScope.popScope(localEnvironmentStack);
            }

        } catch (Exception e) {
            out.println("ON/ELSE Error: " + e.getMessage());
            throw new IllegalArgumentException("Condition Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Boolean visit(Condition condition, PrintWriter out) {

        Object leftOperand = DataEvaluator.getFromMemory(condition.getLeft(), this.getCurrentEnvironment(), this.memory);
        Object rightOperand = DataEvaluator.getFromMemory(condition.getRight(), this.getCurrentEnvironment(), this.memory);
        String operator = condition.getRule();

        return this.compareOperands(leftOperand, rightOperand, operator, out);
    }

    private Boolean compareOperands(Object left, Object right, String operator, PrintWriter out) {
        if (!left.getClass().equals(right.getClass())) {
            return false;
        } else if (left instanceof Number && right instanceof Number) {
            return DataEvaluator.compareNumber((Number) left, (Number) right, operator);
        }
        else if (left instanceof String && right instanceof String) {
            return DataEvaluator.compareString((String) left, (String) right, operator);
        }
        else if (left instanceof JSONObject && right instanceof JSONObject) {
            return DataEvaluator.compareJSONObject((JSONObject) left, (JSONObject) right, operator);
        }
        else if (left instanceof JSONArray && right instanceof JSONArray) {
            return DataEvaluator.compareJSONArray((JSONArray) left, (JSONArray) right, operator);
        }
        else {
            throw new IllegalArgumentException("Incompatible operand types for comparison: " + left.getClass() + " and " + right.getClass());
        }
    }

    @Override
    public Void visit(Set set, PrintWriter out) {
        try {

            this.getCurrentEnvironment().put(set.getKey(), this.memptr);
            if (set.getVal() instanceof Request) {
                this.memory.put(this.memptr++, ((Request) set.getVal()).getRequest().accept(this, out));
            } else if (set.getVal() instanceof Params) {
                this.memory.put(this.memptr++, ((Params) set.getVal()).getContent());
            } else {
                this.memory.put(this.memptr++, set.getVal());
            }
        } catch (Exception e) {
            out.println("SET Error: " + e.getMessage() + "\n");
            throw new IllegalArgumentException("SET: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Void visit(Log log, PrintWriter out) {
        try {
            Object msg = DataEvaluator.getFromMemory(log.getMsgObject(), getCurrentEnvironment(), this.memory);
            if (msg == null) {
                out.write("trying to print " + log.getMsgObject());
                throw new IllegalArgumentException("LOG: trying to print Null");
            }
            out.println(msg);
        } catch (Exception e) {
            out.println("LOG Error: " + e.getMessage());
            throw new IllegalArgumentException("LOG: " + e.getMessage());
        }
        return null;
    }

}