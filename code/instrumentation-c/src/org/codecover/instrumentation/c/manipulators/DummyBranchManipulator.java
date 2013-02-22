package org.codecover.instrumentation.c.manipulators;

import org.codecover.instrumentation.c.syntaxtree.ElseStatement;
import org.codecover.instrumentation.c.syntaxtree.SelectionStatement;

import java.io.PrintWriter;

public class DummyBranchManipulator implements BranchManipulator {
    @Override
    public void visit(PrintWriter out, SelectionStatement n) {
    }

    @Override
    public void visit(PrintWriter out, ElseStatement n) {
    }
}
