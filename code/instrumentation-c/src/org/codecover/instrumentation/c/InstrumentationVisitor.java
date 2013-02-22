package org.codecover.instrumentation.c;

import org.codecover.instrumentation.c.manipulators.BranchManipulator;
import org.codecover.instrumentation.c.manipulators.LoopManipulator;
import org.codecover.instrumentation.c.manipulators.StatementManipulator;
import org.codecover.instrumentation.c.syntaxtree.*;
import org.codecover.instrumentation.c.syntaxtree.Statement;

import java.io.Writer;

public class InstrumentationVisitor extends SimpleTreeDumper {
    private StatementManipulator statementManipulator;
    private BranchManipulator branchManipulator;
    private LoopManipulator loopManipulator;

    public InstrumentationVisitor(Writer writer,
                                  StatementManipulator statementManipulator,
                                  BranchManipulator branchManipulator,
                                  LoopManipulator loopManipulator) {
        super(writer);
        this.statementManipulator = statementManipulator;
        this.branchManipulator = branchManipulator;
        this.loopManipulator = loopManipulator;
    }

    @Override
    public void visit(TranslationUnit n) {
        out.println("void CodeCover_reset();");
        out.println("void CodeCover_dump();");
        statementManipulator.writeForwardDeclaration(out);
        branchManipulator.writeForwardDeclaration(out);
        loopManipulator.writeForwardDeclaration(out);
        super.visit(n);
    }


    public void visit(FunctionDefinition n) {
        boolean isMain = "main".equals(Helper.findFunctionName(n));

        n.nodeOptional.accept(this);
        n.declarator.accept(this);
        n.nodeOptional1.accept(this);
        if(isMain) {
            out.println("{");
            // Not need because the arrays have a static storage duration, which means that they are initialized to 0.
            //out.println("CodeCover_reset();");
            out.println("atexit(CodeCover_dump);");
        }
        n.compoundStatement.accept(this);
        if(isMain)
            out.println("}");
    }

    @Override
    public void visit(Statement n) {
        // Don't instrument labeled and compound statements
        if(n.nodeChoice.which > 1)
            statementManipulator.visit(out, n);
        super.visit(n);
    }

    @Override
    public void visit(IfStatement n) {
        n.nodeToken.accept(this);
        n.nodeToken1.accept(this);
        n.expression.accept(this);
        n.nodeToken2.accept(this);
        out.println("{");
        branchManipulator.visit(out, n);
        n.statement.accept(this);
        out.println("} else {");
        branchManipulator.visitElse(out, n);
        if ( n.nodeOptional.present() ) {
            // skip the "else" and visit the else body directly
            ((NodeSequence)n.nodeOptional.node).elementAt(1).accept(this);
        }
        out.println("}");
    }

    @Override
    public void visit(SwitchStatement n) {
        n.nodeToken.accept(this);
        n.nodeToken1.accept(this);
        n.expression.accept(this);
        n.nodeToken2.accept(this);
        out.println("{");
        // Put the default to the beginning so that it doesn't get executed by mistake
        if(n.branchNum != -1) {
            out.print("default:");
            branchManipulator.visit(out, n);
        }
        n.statement.accept(this);
        // We need an implicit default branch
        out.println("}");
    }

    @Override
    public void visit(CaseStatement n) {
        n.nodeToken.accept(this);
        n.constantExpression.accept(this);
        n.nodeToken1.accept(this);
        branchManipulator.visit(out, n);
        n.statement.accept(this);
    }

    @Override
    public void visit(DefaultStatement n) {
        n.nodeToken.accept(this);
        n.nodeToken1.accept(this);
        branchManipulator.visit(out, n);
        n.statement.accept(this);
    }

    @Override
    public void visit(WhileStatement n) {
        loopManipulator.visitBefore(out, n);
        n.nodeToken.accept(this);
        n.nodeToken1.accept(this);
        n.expression.accept(this);
        n.nodeToken2.accept(this);
        out.println("{");
        loopManipulator.visit(out, n);
        n.statement.accept(this);
        out.println("}");
        loopManipulator.visitAfter(out, n);
    }

    @Override
    public void visit(DoStatement n) {
        loopManipulator.visitBefore(out, n);
        n.nodeToken.accept(this);
        out.println("{");
        loopManipulator.visit(out, n);
        n.statement.accept(this);
        out.println("}");
        n.nodeToken1.accept(this);
        n.nodeToken2.accept(this);
        n.expression.accept(this);
        n.nodeToken3.accept(this);
        n.nodeToken4.accept(this);
        loopManipulator.visitAfter(out, n);
    }

    @Override
    public void visit(ForStatement n) {
        loopManipulator.visitBefore(out, n);
        n.nodeToken.accept(this);
        n.nodeToken1.accept(this);
        n.nodeOptional.accept(this);
        n.nodeToken2.accept(this);
        n.nodeOptional1.accept(this);
        n.nodeToken3.accept(this);
        n.nodeOptional2.accept(this);
        n.nodeToken4.accept(this);
        out.println("{");
        loopManipulator.visit(out, n);
        n.statement.accept(this);
        out.println("}");
        loopManipulator.visitAfter(out, n);
    }
}
