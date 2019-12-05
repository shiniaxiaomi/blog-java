package com.lyj.blog.model;

import java.util.List;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/5 9:36 上午
 */
public class Header {
    int level;
    String text;
    List<Header> children;

    public Header() {
    }

    public Header(int level, String text) {
        this.level = level;
        this.text = text;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Header> getChildren() {
        return children;
    }

    public void setChildren(List<Header> children) {
        this.children = children;
    }
}
