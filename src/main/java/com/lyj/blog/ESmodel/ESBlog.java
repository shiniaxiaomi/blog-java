package com.lyj.blog.ESmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存放ES中Blog相关的内容
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/3 3:53 下午
 */
public class ESBlog {

    public static Map<String,ESBlog> blogMap=new HashMap<>();//根据博客名称保存对应的博客

    public static List<ESBlog> list=new ArrayList<>();

    public static Map<String,String> htmlMap=new HashMap<>();//保存对应的博客的渲染后的html代码

    String blogName;

    String blogId;

    String headers;//一篇笔记中的所有header集合

    String description;//博客描述

    public ESBlog(String blogName, String blogId) {
        this.blogName = blogName;
        this.blogId = blogId;
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

    public String getHeaders() {
        return headers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
