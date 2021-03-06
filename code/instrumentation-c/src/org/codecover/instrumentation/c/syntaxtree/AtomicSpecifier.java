//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.c.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * nodeToken -> &lt;ATOMIC&gt;
 * nodeToken1 -> "("TypedefName()
 * nodeToken2 -> ")"
 * </PRE>
 */
public class AtomicSpecifier extends org.codecover.instrumentation.c.adapter.CCNode implements Node {
   private Node parent;
   public NodeToken nodeToken;
   public NodeToken nodeToken1;
   public TypedefName typedefName;
   public NodeToken nodeToken2;

   public AtomicSpecifier(NodeToken n0, NodeToken n1, TypedefName n2, NodeToken n3) {
      nodeToken = n0;
      if ( nodeToken != null ) nodeToken.setParent(this);
      nodeToken1 = n1;
      if ( nodeToken1 != null ) nodeToken1.setParent(this);
      typedefName = n2;
      if ( typedefName != null ) typedefName.setParent(this);
      nodeToken2 = n3;
      if ( nodeToken2 != null ) nodeToken2.setParent(this);
   }

   public AtomicSpecifier(TypedefName n0) {
      nodeToken = new NodeToken("_Atomic");
      if ( nodeToken != null ) nodeToken.setParent(this);
      nodeToken1 = new NodeToken("(");
      if ( nodeToken1 != null ) nodeToken1.setParent(this);
      typedefName = n0;
      if ( typedefName != null ) typedefName.setParent(this);
      nodeToken2 = new NodeToken(")");
      if ( nodeToken2 != null ) nodeToken2.setParent(this);
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

