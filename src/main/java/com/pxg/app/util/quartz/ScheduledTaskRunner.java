package com.pxg.app.util.quartz;

import com.pxg.app.core.mapper.appmapper.TaskQuartzSetMapper;
import com.pxg.app.core.model.task.TaskQuartzSet;
import com.pxg.app.core.service.TaskQuartzServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 2019/11/2  16:41
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * </p>
 * <p>
 * @author pxg
 * @emil pxg950110@163.com
 * @Date 2019/11/2
 * @Version 1.0.0
 * @description </p>
 * <p>任务运行</p>
 */
@Component
@Order(value = 1)
public class ScheduledTaskRunner implements ApplicationRunner {
    private static Logger log = LoggerFactory.getLogger(ScheduledTaskRunner.class);
    @Autowired
    private TaskQuartzSetMapper taskQuartzSetMapper;

    @Autowired
    private TaskQuartzServer taskQuartzServer;


    /**
     * 程序启动完毕后，需要自动启动的任务
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(">>>>>>>>> 项目启动完毕后 ，开启=>需要自动启动的任务开始 <<<<<<<");
        // 获取所有有效的定时任务
        TaskQuartzSet taskQuartzSet = new TaskQuartzSet();
        taskQuartzSet.setStatus(1);
        List<TaskQuartzSet> taskQuartzSets = taskQuartzSetMapper.selectSelective(taskQuartzSet);
        log.info(">>>> 项目启动 执行数量 {} <<<<", taskQuartzSets.size());
        taskQuartzSets.forEach(taskQuartzSet1 -> taskQuartzServer.start("transformation-" + taskQuartzSet1.getId()));
        log.info(">>>> 项目启动完毕,开启=>需要自启的任务 结束 <<<<");
    }
}
