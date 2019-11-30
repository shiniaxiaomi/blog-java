package com.lyj.blog.filter;

import org.apache.commons.io.filefilter.AbstractFileFilter;

import java.io.File;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/11/29 10:21 上午
 */
public class DirFilter extends AbstractFileFilter {

    @Override
    public boolean accept(File dir, String name) {
        //去除隐藏文件
        if(name.startsWith(".")){
            return false;
        }
//        System.out.println(name);
        return true;
    }
}
