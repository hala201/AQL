package controller;

import java.io.PrintWriter;
import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import ast.Program;
import ast.Statement;
import ast.api.DelReq;
import ast.api.GetReq;
import ast.api.PutReq;
import ast.api.PostReq;
import ast.api.Params;
import ast.api.Request;
import ast.api.WithBlock;
import ast.handler.DelReqHandler;
import ast.handler.GetReqHandler;
import ast.handler.PutReqHandler;
import ast.handler.PostReqHandler;

public class Evaluator implements AQLVisitorType<PrintWriter, Object>{

    private final Map<String, Integer> environment = new HashMap<>();
    private final Map<Integer, JSONObject> memory = new HashMap<>();
    private int memptr = 0;
    private APIService apiService = new APIService();
    // <CLASS of subclass of IRequest, subclass of IRequestHandler> 
    Map<Class<? extends IRequest>, IRequestHandler> handlers = new HashMap<>();

    public Evaluator() {
        this.handlers.put(GetReq.class, new GetReqHandler(this.apiService));
        this.handlers.put(DelReq.class, new DelReqHandler(this.apiService));
        this.handlers.put(PutReq.class, new PutReqHandler(this.apiService));
        this.handlers.put(PostReq.class, new PostReqHandler(this.apiService));
        // TODO: remove, this is a mock for testing dynamicURI
        this.environment.put("user", this.memptr);
        this.memory.put(this.memptr++, new JSONObject("{\"id\": \"123\"}"));
    }

    public Map<String, Integer> getEnvironment() {
        return this.environment;
    }

    public Map<Integer, JSONObject> getMemory() {
        return this.memory;
    }

    // TODO: remove most of out.write(), they are for debugging
    @Override
    public Void visit(Program p, PrintWriter out) {
        out.write("doing program ");
        for (Statement st : p.getTasks()) {
            st.accept(this, out);
        }
        return null;
    }

    @Override
    public Void visit(Statement s, PrintWriter out) {
        out.write("doing statement ");
        s.getStatement().accept(this, out);
        return null;
    }

    @Override
    public Void visit(Request r, PrintWriter out) {
        out.write("doing request ");
        r.getRequest().accept(this, out);
        return null;
    }

    // SET will deal with storing the response, if it is chained with a request
    @Override
    // Object here are expected to be either JSONObject or JSONArray
    public Object visit(GetReq gr, PrintWriter out) {
        IRequestHandler handler = this.handlers.get(gr.getClass());
        if (handler != null) {
            try {
                return handler.handleRequest(gr, out, this.environment, this.memory);
            } catch (UnexpectedException e) {
                out.write(e.getMessage());
            }
        }
        throw new IllegalArgumentException("Unsupported request type");
    }

    @Override
    public Object visit(DelReq dr, PrintWriter out) {
        IRequestHandler handler = this.handlers.get(dr.getClass());
        if (handler != null) {
            try {
                return handler.handleRequest(dr, out, this.environment, this.memory);
            } catch (UnexpectedException e) {
                out.write(e.getMessage());
            }
        }
        throw new IllegalArgumentException("Unsupported request type");
    }

    @Override
    public Object visit(PutReq pur, PrintWriter out) {
        IRequestHandler handler = this.handlers.get(pur.getClass());
        if (handler != null) {
            try {
                return handler.handleRequest(pur, out, this.environment, this.memory);
            } catch (UnexpectedException e) {
                out.write(e.getMessage());
            }
        }
        throw new IllegalArgumentException("Unsupported request type");
    }

    @Override
    public Object visit(PostReq por, PrintWriter out) {
        IRequestHandler handler = this.handlers.get(por.getClass());
        if (handler != null) {
            try {
                return handler.handleRequest(por, out, this.environment, this.memory);
            } catch (UnexpectedException e) {
                out.write(e.getMessage());
            }
        }
        throw new IllegalArgumentException("Unsupported request type");
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

}

