package com.pxg.app.util.quartz;

import com.pxg.app.core.model.task.TaskQuartzSet;
import com.pxg.app.core.service.KettleService;
import com.pxg.app.util.JsonUtils;
import com.pxg.app.util.constant.Constant;
import com.pxg.app.util.task.SpringContextUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/9/7
 * @Time 13:39
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 * <P>
 *     定时任务job执行
 * </P>
 */
public class QuartzFactory implements Job {
    private static Logger log = LoggerFactory.getLogger(QuartzFactory.class);

    @Autowired
    private KettleService kettleService;

    private void getKettleService() {
        this.kettleService = (KettleService) SpringContextUtil.getBean("kettleService");
    }

    /**
     * @param jobExecutionContext 上下文内容
     * @throws JobExecutionException 异常
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext)
            throws JobExecutionException {
        this.getKettleService();
        log.info("开始执行定时任务>>>>> {} ", Constant.dateToFormatString(new Date()));
        log.info(JsonUtils.ObjectToJSONString(jobExecutionContext.getJobDetail().getJobDataMap()));
        //执行一次 任务次数+1
        TaskQuartzSet taskQuartzSet = (TaskQuartzSet) jobExecutionContext.getJobDetail().getJobDataMap().get("jobInfo");
        //获取执行的内容
        log.info(JsonUtils.ObjectToJSONString(taskQuartzSet));
        //
        //执行调用的job
        Map<Object, Object> map = kettleService.runKettleTaskInfo(taskQuartzSet);
        log.info(map.toString());

        //执行次数加1
        kettleService.addTaskQuartzTimes(taskQuartzSet);

    }


}
