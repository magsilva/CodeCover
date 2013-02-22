package org.codecover.instrumentation.c;

import org.codecover.instrumentation.HierarchyLevelContainer;
import org.codecover.instrumentation.c.counter.CounterManager;
import org.codecover.instrumentation.c.syntaxtree.*;
import org.codecover.instrumentation.c.syntaxtree.Statement;
import org.codecover.instrumentation.c.visitor.DepthFirstVisitor;
import org.codecover.model.MASTBuilder;
import org.codecover.model.mast.*;

import java.util.*;

public class MastVisitor extends DepthFirstVisitor {
    private Stack<List<org.codecover.model.mast.Statement>> statementStack = new Stack<List<org.codecover.model.mast.Statement>>();
    private Stack<List<HierarchyLevel>> hierarchyStack = new Stack<List<HierarchyLevel>>();

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
        statementStack.push(new ArrayList<org.codecover.model.mast.Statement>());
    }

    private StatementSequence popStatementLevel() {
        List<org.codecover.model.mast.Statement> statementList = statementStack.pop();
        List<Location> locationsOfSequence = new Vector<Location>(statementList.size());
        for (org.codecover.model.mast.Statement thisStatement : statementList) {
            locationsOfSequence.addAll(thisStatement.getLocation().getLocations());
        }

        return builder.createStatementSequence(
                builder.createLocationList(locationsOfSequence),
                statementList);
    }

    private List<StatementSequence> createStatementSequenceList() {
        StatementSequence statementSequence = popStatementLevel();

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

    private void pushHierarchy() {
        hierarchyStack.push(new ArrayList<HierarchyLevel>());
        pushStatementLevel();
    }

    private void popHierachy(HierarchyLevelType type, String name,
                             int start, int end,
                             int headerStart, int headerEnd) {
        List<StatementSequence> programStatements = createStatementSequenceList();
        HierarchyLevel level = builder.createHierarchyLevel(
                createLocationList(start, end),
                name,
                createLocationList(headerStart, headerEnd),
                type,
                hierarchyStack.pop(),
                programStatements);
        if(hierarchyStack.empty())
            rootContainer.addHierarchyLevelToRoot(level);
        else
            hierarchyStack.peek().add(level);
    }

    private CoverableItem createCoverableItem(String id) {
        return builder.createCoverableItem(sourceFile.getFileName(), id);
    }


    private void createBasicStatement(Node statement, String id) {
        int startOffset = BeginOffset.getStartOffset(statement);

        LocationList locationList = createLocationList(startOffset, lastEndOffset);
        org.codecover.model.mast.Statement newStatement = builder
                .createBasicStatement(locationList,
                        createCoverableItem(id),
                        Collections.<RootTerm> emptySet(), null);
        this.statementStack.peek().add(newStatement);
    }

    private Branch createBranch(String branchID,
                                                 int startOffset,
                                                 int endOffset,
                                                 int decisionStartOffset,
                                                 int decisionEndOffset,
                                                 boolean implicit) {
        LocationList locationList = createLocationList(startOffset, endOffset);
        LocationList locationListDecision = createLocationList(decisionStartOffset,
                decisionEndOffset);
        StatementSequence statementSequence = popStatementLevel();

        return builder.createBranch(locationList,
                createCoverableItem(branchID),
                implicit,
                locationListDecision,
                statementSequence);
    }

    private void createConditionalStatement(String statementID,
                                            int startOffset,
                                            int endOffset,
                                            RootTerm rootTerm,
                                            List<Branch> branchList,
                                            int keywordStartOffset,
                                            int keywordEndOffset) {
        LocationList locationList = createLocationList(startOffset, endOffset);
        Location keywordLocation = createLocation(keywordStartOffset, keywordEndOffset);
        Set<RootTerm> setRootTerms;

        if (rootTerm == null) {
            setRootTerms = Collections.emptySet();
        } else {
            setRootTerms = new HashSet<RootTerm>();
            setRootTerms.add(rootTerm);
        }

        ConditionalStatement conditionalStatement = builder.createConditionalStatement(
                locationList,
                createCoverableItem(statementID),
                setRootTerms,
                branchList,
                keywordLocation, null);
        statementStack.peek().add(conditionalStatement);
    }

    public void createLoop(int start, int end, int keywordStart, int keywordEnd, int id, boolean optionalBodyExecution) {
        LoopingStatement stmt = builder.createLoopingStatement(
                createLocationList(start, end),
                createCoverableItem(cm.stmtID(id)),
                null,
                popStatementLevel(),
                createLocation(keywordStart, keywordEnd),
                createCoverableItem(cm.loopID(id++)),
                createCoverableItem(cm.loopID(id++)),
                createCoverableItem(cm.loopID(id++)),
                optionalBodyExecution,
                null
        );
        statementStack.peek().add(stmt);
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////


    @Override
    public void visit(org.codecover.instrumentation.c.syntaxtree.Statement n) {
        // We don't need ids for all kinds of statements
        // We want nice inscreasing IDs so we assign the ID before we visit the other nodes
        if(n.nodeChoice.which > 1)
            n.stmtNum = cm.newStmtID();
        super.visit(n);
        if(n.nodeChoice.choice instanceof ExpressionStatement ||
                n.nodeChoice.choice instanceof JumpStatement) {
            createBasicStatement(n, cm.stmtID(n.stmtNum));
        }
    }

    public void visit(FunctionDefinition n) {
        int headerStart = BeginOffset.getStartOffset(n);
        n.nodeOptional.accept(this);
        n.declarator.accept(this);
        n.nodeOptional1.accept(this);
        int headerEnd = lastEndOffset;
        int start = BeginOffset.getStartOffset(n.compoundStatement);
        pushHierarchy();
        n.compoundStatement.accept(this);
        int end = lastEndOffset;
        popHierachy(HierachyLevelTypes.getFunctionType(builder), Helper.findFunctionName(n),
                start, end, headerStart, headerEnd);
    }

    @Override
    public void visit(TranslationUnit n) {
        pushHierarchy();
        super.visit(n);
        popHierachy(HierachyLevelTypes.getSourceFileType(builder), sourceFile.getFileName(),
                BeginOffset.getStartOffset(n), lastEndOffset, -1, -1);
    }

    @Override
    public void visit(IfStatement n) {
        List<Branch> branchList = new ArrayList<Branch>(2);
        n.branchNum = cm.newBranchID();
        // another ID for the else part
        cm.newBranchID();

        n.nodeToken.accept(this);
        n.nodeToken1.accept(this);
        n.expression.accept(this);
        n.nodeToken2.accept(this);

        pushStatementLevel();
        n.statement.accept(this);
        branchList.add(createBranch(cm.branchID(n.branchNum),
                BeginOffset.getStartOffset(n.statement), lastEndOffset, -1, -1, false));

        pushStatementLevel();

        if(n.nodeOptional.present()) {
            n.nodeOptional.accept(this);
            NodeToken elseNode = (NodeToken) ((NodeSequence)n.nodeOptional.node).elementAt(0);
            branchList.add(createBranch(cm.branchID(n.branchNum+1),
                    BeginOffset.getStartOffset(n.nodeOptional), lastEndOffset,
                    elseNode.beginOffset, elseNode.endOffset,
                    false));
        } else {
            branchList.add(createBranch(cm.branchID(n.branchNum+1),
                    -1,-1, -1,-1, true));
        }

        createConditionalStatement(
                cm.stmtID(((Statement)n.getParent().getParent()).stmtNum),
                n.nodeToken.beginOffset, lastEndOffset,
                null, branchList,
                n.nodeToken.beginOffset, n.nodeToken.endOffset
                );
    }

    @Override
    public void visit(WhileStatement n) {
        n.nodeToken.accept(this);
        n.nodeToken1.accept(this);
        n.expression.accept(this);
        n.nodeToken2.accept(this);
        pushStatementLevel();
        n.statement.accept(this);

        n.loopNum = cm.newloopID();

        createLoop(
                n.nodeToken.beginOffset, lastEndOffset,
                n.nodeToken.beginOffset, n.nodeToken.endOffset,
                n.loopNum, true);
    }

    @Override
    public void visit(DoStatement n) {
        n.nodeToken.accept(this);
        pushStatementLevel();
        n.statement.accept(this);
        n.nodeToken1.accept(this);
        n.nodeToken2.accept(this);
        n.expression.accept(this);
        n.nodeToken3.accept(this);
        n.nodeToken4.accept(this);

        n.loopNum = cm.newloopID();

        createLoop(
                n.nodeToken.beginOffset, n.nodeToken4.endOffset,
                n.nodeToken.beginOffset, n.nodeToken.endOffset,
                n.loopNum, false);
    }

    @Override
    public void visit(ForStatement n) {
        n.nodeToken.accept(this);
        n.nodeToken1.accept(this);
        n.nodeOptional.accept(this);
        n.nodeToken2.accept(this);
        n.nodeOptional1.accept(this);
        n.nodeToken3.accept(this);
        n.nodeOptional2.accept(this);
        n.nodeToken4.accept(this);
        pushStatementLevel();
        n.statement.accept(this);

        n.loopNum = cm.newloopID();

        createLoop(
                n.nodeToken.beginOffset, lastEndOffset,
                n.nodeToken.beginOffset, n.nodeToken.endOffset,
                n.loopNum, true);
    }

    @Override
    public void visit(NodeToken n) {
        lastEndOffset = n.endOffset;
    }
}
