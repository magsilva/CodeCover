///////////////////////////////////////////////////////////////////////////////
//
// $Id$
//
///////////////////////////////////////////////////////////////////////////////

//
// Generated by JTB 1.3.2
//

package org.gbt2.instrumentation.cobol85.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * f0 -> &lt;STRING&gt;
 * f1 -> ( ( Identifier() | QualifiedDataName() | Literal() )+ &lt;DELIMITED&gt; [ &lt;BY&gt; ] ( Identifier() | Literal() | &lt;SIZE&gt; ) )+
 * f2 -> &lt;INTO&gt;
 * f3 -> Identifier()
 * f4 -> [ [ &lt;WITH&gt; ] &lt;POINTER&gt; QualifiedDataName() ]
 * f5 -> [ [ &lt;ON&gt; ] &lt;OVERFLOW&gt; StatementList() ]
 * f6 -> [ &lt;NOT&gt; [ &lt;ON&gt; ] &lt;OVERFLOW&gt; StatementList() ]
 * f7 -> [ &lt;END_STRING&gt; ]
 * </PRE>
 */
public class StringStatement implements Node {
   private Node parent;
   public NodeToken f0;
   public NodeList f1;
   public NodeToken f2;
   public Identifier f3;
   public NodeOptional f4;
   public NodeOptional f5;
   public NodeOptional f6;
   public NodeOptional f7;

   public StringStatement(NodeToken n0, NodeList n1, NodeToken n2, Identifier n3, NodeOptional n4, NodeOptional n5, NodeOptional n6, NodeOptional n7) {
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
      f5 = n5;
      if ( f5 != null ) f5.setParent(this);
      f6 = n6;
      if ( f6 != null ) f6.setParent(this);
      f7 = n7;
      if ( f7 != null ) f7.setParent(this);
   }

   public StringStatement(NodeList n0, Identifier n1, NodeOptional n2, NodeOptional n3, NodeOptional n4, NodeOptional n5) {
      f0 = new NodeToken("string");
      if ( f0 != null ) f0.setParent(this);
      f1 = n0;
      if ( f1 != null ) f1.setParent(this);
      f2 = new NodeToken("into");
      if ( f2 != null ) f2.setParent(this);
      f3 = n1;
      if ( f3 != null ) f3.setParent(this);
      f4 = n2;
      if ( f4 != null ) f4.setParent(this);
      f5 = n3;
      if ( f5 != null ) f5.setParent(this);
      f6 = n4;
      if ( f6 != null ) f6.setParent(this);
      f7 = n5;
      if ( f7 != null ) f7.setParent(this);
   }

   public void accept(org.gbt2.instrumentation.cobol85.visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(org.gbt2.instrumentation.cobol85.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(org.gbt2.instrumentation.cobol85.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(org.gbt2.instrumentation.cobol85.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
   public void setParent(Node n) { parent = n; }
   public Node getParent()       { return parent; }
}

