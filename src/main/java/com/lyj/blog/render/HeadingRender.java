package com.lyj.blog.render;

import com.lyj.blog.ESmodel.ESBlog;
import com.lyj.blog.ESmodel.ESHeader;
import com.lyj.blog.file.BlogCallBack;
import com.lyj.blog.model.Header;
import org.commonmark.node.*;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.HtmlNodeRendererContext;
import org.commonmark.renderer.html.HtmlNodeRendererFactory;
import org.commonmark.renderer.html.HtmlWriter;

import java.util.Collections;
import java.util.List;
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
        int level=heading.getLevel();
        String headerName;

        //todo 可以在标题中添加标识，如果存在标识，则在大纲的标题中高亮显示（比如标题加粗的话就可以进行高亮，或者是（***））

        try {
            if(heading.getFirstChild() instanceof Link){
                Link link = (Link) heading.getFirstChild();
                Text text = (Text) link.getFirstChild();
                headerName=text.getLiteral();
                String url=link.getDestination();

                html.tag("h"+heading.getLevel()+" id='"+headerName+"'"+" class='header'");
                html.tag("a href='"+url+"'");
                html.text(headerName);//获取header中的内容
                html.tag("a");
                html.tag("/h"+heading.getLevel());
            }else{
                headerName= ((Text) heading.getFirstChild()).getLiteral();

                html.tag("h"+heading.getLevel()+" id='"+headerName+"'"+" class='header'");
                html.text(headerName);//获取header中的内容
                html.tag("/h"+heading.getLevel());
            }

            //获取到list最后一个元素，即当前文件的ESBlog对象
            List<ESBlog> list = ESBlog.list;
            ESBlog esBlog = list.get(list.size() - 1);
            String blogId = esBlog.getBlogId();
            String blogName= esBlog.getBlogName();

            //将所有的header对象添加到list列表中
            //将header和header中的内容取出来
            StringBuilder sb=new StringBuilder();
            toHtml(sb,heading.getNext());

            ESHeader esHeader = new ESHeader(headerName, blogId,blogName,sb.toString(), level);//构建header对象
            ESHeader.addHeader(blogId,esHeader);

            //还在保留单个文件中的所有header
            BlogCallBack.sb.append(headerName);
            BlogCallBack.sb.append("|");

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("note parse error");
        }

    }

    /**
     * 将header节点之间的内容读取出来
     * @param sb
     * @param node
     */
    public void toHtml(StringBuilder sb,Node node){

        //如果到了下一个header，则返回
        if(node instanceof Heading){
            return;
        }

        if(node==null){
            return;
        }



        if(node instanceof FencedCodeBlock){
            sb.append(((FencedCodeBlock)node).getLiteral());
            sb.append("\n");
        }else if(node instanceof Text){
            sb.append(((Text)node).getLiteral());
            sb.append("\n");
        }else{
            toHtml(sb,node.getFirstChild());//访问子节点
        }

        toHtml(sb,node.getNext());//访问下一个节点

    }


}