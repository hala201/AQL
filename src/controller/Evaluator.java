package controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import ast.logic.Log;
import ast.logic.Set;
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
import ast.loop.Loop;
import ast.loop.Variable;
import controller.helper.ConditionUtils;
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

    // TODO: remove most of out.write(), they are for debugging
    private boolean executionHalted = false;
    @Override
    public Void visit(Program p, PrintWriter out) {
        out.write("doing program ");
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
            out.write("doing statement ");
            s.getStatement().accept(this, out);
        }
        return null;
    }

    @Override
    public Void visit(Request r, PrintWriter out) {
        out.write("doing request ");
        r.getRequest().accept(this, out);
        return null;
    }

    // Object here are expected to be either JSONObject or JSONArray
    private Object useHandler(IRequest request, PrintWriter out) {
        IRequestHandler handler = this.handlers.get(request.getClass());
        if (handler != null) {
            try {
                return handler.handleRequest(request, out, this.environment, this.memory);
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
        out.write("doing WITH ");
        wb.getParams().accept(this, out);
        return null;
    }

    @Override
    public Void visit(Params p, PrintWriter out) {
        out.write("doing PARAMS ");
        return null;
    }

    @Override
    public Object visit(Log l, PrintWriter out) {
        out.print("doing LOG ");
        String message = l.getMessage();
        out.print(message);
        return message;
    }

    @Override
    public Object visit(Set s, PrintWriter out) {
        if (s == null) {
            throw new NullPointerException("The passed Set object is null.");
        }
        if (s.getVariableName() == null) {
            throw new IllegalArgumentException("SET: variable name is null");
        }
        out.write("doing SET ");
        int currentMemPtr = this.memptr;
        boolean success = false;
        try {
            if (s.getRequest() != null) {
                Object response = null;
                out.write("with request ");
                if (s.getRequest() instanceof GetReq) {
                    response = ((GetReq) s.getRequest()).accept(this, out);
                    this.environment.put(s.getVariableName(), currentMemPtr);
                    this.memory.put(currentMemPtr, response);
                    this.memptr++;
                    success = true;
                    return response;
                } else if (s.getRequest() instanceof DelReq) {
                    response = ((DelReq) s.getRequest()).accept(this, out);
                    this.environment.put(s.getVariableName(), currentMemPtr);
                    this.memory.put(currentMemPtr, response);
                    this.memptr++;
                    success = true;
                    return response;
                } else if (s.getRequest() instanceof PutReq) {
                    response = ((PutReq) s.getRequest()).accept(this, out);
                    this.environment.put(s.getVariableName(), currentMemPtr);
                    this.memory.put(currentMemPtr, response);
                    this.memptr++;
                    success = true;
                    return response;
                } else if (s.getRequest() instanceof PostReq) {
                    response = ((PostReq) s.getRequest()).accept(this, out);
                    this.environment.put(s.getVariableName(), currentMemPtr);
                    this.memory.put(currentMemPtr, response);
                    this.memptr++;
                    success = true;
                    return response;
                }
            } else if(s.getRightValue() != null){
                out.write("with value ");
                Object value = s.getRightValue();
                out.write(value.toString());
                this.environment.put(s.getVariableName(), currentMemPtr);
                this.memory.put(currentMemPtr, value);
                this.memptr++;
                success = true;
                return value;
            }
            throw new IllegalArgumentException("SET: unexpected request type");
        } catch (Exception e) {
            this.environment.remove(s.getVariableName());
            this.memory.remove(currentMemPtr);
            this.memptr = currentMemPtr;
            out.write("Error: " + e.getMessage());
            throw e;
        } finally {
            if (!success) {
                this.memptr = currentMemPtr;
            }
        }
    }

    @Override
    public Void visit(Loop loop, PrintWriter out) {
        out.write("doing FOR EACH\n");
        LocalScope.pushScope(this.localEnvironmentStack, this.environment);
        Variable loopControlVariable = loop.getLoopControlVariable();
        GetReq getReq = loop.getIterable();
        Object iterableData = this.visit(getReq, out);

        if (!(iterableData instanceof JSONObject) && !(iterableData instanceof JSONArray)) {
            throw new IllegalArgumentException("FOR EACH: Expected JSONObject or JSONArray as iterable data");
        }

        JSONArray dataArray = iterableData instanceof JSONObject ?
                new JSONArray().put((JSONObject) iterableData) : (JSONArray) iterableData;

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject entryObject = dataArray.getJSONObject(i);
            for (Map.Entry<String, Object> entry : entryObject.toMap().entrySet()) {
                JSONObject entryValue = new JSONObject().put(entry.getKey(), entry.getValue());
                loopControlVariable.setVariableContent(entryValue);

                int currentMemPtr = this.memptr;
                this.memory.put(currentMemPtr, entryValue);
                this.environment.put(loopControlVariable.getVariableName(), currentMemPtr);
                this.memptr++;

                //execute loop body
                Program loopBody = loop.getLoopBody();
                if (loopBody.getTasks().size() == 0) throw new IllegalArgumentException("FOR EACH: Empty loop body");
                this.visit(loopBody, out);

                this.memory.remove(currentMemPtr);
            }
        }

        LocalScope.popScope(this.localEnvironmentStack);
        return null;
    }

    @Override
    public Object visit(Value v, PrintWriter out) {
        out.print("doing VARIABLE\n");
        v.accept(this, out);
        return null;
    }

    @Override
    public Void visit(OnElse oe, PrintWriter out) {
        out.write("doing ON/ELSE\n");
        if (oe.getOnBody() == null) {
            out.write("ON/ELSE Error: empty ON body\n");
            throw new IllegalArgumentException("ON/ELSE: empty on body");
        }
        if (oe.getElseBody() == null){
            out.write("ON/ELSE Error: empty ELSE body\n");
            throw new IllegalArgumentException("ON/ELSE: empty else body");
        }
        if (oe.getCondition() == null) {
            out.write("ON/ELSE Error: missing condition\n");
            throw new IllegalArgumentException("ON/ELSE: null condition");
        }

        try {
            Boolean con = (Boolean) oe.getCondition().accept(this, out);
            if (con) {
                oe.getOnBody().accept(this, out);
            } else {
                oe.getElseBody().accept(this, out);
            }
        } catch (Exception e) {
            out.write("Condition Error: " + e.getMessage() + "\n");
            throw new IllegalArgumentException("Condition Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Boolean visit(Condition condition, PrintWriter out) {
        out.write("Evaluating CONDITION\n");

        Object leftOperand = ConditionUtils.evaluateOperand(condition.getLeft(), this.environment, this.memory);
        Object rightOperand = ConditionUtils.evaluateOperand(condition.getRight(), this.environment, this.memory);
        String operator = condition.getRule();

        return this.compareOperands(leftOperand, rightOperand, operator, out);
    }

    private Boolean compareOperands(Object left, Object right, String operator, PrintWriter out) {
        if (left instanceof Integer && right instanceof Integer) {
            return ConditionUtils.compareInteger((Integer) left, (Integer) right, operator);
        }
        else if (left instanceof String && right instanceof String) {
            return ConditionUtils.compareString((String) left, (String) right, operator);
        }
        else if (left instanceof JSONObject && right instanceof JSONObject) {
            return ConditionUtils.compareJSONObject((JSONObject) left, (JSONObject) right, operator);
        }
        else if (left instanceof JSONArray && right instanceof JSONArray) {
            return ConditionUtils.compareJSONArray((JSONArray) left, (JSONArray) right, operator);
        }
        else {
            throw new IllegalArgumentException("Incompatible operand types for comparison: " + left.getClass() + " and " + right.getClass());
        }
    }

}