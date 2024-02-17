package visitor;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sun.jdi.request.InvalidRequestStateException;

import ast.condition.Condition;
import ast.loop.Variable;
import controller.AQLVisitor;
import gen.AQLLexer;
import gen.AQLParser;
import helper.testData;

public class OnElseConditionVisitorTest {
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
    void testConditionString() {
        String input = testData.getCondition_valid_string_input;
        AQLParser parser = this.getParserForInput(input);
        Condition result = (Condition) parser.condition().accept(this.visitor);

        assertNotNull(result);
        assertTrue(result.getLeft() instanceof String);
        assertTrue(result.getRight() instanceof String);
    }

    @Test
    void testConditionNumber() {
        String input = testData.getCondition_valid_number_input;
        AQLParser parser = this.getParserForInput(input);
        Condition result = (Condition) parser.condition().accept(this.visitor);

        assertNotNull(result);
        assertTrue(result.getLeft() instanceof Number);
        assertTrue(result.getRight() instanceof Number);
    }

    @Test
    void testConditionWith2VAR() {
        String input = testData.getCondition_valid_input3_2VAR;
        AQLParser parser = this.getParserForInput(input);
        Condition result = (Condition) parser.condition().accept(this.visitor);

        assertNotNull(result);
        assertTrue(result.getLeft() instanceof Variable);
        assertTrue(result.getRight() instanceof Variable);
    }

    @Test
    void testConditionWith2DVAR() {
        String input = testData.getCondition_valid_input4_2DVAR;
        AQLParser parser = this.getParserForInput(input);
        Condition result = (Condition) parser.condition().accept(this.visitor);

        assertNotNull(result);
        assertTrue(result.getLeft() instanceof String);
        assertTrue(result.getRight() instanceof String);
    }

    @Test
    void testConditionWithVARandDVAR() {
        String input = testData.getCondition_valid_input5_VARandDVAR;
        AQLParser parser = this.getParserForInput(input);
        Condition result = (Condition) parser.condition().accept(this.visitor);

        assertNotNull(result);
        assertTrue(result.getLeft() instanceof Variable);
        assertTrue(result.getRight() instanceof String);
    }

    @Test
    void testConditionWithVARandVAL() {
        String input = testData.getCondition_valid_input6_VARandVAL;
        AQLParser parser = this.getParserForInput(input);
        Condition result = (Condition) parser.condition().accept(this.visitor);

        assertNotNull(result);
        assertTrue(result.getLeft() instanceof Variable);
        assertTrue(result.getRight() instanceof String);
    }

    @Test
    void testOnElseEmptyOnBody() {
        String input = testData.getOnElseCondition_Empty_On_number_input;
        AQLParser parser = this.getParserForInput(input);

        InvalidRequestStateException thrown = assertThrows((InvalidRequestStateException.class), () -> parser.onElse()
                .accept(this.visitor), "Expect to throw empty ON body error");
        assertTrue(thrown.getMessage().contains("Empty ON body"));
    }

    @Test
    void testOnElseEmptyElseBody() {
        String input = testData.getOnElseCondition_Empty_Else_number_input;
        AQLParser parser = this.getParserForInput(input);

        InvalidRequestStateException thrown = assertThrows((InvalidRequestStateException.class), () -> parser.onElse()
                        .accept(this.visitor),
                "Expect to throw empty ElSE body error");
        assertTrue(thrown.getMessage().contains("Empty ELSE body"));
    }

    @Test
    void testOnElseInvalidCondition() {
        String input = testData.getOnElseCondition_Invalid_rule_number_input;
        AQLParser parser = this.getParserForInput(input);

        IllegalArgumentException thrown = assertThrows((IllegalArgumentException.class), () -> parser
                .condition().accept(this.visitor), "Expect to throw invalid condition error");
        assertTrue(thrown.getMessage().contains("Invalid Conditional Statement at"));
    }

    @Test
    void testOnElseNoConditionRule() {
        String input = testData.getOnElseCondition_No_condition_number_input;
        AQLParser parser = this.getParserForInput(input);

        IllegalArgumentException thrown = assertThrows((IllegalArgumentException.class), () -> parser
                .condition().accept(this.visitor), "Expect to throw invalid condition rule error");
        assertTrue(thrown.getMessage().contains("Invalid Conditional Statement at"));
    }
}