package com.lyj.blog.file;

import java.io.File;
import java.io.FileFilter;

/**
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
