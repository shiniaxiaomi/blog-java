package com.lyj.blog.ESmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 存在ES中header相关的内容
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/3 3:54 下午
 */
public class ESHeader {

    public static Map<String,List<ESHeader>> map=new HashMap<>();

    String headerName;

    String blogId;

    String blogName;

    //返回json时不发送该字段内容
    @JsonIgnore
    String headerContent;//header下的笔记内容

    int level;


    public ESHeader(String headerName, String blogId, String blogName, String headerContent, int level) {
        this.headerName = headerName;
        this.blogId = blogId;
        this.blogName = blogName;
        this.headerContent = headerContent;
        this.level = level;
    }

    //添加对应的blogId的所有headers列表
    public static void addHeader(String blogId,ESHeader esHeader){
        List<ESHeader> headerList = map.get(blogId);
        if(headerList==null){
            headerList = new ArrayList();
            map.put(blogId,headerList);
        }
        headerList.add(esHeader);
    }

    //返回blogId对应的所有header列表
    public static List<ESHeader> getHeader(String blogId){
        List<ESHeader> headerList = map.get(blogId);
        return headerList;
    }

    public String getHeaderName() {
        return headerName;
    }

    public String getBlogId() {
        return blogId;
    }

    public int getLevel() {
        return level;
    }

    public String getHeaderContent() {
        return headerContent;
    }

    public String getBlogName() {
        return blogName;
    }
}
