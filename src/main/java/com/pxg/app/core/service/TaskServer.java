package com.pxg.app.core.service;

import com.pxg.app.core.mapper.appmapper.TaskCronMapper;
import com.pxg.app.core.mapper.appmapper.TaskQuartzSetMapper;
import com.pxg.app.core.model.task.TaskCron;
import com.pxg.app.core.model.task.TaskQuartzSet;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            //查询数据
            taskQuartzSetMapper.insert(taskQuartzSet);
            return InterfaceReturnInformation(SUCCESS_CODE, null, SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }
}
