package com.pxg.app.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/8/17
 * @Time 20:55
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description object 与JSON之间相互转换
 */
public class JsonUtils {

    private static Logger log = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * java实体类生成json的String串
     * @param object java实体类
     * @return 返回JSON的string字符串
     */
    public static String ObjectToJSONString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(object);
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
    }


}
