package ast.api;

import org.json.JSONObject;

import ast.Node;
import controller.AQLVisitorType;

public class WithBlock extends Node {

    private final Params params;

    public WithBlock(Params params) {
        this.params = params;
    }

    public Params getParams() {
        return this.params;
    }

    public JSONObject getReqBody() {
        return this.params.getContent();
    }

    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }
    
}