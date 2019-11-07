package com.pxg.app.core.service;

import com.pxg.app.core.mapper.appmapper.TaskCronMapper;
import com.pxg.app.core.mapper.appmapper.TaskQuartzSetMapper;
import com.pxg.app.core.model.task.TaskQuartzSet;
import com.pxg.app.util.JsonUtils;
import com.pxg.app.util.task.ScheduledTaskJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 2019/11/2  22:53
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
 */
@Service
public class ScheduledTaskService {
    /**
     * 日志
     */
    private static final Logger log =
            LoggerFactory.getLogger(ScheduledTaskService.class);

    //可重入锁
    private ReentrantLock lock = new ReentrantLock();


    //定时
    @Autowired
    private TaskCronMapper taskCronMapper;

    //    >>>>>>定时任务表mapper<<<<<<
    @Autowired
    private TaskQuartzSetMapper taskQuartzSetMapper;

    @Autowired
    // 定时任务 >>>>>>>线程池<<<<<<<
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     * 所有定时任务存放Map
     * key :任务 key
     * value:任务实现
     */
    @Autowired
    @Qualifier(value = "scheduledTaskJobMap")
    private Map<String, ScheduledTaskJob> scheduledTaskJobMap;

    /**
     * 存放已经启动的任务map
     */
    private Map<String, ScheduledFuture>
            scheduledFutureMap = new ConcurrentHashMap<>();


    /**
     * 获取所有有效的任务列表
     * @return
     */
    public List<TaskQuartzSet> taskList() {
        log.info(">>>>>>获取任务列表开始<<<<<<");
        TaskQuartzSet taskQuartzSet = new TaskQuartzSet();
        taskQuartzSet.setStatus(1);
        //获取有效的任务
        List<TaskQuartzSet> taskQuartzSets =
                taskQuartzSetMapper.selectSelective(taskQuartzSet);
        if (CollectionUtils.isEmpty(taskQuartzSets)) {
            return new ArrayList<>();
        }
        for (TaskQuartzSet quartzSet : taskQuartzSets) {
            String taskKey = "transformation-" + String.valueOf(quartzSet.getId());
            //是否启动标记处理
        }
        log.info(">>>>>>获取任务列表结束<<<<<<");
        return taskQuartzSets;
    }

    /**
     * 根据任务key 启动任务
     */
    public boolean start(String taskKey) {
        log.info(">>>>>>启动任务 {} 开始<<<<<<", taskKey);
        //添加锁放入一个线程启动。防止多人启动多次
        lock.lock();
        log.info(">>>>>> 添加任务启动锁完毕 <<<<<<");

        threadPoolTaskScheduler.setThreadNamePrefix(taskKey + ">");
        try {
            //校验是否已经启动
            if (isStart(taskKey)) {
                log.info(">>>>>> 当前任务已经启动，无需重复启动 <<<<<<");
                return false;
            }
            //根据key 数据库获取任务配置信息
            TaskQuartzSet taskQuartzSet = taskQuartzSetMapper.selectByPrimaryKey(Integer.valueOf(taskKey.replace("transformation-", "")));
            //校验是否存在
            if (!scheduledTaskJobMap.containsKey(taskKey)) {
                log.info("taskKey不存在");
                //执行默认的
                this.doStartTask(taskQuartzSet, null);
            } else {
                //启动任务
                this.doStartTask(taskQuartzSet);
            }
            log.info(">>>>> 开始启动任务 <<<<<<");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            //释放锁
            lock.unlock();
            log.info(">>>>>> 释放任务启动锁完毕 <<<<<<");
        }
        log.info(">>>>> 启动任务 {} 结束 <<<<<<", taskKey);

        //v1.0版本暂不处理 启动成功之后更新 is_run为1
        return true;

    }


    /**
     * 根据key 停止任务
     * @param taskKey
     * @return
     */
    public boolean stop(String taskKey) {
        log.info(">>>>>> 进入停止任务 {}  >>>>>>", taskKey);
        //当前任务实例是否存在
        boolean taskStartFlag = scheduledFutureMap.containsKey(taskKey);
        log.info(">>>>>> 当前任务实例是否存在 {}", taskStartFlag);
        if (taskStartFlag) {
            //获取任务实例
            ScheduledFuture scheduledFuture = scheduledFutureMap.get(taskKey);
            //关闭实例
            scheduledFuture.cancel(true);
        }
        log.info(">>>>>> 结束停止任务 {}  >>>>>>", taskKey);
        return taskStartFlag;
    }

    /**
     * 根据任务key  重启任务
     */
    public Boolean restart(String taskKey) {
        log.info(">>>>>> 重启任务 {}  任务<<<<<<", taskKey);
        //停止
        this.stop(taskKey);
        //启动
        return this.start(taskKey);
    }


    /**
     * 程序初始化>>>>>>>>启动所有正常状态的任务
     */
    public void initAllTask(List<TaskQuartzSet> taskQuartzSets) {
        log.info(">>>程序启动<<<  初始化所有任务开始 ！ size={} ", taskQuartzSets.size());
        //如果为空 不处理
        if (CollectionUtils.isEmpty(taskQuartzSets)) {
            return;
        }
        for (TaskQuartzSet taskQuartzSet : taskQuartzSets) {
            //任务key
            String taskKey = "transformation-" + String.valueOf(taskQuartzSet.getId());
            //检测是否已经启动
            if (this.isStart(taskKey)) {
                log.info(">>>>>> 任务{} 已经启动<<<<<<", taskKey);
                continue;
            }
            //启动任务
            log.info(">>>>> 启动任务 {} 任务启动 <<<<<<", taskKey);
            this.start(taskKey);
        }
        log.info(">>>>>>程序启动>>>>>>初始化所有任务结束");
    }


    /**
     * @param taskKey
     * @return
     */

    public boolean isStart(String taskKey) {
        if (scheduledFutureMap.containsKey(taskKey)) {
            if (!scheduledFutureMap.get(taskKey).isCancelled()) {
                return true;
            }
        }
        return false;
    }


    /**
     * 默认程序
     * @param taskQuartzSet
     */
    public void doStartTask(TaskQuartzSet taskQuartzSet, String initString) {

        //任务key
        String taskKey = "transformation-" + String.valueOf(taskQuartzSet.getId());
        //定时表达式
        String taskCorn = taskCronMapper.selectByPrimaryKey(taskQuartzSet.getCronId()).getcron();
        //获取需要定时调度的接口
        ScheduledTaskJob scheduledTaskJob = scheduledTaskJobMap.get("scheduledTask02");
        //
        log.info(">>>>>任务 [{}] ,corn={}", taskKey, taskCorn);
        //执行
        ScheduledFuture scheduledFuture = threadPoolTaskScheduler.schedule(
                scheduledTaskJob, new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext triggerContext) {
                        CronTrigger cronTrigger = new CronTrigger(taskCorn);

                        return cronTrigger.nextExecutionTime(triggerContext);
                    }
                }
        );
        //启动的任务放入map
        scheduledFutureMap.put(taskKey, scheduledFuture);
    }


    /**
     * 执行启动任务
     * @param taskQuartzSet
     */
    public void doStartTask(TaskQuartzSet taskQuartzSet) {

        //

        //任务key
        String taskKey = "transformation-" + String.valueOf(taskQuartzSet.getId());
        //定时表达式
        String taskCorn = taskCronMapper.selectByPrimaryKey(taskQuartzSet.getCronId()).getcron();
        //获取需要定时调度的接口
        ScheduledTaskJob scheduledTaskJob = scheduledTaskJobMap.get(taskKey);
        //
        log.info(">>>>>任务 [{}] ,corn={}", taskKey, taskCorn);
        //执行
        ScheduledFuture scheduledFuture = threadPoolTaskScheduler.schedule(
                scheduledTaskJob, new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext triggerContext) {
                        CronTrigger cronTrigger = new CronTrigger(taskCorn);
                        return cronTrigger.nextExecutionTime(triggerContext);
                    }
                }
        );
        //启动的任务放入map
        scheduledFutureMap.put(taskKey, scheduledFuture);
    }

    //打印正在执行的任务
    public String printlnTask() {
        log.info("当前启动的任务项 size {}", scheduledFutureMap.size());
        String printlnTask = JsonUtils.ObjectToJSONString(scheduledFutureMap);
        log.info("当前启动的任务项 {}", printlnTask);
        return printlnTask;
    }
}
