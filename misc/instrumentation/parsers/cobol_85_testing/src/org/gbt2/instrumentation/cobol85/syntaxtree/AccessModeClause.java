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
 * f0 -> &lt;ACCESS&gt;
 * f1 -> [ &lt;MODE&gt; ]
 * f2 -> [ &lt;IS&gt; ]
 * f3 -> ( SequentialAccessMode() | RandomAccessMode() | DynamicAccessMode() )
 * </PRE>
 */
public class AccessModeClause implements Node {
   private Node parent;
   public NodeToken f0;
   public NodeOptional f1;
   public NodeOptional f2;
   public NodeChoice f3;

   public AccessModeClause(NodeToken n0, NodeOptional n1, NodeOptional n2, NodeChoice n3) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);
      f1 = n1;
      if ( f1 != null ) f1.setParent(this);
      f2 = n2;
      if ( f2 != null ) f2.setParent(this);
      f3 = n3;
      if ( f3 != null ) f3.setParent(this);
   }

   public AccessModeClause(NodeOptional n0, NodeOptional n1, NodeChoice n2) {
      f0 = new NodeToken("access");
      if ( f0 != null ) f0.setParent(this);
      f1 = n0;
      if ( f1 != null ) f1.setParent(this);
      f2 = n1;
      if ( f2 != null ) f2.setParent(this);
      f3 = n2;
      if ( f3 != null ) f3.setParent(this);
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

