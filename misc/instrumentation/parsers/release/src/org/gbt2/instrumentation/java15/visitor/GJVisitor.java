﻿///////////////////////////////////////////////////////////////////////////////
//
// $Id$
// 
// created by: Christoph Müller
// created at: 24.02.2007 17:30:00
//
///////////////////////////////////////////////////////////////////////////////

//
// Generated by JTB 1.3.2
//

package org.gbt2.instrumentation.java15.visitor;

import org.gbt2.instrumentation.java15.syntaxtree.*;

/**
 * All GJ visitors must implement this interface.
 */

public interface GJVisitor<R, A> {

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
         * 
         * f0 -> [ PackageDeclaration() ] f1 -> ( ImportDeclaration() )* f2 -> (
         * TypeDeclaration() )* f3 -> ( &lt;"\u001a"&gt; )? f4 -> (
         * &lt;STUFF_TO_IGNORE: ~[]&gt; )? f5 -> &lt;EOF&gt;
         * 
         * </PRE>
         */
    public R visit(CompilationUnit n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> Modifiers() f1 -> "package" f2 -> Name() f3 -> ";"
         * 
         * </PRE>
         */
    public R visit(PackageDeclaration n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "import" f1 -> [ "static" ] f2 -> Name() f3 -> [ "." "*" ] f4 ->
         * ";"
         * 
         * </PRE>
         */
    public R visit(ImportDeclaration n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ( ( "public" | "static" | "protected" | "private" | "final" |
         * "abstract" | "synchronized" | "native" | "transient" | "volatile" |
         * "strictfp" | Annotation() ) )*
         * 
         * </PRE>
         */
    public R visit(Modifiers n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ";" | Modifiers() ( ClassOrInterfaceDeclaration() |
         * EnumDeclaration() | AnnotationTypeDeclaration() )
         * 
         * </PRE>
         */
    public R visit(TypeDeclaration n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ( "class" | "interface" ) f1 -> &lt;IDENTIFIER&gt; f2 -> [
         * TypeParameters() ] f3 -> [ ExtendsList(isInterface) ] f4 -> [
         * ImplementsList(isInterface) ] f5 -> ClassOrInterfaceBody(isInterface)
         * 
         * </PRE>
         */
    public R visit(ClassOrInterfaceDeclaration n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "extends" f1 -> ClassOrInterfaceType() f2 -> ( ","
         * ClassOrInterfaceType() )*
         * 
         * </PRE>
         */
    public R visit(ExtendsList n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "implements" f1 -> ClassOrInterfaceType() f2 -> ( ","
         * ClassOrInterfaceType() )*
         * 
         * </PRE>
         */
    public R visit(ImplementsList n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "enum" f1 -> &lt;IDENTIFIER&gt; f2 -> [ ImplementsList(false) ]
         * f3 -> EnumBody()
         * 
         * </PRE>
         */
    public R visit(EnumDeclaration n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "{" f1 -> [ EnumConstant() ( "," EnumConstant() )* ] f2 -> [
         * "," ] f3 -> [ ";" ( ClassOrInterfaceBodyDeclaration(false) )* ] f4 ->
         * "}"
         * 
         * </PRE>
         */
    public R visit(EnumBody n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> Modifiers() f1 -> &lt;IDENTIFIER&gt; f2 -> [ Arguments() ] f3 -> [
         * ClassOrInterfaceBody(false) ]
         * 
         * </PRE>
         */
    public R visit(EnumConstant n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "&lt;" f1 -> TypeParameter() f2 -> ( "," TypeParameter() )* f3 ->
         * "&gt;"
         * 
         * </PRE>
         */
    public R visit(TypeParameters n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> &lt;IDENTIFIER&gt; f1 -> [ TypeBound() ]
         * 
         * </PRE>
         */
    public R visit(TypeParameter n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "extends" f1 -> ClassOrInterfaceType() f2 -> ( "&"
         * ClassOrInterfaceType() )*
         * 
         * </PRE>
         */
    public R visit(TypeBound n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "{" f1 -> ( ClassOrInterfaceBodyDeclaration(isInterface) )* f2 ->
         * "}"
         * 
         * </PRE>
         */
    public R visit(ClassOrInterfaceBody n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> Initializer() | Modifiers() ( ClassOrInterfaceDeclaration() |
         * EnumDeclaration() | ConstructorDeclaration() | FieldDeclaration() |
         * MethodDeclaration() ) | ";"
         * 
         * </PRE>
         */
    public R visit(ClassOrInterfaceBodyDeclaration n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> Type() f1 -> VariableDeclarator() f2 -> ( ","
         * VariableDeclarator() )* f3 -> ";"
         * 
         * </PRE>
         */
    public R visit(FieldDeclaration n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> VariableDeclaratorId() f1 -> [ "=" VariableInitializer() ]
         * 
         * </PRE>
         */
    public R visit(VariableDeclarator n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> &lt;IDENTIFIER&gt; f1 -> ( "[" "]" )*
         * 
         * </PRE>
         */
    public R visit(VariableDeclaratorId n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ArrayInitializer() | Expression()
         * 
         * </PRE>
         */
    public R visit(VariableInitializer n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "{" f1 -> [ VariableInitializer() ( "," VariableInitializer() )* ]
         * f2 -> [ "," ] f3 -> "}"
         * 
         * </PRE>
         */
    public R visit(ArrayInitializer n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> [ TypeParameters() ] f1 -> ResultType() f2 ->
         * MethodDeclarator() f3 -> [ "throws" NameList() ] f4 -> ( Block() |
         * ";" )
         * 
         * </PRE>
         */
    public R visit(MethodDeclaration n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> &lt;IDENTIFIER&gt; f1 -> FormalParameters() f2 -> ( "[" "]" )*
         * 
         * </PRE>
         */
    public R visit(MethodDeclarator n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "(" f1 -> [ FormalParameter() ( "," FormalParameter() )* ] f2 ->
         * ")"
         * 
         * </PRE>
         */
    public R visit(FormalParameters n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> Modifiers() f1 -> Type() f2 -> [ "..." ] f3 ->
         * VariableDeclaratorId()
         * 
         * </PRE>
         */
    public R visit(FormalParameter n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> [ TypeParameters() ] f1 -> &lt;IDENTIFIER&gt; f2 ->
         * FormalParameters() f3 -> [ "throws" NameList() ] f4 -> "{" f5 -> [
         * ExplicitConstructorInvocation() ] f6 -> ( BlockStatement() )* f7 ->
         * "}"
         * 
         * </PRE>
         */
    public R visit(ConstructorDeclaration n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ( &lt;IDENTIFIER&gt; "." )* f1 -> [ "this" "." ] f2 -> [
         * TypeArguments() ] f3 -> ( "this" | "super" ) f4 -> Arguments() f5 ->
         * ";"
         * 
         * </PRE>
         */
    public R visit(ExplicitConstructorInvocation n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> [ "static" ] f1 -> Block()
         * 
         * </PRE>
         */
    public R visit(Initializer n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ReferenceType() | PrimitiveType()
         * 
         * </PRE>
         */
    public R visit(Type n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> PrimitiveType() ( "[" "]" )+ | ( ClassOrInterfaceType() ) ( "["
         * "]" )*
         * 
         * </PRE>
         */
    public R visit(ReferenceType n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> &lt;IDENTIFIER&gt; f1 -> [ TypeArguments() ] f2 -> ( "."
         * &lt;IDENTIFIER&gt; [ TypeArguments() ] )*
         * 
         * </PRE>
         */
    public R visit(ClassOrInterfaceType n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "&lt;" f1 -> TypeArgument() f2 -> ( "," TypeArgument() )* f3 ->
         * "&gt;"
         * 
         * </PRE>
         */
    public R visit(TypeArguments n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ReferenceType() | "?" [ WildcardBounds() ]
         * 
         * </PRE>
         */
    public R visit(TypeArgument n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "extends" ReferenceType() | "super" ReferenceType()
         * 
         * </PRE>
         */
    public R visit(WildcardBounds n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "boolean" | "char" | "byte" | "short" | "int" | "long" |
         * "float" | "double"
         * 
         * </PRE>
         */
    public R visit(PrimitiveType n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "void" | Type()
         * 
         * </PRE>
         */
    public R visit(ResultType n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> &lt;IDENTIFIER&gt; f1 -> ( "." &lt;IDENTIFIER&gt; )*
         * 
         * </PRE>
         */
    public R visit(Name n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> Name() f1 -> ( "," Name() )*
         * 
         * </PRE>
         */
    public R visit(NameList n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ConditionalExpression() f1 -> [ AssignmentOperator()
         * Expression() ]
         * 
         * </PRE>
         */
    public R visit(Expression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "=" | "*=" | "/=" | "%=" | "+=" | "-=" | "&lt;&lt;=" |
         * "&gt;&gt;=" | "&gt;&gt;&gt;=" | "&=" | "^=" | "|="
         * 
         * </PRE>
         */
    public R visit(AssignmentOperator n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ConditionalOrExpression() f1 -> [ "?" Expression() ":"
         * Expression() ]
         * 
         * </PRE>
         */
    public R visit(ConditionalExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ConditionalAndExpression() f1 -> ( "||"
         * ConditionalAndExpression() )*
         * 
         * </PRE>
         */
    public R visit(ConditionalOrExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> InclusiveOrExpression() f1 -> ( "&&" InclusiveOrExpression() )*
         * 
         * </PRE>
         */
    public R visit(ConditionalAndExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ExclusiveOrExpression() f1 -> ( "|" ExclusiveOrExpression() )*
         * 
         * </PRE>
         */
    public R visit(InclusiveOrExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> AndExpression() f1 -> ( "^" AndExpression() )*
         * 
         * </PRE>
         */
    public R visit(ExclusiveOrExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> EqualityExpression() f1 -> ( "&" EqualityExpression() )*
         * 
         * </PRE>
         */
    public R visit(AndExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> InstanceOfExpression() f1 -> ( ( "==" | "!=" )
         * InstanceOfExpression() )*
         * 
         * </PRE>
         */
    public R visit(EqualityExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> RelationalExpression() f1 -> [ "instanceof" Type() ]
         * 
         * </PRE>
         */
    public R visit(InstanceOfExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ShiftExpression() f1 -> ( ( "&lt;" | "&gt;" | "&lt;=" | "&gt;=" )
         * ShiftExpression() )*
         * 
         * </PRE>
         */
    public R visit(RelationalExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> AdditiveExpression() f1 -> ( ( "&lt;&lt;" | RSIGNEDSHIFT() |
         * RUNSIGNEDSHIFT() ) AdditiveExpression() )*
         * 
         * </PRE>
         */
    public R visit(ShiftExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> MultiplicativeExpression() f1 -> ( ( "+" | "-" )
         * MultiplicativeExpression() )*
         * 
         * </PRE>
         */
    public R visit(AdditiveExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> UnaryExpression() f1 -> ( ( "*" | "/" | "%" ) UnaryExpression() )*
         * 
         * </PRE>
         */
    public R visit(MultiplicativeExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ( "+" | "-" ) UnaryExpression() | PreIncrementExpression() |
         * PreDecrementExpression() | UnaryExpressionNotPlusMinus()
         * 
         * </PRE>
         */
    public R visit(UnaryExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "++" f1 -> PrimaryExpression()
         * 
         * </PRE>
         */
    public R visit(PreIncrementExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "--" f1 -> PrimaryExpression()
         * 
         * </PRE>
         */
    public R visit(PreDecrementExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ( "~" | "!" ) UnaryExpression() | CastExpression() |
         * PostfixExpression()
         * 
         * </PRE>
         */
    public R visit(UnaryExpressionNotPlusMinus n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "(" PrimitiveType() | "(" Type() "[" "]" | "(" Type() ")" ( "~" |
         * "!" | "(" | &lt;IDENTIFIER&gt; | "this" | "super" | "new" | Literal() )
         * 
         * </PRE>
         */
    public R visit(CastLookahead n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> PrimaryExpression() f1 -> [ "++" | "--" ]
         * 
         * </PRE>
         */
    public R visit(PostfixExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "(" Type() ")" UnaryExpression() | "(" Type() ")"
         * UnaryExpressionNotPlusMinus()
         * 
         * </PRE>
         */
    public R visit(CastExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> PrimaryPrefix() f1 -> ( PrimarySuffix() )*
         * 
         * </PRE>
         */
    public R visit(PrimaryExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "." f1 -> TypeArguments() f2 -> &lt;IDENTIFIER&gt;
         * 
         * </PRE>
         */
    public R visit(MemberSelector n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> Literal() | ( &lt;IDENTIFIER&gt; "." )* "this" | "super" "."
         * &lt;IDENTIFIER&gt; | "(" Expression() ")" | AllocationExpression() |
         * ResultType() "." "class" | Name()
         * 
         * </PRE>
         */
    public R visit(PrimaryPrefix n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "." "super" | "." "this" | "." AllocationExpression() |
         * MemberSelector() | "[" Expression() "]" | "." &lt;IDENTIFIER&gt; |
         * Arguments()
         * 
         * </PRE>
         */
    public R visit(PrimarySuffix n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> &lt;INTEGER_LITERAL&gt; | &lt;FLOATING_POINT_LITERAL&gt; |
         * &lt;CHARACTER_LITERAL&gt; | &lt;STRING_LITERAL&gt; | BooleanLiteral() |
         * NullLiteral()
         * 
         * </PRE>
         */
    public R visit(Literal n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "true" | "false"
         * 
         * </PRE>
         */
    public R visit(BooleanLiteral n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "null"
         * 
         * </PRE>
         */
    public R visit(NullLiteral n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "(" f1 -> [ ArgumentList() ] f2 -> ")"
         * 
         * </PRE>
         */
    public R visit(Arguments n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> Expression() f1 -> ( "," Expression() )*
         * 
         * </PRE>
         */
    public R visit(ArgumentList n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "new" PrimitiveType() ArrayDimsAndInits() | "new"
         * ClassOrInterfaceType() [ TypeArguments() ] ( ArrayDimsAndInits() |
         * Arguments() [ ClassOrInterfaceBody(false) ] )
         * 
         * </PRE>
         */
    public R visit(AllocationExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ( "[" Expression() "]" )+ ( "[" "]" )* | ( "[" "]" )+
         * ArrayInitializer()
         * 
         * </PRE>
         */
    public R visit(ArrayDimsAndInits n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> LabeledStatement() | AssertStatement() | Block() |
         * EmptyStatement() | StatementExpression() ";" | SwitchStatement() |
         * IfStatement() | WhileStatement() | DoStatement() | ForStatement() |
         * BreakStatement() | ContinueStatement() | ReturnStatement() |
         * ThrowStatement() | SynchronizedStatement() | TryStatement()
         * 
         * </PRE>
         */
    public R visit(Statement n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "assert" f1 -> Expression() f2 -> [ ":" Expression() ] f3 ->
         * ";"
         * 
         * </PRE>
         */
    public R visit(AssertStatement n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> &lt;IDENTIFIER&gt; f1 -> ":" f2 -> Statement()
         * 
         * </PRE>
         */
    public R visit(LabeledStatement n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "{" f1 -> ( BlockStatement() )* f2 -> "}"
         * 
         * </PRE>
         */
    public R visit(Block n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> LocalVariableDeclaration() ";" | Statement() |
         * ClassOrInterfaceDeclaration()
         * 
         * </PRE>
         */
    public R visit(BlockStatement n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> Modifiers() f1 -> Type() f2 -> VariableDeclarator() f3 -> ( ","
         * VariableDeclarator() )*
         * 
         * </PRE>
         */
    public R visit(LocalVariableDeclaration n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ";"
         * 
         * </PRE>
         */
    public R visit(EmptyStatement n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> PreIncrementExpression() | PreDecrementExpression() |
         * PrimaryExpression() [ "++" | "--" | AssignmentOperator() Expression() ]
         * 
         * </PRE>
         */
    public R visit(StatementExpression n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "switch" f1 -> "(" f2 -> Expression() f3 -> ")" f4 -> "{" f5 -> (
         * SwitchLabel() ( BlockStatement() )* )* f6 -> "}"
         * 
         * </PRE>
         */
    public R visit(SwitchStatement n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "case" Expression() ":" | "default" ":"
         * 
         * </PRE>
         */
    public R visit(SwitchLabel n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "if" f1 -> "(" f2 -> Expression() f3 -> ")" f4 -> Statement()
         * f5 -> [ "else" Statement() ]
         * 
         * </PRE>
         */
    public R visit(IfStatement n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "while" f1 -> "(" f2 -> Expression() f3 -> ")" f4 ->
         * Statement()
         * 
         * </PRE>
         */
    public R visit(WhileStatement n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "do" f1 -> Statement() f2 -> "while" f3 -> "(" f4 ->
         * Expression() f5 -> ")" f6 -> ";"
         * 
         * </PRE>
         */
    public R visit(DoStatement n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "for" f1 -> "(" f2 -> ( Modifiers() Type() &lt;IDENTIFIER&gt;
         * ":" Expression() | [ ForInit() ] ";" [ Expression() ] ";" [
         * ForUpdate() ] ) f3 -> ")" f4 -> Statement()
         * 
         * </PRE>
         */
    public R visit(ForStatement n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> LocalVariableDeclaration() | StatementExpressionList()
         * 
         * </PRE>
         */
    public R visit(ForInit n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> StatementExpression() f1 -> ( "," StatementExpression() )*
         * 
         * </PRE>
         */
    public R visit(StatementExpressionList n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> StatementExpressionList()
         * 
         * </PRE>
         */
    public R visit(ForUpdate n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "break" f1 -> [ &lt;IDENTIFIER&gt; ] f2 -> ";"
         * 
         * </PRE>
         */
    public R visit(BreakStatement n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "continue" f1 -> [ &lt;IDENTIFIER&gt; ] f2 -> ";"
         * 
         * </PRE>
         */
    public R visit(ContinueStatement n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "return" f1 -> [ Expression() ] f2 -> ";"
         * 
         * </PRE>
         */
    public R visit(ReturnStatement n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "throw" f1 -> Expression() f2 -> ";"
         * 
         * </PRE>
         */
    public R visit(ThrowStatement n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "synchronized" f1 -> "(" f2 -> Expression() f3 -> ")" f4 ->
         * Block()
         * 
         * </PRE>
         */
    public R visit(SynchronizedStatement n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "try" f1 -> Block() f2 -> ( "catch" "(" FormalParameter() ")"
         * Block() )* f3 -> [ "finally" Block() ]
         * 
         * </PRE>
         */
    public R visit(TryStatement n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ( "&gt;" "&gt;" "&gt;" )
         * 
         * </PRE>
         */
    public R visit(RUNSIGNEDSHIFT n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> ( "&gt;" "&gt;" )
         * 
         * </PRE>
         */
    public R visit(RSIGNEDSHIFT n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> NormalAnnotation() | SingleMemberAnnotation() |
         * MarkerAnnotation()
         * 
         * </PRE>
         */
    public R visit(Annotation n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "@" f1 -> Name() f2 -> "(" f3 -> [ MemberValuePairs() ] f4 ->
         * ")"
         * 
         * </PRE>
         */
    public R visit(NormalAnnotation n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "@" f1 -> Name()
         * 
         * </PRE>
         */
    public R visit(MarkerAnnotation n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "@" f1 -> Name() f2 -> "(" f3 -> MemberValue() f4 -> ")"
         * 
         * </PRE>
         */
    public R visit(SingleMemberAnnotation n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> MemberValuePair() f1 -> ( "," MemberValuePair() )*
         * 
         * </PRE>
         */
    public R visit(MemberValuePairs n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> &lt;IDENTIFIER&gt; f1 -> "=" f2 -> MemberValue()
         * 
         * </PRE>
         */
    public R visit(MemberValuePair n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> Annotation() | MemberValueArrayInitializer() |
         * ConditionalExpression()
         * 
         * </PRE>
         */
    public R visit(MemberValue n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "{" f1 -> MemberValue() f2 -> ( "," MemberValue() )* f3 -> [
         * "," ] f4 -> "}"
         * 
         * </PRE>
         */
    public R visit(MemberValueArrayInitializer n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "@" f1 -> "interface" f2 -> &lt;IDENTIFIER&gt; f3 ->
         * AnnotationTypeBody()
         * 
         * </PRE>
         */
    public R visit(AnnotationTypeDeclaration n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "{" f1 -> ( AnnotationTypeMemberDeclaration() )* f2 -> "}"
         * 
         * </PRE>
         */
    public R visit(AnnotationTypeBody n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> Modifiers() ( Type() &lt;IDENTIFIER&gt; "(" ")" [
         * DefaultValue() ] ";" | ClassOrInterfaceDeclaration() |
         * EnumDeclaration() | AnnotationTypeDeclaration() | FieldDeclaration() ) | (
         * ";" )
         * 
         * </PRE>
         */
    public R visit(AnnotationTypeMemberDeclaration n, A argu);

    /**
         * <PRE>
         * 
         * f0 -> "default" f1 -> MemberValue()
         * 
         * </PRE>
         */
    public R visit(DefaultValue n, A argu);

}
