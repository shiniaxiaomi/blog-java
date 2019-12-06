package com.lyj.blog.ESmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/3 3:53 下午
 */
public class ESBlog {

    public static List<ESBlog> list=new ArrayList<>();

    String blogName;

    String blogId;

    String url;

    String headers;

    public ESBlog(String blogName, String blogId, String url) {
        this.blogName = blogName;
        this.blogId = blogId;
        this.url = url;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }
}
