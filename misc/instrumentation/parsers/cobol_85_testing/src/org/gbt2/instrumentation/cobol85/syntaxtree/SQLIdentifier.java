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
 * f0 -> ( ( ( Literal() | &lt;COLONCHAR&gt; Identifier() ) ( &lt;YEARS&gt; | &lt;MONTHS&gt; | &lt;DAYS&gt; ) ) | SQLSimpleIdentifier() )
 * f1 -> [ ( &lt;PLUSCHAR&gt; | &lt;MINUSCHAR&gt; | &lt;DOUBLEEXCLAMATIONMARK&gt; ) SQLIdentifier() ]
 * </PRE>
 */
public class SQLIdentifier implements Node {
   private Node parent;
   public NodeChoice f0;
   public NodeOptional f1;

   public SQLIdentifier(NodeChoice n0, NodeOptional n1) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);
      f1 = n1;
      if ( f1 != null ) f1.setParent(this);
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

