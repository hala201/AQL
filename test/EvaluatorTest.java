
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ast.api.DelReq;
import ast.api.GetReq;
import ast.api.PutReq;
import ast.api.PostReq;
import controller.Evaluator;

class EvaluatorTest {

    private Evaluator evaluator;
    private StringWriter output;

    @BeforeEach
    void setUp() {
        this.evaluator = new Evaluator();
        this.output = new StringWriter();

        // mock environment and memory 
        this.evaluator.getEnvironment().put("user", 1);
        this.evaluator.getMemory().put(1, new JSONObject("{\"id\":\"123\"}"));
    }

    @Test
    void testHandleSimpleGetReq_Happy1() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null);
        
        Object result = this.evaluator.visit(getReq, new PrintWriter(this.output));
        assertEquals(JSONObject.class, result.getClass());
        assertEquals(new JSONObject("{\"task1\":\"haha\"}").toString(), result.toString());
    }

    // TODO: log better errors
    @Test
    void testHandleSimpleGetReq_Validbodytail_BadURI() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/admins/", List.of("{user.id}"), List.of("/tasks"), null);
        
        Object result = this.evaluator.visit(getReq, new PrintWriter(this.output));
        assertEquals(String.class, result.getClass());
        assertEquals("Request was not matched", result.toString());
    }

    @Test
    void testHandleSimpleGetReq_MissingURI() {
        GetReq getReq = new GetReq("random", List.of(), List.of(), null);
        
        Object result = this.evaluator.visit(getReq, new PrintWriter(this.output));
        assertEquals(null, result);
        assertThrows(NullPointerException.class, () -> {
            result.toString();
        });
    }

    @Test
    void testHandleSimpleGetReq_NoAdminInMemory_BadURI1() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/", List.of("{admin.id}"), List.of("/tasks"), null);
        
        Object result = this.evaluator.visit(getReq, new PrintWriter(this.output));
        assertEquals(String.class, result.getClass());
        assertEquals("Request was not matched", result.toString());
    }

    @Test
    void testHandleSimpleGetReq_NoResponse_BadURI1() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/admins/", List.of(), List.of(), null);
        
        Object result = this.evaluator.visit(getReq, new PrintWriter(this.output));
        assertEquals(String.class, result.getClass());
        assertEquals("Request was not matched", result.toString());
    }

    @Test
    void testHandleGetReqWITH_Happy1() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of(), new JSONObject("{ \"id\" : 123, \"value\": \"abc-def-ghi\" }"));
        
        Object result = this.evaluator.visit(getReq, new PrintWriter(this.output));
        assertEquals(JSONObject.class, result.getClass());
        assertEquals(new JSONObject("{\"message\": \"Success\"}").toString(), result.toString());
    }

    @Test
    void testHandleSimpleDeleteReq_Happy1() {
        DelReq delReq = new DelReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null);

        Object result = this.evaluator.visit(delReq, new PrintWriter(this.output));
        assertEquals(JSONObject.class, result.getClass());
        assertEquals(new JSONObject("{\"message\": \"Task 123 deleted successfully\"}").toString(), result.toString());
    }
}
