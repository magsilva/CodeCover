//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.c.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * f0 -> ( "=" | &lt;MULT_EQ: "*="&gt; | &lt;DIV_EQ: "/="&gt; | &lt;MOD_EQ: "%="&gt; | &lt;PLUS_EQ: "+="&gt; | &lt;SUB_EQ: "-="&gt; | &lt;LSH_EQ: "&lt;&lt;="&gt; | &lt;RSH_EQ: "&gt;&gt;="&gt; | &lt;AND_EQ: "&="&gt; | &lt;XOR_EQ: "^="&gt; | &lt;OR_EQ: "|="&gt; )
 * </PRE>
 */
public class AssignmentOperator implements Node {
   private Node parent;
   public NodeChoice f0;

   public AssignmentOperator(NodeChoice n0) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);
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
