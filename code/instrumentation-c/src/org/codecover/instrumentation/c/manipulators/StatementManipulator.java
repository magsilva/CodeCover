package org.codecover.instrumentation.c.manipulators;

import org.codecover.instrumentation.c.adapter.CCNode;

import java.io.PrintWriter;

public interface StatementManipulator {
    void visit(PrintWriter out, CCNode n);

    void writeForwardDeclaration(PrintWriter out);
}
