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
 * f0 -> &lt;STOP&gt;
 * f1 -> ( &lt;RUN&gt; | Literal() )
 * </PRE>
 */
public class StopStatement implements Node {
   private Node parent;
   public NodeToken f0;
   public NodeChoice f1;

   public StopStatement(NodeToken n0, NodeChoice n1) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);
      f1 = n1;
      if ( f1 != null ) f1.setParent(this);
   }

   public StopStatement(NodeChoice n0) {
      f0 = new NodeToken("stop");
      if ( f0 != null ) f0.setParent(this);
      f1 = n0;
      if ( f1 != null ) f1.setParent(this);
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

