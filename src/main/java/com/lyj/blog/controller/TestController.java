package com.lyj.blog.controller;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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

    }
}
