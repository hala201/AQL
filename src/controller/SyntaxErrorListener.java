package controller;

import java.io.PrintWriter;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class SyntaxErrorListener extends BaseErrorListener {
    private boolean hasError = false;
    PrintWriter printWriter;

    public SyntaxErrorListener(PrintWriter out) {
        this.printWriter = out;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        this.hasError = true;
        this.printWriter.write("Syntax error at line " + line + ":" + charPositionInLine + " - " + msg);
    }

    public boolean hasError() {
        return this.hasError;
    }
}