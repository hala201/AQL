package controller;

import ast.Program;
import ast.Statement;
import ast.api.DelReq;
import ast.api.GetReq;
import ast.api.Request;

public interface AQLVisitorType<T,U>  {
    U visit (Program p, T t);

    U visit (Statement s, T t);

    U visit(Request r, T t);

    U visit(GetReq gr, T t);

    U visit(DelReq dr, T t);

} 