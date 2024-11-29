package ast;

import controller.AQLVisitorType;

public class Value extends Node {
    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }
}
