package com.pxg.app.web.controller;

import com.pxg.app.core.model.mail.SimpleMailModel;
import com.pxg.app.util.Service.mail.KettleMailServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import static com.pxg.app.util.constant.Constant.InterfaceReturnInformation;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/7/12
 * @Time 22:45
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
@RestController
@RequestMapping("/api/kettle")
@Api(description = "执行业务接口")
public class WebRestApi {

    @Autowired
    private KettleMailServer kettleMailServer;

    @ApiOperation("发送普通邮件")
    @PostMapping("/send/mail/simple")
    public Map<Object, Object> sendSimpleTextMail(
            @RequestBody SimpleMailModel simpleMailModel,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            request.setCharacterEncoding("UTF-8");
            response.addHeader("Author", "aaaaaaaadsda");
            kettleMailServer.sendSimpleTextMail(simpleMailModel);
            return InterfaceReturnInformation(200, null, "成功");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return InterfaceReturnInformation(500, e.getMessage(), "失败");
        }
    }


}
