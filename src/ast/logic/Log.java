package ast.logic;

import ast.Node;
import controller.AQLVisitorType;

public class Log extends Node {
    private final String message;

    public Log(Object message) {
        this.message = message.toString();
    }

    public String getMessage() {
        return message;
    }

    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }

}

