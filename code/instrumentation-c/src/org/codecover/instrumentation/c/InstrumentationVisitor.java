package org.codecover.instrumentation.c;

import org.codecover.instrumentation.HierarchyLevelContainer;
import org.codecover.instrumentation.c.manipulators.BranchManipulator;
import org.codecover.instrumentation.c.manipulators.StatementManipulator;
import org.codecover.instrumentation.c.syntaxtree.*;
import org.codecover.instrumentation.c.syntaxtree.Statement;
import org.codecover.model.MASTBuilder;
import org.codecover.model.mast.*;

import java.io.Writer;
import java.util.*;

public class InstrumentationVisitor extends SimpleTreeDumper {
    private StatementManipulator statementManipulator;
    private BranchManipulator branchManipulator;

    public InstrumentationVisitor(Writer writer,
                                  StatementManipulator statementManipulator,
                                  BranchManipulator branchManipulator) {
        super(writer);
        this.statementManipulator = statementManipulator;
        this.branchManipulator = branchManipulator;
    }

    @Override
    public void visit(TranslationUnit n) {
        out.println("void CodeCover_reset();");
        out.println("void CodeCover_dump();");
        statementManipulator.writeForwardDeclaration(out);
        super.visit(n);
    }

    boolean addBraces = false;
    boolean isMain = false;

    public void visit(FunctionDefinition n) {
        n.nodeOptional.accept(this);
        isMain = "main".equals(Helper.findFunctionName(n));
        n.declarator.accept(this);
        n.nodeOptional1.accept(this);
        n.compoundStatement.accept(this);
        isMain = false;
    }

    @Override
    public void visit(CompoundStatement n) {
        boolean tmp = isMain;
        isMain = false;
        if(tmp) {
            out.println("{");
            out.println("/*CodeCover_reset(); Not need because the arrays have a static storarage duration, which means that they are initialized to 0. */");
            out.println("atexit(CodeCover_dump);");
        }
        super.visit(n);
        if(tmp)
            out.println("}");

    }

    @Override
    public void visit(Statement n) {
        // After addBraces and no compound statement
        boolean tmp = addBraces && (n.nodeChoice.which != 2);
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
        n.nodeSequence.elementAt(0).accept(this);
        Statement s = (Statement) n.nodeSequence.elementAt(1);
        boolean braces = !(s.nodeChoice.choice instanceof CompoundStatement);

        if(braces)
            out.println("{");

        s.accept(this);
        if(braces)
            out.println("}");
    }

    @Override
    public void visit(IterationStatement n) {
        statementManipulator.visit(out, n);
        addBraces = true;
        n.accept(this);
    }
}
