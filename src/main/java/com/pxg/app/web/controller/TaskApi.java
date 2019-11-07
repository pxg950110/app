package com.pxg.app.web.controller;

import com.pxg.app.core.model.task.TaskQuartzSet;
import com.pxg.app.core.service.ScheduledTaskService;
import com.pxg.app.core.service.TaskQuartzServer;
import com.pxg.app.core.service.TaskServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/9/7
 * @Time 11:14
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
@Api(description = "任务相关配置")
@RestController
@RequestMapping("/api/task")
public class TaskApi {
    @Autowired
    private TaskServer taskServer;

    @Autowired
    private ScheduledTaskService scheduledTaskService;

    @Autowired
    private TaskQuartzServer taskQuartzServer;

    @ApiOperation("注册定时任务")
    @PostMapping("/add/task/regist")
    public Map<Object, Object> addTaskByKey(@RequestBody TaskQuartzSet taskQuartzSet) {
        return taskServer.addTaskByKey(taskQuartzSet);
    }


    @GetMapping("/all/list")
    @ApiOperation("获取所有定时任务列表")
    public Map<Object, Object> getAllTaskQuartzSet() {
        return taskQuartzServer.getAllTaskQuartzSet();
    }

    /**
     * 根据任务key => 启动任务
     */
    @GetMapping("/start")
    public String start(@RequestParam("taskKey") String taskKey) {
        scheduledTaskService.start(taskKey);
        return "start success";
    }

    /**
     * 根据任务key => 停止任务
     */
    @GetMapping("/stop")
    public String stop(@RequestParam("taskKey") String taskKey) {
        if (taskQuartzServer.stop(taskKey)) {
            return "stop success";
        }
        return "stop error";
    }


    //增加任务

    @GetMapping("/start2")
    public String start2(@RequestParam("taskKey") String taskKey) {
        taskQuartzServer.start(taskKey);
        return "stop success";
    }

    /**
     * 根据任务key => 重启任务
     */
    @GetMapping("/restart")
    public String restart(@RequestParam("taskKey") String taskKey) {
        scheduledTaskService.restart(taskKey);
        return "restart success";
    }

    @GetMapping("/printlnTask")
    public String printlnTask() {
        return scheduledTaskService.printlnTask();
    }


    /**
     * 获取所有正在执行的定时任务
     * @return
     */
    @GetMapping("/quartz/job/all")
    public Set getAll() {
        return taskQuartzServer.getAll();
    }
}
