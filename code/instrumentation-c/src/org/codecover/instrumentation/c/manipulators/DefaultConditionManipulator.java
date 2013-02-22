package org.codecover.instrumentation.c.manipulators;

import org.codecover.instrumentation.c.counter.CounterManager;

public class DefaultConditionManipulator implements ConditionManipulator {
    private CounterManager cm;

    public DefaultConditionManipulator(CounterManager cm) {
        this.cm = cm;
    }
}
