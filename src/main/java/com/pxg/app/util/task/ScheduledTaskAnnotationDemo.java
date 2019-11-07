package com.pxg.app.util.task;

import com.pxg.app.core.mapper.appmapper.KettleRepositoryTableMapper;
import com.pxg.app.core.service.KettleService;
import com.pxg.app.kettle.model.KettleRepositoryDataInfo;
import com.pxg.app.util.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.pxg.app.util.constant.Constant.TYPE_TRANSFORMATION;

/**
 * <p>
 * 2019/10/31  23:03
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * </p>
 * <p>
 * @author pxg
 * @emil pxg950110@163.com
 * @Date 2019/10/31
 * @Version 1.0.0
 * @description </p>
 */
@Component
public class ScheduledTaskAnnotationDemo {
    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(ScheduledTaskAnnotationDemo.class);

    @Autowired
    private KettleRepositoryTableMapper kettleRepositoryTableMapper;
    @Autowired
    private KettleService kettleService;

    //    @Scheduled(cron = "0/7 * * * * ?")
    public void ScheduledTask01() {
        log.info("==>>" + Constant.dateToFormatString(new Date()) + "==>静态调度任务");
        log.info("==>> {}", ScheduledTaskAnnotationDemo.class.getName());
//
        KettleRepositoryDataInfo kettleRepositoryDataInfo = new KettleRepositoryDataInfo();
        kettleRepositoryDataInfo.setObjectId(String.valueOf(1));
        kettleRepositoryDataInfo.setObjectType(TYPE_TRANSFORMATION);
        kettleService.runKettleDatabaseJobOrTransformation(kettleRepositoryDataInfo, 3);
    }
}
