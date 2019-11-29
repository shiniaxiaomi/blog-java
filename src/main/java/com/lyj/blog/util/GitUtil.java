package com.lyj.blog.util;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class GitUtil {
    private static DefaultExecutor executor = new DefaultExecutor();
    static {
        executor.setWorkingDirectory(new File(FileUtils.getUserDirectory() + "/note"));//将用户目录设置为工作目录
    }

    //拉取笔记
    public static void gitPull() throws IOException {
        int execute = executor.execute(CommandLine.parse("git pull"));
        System.out.println("笔记拉取成功");
    }

}
