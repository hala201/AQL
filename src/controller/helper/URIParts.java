package controller.helper;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import gen.AQLParser;

public class URIParts {
    private String head;
    private List<String> body = new ArrayList<String>();
    private List<String> tail = new ArrayList<String>();

    public URIParts(AQLParser.DynamicURIContext ctx) {
        this.head = ctx.URI().getText();
        for (AQLParser.DynamicVarContext var : ctx.dynamicVar()) {
            this.body.add(var.getText());
        }
        for (TerminalNode uri : ctx.URI_TAIL()) {
            this.tail.add(uri.getText());
        }
    }

    public String getHead() {
        return this.head;
    }
    
    public List<String> getBody() {
        return this.body;
    }

    public List<String> getTail() {
        return this.tail;
    }
    
}