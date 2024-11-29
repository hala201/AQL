package ast.api;

import ast.Node;
import controller.AQLVisitorType;

public class Request extends Node {
    // GetReq, PutReq, DelReq, PostReq
    private final Node request;

    public Request(Node request) {
        this.request = request;
    }

    public Node getRequest() {
        return this.request;
    }

    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }
    
}
