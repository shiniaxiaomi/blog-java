package com.lyj.blog.util;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * git工具类
 * @author yingjie.lu
 * @date 2019/12/13 9:26 下午
 * @version 1.0
 **/

public class GitUtil {
    private static DefaultExecutor executor = new DefaultExecutor();
    static {
        executor.setWorkingDirectory(new File(FileUtils.getUserDirectory() + "/Documents/note"));//将用户目录设置为工作目录
    }

    //拉取笔记
    public static void gitPull() {
        try {
            System.out.println("笔记拉取中。。。");
            executor.execute(CommandLine.parse("git pull"));
        } catch (IOException e) {
            System.out.println("笔记拉取失败:"+e);
        }
        System.out.println("笔记拉取成功");
    }

}
