//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.cobol85.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * f0 -> &lt;SEGMENT&gt;
 * f1 -> &lt;LIMIT&gt;
 * f2 -> [ &lt;IS&gt; ]
 * f3 -> IntegerConstant()
 * </PRE>
 */
public class SegmentLimitClause implements Node {
   private Node parent;
   public NodeToken f0;
   public NodeToken f1;
   public NodeOptional f2;
   public IntegerConstant f3;

   public SegmentLimitClause(NodeToken n0, NodeToken n1, NodeOptional n2, IntegerConstant n3) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);
      f1 = n1;
      if ( f1 != null ) f1.setParent(this);
      f2 = n2;
      if ( f2 != null ) f2.setParent(this);
      f3 = n3;
      if ( f3 != null ) f3.setParent(this);
   }

   public SegmentLimitClause(NodeOptional n0, IntegerConstant n1) {
      f0 = new NodeToken("segment");
      if ( f0 != null ) f0.setParent(this);
      f1 = new NodeToken("limit");
      if ( f1 != null ) f1.setParent(this);
      f2 = n0;
      if ( f2 != null ) f2.setParent(this);
      f3 = n1;
      if ( f3 != null ) f3.setParent(this);
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

