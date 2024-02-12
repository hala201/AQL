package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import ast.Node;
import controller.AQLVisitor;
import controller.Evaluator;
import controller.SyntaxErrorListener;
import gen.AQLLexer;
import gen.AQLParser;

public class main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        String aqlInput = """
        GET https://65y4r.wiremockapi.cloud/admins/{users.id}
        GET https://65y4r.wiremockapi.cloud/users
        DELETE https://65y4r.wiremockapi.cloud/users
        """;
        AQLLexer lexer = new AQLLexer(CharStreams.fromString(aqlInput));
        System.out.println(lexer.getAllTokens());
        lexer.reset();

        TokenStream tokens = new CommonTokenStream(lexer);
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        SyntaxErrorListener errorListener = new SyntaxErrorListener(out);
        
        AQLParser parser = new AQLParser(tokens);
        parser.addErrorListener(errorListener);
        AQLVisitor visitor = new AQLVisitor();
        Node parsedProgram = parser.program().accept(visitor);

        if (!(errorListener.hasError())) {
            System.out.println("No syntax errors found.");
            // System.out.println(parser.program().toStringTree(parser));
            parsedProgram.accept(new Evaluator(), out);
        } else {
            System.out.println("Syntax errors found.");
        }

        out.close();
    }
}