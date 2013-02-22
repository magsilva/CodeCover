package org.codecover.instrumentation.c.adapter;

import org.anarres.cpp.Preprocessor;

import java.io.File;
import java.io.IOException;

public class CCPreprocessor extends Preprocessor {
    public CCPreprocessor(File file) throws IOException {
        super(file);
    }
}
