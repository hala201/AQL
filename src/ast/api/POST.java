// package ast.api;

// import java.io.PrintWriter;
// import java.net.URI;
// import java.net.http.HttpClient;
// import java.net.http.HttpRequest;
// import java.net.http.HttpResponse;
// import java.nio.charset.StandardCharsets;

// import ast.Node;

// public class POST extends Node {
//     private String uri;
//     private String params;
//     public void setURI(String uri_)
//     {
//         this.uri = uri_;
//     }
//     public void setParams(String params_)
//     {
//         this.params = params_;
//     }
//     public HttpResponse<String> sendPost()
//     {
//         try {
//             HttpClient client = HttpClient.newBuilder()
//                     .version(HttpClient.Version.HTTP_2)
//                     .build();
//             HttpRequest req = HttpRequest.newBuilder()
//                     .POST(HttpRequest.BodyPublishers.ofString(this.params, StandardCharsets.UTF_8))
//                     .uri(URI.create(this.uri))
//                     .header("Content-Type", "application/json")
//                     .build();

//             return client.send(req, HttpResponse.BodyHandlers.ofString());
//         } catch (Exception e) {
//             // stub
//         }
//         return null;
//     }
//     @Override
//     public Object evaluate(PrintWriter out) {
//         return null;
//     }
// }