package com.lyj.blog.filter;

import com.lyj.blog.model.BlogFile;
import com.lyj.blog.model.Header;
import com.lyj.blog.util.MDUtil;
import com.lyj.blog.util.VarUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AbstractFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

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

        VarUtil.blogFile.add(new BlogFile(file.getName(),file.getPath()));//将文件添加到文件夹中

        try {
//            String path = file.getPath();
            String note = FileUtils.readFileToString(file);//笔记源码
            // TODO: 2019/12/4 在render的时候，顺便提取header到内存中便于之后使用
            /**
             * 全部header存一份放到es中，index为header，id为随机，内容为header标题，header所属于的blog
             *
             * index为blog，id为随机，内容为
             */

            VarUtil.headerMap.put(file.getPath(),new Header());

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

        Iterator<File> fileIterator = FileUtils.iterateFilesAndDirs(new File("/Users/yingjie.lu/Documents/note"), new FileFilter(), new DirFilter());

        while(fileIterator.hasNext()){
            File file = fileIterator.next();
            if(file.isDirectory()){
                System.out.println(file.getName());
            }
        }

//        FileUtils.listFiles(new File("/Users/yingjie.lu/Documents/note"),new FileFilter(),new DirFilter());
//        System.out.println(VarUtil.blogRoot);
    }
}
