///////////////////////////////////////////////////////////////////////////////
//
// $Id$
// 
///////////////////////////////////////////////////////////////////////////////

package org.gbt2.instrumentation;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.swingui.TestRunner;

/**
 * @author Christoph Müller
 */
public class AllTests extends TestSuite {

    /**
     * Starts all the test cases hierachical using the JUnit swingui TestRunner.
     * 
     * @param args
     *            not needed
     */
    public static void main(String[] args) {
        TestRunner.run(AllTests.class);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getCanonicalName());

        // add testcases in this package
        suite.addTestSuite(JavaNonInstrumentationTest.class);
        suite.addTestSuite(JavaStatementTest.class);
        suite.addTestSuite(JavaBranchTest.class);
        suite.addTestSuite(JavaConditionTest.class);
        suite.addTestSuite(JavaLoopTest.class);
        suite.addTestSuite(JavaAllTest.class);
        suite.addTestSuite(CobolInstrumenterTest.class);

        // add testsuites in subpackages
        suite.addTest(org.gbt2.instrumentation.AllTests.suite());

        return suite;
    }
}
