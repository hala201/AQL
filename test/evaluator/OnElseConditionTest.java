package evaluator;

import static org.junit.jupiter.api.Assertions.assertFalse;
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

import ast.Statement;
import ast.condition.Condition;
import ast.condition.OnElse;
import ast.loop.Variable;
import controller.Evaluator;

class OnElseUnitConditionTest {

    private Evaluator evaluator;
    private PrintWriter out;
    private StringWriter outString;
    private List<Statement> statements;

    private OnElse oe;

    private Condition con;
    private Stack<Map<String, Integer>> localEnvironmentStack;


    @BeforeEach
    void setUp() {
        this.evaluator = new Evaluator();
        this.out = new PrintWriter(System.out, true);
        this.outString = new StringWriter();

        this.statements = new ArrayList<>();
        this.oe = new OnElse(null, null, null);
        this.con = new Condition(null, null, null);


        this.evaluator.getEnvironment().put("user", 1);
        this.evaluator.getMemory().put(1, new JSONObject("{\"id\":\"123\"}"));
        this.localEnvironmentStack = new Stack<>();
    }

    @Test
    void testBasicConditionString() {
        this.con = new Condition("String", "==", "String");
        Object result = this.evaluator.visit(this.con, this.out);

        assertTrue((boolean) result);
    }

    @Test
    void testBasicConditionNumber() {
        this.con = new Condition(2, "==", 2);
        Object result = this.evaluator.visit(this.con, this.out);

        assertTrue((boolean) result);
    }

    @Test
    void testBasicConditionNumber2() {
        this.con = new Condition(3, "==", 2);
        Object result = this.evaluator.visit(this.con, this.out);

        assertFalse((boolean) result);
    }

    @Test
    void testBasicConditionNumberDouble() {
        this.con = new Condition(2.123, "==", 2.123);
        Object result = this.evaluator.visit(this.con, this.out);

        assertTrue((boolean) result);
    }

    @Test
    void testBasicConditionNumberDouble2() {
        this.con = new Condition(3.123, "==", 2.3);
        Object result = this.evaluator.visit(this.con, this.out);

        assertFalse((boolean) result);
    }

    @Test
    void testBasicConditionVariable() {
        JSONObject ojb1 = new JSONObject();
        ojb1.put("two", 2);
        JSONObject ojb2 = new JSONObject();
        ojb2.put("two", 2);


        Variable v1 = new Variable("user1", null);
        Variable v2 = new Variable("user2", null);

        this.evaluator.getEnvironment().put("user1", 10000);
        this.evaluator.getMemory().put(10000, ojb1);
        this.evaluator.getEnvironment().put("user2", 10001);
        this.evaluator.getMemory().put(10001, ojb2);

        this.con = new Condition(v1, "==", v2);
        Object result = this.evaluator.visit(this.con, this.out);

        assertTrue((boolean) result);
    }

    @Test
    void testBasicConditionJSONArray() {
        boolean t = true;
        boolean f = false;

        JSONArray ja = new JSONArray();
        ja.put(t);
        ja.put(f);

        this.con = new Condition(ja, "==", ja);
        Object result = this.evaluator.visit(this.con, this.out);

        assertTrue((boolean) result);
    }

    @Test
    void testBasicConditionJSONArray2() {
        boolean t = true;
        boolean f = false;

        JSONArray ja = new JSONArray();
        ja.put(t);
        ja.put(f);

        JSONArray jb = new JSONArray();
        jb.put(t);
        jb.put(f);

        this.con = new Condition(ja, "==", jb);
        Object result = this.evaluator.visit(this.con, this.out);

        assertTrue((boolean) result);
    }

    @Test
    void testBasicConditionJSONArray3() {
        boolean t = true;
        boolean f = false;

        JSONArray ja = new JSONArray();
        ja.put(t);
        ja.put(f);

        JSONArray jb = new JSONArray();
        jb.put(t);
        jb.put(t);

        this.con = new Condition(ja, "!=", jb);
        Object result = this.evaluator.visit(this.con, this.out);

        assertTrue((boolean) result);
    }

    @Test
    void testBasicConditionJSONIDNumber() {
        JSONObject obj1 = new JSONObject();
        obj1.put("id", 2.0);

        this.con = new Condition(obj1.get("id"), ">", 1.0);
        Object result = this.evaluator.visit(this.con, this.out);

        assertTrue((boolean) result);
    }

    @Test
    void testConditionVarAndDvar() {
        JSONObject userData = new JSONObject("{\"name\":\"bob\", \"id\":123}");
        String dynamicVar = "{user1.name}";

        this.evaluator.getEnvironment().put("user1", 10000);
        this.evaluator.getMemory().put(10000, userData);
        this.evaluator.getEnvironment().put("user1Name", 10001);
        this.evaluator.getMemory().put(10001, "bob");

        Condition condition = new Condition(new Variable("user1Name", null), "==", dynamicVar);
        Boolean result = this.evaluator.visit(condition, new PrintWriter(this.outString));
        assertTrue(result, "Condition should evaluate to true");
    }

    @Test
    void testConditionVarAndDvar2() {
        JSONObject userData = new JSONObject("{\"name\":\"bob\", \"id\":123}");
        String dynamicVar = "{user1.name}";

        this.evaluator.getEnvironment().put("user1", 10000);
        this.evaluator.getMemory().put(10000, userData);
        this.evaluator.getEnvironment().put("user1Name", 10001);
        this.evaluator.getMemory().put(10001, "TOMMM");

        Condition condition = new Condition(new Variable("user1Name", null), "==", dynamicVar);
        Boolean result = this.evaluator.visit(condition, new PrintWriter(this.outString));
        assertFalse(result, "Condition should not evaluate to true");
    }

    @Test
    void testConditionDvarAndDvar() {
        this.evaluator.getEnvironment().put("user1", 10000);
        this.evaluator.getMemory().put(10000, new JSONObject("{\"id\":\"123\"}"));

        this.evaluator.getEnvironment().put("user2", 10001);
        this.evaluator.getMemory().put(10001, new JSONObject("{\"id\":\"123\"}"));

        String dynamicVar1 = "{user1.id}";
        String dynamicVar2 = "{user2.id}";

        Condition condition = new Condition(dynamicVar1, "==", dynamicVar2);

        Boolean result = this.evaluator.visit(condition, new PrintWriter(this.outString));
        assertTrue(result, "Condition should evaluate to true for matching dynamic variable values");
    }

    @Test
    void testConditionDvarAndDvar2() {
        this.evaluator.getEnvironment().put("user1", 10000);
        this.evaluator.getMemory().put(10000, new JSONObject("{\"id\":\"1234\"}"));

        this.evaluator.getEnvironment().put("user2", 10001);
        this.evaluator.getMemory().put(10001, new JSONObject("{\"id\":\"123\"}"));

        String dynamicVar1 = "{user1.id}";
        String dynamicVar2 = "{user2.id}";

        Condition condition = new Condition(dynamicVar1, "==", dynamicVar2);

        Boolean result = this.evaluator.visit(condition, new PrintWriter(this.outString));
        assertFalse(result, "Condition should not evaluate to true for matching dynamic variable values");
    }

    @Test
    void testConditionVarAndVal() {
        this.evaluator.getEnvironment().put("age", 1000);
        this.evaluator.getMemory().put(1000, 30.0);

        Double literalValue = 30.0;

        Condition condition = new Condition(new Variable("age", null), "==", literalValue);

        Boolean result = this.evaluator.visit(condition, new PrintWriter(this.outString));
        assertTrue(result, "Condition should evaluate to true when variable value matches the literal value");
    }

    @Test
    void testConditionVarAndVal2() {
        this.evaluator.getEnvironment().put("age", 1000);
        this.evaluator.getMemory().put(1000, 31.0);

        Double literalValue = 30.0;

        Condition condition = new Condition(new Variable("age", null), "==", literalValue);

        Boolean result = this.evaluator.visit(condition, new PrintWriter(this.outString));
        assertFalse(result, "Condition should not evaluate to true when variable value matches the literal value");
    }

}