package org.codecover.instrumentation.c;

import org.codecover.instrumentation.c.manipulators.BranchManipulator;
import org.codecover.instrumentation.c.manipulators.StatementManipulator;
import org.codecover.instrumentation.c.syntaxtree.*;
import org.codecover.instrumentation.c.syntaxtree.Statement;

import java.io.Writer;

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


    public void visit(FunctionDefinition n) {
        boolean isMain = "main".equals(Helper.findFunctionName(n));

        n.nodeOptional.accept(this);
        n.declarator.accept(this);
        n.nodeOptional1.accept(this);
        if(isMain) {
            out.println("{");
            out.println("/*CodeCover_reset(); Not need because the arrays have a static storarage duration, which means that they are initialized to 0. */");
            out.println("atexit(CodeCover_dump);");
        }
        n.compoundStatement.accept(this);
        if(isMain)
            out.println("}");
    }

    @Override
    public void visit(Statement n) {
        if(n.nodeChoice.which > 1)
            statementManipulator.visit(out, n);
        super.visit(n);
    }

    @Override
    public void visit(IfStatement n) {
        boolean braces = !(n.statement.nodeChoice.choice instanceof CompoundStatement);

        n.nodeToken.accept(this);
        n.nodeToken1.accept(this);
        n.expression.accept(this);
        n.nodeToken2.accept(this);
        if(braces)
            out.println("{");
        n.statement.accept(this);
        if(braces)
            out.println("}");
        n.nodeOptional.accept(this);
    }

    @Override
    public void visit(ElseStatement n) {
        boolean braces = !(n.statement.nodeChoice.choice instanceof CompoundStatement);

        n.nodeToken.accept(this);
        if(braces)
            out.println("{");
        n.statement.accept(this);
        if(braces)
            out.println("}");
    }

    @Override
    public void visit(SwitchStatement n) {
        boolean braces = !(n.statement.nodeChoice.choice instanceof CompoundStatement);

        n.nodeToken.accept(this);
        n.nodeToken1.accept(this);
        n.expression.accept(this);
        n.nodeToken2.accept(this);
        if(braces)
            out.println("{");
        n.statement.accept(this);
        if(braces)
            out.println("}");
    }

    @Override
    public void visit(WhileStatement n) {
        boolean braces = !(n.statement.nodeChoice.choice instanceof CompoundStatement);

        n.nodeToken.accept(this);
        n.nodeToken1.accept(this);
        n.expression.accept(this);
        n.nodeToken2.accept(this);
        if(braces)
            out.println("{");
        n.statement.accept(this);
        if(braces)
            out.println("}");
    }

    @Override
    public void visit(DoStatement n) {
        boolean braces = !(n.statement.nodeChoice.choice instanceof CompoundStatement);

        n.nodeToken.accept(this);
        if(braces)
            out.println("{");
        n.statement.accept(this);
        if(braces)
            out.println("}");
        n.nodeToken1.accept(this);
        n.nodeToken2.accept(this);
        n.expression.accept(this);
        n.nodeToken3.accept(this);
        n.nodeToken4.accept(this);
    }

    @Override
    public void visit(ForStatement n) {
        boolean braces = !(n.statement.nodeChoice.choice instanceof CompoundStatement);

        n.nodeToken.accept(this);
        n.nodeToken1.accept(this);
        n.nodeOptional.accept(this);
        n.nodeToken2.accept(this);
        n.nodeOptional1.accept(this);
        n.nodeToken3.accept(this);
        n.nodeOptional2.accept(this);
        n.nodeToken4.accept(this);
        if(braces)
            out.println("{");
        n.statement.accept(this);
        if(braces)
            out.println("}");
    }
}
