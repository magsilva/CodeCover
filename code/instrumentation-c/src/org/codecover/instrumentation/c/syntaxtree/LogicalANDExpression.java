//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.c.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * inclusiveORExpression -> InclusiveORExpression()
 * nodeOptional -> [ "&&" LogicalANDExpression() ]
 * </PRE>
 */
public class LogicalANDExpression extends org.codecover.instrumentation.c.adapter.CCNode implements Node {
   public InclusiveORExpression inclusiveORExpression;
   public NodeOptional nodeOptional;

   public LogicalANDExpression(InclusiveORExpression n0, NodeOptional n1) {
      inclusiveORExpression = n0;
      nodeOptional = n1;
   }

   public void accept(org.codecover.instrumentation.c.visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(org.codecover.instrumentation.c.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(org.codecover.instrumentation.c.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(org.codecover.instrumentation.c.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
}

