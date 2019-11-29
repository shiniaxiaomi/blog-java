package com.lyj.blog.util;

import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;

import java.util.Map;

public class a implements AttributeProviderFactory {
    @Override
    public AttributeProvider create(AttributeProviderContext context) {
        return new AttributeProvider() {
            @Override
            public void setAttributes(Node node, String tagName, Map<String, String> attributes) {

            }
        };
    }
}
