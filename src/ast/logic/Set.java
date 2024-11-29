package ast.logic;

import ast.Node;
import controller.AQLVisitorType;

public class Set extends Node {
    private String key;
    private Object val;

    public Set(String key, Object val) {
        this.key = key;
        this.val = val;
    }

    public String getKey() {
        return this.key;
    }

    public Object getVal() {
        return this.val;
    }

    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }
}