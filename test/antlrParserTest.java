import static org.junit.Assert.assertEquals;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import gen.AQLLexer;
import gen.AQLParser;

public class antlrParserTest {

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
        this.testParser("LOG 'Hello World'", "(program (statement (log LOG (string ' (value Hello) (value World) '))))");
    }

    @Test
    public void testLogWithVariable() {
        this.testParser("LOG variableName", "(program (statement (log LOG (value variableName))))");
    }

    @Test
    public void testSimpleGet() {
        this.testParser("GET https://api.example.com/data", "(program (statement (request (getReq GET (dynamicURI https://api.example.com/data)))))");
    }

    @Test
    public void testSimplePut() {
        this.testParser("PUT https://api.example.com/data",
                   "(program (statement (request (putReq PUT (dynamicURI https://api.example.com/data)))))");
    }

    @Test
    public void testSimplePost() {
        this.testParser("POST https://api.example.com/data",
                   "(program (statement (request (postReq POST (dynamicURI https://api.example.com/data)))))");
    }

    @Test
    public void testSimpleDelete() {
        this.testParser("DELETE https://api.example.com/data",
                   "(program (statement (request (delReq DELETE (dynamicURI https://api.example.com/data)))))");
    }

    @Test
    public void testWithBlock() {
        this.testParser("GET https://api.example.com/data WITH { param1: value1, param2: 'value2' }",
                   "(program (statement (request (getReq GET (dynamicURI https://api.example.com/data) (withBlock WITH { (params (param param1 : (value value1)) , (param param2 : (string ' (value value2) '))) })))))");
    }

    @Test
    public void testOnElseCondition() {
        this.testParser("GET https://api.example.com/data ON variable1 == variable2 { LOG variable1 } ELSE { LOG 'Error' }",
                   "(program (statement (request (getReq GET (dynamicURI https://api.example.com/data) (onElse ON (condition (value variable1) == (value variable2)) { (statement (log LOG (value variable1))) } ELSE { (statement (log LOG (string ' (value Error) '))) })))))");
    }

    @Test
    public void testSetStatement() {
        this.testParser("SET GET https://api.example.com/data AS myData",
                   "(program (statement (set SET (request (getReq GET (dynamicURI https://api.example.com/data))) AS myData)))");
    }

    @Test
    public void testLoopStatement() {
        this.testParser("FOR EACH user IN GET https://api.example.com/users { LOG user.name }",
                   "(program (statement (loop FOR EACH user IN (request (getReq GET (dynamicURI https://api.example.com/users))) { (statement (log LOG (value (dynamicVar user . name)))) })))");
    }

    @Test
    public void testComplex1() {
        this.testParser("FOR EACH user IN GET https://api.com/users {\n" +
        "    LOG user.name\n" +
        "    SET GET https://api.com/users/{user.id}/tasks AS TaskList\n" +
        "    SET 'failed' AS failMsg\n\n" +
        "    FOR EACH task IN TaskList {\n" +
        "        PUT https://api.com/tasks/{task.id} WITH { status: 'completed' }\n\n" +
        "        POST https://api.com/send WITH {\n" +
        "            userId: user.id,\n" +
        "            message: 'Task {task.id} completed'\n" +
        "        }\n\n" +
        "        ON task.sth == false {\n" +
        "            DELETE https://api.com/tasks/{task.id} \n" +
        "        } ELSE {\n" +
        "            LOG failMsg\n" +
        "        }\n" +
        "    }\n" +
        "}",
                   "(program (statement (loop FOR EACH user IN (request (getReq GET (dynamicURI https://api.com/users))) { (statement (log LOG (value (dynamicVar user . name)))) (statement (set SET (request (getReq GET (dynamicURI https://api.com/users/ (dynamicVar { user . id }) /tasks))) AS TaskList)) (statement (set SET (string ' (value failed) ') AS failMsg)) (statement (loop FOR EACH task IN TaskList { (statement (request (putReq PUT (dynamicURI https://api.com/tasks/ (dynamicVar { task . id })) (withBlock WITH { (params (param status : (string ' (value completed) '))) })))) (statement (request (postReq POST (dynamicURI https://api.com/send) (withBlock WITH { (params (param userId : (value (dynamicVar user . id))) , (param message : (string ' (value Task) (value (dynamicVar { task . id })) (value completed) '))) }) (onElse ON (condition (value (dynamicVar task . sth)) == (value false)) { (statement (request (delReq DELETE (dynamicURI https://api.com/tasks/ (dynamicVar { task . id }))))) } ELSE { (statement (log LOG (value failMsg))) })))) })) })))");
    }
}
