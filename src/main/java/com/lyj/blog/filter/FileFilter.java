package com.lyj.blog.filter;

import com.lyj.blog.util.MDUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AbstractFileFilter;

import java.io.File;
import java.io.IOException;

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

        //去除不以.md结尾的文件
        if(!file.getName().endsWith(".md")){
            return false;
        }
        System.out.println(file.getName());

        try {
//            String path = file.getPath();
            String note = FileUtils.readFileToString(file);//笔记源码
            // TODO: 2019/12/4 在render的时候，顺便提取header到内存中便于之后使用
            String html = MDUtil.render(note);//笔记html
//            System.out.println(html);
//            FileUtils.writeStringToFile(new File("/Users/yingjie.lu/Code/blog/src/main/resources/static/html/"+path.substring(33,path.length()-3)+".html"),render );//将html保存在文件
            // TODO: 2019/12/4 不将html保存到文件，而是一次性存储到elasticsearch中
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static void main(String[] args) {
        FileUtils.listFiles(new File("/Users/yingjie.lu/Documents/note"),new FileFilter(),new DirFilter());
    }
}
