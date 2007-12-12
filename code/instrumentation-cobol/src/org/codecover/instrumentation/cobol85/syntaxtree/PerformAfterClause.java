/******************************************************************************
 * Copyright (c) 2007 Stefan Franke, Robert Hanussek, Benjamin Keil,          *
 *                    Steffen Kieß, Johannes Langauf,                         *
 *                    Christoph Marian Müller, Igor Podolskiy,                *
 *                    Tilmann Scheller, Michael Starzmann, Markus Wittlinger  *
 * All rights reserved. This program and the accompanying materials           *
 * are made available under the terms of the Eclipse Public License v1.0      *
 * which accompanies this distribution, and is available at                   *
 * http://www.eclipse.org/legal/epl-v10.html                                  *
 ******************************************************************************/

//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.cobol85.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * f0 -> &lt;AFTER&gt;
 * f1 -> QualifiedDataName()
 * f2 -> &lt;FROM&gt;
 * f3 -> ( Identifier() | Literal() )
 * f4 -> &lt;BY&gt;
 * f5 -> ( Identifier() | Literal() )
 * f6 -> PerformUntilClause()
 * </PRE>
 */
public class PerformAfterClause implements Node {
   private Node parent;
   public NodeToken f0;
   public QualifiedDataName f1;
   public NodeToken f2;
   public NodeChoice f3;
   public NodeToken f4;
   public NodeChoice f5;
   public PerformUntilClause f6;

   public PerformAfterClause(NodeToken n0, QualifiedDataName n1, NodeToken n2, NodeChoice n3, NodeToken n4, NodeChoice n5, PerformUntilClause n6) {
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

   public PerformAfterClause(QualifiedDataName n0, NodeChoice n1, NodeChoice n2, PerformUntilClause n3) {
      f0 = new NodeToken("after");
      if ( f0 != null ) f0.setParent(this);
      f1 = n0;
      if ( f1 != null ) f1.setParent(this);
      f2 = new NodeToken("from");
      if ( f2 != null ) f2.setParent(this);
      f3 = n1;
      if ( f3 != null ) f3.setParent(this);
      f4 = new NodeToken("by");
      if ( f4 != null ) f4.setParent(this);
      f5 = n2;
      if ( f5 != null ) f5.setParent(this);
      f6 = n3;
      if ( f6 != null ) f6.setParent(this);
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

