package org.codecover.instrumentation.c;

import org.codecover.model.MASTBuilder;
import org.codecover.model.mast.HierarchyLevelType;

public class HierachyLevelTypes {
    private static final String SOURCE_FILE_INTERN = "sourceFile";
    private static final String SOURCE_FILE = "C source file";
    private static final String PROGRAM_INTERN = "program";
    private static final String PROGRAM = "program";
    private static HierarchyLevelType sourceFile = null;
    private static HierarchyLevelType program = null;

    public static HierarchyLevelType getSourceFileType(MASTBuilder builder) {
        if (sourceFile == null) {
            return sourceFile = builder.createHierarchyLevelType(SOURCE_FILE,
                    SOURCE_FILE_INTERN);
        }
        return sourceFile;
    }

    public static HierarchyLevelType getProgramType(MASTBuilder builder) {
        if (program == null) {
            return program = builder.createHierarchyLevelType(PROGRAM,
                    PROGRAM_INTERN);
        }
        return program;
    }
}
