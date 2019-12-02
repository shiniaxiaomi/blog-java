package com.lyj.blog.render;

import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlNodeRendererFactory;
import org.commonmark.renderer.html.HtmlWriter;

import java.util.Collections;
import java.util.Set;

/**
 * 自定义：代码块解析
 */
public class CodeBlockRender implements NodeRenderer, HtmlNodeRendererFactory {

    private HtmlWriter html;

    //自定义渲染类的创建回调:当类被创建时，会注入context对象，拿到writer后，可以在render函数中对内容进行自定义的更改
    @Override
    public NodeRenderer create(HtmlNodeRendererContext context) {
        this.html= context.getWriter();
        return this;
    }

    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return Collections.singleton(FencedCodeBlock.class);//返回代码块的类型，当解析到代码块时，就会回调render函数
    }

    @Override
    public void render(Node node) {

        FencedCodeBlock codeBlock = (FencedCodeBlock) node;

        html.tag("pre class=\"line-numbers\"");
        html.tag("code class=\"language-"+codeBlock.getInfo()+"\"");
        html.text(codeBlock.getLiteral());//可以在此将代码块中的代码进行高亮解析
        html.tag("/code");
        html.tag("/pre");


    }


}
