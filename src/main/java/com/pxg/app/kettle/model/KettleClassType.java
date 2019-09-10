package com.pxg.app.kettle.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/8/25
 * @Time 20:40
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description 任务类型
 */
public class KettleClassType {


    private int id;
    @ApiModelProperty("分类code")
    private String classCode;
    @ApiModelProperty("分类名称")
    private String className;


}
