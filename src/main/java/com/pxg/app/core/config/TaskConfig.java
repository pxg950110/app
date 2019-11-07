package com.pxg.app.core.config;

import com.pxg.app.util.task.ScheduledTaskEnum;
import com.pxg.app.util.task.ScheduledTaskJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Map;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/9/7
 * @Time 13:36
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description 任务配置
 */
@Configuration
@EnableScheduling
public class TaskConfig {

    /**
     * 日志
     * log4j
     */
    private static final Logger log = LoggerFactory.getLogger(TaskConfig.class);

    /**
     * 定时任务线程池
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        log.info("===>>>>>创建定时任务调度线程池 start<<<<<===");
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        //设置线程大小
        threadPoolTaskScheduler.setPoolSize(20);
        //
        threadPoolTaskScheduler.setThreadNamePrefix("kettleTask-");
        //设置前面执行结束后再执行
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        //
        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);
        //
        log.info("===>>>>>创建定时任务调度线程池 end<<<<<===");
        return threadPoolTaskScheduler;
    }

    /**
     * 初始化定时任务Map
     * key:任务key
     * value:执行接口实现
     * @return
     */
//    public interface ScheduledTaskJob
    @Bean(name = "scheduledTaskJobMap")
    public Map<String, ScheduledTaskJob> scheduledTaskJobMap() {
        return ScheduledTaskEnum.initScheduledTask();
    }















}
