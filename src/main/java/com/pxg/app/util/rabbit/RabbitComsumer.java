package com.pxg.app.util.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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

    /**
     * 接收mystring的消息
     */
    @RabbitListener(queues = "kettlefilerunstringlog")
    public void reciveMyString(Message message) {
        log.info(new String(message.getBody()));
        //写入执行日志

    }

}
