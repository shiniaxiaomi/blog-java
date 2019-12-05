package com.lyj.blog.filter;

import com.lyj.blog.model.BlogFile;
import com.lyj.blog.util.VarUtil;
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

        BlogFile blogFile = new BlogFile(name, dir.getPath());//创建文件夹
        VarUtil.blogFile.add(blogFile);//添加当前文件夹
        VarUtil.blogFile=blogFile;//将当前文件夹指向到刚创建的文件夹


//        System.out.println(name);
        return true;
    }
}
