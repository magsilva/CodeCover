//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.c.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * declarator -> Declarator()
 * nodeOptional -> [ "=" Initializer() ]
 * </PRE>
 */
public class InitDeclarator extends org.codecover.instrumentation.c.adapter.CCNode implements Node {
   private Node parent;
   public Declarator declarator;
   public NodeOptional nodeOptional;

   public InitDeclarator(Declarator n0, NodeOptional n1) {
      declarator = n0;
      if ( declarator != null ) declarator.setParent(this);
      nodeOptional = n1;
      if ( nodeOptional != null ) nodeOptional.setParent(this);
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
   public void setParent(Node n) { parent = n; }
   public Node getParent()       { return parent; }
}

