//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.xampil.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * f0 -> Declaration()
 * f1 -> Program()
 * f2 -> ( &lt;EOL&gt; )?
 * f3 -> &lt;EOF&gt;
 * </PRE>
 */
public class CompilationUnit implements Node {
   private Node parent;
   public Declaration f0;
   public Program f1;
   public NodeOptional f2;
   public NodeToken f3;

   public CompilationUnit(Declaration n0, Program n1, NodeOptional n2, NodeToken n3) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);
      f1 = n1;
      if ( f1 != null ) f1.setParent(this);
      f2 = n2;
      if ( f2 != null ) f2.setParent(this);
      f3 = n3;
      if ( f3 != null ) f3.setParent(this);
   }

   public CompilationUnit(Declaration n0, Program n1, NodeOptional n2) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);
      f1 = n1;
      if ( f1 != null ) f1.setParent(this);
      f2 = n2;
      if ( f2 != null ) f2.setParent(this);
      f3 = new NodeToken("");
      if ( f3 != null ) f3.setParent(this);
   }

   public void accept(org.codecover.instrumentation.xampil.visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(org.codecover.instrumentation.xampil.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(org.codecover.instrumentation.xampil.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(org.codecover.instrumentation.xampil.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
   public void setParent(Node n) { parent = n; }
   public Node getParent()       { return parent; }
}

