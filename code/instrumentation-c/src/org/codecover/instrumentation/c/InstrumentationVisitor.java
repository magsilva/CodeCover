package org.codecover.instrumentation.c;

import org.codecover.instrumentation.HierarchyLevelContainer;
import org.codecover.instrumentation.c.manipulators.StatementManipulator;
import org.codecover.instrumentation.c.syntaxtree.*;
import org.codecover.model.MASTBuilder;
import org.codecover.model.mast.SourceFile;

import java.io.Writer;

public class InstrumentationVisitor extends SimpleTreeDumper {
    private MASTBuilder builder;
    private SourceFile sourceFile;
    private HierarchyLevelContainer hierarchyLevelContainer;
    private String testSessionContainerUID;
    private StatementManipulator statementManipulator;

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

    public void setStatementManipulator(StatementManipulator statementManipulator) {
        this.statementManipulator = statementManipulator;
    }

    @Override
    public void visit(TranslationUnit n) {
        statementManipulator.writeForwardDeclaration(out);
        super.visit(n);
        statementManipulator.writeDefinition(out);
    }

    boolean addBraces = false;

    @Override
    public void visit(Statement n) {
        // After addBraces and no compound statement
        boolean tmp = addBraces && (n.f0.which != 2);
        addBraces = false;
        if(tmp)
            out.append("{\n");
        super.visit(n);
        if(tmp)
            out.append("}\n");
    }

    @Override
    public void visit(ExpressionStatement n) {
        statementManipulator.visit(out, n);
        super.visit(n);
    }

    @Override
    public void visit(JumpStatement n) {
        statementManipulator.visit(out, n);
        super.visit(n);
    }

    @Override
    public void visit(SelectionStatement n) {
        statementManipulator.visit(out, n);
        addBraces = true;
        super.visit(n);
    }

    @Override
    public void visit(ElseStatement n) {
        addBraces = true;
        super.visit(n);
    }

    @Override
    public void visit(IterationStatement n) {
        statementManipulator.visit(out, n);
        addBraces = true;
        super.visit(n);
    }
}
