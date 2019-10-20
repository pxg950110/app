package com.pxg.app.util.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/8/17
 * @Time 12:35
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
@Component
public class RabbitProducer {
    private static final Logger log = LoggerFactory.getLogger(RabbitProducer.class);
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void writeLogInfo(String loginfo) {
        this.rabbitTemplate.convertAndSend("apploginfo", loginfo);
    }

    public void stringSend2(String string) {
        this.rabbitTemplate.convertAndSend("mystring", string);
    }

    public void test(String info) {
        this.rabbitTemplate.convertAndSend("stringaaaa", "fanout", info);
    }


    public void stringSend() {
        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-mm-dd  hh:MM:ss").format(date);
//        System.out.println("[string]send msg:"+dateString);
        //第一个参数为第一的队列名
        this.rabbitTemplate.convertAndSend("mystring", dateString);

    }

    public void runKettleFileLog(String logText) {
        this.rabbitTemplate.convertAndSend("kettlefilerunstringlog", logText);
    }

    public void SendCPUINFO(Object object) {
        rabbitTemplate.convertAndSend("EXCHANGESTRING", "CPUDIRECTROUTING", object);
    }
}
