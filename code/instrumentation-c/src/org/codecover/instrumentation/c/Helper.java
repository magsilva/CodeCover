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

    // TODO close out on exception
    public static void writeMeasurementFile(ArrayList<CounterManager> counterManagers, File file, String testSessionContainerUID) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(file));

        out.println("#include <stdio.h>");
        out.println("#include <string.h>");
        out.println("#include <stdlib.h>");
        out.println("#include \"tree.h\"");

        out.println("struct CodeCover_ConditionCounter {\n" +
                "    SPLAY_ENTRY(CodeCover_ConditionCounter) linkage;\n" +
                "    int counter;\n" +
                "    unsigned char* values;\n" +
                "};");

        out.println("SPLAY_HEAD(CodeCover_ConditionCounterMap, CodeCover_ConditionCounter);");

        out.println("static int cmp_num;");
        out.println("static int CodeCover_ConditionCounter_cmp(struct CodeCover_ConditionCounter *left, struct CodeCover_ConditionCounter *right) {return memcmp(left, right, cmp_num);}");

        out.println("SPLAY_PROTOTYPE(CodeCover_ConditionCounterMap, CodeCover_ConditionCounter, linkage, CodeCover_ConditionCounter_cmp);");
        out.println("SPLAY_GENERATE(CodeCover_ConditionCounterMap, CodeCover_ConditionCounter, linkage, CodeCover_ConditionCounter_cmp)");

        out.println("struct CodeCover_Condition {int num; struct CodeCover_ConditionCounterMap head;};");

        for(CounterManager cm : counterManagers) {
            out.format("int %s[%d];\n", cm.stmtVarName(), cm.getStmtCnt());
            out.format("int %s[%d];\n", cm.branchVarName(), cm.getBranchCnt());
            out.format("int %s[%d];\n", cm.loopVarName(), cm.getloopCnt());
            out.format("int %s[%d];\n", cm.loopTmpName(), cm.getloopTmpCnt());
            out.format("%s %s[%d];\n", getCondTypeName(), cm.condVarName(), cm.getCondCnt());
            out.format("int %s[%d];\n", cm.qmoVarName(), cm.getQmoCnt()*2);
        }

        /*out.println("void CodeCover_reset() {");
        out.println("int i;");
        for(CounterManager cm : counterManagers) {
            out.format("for(i=0; i<%d; ++i) {\n", cm.getStmtCnt());
            out.format("%s[i] = 0;\n", cm.stmtVarName());
            out.println("}");
        }
        out.println("}");*/

        out.println("void CodeCover_ConditionAdd(struct CodeCover_Condition* cont, unsigned char* values, int num) {\n" +
                "    struct CodeCover_ConditionCounter find, *res;\n" +
                "    find.values = values;\n" +
                "    find.counter = 0;\n" +
                "    find.linkage.spe_left = 0;\n" +
                "    find.linkage.spe_right = 0;\n" +
                "\n" +
                "    cont->num = num;\n" +
                "\n" +
                "    cmp_num = num / 8 + 1;\n" +
                "\n" +
                "    res = SPLAY_FIND(CodeCover_ConditionCounterMap, &cont->head, &find);\n" +
                "    if(!res) {\n" +
                "        res = malloc(sizeof(struct CodeCover_ConditionCounter));\n" +
                "        memset(res, 0, sizeof(struct CodeCover_ConditionCounter));\n" +
                "        res->values = malloc(cmp_num);\n" +
                "        memcpy(res->values, values, cmp_num);\n" +
                "       SPLAY_INSERT(CodeCover_ConditionCounterMap, &cont->head, res);\n" +
                "    }\n" +
                "\n" +
                "    res->counter++;\n" +
                "}");

        out.println("void print_bits(FILE* f, unsigned char * values, int num) {\n" +
                "    int i;\n" +
                "    for (i=0;i<num;i++)\n" +
                "        fprintf(f, (values[i/8] & 1<<i%8) ? \"1\" : \"0\");\n" +
                "}");


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
            out.format("for(i=0; i<%d; ++i) {\n", cm.getCondCnt());
            out.println("struct CodeCover_ConditionCounter* counter;");
            out.format("SPLAY_FOREACH(counter, CodeCover_ConditionCounterMap, &(%s[i].head)) {\n", cm.condVarName());
            out.format("fprintf(f, \"%s%%i-\", i);\n", cm.condPrefix());
            out.format("print_bits(f, counter->values, %s[i].num);\n", cm.condVarName());
            out.println("fprintf(f, \" %i\\n\", counter->counter);}");
            out.println("}");
            out.format("for(i=0; i<%d; ++i) {\n", cm.getQmoCnt());
            out.format("fprintf(f, \"%s%%i-0 %%i\\n\", i, %s[i*2]);\n", cm.qmoPrefix(), cm.qmoVarName());
            out.format("fprintf(f, \"%s%%i-1 %%i\\n\", i, %s[i*2+1]);\n", cm.qmoPrefix(), cm.qmoVarName());
            out.println("}");
        }
        out.println("fprintf(f, \"END_TEST_CASE \\\"Single Test Case\\\"\\n\");");
        out.println("fclose(f);");
        out.println("}");

        out.close();
    }

    public static String getCondDefinitions() {
        return "struct CodeCover_Condition {int num;void* head;};\nvoid CodeCover_ConditionAdd(struct CodeCover_Condition*, unsigned char[], int);";
    }

    public static String getCondTypeName() {
        return "struct CodeCover_Condition";
    }
}
