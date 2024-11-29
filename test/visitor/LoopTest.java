package visitor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ast.Statement;
import ast.api.GetReq;
import ast.loop.Loop;
import controller.AQLVisitor;
import gen.AQLLexer;
import gen.AQLParser;
import helper.testData;

public class LoopTest {
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
    void testForEachValid() {
        String input = testData.loopStatement_Valid_input;
        AQLParser parser = this.getParseInputFor(input);
        Loop result = (Loop) parser.loop().accept(this.visitor);

        assertNotNull(result);
        assertEquals("user", result.getLoopControlVariable().getVariableName());
        assertEquals("https://api.example.com/users", ((GetReq) result.getIterable()).getHead());
        List<Statement> bodyStatements = result.getLoopBody().getTasks();
        assertEquals(1, bodyStatements.size());
    }

    @Test
    void testForEachDeclaredIterableValid() {
        String input = testData.loopStatement_Valid_DeclaredIterable_input;
        AQLParser parser = this.getParseInputFor(input);
        Loop result = (Loop) parser.loop().accept(this.visitor);
        assertNotNull(result);
        assertEquals("user", result.getLoopControlVariable().getVariableName());
        assertEquals("users", result.getIterable().toString());
        List<Statement> bodyStatements = result.getLoopBody().getTasks();
        assertEquals(1, bodyStatements.size());
    }

    @Test
    void testLongerBody() {
        String input = testData.loopStatement_Valid_LongerBody_input;
        AQLParser parser = this.getParseInputFor(input);
        Loop result = (Loop) parser.loop().accept(this.visitor);
        assertNotNull(result);
        assertEquals("user", result.getLoopControlVariable().getVariableName());
        List<Statement> bodyStatements = result.getLoopBody().getTasks();
        assertEquals(2, bodyStatements.size());

    }
}
