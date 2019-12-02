package com.lyj.blog.util;

import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Text;

class WordCountVisitor extends AbstractVisitor {
    int wordCount = 0;

    @Override
    public void visit(Text text) {
        // This is called for all Text nodes. Override other visit methods for other node types.

        // Count words (this is just an example, don't actually do it this way for various reasons).

        // Descend into children (could be omitted in this case because Text nodes don't have children).
        visitChildren(text);
    }
}