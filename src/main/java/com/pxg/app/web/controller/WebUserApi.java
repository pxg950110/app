package com.pxg.app.web.controller;

import com.pxg.app.core.model.user.TbUser;
import com.pxg.app.core.modelutil.RegistUserModel;
import com.pxg.app.core.service.TbUserService;
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
 * @Date 2019/8/25
 * @Time 14:21
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
@RestController
@Api(description = "用户相关信息")
@RequestMapping("/api/user")
public class WebUserApi {

    @Autowired
    private TbUserService tbUserService;

    //通过userName Password生成token
    @GetMapping("/welcome")
    public String getToken() {
        return "aaa";
    }

    @GetMapping("login")
    public String Login() {
        return "aaaa";
    }

    //获取用户权限

    /**
     * 获取所有权限列表
     * @return
     */
    @GetMapping("/role/list")
    @ApiOperation("用户权限列表")
    public Map<Object, Object> getTbRole() {
        return tbUserService.tbRole();
    }

    @PostMapping("/regist")
    @ApiOperation("用户注册接口")
    public Map<Object, Object> registUser(@RequestBody RegistUserModel registUserModel) {
        return tbUserService.registUser(registUserModel);
    }


    /**
     * 获取所有用户列表
     * @return
     */
    @ApiOperation("获取用户伴随权限")
    @GetMapping("/list/with/role")
    public Map<Object, Object> getUserListWithRole() {
        return tbUserService.getUserListWithRole(new TbUser());
    }

}
