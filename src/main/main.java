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
import gen.AQLLexer;
import gen.AQLParser;

public class main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        String aqlInput = """
        GET https://65y4r.wiremockapi.cloud/users/{user.id}/tasks
        GET https://65y4r.wiremockapi.cloud/users
        DELETE https://65y4r.wiremockapi.cloud/users
                """;
        AQLLexer lexer = new AQLLexer(CharStreams.fromString(aqlInput));
        System.out.println(lexer.getAllTokens());
        lexer.reset();
        TokenStream tokens = new CommonTokenStream(lexer);
        AQLParser parser = new AQLParser(tokens);
        AQLVisitor visitor = new AQLVisitor();
        Node parsedProgram = parser.program().accept(visitor);
        // System.out.println(parser.program().toStringTree(parser));
        PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
        parsedProgram.accept(new Evaluator(), out);
        out.close();
    }
}