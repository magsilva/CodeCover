package org.codecover.instrumentation.c.adapter;

import org.anarres.cpp.LexerException;
import org.anarres.cpp.Preprocessor;
import org.anarres.cpp.Token;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CCPreprocessor extends Preprocessor {
    private boolean ignoreTokens;

    public CCPreprocessor(File file) throws IOException {
        super(file);
    }

    @Override
    protected void pragma(Token name, List<Token> value) throws IOException, LexerException {
        if(!"CodeCover".equalsIgnoreCase(name.getText()))
            return;

        if (value.size() < 1)
            return;

        String cmd = value.get(1).getText();

        if("On".equalsIgnoreCase(cmd)) {
            ignoreTokens = false;
        } else if("Off".equalsIgnoreCase(cmd)) {
            ignoreTokens = true;
        }
    }

    @Override
    public Token token() throws IOException, LexerException {
        Token t;
        do {
            t = super.token();
        } while (ignoreTokens);
        return t;
    }
}
