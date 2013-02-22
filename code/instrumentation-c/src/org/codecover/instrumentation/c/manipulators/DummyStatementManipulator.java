package org.codecover.instrumentation.c.manipulators;

import org.codecover.instrumentation.c.syntaxtree.ExpressionStatement;
import org.codecover.instrumentation.c.syntaxtree.IterationStatement;
import org.codecover.instrumentation.c.syntaxtree.JumpStatement;
import org.codecover.instrumentation.c.syntaxtree.SelectionStatement;

import java.io.PrintWriter;

public class DummyStatementManipulator implements StatementManipulator {
    @Override
    public void visit(PrintWriter out, ExpressionStatement n) {
    }

    @Override
    public void writeForwardDeclaration(PrintWriter out) {
    }

    @Override
    public void visit(PrintWriter out, JumpStatement n) {

    }

    @Override
    public void visit(PrintWriter out, SelectionStatement n) {

    }

    @Override
    public void visit(PrintWriter out, IterationStatement n) {

    }
}
