package com.pxg.app.core.service;

import com.pxg.app.core.mapper.appmapper.MailInfoMapper;
import com.pxg.app.core.model.kmmail.MailInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.pxg.app.util.constant.Constant.*;

/**
 * <p>
 * 2019/10/18  16:43
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * </p>
 * <p>
 * @author pxg
 * @emil pxg950110@163.com
 * @Date 2019/10/18
 * @Version 1.0.0
 * @description </p>
 */
@Service
public class MailServer {
    private static Logger log = LoggerFactory.getLogger(MailServer.class);
    @Autowired
    private MailInfoMapper mailInfoMapper;

    /**
     * 增加邮箱
     */
    public Map<Object, Object> addMailInfo(MailInfo mailInfo) {
        try {
            //查询邮箱是否存在 只查询有效的邮箱
            List<MailInfo> mailInfos = mailInfoMapper.selectBySelective(new MailInfo(mailInfo.getMailUrl(), 1));
            if (mailInfos.size() > 0) {
                return InterfaceReturnInformation(EXIST_CODE, "邮箱已经存在", EXIST_MESSAGE);
            }
            mailInfo.setStatus(1);
            mailInfo.setCreateTime(new Date());
            mailInfoMapper.insert(mailInfo);
            return InterfaceReturnInformation(SUCCESS_CODE, null, SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }


}
