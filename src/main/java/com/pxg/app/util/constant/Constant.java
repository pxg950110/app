package com.pxg.app.util.constant;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/7/12
 * @Time 22:51
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description 定义公共常量
 */
public class Constant {
    /**
     * 公用常量
     */
    /**
     * 定义code
     * 200  返回成功 信息
     * 300  返回数据已存在
     * 500 返回错误信息
     * 401 返回警告信息
     */
    public final static Integer SUCCESS_CODE = 200;
    public final static Integer EXIST_CODE = 300;//已存在
    public final static Integer ERROR_CODE = 500;
    public final static Integer WARN_CODE = 401;
    public static final long EXPIRE_TIME = 60 * 60 * 1000;
    public static final String SUCCESS_MESSAGE = "成功";
    public static final String EXIST_MESSAGE = "数据已存在";
    public static final String WARN_MESSAGE = "警告信息";
    public static final String ERROR_MESSAGE = "失败";

    public static final String aa = "";

    //过期时间60分钟
//    public  static final longEXPIRE_TIME = 60*60*1000;


    /**
     * 接口返回信息
     */
    public static Map<Object, Object> InterfaceReturnInformation(int code,//
                                                                 Object object,
                                                                 //
                                                                 Object message) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("code", code);
        map.put("message", message);
        map.put("data", object);
        return map;
    }

    /**
     * 时间转格式化string 字符串
     * 格式：yyyy-MM-dd HH:mm:ss
     * @param date1
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String dateToFormatString(Date date1) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date1);
    }

    /**
     * 返回时间格式 yyyy-MM-dd
     * @param date
     * @return yyyy-MM-dd
     */
    public static String getDateString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    
}
