package ast;

import java.util.List;

import controller.AQLVisitorType;

public class Program extends Node {
    private final List<Statement> tasks;

    public Program(List<Statement> tasks) {
        this.tasks = tasks;
    }

    public List<Statement> getTasks() {
        return this.tasks;
    }

    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }

}
