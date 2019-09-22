package com.pxg.app.web.controller;

import com.pxg.app.core.model.task.TaskCron;
import com.pxg.app.core.model.task.TaskQuartzSet;
import com.pxg.app.core.modelutil.KettleFileListAll;
import com.pxg.app.core.service.TaskServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @ApiOperation("增加定时策略")
    @PostMapping("/cron/add")
    public Map<Object, Object> addCorn(@RequestBody TaskCron taskCron) {
        return taskServer.addCorn(taskCron);
    }

    @ApiOperation("通过状态获取定时策略")
    @GetMapping("/cron/all")
    public Map<Object, Object> getCronAll(Integer status) {
        return taskServer.getCronAll(status);
    }

    /**
     * 每个任务只允许设置一次定时策略
     * @param taskQuartzSet
     * @return
     */
    //注册定时任务
    @ApiOperation("注册单挑定时任务")
    @PostMapping("/quartz/set/add")
    public Map<Object, Object> addTaskQuartzSet(@RequestBody TaskQuartzSet taskQuartzSet) {
        return taskServer.addTaskQuartzSet(taskQuartzSet);
    }


    @PostMapping("/quartz/set/add/list")
    @ApiOperation("通过list设置定时计划")
    public Map<Object, Object> setKettleQuartzByList(@RequestBody List<KettleFileListAll> kettleFileListAlls
            , Date startPlanTime,
                                                     @RequestParam String cornText
            , Integer classTypeId) {
        return taskServer.setKettleQuartzByList(kettleFileListAlls, startPlanTime, cornText, classTypeId);
    }
}
