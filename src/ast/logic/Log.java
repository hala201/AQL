package ast.logic;

import ast.Node;
import controller.AQLVisitorType;

public class Log extends Node {
    private Object msgObject;

    public Log(Object msg) {
        this.msgObject = msg;
    }

    public Object getMsgObject() {
        return this.msgObject;
    }

    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }
}