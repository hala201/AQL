package controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import org.json.JSONTokener;

public class APIService {

    private HttpClient httpClient;

    public APIService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    // TODO: make sure to handle only JSON response, and throw appropriate exceptions
    public Object makeGetRequest(String uri, JSONObject json) throws Exception {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header("Content-Type", "application/json");
        if (json != null) {
            requestBuilder.method("GET", HttpRequest.BodyPublishers.ofString(json.toString(), StandardCharsets.UTF_8));
        } else {
            requestBuilder.GET();
        }
        return this.sendRequest(requestBuilder.build());
    }

    public Object makeDeleteRequest(String uri, JSONObject json) throws Exception {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header("Content-Type", "application/json");
        if (json != null) {
            requestBuilder.method("DELETE", HttpRequest.BodyPublishers.ofString(json.toString(), StandardCharsets.UTF_8));
        } else {
            requestBuilder.DELETE();
        }
        return this.sendRequest(requestBuilder.build());
    }

    public Object makePostRequest(String uri, JSONObject json) throws Exception {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header("Content-Type", "application/json");
        if (json != null) {
            requestBuilder.POST(HttpRequest.BodyPublishers.ofString(json.toString(), StandardCharsets.UTF_8));
        } else {
            // POST with an empty body
            requestBuilder.POST(HttpRequest.BodyPublishers.noBody());
        }
        return this.sendRequest(requestBuilder.build());
    }

    public Object makePutRequest(String uri, JSONObject json) throws Exception {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header("Content-Type", "application/json");
        if (json != null) {
            requestBuilder.PUT(HttpRequest.BodyPublishers.ofString(json.toString(), StandardCharsets.UTF_8));
        } else {
            // PUT empty body
            requestBuilder.PUT(HttpRequest.BodyPublishers.noBody());
        }
        return this.sendRequest(requestBuilder.build());
    }

    private Object sendRequest(HttpRequest request) throws Exception {
        HttpResponse<String> response = this.httpClient.send(request, BodyHandlers.ofString());

        JSONTokener tokener = new JSONTokener(response.body());
        Object result = tokener.nextValue(); // This could be a JSONObject or a JSONArray

        return result;
    }
}
