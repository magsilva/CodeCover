package org.codecover.instrumentation.c.manipulators;

import org.codecover.instrumentation.c.adapter.CCNode;
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
        out.format("extern int %s[];\n", cm.stmtVarName());
    }

    private void instrument(PrintWriter out, CCNode n) {
        out.format("%s[%d]++;\n", cm.stmtVarName(), n.stmtNum);
    }

    @Override
    public void visit(PrintWriter out, ExpressionStatement n) {
        instrument(out, (CCNode) n.getParent().getParent());
    }

    @Override
    public void visit(PrintWriter out, JumpStatement n) {
        instrument(out, (CCNode) n.getParent().getParent());
    }

    @Override
    public void visit(PrintWriter out, SelectionStatement n) {
        instrument(out, (CCNode) n.getParent().getParent());
    }

    @Override
    public void visit(PrintWriter out, IterationStatement n) {
        instrument(out, (CCNode) n.getParent().getParent());
    }
}
