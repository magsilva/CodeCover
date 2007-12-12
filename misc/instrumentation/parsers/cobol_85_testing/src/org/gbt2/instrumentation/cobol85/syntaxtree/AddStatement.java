﻿///////////////////////////////////////////////////////////////////////////////
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
 * f0 -> &lt;ADD&gt;
 * f1 -> ( ( ( &lt;CORRESPONDING&gt; | &lt;CORR&gt; ) Identifier() &lt;TO&gt; Identifier() [ &lt;GIVING&gt; ( Identifier() [ &lt;ROUNDED&gt; ] )+ ] ) | ( Identifier() | Literal() )+ ( [ &lt;TO&gt; ( Identifier() | Literal() ) ] &lt;GIVING&gt; ( Identifier() [ &lt;ROUNDED&gt; ] )+ | &lt;TO&gt; ( Identifier() [ &lt;ROUNDED&gt; ] )+ ) )
 * f2 -> [ [ &lt;ON&gt; ] &lt;SIZE&gt; &lt;ERROR&gt; StatementList() ]
 * f3 -> [ &lt;NOT&gt; [ &lt;ON&gt; ] &lt;SIZE&gt; &lt;ERROR&gt; StatementList() ]
 * f4 -> [ &lt;END_ADD&gt; ]
 * </PRE>
 */
public class AddStatement implements Node {
   private Node parent;
   public NodeToken f0;
   public NodeChoice f1;
   public NodeOptional f2;
   public NodeOptional f3;
   public NodeOptional f4;

   public AddStatement(NodeToken n0, NodeChoice n1, NodeOptional n2, NodeOptional n3, NodeOptional n4) {
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

   public AddStatement(NodeChoice n0, NodeOptional n1, NodeOptional n2, NodeOptional n3) {
      f0 = new NodeToken("add");
      if ( f0 != null ) f0.setParent(this);
      f1 = n0;
      if ( f1 != null ) f1.setParent(this);
      f2 = n1;
      if ( f2 != null ) f2.setParent(this);
      f3 = n2;
      if ( f3 != null ) f3.setParent(this);
      f4 = n3;
      if ( f4 != null ) f4.setParent(this);
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

