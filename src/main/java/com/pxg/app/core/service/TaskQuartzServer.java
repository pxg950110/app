package com.pxg.app.core.service;

import com.pxg.app.core.mapper.appmapper.TaskCronMapper;
import com.pxg.app.core.mapper.appmapper.TaskQuartzSetMapper;
import com.pxg.app.core.model.task.TaskQuartzSet;
import com.pxg.app.util.JsonUtils;
import com.pxg.app.util.constant.Constant;
import com.pxg.app.util.quartz.QuartzFactory;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 2019/11/4  15:32
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * </p>
 * <p>
 * @author pxg
 * @emil pxg950110@163.com
 * @Date 2019/11/4
 * @Version 1.0.0
 * @description </p>
 * <detail>
 * 定时任务 服务
 * </detail>
 */
@Service
public class TaskQuartzServer {
    private static Logger log = LoggerFactory.getLogger(TaskQuartzServer.class);

    @Autowired
    private org.quartz.Scheduler scheduler;

    //任务mapper
    @Autowired
    private TaskQuartzSetMapper taskQuartzSetMapper;
    //定时器
    @Autowired
    private TaskCronMapper taskCronMapper;


    /**
     * 通过taskKey 启动任务
     * @param taskKey
     * @return
     */
    public Boolean start(String taskKey) {
        try {
            //启动调度器
            scheduler.start();
            //通过 taskKey确定任务是否已执行
            JobKey jobKey = new JobKey(taskKey);
            //通过数据库中的id执行任务
            if (scheduler.checkExists(jobKey)) {
                log.info("任务id：{} 已存在", taskKey);
                return false;
            }
            //根据
            //查询任务执行相关内容
            TaskQuartzSet taskQuartzSet = taskQuartzSetMapper.selectByPrimaryKey(
                    Integer.valueOf(taskKey.replace("transformation-", ""))
            );

            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("jobInfo", taskQuartzSet);
            //获取corn
            String cornString = taskCronMapper.selectByPrimaryKey(taskQuartzSet.getCronId()).getcron();

            // 构建Job信息  只执行一个类
            JobDetail jobDetail = JobBuilder.newJob(QuartzFactory.class).withIdentity(
                    //任务taskKey,
                    taskKey,
                    //group  v1.0版本不加入分组
                    null
            ).setJobData(jobDataMap).build();
            // Cron表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(cornString);
            if (taskQuartzSet.getStartPlanTime() == null) {
                taskQuartzSet.setStartPlanTime(new Date());
            }
            Trigger trigger = TriggerBuilder.newTrigger()
                    //设置开始书简
                    .startAt(taskQuartzSet.getStartPlanTime())
                    //设置绑定任务   默认分组
                    .withIdentity(taskKey, JobKey.DEFAULT_GROUP)
                    //设置定时
                    .withSchedule(cron)
                    .build();
            //不存在创建
            scheduler.scheduleJob(jobDetail, trigger);

            log.info("定时任务创建完毕");

            //v1.0版本 设置is_run为1
            taskQuartzSet.setIsRun(1);
            taskQuartzSetMapper.updateByPrimaryKeySelective(taskQuartzSet);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            log.error("-- {} >>>>  error info:{}", Constant.dateToFormatString(new Date()), e.getMessage());
            return false;
        }
        //JobDetail jobDetail= JobBuilder.newJob("","");
    }


    /**
     * 停止任务
     * @param taskKey
     * @return
     */
    public boolean stop(String taskKey) {
        JobKey jobKey = new JobKey(taskKey, JobKey.DEFAULT_GROUP);
        log.info("停止任务");
        try {
            //停止任务
            scheduler.pauseJob(jobKey);
            //任务计划中移除
            scheduler.deleteJob(jobKey);
            //更新 status为1 is_run为0
            TaskQuartzSet taskQuartzSet = new TaskQuartzSet();
            taskQuartzSet.setId(Integer.valueOf(taskKey.replace("transformation-", "")));
            taskQuartzSet.setStatus(0);
            taskQuartzSet.setIsRun(0);
            taskQuartzSetMapper.updateByPrimaryKeySelective(taskQuartzSet);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    //获取所有在任务列表中的定时任务
    public Set getAll() {
        Set<String> strings = null;
        try {
            GroupMatcher groupMatcher = GroupMatcher.groupContains(JobKey.DEFAULT_GROUP);
            strings = scheduler.getTriggerKeys(
                    groupMatcher
            );
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        log.info(JsonUtils.ObjectToJSONString(strings));
        return strings;
    }


    /**
     * 获取所有定时任务列表
     */
    public Map<Object, Object> getAllTaskQuartzSet() {
        Map<Object, Object> map = new HashMap<>();

        try {

            return Constant.InterfaceReturnInformation(Constant.SUCCESS_CODE, taskQuartzSetMapper.selectSelective(new TaskQuartzSet()), Constant.SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(">>>>错误日志：>>>>{} ", e.getMessage());
            return Constant.InterfaceReturnInformation(Constant.ERROR_CODE, e.getMessage(), Constant.ERROR_MESSAGE);
        }

    }

}
