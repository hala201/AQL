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

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class loopUnitTest {
    private Evaluator evaluator;
    private PrintWriter out;
    private List<Statement> statements;
    private GetReq iterable;
    private Loop loop;
    private Stack<Map<String, Integer>> localEnvironmentStack;

    @BeforeEach
    void setUp() {
        evaluator = new Evaluator();
        out = new PrintWriter(System.out);

        statements = new ArrayList<>();
        iterable = new GetReq(null, null, null, null);
        loop = new Loop(iterable, new Variable("user", new JSONObject()), new Program(statements));

        this.evaluator.getEnvironment().put("user", 1);
        this.evaluator.getMemory().put(1, new JSONObject("{\"id\":\"123\"}"));
        this.localEnvironmentStack = new Stack<>();
    }

    @Test
    void testLoopNonIterable() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/admins/", List.of("{user.id}"), List.of("/tasks"), null);
        loop.setIterable(getReq);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> evaluator.visit(loop, out));
        assertTrue(thrown.getMessage().contains("FOR EACH: Expected JSONObject or JSONArray as iterable data"));
    }

    @Test
    void testLoopSuccessfulOneIteration() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null);
        loop.setIterable(getReq);
        Statement s1 = new Statement(new GetReq("https://65y4r.wiremockapi.cloud/users/tasks2", List.of(), List.of(), null));
        statements.add(s1);
        loop.setLoopBody(new Program(statements));

        evaluator.visit(loop, out);
        assertEquals(loop.getLoopControlVariable().getVariableContent().toString(), new JSONObject("{\"task1\":\"haha\"}").toString());
    }

    @Test
    void testLoopSuccessfulManyIterations() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/tasks2", List.of(), List.of(), null);
        loop.setIterable(getReq);
        Statement s1 = new Statement(new GetReq("https://65y4r.wiremockapi.cloud/users/tasks2", List.of(), List.of(), null));
        statements.add(s1);
        loop.setLoopBody(new Program(statements));

        evaluator.visit(loop, out);
        assertEquals(loop.getLoopControlVariable().getVariableContent().toString(), new JSONObject("{\"task2\":\"haha\"}").toString());
    }

    @Test
    void testLoopEmptyBody() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null);
        loop.setIterable(getReq);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> evaluator.visit(loop, out));
        assertTrue(thrown.getMessage().contains("FOR EACH: Empty loop body"));
    }

    @Test
    void testEvaluateLoopBodySuccessfulCommands() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/tasks2", List.of(), List.of(), null);
        loop.setIterable(getReq);
        Statement s1 = new Statement(new GetReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null));
        Statement s2 = new Statement(new DelReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null));
        Statement s3 = new Statement(new PostReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null));
        Statement s4 = new Statement(new PostReq("https://65y4r.wiremockapi.cloud/users/", List.of("{user.id}"), List.of("/tasks"), null));

        statements.add(s1);
        statements.add(s2);
        statements.add(s3);
        statements.add(s4);

        loop.setLoopBody(new Program(statements));

        evaluator.visit(loop, out);
        assertEquals(loop.getLoopControlVariable().getVariableContent().toString(), new JSONObject("{\"task2\":\"haha\"}").toString());
    }

    @Test
    void testEvaluateLoopBodyUnsuccessfulCommands() {
        GetReq getReq = new GetReq("https://65y4r.wiremockapi.cloud/users/tasks2", List.of(), List.of(), null);
        loop.setIterable(getReq);
        Statement s1 = new Statement(new GetReq("https://65y4r.wiremockapi.cloud/admins/", List.of(), List.of(), null));
        Statement s2 = new Statement(new DelReq(null, null, null, null));
        Statement s3 = new Statement(new PostReq(null, null, null, null));
        Statement s4 = new Statement(new PostReq(null, null, null, null));

        statements.add(s1);
        statements.add(s2);
        statements.add(s3);
        statements.add(s4);

        loop.setLoopBody(new Program(statements));

        assertThrows(NullPointerException.class, () -> evaluator.visit(loop, out));
    }
}
