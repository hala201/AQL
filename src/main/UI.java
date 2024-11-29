package main;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import org.antlr.v4.runtime.*;

import ast.*;
import controller.*;
import gen.*;
import gen.AQLParser.ProgramContext;

import org.fife.ui.rtextarea.RTextScrollPane;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;

public class UI {

    public UI() {
        SwingUtilities.invokeLater(() -> this.createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = this.createFrame();
        RSyntaxTextArea inputArea = this.createInputArea();
        JTextArea outputArea = this.createOutputArea();
        JButton runButton = this.createRunButton(inputArea, outputArea);

        JPanel northPanel = this.createNorthPanel(runButton);
        JPanel centerPanel = this.createCenterPanel(inputArea);
        JPanel southPanel = this.createSouthPanel(outputArea);

        frame.setContentPane(this.assembleMainPanel(northPanel, centerPanel, southPanel));
        this.finalizeFrame(frame);
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame("AQL Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        return frame;
    }

    private JTextArea createOutputArea() {
        JTextArea outputArea = new JTextArea(15, 50);
        outputArea.setEditable(false);
        return outputArea;
    }

    private JButton createRunButton(JTextArea inputArea, JTextArea outputArea) {
        JButton runButton = new JButton("Run");
        runButton.addActionListener(e -> {
            String input = inputArea.getText();
            try {
                String results = this.parseAndExecuteDSL(input, outputArea);
                outputArea.setText(results);
            } catch (Exception ex) {
                outputArea.setText("Error during parsing: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        return runButton;
    }

    private JPanel createNorthPanel(JButton runButton) {
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        northPanel.add(runButton);
        return northPanel;
    }

    private RSyntaxTextArea createInputArea() {
        RSyntaxTextArea inputArea = new AQLSyntax(15, 50);

        AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory) TokenMakerFactory.getDefaultInstance();
        atmf.putMapping("text/AQL", "main.AQLTokenMaker");
        inputArea.setSyntaxEditingStyle("text/AQL");
        inputArea.setTabSize(4);

        return inputArea;
    }

    private JPanel createCenterPanel(RSyntaxTextArea inputArea) {
        JPanel centerPanel = new JPanel(new BorderLayout());
        RTextScrollPane inputScrollPane = new RTextScrollPane(inputArea);
        centerPanel.add(inputScrollPane, BorderLayout.CENTER);
        return centerPanel;
    }

    private JPanel createSouthPanel(JTextArea outputArea) {
        JPanel southPanel = new JPanel(new BorderLayout());
        JLabel outputLabel = new JLabel("OUTPUT");
        southPanel.add(outputLabel, BorderLayout.PAGE_START);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        southPanel.add(outputScrollPane, BorderLayout.CENTER);
        return southPanel;
    }

    private JPanel assembleMainPanel(JPanel northPanel, JPanel centerPanel, JPanel southPanel) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        return mainPanel;
    }

    private void finalizeFrame(JFrame frame) {
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static public String parseAndExecuteDSL(String dslInput, JTextArea outputArea) throws Exception {
        AQLLexer lexer = new AQLLexer(CharStreams.fromString(dslInput));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AQLParser parser = new AQLParser(tokens);
        parser.removeErrorListeners();
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);
        SyntaxErrorListener errorListener = new SyntaxErrorListener(out);
        parser.addErrorListener(errorListener);

        StringBuilder results = new StringBuilder();

        try {
            AQLVisitor visitor = new AQLVisitor();
            ProgramContext tree = parser.program();
            Node parsedProgram = tree.accept(visitor);

            if (!errorListener.hasError()) {
                parsedProgram.accept(new Evaluator(), out);
                results.append(stringWriter.toString());
            } else {
                results.append("Syntax errors found.\n");
                results.append(stringWriter.toString());
            }
        } catch (Exception e) {
            results.append(e.getMessage());
        }

        results.append("\n\n---------\nDone!\n");

        return results.toString();
    }

    
}

