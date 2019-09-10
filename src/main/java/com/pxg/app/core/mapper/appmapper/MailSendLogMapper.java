package com.pxg.app.core.mapper.appmapper;

import com.pxg.app.core.model.kmmail.MailSendLog;
import org.apache.ibatis.annotations.Insert;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/8/18
 * @Time 7:25
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
public interface MailSendLogMapper {

    /**
     * create table km_mail.mail_send_log(
     * mail_id serial primary key ,
     * mail_uuid varchar(50),----记录消息体的id
     * mail_content text,----记录消息体具体发送的内容
     * mail_time  timestamp,--记录时间信息
     * mail_log_info text --返回具体消息信息
     * );
     */
    //插入日志
    @Insert("insert into km_mail.mail_send_log(mail_uuid,mail_content,mail_time,mail_log_info)" +
            " values(#{mailUUID},#{mailContent},now(),#{mailLogInfo})")
    public void insertMailSendLog(MailSendLog mailSendLog);
}
