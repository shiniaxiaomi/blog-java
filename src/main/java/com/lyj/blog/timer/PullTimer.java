package com.lyj.blog.timer;

import com.lyj.blog.service.BlogService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 用于定时同步更新博客
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/13 8:30 下午
 */


@Component
public class PullTimer {

    @Autowired
    private BlogService blogService;

    @Value("${blogVisitTimesFilePath}")
    String blogVisitTimesFilePath;

    //凌晨3点自动更新
    @Scheduled(cron = "0 0 3 * * ?")
    private void notePull() {
        boolean b = blogService.initByManual();
        if(!b){
            System.out.println("手动更新失败！！！");
        }
    }

    //凌晨4点备份blogVisitTimes文件
    @Scheduled(cron = "0 0 4 * * ?")
    private void backupVisitTimes() {
        try {
            FileUtils.copyFile(new File(blogVisitTimesFilePath+"blogVisitTimes"),new File(blogVisitTimesFilePath+"blogVisitTimes-"+LocalDateTime.now()));
        } catch (IOException e) {
            System.out.println("blog访问次数备份失败");
        }

    }

}
