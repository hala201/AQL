// import static org.junit.Assert.assertEquals;
// import ast.api.*;
// import org.junit.Test;

// import java.net.http.HttpResponse;

// public class apiUnitTest {
//     @Test
//     public void testSimplePUT() {
//         PUT put = new PUT();
//         put.setURI("https://8k06e.wiremockapi.cloud/json/2");
//         put.setParams("{\n" +
//                 "  \"id\": 12345,\n" +
//                 "  \"value\": \"abc-def-ghi\"\n" +
//                 "}");
//         HttpResponse<String> res;
//         res = put.sendPut();
//         assertEquals(200, res.statusCode());
//     }

//     @Test
//     public void testSimplePOST() {
//         POST post = new POST();
//         post.setURI("https://8k06e.wiremockapi.cloud/json");
//         post.setParams("{\n" +
//                 "  \"id\": 12345,\n" +
//                 "  \"value\": \"abc-def-ghi\"\n" +
//                 "}");
//         HttpResponse<String> res;
//         res = post.sendPost();
//         assertEquals(201, res.statusCode());
//     }
// }
