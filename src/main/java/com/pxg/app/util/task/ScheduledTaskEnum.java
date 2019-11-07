package com.pxg.app.util.task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum ScheduledTaskEnum {
    /**
     * 创建任务
     */
    TASK_01("transformation-1", new ScheduledTask01()),
    /**
     * 创建任务
     */
    TASK_02("scheduledTask02", new ScheduledTask02()),
    /**
     * 创建任务
     */
    TASK_03("scheduledTask03", new ScheduledTask03()),
    /**
     * 创建任务
     */
    TASK_04("scheduledTask04", new ScheduledTask04()),
    /**
     * 创建任务
     */
    TASK_05("scheduledTask05", new ScheduledTask05()),
    ;
    /**
     * 定时任务key
     */
    private String taskKey;
    /**
     * 定时任务 执行实现类
     */
    private ScheduledTaskJob scheduledTaskJob;

    ScheduledTaskEnum(String taskKey, ScheduledTaskJob scheduledTaskJob) {
        this.taskKey = taskKey;
        this.scheduledTaskJob = scheduledTaskJob;
    }

    /**
     * 初始化 所有任务
     */
    public static Map<String, ScheduledTaskJob> initScheduledTask() {
        if (ScheduledTaskEnum.values().length < 0) {
            return new ConcurrentHashMap<>();
        }
        Map<String, ScheduledTaskJob> scheduledTaskJobMap = new ConcurrentHashMap<>();
        for (ScheduledTaskEnum taskEnum : ScheduledTaskEnum.values()) {
            scheduledTaskJobMap.put(taskEnum.taskKey, taskEnum.scheduledTaskJob);
        }
        return scheduledTaskJobMap;
    }
}
