package com.lyj.blog.file;

import java.io.File;

/**
 * Blog过滤器，可以添加过滤条件，过滤的文件不会在BlogCallBack中回调
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/5 3:23 下午
 */
public class BlogFilter implements java.io.FileFilter {
    @Override
    public boolean accept(File file) {
        //去除隐藏文件
        if(file.getName().startsWith(".")){
            return false;
        }

        //去除不以.md结尾的文件
        if(!file.getName().endsWith(".md")){
            return false;
        }


        return true;
    }
}
