package com.lyj.blog.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 目录文件
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/5 2:44 下午
 */
public class BlogFile {

    String name;
    String url;
    List<BlogFile> children;


    public BlogFile() {
    }

    public BlogFile(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public void add(BlogFile blogFile){
        if(this.children==null){
            this.children=new ArrayList<>();
        }

        this.children.add(blogFile);
    }
}
