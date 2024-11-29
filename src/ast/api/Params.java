package ast.api;

import org.json.JSONObject;

import ast.Node;
import controller.AQLVisitorType;

public class Params extends Node {
    JSONObject content = new JSONObject();

    public Params(JSONObject content) {
        this.content = content;
    }

    public JSONObject getContent() {
        return this.content;
    }

    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }
}
