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
 * f0 -> [ &lt;D&gt; ]
 * f1 -> &lt;DISPLAY&gt;
 * f2 -> ( [ &lt;COMMACHAR&gt; ] ( Identifier() | Literal() ) )+
 * f3 -> [ &lt;UPON&gt; ( MnemonicName() | EnvironmentName() ) ]
 * f4 -> [ [ &lt;WITH&gt; ] &lt;NO&gt; &lt;ADVANCING&gt; ]
 * f5 -> [ &lt;READ&gt; Identifier() ]
 * </PRE>
 */
public class DisplayStatement implements Node {
   private Node parent;
   public NodeOptional f0;
   public NodeToken f1;
   public NodeList f2;
   public NodeOptional f3;
   public NodeOptional f4;
   public NodeOptional f5;

   public DisplayStatement(NodeOptional n0, NodeToken n1, NodeList n2, NodeOptional n3, NodeOptional n4, NodeOptional n5) {
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
   }

   public DisplayStatement(NodeOptional n0, NodeList n1, NodeOptional n2, NodeOptional n3, NodeOptional n4) {
      f0 = n0;
      if ( f0 != null ) f0.setParent(this);
      f1 = new NodeToken("display");
      if ( f1 != null ) f1.setParent(this);
      f2 = n1;
      if ( f2 != null ) f2.setParent(this);
      f3 = n2;
      if ( f3 != null ) f3.setParent(this);
      f4 = n3;
      if ( f4 != null ) f4.setParent(this);
      f5 = n4;
      if ( f5 != null ) f5.setParent(this);
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

