/******************************************************************************
 * Copyright (c) 2007 Stefan Franke, Robert Hanussek, Benjamin Keil,          *
 *                    Steffen Kieß, Johannes Langauf,                         *
 *                    Christoph Marian Müller, Igor Podolskiy,                *
 *                    Tilmann Scheller, Michael Starzmann, Markus Wittlinger  *
 * All rights reserved. This program and the accompanying materials           *
 * are made available under the terms of the Eclipse Public License v1.0      *
 * which accompanies this distribution, and is available at                   *
 * http://www.eclipse.org/legal/epl-v10.html                                  *
 ******************************************************************************/

package org.codecover.instrumentation.java15;

import static org.codecover.instrumentation.java15.JavaAllInstrumentationTest.instrumentationTests;
import junit.framework.TestCase;

import org.codecover.UtilsForTestingJava;
import org.codecover.model.utils.criteria.BranchCoverage;
import org.codecover.model.utils.criteria.Criterion;

/**
 * @author Christoph Müller
 * @version 1.0 ($Id$)
 * 
 */
public class JavaBranchTest extends TestCase {

    public void test() throws Exception {
        instrumentationTests(UtilsForTestingJava.getDefaultJavaInstrumenter(
                new Criterion[] { BranchCoverage.getInstance() }
            ));
    }
}
