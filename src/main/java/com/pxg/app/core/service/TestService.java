package com.pxg.app.core.service;

import com.pxg.app.util.kettle.KettleInit;
import com.pxg.app.util.rabbit.RabbitProducer;
import org.pentaho.di.core.exception.KettleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/9/1
 * @Time 11:00
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
@Service
public class TestService {

    @Autowired
    private RabbitProducer rabbitProducer;

    public void test() throws KettleException {
        KettleInit kettleInit = new KettleInit();
//        Trans trans=kettleInit.Test();
//        String lonText= KettleLogStoreLogInfo.getKettleLogStoreLogInfoByLogChannelId(trans.getLogChannelId());
        rabbitProducer.stringSend2("成功");
//        rabbitProducer.runKettleFileLog(lonText);
//        trans.setStopped(true);

    }
}
