package org.codecover.instrumentation.c.manipulators;

import org.codecover.instrumentation.booleanterms.InstrBasicBooleanTerm;
import org.codecover.instrumentation.booleanterms.InstrBooleanTerm;
import org.codecover.instrumentation.booleanterms.InstrBracketTerm;
import org.codecover.instrumentation.booleanterms.InstrOperatorTerm;
import org.codecover.instrumentation.c.CBooleanExpressions;
import org.codecover.instrumentation.c.CExpressionParser;
import org.codecover.instrumentation.c.counter.CounterManager;
import org.codecover.instrumentation.c.syntaxtree.Expression;
import org.codecover.instrumentation.c.InstrCommaTerm;

import java.io.PrintWriter;
import java.util.ArrayList;

public class DefaultConditionManipulator implements ConditionManipulator {
    private CounterManager cm;

    int sizeofChar = 8;

    CExpressionParser expressionParser = new CExpressionParser();

    public DefaultConditionManipulator(CounterManager cm) {
        this.cm = cm;
    }

    @Override
    public void writeForwardDeclaration(PrintWriter out) {
        out.println("struct CCCond {};");
        out.println("void CCCondAdd(struct CCCond*, unsigned char[], int);");
        out.format("extern struct CCCond %s[];\n", cm.condVarName());
    }

    @Override
    public InstrBooleanTerm visit(PrintWriter out, Expression exp) {
        InstrBooleanTerm root = expressionParser.visit(exp);

        ArrayList<InstrBasicBooleanTerm> basicTerms = new ArrayList<InstrBasicBooleanTerm>();
        root.getAllBasicBooleanTerms(basicTerms);

        int ID = 0;
        String helper = cm.condVarName()  + "tmp" + ID;

        int numBytes = (basicTerms.size()*2 / 8 + 1);

        out.println("unsigned char " + helper + "[" + numBytes + "];");

        for(int i = 0;i < numBytes; i++) {
            out.println(helper + "[" + i + "] = 0;");
        }

        visitBasicTerms(helper, basicTerms);

        // We need 2 variables per term
        return addIncrementation(root, helper, cm.condVarName() + "[" + ID + "]", basicTerms.size() * 2);
    }

    private InstrBooleanTerm addIncrementation(InstrBooleanTerm root,
                                               String helper,
                                               String counter,
                                               int numValues) {
        InstrBooleanTerm write = new InstrBasicBooleanTerm(
                String.format("(CCCondAdd(&%s,%s,%d), 1)", counter, helper, numValues) ,-1 ,-1);

        return new InstrOperatorTerm(new InstrBracketTerm(root),
                CBooleanExpressions.andOperator, write, -1, -1);
    }

    private void visitBasicTerms(String helper, ArrayList<InstrBasicBooleanTerm> terms) {
        int i = 0;
        for(InstrBasicBooleanTerm term : terms) {
            term.modifyImage(String.format(
                    "(%1$s[%3$d] |= %2$d, (" + term.termToString() + ") && (%1$s[%5$d] |= %4$d))",
                    helper, 1<<i%sizeofChar, i++ / sizeofChar, 1<<i%sizeofChar, i++ / sizeofChar));
        }
    }
}
