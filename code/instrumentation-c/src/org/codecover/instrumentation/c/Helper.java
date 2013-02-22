package org.codecover.instrumentation.c;

import org.codecover.instrumentation.c.counter.CounterManager;
import org.codecover.instrumentation.c.syntaxtree.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Helper {

    public static String findFunctionName(FunctionDefinition n) {
        DirectDeclarator d = n.declarator.directDeclarator;

        while (d.nodeChoice.which != 0) {
            d = ((Declarator)((NodeSequence)d.nodeChoice.choice).elementAt(1)).directDeclarator;
        }
        return ((NodeToken) d.nodeChoice.choice).tokenImage;
    }

    public static void writeMeasurementFile(ArrayList<CounterManager> counterManagers, File file, String testSessionContainerUID) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(file));

        out.println("#include <stdio.h>");
        out.println("#include \"tree.h\"");

        out.println(getCondDefinitions());

        for(CounterManager cm : counterManagers) {
            out.format("int %s[%d];\n", cm.stmtVarName(), cm.getStmtCnt());
            out.format("int %s[%d];\n", cm.branchVarName(), cm.getBranchCnt());
            out.format("int %s[%d];\n", cm.loopVarName(), cm.getloopCnt());
            out.format("int %s[%d];\n", cm.loopTmpName(), cm.getloopTmpCnt());
            out.format("%s %s[%d];\n", getCondTypeName(), cm.condVarName(), cm.getCondCnt());
        }

        /*out.println("void CodeCover_reset() {");
        out.println("int i;");
        for(CounterManager cm : counterManagers) {
            out.format("for(i=0; i<%d; ++i) {\n", cm.getStmtCnt());
            out.format("%s[i] = 0;\n", cm.stmtVarName());
            out.println("}");
        }
        out.println("}");*/

        out.println("void CodeCover_dump() {");
        out.println("int i; FILE* f;");
        out.println("f = fopen(\"coverage_log.clf\", \"w\");");
        out.format("fprintf(f, \"TEST_SESSION_CONTAINER \\\"%s\\\"\\n\");\n", testSessionContainerUID);
        out.println("fprintf(f, \"START_TEST_CASE \\\"Single Test Case\\\"\\n\");");
        for(CounterManager cm : counterManagers) {
            out.format("fprintf(f, \"START_SECTION \\\"%s\\\"\\n\");\n", cm.getFileName());
            out.format("for(i=0; i<%d; ++i) {\n", cm.getStmtCnt());
            out.format("fprintf(f, \"%s%%i %%i\\n\", i, %s[i]);\n", cm.stmtPrefix(), cm.stmtVarName());
            out.println("}");
            out.format("for(i=0; i<%d; ++i) {\n", cm.getBranchCnt());
            out.format("fprintf(f, \"%s%%i %%i\\n\", i, %s[i]);\n", cm.branchPrefix(), cm.branchVarName());
            out.println("}");
            out.format("for(i=0; i<%d; ++i) {\n", cm.getloopCnt());
            out.format("fprintf(f, \"%s%%i %%i\\n\", i, %s[i]);\n", cm.loopPrefix(), cm.loopVarName());
            out.println("}");
        }
        out.println("fprintf(f, \"END_TEST_CASE \\\"Single Test Case\\\"\\n\");");
        out.println("fclose(f);");
        out.println("}");

        out.close();
    }

    public static String getCondDefinitions() {
        return getCondTypeName() + " {};\nvoid CCCondAdd(struct CCCond*, unsigned char[], int);";
    }

    public static String getCondTypeName() {
        return "struct CCCond";
    }
}
