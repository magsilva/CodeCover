package org.codecover.instrumentation.c.counter;

public class CounterManager {
    final private String id;
    final private String fileName;
    final private String stmtVarName;
    final private String stmtPrefix;
    final private String branchVarName;
    final private String branchPrefix;
    private final String loopTmpName;
    final private String loopVarName;
    final private String loopPrefix;
    private final String condVarName;
    private final String condPrefix;

    private int stmtCounter;
    private int branchCounter;
    private int loopCounter;
    private int condCounter;

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
        loopPrefix = "L" + id + "-";
        loopVarName = "CodeCover_L" + id;
        loopTmpName = "CodeCover_LTMP" + id;
        condPrefix = "C" + id + "-";
        condVarName = "CodeCover_C" + id;
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

    public String loopVarName() {
        return loopVarName;
    }

    public String loopPrefix() {
        return loopPrefix;
    }

    public String loopID(int i) {
        return loopPrefix + Integer.toString(i);
    }

    public String loopTmpName() {
        return loopTmpName;
    }

    public int getloopTmpCnt() {
        return loopCounter;
    }

    public int newloopID() {
        return loopCounter++*3;
    }

    public int getloopCnt() {
        return loopCounter*3;
    }

    public String condVarName() {
        return condVarName;
    }

    public String condPrefix() {
        return condPrefix;
    }

    public int newCondID() {
        return condCounter++;
    }

    public int getCondCnt() {
        return condCounter;
    }

    public String condID(int i) {
        return condPrefix + Integer.toString(i);
    }
}
