package org.codecover.instrumentation.c;

import org.codecover.instrumentation.HierarchyLevelContainer;
import org.codecover.instrumentation.c.manipulators.StatementManipulator;
import org.codecover.instrumentation.c.syntaxtree.*;
import org.codecover.instrumentation.c.syntaxtree.Statement;
import org.codecover.model.MASTBuilder;
import org.codecover.model.mast.*;

import java.io.Writer;
import java.util.*;

public class InstrumentationVisitor extends SimpleTreeDumper {
    private StatementManipulator statementManipulator;

    public InstrumentationVisitor(Writer writer, StatementManipulator statementManipulator) {
        super(writer);
        this.statementManipulator = statementManipulator;
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
        n.f0.accept(this);
        if(n.f1.f1.f0.which == 0) {
            String name = ((NodeToken) n.f1.f1.f0.choice).tokenImage;
            isMain = "main".equals(name);
        }
        n.f1.accept(this);
        n.f2.accept(this);
        n.f3.accept(this);
        isMain = false;
    }

    @Override
    public void visit(CompoundStatement n) {
        boolean tmp = isMain;
        isMain = false;
        if(tmp) {
            out.println("{");
            out.println("CodeCover_reset();");
            out.println("atexit(CodeCover_dump);");
        }
        super.visit(n);
        if(tmp)
            out.println("}");

    }

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
