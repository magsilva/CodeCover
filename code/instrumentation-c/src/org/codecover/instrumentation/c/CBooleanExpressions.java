package org.codecover.instrumentation.c;

import org.codecover.instrumentation.booleanterms.InstrBooleanOperator;
import org.codecover.model.mast.BooleanAssignment;
import static org.codecover.model.mast.BooleanResult.FALSE;
import static org.codecover.model.mast.BooleanResult.TRUE;

import java.util.HashMap;

public class CBooleanExpressions {
    public final static InstrBooleanOperator orOperator;
    public final static InstrBooleanOperator andOperator;
    public final static InstrBooleanOperator notOperator;

    static {
        HashMap<BooleanAssignment, Boolean> possibleAssignments =
                new HashMap<BooleanAssignment, Boolean>();

        possibleAssignments.put(new BooleanAssignment(FALSE, FALSE),
                Boolean.FALSE);

        possibleAssignments.put(new BooleanAssignment(FALSE, TRUE),
                Boolean.TRUE);

        possibleAssignments.put(new BooleanAssignment(TRUE, FALSE),
                Boolean.TRUE);

        possibleAssignments.put(new BooleanAssignment(TRUE, TRUE),
                Boolean.TRUE);

        orOperator = InstrBooleanOperator.getTwoArgumentOperator(
                "OR", "||", possibleAssignments);
    }

    static {
        HashMap<BooleanAssignment, Boolean> possibleAssignments =
                new HashMap<BooleanAssignment, Boolean>();

        possibleAssignments.put(new BooleanAssignment(FALSE, FALSE),
                Boolean.FALSE);

        possibleAssignments.put(new BooleanAssignment(FALSE, TRUE),
                Boolean.FALSE);

        possibleAssignments.put(new BooleanAssignment(TRUE, FALSE),
                Boolean.FALSE);

        possibleAssignments.put(new BooleanAssignment(TRUE, TRUE),
                Boolean.TRUE);

        andOperator = InstrBooleanOperator.getTwoArgumentOperator(
                "AND", "&&", possibleAssignments);
    }

    static {
        HashMap<BooleanAssignment, Boolean> possibleAssignments =
                new HashMap<BooleanAssignment, Boolean>();

        possibleAssignments.put(new BooleanAssignment(FALSE),
                Boolean.TRUE);

        possibleAssignments.put(new BooleanAssignment(TRUE),
                Boolean.FALSE);

        notOperator = InstrBooleanOperator.getOneArgumentOperator(
                "NOT", "!", false, possibleAssignments);
    }
}
