package com.pxg.app.util.rabbit;

import com.pxg.app.core.mapper.appmapper.LogInfoMapper;
import com.pxg.app.core.model.klog.LogInfo;
import com.pxg.app.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/9/1
 * @Time 14:53
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description 消息消费者
 */
@Component
public class RabbitComsumer {
    private static Logger log = LoggerFactory.getLogger(RabbitComsumer.class);

    @Autowired
    private LogInfoMapper logInfoMapper;
    /**
     * 接收mystring的消息
     */
    @RabbitListener(queues = "kettlefilerunstringlog")
    public void reciveMyString(Message message) {
        log.info(new String(message.getBody()));
        //写入执行日志

    }


    @RabbitListener(queues = "CPUINFO")
    public void reciveCPUINFO(Map<Object, Object> map) {
        System.err.println(JsonUtils.ObjectToJSONString(map));
    }

    @RabbitListener(queues = "DATABASEJOBRUNINFO")
    public void reciveDatabaseJonRunInfo(Map<Object, Object> map) {
        //执行rabbitmq接收到的日志数据写入日志表
        Date startTime = new Date((Long) map.get("startTime"));
        Date endTime = new Date((Long) map.get("endTime"));
        //日志表设计
        LogInfo logInfo = new LogInfo(
                (String) map.get("uuid"), (String) map.get("jobObjectId"),
                (Integer) map.get("repositoyId"), (String) map.get("logInfo"), 1, null,
                startTime,
                endTime, new Date()
//                String klogUuid, String klogJobId, Integer klogRepositoryId,
//                String klogLogDetail, Integer klogLogStatus, String klogLogExtend1,
//                Date klogStartTime, Date klogEndTime, Date klogCreateTime
        );
        try {
            logInfoMapper.insert(logInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
//

    }

}
