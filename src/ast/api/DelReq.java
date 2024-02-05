package ast.api;

import java.util.List;

import org.json.JSONObject;

import ast.Node;
import controller.AQLVisitorType;
import controller.IRequest;

public class DelReq extends Node implements IRequest {
    private String head;
    private List<String> body;
    private List<String> tail;
    private JSONObject params;

    public DelReq(String head, List<String> body, List<String> tail, JSONObject params) {
        this.head = head;
        this.body = body;
        this.tail = tail;
        this.params = params;
    }

    public String getHead() {
        return this.head;
    }
    
    public List<String> getBody() {
        return this.body;
    }

    public List<String> getTail() {
        return this.tail;
    }

    public JSONObject getParams() {
        return this.params;
    }

    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }
}