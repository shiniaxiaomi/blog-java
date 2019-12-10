package com.lyj.blog.file;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/5 4:18 下午
 */
public class DirOrFile {

    public static DirOrFile Instance=new DirOrFile();

    String name;

    String url;

    List<DirOrFile> child;

    public DirOrFile() {
    }

    public DirOrFile(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public void add(DirOrFile dirOrFile){

        if(child==null){
            child=new ArrayList<>();
        }

        child.add(dirOrFile);
    }

    public List<DirOrFile> getChild() {
        return child;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("/blog");
//        sb.append(url);
//        return sb.toString();

        return url;
    }
}
