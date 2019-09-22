package com.pxg.app.util.xfyy;

/**
 * <p>
 * 2019/9/18  20:56
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * </p>
 * <p>
 * @author pxg
 * @init
 * @Date 2019/9/18
 * @Version 1.0.0
 * @description </p>
 */
public class XFYYTest {
    public static void main(String[] args) {
        String user = null;
        String password = null;
        //科大讯飞的appid
        String params = "appid=5d809307";
        int a;
        //调用接口的方法传参
        a = VoiceLib.instance.MSPLogin(user, password, params);
        System.out.println("a=" + a);
    }
}
