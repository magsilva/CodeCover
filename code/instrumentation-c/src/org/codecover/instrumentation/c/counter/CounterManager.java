package org.codecover.instrumentation.c.counter;

import java.util.UUID;

public class CounterManager {
    final private String uuid = UUID.randomUUID().toString().replace('-','_');
    final private String stmtCntName = "CodeCover_StmtCnt" + uuid;

    private int StmtCounter;

    public String id() {
        return uuid;
    }

    public String stmtCntName() {
        return stmtCntName;
    }

    public int newStmtID() {
        return StmtCounter++;
    }

    public int getStmtCnt() {
        return StmtCounter;
    }
}
