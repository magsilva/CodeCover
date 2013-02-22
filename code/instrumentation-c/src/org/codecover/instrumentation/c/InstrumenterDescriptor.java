package org.codecover.instrumentation.c;

import org.codecover.instrumentation.Instrumenter;
import org.codecover.model.utils.criteria.*;
import org.codecover.model.utils.file.FileTool;

import java.io.File;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

public class InstrumenterDescriptor extends org.codecover.instrumentation.InstrumenterDescriptor {

    private static final String INSTRUMENTER_UNIQUE_KEY = "CodeCover_C";

    private static final String PROGRAMMING_LANGUAGE = "C";

    private static final String AUTHOR = "Steffen Hanikel";

    private static final String INSTRUMENTER_VERSION = "1.0";

    private static final String DESCRIPTION = "An instrumenter for C.\n"
            + "Source files have to have the extension \".c\" or \".C\"\n"
            + "This instrumenter is part of the release of CodeCover. It supports "
            + "Statement, Branch, Condition and Loop Coverage. Int arrays are "
            + "used to keep the counters.\n"
            + "by: " + AUTHOR
            + "\ninstrumenter version: " + INSTRUMENTER_VERSION;

    private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();

    /**
     * protected constructor for initializing all member fields.
     */
    protected InstrumenterDescriptor() {
        super(INSTRUMENTER_UNIQUE_KEY);
        super.setLanguageName(PROGRAMMING_LANGUAGE);
        super.setDescription(DESCRIPTION);
        super.setAuthor(AUTHOR);
        super.setDefaultCharset(DEFAULT_CHARSET);

        super.addSupportedCriteria(StatementCoverage.getInstance());
        super.addSupportedCriteria(BranchCoverage.getInstance());
        super.addSupportedCriteria(LoopCoverage.getInstance());
        super.addSupportedCriteria(ConditionCoverage.getInstance());
    }

    @Override
    public boolean isLanguageSupported(String languageNameToCheck) {
        return "C".equalsIgnoreCase(languageNameToCheck);
    }

    @Override
    protected Instrumenter getInstrumenter() {
        return new org.codecover.instrumentation.c.Instrumenter();
    }

    @Override
    public boolean accept(File file) {
        return FileTool.getExtension(file).equalsIgnoreCase("c");
    }
}
