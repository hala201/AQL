package main;

import javax.swing.text.Segment;

import org.fife.ui.rsyntaxtextarea.AbstractTokenMaker;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMap;

public class AQLTokenMaker extends AbstractTokenMaker {

    @Override
    public Token getTokenList(Segment text, int initialTokenType, int startOffset) {
        this.resetTokenList();

        char[] array = text.array;
        int offset = text.offset;
        int count = text.count;
        int end = offset + count;

        for (int i = offset; i < end; i++) {
            char c = array[i];

            switch (c) {
                
                default:
                    if (i == end - 1) {
                        this.addToken(text, offset, i, Token.IDENTIFIER, startOffset);
                    }
                    break;
            }
        }

        this.addNullToken();
        return this.firstToken;
    }

    @Override
    public TokenMap getWordsToHighlight() {
        TokenMap tokenMap = new TokenMap();
        return tokenMap;
    }
}