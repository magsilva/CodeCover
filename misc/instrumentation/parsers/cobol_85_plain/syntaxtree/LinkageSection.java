//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.cobol85.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * f0 -> &lt;LINKAGE&gt;
 * f1 -> &lt;SECTION&gt;
 * f2 -> &lt;DOT&gt;
 * f3 -> ( DataDescriptionEntry() )*
 * </PRE>
 */
public class LinkageSection implements Node {
   private Node parent;
   public NodeToken f0;
   public NodeToken f1;
   public NodeToken f2;
   public NodeListOptional f3;

   public LinkageSection(NodeToken n0, NodeToken n1, NodeToken n2, NodeListOptional n3) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);
      f1 = n1;
      if ( f1 != null ) f1.setParent(this);
      f2 = n2;
      if ( f2 != null ) f2.setParent(this);
      f3 = n3;
      if ( f3 != null ) f3.setParent(this);
   }

   public LinkageSection(NodeToken n0, NodeListOptional n1) {
      f0 = new NodeToken("linkage");
      if ( f0 != null ) f0.setParent(this);
      f1 = new NodeToken("section");
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

