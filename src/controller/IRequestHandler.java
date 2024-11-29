package controller;

import java.io.PrintWriter;
import java.rmi.UnexpectedException;
import java.util.Map;

public interface IRequestHandler {
    Object handleRequest(IRequest request, PrintWriter out, Map<String, Integer> environment, Map<Integer, Object> memory) throws UnexpectedException;
}
