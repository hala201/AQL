package evaluator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ast.Program;
import ast.Statement;
import ast.api.DelReq;
import ast.api.GetReq;
import ast.api.PostReq;
import ast.loop.Loop;
import ast.loop.Variable;
import controller.Evaluator;

public class LoopTest {
    private Evaluator evaluator;
    private PrintWriter out;
    private StringWriter outString;
    private List<Statement> statements;
    private GetReq iterable;
    private Loop loop;
    private Stack<Map<String, Integer>> localEnvironmentStack;
    private final String baseURI     = "https://aqlserver.onrender.com/";
    private final String usersURI    = this.baseURI + "users/";
    private final String testURI     = this.baseURI + "utils/";
    private final JSONObject userResponse = new JSONObject("""
    {"_id":"65cd34f301b10b181cec335b","name":"John Doe","email":"john.doe@example.com","__v":0}
            """);

    @BeforeEach
    void setUp() {
        this.evaluator = new Evaluator();
        this.out = new PrintWriter(System.out);
        this.outString = new StringWriter();

        this.statements = new ArrayList<>();
        this.iterable = new GetReq(null, null, null, null);
        this.loop = new Loop(this.iterable, new Variable("user", new JSONObject()), new Program(this.statements));

        this.evaluator.getEnvironment().put("user", 1);
        this.evaluator.getMemory().put(1, new JSONObject("{\"id\":\"65cd34f301b10b181cec335b\"}"));
        this.evaluator.getEnvironment().put("keys", 2);
        this.evaluator.getMemory().put(2, new JSONObject("{\"key1\":\"getone\",\"key2\":\"gettwo\",\"key3\":\"getnonjson\"}"));
        this.localEnvironmentStack = new Stack<>();
    }

    @Test
    void testLoopBadResponse() {
        GetReq getReq = new GetReq(this.baseURI + "admin", List.of("{user.id}"), List.of("/tasks"), null);
        this.loop.setIterable(getReq);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> this.evaluator.visit(this.loop, this.out));
        assertTrue(thrown.getMessage().contains("Request Error: API call failed: Error: HTTP 404"));
    }

    @Test
    void testLoopSuccessfulOneIteration() {
        GetReq getReq = new GetReq(this.testURI, List.of("{keys.key1}"), List.of(), null);
        this.loop.setIterable(getReq);
        Statement s1 = new Statement(getReq);
        this.statements.add(s1);
        this.loop.setLoopBody(new Program(this.statements));

        this.evaluator.visit(this.loop, this.out);
        assertEquals(this.loop.getLoopControlVariable().getVariableContent().toString(), new JSONObject("{\"message\":\"got one\"}").toString());
    }

    @Test
    void testLoopSuccessfulManyIterations() {
        GetReq getReq = new GetReq(this.testURI, List.of("{keys.key2}"), List.of(), null);
        this.loop.setIterable(getReq);
        Statement s1 = new Statement(getReq);
        this.statements.add(s1);
        this.loop.setLoopBody(new Program(this.statements));

        JSONArray expected = new JSONArray();
        expected.put(new JSONObject("{\"message2\":\"got two\"}"));
        expected.put(new JSONObject("{\"message1\":\"got one\"}"));

        this.evaluator.visit(this.loop, this.out);
        assertTrue(expected.toString().contains(this.loop.getLoopControlVariable().getVariableContent().toString()));
    }

    @Test
    void testLoopEmptyBody() {
        GetReq getReq = new GetReq(this.testURI, List.of("{keys.key2}"), List.of(), null);
        this.loop.setIterable(getReq);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> this.evaluator.visit(this.loop, this.out));
        assertTrue(thrown.getMessage().contains("FOR EACH: Empty loop body"));
    }

    @Test
    void testEvaluateLoopBodySuccessfulCommands() {
        GetReq getReq = new GetReq(this.testURI, List.of("{keys.key2}"), List.of(), null);
        this.loop.setIterable(getReq);
        Statement s1 = new Statement(new GetReq(this.testURI, List.of("{keys.key1}"), List.of(), null));
        Statement s2 = new Statement(new GetReq(this.testURI, List.of("{keys.key1}"), List.of(), null));
        Statement s3 = new Statement(new GetReq(this.testURI, List.of("{keys.key1}"), List.of(), null));
        Statement s4 = new Statement(new GetReq(this.testURI, List.of("{keys.key1}"), List.of(), null));

        this.statements.add(s1);
        this.statements.add(s2);
        this.statements.add(s3);
        this.statements.add(s4);

        this.loop.setLoopBody(new Program(this.statements));

        JSONArray expected = new JSONArray();
        expected.put(new JSONObject("{\"message2\":\"got two\"}"));
        expected.put(new JSONObject("{\"message1\":\"got one\"}"));

        this.evaluator.visit(this.loop, this.out);
        assertTrue(expected.toString().contains(this.loop.getLoopControlVariable().getVariableContent().toString()));
    }

    @Test
    void testEvaluateLoopBodyUnsuccessfulCommands() {
        GetReq getReq = new GetReq(this.testURI, List.of("{keys.key2}"), List.of(), null);
        this.loop.setIterable(getReq);
        Statement s1 = new Statement(new GetReq(this.baseURI+"/admins", List.of(), List.of(), null));
        Statement s2 = new Statement(new DelReq(null, null, null, null));
        Statement s3 = new Statement(new PostReq(null, null, null, null));
        Statement s4 = new Statement(new PostReq(null, null, null, null));

        this.statements.add(s1);
        this.statements.add(s2);
        this.statements.add(s3);
        this.statements.add(s4);

        this.loop.setLoopBody(new Program(this.statements));
        this.evaluator.visit(this.loop, new PrintWriter(this.outString));
        assertTrue(this.outString.toString().contains("Request Error: API call failed: Error: HTTP 404"));
    }
}
