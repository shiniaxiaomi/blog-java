package com.lyj.blog.render;

import org.commonmark.node.*;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlNodeRendererFactory;
import org.commonmark.renderer.html.HtmlWriter;

import java.util.Collections;
import java.util.Set;

/**
 * 当解析到header时被回调
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/4 5:12 下午
 */
public class HeadingRender implements NodeRenderer, HtmlNodeRendererFactory {

    private HtmlWriter html;

    //自定义渲染类的创建回调:当类被创建时，会注入context对象，拿到writer后，可以在render函数中对内容进行自定义的更改
    @Override
    public NodeRenderer create(HtmlNodeRendererContext context) {
        this.html= context.getWriter();
        return this;
    }

    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return Collections.singleton(Heading.class);//返回代码块的类型，当解析到代码块时，就会回调render函数
    }

    @Override
    public void render(Node node) {

        Heading heading = (Heading) node;
        System.out.println(heading.getLevel());

        try {
            if(heading.getFirstChild() instanceof Link){
                Link link = (Link) heading.getFirstChild();
                String url=link.getDestination();

                html.tag("a href='"+url+"'");
                html.text(((Text) link.getFirstChild()).getLiteral());//获取header中的内容
                html.tag("a");
            }else{
                String headerText = ((Text) heading.getFirstChild()).getLiteral();

                html.tag("h"+heading.getLevel());
                html.text(((Text) heading.getFirstChild()).getLiteral());//获取header中的内容
                html.tag("/h"+heading.getLevel());
            }
        }catch (Exception e){
            System.out.println("note parse error");
        }

    }


}