package com.lyj.blog.file;

import com.lyj.blog.util.VarUtil;

import java.io.File;
import java.io.FileFilter;

/**
 * 文件遍历工具类
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/5 3:23 下午
 */
public class FileUtil {


    /**
     * 遍历文件夹
     * @param file 需要遍历的文件路径
     * @param dirFilter 文件夹过滤
     * @param fileFilter 文件过滤
     * @param dirCallBack 目录回调
     * @param fileCallBack 文件回调
     * @param dirOrFile 获取到文件夹目录结构，为null时，则不返回
     */
    public static void mapDir(File file, FileFilter dirFilter, FileFilter fileFilter, CallBack dirCallBack, CallBack fileCallBack, DirOrFile dirOrFile) {

        //如果是文件
        if(file.isFile()){
            boolean accept = fileFilter.accept(file);
            if(!accept) return;//如果不符合过滤条件，则返回

            if(dirOrFile!=null){
                if(file.isFile()){
                    dirOrFile.add(new DirOrFile(file.getName().substring(0,file.getName().length()-3),file.getPath().substring(VarUtil.getNotePathLength(),file.getPath().length()-3)));
                }else{
                    dirOrFile.add(new DirOrFile(file.getName(),file.getPath().substring(VarUtil.getNotePathLength())));
                }
            }

            fileCallBack.callback(file);//文件回调

            return;
        }

        //如果是目录
        boolean accept = dirFilter.accept(file);
        if(!accept) return;//如果不符合过滤条件，则返回

        DirOrFile buff =null;
        if(dirOrFile!=null){
            if(file.isFile()){
                buff = new DirOrFile(file.getName().substring(0,file.getName().length()-3),file.getPath().substring(VarUtil.getNotePathLength(),file.getPath().length()-3) );//创建当前目录
            }else{
                buff = new DirOrFile(file.getName(),file.getPath().substring(VarUtil.getNotePathLength()) );//创建当前目录
            }

            dirOrFile.add(buff);
        }

        dirCallBack.callback(file);//目录回调

        //继续遍历目录
        File[] files = file.listFiles();
        for (File childFile : files) {
            mapDir(childFile,dirFilter,fileFilter,dirCallBack,fileCallBack,buff);
        }

    }



}
