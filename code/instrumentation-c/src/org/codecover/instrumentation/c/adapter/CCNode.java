package org.codecover.instrumentation.c.adapter;

import org.codecover.instrumentation.booleanterms.InstrBooleanTerm;

public class CCNode {
    public int beginOffset, endOffset;

    public int stmtID;
    public int branchID;
    public int loopID;
    public int condID;

    public InstrBooleanTerm terms;
}
