package com.pxg.app.core.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/7/13
 * @Time 15:40
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerUiConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//
                .groupName("app")
                .select()//
                .apis(RequestHandlerSelectors.basePackage("com.pxg.app.web.controller"))
                .paths(PathSelectors.any())//
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("App")//
                .version("V-1.0")//
                .description("demo")//
                .build();
    }


}
