package com.pxg.app.util.task;

import com.pxg.app.core.mapper.appmapper.TbMemoryRecordMapper;
import com.pxg.app.util.rabbit.RabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/8/18
 * @Time 22:55
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description 静态注解调度任务
 * 此方法只能执行单线程
 */
@Component
@Configuration
@EnableScheduling
public class StaticScheduleTask {

    @Autowired
    private RabbitProducer rabbitProducer;

    @Autowired
    private TbMemoryRecordMapper tbMemoryRecordMapper;
    //添加定时任务
//    @Scheduled(cron = "*/3 * * * * ?")
//    public void configureTask() throws IOException {
////        tbMemoryRecordMapper.insert(CpuInfo.getServerMemoryRecord());
//        WebSocketServer.sendInfo(JsonUtils.ObjectToJSONString(CpuInfo.getServerMemoryRecord()),"sid123");
//    }

}
