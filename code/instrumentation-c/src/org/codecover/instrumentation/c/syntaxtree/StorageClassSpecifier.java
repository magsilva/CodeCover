//
// Generated by JTB 1.3.2
//

package org.codecover.instrumentation.c.syntaxtree;

/**
 * Grammar production:
 * <PRE>
 * nodeChoice -> ( &lt;AUTO&gt; | &lt;REGISTER&gt; | &lt;STATIC&gt; | &lt;EXTERN&gt; | &lt;TYPEDEF&gt; )
 * </PRE>
 */
public class StorageClassSpecifier extends org.codecover.instrumentation.c.adapter.CCNode implements Node {
   public NodeChoice nodeChoice;

   public StorageClassSpecifier(NodeChoice n0) {
      nodeChoice = n0;
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

