package org.codecover.instrumentation.c;

import org.codecover.instrumentation.HierarchyLevelContainer;
import org.codecover.instrumentation.c.adapter.TokenAdapter;
import org.codecover.instrumentation.c.manipulators.DefaultStatementManipulator;
import org.codecover.instrumentation.c.manipulators.DummyStatementManipulator;
import org.codecover.instrumentation.c.parser.CParser;
import org.codecover.instrumentation.c.syntaxtree.TranslationUnit;
import org.codecover.instrumentation.exceptions.ParseException;
import org.codecover.model.MASTBuilder;
import org.codecover.model.mast.HierarchyLevelType;
import org.codecover.model.mast.SourceFile;
import org.codecover.model.utils.criteria.StatementCoverage;

import java.io.*;
import java.util.Map;

public class Instrumenter extends org.codecover.instrumentation.Instrumenter {
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
        InstrumentationVisitor instrumentationVisitor = new InstrumentationVisitor(
                target, builder, sourceFile, rootContainer,
                testSessionContainerUID);

        if (super.isCriterionSet(StatementCoverage.getInstance())) {
            instrumentationVisitor.setStatementManipulator(
                    new DefaultStatementManipulator());
        } else {
            instrumentationVisitor.setStatementManipulator(
                    new DummyStatementManipulator());
        }

        instrumentationVisitor.visit(translationUnit);
    }

    @Override
    protected HierarchyLevelType getPackageHierarchyLevelType(MASTBuilder builder) {
        return HierachyLevelTypes.getSourceFileType(builder);
    }

    @Override
    public boolean allowsFileListInstrumentation() {
        return true;
    }


}
