package evaluator;
import ast.Node;
import ast.Program;
import ast.Statement;
import ast.api.DelReq;
import ast.api.GetReq;
import ast.api.PostReq;
import ast.loop.Loop;
import ast.loop.Variable;

import controller.Evaluator;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class LoopTest {
    private Evaluator evaluator;
    private PrintWriter out;
    private StringWriter outString;
    private List<Statement> statements;
    private GetReq iterable;
    private Loop loop;
    private Stack<Map<String, Integer>> localEnvironmentStack;

    @BeforeEach
    void setUp() {
        this.evaluator = new Evaluator();
        this.out = new PrintWriter(System.out);
        this.outString = new StringWriter();

        this.statements = new ArrayList<>();
        this.iterable = new GetReq(null, null, null, null);
        this.loop = new Loop(this.iterable, new Variable("user", new JSONObject()), new Program(this.statements));

        this.evaluator.getEnvironment().put("user", 1);
        this.evaluator.getMemory().put(1, new JSONObject("{\"id\":\"123\"}"));
        this.localEnvironmentStack = new Stack<>();
    }

    @Test
    void testLoopBadResponse() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/admins/", List.of("{user.id}"), List.of("/tasks"), null);
        this.loop.setIterable(getReq);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> this.evaluator.visit(this.loop, this.out));
        assertTrue(thrown.getMessage().contains("Request Error: API call failed: Error: HTTP 404"));
    }

    @Test
    void testLoopSuccessfulOneIteration() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null);
        this.loop.setIterable(getReq);
        Statement s1 = new Statement(new GetReq("https://65y4r.wiremockapi.cloud/users/tasks2", List.of(), List.of(), null));
        this.statements.add(s1);
        this.loop.setLoopBody(new Program(this.statements));

        this.evaluator.visit(this.loop, this.out);
        assertEquals(this.loop.getLoopControlVariable().getVariableContent().toString(), new JSONObject("{\"task1\":\"haha\"}").toString());
    }

    @Test
    void testLoopSuccessfulManyIterations() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/tasks2", List.of(), List.of(), null);
        this.loop.setIterable(getReq);
        Statement s1 = new Statement(new GetReq("https://65y4r.wiremockapi.cloud/users/tasks2", List.of(), List.of(), null));
        this.statements.add(s1);
        this.loop.setLoopBody(new Program(this.statements));

        this.evaluator.visit(this.loop, this.out);
        assertEquals(this.loop.getLoopControlVariable().getVariableContent().toString(), new JSONObject("{\"task2\":\"haha\"}").toString());
    }

    @Test
    void testLoopEmptyBody() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null);
        this.loop.setIterable(getReq);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> this.evaluator.visit(this.loop, this.out));
        assertTrue(thrown.getMessage().contains("FOR EACH: Empty loop body"));
    }

    @Test
    void testEvaluateLoopBodySuccessfulCommands() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/tasks2", List.of(), List.of(), null);
        this.loop.setIterable(getReq);
        Statement s1 = new Statement(new GetReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null));
        Statement s2 = new Statement(new DelReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null));
        Statement s3 = new Statement(new PostReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null));
        Statement s4 = new Statement(new PostReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null));

        this.statements.add(s1);
        this.statements.add(s2);
        this.statements.add(s3);
        this.statements.add(s4);

        this.loop.setLoopBody(new Program(this.statements));

        this.evaluator.visit(this.loop, this.out);
        assertEquals(this.loop.getLoopControlVariable().getVariableContent().toString(), new JSONObject("{\"task2\":\"haha\"}").toString());
    }

    @Test
    void testEvaluateLoopBodyUnsuccessfulCommands() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/tasks2", List.of(), List.of(), null);
        this.loop.setIterable(getReq);
        Statement s1 = new Statement(new GetReq("https://65y4r.wiremockapi.cloud/admins/", List.of(), List.of(), null));
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
