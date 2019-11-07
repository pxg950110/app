package com.pxg.app.util.task;

import com.pxg.app.core.mapper.appmapper.TaskQuartzSetMapper;
import com.pxg.app.core.model.task.TaskQuartzSet;
import com.pxg.app.core.service.ScheduledTaskService;
import com.pxg.app.core.service.TaskServer;
import com.pxg.app.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <p>
 * 2019/10/27  12:37
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * </p>
 * <p>
 * @author pxg
 * @emil pxg950110@163.com
 * @Date 2019/10/27
 * @Version 1.0.0
 * @description </p>
 */
@EnableAsync
public class ScheduledTask02 implements ScheduledTaskJob {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTask02.class);

    /**
     * 通过线程名称获取数据库中的内容
     */
    @Autowired
    private static TaskQuartzSetMapper taskQuartzSetMapper;
    @Autowired
    private TaskServer taskServer;
    @Autowired
    private ScheduledTaskService scheduledTaskService;

    private void getTaskQuartzSetMapper() {
        this.taskQuartzSetMapper = (TaskQuartzSetMapper) SpringContextUtil.getBean("taskQuartzSetMapper");
    }

    public void getScheduledTaskService() {
        this.scheduledTaskService = (ScheduledTaskService) SpringContextUtil.getBean("scheduledTaskService");
    }

    public void getTaskServer() {
        this.taskServer = (TaskServer) SpringContextUtil.getBean("taskServer");
    }

    void init() {
        this.getTaskQuartzSetMapper();
        this.getScheduledTaskService();
        this.getTaskServer();
    }

    @Async
    @Override
    public void run() {
        this.init();
        log.info("ScheduledTask => 02 run 当前线程名称 {}", Thread.currentThread().getName());
        //执行 job
        log.info(" 》》》》》》》》》》》{}《《《《《", Thread.currentThread().getId());
        //获取执行的任务map
        log.info(Thread.currentThread().getName());
        String taskKey = null;
        try {
            taskKey = Thread.currentThread().getName().split(">")[0];
            //通过taskKey查询
            TaskQuartzSet taskQuartzSet =
                    taskQuartzSetMapper.selectByPrimaryKey(Integer.valueOf(taskKey.replace("transformation-", "")));
            log.info(JsonUtils.ObjectToJSONString(taskQuartzSet));
            if (taskQuartzSet.getId() == null) {
                //如果数据中数据删除
                //停止任务
                log.info("数据中不存在此条任务记录,任务已停止");
                scheduledTaskService.stop(taskKey);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}
