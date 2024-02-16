package evaluator;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import ast.api.DelReq;
import ast.api.PostReq;
import ast.api.PutReq;
import ast.logic.Log;
import ast.logic.Set;
import helper.testData;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ast.api.GetReq;
import controller.Evaluator;

import static org.junit.jupiter.api.Assertions.*;

class SetLogTest {

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
    void testLogString() {
        Log log = new Log("log successfully");
        Object result = this.evaluator.visit(log, new PrintWriter(System.out, true));
        assertEquals("log successfully", result);
    }

    @Test
    void testLogNumber() {
        Log log = new Log(123);
        Object result = this.evaluator.visit(log, new PrintWriter(System.out, true));
        assertEquals("123", result);
    }

    @Test
    void testLogVariableString(){
        Set set = new Set("abc", "string");
        Log log = new Log(set.getRightValue());
        Object result = this.evaluator.visit(log, new PrintWriter(System.out, true));
        assertEquals("abc", result);
    }

    @Test
    void testLogVariableNumber(){
        Set set = new Set(123, "number");
        Log log = new Log(set.getRightValue());
        Object result = this.evaluator.visit(log, new PrintWriter(System.out, true));
        assertEquals("123", result);
    }

    @Test
    void testLogDynamicVariable(){
        Set set = new Set(testData.SimpleClass.getA(), "stringVariable");
        Log log = new Log(set.getRightValue());
        Object result = this.evaluator.visit(set, new PrintWriter(System.out, true));
        assertEquals("hello", result);
    }

    @Test
    void testSetNumber() {
        Set set = new Set(123, "numberVar");
        Object result = this.evaluator.visit(set, new PrintWriter(System.out, true));
        assertEquals(123, result);
    }

    @Test
    void testSetString() {
        Set set = new Set("abc", "stringVar");
        Object result = this.evaluator.visit(set, new PrintWriter(System.out, true));
        assertEquals("abc", result);
    }

    @Test
    void testSetReq_Happy1() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of(), new JSONObject("{ \"id\" : 123, \"value\": \"abc-def-ghi\" }"));
        Set set = new Set(getReq, "getReq");
        Object result = this.evaluator.visit(set, new PrintWriter(System.out, true));
        assertEquals(JSONObject.class, result.getClass());
        System.out.println("Env" + this.evaluator.getEnvironment());
        assertEquals(new JSONObject("{\"message\": \"Success\"}").toString(), result.toString());
    }

    @Test
    void testSetSimpleReq_GoodGet() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null);
        Set set = new Set(getReq, "getReq");
        Object result = this.evaluator.visit(set, new PrintWriter(this.output));
        assertEquals(JSONObject.class, result.getClass());
        assertEquals(new JSONObject("{\"task1\":\"haha\"}").toString(), result.toString());
    }

    @Test
    void testHandleSimpleReq_GoodDel() {
        DelReq delReq = new DelReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null);
        Set set = new Set(delReq, "delReq");
        Object result = this.evaluator.visit(set, new PrintWriter(this.output));
        assertEquals(JSONObject.class, result.getClass());
        assertEquals(new JSONObject("{\"message\": \"Task 123 deleted successfully\"}").toString(), result.toString());
    }

    @Test
    void testHandleSimpleReq_GoodPut() {
        PutReq putReq = new PutReq("https://65y4r.wiremockapi.cloud/users", List.of(), List.of(), new JSONObject("{\"task1\": \"Breakfast\"}"));
        Set set = new Set(putReq, "putReq");
        Object result = this.evaluator.visit(set, new PrintWriter(this.output));
        assertEquals(JSONObject.class, result.getClass());
        assertEquals(new JSONObject("{\"message\": \"Success\"}").toString(), result.toString());
    }

    @Test
    void testHandleSimpleReq_GoodPost() {
        PostReq postReq = new PostReq("https://65y4r.wiremockapi.cloud/users", List.of(), List.of(), new JSONObject("{\"task1\": \"Breakfast\"}"));
        Set set = new Set(postReq, "postReq");
        Object result = this.evaluator.visit(set, new PrintWriter(this.output));
        assertEquals(JSONObject.class, result.getClass());
        assertEquals(new JSONObject("{\"message\": \"Success\"}").toString(), result.toString());
    }

    @Test
    void testHandleSimpleReq_ValidBodyTail_BadURI() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/???/", List.of("{user.id}"), List.of("/tasks"), null);
        Set set = new Set(getReq, "getReq");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            this.evaluator.visit(set, new PrintWriter(this.output));
        });
        assertTrue(exception.getMessage().contains("Error: HTTP 404"));
    }

    @Test
    void testHandleSimpleReq_NoExist_BadURI2() {
        GetReq getReq = new GetReq("https://doesntexist12314241.com", List.of(), List.of(), null);
        Set set = new Set(getReq, "getReq");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            this.evaluator.visit(set, new PrintWriter(this.output));
        });
        assertTrue(exception.getMessage().contains("API call failed: I/O error - null"));
    }

    @Test
    void testHandleReq_DynamicVariableNotSet() {
        GetReq getReq = new GetReq("https://example.com/users/", List.of("{nonexistent.id}"), List.of("/tasks"), null);
        Set set = new Set(getReq, "getReq");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            this.evaluator.visit(set, new PrintWriter(this.output));
        });
        assertTrue(exception.getMessage().contains("Variable `nonexistent` was not SET properly."));
    }

    @Test
    void testHandleReq_PropertyNotFoundInMemory() {
        GetReq getReq = new GetReq("https://example.com/users/", List.of("{user.nonexistentProperty}"), List.of("/tasks"), null);
        Set set = new Set(getReq, "getReq");
        Exception exception = assertThrows(RuntimeException.class, () -> {
            this.evaluator.visit(set, new PrintWriter(this.output));
        });
        assertTrue(exception.getMessage().contains("Property `nonexistentProperty` not found for variable `user`"));
    }

    @Test
    void testHandleReq_VariableNotInMemory() {
        GetReq getReq = new GetReq("https://example.com/users/", List.of("{user.id}"), List.of("/tasks"), null);
        Set set = new Set(getReq, "user");
        this.evaluator.getMemory().remove(1); // Simulate the variable not being set

        Exception exception = assertThrows(RuntimeException.class, () -> {
            this.evaluator.visit(set, new PrintWriter(this.output));
        });
        assertTrue(exception.getMessage().contains("Variable `user` was not SET properly. Variable is not in memory."));
    }


}