package main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;

import ast.Node;
import gen.AQLLexer;
import gen.AQLParser;
import utils.AQLPrintVisitor;

public class main {
    static AQLParser.ProgramContext parseAQLExample() {
        String aqlInput = 
                    "FOR EACH user IN GET https://api.com/users {\n" +
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
                    "}";
        // String aqlInput = 
        //             "LOG hi\n";
        AQLLexer lexer = new AQLLexer(CharStreams.fromString(aqlInput));
        for (Token token : lexer.getAllTokens()) {
            System.out.println(token);
        }
        lexer.reset();
        TokenStream tokens = new CommonTokenStream(lexer);
        AQLParser parser = new AQLParser(tokens);
        AQLParser.ProgramContext tree = parser.program();
        AQLPrintVisitor visitor = new AQLPrintVisitor();
        // System.out.println(tree.toStringTree(parser));
        visitor.visit(tree);

        return tree;
    }
    public static void main(String[] args) {
        System.out.println("Hello World!");
        parseAQLExample();
    }
}