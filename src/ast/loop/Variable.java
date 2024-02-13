package ast.loop;

import java.util.Objects;

import ast.Value;
import controller.AQLVisitorType;

public class Variable extends Value {
    private String variableName;
    private Object variableContent;

    public Variable(String variableName, Object variableContent) {
        this.variableName = variableName;
        this.variableContent = variableContent;
    }

    public String getVariableName() {
        return this.variableName;
    }

    public Object getVariableContent() {
        return this.variableContent;
    }
    public void setVariableContent(Object content) {
        this.variableContent = content;
    }

    @Override
    public String toString() {
        return this.getVariableName();
    }

    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variable)) return false;
        Variable variable = (Variable) o;
        return Objects.equals(this.variableName, variable.variableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.variableName);
    }
}