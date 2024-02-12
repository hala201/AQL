package visitor;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.AQLVisitor;
import gen.AQLParser;
import helper.TestUtils;

public class WithTest {
    private AQLVisitor visitor;

    @BeforeEach
    void setUp() {
        this.visitor = new AQLVisitor();
    }

    @Test
    void testVisitWithBlockMissingOpeningBrace() {
        String input = "GET https://api.example.com/users/{user.id} WITH \"id\":123, \"value\":\"abc\" }";
        AQLParser parser = TestUtils.getParserForInput(input);

        IllegalStateException thrown = assertThrows(IllegalStateException.class, 
            () -> parser.getReq().accept(this.visitor),
            "Expected to throw due to missing opening brace in WITH block.");
        assertTrue(thrown.getMessage().contains("missing {"), "Exception message should indicate missing opening brace.");
    }

    @Test
    void testVisitWithBlockMissingClosingBrace() {
        String input = "GET https://api.example.com/users/{user.id} WITH { \"id\":123, \"value\":\"abc\"";
        AQLParser parser = TestUtils.getParserForInput(input);

        IllegalStateException thrown = assertThrows(IllegalStateException.class, 
            () -> parser.getReq().accept(this.visitor),
            "Expected to throw due to missing closing brace in WITH block.");
        assertTrue(thrown.getMessage().contains("missing }"), "Exception message should indicate missing closing brace.");
    }

    @Test
    void testVisitWithBlockInvalidParams() {
        String input = "GET https://api.example.com/users/{user.id} WITH { : }";
        AQLParser parser = TestUtils.getParserForInput(input);

        IllegalStateException thrown = assertThrows(IllegalStateException.class, 
            () -> parser.getReq().accept(this.visitor),
            "Expected to throw due to invalid parameters in WITH block.");
        assertTrue(thrown.getMessage().contains("Invalid parameters"), "Exception message should indicate invalid parameters.");
    }

    @Test
    void testVisitWithBlockEmptyParams() {
        String input = "GET https://api.example.com/users/{user.id} WITH { }";
        AQLParser parser = TestUtils.getParserForInput(input);

        IllegalStateException thrown = assertThrows(IllegalStateException.class, 
            () -> parser.getReq().accept(this.visitor),
            "Expected to throw due to empty parameters in WITH block.");
        assertTrue(thrown.getMessage().contains("Invalid parameters"), "Exception message should indicate empty parameters.");
    }
}
