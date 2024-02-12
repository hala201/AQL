package ast.loop;

import ast.Node;
import ast.Value;
import controller.AQLVisitorType;
import org.json.JSONObject;

import java.util.Objects;

public class Variable extends Value {
    private String variableName;
    private JSONObject variableContent;

    public Variable(String variableName, JSONObject variableContent) {
        this.variableName = variableName;
        this.variableContent = variableContent;
    }

    public String getVariableName() {
        return variableName;
    }

    public JSONObject getVariableContent() {
        return variableContent;
    }
    public void setVariableContent(JSONObject content) {
        this.variableContent = content;
    }

    @Override
    public String toString() {
        return getVariableName();
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
        return Objects.equals(variableName, variable.variableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableName);
    }
}
