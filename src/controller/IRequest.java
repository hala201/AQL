package controller;

import java.util.List;

import ast.api.Params;

public interface IRequest {
    String getHead();
    List<String> getBody();
    List<String> getTail();
    Params getParams();
}
