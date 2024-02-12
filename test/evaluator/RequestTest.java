package evaluator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ast.api.DelReq;
import ast.api.GetReq;
import ast.api.PostReq;
import ast.api.PutReq;
import controller.Evaluator;

public class RequestTest {
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
    void testHandleSimpleReq_GoodGet() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null);
        
        Object result = this.evaluator.visit(getReq, new PrintWriter(this.output));
        assertEquals(JSONObject.class, result.getClass());
        assertEquals(new JSONObject("{\"task1\":\"haha\"}").toString(), result.toString());
    }

    @Test
    void testHandleSimpleReq_GoodDel() {
        DelReq delReq = new DelReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null);

        Object result = this.evaluator.visit(delReq, new PrintWriter(this.output));
        assertEquals(JSONObject.class, result.getClass());
        assertEquals(new JSONObject("{\"message\": \"Task 123 deleted successfully\"}").toString(), result.toString());
    }

    @Test
    void testHandleSimpleReq_GoodPut() {
        PutReq putReq = new PutReq("https://65y4r.wiremockapi.cloud/users", List.of(), List.of(), new JSONObject("{\"task1\": \"Breakfast\"}"));

        Object result = this.evaluator.visit(putReq, new PrintWriter(this.output));
        assertEquals(JSONObject.class, result.getClass());
        assertEquals(new JSONObject("{\"message\": \"Success\"}").toString(), result.toString());
    }

    @Test
    void testHandleSimpleReq_GoodPost() {
        PostReq postReq = new PostReq("https://65y4r.wiremockapi.cloud/users", List.of(), List.of(), new JSONObject("{\"task1\": \"Breakfast\"}"));

        Object result = this.evaluator.visit(postReq, new PrintWriter(this.output));
        assertEquals(JSONObject.class, result.getClass());
        assertEquals(new JSONObject("{\"message\": \"Success\"}").toString(), result.toString());
    }

    @Test
    void testHandleSimpleReq_ValidBodyTail_BadURI() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/???/", List.of("{user.id}"), List.of("/tasks"), null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            this.evaluator.visit(getReq, new PrintWriter(this.output));
        });
        assertTrue(exception.getMessage().contains("Error: HTTP 404"));
    }

    @Test
    void testHandleSimpleReq_NoExist_BadURI2() {
        GetReq getReq = new GetReq("https://doesntexist12314241.com", List.of(), List.of(), null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            this.evaluator.visit(getReq, new PrintWriter(this.output));
        });
        assertTrue(exception.getMessage().contains("API call failed: I/O error - null"));
    }

    @Test
    void testHandleReq_DynamicVariableNotSet() {
        GetReq getReq = new GetReq("https://example.com/users/", List.of("{nonexistent.id}"), List.of("/tasks"), null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            this.evaluator.visit(getReq, new PrintWriter(this.output));
        });
        assertTrue(exception.getMessage().contains("Variable `nonexistent` was not SET properly."));
    }

    @Test
    void testHandleReq_PropertyNotFoundInMemory() {
        GetReq getReq = new GetReq("https://example.com/users/", List.of("{user.nonexistentProperty}"), List.of("/tasks"), null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            this.evaluator.visit(getReq, new PrintWriter(this.output));
        });
        assertTrue(exception.getMessage().contains("Property `nonexistentProperty` not found for variable `user`"));
    }

    @Test
    void testHandleReq_VariableNotInMemory() {
        GetReq getReq = new GetReq("https://example.com/users/", List.of("{user.id}"), List.of("/tasks"), null);
        this.evaluator.getMemory().remove(1); // Simulate the variable not being set

        Exception exception = assertThrows(RuntimeException.class, () -> {
            this.evaluator.visit(getReq, new PrintWriter(this.output));
        });
        assertTrue(exception.getMessage().contains("Variable `user` was not SET properly. Variable is not in memory."));
    }

    @Test
    void testHandleReqWITH_Happy1() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of(), new JSONObject("{ \"id\" : 123, \"value\": \"abc-def-ghi\" }"));
        
        Object result = this.evaluator.visit(getReq, new PrintWriter(this.output));
        assertEquals(JSONObject.class, result.getClass());
        assertEquals(new JSONObject("{\"message\": \"Success\"}").toString(), result.toString());
    }

    @Test
    void testUnreachableURL() {
        GetReq getReq = new GetReq("https://vajnsfkanfajks", List.of(), List.of(), null);
        Exception exception = assertThrows(RuntimeException.class, () -> this.evaluator.visit(getReq, new PrintWriter(this.output)));
        // assertEquals("getReq", exception.getMessage());
        assertTrue(exception.getMessage().contains("API call failed: I/O error"));
    }

    @Test
    void testNotFoundResponse() {
        GetReq getReq = new GetReq("https://httpstat.us/404", List.of(), List.of(), null);
        Exception exception = assertThrows(RuntimeException.class, () -> this.evaluator.visit(getReq, new PrintWriter(this.output)));
        assertTrue(exception.getMessage().contains("Error: HTTP 404"));
    }

    @Test
    void testNotJSON() {
        GetReq getReq = new GetReq("https://locahost.com", List.of(), List.of(), null);
        Exception exception = assertThrows(RuntimeException.class, () -> this.evaluator.visit(getReq, new PrintWriter(this.output)));
        assertTrue(exception.getMessage().contains("Language only supports Response with a JSON format"));
    }

    @Test
    void testInternalServerErrorResponse() {
        GetReq getReq = new GetReq("https://httpstat.us/500", List.of(), List.of(), null);
        Exception exception = assertThrows(RuntimeException.class, () -> this.evaluator.visit(getReq, new PrintWriter(this.output)));
        assertTrue(exception.getMessage().contains("Error: HTTP 500"));
    }

}
