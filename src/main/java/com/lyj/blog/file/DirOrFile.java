package com.lyj.blog.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/5 4:18 下午
 */
public class DirOrFile {

    String name;

    File file;

    List<DirOrFile> child;

    public DirOrFile() {
    }

    public DirOrFile(String name, File file) {
        this.name = name;
        this.file = file;
    }

    public void add(DirOrFile dirOrFile){

        if(child==null){
            child=new ArrayList<>();
        }

        child.add(dirOrFile);
    }
}
