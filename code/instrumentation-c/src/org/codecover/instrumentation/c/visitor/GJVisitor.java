//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.c.visitor;
import org.codecover.instrumentation.c.syntaxtree.*;
import java.util.*;

/**
 * All GJ visitors must implement this interface.
 */

public interface GJVisitor<R,A> {

   //
   // GJ Auto class visitors
   //

   public R visit(NodeList n, A argu);
   public R visit(NodeListOptional n, A argu);
   public R visit(NodeOptional n, A argu);
   public R visit(NodeSequence n, A argu);
   public R visit(NodeToken n, A argu);

   //
   // User-generated visitor methods below
   //

   /**
    * <PRE>
    * nodeChoice -> &lt;NUMBER&gt;
    *       | &lt;CHARACTER_LITERAL&gt;
    * </PRE>
    */
   public R visit(Constant n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;IDENTIFIER&gt;
    * </PRE>
    */
   public R visit(EnumerationConstant n, A argu);

   /**
    * <PRE>
    * nodeList -> ( &lt;STRING_LITERAL&gt; )+
    * </PRE>
    */
   public R visit(StringLiteral n, A argu);

   /**
    * <PRE>
    * nodeChoice -> &lt;IDENTIFIER&gt;
    *       | Constant()
    *       | StringLiteral()
    *       | "(" Expression() ")"
    *       | GenericSelection()
    * </PRE>
    */
   public R visit(PrimaryExpression n, A argu);

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
   public R visit(GenericSelection n, A argu);

   /**
    * <PRE>
    * genericAssociation -> GenericAssociation()
    * nodeListOptional -> ( "," GenericAssociation() )*
    * </PRE>
    */
   public R visit(GenericAssocList n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( &lt;DFAULT&gt; | TypeName() )
    * nodeToken -> ":"
    * assignmentExpression -> AssignmentExpression()
    * </PRE>
    */
   public R visit(GenericAssociation n, A argu);

   /**
    * <PRE>
    * nodeChoice -> "(" TypeName() ")" "{" InitializerList() [ "," ] "}"
    *       | PrimaryExpression() ( "[" Expression() "]" | "(" [ ArgumentExpressionList() ] ")" | "." &lt;IDENTIFIER&gt; | &lt;ARROW: "-&gt;"&gt; &lt;IDENTIFIER&gt; | "++" | "--" )*
    * </PRE>
    */
   public R visit(PostfixExpression n, A argu);

   /**
    * <PRE>
    * assignmentExpression -> AssignmentExpression()
    * nodeListOptional -> ( "," AssignmentExpression() )*
    * </PRE>
    */
   public R visit(ArgumentExpressionList n, A argu);

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
   public R visit(UnaryExpression n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( "&" | "*" | "+" | "-" | "~" | "!" )
    * </PRE>
    */
   public R visit(UnaryOperator n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( "(" TypeName() ")" CastExpression() | UnaryExpression() )
    * </PRE>
    */
   public R visit(CastExpression n, A argu);

   /**
    * <PRE>
    * castExpression -> CastExpression()
    * nodeOptional -> [ ( "*" | "/" | "%" ) MultiplicativeExpression() ]
    * </PRE>
    */
   public R visit(MultiplicativeExpression n, A argu);

   /**
    * <PRE>
    * multiplicativeExpression -> MultiplicativeExpression()
    * nodeOptional -> [ ( "+" | "-" ) AdditiveExpression() ]
    * </PRE>
    */
   public R visit(AdditiveExpression n, A argu);

   /**
    * <PRE>
    * additiveExpression -> AdditiveExpression()
    * nodeOptional -> [ ( &lt;LSH: "&lt;&lt;"&gt; | &lt;RSH: "&gt;&gt;"&gt; ) ShiftExpression() ]
    * </PRE>
    */
   public R visit(ShiftExpression n, A argu);

   /**
    * <PRE>
    * shiftExpression -> ShiftExpression()
    * nodeOptional -> [ ( "&lt;" | "&gt;" | &lt;LE: "&lt;="&gt; | &lt;GE: "&gt;="&gt; ) RelationalExpression() ]
    * </PRE>
    */
   public R visit(RelationalExpression n, A argu);

   /**
    * <PRE>
    * relationalExpression -> RelationalExpression()
    * nodeOptional -> [ ( &lt;EQ: "=="&gt; | &lt;NE: "!="&gt; ) EqualityExpression() ]
    * </PRE>
    */
   public R visit(EqualityExpression n, A argu);

   /**
    * <PRE>
    * equalityExpression -> EqualityExpression()
    * nodeOptional -> [ "&" ANDExpression() ]
    * </PRE>
    */
   public R visit(ANDExpression n, A argu);

   /**
    * <PRE>
    * aNDExpression -> ANDExpression()
    * nodeOptional -> [ "^" ExclusiveORExpression() ]
    * </PRE>
    */
   public R visit(ExclusiveORExpression n, A argu);

   /**
    * <PRE>
    * exclusiveORExpression -> ExclusiveORExpression()
    * nodeOptional -> [ "|" InclusiveORExpression() ]
    * </PRE>
    */
   public R visit(InclusiveORExpression n, A argu);

   /**
    * <PRE>
    * inclusiveORExpression -> InclusiveORExpression()
    * nodeOptional -> [ "&&" LogicalANDExpression() ]
    * </PRE>
    */
   public R visit(LogicalANDExpression n, A argu);

   /**
    * <PRE>
    * logicalANDExpression -> LogicalANDExpression()
    * nodeOptional -> [ "||" LogicalORExpression() ]
    * </PRE>
    */
   public R visit(LogicalORExpression n, A argu);

   /**
    * <PRE>
    * logicalORExpression -> LogicalORExpression()
    * nodeOptional -> [ ConditionalExpressionRightSide() ]
    * </PRE>
    */
   public R visit(ConditionalExpression n, A argu);

   /**
    * <PRE>
    * nodeToken -> "?"
    * expression -> Expression()
    * nodeToken1 -> ":"
    * conditionalExpression -> ConditionalExpression()
    * </PRE>
    */
   public R visit(ConditionalExpressionRightSide n, A argu);

   /**
    * <PRE>
    * nodeChoice -> UnaryExpression() AssignmentOperator() AssignmentExpression()
    *       | ConditionalExpression()
    * </PRE>
    */
   public R visit(AssignmentExpression n, A argu);

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
   public R visit(AssignmentOperator n, A argu);

   /**
    * <PRE>
    * assignmentExpression -> AssignmentExpression()
    * nodeListOptional -> ( "," AssignmentExpression() )*
    * </PRE>
    */
   public R visit(Expression n, A argu);

   /**
    * <PRE>
    * conditionalExpression -> ConditionalExpression()
    * </PRE>
    */
   public R visit(ConstantExpression n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( DeclarationSpecifiers() [ InitDeclaratorList() ] ";" | Static_AssertDeclaration() )
    * </PRE>
    */
   public R visit(Declaration n, A argu);

   /**
    * <PRE>
    * nodeChoice -> StorageClassSpecifier() [ DeclarationSpecifiers() ]
    *       | TypeSpecifier() [ DeclarationSpecifiers() ]
    *       | TypeQualifier() [ DeclarationSpecifiers() ]
    *       | FunctionSpecifier() [ DeclarationSpecifiers() ]
    *       | AlignmentSpecifier() [ DeclarationSpecifiers() ]
    * </PRE>
    */
   public R visit(DeclarationSpecifiers n, A argu);

   /**
    * <PRE>
    * initDeclarator -> InitDeclarator()
    * nodeListOptional -> ( "," InitDeclarator() )*
    * </PRE>
    */
   public R visit(InitDeclaratorList n, A argu);

   /**
    * <PRE>
    * declarator -> Declarator()
    * nodeOptional -> [ "=" Initializer() ]
    * </PRE>
    */
   public R visit(InitDeclarator n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( &lt;EXTERN&gt; | &lt;STATIC&gt; | &lt;THREADLOCAL&gt; | &lt;AUTO&gt; | &lt;REGISTER&gt; | &lt;TYPEDEF&gt; )
    * </PRE>
    */
   public R visit(StorageClassSpecifier n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( &lt;VOID&gt; | &lt;CHAR&gt; | &lt;SHORT&gt; | &lt;INT&gt; | &lt;LONG&gt; | &lt;FLOAT&gt; | &lt;DOUBLE&gt; | &lt;SIGNED&gt; | &lt;UNSIGNED&gt; | &lt;BOOL&gt; | &lt;COMPLEX&gt; | AtomicSpecifier() | StructOrUnionSpecifier() | EnumSpecifier() | TypedefName() )
    * </PRE>
    */
   public R visit(TypeSpecifier n, A argu);

   /**
    * <PRE>

    * structOrUnion -> StructOrUnion()
    * nodeChoice -> ( [ &lt;IDENTIFIER&gt; ] "{" StructDeclarationList() "}" | &lt;IDENTIFIER&gt; )
    * </PRE>
    */
   public R visit(StructOrUnionSpecifier n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( &lt;STRUCT&gt; | &lt;UNION&gt; )
    * </PRE>
    */
   public R visit(StructOrUnion n, A argu);

   /**
    * <PRE>
    * nodeList -> ( StructDeclaration() )+
    * </PRE>
    */
   public R visit(StructDeclarationList n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( SpecifierQualifierList() [ StructDeclaratorList() ] ";" | Static_AssertDeclaration() )
    * </PRE>
    */
   public R visit(StructDeclaration n, A argu);

   /**
    * <PRE>
    * nodeChoice -> TypeSpecifier() [ SpecifierQualifierList() ]
    *       | TypeQualifier() [ SpecifierQualifierList() ]
    * </PRE>
    */
   public R visit(SpecifierQualifierList n, A argu);

   /**
    * <PRE>
    * structDeclarator -> StructDeclarator()
    * nodeListOptional -> ( "," StructDeclarator() )*
    * </PRE>
    */
   public R visit(StructDeclaratorList n, A argu);

   /**
    * <PRE>
    * nodeChoice -> Declarator() [ ":" ConstantExpression() ]
    *       | ":" ConstantExpression()
    * </PRE>
    */
   public R visit(StructDeclarator n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;ENUM&gt;
    * nodeChoice -> ( [ &lt;IDENTIFIER&gt; ] "{" EnumeratorList() [ "," ] "}" | &lt;IDENTIFIER&gt; )
    * </PRE>
    */
   public R visit(EnumSpecifier n, A argu);

   /**
    * <PRE>
    * enumerator -> Enumerator()
    * nodeListOptional -> ( "," Enumerator() )*
    * </PRE>
    */
   public R visit(EnumeratorList n, A argu);

   /**
    * <PRE>
    * enumerationConstant -> EnumerationConstant()
    * nodeOptional -> [ "=" ConstantExpression() ]
    * </PRE>
    */
   public R visit(Enumerator n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;ATOMIC&gt;
    * nodeToken1 -> "("TypedefName()
    * nodeToken2 -> ")"
    * </PRE>
    */
   public R visit(AtomicSpecifier n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( &lt;CONST&gt; | &lt;RESTRICT&gt; | &lt;VOLATILE&gt; | &lt;ATOMIC&gt; )
    * </PRE>
    */
   public R visit(TypeQualifier n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( &lt;INLINE&gt; | &lt;NORETURN&gt; )
    * </PRE>
    */
   public R visit(FunctionSpecifier n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;ALIGNAS&gt;
    * nodeToken1 -> "("
    * nodeChoice -> ( TypedefName() | ConstantExpression() )
    * nodeToken2 -> ")"
    * </PRE>
    */
   public R visit(AlignmentSpecifier n, A argu);

   /**
    * <PRE>
    * nodeOptional -> [ Pointer() ]
    * directDeclarator -> DirectDeclarator()
    * </PRE>
    */
   public R visit(Declarator n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( t=&lt;IDENTIFIER&gt; | "(" Declarator() ")" )
    * nodeListOptional -> ( "[" ( &lt;STATIC&gt; [ TypeQualifierList() ] AssignmentExpression() | TypeQualifierList() &lt;STATIC&gt; AssignmentExpression() | [ TypeQualifierList() ] "*" "]" | [ ConstantExpression() ] ) "]" | "(" ParameterTypeList() ")" | "(" [ IdentifierList() ] ")" )*
    * </PRE>
    */
   public R visit(DirectDeclarator n, A argu);

   /**
    * <PRE>
    * nodeToken -> "*"
    * nodeOptional -> [ TypeQualifierList() ]
    * nodeOptional1 -> [ Pointer() ]
    * </PRE>
    */
   public R visit(Pointer n, A argu);

   /**
    * <PRE>
    * nodeList -> ( TypeQualifier() )+
    * </PRE>
    */
   public R visit(TypeQualifierList n, A argu);

   /**
    * <PRE>
    * parameterList -> ParameterList()
    * nodeOptional -> [ "," &lt;ELLIPSIS: "..."&gt; ]
    * </PRE>
    */
   public R visit(ParameterTypeList n, A argu);

   /**
    * <PRE>
    * parameterDeclaration -> ParameterDeclaration()
    * nodeListOptional -> ( "," ParameterDeclaration() )*
    * </PRE>
    */
   public R visit(ParameterList n, A argu);

   /**
    * <PRE>
    * declarationSpecifiers -> DeclarationSpecifiers()
    * nodeChoice -> ( Declarator() | [ AbstractDeclarator() ] )
    * </PRE>
    */
   public R visit(ParameterDeclaration n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;IDENTIFIER&gt;
    * nodeListOptional -> ( "," &lt;IDENTIFIER&gt; )*
    * </PRE>
    */
   public R visit(IdentifierList n, A argu);

   /**
    * <PRE>
    * specifierQualifierList -> SpecifierQualifierList()
    * nodeOptional -> [ AbstractDeclarator() ]
    * </PRE>
    */
   public R visit(TypeName n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( Pointer() | [ Pointer() ] DirectAbstractDeclarator() )
    * </PRE>
    */
   public R visit(AbstractDeclarator n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( "(" AbstractDeclarator() ")" | "(" [ ParameterTypeList() ] ")" | "[" ( &lt;STATIC&gt; [ TypeQualifierList() ] AssignmentExpression() | TypeQualifierList() &lt;STATIC&gt; AssignmentExpression() | "*" | [ TypeQualifierList() ] AssignmentExpression() ) "]" )
    * nodeListOptional -> ( "(" [ ParameterTypeList() ] ")" | "[" ( &lt;STATIC&gt; [ TypeQualifierList() ] AssignmentExpression() | TypeQualifierList() &lt;STATIC&gt; AssignmentExpression() | "*" | [ TypeQualifierList() ] AssignmentExpression() ) "]" )*
    * </PRE>
    */
   public R visit(DirectAbstractDeclarator n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;IDENTIFIER&gt;
    * </PRE>
    */
   public R visit(TypedefName n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( AssignmentExpression() | "{" InitializerList() [ "," ] "}" )
    * </PRE>
    */
   public R visit(Initializer n, A argu);

   /**
    * <PRE>
    * nodeOptional -> [ Designation() ]
    * initializer -> Initializer()
    * nodeListOptional -> ( "," Initializer() )*
    * </PRE>
    */
   public R visit(InitializerList n, A argu);

   /**
    * <PRE>
    * designatorList -> DesignatorList()
    * nodeToken -> "="
    * </PRE>
    */
   public R visit(Designation n, A argu);

   /**
    * <PRE>
    * nodeList -> ( Designator() )+
    * </PRE>
    */
   public R visit(DesignatorList n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( "[" ConstantExpression() "]" | "." &lt;IDENTIFIER&gt; )
    * </PRE>
    */
   public R visit(Designator n, A argu);

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
   public R visit(Static_AssertDeclaration n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( LabeledStatement() | CompoundStatement() | ExpressionStatement() | IfStatement() | SwitchStatement() | WhileStatement() | DoStatement() | ForStatement() | JumpStatement() )
    * </PRE>
    */
   public R visit(Statement n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( &lt;IDENTIFIER&gt; ":" Statement() | CaseStatement() | DefaultStatement() )
    * </PRE>
    */
   public R visit(LabeledStatement n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;CASE&gt;
    * constantExpression -> ConstantExpression()
    * nodeToken1 -> ":"
    * statement -> Statement()
    * </PRE>
    */
   public R visit(CaseStatement n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;DFAULT&gt;
    * nodeToken1 -> ":"
    * statement -> Statement()
    * </PRE>
    */
   public R visit(DefaultStatement n, A argu);

   /**
    * <PRE>
    * nodeToken -> "{"
    * nodeOptional -> [ BlockItemList() ]
    * nodeToken1 -> "}"
    * </PRE>
    */
   public R visit(CompoundStatement n, A argu);

   /**
    * <PRE>
    * nodeList -> ( BlockItem() )+
    * </PRE>
    */
   public R visit(BlockItemList n, A argu);

   /**
    * <PRE>
    * nodeChoice -> Declaration()
    *       | Statement()
    * </PRE>
    */
   public R visit(BlockItem n, A argu);

   /**
    * <PRE>
    * nodeOptional -> [ Expression() ]
    * nodeToken -> ";"
    * </PRE>
    */
   public R visit(ExpressionStatement n, A argu);

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
   public R visit(IfStatement n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;SWITCH&gt;
    * nodeToken1 -> "("
    * expression -> Expression()
    * nodeToken2 -> ")"
    * statement -> Statement()
    * </PRE>
    */
   public R visit(SwitchStatement n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;WHILE&gt;
    * nodeToken1 -> "("
    * expression -> Expression()
    * nodeToken2 -> ")"
    * statement -> Statement()
    * </PRE>
    */
   public R visit(WhileStatement n, A argu);

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
   public R visit(DoStatement n, A argu);

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
   public R visit(ForStatement n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( &lt;GOTO&gt; &lt;IDENTIFIER&gt; ";" | &lt;CONTINUE&gt; ";" | &lt;BREAK&gt; ";" | ReturnStatement() )
    * </PRE>
    */
   public R visit(JumpStatement n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;RETURN&gt;
    * nodeOptional -> [ Expression() ]
    * nodeToken1 -> ";"
    * </PRE>
    */
   public R visit(ReturnStatement n, A argu);

   /**
    * <PRE>
    * nodeList -> ( ExternalDeclaration() )+
    * </PRE>
    */
   public R visit(TranslationUnit n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( FunctionDefinition() | Declaration() )
    * </PRE>
    */
   public R visit(ExternalDeclaration n, A argu);

   /**
    * <PRE>
    * nodeOptional -> [ DeclarationSpecifiers() ]
    * declarator -> Declarator()
    * nodeOptional1 -> [ DeclarationList() ]
    * compoundStatement -> CompoundStatement()
    * </PRE>
    */
   public R visit(FunctionDefinition n, A argu);

   /**
    * <PRE>
    * nodeList -> ( Declaration() )+
    * </PRE>
    */
   public R visit(DeclarationList n, A argu);

}
