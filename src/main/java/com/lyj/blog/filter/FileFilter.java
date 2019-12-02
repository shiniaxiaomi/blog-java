package com.lyj.blog.filter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AbstractFileFilter;

import java.io.File;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/11/29 10:17 上午
 */
public class FileFilter extends AbstractFileFilter {
    @Override
    public boolean accept(File file) {
        //去除目录
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

    //demo
    public static void main(String[] args) {
        FileUtils.listFilesAndDirs(new File("/Users/yingjie.lu/Desktop/test2"),new FileFilter(),new DirFilter());
    }
}
