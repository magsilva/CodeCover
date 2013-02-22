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
public class GJNoArguDepthFirst<R> implements GJNoArguVisitor<R> {
   //
   // Auto class visitors--probably don't need to be overridden.
   //
   public R visit(NodeList n) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeListOptional n) {
      if ( n.present() ) {
         R _ret=null;
         int _count=0;
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this);
            _count++;
         }
         return _ret;
      }
      else
         return null;
   }

   public R visit(NodeOptional n) {
      if ( n.present() )
         return n.node.accept(this);
      else
         return null;
   }

   public R visit(NodeSequence n) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeToken n) { return null; }

   //
   // User-generated visitor methods below
   //

   /**
    * <PRE>
    * f0 -> ( ExternalDeclaration() )+
    * </PRE>
    */
   public R visit(TranslationUnit n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( FunctionDefinition() | Declaration() )
    * </PRE>
    */
   public R visit(ExternalDeclaration n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> [ DeclarationSpecifiers() ]
    * f1 -> Declarator()
    * f2 -> [ DeclarationList() ]
    * f3 -> CompoundStatement()
    * </PRE>
    */
   public R visit(FunctionDefinition n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> DeclarationSpecifiers()
    * f1 -> [ InitDeclaratorList() ]
    * f2 -> ";"
    * </PRE>
    */
   public R visit(Declaration n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( Declaration() )+
    * </PRE>
    */
   public R visit(DeclarationList n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> StorageClassSpecifier() [ DeclarationSpecifiers() ]
    *       | TypeSpecifier() [ DeclarationSpecifiers() ]
    *       | TypeQualifier() [ DeclarationSpecifiers() ]
    * </PRE>
    */
   public R visit(DeclarationSpecifiers n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( &lt;AUTO&gt; | &lt;REGISTER&gt; | &lt;STATIC&gt; | &lt;EXTERN&gt; | &lt;TYPEDEF&gt; )
    * </PRE>
    */
   public R visit(StorageClassSpecifier n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( &lt;VOID&gt; | &lt;CHAR&gt; | &lt;SHORT&gt; | &lt;INT&gt; | &lt;LONG&gt; | &lt;FLOAT&gt; | &lt;DOUBLE&gt; | &lt;SIGNED&gt; | &lt;UNSIGNED&gt; | StructOrUnionSpecifier() | EnumSpecifier() | TypedefName() )
    * </PRE>
    */
   public R visit(TypeSpecifier n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( &lt;CONST&gt; | &lt;VOLATILE&gt; )
    * </PRE>
    */
   public R visit(TypeQualifier n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>

    * f0 -> StructOrUnion()
    * f1 -> ( [ &lt;IDENTIFIER&gt; ] "{" StructDeclarationList() "}" | &lt;IDENTIFIER&gt; )
    * </PRE>
    */
   public R visit(StructOrUnionSpecifier n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( &lt;STRUCT&gt; | &lt;UNION&gt; )
    * </PRE>
    */
   public R visit(StructOrUnion n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( StructDeclaration() )+
    * </PRE>
    */
   public R visit(StructDeclarationList n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> InitDeclarator()
    * f1 -> ( "," InitDeclarator() )*
    * </PRE>
    */
   public R visit(InitDeclaratorList n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> Declarator()
    * f1 -> [ "=" Initializer() ]
    * </PRE>
    */
   public R visit(InitDeclarator n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> SpecifierQualifierList()
    * f1 -> StructDeclaratorList()
    * f2 -> ";"
    * </PRE>
    */
   public R visit(StructDeclaration n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> TypeSpecifier() [ SpecifierQualifierList() ]
    *       | TypeQualifier() [ SpecifierQualifierList() ]
    * </PRE>
    */
   public R visit(SpecifierQualifierList n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> StructDeclarator()
    * f1 -> ( "," StructDeclarator() )*
    * </PRE>
    */
   public R visit(StructDeclaratorList n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( Declarator() | [ Declarator() ] ":" ConstantExpression() )
    * </PRE>
    */
   public R visit(StructDeclarator n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> &lt;ENUM&gt;
    * f1 -> ( [ &lt;IDENTIFIER&gt; ] "{" EnumeratorList() "}" | &lt;IDENTIFIER&gt; )
    * </PRE>
    */
   public R visit(EnumSpecifier n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> Enumerator()
    * f1 -> ( "," Enumerator() )*
    * </PRE>
    */
   public R visit(EnumeratorList n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> &lt;IDENTIFIER&gt;
    * f1 -> [ "=" ConstantExpression() ]
    * </PRE>
    */
   public R visit(Enumerator n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> [ Pointer() ]
    * f1 -> DirectDeclarator()
    * </PRE>
    */
   public R visit(Declarator n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( t=&lt;IDENTIFIER&gt; | "(" Declarator() ")" )
    * f1 -> ( "[" [ ConstantExpression() ] "]" | "(" ParameterTypeList() ")" | "(" [ IdentifierList() ] ")" )*
    * </PRE>
    */
   public R visit(DirectDeclarator n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> "*"
    * f1 -> [ TypeQualifierList() ]
    * f2 -> [ Pointer() ]
    * </PRE>
    */
   public R visit(Pointer n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( TypeQualifier() )+
    * </PRE>
    */
   public R visit(TypeQualifierList n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ParameterList()
    * f1 -> [ "," &lt;ELLIPSIS: "..."&gt; ]
    * </PRE>
    */
   public R visit(ParameterTypeList n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ParameterDeclaration()
    * f1 -> ( "," ParameterDeclaration() )*
    * </PRE>
    */
   public R visit(ParameterList n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> DeclarationSpecifiers()
    * f1 -> ( Declarator() | [ AbstractDeclarator() ] )
    * </PRE>
    */
   public R visit(ParameterDeclaration n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> &lt;IDENTIFIER&gt;
    * f1 -> ( "," &lt;IDENTIFIER&gt; )*
    * </PRE>
    */
   public R visit(IdentifierList n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( AssignmentExpression() | "{" InitializerList() [ "," ] "}" )
    * </PRE>
    */
   public R visit(Initializer n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> Initializer()
    * f1 -> ( "," Initializer() )*
    * </PRE>
    */
   public R visit(InitializerList n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> SpecifierQualifierList()
    * f1 -> [ AbstractDeclarator() ]
    * </PRE>
    */
   public R visit(TypeName n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( Pointer() | [ Pointer() ] DirectAbstractDeclarator() )
    * </PRE>
    */
   public R visit(AbstractDeclarator n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( "(" AbstractDeclarator() ")" | "[" [ ConstantExpression() ] "]" | "(" [ ParameterTypeList() ] ")" )
    * f1 -> ( "[" [ ConstantExpression() ] "]" | "(" [ ParameterTypeList() ] ")" )*
    * </PRE>
    */
   public R visit(DirectAbstractDeclarator n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> &lt;IDENTIFIER&gt;
    * </PRE>
    */
   public R visit(TypedefName n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( LabeledStatement() | ExpressionStatement() | CompoundStatement() | SelectionStatement() | IterationStatement() | JumpStatement() )
    * </PRE>
    */
   public R visit(Statement n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( &lt;IDENTIFIER&gt; ":" Statement() | &lt;CASE&gt; ConstantExpression() ":" Statement() | &lt;DFLT&gt; ":" Statement() )
    * </PRE>
    */
   public R visit(LabeledStatement n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> [ Expression() ]
    * f1 -> ";"
    * </PRE>
    */
   public R visit(ExpressionStatement n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> "{"
    * f1 -> [ DeclarationList() ]
    * f2 -> [ StatementList() ]
    * f3 -> "}"
    * </PRE>
    */
   public R visit(CompoundStatement n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      n.f3.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( Statement() )+
    * </PRE>
    */
   public R visit(StatementList n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( ( &lt;IF&gt; "(" Expression() ")" Statement() [ ElseStatement() ] ) | &lt;SWITCH&gt; "(" Expression() ")" Statement() )
    * </PRE>
    */
   public R visit(SelectionStatement n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( &lt;ELSE&gt; Statement() )
    * </PRE>
    */
   public R visit(ElseStatement n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( &lt;WHILE&gt; "(" Expression() ")" Statement() | &lt;DO&gt; Statement() &lt;WHILE&gt; "(" Expression() ")" ";" | &lt;FOR&gt; "(" [ Expression() ] ";" [ Expression() ] ";" [ Expression() ] ")" Statement() )
    * </PRE>
    */
   public R visit(IterationStatement n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( &lt;GOTO&gt; &lt;IDENTIFIER&gt; ";" | &lt;CONTINUE&gt; ";" | &lt;BREAK&gt; ";" | &lt;RETURN&gt; [ Expression() ] ";" )
    * </PRE>
    */
   public R visit(JumpStatement n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> AssignmentExpression()
    * f1 -> ( "," AssignmentExpression() )*
    * </PRE>
    */
   public R visit(Expression n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> UnaryExpression() AssignmentOperator() AssignmentExpression()
    *       | ConditionalExpression()
    * </PRE>
    */
   public R visit(AssignmentExpression n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( "=" | &lt;MULT_EQ: "*="&gt; | &lt;DIV_EQ: "/="&gt; | &lt;MOD_EQ: "%="&gt; | &lt;PLUS_EQ: "+="&gt; | &lt;SUB_EQ: "-="&gt; | &lt;LSH_EQ: "&lt;&lt;="&gt; | &lt;RSH_EQ: "&gt;&gt;="&gt; | &lt;AND_EQ: "&="&gt; | &lt;XOR_EQ: "^="&gt; | &lt;OR_EQ: "|="&gt; )
    * </PRE>
    */
   public R visit(AssignmentOperator n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> LogicalORExpression()
    * f1 -> [ "?" Expression() ":" ConditionalExpression() ]
    * </PRE>
    */
   public R visit(ConditionalExpression n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ConditionalExpression()
    * </PRE>
    */
   public R visit(ConstantExpression n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> LogicalANDExpression()
    * f1 -> [ "||" LogicalORExpression() ]
    * </PRE>
    */
   public R visit(LogicalORExpression n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> InclusiveORExpression()
    * f1 -> [ "&&" LogicalANDExpression() ]
    * </PRE>
    */
   public R visit(LogicalANDExpression n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ExclusiveORExpression()
    * f1 -> [ "|" InclusiveORExpression() ]
    * </PRE>
    */
   public R visit(InclusiveORExpression n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ANDExpression()
    * f1 -> [ "^" ExclusiveORExpression() ]
    * </PRE>
    */
   public R visit(ExclusiveORExpression n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> EqualityExpression()
    * f1 -> [ "&" ANDExpression() ]
    * </PRE>
    */
   public R visit(ANDExpression n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> RelationalExpression()
    * f1 -> [ ( &lt;EQ: "=="&gt; | &lt;NE: "!="&gt; ) EqualityExpression() ]
    * </PRE>
    */
   public R visit(EqualityExpression n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ShiftExpression()
    * f1 -> [ ( "&lt;" | "&gt;" | &lt;LE: "&lt;="&gt; | &lt;GE: "&gt;="&gt; ) RelationalExpression() ]
    * </PRE>
    */
   public R visit(RelationalExpression n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> AdditiveExpression()
    * f1 -> [ ( &lt;LSH: "&lt;&lt;"&gt; | &lt;RSH: "&gt;&gt;"&gt; ) ShiftExpression() ]
    * </PRE>
    */
   public R visit(ShiftExpression n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> MultiplicativeExpression()
    * f1 -> [ ( "+" | "-" ) AdditiveExpression() ]
    * </PRE>
    */
   public R visit(AdditiveExpression n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> CastExpression()
    * f1 -> [ ( "*" | "/" | "%" ) MultiplicativeExpression() ]
    * </PRE>
    */
   public R visit(MultiplicativeExpression n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( "(" TypeName() ")" CastExpression() | UnaryExpression() )
    * </PRE>
    */
   public R visit(CastExpression n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( PostfixExpression() | "++" UnaryExpression() | "--" UnaryExpression() | UnaryOperator() CastExpression() | &lt;SIZEOF&gt; ( UnaryExpression() | "(" TypeName() ")" ) )
    * </PRE>
    */
   public R visit(UnaryExpression n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( "&" | "*" | "+" | "-" | "~" | "!" )
    * </PRE>
    */
   public R visit(UnaryOperator n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> PrimaryExpression()
    * f1 -> ( "[" Expression() "]" | "(" [ ArgumentExpressionList() ] ")" | "." &lt;IDENTIFIER&gt; | &lt;ARROW: "-&gt;"&gt; &lt;IDENTIFIER&gt; | "++" | "--" )*
    * </PRE>
    */
   public R visit(PostfixExpression n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> ( &lt;IDENTIFIER&gt; | Constant() | "(" Expression() ")" )
    * </PRE>
    */
   public R visit(PrimaryExpression n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> AssignmentExpression()
    * f1 -> ( "," AssignmentExpression() )*
    * </PRE>
    */
   public R visit(ArgumentExpressionList n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * <PRE>
    * f0 -> &lt;NUMBER&gt;
    *       | &lt;CHARACTER_LITERAL&gt;
    *       | ( &lt;STRING_LITERAL&gt; )+
    * </PRE>
    */
   public R visit(Constant n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

}
