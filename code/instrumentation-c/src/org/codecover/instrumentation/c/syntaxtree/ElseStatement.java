//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.c.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * nodeToken -> &lt;ELSE&gt;
 * statement -> Statement()
 * </PRE>
 */
public class ElseStatement extends org.codecover.instrumentation.c.adapter.CCNode implements Node {
   private Node parent;
   public NodeToken nodeToken;
   public Statement statement;

   public ElseStatement(NodeToken n0, Statement n1) {
      nodeToken = n0;
      if ( nodeToken != null ) nodeToken.setParent(this);
      statement = n1;
      if ( statement != null ) statement.setParent(this);
   }

   public ElseStatement(Statement n0) {
      nodeToken = new NodeToken("else");
      if ( nodeToken != null ) nodeToken.setParent(this);
      statement = n0;
      if ( statement != null ) statement.setParent(this);
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

