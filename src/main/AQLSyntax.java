package main;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class AQLSyntax extends RSyntaxTextArea {

    public AQLSyntax(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        this.initCustomKeyBindings();
    }

    private void initCustomKeyBindings() {
        KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
        this.getInputMap().put(enter, "insertBreakCustom");
        this.getActionMap().put("insertBreakCustom", new AbstractAction() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                AQLSyntax.this.insertBreakCustom();
            }
        });
    }

    protected void insertBreakCustom() {
        try {
            int caretPosition = this.getCaretPosition();
            int lineStart = this.getLineStartOffsetOfCurrentLine(caretPosition);
            String textUpToCaret = this.getText(lineStart, caretPosition - lineStart);
            
            String currentIndentation = this.getCurrentIndentation(textUpToCaret);
            
            boolean openBraceBefore = textUpToCaret.trim().endsWith("{");
            boolean closeBraceAfter = textUpToCaret.trim().endsWith("}");
            
            StringBuilder insertion = new StringBuilder("\n").append(currentIndentation);
            
            if (openBraceBefore && !closeBraceAfter) {
                insertion.append("\t");
            } else if (closeBraceAfter) {
                insertion = new StringBuilder("\n").append(currentIndentation.substring(0, Math.max(0, currentIndentation.length() - 1)));
            }
            
            this.getDocument().insertString(caretPosition, insertion.toString(), null);
            this.setCaretPosition(caretPosition + insertion.length());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    
    private String getCurrentIndentation(String text) {
        StringBuilder indentation = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (ch == '\t' || ch == ' ') {
                indentation.append(ch);
            } else {
                break;
            }
        }
        return indentation.toString();
    }
    
    private int getLineStartOffsetOfCurrentLine(int caretPosition) throws BadLocationException {
        return this.getLineStartOffset(this.getLineOfOffset(caretPosition));
    }
    
    
}
