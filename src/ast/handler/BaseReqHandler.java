package ast.handler;

import java.io.PrintWriter;
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

    protected abstract Object makeApiCall(String uri) throws Exception;

    @Override
    public Object handleRequest(IRequest request, PrintWriter out, Map<String, Integer> environment, Map<Integer, JSONObject> memory) {
        String parsedURI = this.buildUri(request, environment, memory);
        out.write(parsedURI + "\n");
        try {
            Object response = this.makeApiCall(parsedURI);
            out.write(response.toString() + "\n");
            return response;
        } catch (Exception e) {
            out.write("Error during API call: " + e.getMessage() + "\n");
            e.printStackTrace(out);
            return null;
        }
    }

    private String buildUri(IRequest request, Map<String, Integer> environment, Map<Integer, JSONObject> memory) {
        StringBuilder uriBuilder = new StringBuilder(request.getHead());
    
        for (int i = 0; i < request.getBody().size(); i++) {
            String variablePlaceholder = request.getBody().get(i);
            // Process for dynamic variables
            if (variablePlaceholder.startsWith("{") && variablePlaceholder.endsWith("}")) {
                String trimmedPlaceholder = variablePlaceholder.substring(1, variablePlaceholder.length() - 1);
                String[] parts = trimmedPlaceholder.split("\\.");
                if (parts.length == 2) {
                    String variableName = parts[0];
                    String propertyName = parts[1];
                    if (environment.containsKey(variableName)) {
                        Integer pointer = environment.get(variableName);
                        if (memory.containsKey(pointer)) {
                            JSONObject data = memory.get(pointer);
                            if (data.has(propertyName)) {
                                String resolvedValue = data.getString(propertyName);
                                uriBuilder.append(propertyName).append(resolvedValue);
                            }
                        }
                    }
                }
            } else {
                uriBuilder.append(variablePlaceholder);
            }
    
            if (i < request.getTail().size()) {
                String tailSegment = request.getTail().get(i);
                uriBuilder.append(tailSegment);
            }
        }
    
        return uriBuilder.toString();
    }

}
