package visitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ast.api.PostReq;
import controller.AQLVisitor;
import gen.AQLParser;
import helper.TestUtils;

public class PostReqTest {
    private AQLVisitor visitor;

    @BeforeEach
    void setUp() {
        this.visitor = new AQLVisitor();
    }

    @Test
    void testVisitPostReq_ParsesCorrectly() {
        String input = "POST https://api.example.com/users/{user.id}";
        AQLParser parser = TestUtils.getParserForInput(input);
        PostReq result = (PostReq) parser.postReq().accept(this.visitor);

        assertNotNull(result, "The result should not be null.");
        assertEquals("https://api.example.com/users/", result.getHead(), "The head of the URI should be correctly parsed.");
        assertTrue(result.getBody().contains("{user.id}"), "The body should contain the user id placeholder.");
    }

    @Test
    void testVisitPostReqWithParams() {
        String input = "POST https://api.example.com/users/{user.id} WITH { \"id\": 456, \"value\": \"xyz-abc\" }";
        AQLParser parser = TestUtils.getParserForInput(input);
        PostReq result = (PostReq) parser.postReq().accept(this.visitor);

        assertNotNull(result, "The result should not be null.");
        assertEquals("https://api.example.com/users/", result.getHead(), "The head of the URI should be correctly parsed.");
        assertNotNull(result.getParams(), "Params should not be null.");
        assertEquals(456, result.getParams().getContent().getInt("id"), "The id parameter should be correctly parsed.");
        assertEquals("xyz-abc", result.getParams().getContent().getString("value"), "The value parameter should be correctly parsed.");
    }
}
