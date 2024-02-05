import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ast.api.DelReq;
import ast.api.GetReq;
import controller.AQLVisitor;
import gen.AQLLexer;
import gen.AQLParser;

class AQLVisitorTest {
    private AQLVisitor visitor;

    @BeforeEach
    void setUp() {
        this.visitor = new AQLVisitor();
    }

    private AQLParser getParserForInput(String input) {
        AQLLexer lexer = new AQLLexer(CharStreams.fromString(input));
        TokenStream tokens = new CommonTokenStream(lexer);
        return new AQLParser(tokens);
    }

    @Test
    void testVisitGetReq_ParsesCorrectly() {
        String input = "GET https://api.example.com/users/{user.id}";
        AQLParser parser = this.getParserForInput(input);
        GetReq result = (GetReq) parser.getReq().accept(this.visitor);

        assertNotNull(result, "The result should not be null.");
        assertEquals("https://api.example.com/users/", result.getHead(), "The head of the URI should be correctly parsed.");
        assertTrue(result.getBody().contains("{user.id}"), "The body should contain the user id placeholder.");
    }

    @Test
    void testVisitDelReq_ParsesCorrectly() {
        String input = "DELETE https://api.example.com/users/{user.id}";
        AQLParser parser = this.getParserForInput(input);
        DelReq result = (DelReq) parser.delReq().accept(this.visitor);

        assertNotNull(result, "The result should not be null.");
        assertEquals("https://api.example.com/users/", result.getHead(), "The head of the URI should be correctly parsed.");
        assertTrue(result.getBody().contains("{user.id}"), "The body should contain the user id placeholder.");
    }

    @Test
    void testVisitGetReqWithParams() {
        String input = "GET https://api.example.com/users/{user.id} WITH { \"id\": 123, \"value\": \"abc-def-ghi\" }";
        AQLParser parser = this.getParserForInput(input);
        GetReq result = (GetReq) parser.getReq().accept(this.visitor);

        assertNotNull(result, "The result should not be null.");
        assertEquals("https://api.example.com/users/", result.getHead(), "The head of the URI should be correctly parsed.");
        assertNotNull(result.getParams(), "Params should not be null.");
        assertEquals(123, result.getParams().getInt("id"), "The id parameter should be correctly parsed.");
        assertEquals("abc-def-ghi", result.getParams().getString("value"), "The value parameter should be correctly parsed.");
    }

    @Test
    void testVisitDelReqWithParams() {
        String input = "DELETE https://api.example.com/users/{user.id} WITH { \"id\": 456, \"value\": \"xyz-abc\" }";
        AQLParser parser = this.getParserForInput(input);
        DelReq result = (DelReq) parser.delReq().accept(this.visitor);

        assertNotNull(result, "The result should not be null.");
        assertEquals("https://api.example.com/users/", result.getHead(), "The head of the URI should be correctly parsed.");
        assertNotNull(result.getParams(), "Params should not be null.");
        assertEquals(456, result.getParams().getInt("id"), "The id parameter should be correctly parsed.");
        assertEquals("xyz-abc", result.getParams().getString("value"), "The value parameter should be correctly parsed.");
    }

    @Test
    void testVisitWithBlockMissingOpeningBrace() {
        String input = "GET https://api.example.com/users/{user.id} WITH \"id\":123, \"value\":\"abc\" }";
        AQLParser parser = this.getParserForInput(input);

        IllegalStateException thrown = assertThrows(IllegalStateException.class, 
            () -> parser.getReq().accept(this.visitor),
            "Expected to throw due to missing opening brace in WITH block.");
        assertTrue(thrown.getMessage().contains("missing {"), "Exception message should indicate missing opening brace.");
    }

    @Test
    void testVisitWithBlockMissingClosingBrace() {
        String input = "GET https://api.example.com/users/{user.id} WITH { \"id\":123, \"value\":\"abc\"";
        AQLParser parser = this.getParserForInput(input);

        IllegalStateException thrown = assertThrows(IllegalStateException.class, 
            () -> parser.getReq().accept(this.visitor),
            "Expected to throw due to missing closing brace in WITH block.");
        assertTrue(thrown.getMessage().contains("missing }"), "Exception message should indicate missing closing brace.");
    }

    @Test
    void testVisitWithBlockInvalidParams() {
        String input = "GET https://api.example.com/users/{user.id} WITH { : }";
        AQLParser parser = this.getParserForInput(input);

        IllegalStateException thrown = assertThrows(IllegalStateException.class, 
            () -> parser.getReq().accept(this.visitor),
            "Expected to throw due to invalid parameters in WITH block.");
        assertTrue(thrown.getMessage().contains("Invalid parameters"), "Exception message should indicate invalid parameters.");
    }

    @Test
    void testVisitWithBlockEmptyParams() {
        String input = "GET https://api.example.com/users/{user.id} WITH { }";
        AQLParser parser = this.getParserForInput(input);

        IllegalStateException thrown = assertThrows(IllegalStateException.class, 
            () -> parser.getReq().accept(this.visitor),
            "Expected to throw due to empty parameters in WITH block.");
        assertTrue(thrown.getMessage().contains("Invalid parameters"), "Exception message should indicate empty parameters.");
    }

    // TBD: add more static checks for expected fails
}
