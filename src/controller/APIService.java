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
    public Object makeGetRequest(String uri) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();
        return this.sendRequest(request);
    }

    public Object makeDeleteRequest(String uri) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .DELETE()
                .build();
        return this.sendRequest(request);
    }

    public Object makePostRequest(String uri, JSONObject json) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json.toString(), StandardCharsets.UTF_8))
                .build();
        return this.sendRequest(request);
    }

    public Object makePutRequest(String uri, JSONObject json) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json.toString(), StandardCharsets.UTF_8))
                .build();
        return this.sendRequest(request);
    }

    private Object sendRequest(HttpRequest request) throws Exception {
        HttpResponse<String> response = this.httpClient.send(request, BodyHandlers.ofString());

        JSONTokener tokener = new JSONTokener(response.body());
        Object result = tokener.nextValue(); // This could be a JSONObject or a JSONArray

        return result;
    }
}
