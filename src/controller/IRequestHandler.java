package controller;

import java.io.PrintWriter;
import java.util.Map;

import org.json.JSONObject;

public interface IRequestHandler {
    Object handleRequest(IRequest request, PrintWriter out, Map<String, Integer> environment, Map<Integer, JSONObject> memory);
}
