package org.codecover.instrumentation.c;

import org.codecover.instrumentation.HierarchyLevelContainer;
import org.codecover.instrumentation.c.adapter.TokenAdapter;
import org.codecover.instrumentation.c.counter.CounterManager;
import org.codecover.instrumentation.c.manipulators.DefaultBranchManipulator;
import org.codecover.instrumentation.c.manipulators.DefaultStatementManipulator;
import org.codecover.instrumentation.c.manipulators.DummyBranchManipulator;
import org.codecover.instrumentation.c.manipulators.DummyStatementManipulator;
import org.codecover.instrumentation.c.parser.CParser;
import org.codecover.instrumentation.c.syntaxtree.TranslationUnit;
import org.codecover.instrumentation.exceptions.InstrumentationException;
import org.codecover.instrumentation.exceptions.ParseException;
import org.codecover.model.MASTBuilder;
import org.codecover.model.mast.HierarchyLevelType;
import org.codecover.model.mast.SourceFile;
import org.codecover.model.utils.criteria.BranchCoverage;
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
                        isCriterionSet(StatementCoverage.getInstance()) ? new DefaultStatementManipulator(cm) : new DummyStatementManipulator(),
                        isCriterionSet(BranchCoverage.getInstance()) ? new DefaultBranchManipulator(cm) : new DummyBranchManipulator()
                );


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
            Helper.writeMeasurementFile(counterManagers, new File(targetFolder, "CodeCover.c"), testSessionContainerUID);
        } catch (IOException e) {
            throw new InstrumentationException(e);
        }
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
