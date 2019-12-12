package com.lyj.blog.file;

import com.lyj.blog.ESmodel.ESBlog;
import com.lyj.blog.util.MDUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/5 3:35 下午
 */
public class BlogCallBack implements CallBack {

//    public static StringBuilder sb;//累计每个文件的header

    @Override
    public void callback(File file) {
        System.out.println(file.getName());

        try {
            String note = FileUtils.readFileToString(file);//笔记源码

            //标记文件开始
            //将blog添加到list中，并设置好id
            String blogId=file.getPath().substring(32,file.getPath().length()-3);
            ESBlog esBlog = new ESBlog(file.getName(), blogId,note, file.getPath());
            ESBlog.list.add(esBlog);//将blog保存到最后一个元素
//            sb=new StringBuilder();//每次渲染新文件时就重新创建一个StringBuilder

            //将笔记进行渲染
            String html = MDUtil.render(note);//笔记html

            //保存每个blog对应渲染后的html
            ESBlog.htmlMap.put(blogId,html);

            //标记文件结束
            //在一个文件渲染完成后，将本文件的headers添加到对应的blog对象的headers属性中
//            esBlog.setHeaders(sb.toString());//将所有的headers设置进去

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

/**
 * header的结构：
 * {
 *     headerName：xxx，
 *     blogId:xxx,
 *     level：xxx
 * }
 */

/**
 * blog的结构
 * {
 *     blogName：xxx，
 *     blogText：xxx，
 *     blogUrl：xxx，
 *     headerName：[xxx,xxx],
 * }
 */
