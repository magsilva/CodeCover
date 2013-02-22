//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.c.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * nodeToken -> &lt;STATICASSERT&gt;
 * nodeToken1 -> "("
 * constantExpression -> ConstantExpression()
 * nodeToken2 -> ","
 * stringLiteral -> StringLiteral()
 * nodeToken3 -> ")"
 * nodeToken4 -> ";"
 * </PRE>
 */
public class Static_AssertDeclaration extends org.codecover.instrumentation.c.adapter.CCNode implements Node {
   private Node parent;
   public NodeToken nodeToken;
   public NodeToken nodeToken1;
   public ConstantExpression constantExpression;
   public NodeToken nodeToken2;
   public StringLiteral stringLiteral;
   public NodeToken nodeToken3;
   public NodeToken nodeToken4;

   public Static_AssertDeclaration(NodeToken n0, NodeToken n1, ConstantExpression n2, NodeToken n3, StringLiteral n4, NodeToken n5, NodeToken n6) {
      nodeToken = n0;
      if ( nodeToken != null ) nodeToken.setParent(this);
      nodeToken1 = n1;
      if ( nodeToken1 != null ) nodeToken1.setParent(this);
      constantExpression = n2;
      if ( constantExpression != null ) constantExpression.setParent(this);
      nodeToken2 = n3;
      if ( nodeToken2 != null ) nodeToken2.setParent(this);
      stringLiteral = n4;
      if ( stringLiteral != null ) stringLiteral.setParent(this);
      nodeToken3 = n5;
      if ( nodeToken3 != null ) nodeToken3.setParent(this);
      nodeToken4 = n6;
      if ( nodeToken4 != null ) nodeToken4.setParent(this);
   }

   public Static_AssertDeclaration(ConstantExpression n0, StringLiteral n1) {
      nodeToken = new NodeToken("_Static_assert");
      if ( nodeToken != null ) nodeToken.setParent(this);
      nodeToken1 = new NodeToken("(");
      if ( nodeToken1 != null ) nodeToken1.setParent(this);
      constantExpression = n0;
      if ( constantExpression != null ) constantExpression.setParent(this);
      nodeToken2 = new NodeToken(",");
      if ( nodeToken2 != null ) nodeToken2.setParent(this);
      stringLiteral = n1;
      if ( stringLiteral != null ) stringLiteral.setParent(this);
      nodeToken3 = new NodeToken(")");
      if ( nodeToken3 != null ) nodeToken3.setParent(this);
      nodeToken4 = new NodeToken(";");
      if ( nodeToken4 != null ) nodeToken4.setParent(this);
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

