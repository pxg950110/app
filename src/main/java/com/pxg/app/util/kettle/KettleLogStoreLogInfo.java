package com.pxg.app.util.kettle;

import org.pentaho.di.core.logging.KettleLogStore;
import org.pentaho.di.core.logging.LoggingBuffer;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/9/1
 * @Time 11:37
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
public class KettleLogStoreLogInfo {

    /**
     * 通过LogChannelId 获取日志
     * @param logChannelId
     * @return
     */
    public static String getKettleLogStoreLogInfoByLogChannelId(String logChannelId) {
        //日志流
        LoggingBuffer buffer = KettleLogStore.getAppender();
        //logChannelId 获取日志
        String logText = buffer.getBuffer(logChannelId, true).toString();
        return logText;
    }


}
