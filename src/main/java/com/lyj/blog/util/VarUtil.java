package com.lyj.blog.util;

import org.apache.commons.io.FileUtils;

/**
 * 保存全局变量
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/5 9:59 上午
 */
public class VarUtil {

    //笔记存放的路径
    public static final String notePath= FileUtils.getUserDirectory() + "/Documents/note";

    //笔记中的本地的图片路径
    public static final String localImagePath="/Users/yingjie.lu/Documents/note/.img";


    //获取笔记存放路径的长度
    public static int getNotePathLength(){
        return notePath.length();
    }

    //获取本地图片的路径长度
    public static int getImagePathLength(){
        return localImagePath.length();
    }

    //目录数据
    public static String dirData;

    //所有博客的链接数据
    public static String SEOData;

    //elasticsearch查询每页的个数
    public static final int pageSize=20;
}
