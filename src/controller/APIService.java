package controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class APIService {
    private final HttpClient httpClient;

    public APIService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public Object makeGetRequest(String uri, JSONObject params) throws Exception {
        HttpRequest request = this.buildRequest(uri, "GET", params);
        return this.sendRequest(request);
    }

    public Object makePostRequest(String uri, JSONObject params) throws Exception {
        HttpRequest request = this.buildRequest(uri, "POST", params);
        return this.sendRequest(request);
    }

    public Object makePutRequest(String uri, JSONObject params) throws Exception {
        HttpRequest request = this.buildRequest(uri, "PUT", params);
        return this.sendRequest(request);
    }

    public Object makeDeleteRequest(String uri, JSONObject params) throws Exception {
        HttpRequest request = this.buildRequest(uri, "DELETE", params);
        return this.sendRequest(request);
    }

    private HttpRequest buildRequest(String uri, String method, JSONObject params) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json");
    
        JSONObject bodyParams = new JSONObject();
    
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                if (key.startsWith("AQL@")) {
                    // handle special keys prefixed with AQL@
                    String actualKey = key.substring(4);
                    if ("Authorization".equals(actualKey)) {
                        String value = params.getString(key);
                        requestBuilder.header("Authorization", "Bearer " + value);
                    } else {
                        String value = params.getString(key);
                        requestBuilder.header(actualKey, value);
                    }
                } else if (key.startsWith("AQL@Body") || !key.startsWith("AQL@")) {
                    bodyParams.put(key, params.get(key));
                }
            }
        }
    
        if (bodyParams.length() > 0) {
            String requestBody = bodyParams.toString();
            requestBuilder.method(method, HttpRequest.BodyPublishers.ofString(requestBody));
        } else {
            if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method)) {
                requestBuilder.method(method, HttpRequest.BodyPublishers.noBody());
            } else {
                requestBuilder.method(method, HttpRequest.BodyPublishers.noBody());
            }
        }
    
        return requestBuilder.build();
    }

    private Object sendRequest(HttpRequest request) throws Exception {
        try {
            HttpResponse<String> response = this.httpClient.send(request, BodyHandlers.ofString());

            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                JSONTokener tokener = new JSONTokener(response.body());
                Object result = tokener.nextValue();
                if (result instanceof JSONObject || result instanceof JSONArray) {
                    return result;
                } else {
                    throw new RuntimeException("Language only supports Response with a JSON format");
                }
            } else {
                String errorMessage = String.format("Error: HTTP %d - %s", response.statusCode(), response.body());
                throw new RuntimeException(errorMessage);
            }
        } catch (java.net.UnknownHostException e) {
            throw new RuntimeException("Unknown host - " + e.getMessage());
        } catch (java.net.MalformedURLException e) {
            throw new RuntimeException("Malformed URL - " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("I/O error - " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Request interrupted - " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException((e.getMessage() != null ? e.getMessage() : "Unknown error"));
        }
    }

}