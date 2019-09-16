package com.pxg.app.core.service;

import com.pxg.app.core.mapper.appmapper.KettleFileListMapper;
import com.pxg.app.core.mapper.appmapper.TaskCronMapper;
import com.pxg.app.core.mapper.appmapper.TaskQuartzSetMapper;
import com.pxg.app.core.model.task.TaskCron;
import com.pxg.app.core.model.task.TaskQuartzSet;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.pxg.app.util.constant.Constant.*;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/9/7
 * @Time 10:52
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
@Service
public class TaskServer {
    private static org.slf4j.Logger log = LoggerFactory.getLogger(TaskServer.class);

    @Autowired
    private TaskCronMapper taskCronMapper;
    @Autowired
    private TaskQuartzSetMapper taskQuartzSetMapper;

    @Autowired
    private KettleFileListMapper kettleFileListMapper;
    //定时策略相关操作
    //定时策略增加

    /**
     * @param taskCron
     * @return
     */
    public Map<Object, Object> addCorn(TaskCron taskCron) {
        try {
            //插入数据库
            taskCronMapper.insert(taskCron);
            return InterfaceReturnInformation(SUCCESS_CODE, null, SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }

    /**
     * 获取所有策略信息
     * @param status
     * @return
     */
    //获取所有定时策略
    public Map<Object, Object> getCronAll(Integer status) {
        try {
            //查询数据
            List<TaskCron> taskCrons = taskCronMapper.selectSelective(new TaskCron(1));
            return InterfaceReturnInformation(SUCCESS_CODE, taskCrons, SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }


    public Map<Object, Object> addTaskQuartzSet(TaskQuartzSet taskQuartzSet) {
        try {

            TaskQuartzSet taskQuartzSet1 = new TaskQuartzSet();
            taskQuartzSet1.setJobId(taskQuartzSet.getJobId());
            taskQuartzSet1.setClassTypeId(taskQuartzSet.getClassTypeId());
            //查询任务是否存在
            Long count = taskQuartzSetMapper.count(taskQuartzSet1);
            if (count > 0) {
                //已存在 返回warn 消息
                log.warn("this job having been set the quartz ifno check it and reset this info " +
                        taskQuartzSet.toString());
                return InterfaceReturnInformation(EXIST_CODE,
                        "this job having been set the quartz ifno check it and reset this info " +
                                taskQuartzSet.toString(),
                        EXIST_MESSAGE);

            }

            //获取===kettlefilelist相关信息
            taskQuartzSet.setCreateTime(new Date());
            //所有前端的cordId都设置为0
            taskQuartzSet.setCronId(0);
            //
            String cronText = taskQuarTime(taskQuartzSet.getCronText());
            if (cronText == null) {
                log.error("this time set errors");
                return InterfaceReturnInformation(ERROR_CODE, "this time set errors", ERROR_MESSAGE);
            }
            taskQuartzSet.setCronText(
                    cronText
            );
            taskQuartzSet.setPlanTimes(0);
            taskQuartzSet.setJobTypeId(kettleFileListMapper.selectByPrimaryKey(taskQuartzSet.getJobId()).getJobTypeId());
            //状态
            taskQuartzSet.setStatus(1);
            //插入数据
            taskQuartzSetMapper.insert(taskQuartzSet);
            return InterfaceReturnInformation(SUCCESS_CODE, null, SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }

    public String taskQuarTime(String var1) {
        //分割时间单位
        Long timeCount = Long.valueOf(var1.split("-")[0]);
        String timeUtil = var1.split("-")[1];
        String result = null;
        log.info(var1);
        //s	秒
        switch (timeUtil) {
            case "s":
                if (timeCount <= 59 && timeCount > 0) {
                    result = "*/" + timeCount + " * * * *?";
                }
                break;
            case "m":
                if (timeCount <= 59 && timeCount > 0) {
                    result = "* */" + timeCount + " * * *?";
                }
                break;
            case "h":
                if (timeCount <= 59 && timeCount > 0) {
                    result = "* * */" + timeCount + " * *?";
                }
                break;
            case "d":
                if (timeCount <= 30 && timeCount > 0) {
                    result = "* * */" + timeCount + " * *?";
                }
                break;
//                1 * * * * ?
        }
        //m	分
        //h	时
        //d	日
        //M	月
        //y	年


        return result;
    }
}
