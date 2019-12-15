package com.lyj.blog.test;


import com.lyj.blog.file.FileUtil;
import com.lyj.blog.util.VarUtil;

import java.io.File;

public class Test {

    /**
     * 替换笔记的toc目录，替换笔记的图片路径
     */
    public static void main(String[] args) {
        //遍历目录，渲染笔记
        FileUtil.mapDir(new File(VarUtil.notePath),
                new DirFilter(), new BlogFilter(),
                new DirCallBack(), new BlogCallBack(), null);
    }

}
