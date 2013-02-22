package org.codecover.instrumentation.c.manipulators;

import org.codecover.instrumentation.c.counter.CounterManager;

import java.io.PrintWriter;

public class DefaultQMOManipulator implements QMOManipulator {
    private final CounterManager cm;

    public DefaultQMOManipulator(CounterManager cm) {
        this.cm = cm;
    }

    @Override
    public void writeForwardDeclaration(PrintWriter out) {
    }
}
