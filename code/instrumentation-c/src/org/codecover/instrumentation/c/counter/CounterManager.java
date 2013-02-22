package org.codecover.instrumentation.c.counter;

import java.util.UUID;

public class CounterManager {
    final private UUID id = UUID.randomUUID();
    final private String uuid = UUID.randomUUID().toString().replace('-','_');
    final private String stmtVarName = "CodeCover_S" + uuid;
    final private String stmtPrefix = String.format("S%d-", id().hashCode());
    final private String fileName;

    private int StmtCounter;

    public CounterManager(String fileName) {
        this.fileName = fileName;
    }

    public String id() {
        return uuid;
    }

    public String stmtVarName() {
        return stmtVarName;
    }

    public String stmtPrefix() {
        return stmtPrefix;
    }

    public int newStmtID() {
        return StmtCounter++;
    }

    public int getStmtCnt() {
        return StmtCounter;
    }

    public String getFileName() {
        return fileName;
    }
}
