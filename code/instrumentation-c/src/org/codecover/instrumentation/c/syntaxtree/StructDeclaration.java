//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.c.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * specifierQualifierList -> SpecifierQualifierList()
 * structDeclaratorList -> StructDeclaratorList()
 * nodeToken -> ";"
 * </PRE>
 */
public class StructDeclaration extends org.codecover.instrumentation.c.adapter.CCNode implements Node {
   public SpecifierQualifierList specifierQualifierList;
   public StructDeclaratorList structDeclaratorList;
   public NodeToken nodeToken;

   public StructDeclaration(SpecifierQualifierList n0, StructDeclaratorList n1, NodeToken n2) {
      specifierQualifierList = n0;
      structDeclaratorList = n1;
      nodeToken = n2;
   }

   public StructDeclaration(SpecifierQualifierList n0, StructDeclaratorList n1) {
      specifierQualifierList = n0;
      structDeclaratorList = n1;
      nodeToken = new NodeToken(";");
   }

   public void accept(org.codecover.instrumentation.c.visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(org.codecover.instrumentation.c.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(org.codecover.instrumentation.c.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(org.codecover.instrumentation.c.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
}

