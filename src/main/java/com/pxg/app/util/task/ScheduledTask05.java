package com.pxg.app.util.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 2019/10/27  12:43
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * </p>
 * <p>
 * @author pxg
 * @emil pxg950110@163.com
 * @Date 2019/10/27
 * @Version 1.0.0
 * @description </p>
 */
public class ScheduledTask05 implements ScheduledTaskJob {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTask05.class);

    @Override
    public void run() {
        log.info("ScheduledTask => 05 run 当前线程名称 {}", Thread.currentThread().getName());
    }
}
