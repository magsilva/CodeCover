//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.cobol85.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * f0 -> [ &lt;WITH&gt; ]
 * f1 -> &lt;TEST&gt;
 * f2 -> ( &lt;BEFORE&gt; | &lt;AFTER&gt; )
 * </PRE>
 */
public class PerformTestPositionClause implements Node {
   private Node parent;
   public NodeOptional f0;
   public NodeToken f1;
   public NodeChoice f2;

   public PerformTestPositionClause(NodeOptional n0, NodeToken n1, NodeChoice n2) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);
      f1 = n1;
      if ( f1 != null ) f1.setParent(this);
      f2 = n2;
      if ( f2 != null ) f2.setParent(this);
   }

   public PerformTestPositionClause(NodeOptional n0, NodeChoice n1) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);
      f1 = new NodeToken("test");
      if ( f1 != null ) f1.setParent(this);
      f2 = n1;
      if ( f2 != null ) f2.setParent(this);
   }

   public void accept(org.codecover.instrumentation.cobol85.visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(org.codecover.instrumentation.cobol85.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(org.codecover.instrumentation.cobol85.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(org.codecover.instrumentation.cobol85.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
   public void setParent(Node n) { parent = n; }
   public Node getParent()       { return parent; }
}

