package com.lyj.blog.ESmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/3 3:53 下午
 */
public class ESBlog {

    public static List<ESBlog> list=new ArrayList<>();

    public static Map<String,String> htmlMap=new HashMap<>();//保存对应的博客的渲染后的html代码

    public static final String blogPath="";//blog的路径

    String blogName;

    String blogId;

    String blogText;//笔记源文件

    String url;

    String headers;//一篇笔记中的所有header集合

    public ESBlog(String blogName, String blogId,String blogText, String url) {
        this.blogName = blogName;
        this.blogId = blogId;
        this.blogText=blogText;
        this.url = url;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getBlogName() {
        return blogName;
    }

    public String getBlogText() {
        return blogText;
    }

    public String getUrl() {
        return url;
    }

    public String getHeaders() {
        return headers;
    }
}
