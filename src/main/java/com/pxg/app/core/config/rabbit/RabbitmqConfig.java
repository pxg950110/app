package com.pxg.app.core.config.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
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


    //队列  CPUINFO
    private final static String CPUINFO = "CPUINFO";



    /**
     * 定义对列名
     */
    private final static String staticString = "mystring";
    //交换机
    private final static String EXCHANGESTRING = "EXCHANGESTRING";

    @Bean
    public Queue CPUINFO() {
        return new Queue(CPUINFO, true);//true 持久
    }

    @Bean
    public Queue DataBaseJobRunInfo() {
        return new Queue("DATABASEJOBRUNINFO");
    }

    //日志队列和交换机绑定
    @Bean
    Binding bindingDirect2() {
        return BindingBuilder.bind(DataBaseJobRunInfo())
                .to(directExchange())
                .with("CPUDIRECTROUTING2");
    }
    /**
     *
     * @return
     */
    //直连交换机

    /**
     * @return
     */
    //直连交换机
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(EXCHANGESTRING);
    }

    //队列和交换机绑定
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(CPUINFO())
                .to(directExchange())
                .with("CPUDIRECTROUTING");
    }


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
