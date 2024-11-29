package ast.condition;

import ast.Node;
import controller.AQLVisitorType;

public class Condition extends Node {

    private Object left;

    private String rule;
    private Object right;

    public Condition(Object left, String rule,Object right) {
        this.left = left;
        this.rule = rule;
        this.right = right;
    }

    public String getRule() {
        return this.rule;
    }

    public Object getLeft() {
        return this.left;
    }

    public Object getRight() {
        return this.right;
    }

    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }
}