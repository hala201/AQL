package controller.helper;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import gen.AQLParser;

public class URIParts {
    String head;
    List<String> body;
    List<String> tail;

    public URIParts(String head, List<String> body, List<String> tail) {
          this.head = head;
          this.body = body;
          this.tail = tail;
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

    public static URIParts parseAndExtractURI(AQLParser.DynamicURIContext ctx) {
        String head = ctx.URI().getText();
        List<String> body = new ArrayList<>();
        List<String> tail = new ArrayList<>();
        for (AQLParser.DynamicVarContext var : ctx.dynamicVar()) {
            body.add(var.getText());
        }
        for (TerminalNode uri : ctx.URI_TAIL()) {
            tail.add(uri.getText());
        }
        return new URIParts(head, body, tail);
  }
}