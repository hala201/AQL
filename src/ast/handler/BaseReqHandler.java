package ast.handler;

import java.io.PrintWriter;
import java.rmi.UnexpectedException;
import java.util.Map;

import org.json.JSONObject;

import controller.APIService;
import controller.IRequest;
import controller.IRequestHandler;

public abstract class BaseReqHandler implements IRequestHandler {
    protected APIService apiService;

    public BaseReqHandler(APIService apiService) {
        this.apiService = apiService;
    }

    protected abstract Object makeApiCall(String uri, JSONObject params) throws Exception;

    public Object handleRequest(IRequest request, PrintWriter out, Map<String, Integer> environment, Map<Integer, Object> memory) throws UnexpectedException {
        String parsedURI = this.buildUri(request, environment, memory);
        JSONObject json = null;

        if (request.getParams() != null) {
            json = request.getParams().getContent();
        }
        
        try {
            Object response = this.makeApiCall(parsedURI, json);
            return response;
        } catch (Exception e) {
            throw new RuntimeException("API call failed: " + e.getMessage(), e);
        }
    }

    private String buildUri(IRequest request, Map<String, Integer> environment, Map<Integer, Object> memory) throws UnexpectedException {
        StringBuilder uriBuilder = new StringBuilder(request.getHead());
    
        for (int i = 0; i < request.getBody().size(); i++) {
            String variablePlaceholder = request.getBody().get(i);
            // Process for dynamic variables
            String trimmedPlaceholder = variablePlaceholder.substring(1, variablePlaceholder.length() - 1);
            String[] parts = trimmedPlaceholder.split("\\.");
            if (parts.length == 2) {
                String variableName = parts[0];
                String propertyName = parts[1];
                if (environment.containsKey(variableName)) {
                    Integer pointer = environment.get(variableName);
                    if (memory.containsKey(pointer)) {
                        JSONObject data = (JSONObject) memory.get(pointer);
                        if (data.has(propertyName)) {
                            String resolvedValue = data.getString(propertyName);
                            uriBuilder.append(resolvedValue);
                        } else {
                            throw new RuntimeException("Property `" + propertyName + "` not found for variable `" + variableName + "`");
                        }
                    } else {
                        throw new RuntimeException("Variable `" + variableName + "` was not SET properly. Variable is not in memory.");
                    }
                } else {
                    throw new RuntimeException("Variable `" + variableName + "` was not SET properly.");
                }
            } else {
                throw new RuntimeException("Dynamic variable `" + variablePlaceholder + "` improperly formatted. Chaining like this is not allowed.");
            }
    
            if (i < request.getTail().size()) {
                String tailSegment = request.getTail().get(i);
                uriBuilder.append(tailSegment);
            }
        }
    
        return uriBuilder.toString();
    }

}