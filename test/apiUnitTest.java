import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.APIService;

public class apiUnitTest {
    private APIService api;

    @BeforeEach
    public void setUp() {
        this.api = new APIService();
    }

    @Test
    public void testSimpleGET() throws Exception {
        // wiremock api from Meng's setup
        JSONObject expected = new JSONObject("{\"task1\":\"haha\"}");
        Object result = this.api.makeGetRequest("https://65y4r.wiremockapi.cloud/users/123/tasks", new JSONObject());
        assertEquals(expected.toString(), result.toString(), "GET response should match expected task.");
    }

    @Test
    public void testSimpleDELETE() throws Exception {
        // wiremock api from Meng's setup
        JSONObject expected = new JSONObject("{\"message\": \"Task 123 deleted successfully\"}");
        Object result = this.api.makeDeleteRequest("https://65y4r.wiremockapi.cloud/users/123/tasks", new JSONObject());
        assertEquals(expected.toString(), result.toString(), "DELETE response should match success message.");
    }

    @Test
    public void testSimplePUT() throws Exception {
        // wiremock api from Ricky's setup
        JSONObject request = new JSONObject("{ \"id\": 12345, \"value\": \"abc-def-ghi\" }");
        JSONObject expected = new JSONObject("{\"message\":\"Success\"}");
        Object result = this.api.makePutRequest("https://8k06e.wiremockapi.cloud/json/2", request);
        assertEquals(expected.toString(), result.toString(), "PUT response should match success message.");
    }

    @Test
    public void testSimplePOST() throws Exception {
        // wiremock api from Ricky's setup
        JSONObject request = new JSONObject("{ \"id\": 12345, \"value\": \"abc-def-ghi\" }");
        JSONObject expected = new JSONObject("{\"message\":\"Success\"}");
        Object result = this.api.makePostRequest("https://8k06e.wiremockapi.cloud/json", request);
        assertEquals(expected.toString(), result.toString(), "POST response should match success message.");
    }
}
