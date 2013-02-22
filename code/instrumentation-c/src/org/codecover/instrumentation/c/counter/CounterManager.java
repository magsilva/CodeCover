package org.codecover.instrumentation.c.counter;

public class CounterManager {
    final private String id;
    final private String fileName;
    final private String stmtVarName;
    final private String stmtPrefix;
    final private String branchVarName;
    final private String branchPrefix;

    private int stmtCounter;
    private int branchCounter;

    /**
     * @param id must be a positive integer
     * @param fileName
     */
    public CounterManager(String id, String fileName) {
        this.id = id;
        this.fileName = fileName;
        stmtPrefix = "S" + id + "-";
        stmtVarName = "CodeCover_S" + id;
        branchPrefix = "B" + id + "-";
        branchVarName = "CodeCover_B" + id;
    }

    public String id() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String stmtVarName() {
        return stmtVarName;
    }

    public String stmtPrefix() {
        return stmtPrefix;
    }

    public String stmtID(int i) {
        return stmtPrefix + Integer.toString(i);
    }

    public int newStmtID() {
        return stmtCounter++;
    }

    public int getStmtCnt() {
        return stmtCounter;
    }

    public String branchVarName() {
        return branchVarName;
    }

    public String branchPrefix() {
        return branchPrefix;
    }

    public String branchID(int i) {
        return branchPrefix + Integer.toString(i);
    }

    public int newBranchID() {
        return branchCounter++;
    }

    public int getBranchCnt() {
        return branchCounter;
    }
}
