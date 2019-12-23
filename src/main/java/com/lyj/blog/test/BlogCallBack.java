package com.lyj.blog.test;

import com.lyj.blog.file.CallBack;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * 当扫描到一个blog的时候的回调，在回调中将笔记源码渲染成html，并提取出相关的信息，用于Elasticsearch的搜索
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/5 3:35 下午
 */
public class BlogCallBack implements CallBack {


    @Override
    public void callback(File file) {
        try {
            String note = FileUtils.readFileToString(file);//笔记源码
            note=note.replace("[TOC]\n\n","");//替换笔记的toc目录
            note=note.replace("D:\\note\\.img\\","/Users/yingjie.lu/Documents/note/.img/");//修改图片路径
//            System.out.println(note);
            FileUtils.writeStringToFile(file,note);


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
