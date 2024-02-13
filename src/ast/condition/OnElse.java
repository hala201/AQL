package ast.condition;

import ast.Node;
import ast.Program;
import controller.AQLVisitorType;

public class OnElse extends Node {

    private Condition condition;
    private Program onBody;
    private Program elseBody;

    public OnElse(Condition condition, Program onBody, Program elseBody) {
        this.condition = condition;
        this.onBody = onBody;
        this.elseBody = elseBody;
    }

    public Program getOnBody() {
        return this.onBody;
    }

    public Condition getCondition() {
        return this.condition;
    }

    public Program getElseBody() {
        return this.elseBody;
    }

    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }
}