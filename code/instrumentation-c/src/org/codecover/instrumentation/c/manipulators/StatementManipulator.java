package org.codecover.instrumentation.c.manipulators;

import org.codecover.instrumentation.c.syntaxtree.*;

import java.io.PrintWriter;

public interface StatementManipulator {
    void visit(PrintWriter out, ExpressionStatement n);

    void writeForwardDeclaration(PrintWriter out);

    void writeDefinition(PrintWriter out);

    void visit(PrintWriter out, JumpStatement n);

    void visit(PrintWriter out, SelectionStatement n);

    void visit(PrintWriter out, IterationStatement n);
}
