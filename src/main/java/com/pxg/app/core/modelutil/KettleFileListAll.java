package com.pxg.app.core.modelutil;

import com.pxg.app.core.model.dict.AppDict;
import com.pxg.app.core.model.km.KettleFileList;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/9/2
 * @Time 22:01
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
public class KettleFileListAll {
    private KettleFileList kettleFileList;
    private AppDict appDict;

    public KettleFileList getKettleFileList() {
        return kettleFileList;
    }

    public void setKettleFileList(KettleFileList kettleFileList) {
        this.kettleFileList = kettleFileList;
    }

    public AppDict getAppDict() {
        return appDict;
    }

    public void setAppDict(AppDict appDict) {
        this.appDict = appDict;
    }
}
