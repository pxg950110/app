package com.pxg.app.core.service;

import com.pxg.app.core.mapper.appmapper.AppDictMapper;
import com.pxg.app.core.mapper.appmapper.PermissionRoleMapper;
import com.pxg.app.core.mapper.appmapper.TbRoleMapper;
import com.pxg.app.core.mapper.appmapper.TbUserMapper;
import com.pxg.app.core.model.user.PermissionRole;
import com.pxg.app.core.model.user.TbRole;
import com.pxg.app.core.model.user.TbUser;
import com.pxg.app.core.modelutil.RegistUserModel;
import com.pxg.app.util.JsonUtils;
import com.pxg.app.util.rabbit.RabbitProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.pxg.app.util.constant.Constant.*;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/7/12
 * @Time 9:41
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
@Service
public class TbUserService {

    private static Logger log = LoggerFactory.getLogger(TbUserService.class);
    @Autowired
    private AppDictMapper appDictMapper;

    @Autowired
    private PermissionRoleMapper permissionRoleMapper;

    @Autowired
    private TbRoleMapper tbRoleMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private RabbitProducer rabbitProducer;

    public TbUser getTbUserByUserName(String userName) {
        TbUser tbUser = new TbUser();
        tbUser.setUserName(userName);
        tbUser.setIsDeleted(false);
        List<TbUser> tbUsers = tbUserMapper.selectSelective(tbUser);
        return tbUsers.get(0);
    }

    //获取权限列表
    public Map<Object, Object> tbRole() {
        //返回所有role
        return InterfaceReturnInformation(SUCCESS_CODE,//
                tbRoleMapper.selectSelective(new TbRole()),
                "查询成功");
    }

    /**
     * 用户注册接口
     * @return
     */
    public Map<Object, Object> registUser(RegistUserModel registUserModel) {
        log.info(JsonUtils.ObjectToJSONString(registUserModel));
        /**
         * 用户注册页面
         */
        //判断用户是否存在
        TbUser tbUser = new TbUser(registUserModel.getTbUser().getUserName());
        //通过用户名查询是否存在
        long count = tbUserMapper.count(tbUser);
        //存在返回数据
        if (count > 0 || tbUser.getUserName().equals("") || tbUser.getUserName() == null) {
            return InterfaceReturnInformation(EXIST_CODE, null, "数据已存在");
        }
        //不存在插入注册数据
        tbUser = registUserModel.getTbUser();
        if (tbUser.getIsDeleted() == null) {
            tbUser.setIsDeleted(false);
        }
        if (tbUser.getIsLocked() == null) {
            tbUser.setIsLocked(false);
        }
        tbUser.setCreateTime(new Date());
        try {
            tbUserMapper.insert(tbUser);
            //插入用户权限对应表
            PermissionRole permissionRole
                    = new PermissionRole(tbUser.getId(), registUserModel.getTbRole().getRid());
            permissionRoleMapper.insert(permissionRole);
            return InterfaceReturnInformation(SUCCESS_CODE, null, "注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            //失败写入rabbit
            rabbitProducer.stringSend2(dateToFormatString(new Date()) + "   :" + e.getMessage());
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), "查询失败");
        }

    }


    //用户登陆验证

    //  获取  用户权限


    //获取用户列表伴随权限
    public Map<Object, Object> getUserListWithRole(TbUser tbUser) {
        try {

            return InterfaceReturnInformation(SUCCESS_CODE, tbUserMapper.selectUserWithRoleList(), SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);

        }
    }

}
