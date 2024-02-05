package ast.api;

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

    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }
    
}
