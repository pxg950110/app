package com.pxg.app.web.controller;

import com.pxg.app.core.service.AppDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/9/4
 * @Time 22:57
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
@RestController
@RequestMapping("/api/dict")
@Api(description = "字典数据")
public class AppDictApi {
    @Autowired
    private AppDictService appDictService;

    @PostMapping("/class/all")
    @ApiOperation("通过分类获取字典")
    public Map<Object, Object> getAppDictByClassType(@RequestParam String classType) {
        return appDictService.getAppDictByClassType(classType);
    }

    @GetMapping("/class/dict/name")
    @ApiOperation("获取所有类别")
    public Map<Object, Object> getAllDictType() {
        return appDictService.getAllDictType();
    }
}
