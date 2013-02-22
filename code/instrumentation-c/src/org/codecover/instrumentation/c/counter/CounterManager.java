package org.codecover.instrumentation.c.counter;

public class CounterManager {
    final private String id;
    final private String stmtVarName;
    final private String stmtPrefix;
    final private String fileName;

    private int StmtCounter;

    /**
     * @param id must be a positive integer
     * @param fileName
     */
    public CounterManager(String id, String fileName) {
        this.id = id;
        this.fileName = fileName;
        stmtPrefix = "S" + id + "-";
        stmtVarName = "CodeCover_S" + id;
    }

    public String id() {
        return id;
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
