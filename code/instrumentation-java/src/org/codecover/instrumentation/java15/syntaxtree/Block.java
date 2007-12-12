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

package org.codecover.instrumentation.java15.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * f0 -> "{"
 * f1 -> ( BlockStatement() )*
 * f2 -> "}"
 * </PRE>
 */
@SuppressWarnings("all")
public class Block implements Node {
   private Node parent;
   public NodeToken f0;
   public NodeListOptional f1;
   public NodeToken f2;

   public Block(NodeToken n0, NodeListOptional n1, NodeToken n2) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);
      f1 = n1;
      if ( f1 != null ) f1.setParent(this);
      f2 = n2;
      if ( f2 != null ) f2.setParent(this);
   }

   public Block(NodeListOptional n0) {
      f0 = new NodeToken("{");
      if ( f0 != null ) f0.setParent(this);
      f1 = n0;
      if ( f1 != null ) f1.setParent(this);
      f2 = new NodeToken("}");
      if ( f2 != null ) f2.setParent(this);
   }

   public void accept(org.codecover.instrumentation.java15.visitor.Visitor v) {
      v.visit(this);
   }
   public void accept(org.codecover.instrumentation.java15.visitor.VisitorWithException v) throws java.io.IOException {
      v.visit(this);
   }
   public <R,A> R accept(org.codecover.instrumentation.java15.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(org.codecover.instrumentation.java15.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(org.codecover.instrumentation.java15.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
   public void setParent(Node n) { parent = n; }
   public Node getParent()       { return parent; }
   /** for debugging purposes */
   @Override
   public String toString() {
      return org.codecover.instrumentation.java15.visitor.TreeSourceFileImageDumper.convertToString(this);
   }
}

