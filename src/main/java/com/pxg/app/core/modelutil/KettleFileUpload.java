package com.pxg.app.core.modelutil;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/9/1
 * @Time 16:45
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
public class KettleFileUpload {
    private String fileName;
    private String description;
    private Integer appDictId;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAppDictId() {
        return appDictId;
    }

    public void setAppDictId(Integer appDictId) {
        this.appDictId = appDictId;
    }
}
