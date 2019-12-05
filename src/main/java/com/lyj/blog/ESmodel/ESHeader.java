package com.lyj.blog.ESmodel;

import java.util.List;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/3 3:54 下午
 */
public class ESHeader {

    public static List<ESHeader> list;

    String headerName;

    String blogId;

    int level;


    public ESHeader(String headerName, String blogId, int level) {
        this.headerName = headerName;
        this.blogId = blogId;
        this.level = level;
    }
}
