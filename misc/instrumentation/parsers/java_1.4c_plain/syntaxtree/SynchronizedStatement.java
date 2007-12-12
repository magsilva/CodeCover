//
// Generated by JTB 1.3.2
//

package syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * f0 -> "synchronized"
 * f1 -> "("
 * f2 -> Expression()
 * f3 -> ")"
 * f4 -> Block()
 * </PRE>
 */
public class SynchronizedStatement implements Node {
   private Node parent;
   public NodeToken f0;
   public NodeToken f1;
   public Expression f2;
   public NodeToken f3;
   public Block f4;

   public SynchronizedStatement(NodeToken n0, NodeToken n1, Expression n2, NodeToken n3, Block n4) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);
      f1 = n1;
      if ( f1 != null ) f1.setParent(this);
      f2 = n2;
      if ( f2 != null ) f2.setParent(this);
      f3 = n3;
      if ( f3 != null ) f3.setParent(this);
      f4 = n4;
      if ( f4 != null ) f4.setParent(this);
   }

   public SynchronizedStatement(Expression n0, Block n1) {
      f0 = new NodeToken("synchronized");
      if ( f0 != null ) f0.setParent(this);
      f1 = new NodeToken("(");
      if ( f1 != null ) f1.setParent(this);
      f2 = n0;
      if ( f2 != null ) f2.setParent(this);
      f3 = new NodeToken(")");
      if ( f3 != null ) f3.setParent(this);
      f4 = n1;
      if ( f4 != null ) f4.setParent(this);
   }

   public void accept(visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
   public void setParent(Node n) { parent = n; }
   public Node getParent()       { return parent; }
}

