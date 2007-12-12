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
 * f0 -> [ TypeParameters() ]
 * f1 -> &lt;IDENTIFIER&gt;
 * f2 -> FormalParameters()
 * f3 -> [ "throws" NameList() ]
 * f4 -> "{"
 * f5 -> [ ExplicitConstructorInvocation() ]
 * f6 -> ( BlockStatement() )*
 * f7 -> "}"
 * </PRE>
 */
@SuppressWarnings("all")
public class ConstructorDeclaration implements Node {
   private Node parent;
   public NodeOptional f0;
   public NodeToken f1;
   public FormalParameters f2;
   public NodeOptional f3;
   public NodeToken f4;
   public NodeOptional f5;
   public NodeListOptional f6;
   public NodeToken f7;

   public ConstructorDeclaration(NodeOptional n0, NodeToken n1, FormalParameters n2, NodeOptional n3, NodeToken n4, NodeOptional n5, NodeListOptional n6, NodeToken n7) {
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

   public ConstructorDeclaration(NodeOptional n0, NodeToken n1, FormalParameters n2, NodeOptional n3, NodeOptional n4, NodeListOptional n5) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);
      f1 = n1;
      if ( f1 != null ) f1.setParent(this);
      f2 = n2;
      if ( f2 != null ) f2.setParent(this);
      f3 = n3;
      if ( f3 != null ) f3.setParent(this);
      f4 = new NodeToken("{");
      if ( f4 != null ) f4.setParent(this);
      f5 = n4;
      if ( f5 != null ) f5.setParent(this);
      f6 = n5;
      if ( f6 != null ) f6.setParent(this);
      f7 = new NodeToken("}");
      if ( f7 != null ) f7.setParent(this);
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

