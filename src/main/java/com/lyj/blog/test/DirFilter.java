package com.lyj.blog.test;

import java.io.File;
import java.io.FileFilter;

/**
 * Dir的过滤器，过滤的目录不会在DirCallBack中回调
 * false为过滤
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/5 3:23 下午
 */
public class DirFilter implements FileFilter {
    @Override
    public boolean accept(File file) {
        //去除隐藏文件
        if(file.getName().startsWith(".")){
            return false;
        }

        return true;
    }
}
