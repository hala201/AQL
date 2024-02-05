package controller;

import ast.Program;
import ast.Statement;
import ast.api.DelReq;
import ast.api.GetReq;
import ast.api.Params;
import ast.api.Request;
import ast.api.WithBlock;

public interface AQLVisitorType<T,U>  {
    U visit (Program p, T t);

    U visit (Statement s, T t);

    U visit(Request r, T t);

    U visit(GetReq gr, T t);

    U visit(DelReq dr, T t);

    U visit(WithBlock wb, T t);

    U visit(Params p, T t);

} 