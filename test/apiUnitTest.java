import static org.junit.Assert.*;

import controller.APIService;
import org.json.JSONObject;
import org.junit.Test;


public class apiUnitTest {
    @Test
    public void testSimpleGET() {

    }

    @Test
    public void testSimpleDELETE() {

    }

    @Test
    public void testSimplePUT() {
        // wiremock api from Ricky's setup
        APIService api = new APIService();
        JSONObject json = new JSONObject("{\n" +
                "  \"id\": 12345,\n" +
                "  \"value\": \"abc-def-ghi\"\n" +
                "}");
        try
        {
            Object res = api.makePutRequest("https://8k06e.wiremockapi.cloud/json/2", json);
            assertEquals("{\"message\":\"Success\"}", res.toString());
        }
        catch (Exception e)
        {
            assertTrue(false);
        }
    }

    @Test
    public void testSimplePOST() {
        // wiremock api from Ricky's setup
        APIService api = new APIService();
        JSONObject json = new JSONObject("{\n" +
                "  \"id\": 12345,\n" +
                "  \"value\": \"abc-def-ghi\"\n" +
                "}");
        try
        {
            Object res = api.makePostRequest("https://8k06e.wiremockapi.cloud/json", json);
            assertEquals("{\"message\":\"Success\"}", res.toString());
        }
        catch (Exception e)
        {
            assertTrue(false);
        }
    }
}
