package visitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ast.api.Request;
import ast.logic.Log;
import ast.logic.Set;
import controller.AQLVisitor;
import gen.AQLLexer;
import gen.AQLParser;

public class SetLogTest {
    private AQLVisitor visitor;

    @BeforeEach
    void setUp() {
        this.visitor = new AQLVisitor();
    }

    private AQLParser getParseInputFor(String input) {
        AQLLexer lexer = new AQLLexer(CharStreams.fromString(input));
        TokenStream tokens = new CommonTokenStream(lexer);
        return new AQLParser(tokens);
    }

    @Test
    void testLogString() {
        String input = "LOG \"Hello World\"";
        AQLParser parser = this.getParseInputFor(input);
        Log result = (Log) parser.log().accept(this.visitor);
        assertEquals("Hello World", result.getMsgObject());
    }

    @Test
    void testLogNumberNoDecimal() {
        String input = "LOG 123";
        AQLParser parser = this.getParseInputFor(input);
        Log result = (Log) parser.log().accept(this.visitor);

        Object msgObject = result.getMsgObject();
        if (msgObject instanceof Integer) {
            assertEquals(123, msgObject); 
        } else if (msgObject instanceof Double) {
            fail("The message object shouldnt be Double.");
        } else {
            fail("The message object is neither an Integer nor a Double.");
        }
    }

    @Test
    void testLogNumberDouble() {
        String input = "LOG 123.123";
        AQLParser parser = this.getParseInputFor(input);
        Log result = (Log) parser.log().accept(this.visitor);

        Object msgObject = result.getMsgObject();
        if (msgObject instanceof Integer) {
            fail("The message object shouldnt be Integer.");
        } else if (msgObject instanceof Double) {
            assertEquals(123.123, (Double) msgObject, 0.0);
        } else {
            fail("The message object is neither an Integer nor a Double.");
        }
    }

    @Test
    void testSetString() {
        String input = "SET \"Hello\" AS myVar";
        AQLParser parser = this.getParseInputFor(input);
        Set set = (Set) parser.set().accept(this.visitor);
        assertNotNull(set);
        assertEquals(set.getKey(), "myVar");
        assertEquals(set.getVal(), "Hello");
    }

    @Test
    void testSetNumberInteger() {
        String input = "SET 123 AS myVar";
        AQLParser parser = this.getParseInputFor(input);
        Set set = (Set) parser.set().accept(this.visitor);
        assertNotNull(set);
        assertEquals(set.getKey(), "myVar");
        assertEquals(set.getVal(), 123);
    }

    @Test
    void testSetNumberDouble() {
        String input = "SET 123.123 AS myVar";
        AQLParser parser = this.getParseInputFor(input);
        Set set = (Set) parser.set().accept(this.visitor);
        assertNotNull(set);
        assertEquals(set.getKey(), "myVar");
        assertEquals(set.getVal(), 123.123);
    }

    @Test
    void testSetRequest() {
        String input = "SET GET https://aqlserver.onrender.com/users AS myVar";
        AQLParser parser = this.getParseInputFor(input);
        Set set = (Set) parser.set().accept(this.visitor);
        assertNotNull(set);
        assertEquals(set.getKey(), "myVar");
        assertTrue(set.getVal() instanceof Request);
    }

}
