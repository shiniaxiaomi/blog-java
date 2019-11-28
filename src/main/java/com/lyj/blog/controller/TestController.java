package com.lyj.blog.controller;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.sun.glass.ui.mac.MacFileNSURL;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.util.*;

/**
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/11/28 4:49 下午
 */
@RestController
@RequestMapping
public class TestController {

    @RequestMapping("test")
    public String test(){

        return "success";
    }

    public static void main(String[] args) throws IOException {
        //直接快速的创建文件
//        Files.write("abc".getBytes(),new File("/Users/yingjie.lu/abc"));

        //使用witer写入大量文件
//        BufferedWriter writer = Files.newWriter(new File("/Users/yingjie.lu/abc"),Charsets.UTF_8);
//        writer.write("hfsdfdhsjfsd\n");
//        writer.write("23423423\n");
//        writer.close();

        //直接快速的读取文件到list中
//        List<String> context = Files.readLines(new File("/Users/yingjie.lu/abc"), Charsets.UTF_8);
//        Iterator<String> iterator = context.iterator();
//        while(iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

        //使用reader自定义读取文件
//        BufferedReader reader = Files.newReader(new File("/Users/yingjie.lu/abc"), Charsets.UTF_8);
//        String str=null;
//        while((str=reader.readLine())!=null){
//            System.out.println(str);
//        }
//        reader.close();

        //复制文件
//        Files.copy(new File("/Users/yingjie.lu/abc"),new File("/Users/yingjie.lu/Desktop/abc"));

        //移动文件
//        Files.move(new File("/Users/yingjie.lu/abc"),new File("/Users/yingjie.lu/Desktop/abcd"));


        //先有文件，才能有文件夹
//        File file = new File("/Users/yingjie.lu/Desktop/dfdfd/ddd");
//        String name1 = file.getName();
//
//        System.out.println(name1);

        //创建嵌套目录中的文件
//        File file = new File("/Users/yingjie.lu/Desktop/test/dsfds/xcjds/dsfs");
//        file.getParentFile().mkdirs();
//        file.createNewFile();

        //创建嵌套文件夹
//        File file=new File("/Users/yingjie.lu/Desktop/test2");
//        file.mkdirs();

        //移动文件夹（如需保留原文件夹需要在移动到的路径中指明）
//        File file=new File("/Users/yingjie.lu/Desktop/test");//文件夹路径
//        Files.move(file,new File("/Users/yingjie.lu/Desktop/test2/test"));

        //复制文件夹
        File file=new File("/Users/yingjie.lu/Desktop/test");//文件夹路径

        Files.copy(file,new File("/Users/yingjie.lu/Desktop/test2/test"));




//        Files.touch(file);
//        Files.createParentDirs(file);
//        Files.move(file,file.getParentFile());


    }
}
