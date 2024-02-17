package ast.loop;

import ast.Node;
import ast.Program;
import ast.api.GetReq;
import controller.AQLVisitorType;

public class Loop extends Node {
    private Object iterableResponse;
    private Variable loopControlVariable;
    private Program loopBody;


    public Loop(Object iterableResponse, Variable loopControlVariable, Program loopBody) {
        this.iterableResponse = iterableResponse;
        this.loopControlVariable = loopControlVariable;
        this.loopBody = loopBody;
    }
    public Variable getLoopControlVariable() {
        return this.loopControlVariable;
    }

    public Object getIterable() {
        return this.iterableResponse;
    }

    public void setIterable(GetReq getReq) {
        this.iterableResponse = getReq;
    }

    public Program getLoopBody() {
        return this.loopBody;
    }

    public void setLoopBody(Program program) {
        this.loopBody = program;
    }

    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }
}