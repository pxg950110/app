package com.pxg.app.util.cpu;


import com.pxg.app.core.model.monitor.TbMemoryRecord;
import com.pxg.app.util.constant.Constant;
import com.sun.management.OperatingSystemMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.SystemInfo;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Properties;

/**
 * <p>
 * 2019/9/28  0:56
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * </p>
 * <p>
 * @author pxg
 * @emil pxg950110@163.com
 * @Date 2019/9/28
 * @Version 1.0.0
 * @description 获取CPU相关内容
 *
 * </p>
 */
public class CpuInfo implements Serializable {

    private static Logger log = LoggerFactory.getLogger(CpuInfo.class);


    /**
     * <p>获取系统日志信息</p>
     * @return
     */
    public static TbMemoryRecord getServerMemoryRecord() {
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystemMXBean operatingSystemMXBean =
                (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        Double totalMemory = Double.valueOf(new DecimalFormat("#.##").format(
                operatingSystemMXBean.getTotalPhysicalMemorySize() / 1024.0 / 1024));
        //空闲内存
        Double freeMemory = Double.valueOf(new DecimalFormat("#.##").format(
                operatingSystemMXBean.getFreePhysicalMemorySize() / 1024.0 / 1024));
        //已使用内存
        Double usedMemory = Double.valueOf(new DecimalFormat("#.##").format(
                (operatingSystemMXBean.getTotalPhysicalMemorySize() - operatingSystemMXBean.getFreePhysicalMemorySize()) / 1024.0 / 1024));
        return new TbMemoryRecord(Constant.getDateString(new Date()),
                Constant.timeToFormatString(new Date()),
                totalMemory, usedMemory, freeMemory);
    }

    //获取CPU相关信息


    public static void main(String[] args) {
        getCpuInfo();
    }


    public static Object getCpuInfo() {
        //判断系统类型
        if (getOsType().startsWith("Windows")) {//windows系统
            log.info("---------------获取cpu信息--------------");
            SystemInfo systemInfo = new SystemInfo();
            OperatingSystemMXBean operatingSystemMXBean =
                    (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            double x = operatingSystemMXBean.getFreeSwapSpaceSize();
            System.out.println(x);
            ThreadGroup threadGroup;
            threadGroup = Thread.currentThread().getThreadGroup();
            System.out.println("---------------");

            return null;
        } else {
            log.error(getOsType());
            return null;
        }
    }

    /**
     * 获取操作系统类型
     * @return
     */
    public static String getOsType() {
        Properties properties = System.getProperties();
        String osName = properties.getProperty("os.name");
        return osName;
    }

}
