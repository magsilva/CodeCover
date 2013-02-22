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
public class DepthFirstVisitor implements Visitor {
   //
   // Auto class visitors--probably don't need to be overridden.
   //
   public void visit(NodeList n) {
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); )
         e.nextElement().accept(this);
   }

   public void visit(NodeListOptional n) {
      if ( n.present() )
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); )
            e.nextElement().accept(this);
   }

   public void visit(NodeOptional n) {
      if ( n.present() )
         n.node.accept(this);
   }

   public void visit(NodeSequence n) {
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); )
         e.nextElement().accept(this);
   }

   public void visit(NodeToken n) { }

   //
   // User-generated visitor methods below
   //

   /**
    * <PRE>
    * nodeChoice -> &lt;NUMBER&gt;
    *       | &lt;CHARACTER_LITERAL&gt;
    * </PRE>
    */
   public void visit(Constant n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeToken -> &lt;IDENTIFIER&gt;
    * </PRE>
    */
   public void visit(EnumerationConstant n) {
      n.nodeToken.accept(this);
   }

   /**
    * <PRE>
    * nodeList -> ( &lt;STRING_LITERAL&gt; )+
    * </PRE>
    */
   public void visit(StringLiteral n) {
      n.nodeList.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> &lt;IDENTIFIER&gt;
    *       | Constant()
    *       | StringLiteral()
    *       | "(" Expression() ")"
    *       | GenericSelection()
    * </PRE>
    */
   public void visit(PrimaryExpression n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeToken -> &lt;GENERIC&gt;
    * nodeToken1 -> "("
    * assignmentExpression -> AssignmentExpression()
    * nodeToken2 -> ","
    * genericAssocList -> GenericAssocList()
    * nodeToken3 -> ")"
    * </PRE>
    */
   public void visit(GenericSelection n) {
      n.nodeToken.accept(this);
      n.nodeToken1.accept(this);
      n.assignmentExpression.accept(this);
      n.nodeToken2.accept(this);
      n.genericAssocList.accept(this);
      n.nodeToken3.accept(this);
   }

   /**
    * <PRE>
    * genericAssociation -> GenericAssociation()
    * nodeListOptional -> ( "," GenericAssociation() )*
    * </PRE>
    */
   public void visit(GenericAssocList n) {
      n.genericAssociation.accept(this);
      n.nodeListOptional.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( &lt;DFAULT&gt; | TypeName() )
    * nodeToken -> ":"
    * assignmentExpression -> AssignmentExpression()
    * </PRE>
    */
   public void visit(GenericAssociation n) {
      n.nodeChoice.accept(this);
      n.nodeToken.accept(this);
      n.assignmentExpression.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> "(" TypeName() ")" "{" InitializerList() [ "," ] "}"
    *       | PrimaryExpression() ( "[" Expression() "]" | "(" [ ArgumentExpressionList() ] ")" | "." &lt;IDENTIFIER&gt; | &lt;ARROW: "-&gt;"&gt; &lt;IDENTIFIER&gt; | "++" | "--" )*
    * </PRE>
    */
   public void visit(PostfixExpression n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * assignmentExpression -> AssignmentExpression()
    * nodeListOptional -> ( "," AssignmentExpression() )*
    * </PRE>
    */
   public void visit(ArgumentExpressionList n) {
      n.assignmentExpression.accept(this);
      n.nodeListOptional.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> PostfixExpression()
    *       | "++" UnaryExpression()
    *       | "--" UnaryExpression()
    *       | UnaryOperator() CastExpression()
    *       | &lt;SIZEOF&gt; ( "(" TypeName() ")" | UnaryExpression() )
    *       | &lt;ALIGNOF&gt; "(" TypeName() ")"
    * </PRE>
    */
   public void visit(UnaryExpression n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( "&" | "*" | "+" | "-" | "~" | "!" )
    * </PRE>
    */
   public void visit(UnaryOperator n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( "(" TypeName() ")" CastExpression() | UnaryExpression() )
    * </PRE>
    */
   public void visit(CastExpression n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * castExpression -> CastExpression()
    * nodeOptional -> [ ( "*" | "/" | "%" ) MultiplicativeExpression() ]
    * </PRE>
    */
   public void visit(MultiplicativeExpression n) {
      n.castExpression.accept(this);
      n.nodeOptional.accept(this);
   }

   /**
    * <PRE>
    * multiplicativeExpression -> MultiplicativeExpression()
    * nodeOptional -> [ ( "+" | "-" ) AdditiveExpression() ]
    * </PRE>
    */
   public void visit(AdditiveExpression n) {
      n.multiplicativeExpression.accept(this);
      n.nodeOptional.accept(this);
   }

   /**
    * <PRE>
    * additiveExpression -> AdditiveExpression()
    * nodeOptional -> [ ( &lt;LSH: "&lt;&lt;"&gt; | &lt;RSH: "&gt;&gt;"&gt; ) ShiftExpression() ]
    * </PRE>
    */
   public void visit(ShiftExpression n) {
      n.additiveExpression.accept(this);
      n.nodeOptional.accept(this);
   }

   /**
    * <PRE>
    * shiftExpression -> ShiftExpression()
    * nodeOptional -> [ ( "&lt;" | "&gt;" | &lt;LE: "&lt;="&gt; | &lt;GE: "&gt;="&gt; ) RelationalExpression() ]
    * </PRE>
    */
   public void visit(RelationalExpression n) {
      n.shiftExpression.accept(this);
      n.nodeOptional.accept(this);
   }

   /**
    * <PRE>
    * relationalExpression -> RelationalExpression()
    * nodeOptional -> [ ( &lt;EQ: "=="&gt; | &lt;NE: "!="&gt; ) EqualityExpression() ]
    * </PRE>
    */
   public void visit(EqualityExpression n) {
      n.relationalExpression.accept(this);
      n.nodeOptional.accept(this);
   }

   /**
    * <PRE>
    * equalityExpression -> EqualityExpression()
    * nodeOptional -> [ "&" ANDExpression() ]
    * </PRE>
    */
   public void visit(ANDExpression n) {
      n.equalityExpression.accept(this);
      n.nodeOptional.accept(this);
   }

   /**
    * <PRE>
    * aNDExpression -> ANDExpression()
    * nodeOptional -> [ "^" ExclusiveORExpression() ]
    * </PRE>
    */
   public void visit(ExclusiveORExpression n) {
      n.aNDExpression.accept(this);
      n.nodeOptional.accept(this);
   }

   /**
    * <PRE>
    * exclusiveORExpression -> ExclusiveORExpression()
    * nodeOptional -> [ "|" InclusiveORExpression() ]
    * </PRE>
    */
   public void visit(InclusiveORExpression n) {
      n.exclusiveORExpression.accept(this);
      n.nodeOptional.accept(this);
   }

   /**
    * <PRE>
    * inclusiveORExpression -> InclusiveORExpression()
    * nodeOptional -> [ "&&" LogicalANDExpression() ]
    * </PRE>
    */
   public void visit(LogicalANDExpression n) {
      n.inclusiveORExpression.accept(this);
      n.nodeOptional.accept(this);
   }

   /**
    * <PRE>
    * logicalANDExpression -> LogicalANDExpression()
    * nodeOptional -> [ "||" LogicalORExpression() ]
    * </PRE>
    */
   public void visit(LogicalORExpression n) {
      n.logicalANDExpression.accept(this);
      n.nodeOptional.accept(this);
   }

   /**
    * <PRE>
    * logicalORExpression -> LogicalORExpression()
    * nodeOptional -> [ ConditionalExpressionRightSide() ]
    * </PRE>
    */
   public void visit(ConditionalExpression n) {
      n.logicalORExpression.accept(this);
      n.nodeOptional.accept(this);
   }

   /**
    * <PRE>
    * nodeToken -> "?"
    * expression -> Expression()
    * nodeToken1 -> ":"
    * conditionalExpression -> ConditionalExpression()
    * </PRE>
    */
   public void visit(ConditionalExpressionRightSide n) {
      n.nodeToken.accept(this);
      n.expression.accept(this);
      n.nodeToken1.accept(this);
      n.conditionalExpression.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> UnaryExpression() AssignmentOperator() AssignmentExpression()
    *       | ConditionalExpression()
    * </PRE>
    */
   public void visit(AssignmentExpression n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> "="
    *       | &lt;MULT_EQ: "*="&gt;
    *       | &lt;DIV_EQ: "/="&gt;
    *       | &lt;MOD_EQ: "%="&gt;
    *       | &lt;PLUS_EQ: "+="&gt;
    *       | &lt;SUB_EQ: "-="&gt;
    *       | &lt;LSH_EQ: "&lt;&lt;="&gt;
    *       | &lt;RSH_EQ: "&gt;&gt;="&gt;
    *       | &lt;AND_EQ: "&="&gt;
    *       | &lt;XOR_EQ: "^="&gt;
    *       | &lt;OR_EQ: "|="&gt;
    * </PRE>
    */
   public void visit(AssignmentOperator n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * assignmentExpression -> AssignmentExpression()
    * nodeListOptional -> ( "," AssignmentExpression() )*
    * </PRE>
    */
   public void visit(Expression n) {
      n.assignmentExpression.accept(this);
      n.nodeListOptional.accept(this);
   }

   /**
    * <PRE>
    * conditionalExpression -> ConditionalExpression()
    * </PRE>
    */
   public void visit(ConstantExpression n) {
      n.conditionalExpression.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( DeclarationSpecifiers() [ InitDeclaratorList() ] ";" | Static_AssertDeclaration() )
    * </PRE>
    */
   public void visit(Declaration n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> StorageClassSpecifier() [ DeclarationSpecifiers() ]
    *       | TypeSpecifier() [ DeclarationSpecifiers() ]
    *       | TypeQualifier() [ DeclarationSpecifiers() ]
    *       | FunctionSpecifier() [ DeclarationSpecifiers() ]
    *       | AlignmentSpecifier() [ DeclarationSpecifiers() ]
    * </PRE>
    */
   public void visit(DeclarationSpecifiers n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * initDeclarator -> InitDeclarator()
    * nodeListOptional -> ( "," InitDeclarator() )*
    * </PRE>
    */
   public void visit(InitDeclaratorList n) {
      n.initDeclarator.accept(this);
      n.nodeListOptional.accept(this);
   }

   /**
    * <PRE>
    * declarator -> Declarator()
    * nodeOptional -> [ "=" Initializer() ]
    * </PRE>
    */
   public void visit(InitDeclarator n) {
      n.declarator.accept(this);
      n.nodeOptional.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( &lt;EXTERN&gt; | &lt;STATIC&gt; | &lt;THREADLOCAL&gt; | &lt;AUTO&gt; | &lt;REGISTER&gt; | &lt;TYPEDEF&gt; )
    * </PRE>
    */
   public void visit(StorageClassSpecifier n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( &lt;VOID&gt; | &lt;CHAR&gt; | &lt;SHORT&gt; | &lt;INT&gt; | &lt;LONG&gt; | &lt;FLOAT&gt; | &lt;DOUBLE&gt; | &lt;SIGNED&gt; | &lt;UNSIGNED&gt; | &lt;BOOL&gt; | &lt;COMPLEX&gt; | AtomicSpecifier() | StructOrUnionSpecifier() | EnumSpecifier() | TypedefName() )
    * </PRE>
    */
   public void visit(TypeSpecifier n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>

    * structOrUnion -> StructOrUnion()
    * nodeChoice -> ( [ &lt;IDENTIFIER&gt; ] "{" StructDeclarationList() "}" | &lt;IDENTIFIER&gt; )
    * </PRE>
    */
   public void visit(StructOrUnionSpecifier n) {
      n.structOrUnion.accept(this);
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( &lt;STRUCT&gt; | &lt;UNION&gt; )
    * </PRE>
    */
   public void visit(StructOrUnion n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeList -> ( StructDeclaration() )+
    * </PRE>
    */
   public void visit(StructDeclarationList n) {
      n.nodeList.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( SpecifierQualifierList() [ StructDeclaratorList() ] ";" | Static_AssertDeclaration() )
    * </PRE>
    */
   public void visit(StructDeclaration n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> TypeSpecifier() [ SpecifierQualifierList() ]
    *       | TypeQualifier() [ SpecifierQualifierList() ]
    * </PRE>
    */
   public void visit(SpecifierQualifierList n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * structDeclarator -> StructDeclarator()
    * nodeListOptional -> ( "," StructDeclarator() )*
    * </PRE>
    */
   public void visit(StructDeclaratorList n) {
      n.structDeclarator.accept(this);
      n.nodeListOptional.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> Declarator() [ ":" ConstantExpression() ]
    *       | ":" ConstantExpression()
    * </PRE>
    */
   public void visit(StructDeclarator n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeToken -> &lt;ENUM&gt;
    * nodeChoice -> ( [ &lt;IDENTIFIER&gt; ] "{" EnumeratorList() [ "," ] "}" | &lt;IDENTIFIER&gt; )
    * </PRE>
    */
   public void visit(EnumSpecifier n) {
      n.nodeToken.accept(this);
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * enumerator -> Enumerator()
    * nodeListOptional -> ( "," Enumerator() )*
    * </PRE>
    */
   public void visit(EnumeratorList n) {
      n.enumerator.accept(this);
      n.nodeListOptional.accept(this);
   }

   /**
    * <PRE>
    * enumerationConstant -> EnumerationConstant()
    * nodeOptional -> [ "=" ConstantExpression() ]
    * </PRE>
    */
   public void visit(Enumerator n) {
      n.enumerationConstant.accept(this);
      n.nodeOptional.accept(this);
   }

   /**
    * <PRE>
    * nodeToken -> &lt;ATOMIC&gt;
    * nodeToken1 -> "("TypedefName()
    * nodeToken2 -> ")"
    * </PRE>
    */
   public void visit(AtomicSpecifier n) {
      n.nodeToken.accept(this);
      n.nodeToken1.accept(this);
      n.typedefName.accept(this);
      n.nodeToken2.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( &lt;CONST&gt; | &lt;RESTRICT&gt; | &lt;VOLATILE&gt; | &lt;ATOMIC&gt; )
    * </PRE>
    */
   public void visit(TypeQualifier n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( &lt;INLINE&gt; | &lt;NORETURN&gt; )
    * </PRE>
    */
   public void visit(FunctionSpecifier n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeToken -> &lt;ALIGNAS&gt;
    * nodeToken1 -> "("
    * nodeChoice -> ( TypedefName() | ConstantExpression() )
    * nodeToken2 -> ")"
    * </PRE>
    */
   public void visit(AlignmentSpecifier n) {
      n.nodeToken.accept(this);
      n.nodeToken1.accept(this);
      n.nodeChoice.accept(this);
      n.nodeToken2.accept(this);
   }

   /**
    * <PRE>
    * nodeOptional -> [ Pointer() ]
    * directDeclarator -> DirectDeclarator()
    * </PRE>
    */
   public void visit(Declarator n) {
      n.nodeOptional.accept(this);
      n.directDeclarator.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( t=&lt;IDENTIFIER&gt; | "(" Declarator() ")" )
    * nodeListOptional -> ( "[" ( &lt;STATIC&gt; [ TypeQualifierList() ] AssignmentExpression() | TypeQualifierList() &lt;STATIC&gt; AssignmentExpression() | [ TypeQualifierList() ] "*" "]" | [ ConstantExpression() ] ) "]" | "(" ParameterTypeList() ")" | "(" [ IdentifierList() ] ")" )*
    * </PRE>
    */
   public void visit(DirectDeclarator n) {
      n.nodeChoice.accept(this);
      n.nodeListOptional.accept(this);
   }

   /**
    * <PRE>
    * nodeToken -> "*"
    * nodeOptional -> [ TypeQualifierList() ]
    * nodeOptional1 -> [ Pointer() ]
    * </PRE>
    */
   public void visit(Pointer n) {
      n.nodeToken.accept(this);
      n.nodeOptional.accept(this);
      n.nodeOptional1.accept(this);
   }

   /**
    * <PRE>
    * nodeList -> ( TypeQualifier() )+
    * </PRE>
    */
   public void visit(TypeQualifierList n) {
      n.nodeList.accept(this);
   }

   /**
    * <PRE>
    * parameterList -> ParameterList()
    * nodeOptional -> [ "," &lt;ELLIPSIS: "..."&gt; ]
    * </PRE>
    */
   public void visit(ParameterTypeList n) {
      n.parameterList.accept(this);
      n.nodeOptional.accept(this);
   }

   /**
    * <PRE>
    * parameterDeclaration -> ParameterDeclaration()
    * nodeListOptional -> ( "," ParameterDeclaration() )*
    * </PRE>
    */
   public void visit(ParameterList n) {
      n.parameterDeclaration.accept(this);
      n.nodeListOptional.accept(this);
   }

   /**
    * <PRE>
    * declarationSpecifiers -> DeclarationSpecifiers()
    * nodeChoice -> ( Declarator() | [ AbstractDeclarator() ] )
    * </PRE>
    */
   public void visit(ParameterDeclaration n) {
      n.declarationSpecifiers.accept(this);
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeToken -> &lt;IDENTIFIER&gt;
    * nodeListOptional -> ( "," &lt;IDENTIFIER&gt; )*
    * </PRE>
    */
   public void visit(IdentifierList n) {
      n.nodeToken.accept(this);
      n.nodeListOptional.accept(this);
   }

   /**
    * <PRE>
    * specifierQualifierList -> SpecifierQualifierList()
    * nodeOptional -> [ AbstractDeclarator() ]
    * </PRE>
    */
   public void visit(TypeName n) {
      n.specifierQualifierList.accept(this);
      n.nodeOptional.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( Pointer() | [ Pointer() ] DirectAbstractDeclarator() )
    * </PRE>
    */
   public void visit(AbstractDeclarator n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( "(" AbstractDeclarator() ")" | "(" [ ParameterTypeList() ] ")" | "[" ( &lt;STATIC&gt; [ TypeQualifierList() ] AssignmentExpression() | TypeQualifierList() &lt;STATIC&gt; AssignmentExpression() | "*" | [ TypeQualifierList() ] AssignmentExpression() ) "]" )
    * nodeListOptional -> ( "(" [ ParameterTypeList() ] ")" | "[" ( &lt;STATIC&gt; [ TypeQualifierList() ] AssignmentExpression() | TypeQualifierList() &lt;STATIC&gt; AssignmentExpression() | "*" | [ TypeQualifierList() ] AssignmentExpression() ) "]" )*
    * </PRE>
    */
   public void visit(DirectAbstractDeclarator n) {
      n.nodeChoice.accept(this);
      n.nodeListOptional.accept(this);
   }

   /**
    * <PRE>
    * nodeToken -> &lt;IDENTIFIER&gt;
    * </PRE>
    */
   public void visit(TypedefName n) {
      n.nodeToken.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( AssignmentExpression() | "{" InitializerList() [ "," ] "}" )
    * </PRE>
    */
   public void visit(Initializer n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeOptional -> [ Designation() ]
    * initializer -> Initializer()
    * nodeListOptional -> ( "," Initializer() )*
    * </PRE>
    */
   public void visit(InitializerList n) {
      n.nodeOptional.accept(this);
      n.initializer.accept(this);
      n.nodeListOptional.accept(this);
   }

   /**
    * <PRE>
    * designatorList -> DesignatorList()
    * nodeToken -> "="
    * </PRE>
    */
   public void visit(Designation n) {
      n.designatorList.accept(this);
      n.nodeToken.accept(this);
   }

   /**
    * <PRE>
    * nodeList -> ( Designator() )+
    * </PRE>
    */
   public void visit(DesignatorList n) {
      n.nodeList.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( "[" ConstantExpression() "]" | "." &lt;IDENTIFIER&gt; )
    * </PRE>
    */
   public void visit(Designator n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeToken -> &lt;STATICASSERT&gt;
    * nodeToken1 -> "("
    * constantExpression -> ConstantExpression()
    * nodeToken2 -> ","
    * nodeToken3 -> &lt;STRING_LITERAL&gt;
    * nodeToken4 -> ")"
    * nodeToken5 -> ";"
    * </PRE>
    */
   public void visit(Static_AssertDeclaration n) {
      n.nodeToken.accept(this);
      n.nodeToken1.accept(this);
      n.constantExpression.accept(this);
      n.nodeToken2.accept(this);
      n.nodeToken3.accept(this);
      n.nodeToken4.accept(this);
      n.nodeToken5.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( LabeledStatement() | CompoundStatement() | ExpressionStatement() | IfStatement() | SwitchStatement() | WhileStatement() | DoStatement() | ForStatement() | JumpStatement() )
    * </PRE>
    */
   public void visit(Statement n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( &lt;IDENTIFIER&gt; ":" Statement() | CaseStatement() | DefaultStatement() )
    * </PRE>
    */
   public void visit(LabeledStatement n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeToken -> &lt;CASE&gt;
    * constantExpression -> ConstantExpression()
    * nodeToken1 -> ":"
    * statement -> Statement()
    * </PRE>
    */
   public void visit(CaseStatement n) {
      n.nodeToken.accept(this);
      n.constantExpression.accept(this);
      n.nodeToken1.accept(this);
      n.statement.accept(this);
   }

   /**
    * <PRE>
    * nodeToken -> &lt;DFAULT&gt;
    * nodeToken1 -> ":"
    * statement -> Statement()
    * </PRE>
    */
   public void visit(DefaultStatement n) {
      n.nodeToken.accept(this);
      n.nodeToken1.accept(this);
      n.statement.accept(this);
   }

   /**
    * <PRE>
    * nodeToken -> "{"
    * nodeOptional -> [ BlockItemList() ]
    * nodeToken1 -> "}"
    * </PRE>
    */
   public void visit(CompoundStatement n) {
      n.nodeToken.accept(this);
      n.nodeOptional.accept(this);
      n.nodeToken1.accept(this);
   }

   /**
    * <PRE>
    * nodeList -> ( BlockItem() )+
    * </PRE>
    */
   public void visit(BlockItemList n) {
      n.nodeList.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> Declaration()
    *       | Statement()
    * </PRE>
    */
   public void visit(BlockItem n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeOptional -> [ Expression() ]
    * nodeToken -> ";"
    * </PRE>
    */
   public void visit(ExpressionStatement n) {
      n.nodeOptional.accept(this);
      n.nodeToken.accept(this);
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
   public void visit(IfStatement n) {
      n.nodeToken.accept(this);
      n.nodeToken1.accept(this);
      n.expression.accept(this);
      n.nodeToken2.accept(this);
      n.statement.accept(this);
      n.nodeOptional.accept(this);
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
   public void visit(SwitchStatement n) {
      n.nodeToken.accept(this);
      n.nodeToken1.accept(this);
      n.expression.accept(this);
      n.nodeToken2.accept(this);
      n.statement.accept(this);
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
   public void visit(WhileStatement n) {
      n.nodeToken.accept(this);
      n.nodeToken1.accept(this);
      n.expression.accept(this);
      n.nodeToken2.accept(this);
      n.statement.accept(this);
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
   public void visit(DoStatement n) {
      n.nodeToken.accept(this);
      n.statement.accept(this);
      n.nodeToken1.accept(this);
      n.nodeToken2.accept(this);
      n.expression.accept(this);
      n.nodeToken3.accept(this);
      n.nodeToken4.accept(this);
   }

   /**
    * <PRE>
    * nodeToken -> &lt;FOR&gt;
    * nodeToken1 -> "("
    * nodeChoice -> ( Declaration() [ Expression() ] | [ Expression() ] ";" [ Expression() ] )
    * nodeToken2 -> ";"
    * nodeOptional -> [ Expression() ]
    * nodeToken3 -> ")"
    * statement -> Statement()
    * </PRE>
    */
   public void visit(ForStatement n) {
      n.nodeToken.accept(this);
      n.nodeToken1.accept(this);
      n.nodeChoice.accept(this);
      n.nodeToken2.accept(this);
      n.nodeOptional.accept(this);
      n.nodeToken3.accept(this);
      n.statement.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( &lt;GOTO&gt; &lt;IDENTIFIER&gt; ";" | &lt;CONTINUE&gt; ";" | &lt;BREAK&gt; ";" | ReturnStatement() )
    * </PRE>
    */
   public void visit(JumpStatement n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeToken -> &lt;RETURN&gt;
    * nodeOptional -> [ Expression() ]
    * nodeToken1 -> ";"
    * </PRE>
    */
   public void visit(ReturnStatement n) {
      n.nodeToken.accept(this);
      n.nodeOptional.accept(this);
      n.nodeToken1.accept(this);
   }

   /**
    * <PRE>
    * nodeList -> ( ExternalDeclaration() )+
    * </PRE>
    */
   public void visit(TranslationUnit n) {
      n.nodeList.accept(this);
   }

   /**
    * <PRE>
    * nodeChoice -> ( FunctionDefinition() | Declaration() )
    * </PRE>
    */
   public void visit(ExternalDeclaration n) {
      n.nodeChoice.accept(this);
   }

   /**
    * <PRE>
    * nodeOptional -> [ DeclarationSpecifiers() ]
    * declarator -> Declarator()
    * nodeOptional1 -> [ DeclarationList() ]
    * compoundStatement -> CompoundStatement()
    * </PRE>
    */
   public void visit(FunctionDefinition n) {
      n.nodeOptional.accept(this);
      n.declarator.accept(this);
      n.nodeOptional1.accept(this);
      n.compoundStatement.accept(this);
   }

   /**
    * <PRE>
    * nodeList -> ( Declaration() )+
    * </PRE>
    */
   public void visit(DeclarationList n) {
      n.nodeList.accept(this);
   }

}
