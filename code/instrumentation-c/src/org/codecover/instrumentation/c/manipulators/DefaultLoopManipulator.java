package org.codecover.instrumentation.c.manipulators;

import org.codecover.instrumentation.c.adapter.CCNode;
import org.codecover.instrumentation.c.counter.CounterManager;

import java.io.PrintWriter;

public class DefaultLoopManipulator implements LoopManipulator {
    private CounterManager cm;

    public DefaultLoopManipulator(CounterManager cm) {
        this.cm = cm;
    }

    @Override
    public void writeForwardDeclaration(PrintWriter out) {
        out.format("extern int %s[];\n", cm.loopVarName());
        out.format("extern int %s[];\n", cm.loopTmpName());
    }

    @Override
    public void visitBefore(PrintWriter out, CCNode n) {
        out.format("%s[%d]=0;\n", cm.loopTmpName(), n.loopNum);
    }

    @Override
    public void visit(PrintWriter out, CCNode n) {
        out.format("%s[%d]++;\n", cm.loopTmpName(), n.loopNum);
    }

    @Override
    public void visitAfter(PrintWriter out, CCNode n) {
        out.format("switch(%s[%d]) {\n", cm.loopTmpName(), n.loopNum);
        out.println("case 0:");
        out.format("%s[%d]++;\n", cm.loopVarName(), n.loopNum);
        out.println("break;");
        out.println("case 1:");
        out.format("%s[%d]++;\n", cm.loopVarName(), n.loopNum + 1);
        out.println("break;");
        out.println("default:");
        out.format("%s[%d]++;\n", cm.loopVarName(), n.loopNum + 2);
        out.println("break;");
        out.println("}");
    }
}
