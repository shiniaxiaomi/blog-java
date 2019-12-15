package com.lyj.blog.test;

import com.lyj.blog.file.CallBack;

import java.io.File;

/**
 * Dir回调类，读取到目录时会回调callBack方法
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/5 3:35 下午
 */
public class DirCallBack implements CallBack {
    @Override
    public void callback(File file) {
//        System.out.println(file.getName());
    }
}
