import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ast.Node;
import controller.AQLVisitor;
import controller.Evaluator;
import gen.AQLLexer;
import gen.AQLParser;
import helper.testData;
import main.UI;

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

    private void testJSONs(String aqlInput, String expectedOutput, String testDescription) {
        JSONArray resultArray = null;
        JSONArray expectedArray = null;
        try {
            String resultString = UI.parseAndExecuteDSL(aqlInput, null).replaceAll("\n", "").replaceAll("\r", "");
            resultArray = new JSONArray(resultString);
        } catch (Exception e) {
            System.out.println("input: " + aqlInput + "\n caused exception from parseAndExecuteDSL");
            assert(false);
        }
    
        try {
            expectedOutput = expectedOutput.replaceAll("\n", "").replaceAll("\r", "");
            expectedArray = new JSONArray(expectedOutput);
        } catch (Exception e) {
            System.out.println("Invalid expected JSON format for: " + testDescription);
            assert(false);
        }

        System.out.println(resultArray);
        System.out.println(expectedArray);
    
        boolean allElementsFound = true;
        for (int i = 0; i < expectedArray.length(); i++) {
            JSONObject expectedElement = expectedArray.getJSONObject(i);
            boolean found = false;
            for (int j = 0; j < resultArray.length() && !found; j++) {
                JSONObject resultElement = resultArray.getJSONObject(j);
                if (resultElement.similar(expectedElement)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                allElementsFound = false;
                break;
            }
        }
    
        assertTrue(allElementsFound, "Not all expected elements found in the result for: " + testDescription);
    }

    @Test
    void testSimpleGet() {
        this.inputTest_UI(testData.simpleGet_input,
                testData.simpleSuccessNoLog,
                "simple get test");
    }

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

    @Test
    void testTrip_1() {
        this.testJSONs(testData.e2e_trip_success_1_input,
                testData.e2e_trip_success_1_output + testData.simpleSuccessNoLog,
                "trip server 1 success test");
    }
    
    @Test
    void testServer_1() {
        this.testJSONs(testData.e2e_aqlserver_success_1_input,
                testData.e2e_aqlserver_success_1_output + testData.simpleSuccessNoLog,
                "server 1 success test");
    }

    @Test
    void testCat_1() {
        this.inputTest_UI(testData.e2e_cat_1_success_input,
                testData.e2e_cat_1_success_output + testData.simpleSuccessNoLog,
                "cat 1 success test");
    }

    @Test
    void testCat_2() {
        this.inputTest_UI(testData.e2e_cat_2_success_input,
                testData.e2e_cat_2_success_output + testData.simpleSuccessNoLog,
                "cat 2 success test");
    }

    @Test
    void testNestedOnElse() {
        this.inputTest_UI(testData.e2e_cat_2_success_input,
                testData.e2e_cat_2_success_output + testData.simpleSuccessNoLog,
                "cat 2 success test");
    }

    @Test
    void testFailureCat_EmptyON() {
        this.inputTest_UI(testData.e2e_nestedOnElse_input,
                testData.e2e_nestedOnElse_output + testData.simpleSuccessNoLog, "Nested OnElse success");
    }

    @Test
    void testFailureCat_EmptyELSE() {
        this.inputTest_UI(testData.e2e_cat_EmptyElse_failed_input,
                testData.e2e_cat_EmptyElse_failed_ouput + testData.simpleSuccessNoLog, "cat Empty ELSE failed input success test");
    }

    @Test
    void testFailureCat_EmptyLOG() {
        this.inputTest_UI(testData.e2e_cat_EmptyLog_failed_input,
                testData.e2e_cat_EmptyLog_failed_output + testData.simpleSuccessNoLog, "cat Empty LOG failed input success test");
    }

    @Test
    void testFailureCat_dynamicVar() {
        this.inputTest_UI(testData.e2e_cat_dynamicVar_failed_input,
                testData.e2e_cat_dynamicVar_failed_output + testData.simpleSuccessNoLog, "cat dynamicVar failed input success test");
    }

    @Test
    void testFailureCat_Variable() {
        this.inputTest_UI(testData.e2e_cat_Variable_failed_input,
                testData.e2e_cat_Variable_failed_output + testData.simpleSuccessNoLog, "cat Variable failed input success test");
    }

    @Test
    void testFailureCat_Condition() {
        this.inputTest_UI(testData.e2e_cat_Condition_failed_input,
                testData.e2e_cat_Condition_failed_output + testData.simpleSuccessNoLog, "cat Condition failed input success test");
    }

    @Test
    void testFailureCat_Comparator() {
        this.inputTest_UI(testData.e2e_cat_Comparator_failed_input,
                testData.e2e_cat_Comparator_failed_output + testData.simpleSuccessNoLog, "cat Comparator failed input success test");
    }

    @Test
    void testFailureURL1_Comparator() {
        this.inputTest_UI(testData.e2e_cat_URL1_failed_input,
                testData.e2e_cat_URL1_failed_output + testData.simpleSuccessNoLog, "cat URL1 failed input success test");
    }

    @Test
    void testFailureURL2_Comparator() {
        this.inputTest_UI(testData.e2e_cat_URL2_failed_input,
                testData.e2e_cat_URL2_failed_output + testData.simpleSuccessNoLog, "cat URL2 failed input success test");
    }

    @Test
    void testLocalScope_Loop() {
        this.inputTest_UI(testData.e2e_local_scope_failed_input, testData.e2e_local_scope_failed_output + testData.simpleSuccessNoLog, "testing out of scope");
    }

    @Test
    void testLocalScopeCat_Loop() {
        this.inputTest_UI(testData.e2e_cat_URL_local_scope_failed_input, testData.e2e_local_scope_failed_output + testData.simpleSuccessNoLog, "testing out of scope");
    }

    @Test
    void testLocalScope_LoopDupeName() {
        this.inputTest_UI(testData.e2e_cat_URL_dupe_var_name_input, testData.e2e_cat_URL_dupe_var_name_output + testData.simpleSuccessNoLog, "testing  out of scope");
    }

    @Test
    void testLocalScope_Loop_Nested_EarlyFailure() {
        this.inputTest_UI(testData.e2e_nested_scopes_loop_fail_early_input, testData.e2e_nested_scopes_loop_fail_early_output + testData.simpleSuccessNoLog, "testing  out of scope");
    }

    @Test
    void testLocalScope_Loop_Nested_LateFailure() {
        this.inputTest_UI(testData.e2e_nested_scopes_loop_input, testData.e2e_nested_scopes_loop_output + testData.simpleSuccessNoLog, "testing  out of scope");

    }

    @Test
    void testLocalScope_Loop_Nested_Success() {
        this.inputTest_UI(testData.e2e_nested_scope_success_input, testData.e2e_nested_scope_success_ouput + testData.simpleSuccessNoLog, "testing  out of scope");

    }

    @Test
    void testLocalScope_OnElse_Success() {
        this.inputTest_UI(testData.e2e_local_scope_success_on_else_input, testData.e2e_local_scope_success_on_else_output + testData.simpleSuccessNoLog, "testing  out of scope");
    }

    @Test
    void testLocalScope_OnElse_Failure() {
        this.inputTest_UI(testData.e2e_local_scope_failed_on_else_input, testData.e2e_local_scope_failed_on_else_output + testData.simpleSuccessNoLog, "testing  out of scope");
    }

    @Test
    void testLocalScope_OnElse_Nested_Success() {
        this.inputTest_UI(testData.e2e_local_scope_success_on_else_nested_input, testData.e2e_local_scope_success_on_else_nested_ouput + testData.simpleSuccessNoLog, "testing  out of scope");
    }

    @Test
    void testLocalScope_OnElse_Nested_Failure() {
        this.inputTest_UI(testData.e2e_local_scope_fail_on_else_nested_input, testData.e2e_local_scope_fail_on_else_nested_ouput + testData.simpleSuccessNoLog, "testing  out of scope");
    }
}
