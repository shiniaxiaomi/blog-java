package com.lyj.blog;

import com.lyj.blog.filter.DirFilter;
import com.lyj.blog.filter.FileFilter;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class BlogApplication {

    public static void main(String[] args) {
        FileUtils.listFiles(new File("/Users/yingjie.lu/Documents/note"),new FileFilter(),new DirFilter());
        SpringApplication.run(BlogApplication.class, args);
    }

}
