package com.lyj.blog.timer;

import com.lyj.blog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 用于定时同步更新博客
 * @author yingjie.lu
 * @version 1.0
 * @date 2019/12/13 8:30 下午
 */


@Component
@Slf4j
public class PullTimer {

    @Autowired
    private BlogService blogService;

    //凌晨3点自动更新
    @Scheduled(cron = "* * 3 * * ?")
    private void notePull() throws IOException {
        try{
            log.debug("执行定时任务。。。");
            blogService.init();
        }catch (Exception e){
            log.error("定时任务执行异常："+e);
        }
    }

}
