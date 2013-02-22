package org.codecover.instrumentation.c;

import org.anarres.cpp.*;
import org.codecover.instrumentation.c.parser.CParserConstants;
import org.codecover.instrumentation.c.parser.CParserTokenManager;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

public class TokenAdapter extends CParserTokenManager {
    Preprocessor pp;

    public TokenAdapter(File file) throws IOException {
        super(null);

        pp = new Preprocessor(file);
        //pp.addFeature(Feature.VERBOSE);
        //pp.addFeature(Feature.DEBUG);
    }

    public TokenAdapter(Reader reader) throws IOException {
        super(null);

        pp = new Preprocessor(new LexerSource(reader, true));
        //pp.addFeature(Feature.VERBOSE);
        //pp.addFeature(Feature.DEBUG);
    }

    @Override
    public org.codecover.instrumentation.c.parser.Token getNextToken() {
        try {
            return convertToken();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LexerException e) {
            e.printStackTrace();
        }
        return null;
    }

    private org.codecover.instrumentation.c.parser.Token convertToken() throws IOException, LexerException {
        // Skip Whitespace and comments?
        int kind;
        Token t;
        do {
            t = pp.token();
            //System.out.print(t.getText());
            kind = convertKind(t, t.getType());
        } while(kind == -1);
        if(kind == -2) {
            System.err.println("ERROR:");
            System.err.println(t.getText());
        }

        org.codecover.instrumentation.c.parser.Token tt =
                new org.codecover.instrumentation.c.parser.Token(kind,t.getText());
        // TODO: proper begin/end
        tt.beginColumn = t.getColumn() + 1;
        tt.endColumn = t.getText() != null ? t.getText().length() + tt.beginColumn - 1 : tt.beginColumn;
        tt.beginLine = tt.endLine = t.getLine();

        tt.beginOffset = t.offset + 1;
        tt.endOffset = t.getText() != null ? t.getText().length() + tt.beginOffset - 1 : tt.beginOffset;

        return tt;
    }

    private static int convertKind(Token t, int type) {
        switch (type)
        {
            case '(':
                return CParserConstants.RBL;
            case ')':
                return CParserConstants.RBR;
            case '{':
                return CParserConstants.CBL;
            case '}':
                return CParserConstants.CBR;
            case '[':
                return CParserConstants.SBL;
            case ']':
                return CParserConstants.SBR;
            case '*':
                return CParserConstants.STAR;
            case '/':
                return CParserConstants.SLASH;
            case '%':
                return CParserConstants.MOD;
            case '+':
                return CParserConstants.PLUS;
            case '-':
                return CParserConstants.MINUS;
            case '!':
                return CParserConstants.EXCL;
            case '&':
                return CParserConstants.AMP;
            case '?':
                return CParserConstants.QEM;
            case '~':
                return CParserConstants.TILDE;
            case '^':
                return CParserConstants.CARE;
            case '|':
                return CParserConstants.PIPE;
            case ',':
                return CParserConstants.COMMA;
            case ';':
                return CParserConstants.SEMICOLON;
            case ':':
                return CParserConstants.COLON;
            case '=':
                return CParserConstants.ASSIGNMENT;
            case '.':
                return CParserConstants.DOT;
            case '<':
                return CParserConstants.LESS;
            case '>':
                return CParserConstants.GREATER;
            case Token.AND_EQ:
                return CParserConstants.AND_EQ;
            case Token.ARROW:
                return CParserConstants.ARROW;
            case Token.CHARACTER:
                return CParserConstants.CHARACTER_LITERAL;
            case Token.CCOMMENT:
                return -1;
            case Token.CPPCOMMENT:
                return -1;
            case Token.DEC:
                return CParserConstants.DEC;
            case Token.DIV_EQ:
                return CParserConstants.DIV_EQ;
            case Token.ELLIPSIS:
                return CParserConstants.ELLIPSIS;
            case Token.EOF:
                return CParserConstants.EOF;
            case Token.EQ:
                return CParserConstants.EQ;
            case Token.GE:
                return CParserConstants.GE;
            case Token.HASH:
                // TODO
                System.out.println("TODO: convert id: " + type);
                return -2;
            case Token.HEADER:
                // TODO
                System.out.println("TODO: convert id: " + type);
                return -2;
            case Token.IDENTIFIER:
                return convertIdentifier(t.getText());
            case Token.INC:
                return CParserConstants.INC;
            case Token.LAND:
                return CParserConstants.LAND;
            case Token.LAND_EQ:
                // TODO invalid
                System.out.println("TODO: convert id: " + type);
                return -2;
            case Token.LE:
                return CParserConstants.LE;
            case Token.LITERAL:
                // TODO
                System.out.println("TODO: convert id: " + type);
                return -2;
            case Token.LOR:
                return CParserConstants.LOR;
            case Token.LOR_EQ:
                return -2;
            case Token.LSH:
                return CParserConstants.LSH;
            case Token.LSH_EQ:
                return CParserConstants.LSH_EQ;
            case Token.MOD_EQ:
                return CParserConstants.MOD_EQ;
            case Token.MULT_EQ:
                return CParserConstants.MULT_EQ;
            case Token.NE:
                return CParserConstants.NE;
            case Token.NL:
                return -1;
            case Token.NUMBER:
                return CParserConstants.NUMBER;
            case Token.OR_EQ:
                return CParserConstants.OR_EQ;
            case Token.PASTE:
                // TODO invalid;
                System.out.println("TODO: convert id: " + type);
                return -2;
            case Token.PLUS_EQ:
                return CParserConstants.PLUS_EQ;
            case Token.RANGE:
                // TODO invalid
                System.out.println("TODO: convert id: " + type);
                return -2;
            case Token.RSH:
                return CParserConstants.RSH;
            case Token.RSH_EQ:
                return CParserConstants.RSH_EQ;
            case Token.STRING:
                return CParserConstants.STRING_LITERAL;
            case Token.SUB_EQ:
                return CParserConstants.SUB_EQ;
            case Token.WHITESPACE:
                return -1;
            case Token.XOR_EQ:
                return CParserConstants.XOR_EQ;
            case Token.M_ARG:
            case Token.M_PASTE:
            case Token.M_STRING:
            case Token.P_LINE:
                // TODO invalid
                System.out.println("TODO: convert id: " + type);
                return -2;
            case Token.INVALID:
                // TODO
                System.out.println("TODO: convert id: " + type);
                return -2;
            default:
                return type;
        }
    }

    private static int convertIdentifier(String image) {
        if("int".equals(image)) {
            return CParserConstants.INT;
        } if("void".equals(image)) {
            return CParserConstants.VOID;
        } if("return".equals(image)) {
            return CParserConstants.RETURN;
        } if("typedef".equals(image)) {
            return CParserConstants.TYPEDEF;
        } if("double".equals(image)) {
            return CParserConstants.DOUBLE;
        } if("char".equals(image)) {
            return CParserConstants.CHAR;
        } if("for".equals(image)) {
            return CParserConstants.FOR;
        } if("while".equals(image)) {
            return CParserConstants.WHILE;
        } if("do".equals(image)) {
            return CParserConstants.DO;
        } if("goto".equals(image)) {
            return CParserConstants.GOTO;
        } if("if".equals(image)) {
            return CParserConstants.IF;
        } if("else".equals(image)) {
            return CParserConstants.ELSE;
        } if("break".equals(image)) {
            return CParserConstants.BREAK;
        } if("switch".equals(image)) {
            return CParserConstants.SWITCH;
        } if("case".equals(image)) {
            return CParserConstants.CASE;
        } if("default".equals(image)) {
            return CParserConstants.DEFAULT;
        } if("float".equals(image)) {
            return CParserConstants.FLOAT;
        } if("const".equals(image)) {
            return CParserConstants.CONST;
        } if("volatile".equals(image)) {
            return CParserConstants.VOLATILE;
        } if("continue".equals(image)) {
            return CParserConstants.CONTINUE;
        } if("register".equals(image)) {
            return CParserConstants.REGISTER;
        } if("signed".equals(image)) {
            return CParserConstants.SIGNED;
        } if("unsigned".equals(image)) {
            return CParserConstants.UNSIGNED;
        } if("sizeof".equals(image)) {
            return CParserConstants.SIZEOF;
        } if("extern".equals(image)) {
            return CParserConstants.EXTERN;
        } if("static".equals(image)) {
            return CParserConstants.STATIC;
        } if("struct".equals(image)) {
            return CParserConstants.STRUCT;
        } if("union".equals(image)) {
            return CParserConstants.UNION;
        } if("long".equals(image)) {
            return CParserConstants.LONG;
        } if("short".equals(image)) {
            return CParserConstants.SHORT;
        } if("enum".equals(image)) {
            return CParserConstants.ENUM;
        } if("auto".equals(image)) {
            return CParserConstants.AUTO;
        } else {
            return CParserConstants.IDENTIFIER;
        }
    }
}
