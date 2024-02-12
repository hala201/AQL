package controller;

import java.io.PrintWriter;
import java.util.HashMap;
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
import ast.handler.DelReqHandler;
import ast.handler.GetReqHandler;
import ast.handler.PostReqHandler;
import ast.handler.PutReqHandler;
import ast.loop.Loop;
import ast.loop.Variable;
import controller.helper.LocalScope;

public class Evaluator implements AQLVisitorType<PrintWriter, Object>{

    private final Map<String, Integer> environment = new HashMap<>();
    private final Map<Integer, JSONObject> memory = new HashMap<>();
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

    public Map<Integer, JSONObject> getMemory() {
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
                out.write("Request Error: " + e.getMessage());
                throw new RuntimeException("Request Error: " + e.getMessage());
            }
        }
        out.write("Unexpected Error: API handler couldn't recognize request type");
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
    public Object visit(Loop loop, PrintWriter out) {
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

}
