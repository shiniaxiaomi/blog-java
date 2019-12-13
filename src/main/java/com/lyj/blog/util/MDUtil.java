package com.lyj.blog.util;

import com.lyj.blog.render.CodeBlockRender;
import com.lyj.blog.render.HeadingRender;
import com.lyj.blog.render.ImageRender;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * markdown解析渲染工具类
 */
public class MDUtil {
    private static List<Extension> extensions = Collections.singletonList(TablesExtension.create());//添加table解析插件
    private static Parser parser = Parser.builder().extensions(extensions).build();//构建解析类

    private static HtmlRenderer renderer = HtmlRenderer.builder().extensions(extensions)
            .nodeRendererFactory(new CodeBlockRender()) //添加代码块解析回调
            .nodeRendererFactory(new HeadingRender()) //添加header解析回调
            .nodeRendererFactory(new ImageRender()) //添加image解析回调
            .build();//构建html渲染类


    public static String render(String str) {
        Node parse = parser.parse(str);
        String render = renderer.render(parse);
        return render;
    }
}
