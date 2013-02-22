package org.codecover.instrumentation.c.manipulators;

import org.codecover.instrumentation.booleanterms.InstrBooleanTerm;
import org.codecover.instrumentation.c.CExpressionParser;
import org.codecover.instrumentation.c.syntaxtree.Expression;

import java.io.PrintWriter;

public class DummyConditionManipulator implements ConditionManipulator {
    CExpressionParser expressionParser = new CExpressionParser();

    @Override
    public void writeForwardDeclaration(PrintWriter out) {
    }

    @Override
    public InstrBooleanTerm visit(PrintWriter out, Expression exp) {
        return expressionParser.visit(exp);
    }
}
