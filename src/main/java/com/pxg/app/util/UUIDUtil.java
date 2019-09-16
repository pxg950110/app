package com.pxg.app.util;

import java.util.UUID;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/8/1
 * @Time 20:16
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description 生成UUID
 */
public class UUIDUtil {
    /**
     * @return 返回uuid
     */
    public static String getUUID() {
        String uuidStr = UUID.randomUUID().toString().replace("-", "");
        return uuidStr;
    }
}
