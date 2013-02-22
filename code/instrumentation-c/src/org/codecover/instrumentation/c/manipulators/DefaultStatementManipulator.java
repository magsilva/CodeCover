package org.codecover.instrumentation.c.manipulators;

import org.codecover.instrumentation.c.syntaxtree.ExpressionStatement;
import org.codecover.instrumentation.c.syntaxtree.IterationStatement;
import org.codecover.instrumentation.c.syntaxtree.JumpStatement;
import org.codecover.instrumentation.c.syntaxtree.SelectionStatement;

import java.io.PrintWriter;

public class DefaultStatementManipulator implements StatementManipulator {
    private int counter=0;

    @Override
    public void visit(PrintWriter out, ExpressionStatement n) {
        out.format("CodeCover_StmtCounter[%d]++;\n", counter++);
    }

    @Override
    public void writeForwardDeclaration(PrintWriter out) {
        out.write("extern int CodeCover_StmtCounter[];\n");
    }

    @Override
    public void writeDefinition(PrintWriter out) {
        out.format("int CodeCover_StmtCounter[%d];\n", counter);
    }

    @Override
    public void visit(PrintWriter out, JumpStatement n) {
        out.format("CodeCover_StmtCounter[%d]++;\n", counter++);
    }

    @Override
    public void visit(PrintWriter out, SelectionStatement n) {
        out.format("CodeCover_StmtCounter[%d]++;\n", counter++);
    }

    @Override
    public void visit(PrintWriter out, IterationStatement n) {
        out.format("CodeCover_StmtCounter[%d]++;\n", counter++);
    }
}
