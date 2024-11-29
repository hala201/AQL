package evaluator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ast.api.GetReq;
import ast.api.Request;
import ast.logic.Log;
import ast.logic.Set;
import ast.loop.Variable;
import controller.Evaluator;

class SetLogTest {

    private Evaluator evaluator;
    private StringWriter output;

    private final JSONObject User1InAQLServer = new JSONObject("""
    {
        "_id": "65cd34f301b10b181cec335b",
        "name": "John Doe",
        "email": "john.doe@example.com",
        "__v": 0
    }
            """);

    @BeforeEach
    void setUp() {
        this.evaluator = new Evaluator();
        this.output = new StringWriter();

        this.evaluator.getEnvironment().put("userJSON", 1);
        this.evaluator.getMemory().put(1, new JSONObject("{\"id\":\"123\"}"));
        this.evaluator.getEnvironment().put("userNumber", 2);
        this.evaluator.getMemory().put(2, 123);
        this.evaluator.getEnvironment().put("userString", 3);
        this.evaluator.getMemory().put(3, "ben 10");
    }

    @Test
    void logStringLiteral() {
        Log log = new Log("Log successfully executed");
        this.evaluator.visit(log, new PrintWriter(this.output));
        assertEquals("Log successfully executed", this.output.toString().trim());
    }

    @Test
    void logNumberLiteral() {
        Log log = new Log(123);
        this.evaluator.visit(log, new PrintWriter(this.output));
        assertEquals("123", this.output.toString().trim());
    }

    @Test
    void logJSONFromVariable() {
        Log log = new Log(new Variable("userJSON", null));
        this.evaluator.visit(log, new PrintWriter(this.output));
        assertEquals("{\"id\":\"123\"}", this.output.toString().trim());  
    }

    @Test
    void logDvarFromVariable() {
        Log log = new Log("userJSON.id");
        this.evaluator.visit(log, new PrintWriter(this.output));
        assertEquals("123", this.output.toString().trim());  
    }
        
    @Test
    void logNumberFromVariable() {
        Log log = new Log(new Variable("userNumber", null));
        this.evaluator.visit(log, new PrintWriter(this.output));
        assertEquals("123", this.output.toString().trim());  
    }

    @Test
    void logStringFromVariable() {
        Log log = new Log(new Variable("userString", null));
        this.evaluator.visit(log, new PrintWriter(this.output));
        assertEquals("ben 10", this.output.toString().trim());  
    }

    @Test
    void setNumberLiteral() {
        Set set = new Set("numberVar", 123);
        this.evaluator.visit(set, new PrintWriter(this.output));
        Integer memptr = this.evaluator.getEnvironment().get("numberVar");
        assertEquals(0, memptr);
        assertEquals(123, this.evaluator.getMemory().get(memptr));
    }

    @Test
    void setStringLiteral() {
        Set set = new Set("stringVar", "asjkf aknak jfnakjf a xD");
        this.evaluator.visit(set, new PrintWriter(this.output));
        Integer memptr = this.evaluator.getEnvironment().get("stringVar");
        assertEquals(0, memptr);
        assertEquals("asjkf aknak jfnakjf a xD", this.evaluator.getMemory().get(memptr));
    }

    @Test
    void fetchAndSetDataFromGetRequest() {
        Request getReq = new Request(new GetReq("https://aqlserver.onrender.com/users", List.of(), List.of(), null));
        Set set = new Set("response", getReq);
        this.evaluator.visit(set, new PrintWriter(this.output));
        Object result = this.evaluator.getMemory().get(this.evaluator.getEnvironment().get("response"));
        assertTrue(result instanceof JSONArray);
        assertTrue(((JSONArray) result).toString().contains(this.User1InAQLServer.toString()));
    }

}   
