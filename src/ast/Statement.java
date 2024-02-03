package ast;

import controller.AQLVisitorType;

public class Statement extends Node {
    // Request, Loop, Log, Set
    private final Node statement;

    public Statement(Node statement) {
        this.statement = statement;
    }
    
    public Node getStatement() {
        return this.statement;
    }

    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }

}
