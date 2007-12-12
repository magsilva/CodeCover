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
 * f0 -> &lt;ALTERNATE&gt;
 * f1 -> &lt;RECORD&gt;
 * f2 -> [ &lt;KEY&gt; ]
 * f3 -> [ &lt;IS&gt; ]
 * f4 -> QualifiedDataName()
 * f5 -> [ PasswordClause() ]
 * f6 -> [ [ &lt;WITH&gt; ] &lt;DUPLICATES&gt; ]
 * </PRE>
 */
public class AlternateRecordKeyClause implements Node {
   private Node parent;
   public NodeToken f0;
   public NodeToken f1;
   public NodeOptional f2;
   public NodeOptional f3;
   public QualifiedDataName f4;
   public NodeOptional f5;
   public NodeOptional f6;

   public AlternateRecordKeyClause(NodeToken n0, NodeToken n1, NodeOptional n2, NodeOptional n3, QualifiedDataName n4, NodeOptional n5, NodeOptional n6) {
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
   }

   public AlternateRecordKeyClause(NodeOptional n0, NodeOptional n1, QualifiedDataName n2, NodeOptional n3, NodeOptional n4) {
      f0 = new NodeToken("alternate");
      if ( f0 != null ) f0.setParent(this);
      f1 = new NodeToken("record");
      if ( f1 != null ) f1.setParent(this);
      f2 = n0;
      if ( f2 != null ) f2.setParent(this);
      f3 = n1;
      if ( f3 != null ) f3.setParent(this);
      f4 = n2;
      if ( f4 != null ) f4.setParent(this);
      f5 = n3;
      if ( f5 != null ) f5.setParent(this);
      f6 = n4;
      if ( f6 != null ) f6.setParent(this);
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

