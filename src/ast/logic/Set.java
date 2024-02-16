package ast.logic;

import ast.Node;
import controller.AQLVisitorType;
import controller.IRequest;

public class Set extends Node {
    private String variableName;
    private Object rightValue;
    private IRequest request;

    public Set(Integer rightValue, String variableName) {
        this.rightValue = rightValue;
        this.variableName = variableName;
    }

    public Set(String rightValue, String variableName) {
        this.rightValue = rightValue;
        this.variableName = variableName;
    }

    public Set(IRequest request, String variableName) {
        this.request = request;
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    public Object getRightValue() {
        return rightValue;
    }

    public IRequest getRequest() {
        return request;
    }
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }

}

