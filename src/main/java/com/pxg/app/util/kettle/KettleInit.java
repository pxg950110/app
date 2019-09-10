package com.pxg.app.util.kettle;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.LogLevel;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/9/1
 * @Time 10:35
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description kettle初始化
 */
public class KettleInit {

    private static final Logger log = LoggerFactory.getLogger(KettleInit.class);


    /**
     * 测试运行kettle
     * @throws KettleException
     */
    public Trans runTransTest(String fileName) throws KettleException {
        KettleEnvironment.init();
        TransMeta transMeta = new TransMeta(fileName);
        //kettle文件路径
        //trans
        Trans trans = new Trans(transMeta);
        trans.setLogLevel(LogLevel.ROWLEVEL);
        //运行数据
        //无参数运行
        trans.execute(null);
        trans.waitUntilFinished();
        //获取日志
//        String logText=KettleLogStoreLogInfo.getKettleLogStoreLogInfoByLogChannelId(
//                x.getLogChannelId()
//        );
        return trans;
    }


    public Job runJobByFileName(String fileName) throws KettleException {
        KettleEnvironment.init();
        JobMeta jobMeta = new JobMeta(fileName, null);
        Job job = new Job(null, jobMeta);
//       Job job=new Job("测试", fileName,null);
        job.setLogLevel(LogLevel.ROWLEVEL);
        job.setDaemon(true);
        job.start();
        System.err.println(job.getName());
        return job;
    }

    //停止正在运行的job


}
