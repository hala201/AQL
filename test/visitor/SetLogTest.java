package visitor;

import ast.logic.Log;
import ast.logic.Set;
import controller.AQLVisitor;
import controller.IRequest;
import gen.AQLLexer;
import gen.AQLParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        String input = "LOG 'Hello World'";
        AQLParser parser = this.getParseInputFor(input);
        Log result = (Log) parser.log().accept(this.visitor);
        assertEquals("'Hello World'", result.getMessage());
    }

    @Test
    void testLogNumber() {
        String input = "LOG 123";
        AQLParser parser = this.getParseInputFor(input);
        Log result = (Log) parser.log().accept(this.visitor);
        assertEquals("123", result.getMessage());
    }

    @Test
    void testLogVariableAfterSet() {
        String setInput = "SET 123 AS myVar";
        AQLParser setParser = this.getParseInputFor(setInput);
        setParser.set().accept(this.visitor);

        String logInput = "LOG myVar";
        AQLParser logParser = this.getParseInputFor(logInput);
        Log result = (Log) logParser.log().accept(this.visitor);
        assertEquals("123", result.getMessage());
    }

    @Test
    void testSetString() {
        String input = "SET 'Hello' AS myVar";
        AQLParser parser = this.getParseInputFor(input);
        Set set = (Set) parser.set().accept(this.visitor);
        assertNotNull(set);
        assertEquals(set.getVariableName(), "myVar");
        assertEquals(set.getRightValue(), "'Hello'");
    }

    @Test
    void testSetNumber() {
        String input = "SET 123 AS myVar";
        AQLParser parser = this.getParseInputFor(input);
        Set set = (Set) parser.set().accept(this.visitor);
        assertNotNull(set);
        assertEquals(set.getVariableName(), "myVar");
        assertEquals(set.getRightValue(), "123");
    }

    @Test
    void testSetRequest(){
        String input = "SET GET https://65y4r.wiremockapi.cloud/users/ AS myVar";
        AQLParser parser = this.getParseInputFor(input);
        Set set = (Set) parser.set().accept(this.visitor);
        assertNotNull(set);
        assertTrue(set.getRequest() instanceof IRequest);
    }
}
