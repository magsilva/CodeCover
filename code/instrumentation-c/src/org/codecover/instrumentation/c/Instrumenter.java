package org.codecover.instrumentation.c;

import org.codecover.instrumentation.HierarchyLevelContainer;
import org.codecover.instrumentation.c.adapter.TokenAdapter;
import org.codecover.instrumentation.c.counter.CounterManager;
import org.codecover.instrumentation.c.manipulators.DefaultStatementManipulator;
import org.codecover.instrumentation.c.manipulators.DummyStatementManipulator;
import org.codecover.instrumentation.c.parser.CParser;
import org.codecover.instrumentation.c.syntaxtree.TranslationUnit;
import org.codecover.instrumentation.exceptions.InstrumentationException;
import org.codecover.instrumentation.exceptions.ParseException;
import org.codecover.model.MASTBuilder;
import org.codecover.model.mast.HierarchyLevelType;
import org.codecover.model.mast.SourceFile;
import org.codecover.model.utils.criteria.StatementCoverage;
import org.codecover.model.utils.file.SourceTargetContainer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Instrumenter extends org.codecover.instrumentation.Instrumenter {
    private ArrayList<CounterManager> counterManagers = new ArrayList<CounterManager>();

    private int maxId;

    @Override
    protected void instrumentThis(Reader source,
                                  Writer target,
                                  MASTBuilder builder,
                                  SourceFile sourceFile,
                                  HierarchyLevelContainer rootContainer,
                                  String testSessionContainerUID,
                                  Map<String, Object> instrumenterDirectives) throws ParseException, IOException {
        CParser cParser = new CParser(new TokenAdapter(source));
        TranslationUnit translationUnit = cParser.TranslationUnit();
        CounterManager cm = new CounterManager(Integer.toString(maxId++), sourceFile.getFileName());

        MastVisitor mastVisitor = new MastVisitor(builder, sourceFile, rootContainer, cm);

        mastVisitor.visit(translationUnit);

        InstrumentationVisitor instrumentationVisitor =
                new InstrumentationVisitor(target,
                        isCriterionSet(StatementCoverage.getInstance()) ? new DefaultStatementManipulator(cm) : new DummyStatementManipulator());


        instrumentationVisitor.visit(translationUnit);

        counterManagers.add(cm);
    }

    @Override
    protected void notifyEnd(File rootFolder,
                             File targetFolder,
                             Collection<SourceTargetContainer> jobs,
                             HierarchyLevelContainer rootHierarchyLevelContainer,
                             MASTBuilder builder, String testSessionContainerUID,
                             Map<String, Object> instrumenterDirectives) throws InstrumentationException {
        try {
            writeMeasurementFile(new File(targetFolder, "CodeCover.c"), testSessionContainerUID);
        } catch (IOException e) {
            throw new InstrumentationException(e);
        }
    }

    private void writeMeasurementFile(File file, String testSessionContainerUID) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(file));

        out.println("#include <stdio.h>");

        for(CounterManager cm : counterManagers) {
            out.format("int %s[%d];\n", cm.stmtVarName(), cm.getStmtCnt());
        }

        out.println("void CodeCover_reset() {");
        out.println("int i;");
        for(CounterManager cm : counterManagers) {
            out.format("for(i=0; i<%d; ++i) {\n", cm.getStmtCnt());
            out.format("%s[i] = 0;\n", cm.stmtVarName());
            out.println("}");
        }
        out.println("}");

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
        }
        out.println("fprintf(f, \"END_TEST_CASE \\\"Single Test Case\\\"\\n\");");
        out.println("fclose(f);");
        out.println("}");

        out.close();
    }

    @Override
    protected HierarchyLevelType getPackageHierarchyLevelType(MASTBuilder builder) {
        return HierachyLevelTypes.getProgramType(builder);
    }

    @Override
    public boolean allowsFileListInstrumentation() {
        return true;
    }


}
