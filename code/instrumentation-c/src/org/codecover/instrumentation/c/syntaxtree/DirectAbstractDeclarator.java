//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.c.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * nodeChoice -> ( "(" AbstractDeclarator() ")" | "[" [ ConstantExpression() ] "]" | "(" [ ParameterTypeList() ] ")" )
 * nodeListOptional -> ( "[" [ ConstantExpression() ] "]" | "(" [ ParameterTypeList() ] ")" )*
 * </PRE>
 */
public class DirectAbstractDeclarator extends org.codecover.instrumentation.c.adapter.CCNode implements Node {
   private Node parent;
   public NodeChoice nodeChoice;
   public NodeListOptional nodeListOptional;

   public DirectAbstractDeclarator(NodeChoice n0, NodeListOptional n1) {
      nodeChoice = n0;
      if ( nodeChoice != null ) nodeChoice.setParent(this);
      nodeListOptional = n1;
      if ( nodeListOptional != null ) nodeListOptional.setParent(this);
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

