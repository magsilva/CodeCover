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

// Generated by JTB 1.3.2
package org.codecover.instrumentation.java15.visitor;

import org.codecover.instrumentation.java15.syntaxtree.Node;
import org.codecover.instrumentation.java15.syntaxtree.NodeToken;

/**
 * Dumps the syntax tree to a {@link StringBuilder} using the
 * {@link NodeToken#getParsedImage()}.
 * 
 * @see #convertToString(Node)
 * 
 * @author Christoph Müller
 * @version 1.0 ($Id: TreeSourceFileImageDumper.java 2030 2007-10-04 09:16:22Z
 *          muellecr $)
 */
public class TreeParsedImageDumper extends DepthFirstVisitor {
    private StringBuilder writer = new StringBuilder();

    /**
     * Constructor.
     */
    public TreeParsedImageDumper() {
        reset();
    }

    /**
     * The internal {@link StringBuilder} is replaced by an empty instance.
     */
    public void reset() {
        this.writer = new StringBuilder();
    }

    /**
     * @return The String, that has been captured through all the visiting since
     *         the last {@link #reset()} and {@link String#trim()}.
     */
    public String getContent() {
        return this.writer.toString().trim();
    }

    /**
     * @return The String, that has been captured through all the visiting since
     *         the last {@link #reset()} without {@link String#trim()};
     */
    public String getContentUntrimmed() {
        return this.writer.toString();
    }

    /**
     * Dumps the current NodeToken to the {@link #writer}.
     */
    @Override
    public void visit(NodeToken n) {
        if (n.numSpecials() > 0) {
            for (NodeToken nt : n.specialTokens) {
                this.writer.append(nt.getParsedImage());
            }
        }

        this.writer.append(n.getParsedImage());
    }

    /**
     * This method converts a {@link Node} to a String by capturing all
     * {@link NodeToken#getParsedImage()}.<br>
     * <br>
     * This method calls:
     * 
     * <pre>
     * TreeParsedImageDumper treeStringDumper = new TreeParsedImageDumper();
     * n.accept(treeStringDumper);
     * return treeStringDumper.getContent();
     * </pre>
     * 
     * @param n
     *            The Node to convert.
     * 
     * @return The captured String.
     */
    public static String convertToString(Node n) {
        TreeParsedImageDumper treeStringDumper = new TreeParsedImageDumper();
        n.accept(treeStringDumper);
        return treeStringDumper.getContent();
    }
}
