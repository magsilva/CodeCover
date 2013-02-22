package org.codecover.instrumentation.c.manipulators;

import org.codecover.instrumentation.c.syntaxtree.ElseStatement;

import java.io.PrintWriter;

public interface BranchManipulator {
    public void visit(PrintWriter out, ElseStatement n);
}
