
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

import main.UI;

import helper.testData;

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
        AQLLexer lexer = new AQLLexer(CharStreams.fromString(aqlInput));
        lexer.reset();
        TokenStream tokens = new CommonTokenStream(lexer);
        AQLParser parser = new AQLParser(tokens);
        AQLVisitor visitor = new AQLVisitor();
        Node parsedProgram = parser.program().accept(visitor);
        StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);
        parsedProgram.accept(new Evaluator(), out);
        assertEquals(aqlOutput, sw.toString(), "Fail to match the expected output for: " + test);
    }

    private void inputTest_UI(String aqlInput, String aqlOutput, String test)
    {
        String result = "";
        try
        {
            result = UI.parseAndExecuteDSL(aqlInput, null);
        }
        catch (Exception e)
        {
            System.out.println("input: " + aqlInput + "\n caused exception from parseAndExecuteDSL");
            assert(false);
        }
        // Suppress line separator difference
        aqlOutput = aqlOutput.replaceAll("\n", "").replaceAll("\r", "");
        result = result.replaceAll("\n", "").replaceAll("\r", "");

        assertEquals(aqlOutput, result,"Fail to match the expected output for: " + test);
    }

    @Test
    void testSimpleGet() {
        this.inputTest_UI(testData.simpleGet_input,
                testData.simpleSuccessNoLog,
                "simple get test");
    }

    // TODO: actual url needed
//    @Test
//    void testSimpleDelete() {
//        this.inputTest_UI(testData.simpleDelete_input,
//                testData.simpleSuccessNoLog,
//                "simple delete test");
//    }

     @Test
     void testSimplePost() {
         this.inputTest_UI(testData.simplePost_input,
                 testData.simpleSuccessNoLog,
                 "simple post test");
     }

     @Test
     void testSimplePut() {
         this.inputTest_UI(testData.simplePut_input,
                 testData.simpleSuccessNoLog,
                 "simple put test");
     }

    @Test
    void testSimpleLog() {
        this.inputTest_UI(testData.simpleLog_input,
                testData.simpleLog_e2e_output + testData.simpleSuccessNoLog,
                "simple log test");
    }

//    @Test
//    void testTrip_1() {
        // Special case, "_id" is changing every time
//        this.inputTest_UI(testData.e2e_trip_success_1_input,
//                testData.e2e_trip_success_1_output + testData.simpleSuccessNoLog,
//                "trip 1 success test");
//        String result = "";
//        String input = testData.e2e_trip_success_1_input;
//        String expected = testData.e2e_trip_success_1_output + testData.simpleSuccessNoLog;
//        try
//        {
//           result = UI.parseAndExecuteDSL(input, null);
//        }
//        catch (Exception e)
//        {
//            System.out.println("input: " + input + "\n caused exception from parseAndExecuteDSL");
//            assert(false);
//        }
//        // Suppress line separator difference
//        expected = expected.replaceAll("\n", "").replaceAll("\r", "");
//        result = result.replaceAll("\n", "").replaceAll("\r", "");
//        int part1 = expected.indexOf("_id");
//        int part2 = expected.indexOf("numberOfDays");
//        assertEquals(expected.length(), result.length());
//        assertEquals(expected.substring(0, part1), result.substring(0, part1), "Fail to match the expected output for: trip 1 success test");
//        assertEquals(expected.substring(part2), result.substring(part2), "Fail to match the expected output for: trip 1 success test");

//    }

    @Test
    void testServer_1() {
        this.inputTest_UI(testData.e2e_aqlserver_success_1_input,
                testData.e2e_aqlserver_success_1_output + testData.simpleSuccessNoLog,
                "server 1 success test");
    }

    @Test
    void testCat_1() {
        this.inputTest_UI(testData.e2e_cat_1_success_input,
                testData.e2e_cat_1_success_output + testData.simpleSuccessNoLog,
                "cat 1 success test");
    }
}
