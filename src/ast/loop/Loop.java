package ast.loop;

import ast.Node;
import ast.Program;
import ast.api.GetReq;
import ast.api.Params;
import ast.api.Request;
import controller.AQLVisitorType;

import javax.sound.sampled.Port;
import java.util.List;

public class Loop extends Node {
    private GetReq iterableResponse;
    private Variable loopControlVariable;
    private Program loopBody;


    public Loop(GetReq iterableResponse, Variable loopControlVariable, Program loopBody) {
        this.iterableResponse = iterableResponse;
        this.loopControlVariable = loopControlVariable;
        this.loopBody = loopBody;
    }
    public Variable getLoopControlVariable() {
        return loopControlVariable;
    }

    public GetReq getIterable() {
        return iterableResponse;
    }

    public void setIterable(GetReq getReq) {
        iterableResponse = getReq;
    }

    public Program getLoopBody() {
        return loopBody;
    }

    public void setLoopBody(Program program) {
        loopBody = program;
    }




    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }
}
