package com.lyj.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories
public class BlogApplication {

    public static void main(String[] args) {
//        FileUtils.listFiles(new File("/Users/yingjie.lu/Documents/note"),new FileFilter(),new DirFilter());
        SpringApplication.run(BlogApplication.class, args);
    }

}
