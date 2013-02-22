package org.codecover.instrumentation.c;

import org.codecover.instrumentation.booleanterms.InstrBasicBooleanTerm;
import org.codecover.instrumentation.booleanterms.InstrBooleanTerm;
import org.codecover.instrumentation.booleanterms.InstrOperatorTerm;
import org.codecover.instrumentation.c.syntaxtree.*;
import org.codecover.instrumentation.c.visitor.GJNoArguDepthFirst;
import org.codecover.instrumentation.c.visitor.GJNoArguVisitor;

public class CExpressionParser extends GJNoArguDepthFirst<InstrBooleanTerm> implements GJNoArguVisitor<InstrBooleanTerm> {
    public InstrBooleanTerm parse(Expression n) {
        return n.accept(this);
    }

    @Override
    public InstrBooleanTerm visit(Expression n) {
        // No comma operator
        if(!n.nodeListOptional.present())
            return n.assignmentExpression.accept(this);

        // If there is a list of expressions separated by commas, the return value
        // of the list is the return value of the last expression
        AssignmentExpression lastExpression = (AssignmentExpression) n.nodeListOptional.elementAt(n.nodeListOptional.size() - 1);

        return lastExpression.accept(this);
    }

    @Override
    public InstrBooleanTerm visit(AssignmentExpression n) {
        // Actually an assignment expression
        if(n.nodeChoice.which == 0) {
            return InstrBasicBooleanVisitor.convertToInstrBasicBoolean(n);
        }
        // Conditional Expression
        else {
            return n.nodeChoice.choice.accept(this);
        }
    }

    @Override
    public InstrBooleanTerm visit(ConditionalExpression n) {
        // Actually an conditional expression
        if(n.nodeOptional.present()) {
            // Give up TODO: fix
            return InstrBasicBooleanVisitor.convertToInstrBasicBoolean(n);
        } else {
            return n.logicalORExpression.accept(this);
        }
    }

    @Override
    public InstrBooleanTerm visit(LogicalORExpression n) {
        InstrBooleanTerm returnTerm = n.logicalANDExpression.accept(this);

        if(n.nodeOptional.present()) {
            NodeSequence sequence = (NodeSequence) n.nodeOptional.node;
            NodeToken operatorToken = (NodeToken) sequence.elementAt(0);
            InstrBooleanTerm term = sequence.elementAt(1).accept(this);
            returnTerm = new InstrOperatorTerm(returnTerm, CBooleanExpressions.orOperator, term,
                    operatorToken.beginOffset, operatorToken.endOffset);
        }

        return returnTerm;
    }

    @Override
    public InstrBooleanTerm visit(LogicalANDExpression n) {
        InstrBooleanTerm returnTerm = n.inclusiveORExpression.accept(this);

        if(n.nodeOptional.present()) {
            NodeSequence sequence = (NodeSequence) n.nodeOptional.node;
            NodeToken operatorToken = (NodeToken) sequence.elementAt(0);
            InstrBooleanTerm term = sequence.elementAt(1).accept(this);
            returnTerm = new InstrOperatorTerm(returnTerm, CBooleanExpressions.andOperator, term,
                    operatorToken.beginOffset, operatorToken.endOffset);
        }

        return returnTerm;
    }

    @Override
    public InstrBooleanTerm visit(InclusiveORExpression n) {
        if(n.nodeOptional.present()) {
            // Give up TODO: fix
            return InstrBasicBooleanVisitor.convertToInstrBasicBoolean(n);
        } else {
            return n.exclusiveORExpression.accept(this);
        }
    }

    @Override
    public InstrBooleanTerm visit(ExclusiveORExpression n) {
        if(n.nodeOptional.present()) {
            // Give up TODO: fix
            return InstrBasicBooleanVisitor.convertToInstrBasicBoolean(n);
        } else {
            return n.aNDExpression.accept(this);
        }
    }

    @Override
    public InstrBooleanTerm visit(ANDExpression n) {
        if(n.nodeOptional.present()) {
            // Give up TODO: fix
            return InstrBasicBooleanVisitor.convertToInstrBasicBoolean(n);
        } else {
            return n.equalityExpression.accept(this);
        }
    }

    @Override
    public InstrBooleanTerm visit(EqualityExpression n) {
        if(n.nodeOptional.present()) {
            // Give up TODO: fix
            return InstrBasicBooleanVisitor.convertToInstrBasicBoolean(n);
        } else {
            return n.relationalExpression.accept(this);
        }
    }

    @Override
    public InstrBooleanTerm visit(RelationalExpression n) {
        if(n.nodeOptional.present()) {
            // Give up TODO: fix
            return InstrBasicBooleanVisitor.convertToInstrBasicBoolean(n);
        } else {
            return n.shiftExpression.accept(this);
        }
    }

    @Override
    public InstrBooleanTerm visit(ShiftExpression n) {
        if(n.nodeOptional.present()) {
            // Give up TODO: fix
            return InstrBasicBooleanVisitor.convertToInstrBasicBoolean(n);
        } else {
            return n.additiveExpression.accept(this);
        }
    }

    @Override
    public InstrBooleanTerm visit(AdditiveExpression n) {
        if(n.nodeOptional.present()) {
            // Give up TODO: fix
            return InstrBasicBooleanVisitor.convertToInstrBasicBoolean(n);
        } else {
            return n.multiplicativeExpression.accept(this);
        }
    }

    @Override
    public InstrBooleanTerm visit(MultiplicativeExpression n) {
        if(n.nodeOptional.present()) {
            // Give up TODO: fix
            return InstrBasicBooleanVisitor.convertToInstrBasicBoolean(n);
        } else {
            return n.castExpression.accept(this);
        }
    }

    @Override
    public InstrBooleanTerm visit(CastExpression n) {
        // when this expression is casting
        if(n.nodeChoice.which == 0) {
            // Give up TODO: fix
            return InstrBasicBooleanVisitor.convertToInstrBasicBoolean(n);
        } else {
            return n.nodeChoice.choice.accept(this);
        }
    }

    @Override
    public InstrBooleanTerm visit(UnaryExpression n) {
        // Postfix Expression
        if(n.nodeChoice.which == 0) {
            return n.nodeChoice.choice.accept(this);
        } else if(n.nodeChoice.which == 3) {
            NodeSequence sequence = (NodeSequence) n.nodeChoice.choice;
            UnaryOperator op = (UnaryOperator) sequence.elementAt(0);
            NodeToken operatorToken = (NodeToken) op.nodeChoice.choice;
            return new InstrOperatorTerm(CBooleanExpressions.notOperator,
                    sequence.elementAt(1).accept(this),
                    operatorToken.beginOffset, operatorToken.endOffset);
        } else {
            // Give up TODO: fix
            return InstrBasicBooleanVisitor.convertToInstrBasicBoolean(n);
        }
    }

    @Override
    public InstrBooleanTerm visit(PostfixExpression n) {
        // Compound literal
        if(n.nodeChoice.which == 0) {
            return InstrBasicBooleanVisitor.convertToInstrBasicBoolean(n);
        } else {
            NodeSequence sequence = (NodeSequence) n.nodeChoice.choice;
            PrimaryExpression exp = (PrimaryExpression) sequence.elementAt(0);
            NodeListOptional list = (NodeListOptional) sequence.elementAt(1);
            if(list.present()) {
                // Give up TODO: fix
                return InstrBasicBooleanVisitor.convertToInstrBasicBoolean(n);
            } else {
                return exp.accept(this);
            }
        }
    }

    @Override
    public InstrBooleanTerm visit(PrimaryExpression n) {
        // Generic Selection
        if(n.nodeChoice.which == 1) {
            return InstrBasicBooleanVisitor.convertToInstrBasicBoolean(n);
        } else {
            NodeChoice choice = (NodeChoice) n.nodeChoice.choice;
            if(choice.which == 1) {
                // Nested Expression
                NodeSequence seq = (NodeSequence) choice.choice;
                return seq.elementAt(1).accept(this);
            } else if () {
                // TODO convert 0 and numbers > 1 to boolean terms
            } else {
                return InstrBasicBooleanVisitor.convertToInstrBasicBoolean(n);
            }
        }
    }
}
