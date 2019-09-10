package com.pxg.app.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/8/25
 * @Time 15:51
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description 解决跨域问题
 */
@Configuration
@EnableWebMvc
public class Cors extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:53529")
                .allowedMethods("GET", "POST", "OPTIONS", "DELETE", "PATCH")
                .allowCredentials(true).maxAge(3600);
//        registry.addMapping("/**")
//                .allowedOrigins("http://127.0.0.1:54500")
//                .allowedMethods("GET","POST","OPTIONS","DELETE","PATCH")
//                .allowCredentials(true).maxAge(3600);
    }
}
