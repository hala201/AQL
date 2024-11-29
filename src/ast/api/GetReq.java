package ast.api;

import java.util.List;

import ast.Node;
import controller.AQLVisitorType;
import controller.IRequest;

public class GetReq extends Node implements IRequest {
    private String head;
    private List<String> body;
    private List<String> tail;
    private Params params;

    public GetReq(String head, List<String> body, List<String> tail, Params params2) {
        this.head = head;
        this.body = body;
        this.tail = tail;
        this.params = params2;
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

    public Params getParams() {
        return this.params;
    }

    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }
}