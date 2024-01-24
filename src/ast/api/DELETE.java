package ast.api;

import java.util.Map;

import ast.Node;

public class DELETE extends Node {
    private String uri;
    private Map<String, String> params;


    @Override
    public Object evaluate() {
        return null;
    }
}