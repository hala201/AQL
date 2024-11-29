
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

import gen.AQLLexer;
import gen.AQLParser;
import helper.testData;

public class antlrRegressionTest {

    private void testParser(String input, String expectedOutput) {
        AQLParser.ProgramContext tree = this.parse(input);
        assertEquals(expectedOutput, tree.toStringTree(new AQLParser(null)));
    }

    private AQLParser.ProgramContext parse(String input) {
        AQLLexer lexer = new AQLLexer(CharStreams.fromString(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        lexer.reset();
        AQLParser parser = new AQLParser(tokens);
        return parser.program();
    }

    @Test
    public void testSimpleLog() {
        this.testParser(testData.simpleLog_input, testData.simpleLog_parse_output);
    }

    @Test
    public void testLogWithVariable() {
        this.testParser(testData.LogWithVariable_input, testData.LogWithVariable_parse_output);
    }

    @Test
    public void testSimpleGet() {
        this.testParser(testData.simpleGet_input, testData.simpleGet_parse_output);
    }

    @Test
    public void testSimplePut() {
        this.testParser(testData.simplePut_input, testData.simplePut_parse_output.trim());
    }

    @Test
    public void testSimplePost() {
        this.testParser(testData.simplePost_input, testData.simplePost_parse_output.trim());
    }

    @Test
    public void testSimpleDelete() {
        this.testParser(testData.simpleDelete_input, testData.simpleDelete_parse_output);
    }

    @Test
    public void testWithBlock() {
        this.testParser(testData.withBlock_input, testData.withBlock_parse_output.trim());
    }

    @Test
    public void testOnElseCondition() {
        this.testParser(testData.onElseCondition_input, testData.onElseCondition_parse_output);
    }

    @Test
    public void testLoopStatement() {
        this.testParser(testData.loopStatement_Valid_input, testData.loopStatement_parse_output);
    }

    @Test
    public void testComplex1() {
        this.testParser(testData.complex1_input, testData.complex1_parse_output.trim());
    }

    @Test
    public void testNumberLog() {
        this.testParser(testData.numberLog_input, testData.numberLog_parse_output);
    }
    @Test
    public void testLogDynamicVarString() {
        this.testParser(testData.LogWithDynamicVar_inputString, testData.LogWithDynamicVar_parse_outputString);
    }

    @Test
    public void testLogDynamicVarNumber() {
        this.testParser(testData.LogWithDynamicVar_inputNumber, testData.LogWithDynamicVar_parse_outputNumber);
    }

    @Test
    public void testSetStatement() {
        this.testParser(testData.setStatement_input, testData.setStatement_parse_output);
    }

    @Test
    public void testSetValue(){
        this.testParser(testData.SetWithString_input, testData.SetWithString_parse_output);
    }

    @Test
    public void testSetValueNumber(){
        this.testParser(testData.SetWithNumber_input, testData.SetWithNumber_parse_output);
    }

    @Test
    public void testSetReq(){
        this.testParser(testData.SetWithGetReq_input, testData.SetWithGetReq_parse_output);
    }

}