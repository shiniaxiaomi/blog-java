package com.lyj.blog.filter;

import org.apache.commons.io.filefilter.AbstractFileFilter;

import java.io.File;

public class FileFilter extends AbstractFileFilter {
    @Override
    public boolean accept(File file) {
        //排除目录
        if(file.isDirectory()){
            return false;
        }
        //去除隐藏文件
        if(file.getName().startsWith(".")){
            return false;
        }
        System.out.println(file.getName());
        return true;
    }
}
