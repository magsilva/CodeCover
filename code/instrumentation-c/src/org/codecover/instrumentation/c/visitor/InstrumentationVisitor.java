package org.codecover.instrumentation.c.visitor;

import org.codecover.instrumentation.HierarchyLevelContainer;
import org.codecover.model.MASTBuilder;
import org.codecover.model.mast.SourceFile;

import java.io.PrintWriter;
import java.io.Writer;

public class InstrumentationVisitor extends TreeDumper {
    private MASTBuilder builder;
    private SourceFile sourceFile;
    private HierarchyLevelContainer hierarchyLevelContainer;
    private String testSessionContainerUID;

    public InstrumentationVisitor(Writer writer,
                                  MASTBuilder builder,
                                  SourceFile sourceFile,
                                  HierarchyLevelContainer hierarchyLevelContainer,
                                  String testSessionContainerUID) {
        super(writer);
        this.builder = builder;
        this.sourceFile = sourceFile;
        this.hierarchyLevelContainer = hierarchyLevelContainer;
        this.testSessionContainerUID = testSessionContainerUID;
    }
}
