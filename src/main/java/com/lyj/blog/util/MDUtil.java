package com.lyj.blog.util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.*;
import org.commonmark.renderer.text.TextContentRenderer;

import java.util.Arrays;
import java.util.List;

/**
 * markdown解析工具类
 */
public class MDUtil {


    public static void main(String[] args) {
        test();
    }

    //helloworld
    public static void test0() {
        Parser parser = Parser.builder().build();
        Node document = parser.parse("helloworld");
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String render = renderer.render(document);
        System.out.println(render);
    }

    //计算字符个数
    public static void test1() {
        Parser parser = Parser.builder().build();
        Node node = parser.parse("Example\n=======\n\nSome more text");
        WordCountVisitor visitor = new WordCountVisitor();
        node.accept(visitor);
        System.out.println(visitor.wordCount);
    }

    //对标签添加一些相对应的属性
    public static void test2() {
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder()
                .attributeProviderFactory(new AttributeProviderFactory() {
                    public AttributeProvider create(AttributeProviderContext context) {
                        return new ImageAttributeProvider();
                    }
                }).attributeProviderFactory(new a())
                .build();

        Node document = parser.parse("![text](/url.png)");
        String render = renderer.render(document);
        System.out.println(render);
    }

    //使用table插件
    public static void test3() {
        List<Extension> extensions = Arrays.asList(TablesExtension.create());//添加table解析插件
        Parser parser = Parser.builder()
                .extensions(extensions)
                .build();
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(extensions)
                .build();

        Node parse = parser.parse("tbale的markdown源代码");
        String render = renderer.render(parse);
        System.out.println(render);

    }

    //自定义元素的渲染结果
    public static void test() {
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder()
                .nodeRendererFactory(new HtmlNodeRendererFactory() {
                    public NodeRenderer create(HtmlNodeRendererContext context) {
                        return new IndentedCodeBlockNodeRenderer(context);
                    }
                }).nodeRendererFactory(new HtmlNodeRendererFactory() {
                    @Override
                    public NodeRenderer create(HtmlNodeRendererContext context) {
                        return new ;
                    }
                })
                .build();

        Node document = parser.parse("Example:\n\n    code");
        String render = renderer.render(document);
        System.out.println(render);

    }
}
