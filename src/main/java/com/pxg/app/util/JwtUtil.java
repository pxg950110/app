package com.pxg.app.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.pxg.app.core.model.user.TbUser;
import com.pxg.app.util.constant.Constant;

import java.util.Date;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/8/1
 * @Time 20:24
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
public class JwtUtil {

    /**
     * 生成token
     * @param tbUser
     * @return token
     */
    public static String sign(TbUser tbUser) {
        try {
            Date date = new Date(System.currentTimeMillis() + Constant.EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(tbUser.getUserPassword());
            //生成token 附带username信息
            String token = JWT.create()//
                    .withClaim("username", tbUser.getUserName())//userName
                    .withClaim("userId", tbUser.getId())//userID
                    .withClaim("uuid", UUIDUtil.getUUID())//UUID
                    //设置过期时间
                    .withExpiresAt(date)
                    //设置签名密码
                    .sign(algorithm);
            return token;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过token 获取userName
     * @param token
     * @return userName
     */
    public static String getUserName(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            //返回userName
            return decodedJWT.getClaim("username").asString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过用户名和密码进行token验证
     * @param token
     * @param userName
     * @param password
     * @return
     */
    public static Boolean verify(String token, String userName, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", userName)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }

    }
}
