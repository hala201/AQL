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
import ast.api.Params;
import ast.api.PostReq;
import ast.api.PutReq;
import controller.Evaluator;

public class RequestTest {
    private Evaluator evaluator;
    private StringWriter output;
    private final String baseURI     = "https://aqlserver.onrender.com/";
    private final String usersURI    = this.baseURI + "users/";
    private final String tasksURI    = this.baseURI + "tasks/";
    private final String testURI     = this.baseURI + "utils/";
    private final JSONObject userResponse = new JSONObject("""
    {"_id":"65cd34f301b10b181cec335b","name":"John Doe","email":"john.doe@example.com","__v":0}
            """);
    private final JSONObject taskResponse = new JSONObject("""
    {"title":"Breakfast","description":"Eat breakfast","completed":false,"user":"65cd34f301b10b181cec335b"}
            """);

    @BeforeEach
    void setUp() {
        this.evaluator = new Evaluator();
        this.output = new StringWriter();

        this.evaluator.getEnvironment().put("user", 1);
        this.evaluator.getMemory().put(1, new JSONObject("{\"id\":\"65cd34f301b10b181cec335b\"}"));
        this.evaluator.getEnvironment().put("keys", 2);
        this.evaluator.getMemory().put(2, new JSONObject("{\"key1\":\"getone\",\"key2\":\"gettwo\",\"key3\":\"getnonjson\"}"));
    }

    @Test
    void testHandleSimpleReq_GoodGet() {
        GetReq getReq = new GetReq(this.usersURI, List.of("{user.id}"), List.of(), null);
        
        Object result = this.evaluator.visit(getReq, new PrintWriter(this.output));
        assertEquals(JSONObject.class, result.getClass());
        assertEquals(this.userResponse.toString(), result.toString());
    }

    @Test
    void testHandleSimpleReq_GoodPost_usingWITH() {
        PostReq postReq = new PostReq(this.tasksURI, List.of(), List.of(), new Params(new JSONObject("{\"userId\": \"65cd34f301b10b181cec335b\", \"title\": \"Breakfast\" , \"description\": \"Eat breakfast\"}")));

        Object result = this.evaluator.visit(postReq, new PrintWriter(this.output));
        assertEquals(JSONObject.class, result.getClass());
        assertTrue(result.toString().contains("\"title\":\"Breakfast\""));
        assertTrue(result.toString().contains("\"user\":\"65cd34f301b10b181cec335b\""));
        assertTrue(result.toString().contains("\"description\":\"Eat breakfast\""));
    }

    @Test
    void testHandleSimpleReq_GoodPut() {
        // create a new task before editing it, to make sure the task actually exists
        PostReq postReq = new PostReq(this.tasksURI, List.of(), List.of(), new Params(new JSONObject("{\"userId\": \"65cd34f301b10b181cec335b\", \"title\": \"Breakfast\" , \"description\": \"Eat breakfast\"}")));
        Object createdTask = this.evaluator.visit(postReq, new PrintWriter(this.output));

        JSONObject requestBody = new JSONObject();
        requestBody.put("title", "Breakfast2");

        PutReq putReq = new PutReq(this.tasksURI + "/" + ((JSONObject) createdTask).getString("_id"), List.of(), List.of(), new Params(requestBody));

        Object result = this.evaluator.visit(putReq, new PrintWriter(this.output));
        assertEquals(JSONObject.class, result.getClass());
        assertTrue(result.toString().contains("\"title\":\"Breakfast2\""));
        assertTrue(result.toString().contains("\"user\":\"65cd34f301b10b181cec335b\""));
        assertTrue(result.toString().contains("\"description\":\"Eat breakfast\""));
    }

    void testHandleSimpleReq_GoodDel() {
        // create a new task before deleting it, to make sure the task actually exists
        PostReq postReq = new PostReq(this.tasksURI, List.of(), List.of(), new Params(new JSONObject("{\"userId\": \"65cd34f301b10b181cec335b\", \"title\": \"Breakfast\" , \"description\": \"Eat breakfast\"}")));
        Object createdTask = this.evaluator.visit(postReq, new PrintWriter(this.output));

        DelReq delReq = new DelReq(this.tasksURI + "/" + ((JSONObject) createdTask).getString("_id"), List.of(), List.of(), null);

        Object result = this.evaluator.visit(delReq, new PrintWriter(this.output));
        assertEquals(JSONObject.class, result.getClass());
        assertEquals(result.toString(), new JSONObject("{ \"message\": \"Task deleted\" }"));
    
    }

    @Test
    void testHandleSimpleReq_ValidBodyTail_BadURI() {
        GetReq getReq = new GetReq(this.baseURI + "????", List.of("{user.id}"), List.of("/tasks"), null);

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
