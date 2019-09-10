package com.pxg.app.core.modelutil;

import com.pxg.app.core.model.user.TbRole;
import com.pxg.app.core.model.user.TbUser;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/8/31
 * @Time 17:25
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
public class RegistUserModel {
    private TbUser tbUser;
    private TbRole tbRole;

    public TbUser getTbUser() {
        return tbUser;
    }

    public void setTbUser(TbUser tbUser) {
        this.tbUser = tbUser;
    }

    public TbRole getTbRole() {
        return tbRole;
    }

    public void setTbRole(TbRole tbRole) {
        this.tbRole = tbRole;
    }

    @Override
    public String toString() {
        return "RegistUserModel{" +
                "tbUser=" + tbUser +
                ", tbRole=" + tbRole +
                '}';
    }
}
