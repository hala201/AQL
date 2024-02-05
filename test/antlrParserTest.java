
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;

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
    
    // TBD: update failing tests due to changes in grammar
    @Test
    public void testSimpleLog() {
        this.testParser("LOG 'Hello World'", "(program (statement (log LOG (string 'Hello World'))))");
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
        this.testParser("""
        GET https://api.example.com/data WITH { "param1": value1, "param2": 'value2' }
                """,
                   "(program (statement (request (getReq GET (dynamicURI https://api.example.com/data) (withBlock WITH { (params (param (string \"param1\") : (value value1)) , (param (string \"param2\") : (string 'value2'))) })))))");
    }

    @Test
    public void testOnElseCondition() {
        this.testParser("GET https://api.example.com/data ON variable1 == variable2 { LOG variable1 } ELSE { LOG 'Error' }",
                   "(program (statement (request (getReq GET (dynamicURI https://api.example.com/data) (onElse ON (condition (value variable1) == (value variable2)) { (statement (log LOG (value variable1))) } ELSE { (statement (log LOG (string 'Error'))) })))))");
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
        this.testParser("""
        FOR EACH user IN GET https://api.com/users {
            LOG user.name
            SET GET https://api.com/users/{user.id}/tasks AS TaskList
            SET 'failed' AS failMsg
        
            FOR EACH task IN TaskList {
                PUT https://api.com/tasks/{task.id} WITH { "status": 'completed' }
        
                POST https://api.com/send WITH {
                    "userId": user.id,
                    "message": 'Task {task.id} completed'
                }
        
                ON task.sth==false {
                    DELETE https://api.com/tasks/{task.id}
                } ELSE {
                    LOG failMsg
                }
            }
        }
                """,
                   "(program (statement (loop FOR EACH user IN (request (getReq GET (dynamicURI https://api.com/users))) { (statement (log LOG (value (dynamicVar user . name)))) (statement (set SET (request (getReq GET (dynamicURI https://api.com/users/ (dynamicVar { user . id }) /tasks))) AS TaskList)) (statement (set SET (string 'failed') AS failMsg)) (statement (loop FOR EACH task IN TaskList { (statement (request (putReq PUT (dynamicURI https://api.com/tasks/ (dynamicVar { task . id })) (withBlock WITH { (params (param (string \"status\") : (string 'completed'))) })))) (statement (request (postReq POST (dynamicURI https://api.com/send) (withBlock WITH { (params (param (string \"userId\") : (value (dynamicVar user . id))) , (param (string \"message\") : (string 'Task {task.id} completed'))) }) (onElse ON (condition (value (dynamicVar task . sth)) == (value false)) { (statement (request (delReq DELETE (dynamicURI https://api.com/tasks/ (dynamicVar { task . id }))))) } ELSE { (statement (log LOG (value failMsg))) })))) })) })))");
    }
}
