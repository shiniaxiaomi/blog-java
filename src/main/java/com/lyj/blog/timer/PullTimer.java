package com.lyj.blog.timer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 用于定时同步更新博客
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/13 8:30 下午
 */


//@Component
@Slf4j
public class PullTimer {

    @Value("${blogVisitTimesFilePath}")
    String blogVisitTimesFilePath;

    //凌晨3点自动更新
    @Scheduled(cron = "0 0 3 * * ?")
    private void notePull() {

        log.info("手动更新成功！！！");
    }

    //凌晨4点备份blogVisitTimes文件
    @Scheduled(cron = "0 0 4 * * ?")
    private void backupVisitTimes() {

    }

}
