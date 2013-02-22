package org.codecover.instrumentation.c;

import org.codecover.instrumentation.HierarchyLevelContainer;
import org.codecover.instrumentation.c.counter.CounterManager;
import org.codecover.instrumentation.c.syntaxtree.*;
import org.codecover.instrumentation.c.visitor.DepthFirstVisitor;
import org.codecover.model.MASTBuilder;
import org.codecover.model.mast.*;
import org.codecover.model.mast.Statement;

import java.util.*;

public class MastVisitor extends DepthFirstVisitor {
    private Stack<List<Statement>> statementStack = new Stack<List<org.codecover.model.mast.Statement>>();
    private MASTBuilder builder;
    private SourceFile sourceFile;
    private HierarchyLevelContainer rootContainer;
    private int lastEndOffset;

    private CounterManager cm;

    public MastVisitor(MASTBuilder builder, SourceFile sourceFile, HierarchyLevelContainer rootContainer, CounterManager cm) {
        this.builder = builder;
        this.sourceFile = sourceFile;
        this.rootContainer = rootContainer;
        this.cm = cm;
    }

    private void pushStatementLevel() {
        this.statementStack.push(new ArrayList<Statement>());
    }

    private StatementSequence createStatementSequence() {
        List<org.codecover.model.mast.Statement> statementList = this.statementStack.pop();
        List<Location> locationsOfSequence = new Vector<Location>(statementList.size());
        for (org.codecover.model.mast.Statement thisStatement : statementList) {
            locationsOfSequence.addAll(thisStatement.getLocation().getLocations());
        }

        return builder.createStatementSequence(
                builder.createLocationList(locationsOfSequence),
                statementList);
    }

    private List<StatementSequence> createStatementSequenceList() {
        StatementSequence statementSequence = createStatementSequence();

        if (statementSequence.getStatements().isEmpty()) {
            return Collections.emptyList();
        }

        return Collections.singletonList(statementSequence);
    }

    private Location createLocation(int startOffset, int endOffset) {
        return builder.createLocation(sourceFile, startOffset, endOffset);
    }

    private LocationList createLocationList(int startOffset, int endOffset) {
        if (startOffset == -1 && endOffset == -1) {
            return builder.createEmptyLocationList();
        }

        if (startOffset != -1 && endOffset != -1) {
            Location location = createLocation(startOffset, endOffset);
            List<Location> listOfLocations = Collections.singletonList(location);
            return builder.createLocationList(listOfLocations);
        }

        String message = "startOffset == -1 ^ endOffset == -1";
        Exception exception = new IllegalStateException(message);
        builder.getLogger().fatal(message, exception);

        // never reached cause fatal throws an Exception
        return null;
    }

    private void popTopLevelHieraryLevels(int start,
                                                   int end,
                                                   int headerStartOffset,
                                                   int headerEndOffset) {
        if (statementStack.size() != 1) {
            String message = "this.statementAttic.size() != 1";
            Exception exception = new IllegalStateException(message);
            builder.getLogger().fatal(message, exception);
        }

        List<StatementSequence> programStatements = createStatementSequenceList();
        HierarchyLevel programHL = builder.createHierarchyLevel(
                createLocationList(start, end),
                sourceFile.getFileName(),
                createLocationList(headerStartOffset, headerEndOffset),
                HierachyLevelTypes.getSourceFileType(builder),
                Collections.<HierarchyLevel>emptyList(),
                programStatements);
        rootContainer.addHierarchyLevelToRoot(programHL);
    }

    private CoverableItem createCoverableItem(String id) {
        return builder.createCoverableItem(sourceFile.getFileName(), id);
    }


    private void atticStatement(Node statement, String id) {
        int startOffset = BeginOffset.getStartOffset(statement);

        LocationList locationList = createLocationList(startOffset, lastEndOffset);
        org.codecover.model.mast.Statement newStatement = builder
                .createBasicStatement(locationList,
                        createCoverableItem(id),
                        Collections.<RootTerm> emptySet(), null);
        this.statementStack.peek().add(newStatement);
    }

    @Override
    public void visit(org.codecover.instrumentation.c.syntaxtree.Statement n) {
        super.visit(n);
        // We don't need ids for all kinds of statements
        if(n.nodeChoice.which == 1 || n.nodeChoice.which == 5) {
            n.stmtNum = cm.newStmtID();
            atticStatement(n, cm.stmtPrefix() + Integer.toString(n.stmtNum));
        }
    }

    @Override
    public void visit(TranslationUnit n) {
        int headerStartOffset = 0;
        int headerEndOffset = BeginOffset.getStartOffset(n);
        pushStatementLevel();
        super.visit(n);
        popTopLevelHieraryLevels(headerEndOffset, lastEndOffset, headerStartOffset, headerEndOffset);

    }

    @Override
    public void visit(NodeToken n) {
        this.lastEndOffset = n.endOffset;
    }
}
