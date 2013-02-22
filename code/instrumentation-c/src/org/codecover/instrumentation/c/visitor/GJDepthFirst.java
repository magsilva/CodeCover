//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.c.visitor;
import org.codecover.instrumentation.c.syntaxtree.*;
import java.util.*;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class GJDepthFirst<R,A> implements GJVisitor<R,A> {
   //
   // Auto class visitors--probably don't need to be overridden.
   //
   public R visit(NodeList n, A argu) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeListOptional n, A argu) {
      if ( n.present() ) {
         R _ret=null;
         int _count=0;
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this,argu);
            _count++;
         }
         return _ret;
      }
      else
         return null;
   }

   public R visit(NodeOptional n, A argu) {
      if ( n.present() )
         return n.node.accept(this,argu);
      else
         return null;
   }

   public R visit(NodeSequence n, A argu) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeToken n, A argu) { return null; }

   //
   // User-generated visitor methods below
   //

   /**
    * <PRE>
    * nodeList -> ( ExternalDeclaration() )+
    * </PRE>
    */
   public R visit(TranslationUnit n, A argu) {
      R _ret=null;
      n.nodeList.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( FunctionDefinition() | Declaration() )
    * </PRE>
    */
   public R visit(ExternalDeclaration n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeOptional -> [ DeclarationSpecifiers() ]
    * declarator -> Declarator()
    * nodeOptional1 -> [ DeclarationList() ]
    * compoundStatement -> CompoundStatement()
    * </PRE>
    */
   public R visit(FunctionDefinition n, A argu) {
      R _ret=null;
      n.nodeOptional.accept(this, argu);
      n.declarator.accept(this, argu);
      n.nodeOptional1.accept(this, argu);
      n.compoundStatement.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * declarationSpecifiers -> DeclarationSpecifiers()
    * nodeOptional -> [ InitDeclaratorList() ]
    * nodeToken -> ";"
    * </PRE>
    */
   public R visit(Declaration n, A argu) {
      R _ret=null;
      n.declarationSpecifiers.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      n.nodeToken.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeList -> ( Declaration() )+
    * </PRE>
    */
   public R visit(DeclarationList n, A argu) {
      R _ret=null;
      n.nodeList.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> StorageClassSpecifier() [ DeclarationSpecifiers() ]
    *       | TypeSpecifier() [ DeclarationSpecifiers() ]
    *       | TypeQualifier() [ DeclarationSpecifiers() ]
    * </PRE>
    */
   public R visit(DeclarationSpecifiers n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( &lt;AUTO&gt; | &lt;REGISTER&gt; | &lt;STATIC&gt; | &lt;EXTERN&gt; | &lt;TYPEDEF&gt; )
    * </PRE>
    */
   public R visit(StorageClassSpecifier n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( &lt;VOID&gt; | &lt;CHAR&gt; | &lt;SHORT&gt; | &lt;INT&gt; | &lt;LONG&gt; | &lt;FLOAT&gt; | &lt;DOUBLE&gt; | &lt;SIGNED&gt; | &lt;UNSIGNED&gt; | StructOrUnionSpecifier() | EnumSpecifier() | TypedefName() )
    * </PRE>
    */
   public R visit(TypeSpecifier n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( &lt;CONST&gt; | &lt;VOLATILE&gt; )
    * </PRE>
    */
   public R visit(TypeQualifier n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>

    * structOrUnion -> StructOrUnion()
    * nodeChoice -> ( [ &lt;IDENTIFIER&gt; ] "{" StructDeclarationList() "}" | &lt;IDENTIFIER&gt; )
    * </PRE>
    */
   public R visit(StructOrUnionSpecifier n, A argu) {
      R _ret=null;
      n.structOrUnion.accept(this, argu);
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( &lt;STRUCT&gt; | &lt;UNION&gt; )
    * </PRE>
    */
   public R visit(StructOrUnion n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeList -> ( StructDeclaration() )+
    * </PRE>
    */
   public R visit(StructDeclarationList n, A argu) {
      R _ret=null;
      n.nodeList.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * initDeclarator -> InitDeclarator()
    * nodeListOptional -> ( "," InitDeclarator() )*
    * </PRE>
    */
   public R visit(InitDeclaratorList n, A argu) {
      R _ret=null;
      n.initDeclarator.accept(this, argu);
      n.nodeListOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * declarator -> Declarator()
    * nodeOptional -> [ "=" Initializer() ]
    * </PRE>
    */
   public R visit(InitDeclarator n, A argu) {
      R _ret=null;
      n.declarator.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * specifierQualifierList -> SpecifierQualifierList()
    * structDeclaratorList -> StructDeclaratorList()
    * nodeToken -> ";"
    * </PRE>
    */
   public R visit(StructDeclaration n, A argu) {
      R _ret=null;
      n.specifierQualifierList.accept(this, argu);
      n.structDeclaratorList.accept(this, argu);
      n.nodeToken.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> TypeSpecifier() [ SpecifierQualifierList() ]
    *       | TypeQualifier() [ SpecifierQualifierList() ]
    * </PRE>
    */
   public R visit(SpecifierQualifierList n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * structDeclarator -> StructDeclarator()
    * nodeListOptional -> ( "," StructDeclarator() )*
    * </PRE>
    */
   public R visit(StructDeclaratorList n, A argu) {
      R _ret=null;
      n.structDeclarator.accept(this, argu);
      n.nodeListOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( Declarator() | [ Declarator() ] ":" ConstantExpression() )
    * </PRE>
    */
   public R visit(StructDeclarator n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeToken -> &lt;ENUM&gt;
    * nodeChoice -> ( [ &lt;IDENTIFIER&gt; ] "{" EnumeratorList() "}" | &lt;IDENTIFIER&gt; )
    * </PRE>
    */
   public R visit(EnumSpecifier n, A argu) {
      R _ret=null;
      n.nodeToken.accept(this, argu);
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * enumerator -> Enumerator()
    * nodeListOptional -> ( "," Enumerator() )*
    * </PRE>
    */
   public R visit(EnumeratorList n, A argu) {
      R _ret=null;
      n.enumerator.accept(this, argu);
      n.nodeListOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeToken -> &lt;IDENTIFIER&gt;
    * nodeOptional -> [ "=" ConstantExpression() ]
    * </PRE>
    */
   public R visit(Enumerator n, A argu) {
      R _ret=null;
      n.nodeToken.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeOptional -> [ Pointer() ]
    * directDeclarator -> DirectDeclarator()
    * </PRE>
    */
   public R visit(Declarator n, A argu) {
      R _ret=null;
      n.nodeOptional.accept(this, argu);
      n.directDeclarator.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( t=&lt;IDENTIFIER&gt; | "(" Declarator() ")" )
    * nodeListOptional -> ( "[" [ ConstantExpression() ] "]" | "(" ParameterTypeList() ")" | "(" [ IdentifierList() ] ")" )*
    * </PRE>
    */
   public R visit(DirectDeclarator n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      n.nodeListOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeToken -> "*"
    * nodeOptional -> [ TypeQualifierList() ]
    * nodeOptional1 -> [ Pointer() ]
    * </PRE>
    */
   public R visit(Pointer n, A argu) {
      R _ret=null;
      n.nodeToken.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      n.nodeOptional1.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeList -> ( TypeQualifier() )+
    * </PRE>
    */
   public R visit(TypeQualifierList n, A argu) {
      R _ret=null;
      n.nodeList.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * parameterList -> ParameterList()
    * nodeOptional -> [ "," &lt;ELLIPSIS: "..."&gt; ]
    * </PRE>
    */
   public R visit(ParameterTypeList n, A argu) {
      R _ret=null;
      n.parameterList.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * parameterDeclaration -> ParameterDeclaration()
    * nodeListOptional -> ( "," ParameterDeclaration() )*
    * </PRE>
    */
   public R visit(ParameterList n, A argu) {
      R _ret=null;
      n.parameterDeclaration.accept(this, argu);
      n.nodeListOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * declarationSpecifiers -> DeclarationSpecifiers()
    * nodeChoice -> ( Declarator() | [ AbstractDeclarator() ] )
    * </PRE>
    */
   public R visit(ParameterDeclaration n, A argu) {
      R _ret=null;
      n.declarationSpecifiers.accept(this, argu);
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeToken -> &lt;IDENTIFIER&gt;
    * nodeListOptional -> ( "," &lt;IDENTIFIER&gt; )*
    * </PRE>
    */
   public R visit(IdentifierList n, A argu) {
      R _ret=null;
      n.nodeToken.accept(this, argu);
      n.nodeListOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( AssignmentExpression() | "{" InitializerList() [ "," ] "}" )
    * </PRE>
    */
   public R visit(Initializer n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * initializer -> Initializer()
    * nodeListOptional -> ( "," Initializer() )*
    * </PRE>
    */
   public R visit(InitializerList n, A argu) {
      R _ret=null;
      n.initializer.accept(this, argu);
      n.nodeListOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * specifierQualifierList -> SpecifierQualifierList()
    * nodeOptional -> [ AbstractDeclarator() ]
    * </PRE>
    */
   public R visit(TypeName n, A argu) {
      R _ret=null;
      n.specifierQualifierList.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( Pointer() | [ Pointer() ] DirectAbstractDeclarator() )
    * </PRE>
    */
   public R visit(AbstractDeclarator n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( "(" AbstractDeclarator() ")" | "[" [ ConstantExpression() ] "]" | "(" [ ParameterTypeList() ] ")" )
    * nodeListOptional -> ( "[" [ ConstantExpression() ] "]" | "(" [ ParameterTypeList() ] ")" )*
    * </PRE>
    */
   public R visit(DirectAbstractDeclarator n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      n.nodeListOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeToken -> &lt;IDENTIFIER&gt;
    * </PRE>
    */
   public R visit(TypedefName n, A argu) {
      R _ret=null;
      n.nodeToken.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( LabeledStatement() | CompoundStatement() | ExpressionStatement() | IfStatement() | SwitchStatement() | WhileStatement() | DoStatement() | ForStatement() | JumpStatement() )
    * </PRE>
    */
   public R visit(Statement n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( &lt;IDENTIFIER&gt; ":" Statement() | &lt;CASE&gt; ConstantExpression() ":" Statement() | &lt;DFLT&gt; ":" Statement() )
    * </PRE>
    */
   public R visit(LabeledStatement n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeOptional -> [ Expression() ]
    * nodeToken -> ";"
    * </PRE>
    */
   public R visit(ExpressionStatement n, A argu) {
      R _ret=null;
      n.nodeOptional.accept(this, argu);
      n.nodeToken.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeToken -> "{"
    * nodeOptional -> [ DeclarationList() ]
    * nodeOptional1 -> [ StatementList() ]
    * nodeToken1 -> "}"
    * </PRE>
    */
   public R visit(CompoundStatement n, A argu) {
      R _ret=null;
      n.nodeToken.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      n.nodeOptional1.accept(this, argu);
      n.nodeToken1.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeList -> ( Statement() )+
    * </PRE>
    */
   public R visit(StatementList n, A argu) {
      R _ret=null;
      n.nodeList.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeToken -> &lt;IF&gt;
    * nodeToken1 -> "("
    * expression -> Expression()
    * nodeToken2 -> ")"
    * statement -> Statement()
    * nodeOptional -> [ &lt;ELSE&gt; Statement() ]
    * </PRE>
    */
   public R visit(IfStatement n, A argu) {
      R _ret=null;
      n.nodeToken.accept(this, argu);
      n.nodeToken1.accept(this, argu);
      n.expression.accept(this, argu);
      n.nodeToken2.accept(this, argu);
      n.statement.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeToken -> &lt;SWITCH&gt;
    * nodeToken1 -> "("
    * expression -> Expression()
    * nodeToken2 -> ")"
    * statement -> Statement()
    * </PRE>
    */
   public R visit(SwitchStatement n, A argu) {
      R _ret=null;
      n.nodeToken.accept(this, argu);
      n.nodeToken1.accept(this, argu);
      n.expression.accept(this, argu);
      n.nodeToken2.accept(this, argu);
      n.statement.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeToken -> &lt;WHILE&gt;
    * nodeToken1 -> "("
    * expression -> Expression()
    * nodeToken2 -> ")"
    * statement -> Statement()
    * </PRE>
    */
   public R visit(WhileStatement n, A argu) {
      R _ret=null;
      n.nodeToken.accept(this, argu);
      n.nodeToken1.accept(this, argu);
      n.expression.accept(this, argu);
      n.nodeToken2.accept(this, argu);
      n.statement.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeToken -> &lt;DO&gt;
    * statement -> Statement()
    * nodeToken1 -> &lt;WHILE&gt;
    * nodeToken2 -> "("
    * expression -> Expression()
    * nodeToken3 -> ")"
    * nodeToken4 -> ";"
    * </PRE>
    */
   public R visit(DoStatement n, A argu) {
      R _ret=null;
      n.nodeToken.accept(this, argu);
      n.statement.accept(this, argu);
      n.nodeToken1.accept(this, argu);
      n.nodeToken2.accept(this, argu);
      n.expression.accept(this, argu);
      n.nodeToken3.accept(this, argu);
      n.nodeToken4.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeToken -> &lt;FOR&gt;
    * nodeToken1 -> "("
    * nodeOptional -> [ Expression() ]
    * nodeToken2 -> ";"
    * nodeOptional1 -> [ Expression() ]
    * nodeToken3 -> ";"
    * nodeOptional2 -> [ Expression() ]
    * nodeToken4 -> ")"
    * statement -> Statement()
    * </PRE>
    */
   public R visit(ForStatement n, A argu) {
      R _ret=null;
      n.nodeToken.accept(this, argu);
      n.nodeToken1.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      n.nodeToken2.accept(this, argu);
      n.nodeOptional1.accept(this, argu);
      n.nodeToken3.accept(this, argu);
      n.nodeOptional2.accept(this, argu);
      n.nodeToken4.accept(this, argu);
      n.statement.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( &lt;GOTO&gt; &lt;IDENTIFIER&gt; ";" | &lt;CONTINUE&gt; ";" | &lt;BREAK&gt; ";" | ReturnStatement() )
    * </PRE>
    */
   public R visit(JumpStatement n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeToken -> &lt;RETURN&gt;
    * nodeOptional -> [ Expression() ]
    * nodeToken1 -> ";"
    * </PRE>
    */
   public R visit(ReturnStatement n, A argu) {
      R _ret=null;
      n.nodeToken.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      n.nodeToken1.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * assignmentExpression -> AssignmentExpression()
    * nodeListOptional -> ( "," AssignmentExpression() )*
    * </PRE>
    */
   public R visit(Expression n, A argu) {
      R _ret=null;
      n.assignmentExpression.accept(this, argu);
      n.nodeListOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> UnaryExpression() AssignmentOperator() AssignmentExpression()
    *       | ConditionalExpression()
    * </PRE>
    */
   public R visit(AssignmentExpression n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( "=" | &lt;MULT_EQ: "*="&gt; | &lt;DIV_EQ: "/="&gt; | &lt;MOD_EQ: "%="&gt; | &lt;PLUS_EQ: "+="&gt; | &lt;SUB_EQ: "-="&gt; | &lt;LSH_EQ: "&lt;&lt;="&gt; | &lt;RSH_EQ: "&gt;&gt;="&gt; | &lt;AND_EQ: "&="&gt; | &lt;XOR_EQ: "^="&gt; | &lt;OR_EQ: "|="&gt; )
    * </PRE>
    */
   public R visit(AssignmentOperator n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * logicalORExpression -> LogicalORExpression()
    * nodeOptional -> [ "?" Expression() ":" ConditionalExpression() ]
    * </PRE>
    */
   public R visit(ConditionalExpression n, A argu) {
      R _ret=null;
      n.logicalORExpression.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * conditionalExpression -> ConditionalExpression()
    * </PRE>
    */
   public R visit(ConstantExpression n, A argu) {
      R _ret=null;
      n.conditionalExpression.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * logicalANDExpression -> LogicalANDExpression()
    * nodeOptional -> [ "||" LogicalORExpression() ]
    * </PRE>
    */
   public R visit(LogicalORExpression n, A argu) {
      R _ret=null;
      n.logicalANDExpression.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * inclusiveORExpression -> InclusiveORExpression()
    * nodeOptional -> [ "&&" LogicalANDExpression() ]
    * </PRE>
    */
   public R visit(LogicalANDExpression n, A argu) {
      R _ret=null;
      n.inclusiveORExpression.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * exclusiveORExpression -> ExclusiveORExpression()
    * nodeOptional -> [ "|" InclusiveORExpression() ]
    * </PRE>
    */
   public R visit(InclusiveORExpression n, A argu) {
      R _ret=null;
      n.exclusiveORExpression.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * aNDExpression -> ANDExpression()
    * nodeOptional -> [ "^" ExclusiveORExpression() ]
    * </PRE>
    */
   public R visit(ExclusiveORExpression n, A argu) {
      R _ret=null;
      n.aNDExpression.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * equalityExpression -> EqualityExpression()
    * nodeOptional -> [ "&" ANDExpression() ]
    * </PRE>
    */
   public R visit(ANDExpression n, A argu) {
      R _ret=null;
      n.equalityExpression.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * relationalExpression -> RelationalExpression()
    * nodeOptional -> [ ( &lt;EQ: "=="&gt; | &lt;NE: "!="&gt; ) EqualityExpression() ]
    * </PRE>
    */
   public R visit(EqualityExpression n, A argu) {
      R _ret=null;
      n.relationalExpression.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * shiftExpression -> ShiftExpression()
    * nodeOptional -> [ ( "&lt;" | "&gt;" | &lt;LE: "&lt;="&gt; | &lt;GE: "&gt;="&gt; ) RelationalExpression() ]
    * </PRE>
    */
   public R visit(RelationalExpression n, A argu) {
      R _ret=null;
      n.shiftExpression.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * additiveExpression -> AdditiveExpression()
    * nodeOptional -> [ ( &lt;LSH: "&lt;&lt;"&gt; | &lt;RSH: "&gt;&gt;"&gt; ) ShiftExpression() ]
    * </PRE>
    */
   public R visit(ShiftExpression n, A argu) {
      R _ret=null;
      n.additiveExpression.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * multiplicativeExpression -> MultiplicativeExpression()
    * nodeOptional -> [ ( "+" | "-" ) AdditiveExpression() ]
    * </PRE>
    */
   public R visit(AdditiveExpression n, A argu) {
      R _ret=null;
      n.multiplicativeExpression.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * castExpression -> CastExpression()
    * nodeOptional -> [ ( "*" | "/" | "%" ) MultiplicativeExpression() ]
    * </PRE>
    */
   public R visit(MultiplicativeExpression n, A argu) {
      R _ret=null;
      n.castExpression.accept(this, argu);
      n.nodeOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( "(" TypeName() ")" CastExpression() | UnaryExpression() )
    * </PRE>
    */
   public R visit(CastExpression n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( PostfixExpression() | "++" UnaryExpression() | "--" UnaryExpression() | UnaryOperator() CastExpression() | &lt;SIZEOF&gt; ( UnaryExpression() | "(" TypeName() ")" ) )
    * </PRE>
    */
   public R visit(UnaryExpression n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( "&" | "*" | "+" | "-" | "~" | "!" )
    * </PRE>
    */
   public R visit(UnaryOperator n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * primaryExpression -> PrimaryExpression()
    * nodeListOptional -> ( "[" Expression() "]" | "(" [ ArgumentExpressionList() ] ")" | "." &lt;IDENTIFIER&gt; | &lt;ARROW: "-&gt;"&gt; &lt;IDENTIFIER&gt; | "++" | "--" )*
    * </PRE>
    */
   public R visit(PostfixExpression n, A argu) {
      R _ret=null;
      n.primaryExpression.accept(this, argu);
      n.nodeListOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> ( &lt;IDENTIFIER&gt; | Constant() | "(" Expression() ")" )
    * </PRE>
    */
   public R visit(PrimaryExpression n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * assignmentExpression -> AssignmentExpression()
    * nodeListOptional -> ( "," AssignmentExpression() )*
    * </PRE>
    */
   public R visit(ArgumentExpressionList n, A argu) {
      R _ret=null;
      n.assignmentExpression.accept(this, argu);
      n.nodeListOptional.accept(this, argu);
      return _ret;
   }

   /**
    * <PRE>
    * nodeChoice -> &lt;NUMBER&gt;
    *       | &lt;CHARACTER_LITERAL&gt;
    *       | ( &lt;STRING_LITERAL&gt; )+
    * </PRE>
    */
   public R visit(Constant n, A argu) {
      R _ret=null;
      n.nodeChoice.accept(this, argu);
      return _ret;
   }

}
