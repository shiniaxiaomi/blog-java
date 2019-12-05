package com.lyj.blog.ESmodel;

import java.util.List;
import java.util.UUID;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/3 3:53 下午
 */
public class ESBlog {

    public static List<ESBlog> list;

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
}
