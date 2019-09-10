package com.pxg.app.util.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/9/7
 * @Time 10:24
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
@Component
public class TaskScheduleRun implements CommandLineRunner {

    private static Logger log = LoggerFactory.getLogger(TaskScheduleRun.class);

    /**
     * 开始时启动任务
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("============开始执行调度任务===========");
        log.info("-------------执行调度任务的方法--------------------");
        log.info("*************结束执行调度任务*************");
    }
}
