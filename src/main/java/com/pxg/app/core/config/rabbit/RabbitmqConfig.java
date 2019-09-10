package com.pxg.app.core.config.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/8/17
 * @Time 12:24
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description rabbitmq配置类
 */
@Configuration
public class RabbitmqConfig {
    //日志
    private static final Logger log = LoggerFactory.getLogger(RabbitmqConfig.class);


    /**
     * 定义对列名
     */
    private final static String staticString = "mystring";

    @Bean
    public Queue String() {
        return new Queue(staticString);
    }

    @Bean
    public Queue String2() {
        return new Queue("apploginfo");
    }

    //设置不同的消息体


    @Bean
    public Queue kettleString() {
        //kettle  文件 tran run 日志
        return new Queue("kettlefilerunstringlog");

    }


}
