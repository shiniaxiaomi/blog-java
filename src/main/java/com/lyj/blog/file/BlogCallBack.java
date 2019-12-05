package com.lyj.blog.file;

import com.lyj.blog.util.MDUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/5 3:35 下午
 */
public class BlogCallBack implements CallBack {
    @Override
    public void callback(File file) {
        System.out.println(file.getName());

        try {
            String note = FileUtils.readFileToString(file);//笔记源码

            //标记文件开始
            


            String html = MDUtil.render(note);//笔记html

            //标记文件结束


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
