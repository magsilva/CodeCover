package org.codecover.instrumentation.c;

import org.codecover.instrumentation.c.parser.CParserConstants;
import org.codecover.instrumentation.c.syntaxtree.*;
import org.codecover.instrumentation.c.visitor.DepthFirstVisitor;

import java.io.*;

/**
 * Dumps the syntax tree to a Writer.
 */
public class SimpleTreeDumper extends DepthFirstVisitor {
   protected PrintWriter out;

   public SimpleTreeDumper(Writer o)        { out = new PrintWriter(o, true); }

   /**
    * Dumps the current NodeToken to the output stream being used.
    */
   public void visit(NodeToken n) {
       out.append(n.tokenImage);

       if(n.kind == CParserConstants.SEMICOLON
               || n.kind == CParserConstants.CBL
               || n.kind == CParserConstants.CBR) {
           out.append('\n');
       } else {
           out.append(' ');
       }
   }
}
