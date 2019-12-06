package com.lyj.blog;

import com.lyj.blog.file.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
//@EnableJpaRepositories
public class BlogApplication {

    public static void main(String[] args) {

//        DirOrFile dirOrFile = new DirOrFile();
        FileUtil.mapDir(new File("/Users/yingjie.lu/Documents/note"),
                new DirFilter(),new BlogFilter(),
                new DirCallBack(),new BlogCallBack(),DirOrFile.Instance);

        //将instance转化为树状结构
//        StringBuilder sb=new StringBuilder();
//        DirOrFile root = DirOrFile.Instance.getChild().get(0);
//        FileUtil.transformDirToHtml(root,sb);
//        String dirHtml = sb.toString();
//        System.out.println("12212");

        SpringApplication.run(BlogApplication.class, args);
    }

}
