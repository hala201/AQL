package ast.logic;

import java.util.Map;

import ast.Node;

public class SET extends Node {
    private String uri;
    private Map<String, String> params;


    @Override
    public Object evaluate() {
        return null;
    }
}