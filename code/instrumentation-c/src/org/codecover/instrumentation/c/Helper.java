package org.codecover.instrumentation.c;

import org.codecover.instrumentation.c.syntaxtree.*;

public class Helper {

    public static String findFunctionName(FunctionDefinition n) {
        DirectDeclarator d = n.declarator.directDeclarator;

        while (d.nodeChoice.which != 0) {
            d = ((Declarator)((NodeSequence)d.nodeChoice.choice).elementAt(1)).directDeclarator;
        }
        return ((NodeToken) d.nodeChoice.choice).tokenImage;
    }
}
