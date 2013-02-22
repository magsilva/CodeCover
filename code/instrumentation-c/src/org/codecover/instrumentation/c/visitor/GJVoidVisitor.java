//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.c.visitor;
import org.codecover.instrumentation.c.syntaxtree.*;
import java.util.*;

/**
 * All GJ void visitors must implement this interface.
 */

public interface GJVoidVisitor<A> {

   //
   // GJ void Auto class visitors
   //

   public void visit(NodeList n, A argu);
   public void visit(NodeListOptional n, A argu);
   public void visit(NodeOptional n, A argu);
   public void visit(NodeSequence n, A argu);
   public void visit(NodeToken n, A argu);

   //
   // User-generated visitor methods below
   //

   /**
    * <PRE>
    * nodeList -> ( ExternalDeclaration() )+
    * </PRE>
    */
   public void visit(TranslationUnit n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( FunctionDefinition() | Declaration() )
    * </PRE>
    */
   public void visit(ExternalDeclaration n, A argu);

   /**
    * <PRE>
    * nodeOptional -> [ DeclarationSpecifiers() ]
    * declarator -> Declarator()
    * nodeOptional1 -> [ DeclarationList() ]
    * compoundStatement -> CompoundStatement()
    * </PRE>
    */
   public void visit(FunctionDefinition n, A argu);

   /**
    * <PRE>
    * declarationSpecifiers -> DeclarationSpecifiers()
    * nodeOptional -> [ InitDeclaratorList() ]
    * nodeToken -> ";"
    * </PRE>
    */
   public void visit(Declaration n, A argu);

   /**
    * <PRE>
    * nodeList -> ( Declaration() )+
    * </PRE>
    */
   public void visit(DeclarationList n, A argu);

   /**
    * <PRE>
    * nodeChoice -> StorageClassSpecifier() [ DeclarationSpecifiers() ]
    *       | TypeSpecifier() [ DeclarationSpecifiers() ]
    *       | TypeQualifier() [ DeclarationSpecifiers() ]
    * </PRE>
    */
   public void visit(DeclarationSpecifiers n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( &lt;AUTO&gt; | &lt;REGISTER&gt; | &lt;STATIC&gt; | &lt;EXTERN&gt; | &lt;TYPEDEF&gt; )
    * </PRE>
    */
   public void visit(StorageClassSpecifier n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( &lt;VOID&gt; | &lt;CHAR&gt; | &lt;SHORT&gt; | &lt;INT&gt; | &lt;LONG&gt; | &lt;FLOAT&gt; | &lt;DOUBLE&gt; | &lt;SIGNED&gt; | &lt;UNSIGNED&gt; | StructOrUnionSpecifier() | EnumSpecifier() | TypedefName() )
    * </PRE>
    */
   public void visit(TypeSpecifier n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( &lt;CONST&gt; | &lt;VOLATILE&gt; )
    * </PRE>
    */
   public void visit(TypeQualifier n, A argu);

   /**
    * <PRE>

    * structOrUnion -> StructOrUnion()
    * nodeChoice -> ( [ &lt;IDENTIFIER&gt; ] "{" StructDeclarationList() "}" | &lt;IDENTIFIER&gt; )
    * </PRE>
    */
   public void visit(StructOrUnionSpecifier n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( &lt;STRUCT&gt; | &lt;UNION&gt; )
    * </PRE>
    */
   public void visit(StructOrUnion n, A argu);

   /**
    * <PRE>
    * nodeList -> ( StructDeclaration() )+
    * </PRE>
    */
   public void visit(StructDeclarationList n, A argu);

   /**
    * <PRE>
    * initDeclarator -> InitDeclarator()
    * nodeListOptional -> ( "," InitDeclarator() )*
    * </PRE>
    */
   public void visit(InitDeclaratorList n, A argu);

   /**
    * <PRE>
    * declarator -> Declarator()
    * nodeOptional -> [ "=" Initializer() ]
    * </PRE>
    */
   public void visit(InitDeclarator n, A argu);

   /**
    * <PRE>
    * specifierQualifierList -> SpecifierQualifierList()
    * structDeclaratorList -> StructDeclaratorList()
    * nodeToken -> ";"
    * </PRE>
    */
   public void visit(StructDeclaration n, A argu);

   /**
    * <PRE>
    * nodeChoice -> TypeSpecifier() [ SpecifierQualifierList() ]
    *       | TypeQualifier() [ SpecifierQualifierList() ]
    * </PRE>
    */
   public void visit(SpecifierQualifierList n, A argu);

   /**
    * <PRE>
    * structDeclarator -> StructDeclarator()
    * nodeListOptional -> ( "," StructDeclarator() )*
    * </PRE>
    */
   public void visit(StructDeclaratorList n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( Declarator() | [ Declarator() ] ":" ConstantExpression() )
    * </PRE>
    */
   public void visit(StructDeclarator n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;ENUM&gt;
    * nodeChoice -> ( [ &lt;IDENTIFIER&gt; ] "{" EnumeratorList() "}" | &lt;IDENTIFIER&gt; )
    * </PRE>
    */
   public void visit(EnumSpecifier n, A argu);

   /**
    * <PRE>
    * enumerator -> Enumerator()
    * nodeListOptional -> ( "," Enumerator() )*
    * </PRE>
    */
   public void visit(EnumeratorList n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;IDENTIFIER&gt;
    * nodeOptional -> [ "=" ConstantExpression() ]
    * </PRE>
    */
   public void visit(Enumerator n, A argu);

   /**
    * <PRE>
    * nodeOptional -> [ Pointer() ]
    * directDeclarator -> DirectDeclarator()
    * </PRE>
    */
   public void visit(Declarator n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( t=&lt;IDENTIFIER&gt; | "(" Declarator() ")" )
    * nodeListOptional -> ( "[" [ ConstantExpression() ] "]" | "(" ParameterTypeList() ")" | "(" [ IdentifierList() ] ")" )*
    * </PRE>
    */
   public void visit(DirectDeclarator n, A argu);

   /**
    * <PRE>
    * nodeToken -> "*"
    * nodeOptional -> [ TypeQualifierList() ]
    * nodeOptional1 -> [ Pointer() ]
    * </PRE>
    */
   public void visit(Pointer n, A argu);

   /**
    * <PRE>
    * nodeList -> ( TypeQualifier() )+
    * </PRE>
    */
   public void visit(TypeQualifierList n, A argu);

   /**
    * <PRE>
    * parameterList -> ParameterList()
    * nodeOptional -> [ "," &lt;ELLIPSIS: "..."&gt; ]
    * </PRE>
    */
   public void visit(ParameterTypeList n, A argu);

   /**
    * <PRE>
    * parameterDeclaration -> ParameterDeclaration()
    * nodeListOptional -> ( "," ParameterDeclaration() )*
    * </PRE>
    */
   public void visit(ParameterList n, A argu);

   /**
    * <PRE>
    * declarationSpecifiers -> DeclarationSpecifiers()
    * nodeChoice -> ( Declarator() | [ AbstractDeclarator() ] )
    * </PRE>
    */
   public void visit(ParameterDeclaration n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;IDENTIFIER&gt;
    * nodeListOptional -> ( "," &lt;IDENTIFIER&gt; )*
    * </PRE>
    */
   public void visit(IdentifierList n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( AssignmentExpression() | "{" InitializerList() [ "," ] "}" )
    * </PRE>
    */
   public void visit(Initializer n, A argu);

   /**
    * <PRE>
    * initializer -> Initializer()
    * nodeListOptional -> ( "," Initializer() )*
    * </PRE>
    */
   public void visit(InitializerList n, A argu);

   /**
    * <PRE>
    * specifierQualifierList -> SpecifierQualifierList()
    * nodeOptional -> [ AbstractDeclarator() ]
    * </PRE>
    */
   public void visit(TypeName n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( Pointer() | [ Pointer() ] DirectAbstractDeclarator() )
    * </PRE>
    */
   public void visit(AbstractDeclarator n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( "(" AbstractDeclarator() ")" | "[" [ ConstantExpression() ] "]" | "(" [ ParameterTypeList() ] ")" )
    * nodeListOptional -> ( "[" [ ConstantExpression() ] "]" | "(" [ ParameterTypeList() ] ")" )*
    * </PRE>
    */
   public void visit(DirectAbstractDeclarator n, A argu);

   /**
    * <PRE>
    * nodeToken -> &lt;IDENTIFIER&gt;
    * </PRE>
    */
   public void visit(TypedefName n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( LabeledStatement() | ExpressionStatement() | CompoundStatement() | SelectionStatement() | IterationStatement() | JumpStatement() )
    * </PRE>
    */
   public void visit(Statement n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( &lt;IDENTIFIER&gt; ":" Statement() | &lt;CASE&gt; ConstantExpression() ":" Statement() | &lt;DFLT&gt; ":" Statement() )
    * </PRE>
    */
   public void visit(LabeledStatement n, A argu);

   /**
    * <PRE>
    * nodeOptional -> [ Expression() ]
    * nodeToken -> ";"
    * </PRE>
    */
   public void visit(ExpressionStatement n, A argu);

   /**
    * <PRE>
    * nodeToken -> "{"
    * nodeOptional -> [ DeclarationList() ]
    * nodeOptional1 -> [ StatementList() ]
    * nodeToken1 -> "}"
    * </PRE>
    */
   public void visit(CompoundStatement n, A argu);

   /**
    * <PRE>
    * nodeList -> ( Statement() )+
    * </PRE>
    */
   public void visit(StatementList n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( ( &lt;IF&gt; "(" Expression() ")" Statement() [ ElseStatement() ] ) | &lt;SWITCH&gt; "(" Expression() ")" Statement() )
    * </PRE>
    */
   public void visit(SelectionStatement n, A argu);

   /**
    * <PRE>
    * nodeSequence -> ( &lt;ELSE&gt; Statement() )
    * </PRE>
    */
   public void visit(ElseStatement n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( &lt;WHILE&gt; "(" Expression() ")" Statement() | &lt;DO&gt; Statement() &lt;WHILE&gt; "(" Expression() ")" ";" | &lt;FOR&gt; "(" [ Expression() ] ";" [ Expression() ] ";" [ Expression() ] ")" Statement() )
    * </PRE>
    */
   public void visit(IterationStatement n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( &lt;GOTO&gt; &lt;IDENTIFIER&gt; ";" | &lt;CONTINUE&gt; ";" | &lt;BREAK&gt; ";" | &lt;RETURN&gt; [ Expression() ] ";" )
    * </PRE>
    */
   public void visit(JumpStatement n, A argu);

   /**
    * <PRE>
    * assignmentExpression -> AssignmentExpression()
    * nodeListOptional -> ( "," AssignmentExpression() )*
    * </PRE>
    */
   public void visit(Expression n, A argu);

   /**
    * <PRE>
    * nodeChoice -> UnaryExpression() AssignmentOperator() AssignmentExpression()
    *       | ConditionalExpression()
    * </PRE>
    */
   public void visit(AssignmentExpression n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( "=" | &lt;MULT_EQ: "*="&gt; | &lt;DIV_EQ: "/="&gt; | &lt;MOD_EQ: "%="&gt; | &lt;PLUS_EQ: "+="&gt; | &lt;SUB_EQ: "-="&gt; | &lt;LSH_EQ: "&lt;&lt;="&gt; | &lt;RSH_EQ: "&gt;&gt;="&gt; | &lt;AND_EQ: "&="&gt; | &lt;XOR_EQ: "^="&gt; | &lt;OR_EQ: "|="&gt; )
    * </PRE>
    */
   public void visit(AssignmentOperator n, A argu);

   /**
    * <PRE>
    * logicalORExpression -> LogicalORExpression()
    * nodeOptional -> [ "?" Expression() ":" ConditionalExpression() ]
    * </PRE>
    */
   public void visit(ConditionalExpression n, A argu);

   /**
    * <PRE>
    * conditionalExpression -> ConditionalExpression()
    * </PRE>
    */
   public void visit(ConstantExpression n, A argu);

   /**
    * <PRE>
    * logicalANDExpression -> LogicalANDExpression()
    * nodeOptional -> [ "||" LogicalORExpression() ]
    * </PRE>
    */
   public void visit(LogicalORExpression n, A argu);

   /**
    * <PRE>
    * inclusiveORExpression -> InclusiveORExpression()
    * nodeOptional -> [ "&&" LogicalANDExpression() ]
    * </PRE>
    */
   public void visit(LogicalANDExpression n, A argu);

   /**
    * <PRE>
    * exclusiveORExpression -> ExclusiveORExpression()
    * nodeOptional -> [ "|" InclusiveORExpression() ]
    * </PRE>
    */
   public void visit(InclusiveORExpression n, A argu);

   /**
    * <PRE>
    * aNDExpression -> ANDExpression()
    * nodeOptional -> [ "^" ExclusiveORExpression() ]
    * </PRE>
    */
   public void visit(ExclusiveORExpression n, A argu);

   /**
    * <PRE>
    * equalityExpression -> EqualityExpression()
    * nodeOptional -> [ "&" ANDExpression() ]
    * </PRE>
    */
   public void visit(ANDExpression n, A argu);

   /**
    * <PRE>
    * relationalExpression -> RelationalExpression()
    * nodeOptional -> [ ( &lt;EQ: "=="&gt; | &lt;NE: "!="&gt; ) EqualityExpression() ]
    * </PRE>
    */
   public void visit(EqualityExpression n, A argu);

   /**
    * <PRE>
    * shiftExpression -> ShiftExpression()
    * nodeOptional -> [ ( "&lt;" | "&gt;" | &lt;LE: "&lt;="&gt; | &lt;GE: "&gt;="&gt; ) RelationalExpression() ]
    * </PRE>
    */
   public void visit(RelationalExpression n, A argu);

   /**
    * <PRE>
    * additiveExpression -> AdditiveExpression()
    * nodeOptional -> [ ( &lt;LSH: "&lt;&lt;"&gt; | &lt;RSH: "&gt;&gt;"&gt; ) ShiftExpression() ]
    * </PRE>
    */
   public void visit(ShiftExpression n, A argu);

   /**
    * <PRE>
    * multiplicativeExpression -> MultiplicativeExpression()
    * nodeOptional -> [ ( "+" | "-" ) AdditiveExpression() ]
    * </PRE>
    */
   public void visit(AdditiveExpression n, A argu);

   /**
    * <PRE>
    * castExpression -> CastExpression()
    * nodeOptional -> [ ( "*" | "/" | "%" ) MultiplicativeExpression() ]
    * </PRE>
    */
   public void visit(MultiplicativeExpression n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( "(" TypeName() ")" CastExpression() | UnaryExpression() )
    * </PRE>
    */
   public void visit(CastExpression n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( PostfixExpression() | "++" UnaryExpression() | "--" UnaryExpression() | UnaryOperator() CastExpression() | &lt;SIZEOF&gt; ( UnaryExpression() | "(" TypeName() ")" ) )
    * </PRE>
    */
   public void visit(UnaryExpression n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( "&" | "*" | "+" | "-" | "~" | "!" )
    * </PRE>
    */
   public void visit(UnaryOperator n, A argu);

   /**
    * <PRE>
    * primaryExpression -> PrimaryExpression()
    * nodeListOptional -> ( "[" Expression() "]" | "(" [ ArgumentExpressionList() ] ")" | "." &lt;IDENTIFIER&gt; | &lt;ARROW: "-&gt;"&gt; &lt;IDENTIFIER&gt; | "++" | "--" )*
    * </PRE>
    */
   public void visit(PostfixExpression n, A argu);

   /**
    * <PRE>
    * nodeChoice -> ( &lt;IDENTIFIER&gt; | Constant() | "(" Expression() ")" )
    * </PRE>
    */
   public void visit(PrimaryExpression n, A argu);

   /**
    * <PRE>
    * assignmentExpression -> AssignmentExpression()
    * nodeListOptional -> ( "," AssignmentExpression() )*
    * </PRE>
    */
   public void visit(ArgumentExpressionList n, A argu);

   /**
    * <PRE>
    * nodeChoice -> &lt;NUMBER&gt;
    *       | &lt;CHARACTER_LITERAL&gt;
    *       | ( &lt;STRING_LITERAL&gt; )+
    * </PRE>
    */
   public void visit(Constant n, A argu);

}

