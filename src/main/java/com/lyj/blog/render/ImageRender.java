package com.lyj.blog.render;

import com.lyj.blog.util.VarUtil;
import org.commonmark.node.Image;
import org.commonmark.node.Node;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlNodeRendererFactory;
import org.commonmark.renderer.html.HtmlWriter;

import java.util.Collections;
import java.util.Set;

/**
 * 当解析到Image时被回调
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/4 5:14 下午
 */
public class ImageRender implements NodeRenderer, HtmlNodeRendererFactory {

    private HtmlWriter html;

    //自定义渲染类的创建回调:当类被创建时，会注入context对象，拿到writer后，可以在render函数中对内容进行自定义的更改
    @Override
    public NodeRenderer create(HtmlNodeRendererContext context) {
        this.html= context.getWriter();
        return this;
    }

    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        return Collections.singleton(Image.class);//返回代码块的类型，当解析到代码块时，就会回调render函数
    }

    @Override
    public void render(Node node) {
        Image image = (Image) node;
        html.tag("img src='/loading.gif' buff='" + image.getDestination().substring(VarUtil.getImagePathLength()) + "'");//将src先设置成loading图片，之后在js中加载真正的图片
    }

}