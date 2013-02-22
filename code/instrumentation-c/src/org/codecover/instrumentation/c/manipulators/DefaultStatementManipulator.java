package org.codecover.instrumentation.c.manipulators;

import org.codecover.instrumentation.c.counter.CounterManager;
import org.codecover.instrumentation.c.syntaxtree.*;

import java.io.PrintWriter;

public class DefaultStatementManipulator implements StatementManipulator {
    private CounterManager cm;

    public DefaultStatementManipulator(CounterManager cm) {
        this.cm = cm;
    }

    @Override
    public void writeForwardDeclaration(PrintWriter out) {
        out.format("extern int %s[];\n", cm.stmtCntName());
    }

    @Override
    public void visit(PrintWriter out, ExpressionStatement n) {
        out.format("%s[%d]++;\n", cm.stmtCntName(), cm.newStmtID());
    }

    @Override
    public void visit(PrintWriter out, JumpStatement n) {
        out.format("%s[%d]++;\n", cm.stmtCntName(), cm.newStmtID());
    }

    @Override
    public void visit(PrintWriter out, SelectionStatement n) {
        out.format("%s[%d]++;\n", cm.stmtCntName(), cm.newStmtID());
    }

    @Override
    public void visit(PrintWriter out, IterationStatement n) {
        out.format("%s[%d]++;\n", cm.stmtCntName(), cm.newStmtID());
    }
}
