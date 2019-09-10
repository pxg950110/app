package com.pxg.app.core.config.shiro;

import com.pxg.app.core.model.user.TbUser;
import com.pxg.app.core.service.TbUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/7/13
 * @Time 1:08
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
@Component
public class UserRealm extends AuthorizingRealm {

    private TbUserService tbUserService;


    /**
     * 用户授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token)

            throws AuthenticationException {

//        username
        String userName = (String) token.getPrincipal();
        //
        TbUser tbUser = tbUserService.getTbUserByUserName(userName);

        if (tbUser != null) {
            AuthenticationInfo authenticationInfo =
                    new SimpleAuthenticationInfo(tbUser.getUserName(), tbUser.getUserPassword(), getName());
            return authenticationInfo;
        } else {
            return null;
        }
    }
}
