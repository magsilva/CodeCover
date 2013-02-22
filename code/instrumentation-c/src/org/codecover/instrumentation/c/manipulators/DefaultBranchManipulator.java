package org.codecover.instrumentation.c.manipulators;

import org.codecover.instrumentation.c.counter.CounterManager;
import org.codecover.instrumentation.c.syntaxtree.ElseStatement;
import org.codecover.instrumentation.c.syntaxtree.SelectionStatement;

import java.io.PrintWriter;

public class DefaultBranchManipulator implements BranchManipulator {
    private CounterManager cm;

    public DefaultBranchManipulator(CounterManager cm) {
        this.cm = cm;
    }

    @Override
    public void visit(PrintWriter out, SelectionStatement n) {

    }

    @Override
    public void visit(PrintWriter out, ElseStatement n) {

    }
}
