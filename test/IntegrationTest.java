
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import ast.Node;
import ast.Program;
import controller.AQLVisitor;
import gen.AQLLexer;
import gen.AQLParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.Evaluator;

class IntegrationTest {
    // End-to-end test
    private Evaluator evaluator;
    private StringWriter output;

    @BeforeEach
    void setUp() {
        this.evaluator = new Evaluator();
        this.output = new StringWriter();
    }

    private void inputTest(String aqlInput, String aqlOutput, String test)
    {
        // TODO: remove debug output from Evaluator in testData, e.g doing program
        AQLLexer lexer = new AQLLexer(CharStreams.fromString(aqlInput));
        lexer.reset();
        TokenStream tokens = new CommonTokenStream(lexer);
        AQLParser parser = new AQLParser(tokens);
        AQLVisitor visitor = new AQLVisitor();
        Node parsedProgram = parser.program().accept(visitor);
        StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);
        parsedProgram.accept(new Evaluator(), out);
        assertEquals(sw.toString(), aqlOutput, "Fail to match the expected output for: " + test);
    }

    @Test
    void testSimplePost() {
        this.inputTest(testData.simplePost_input,
                testData.simplePost_integration_output,
                "simple post test");
    }

    @Test
    void testSimplePut() {
        this.inputTest(testData.simplePut_input,
                testData.simplePut_integration_output,
                "simple put test");
    }
}