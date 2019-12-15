package com.lyj.blog.util;

import org.apache.commons.io.FileUtils;

/**
 * 保存全局变量
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/5 9:59 上午
 */
public class VarUtil {

    public static final String notePath= FileUtils.getUserDirectory() + "/Documents/note";

    public static int getNotePathLength(){
        return notePath.length();
    }

    public static String dirData;

    public static final int pageSize=20;
}
