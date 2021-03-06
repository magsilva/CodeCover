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

package org.codecover.instrumentation.xampil.parser;

/**
 * An implementation of interface CharStream, where the stream is assumed to
 * contain only ASCII characters (without Unicode processing).
 * 
 * @author Generated By: JavaCC: Token.java, Version 4.0
 * @author Christoph Müller: added offset handling
 */
@SuppressWarnings("all")
public class SimpleCharStream implements CharStream {
    int bufsize;

    int available;

    int tokenBegin;

    protected int bufpos = -1;

    protected int bufLine[];

    protected int bufColumn[];

    protected int bufOffset[];

    protected int column = 0;

    protected int line = 1;

    protected int offset = 0;

    protected boolean prevCharIsCR = false;

    protected boolean prevCharIsLF = false;

    protected java.io.Reader inputStream;

    protected char[] buffer;

    protected int maxNextCharInd = 0;

    protected int inBuf = 0;

    protected int tabSize = 8;

    protected void setTabSize(int i) {
        this.tabSize = i;
    }

    protected int getTabSize() {
        return this.tabSize;
    }

    protected void ExpandBuff(boolean wrapAround) {
        char[] newbuffer = new char[this.bufsize + 2048];
        int newbufline[] = new int[this.bufsize + 2048];
        int newbufcolumn[] = new int[this.bufsize + 2048];
        int newbufposition[] = new int[this.bufsize + 2048];

        try {
            if (wrapAround) {
                System.arraycopy(this.buffer, this.tokenBegin, newbuffer, 0, this.bufsize
                        - this.tokenBegin);
                System.arraycopy(this.buffer, 0, newbuffer, this.bufsize - this.tokenBegin,
                        this.bufpos);
                this.buffer = newbuffer;

                System.arraycopy(this.bufLine, this.tokenBegin, newbufline, 0, this.bufsize
                        - this.tokenBegin);
                System.arraycopy(this.bufLine, 0, newbufline, this.bufsize - this.tokenBegin,
                        this.bufpos);
                this.bufLine = newbufline;

                System.arraycopy(this.bufColumn, this.tokenBegin, newbufcolumn, 0,
                        this.bufsize - this.tokenBegin);
                System.arraycopy(this.bufColumn, 0, newbufcolumn, this.bufsize
                        - this.tokenBegin, this.bufpos);
                this.bufColumn = newbufcolumn;

                System.arraycopy(this.bufOffset, this.tokenBegin, newbufposition, 0,
                        this.bufsize - this.tokenBegin);
                System.arraycopy(this.bufOffset, 0, newbufposition, this.bufsize
                        - this.tokenBegin, this.bufpos);
                this.bufOffset = newbufposition;

                this.maxNextCharInd = (this.bufpos += (this.bufsize - this.tokenBegin));
            } else {
                System.arraycopy(this.buffer, this.tokenBegin, newbuffer, 0, this.bufsize
                        - this.tokenBegin);
                this.buffer = newbuffer;

                System.arraycopy(this.bufLine, this.tokenBegin, newbufline, 0, this.bufsize
                        - this.tokenBegin);
                this.bufLine = newbufline;

                System.arraycopy(this.bufColumn, this.tokenBegin, newbufcolumn, 0,
                        this.bufsize - this.tokenBegin);
                this.bufColumn = newbufcolumn;

                System.arraycopy(this.bufOffset, this.tokenBegin, newbufposition, 0,
                        this.bufsize - this.tokenBegin);
                this.bufOffset = newbufposition;

                this.maxNextCharInd = (this.bufpos -= this.tokenBegin);
            }
        } catch (Throwable t) {
            throw new Error(t.getMessage());
        }

        this.bufsize += 2048;
        this.available = this.bufsize;
        this.tokenBegin = 0;
    }

    protected void FillBuff() throws java.io.IOException {
        if (this.maxNextCharInd == this.available) {
            if (this.available == this.bufsize) {
                if (this.tokenBegin > 2048) {
                    this.bufpos = this.maxNextCharInd = 0;
                    this.available = this.tokenBegin;
                } else if (this.tokenBegin < 0) {
                    this.bufpos = this.maxNextCharInd = 0;
                } else {
                    ExpandBuff(false);
                }
            } else if (this.available > this.tokenBegin) {
                this.available = this.bufsize;
            } else if ((this.tokenBegin - this.available) < 2048) {
                ExpandBuff(true);
            } else {
                this.available = this.tokenBegin;
            }
        }

        int i;
        try {
            if ((i = this.inputStream.read(this.buffer, this.maxNextCharInd, this.available
                    - this.maxNextCharInd)) == -1) {
                this.inputStream.close();
                throw new java.io.IOException();
            } else {
                this.maxNextCharInd += i;
            }
            return;
        } catch (java.io.IOException e) {
            --this.bufpos;
            backup(0);
            if (this.tokenBegin == -1) {
                this.tokenBegin = this.bufpos;
            }
            throw e;
        }
    }

    public char BeginToken() throws java.io.IOException {
        this.tokenBegin = -1;
        char c = readChar();
        this.tokenBegin = this.bufpos;

        return c;
    }

    protected void updateLineColumn(char c) {
        this.column++;
        this.offset++;

        if (this.prevCharIsLF) {
            this.prevCharIsLF = false;
            this.line += (this.column = 1);
        } else if (this.prevCharIsCR) {
            this.prevCharIsCR = false;
            if (c == '\n') {
                this.prevCharIsLF = true;
            } else {
                this.line += (this.column = 1);
            }
        }

        switch (c) {
        case '\r':
            this.prevCharIsCR = true;
            break;
        case '\n':
            this.prevCharIsLF = true;
            break;
        case '\t':
            this.column--;
            this.column += (this.tabSize - (this.column % this.tabSize));
            break;
        default:
            break;
        }

        this.bufLine[this.bufpos] = this.line;
        this.bufColumn[this.bufpos] = this.column;
        // we set the start offset of this char -> (bufpos)
        this.bufOffset[this.bufpos] = this.offset;
    }

    public char readChar() throws java.io.IOException {
        if (this.inBuf > 0) {
            --this.inBuf;

            if (++this.bufpos == this.bufsize) {
                this.bufpos = 0;
            }

            return this.buffer[this.bufpos];
        }

        if (++this.bufpos >= this.maxNextCharInd) {
            FillBuff();
        }

        char c = this.buffer[this.bufpos];

        updateLineColumn(c);
        return (c);
    }

    public int getEndColumn() {
        return this.bufColumn[this.bufpos];
    }

    public int getEndLine() {
        return this.bufLine[this.bufpos];
    }

    public int getEndOffset() {
        return this.bufOffset[this.bufpos];
    }

    public int getBeginColumn() {
        return this.bufColumn[this.tokenBegin];
    }

    public int getBeginLine() {
        return this.bufLine[this.tokenBegin];
    }

    public int getBeginOffset() {
        return this.bufOffset[this.tokenBegin] - 1;
    }

    public void backup(int amount) {

        this.inBuf += amount;
        if ((this.bufpos -= amount) < 0) {
            this.bufpos += this.bufsize;
        }
    }

    private SimpleCharStream(java.io.Reader dstream, int startline,
            int startcolumn, int buffersize) {
        this.inputStream = dstream;
        this.line = startline;
        this.column = startcolumn - 1;
        this.offset = 0;

        this.available = this.bufsize = buffersize;
        this.buffer = new char[buffersize];
        this.bufLine = new int[buffersize];
        this.bufColumn = new int[buffersize];
        this.bufOffset = new int[buffersize];
    }

    public SimpleCharStream(java.io.Reader dstream) {
        this(dstream, 1, 1, 4096);
    }

    private void ReInit(java.io.Reader dstream, int startline, int startcolumn,
            int buffersize) {
        this.inputStream = dstream;
        this.line = startline;
        this.column = startcolumn - 1;
        this.offset = 0;

        if (this.buffer == null || buffersize != this.buffer.length) {
            this.available = this.bufsize = buffersize;
            this.buffer = new char[buffersize];
            this.bufLine = new int[buffersize];
            this.bufColumn = new int[buffersize];
            this.bufOffset = new int[buffersize];
        }
        this.prevCharIsLF = this.prevCharIsCR = false;
        this.tokenBegin = this.inBuf = this.maxNextCharInd = 0;
        this.bufpos = -1;
    }

    public void ReInit(java.io.Reader dstream) {
        ReInit(dstream, 1, 1, 4096);
    }

    public String GetImage() {
        if (this.bufpos >= this.tokenBegin) {
            return new String(this.buffer, this.tokenBegin, this.bufpos - this.tokenBegin + 1);
        } else {
            return new String(this.buffer, this.tokenBegin, this.bufsize - this.tokenBegin)
                    + new String(this.buffer, 0, this.bufpos + 1);
        }
    }

    public char[] GetSuffix(int len) {
        char[] ret = new char[len];

        if ((this.bufpos + 1) >= len) {
            System.arraycopy(this.buffer, this.bufpos - len + 1, ret, 0, len);
        } else {
            System.arraycopy(this.buffer, this.bufsize - (len - this.bufpos - 1), ret, 0, len
                    - this.bufpos - 1);
            System.arraycopy(this.buffer, 0, ret, len - this.bufpos - 1, this.bufpos + 1);
        }

        return ret;
    }

    public void Done() {
        this.buffer = null;
        this.bufLine = null;
        this.bufColumn = null;
    }
}
