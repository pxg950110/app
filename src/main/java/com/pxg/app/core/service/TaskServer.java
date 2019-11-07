package com.pxg.app.core.service;

import com.pxg.app.core.mapper.appmapper.KettleFileListMapper;
import com.pxg.app.core.mapper.appmapper.TaskCronMapper;
import com.pxg.app.core.mapper.appmapper.TaskQuartzSetMapper;
import com.pxg.app.core.model.task.TaskQuartzSet;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
     * 定时任务注册机制
     */
    public Map<Object, Object> addTaskByKey(TaskQuartzSet taskQuartzSet) {
        //执行任务
        //状态  1 执行  0 暂停  2 删除
        //如果状态为空 则设置状态为默认执行
        if (taskQuartzSet.getStatus() == null)
            taskQuartzSet.setStatus(1);
        //创建时间
        taskQuartzSet.setCreateTime(new Date());


        // 设置开始执行时间

        //判断类型 文件类型
        if (taskQuartzSet.getClassType().equals(CLASSTYPE_FILE)) {
            // v1.0版本的文件只支持上传类型的文件
            //如果 jobId不为空
            taskQuartzSetMapper.insert(taskQuartzSet);
        } else if (taskQuartzSet.getClassType().equals(CLASSTYPE_REPOSITORY)) {//资源库类型
            //如果为资源库
            if (taskQuartzSet.getRepositoryId() == null || taskQuartzSet.getRepositoryId() == 0) {
                return InterfaceReturnInformation(WARN_CODE,
                        "未选择任何资源库",
                        WARN_MESSAGE);
            }
            taskQuartzSetMapper.insert(taskQuartzSet);
        } else {
            return InterfaceReturnInformation(WARN_CODE,
                    "未知的任务来源类型",
                    WARN_MESSAGE);
        }
        return null;
    }
}
