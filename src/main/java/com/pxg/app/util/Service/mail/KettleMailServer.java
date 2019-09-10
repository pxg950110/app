package com.pxg.app.util.Service.mail;

import com.pxg.app.core.mapper.appmapper.MailSendLogMapper;
import com.pxg.app.core.model.kmmail.MailSendLog;
import com.pxg.app.core.model.mail.SimpleMailModel;
import com.pxg.app.util.UUIDUtil;
import com.pxg.app.util.rabbit.RabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/8/17
 * @Time 20:14
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description 调度工具中发送邮件的类
 */
@Component
public class KettleMailServer {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Autowired
    private RabbitProducer rabbitProducer;

    @Autowired
    private MailSendLogMapper mailSendLogMapper;

    /**
     * 邮件发送模块  简单邮件
     * @param simpleMailModel
     */
    public void sendSimpleTextMail(SimpleMailModel simpleMailModel) {
        //生成消息体的UUID
        String uuid = UUIDUtil.getUUID();
        simpleMailModel.setMailForm(mailFrom);
        //建立消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //设置发送方
        mainMessage.setFrom(mailFrom);
        //设置主题
        mainMessage.setSubject(simpleMailModel.getMailSubject());
        //设置内容
        mainMessage.setText(simpleMailModel.getMailContent());
        //设置接收方
        mainMessage.setTo(simpleMailModel.getMailTo());
        //
        try {
            javaMailSender.send(mainMessage);
            //成功写入邮件
            //发送到MQ
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dataString = simpleDateFormat.format(date);
            rabbitProducer.stringSend2("time:" + dataString + ";uuid:" + uuid + ";内容：" + simpleMailModel.toString() + ";结果：成功");
            MailSendLog mailSendLog = new MailSendLog(uuid, simpleMailModel.toString(), "成功");
            mailSendLogMapper.insertMailSendLog(mailSendLog);
        } catch (Exception e) {
            e.printStackTrace();
            //失败写入邮件
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
            String dataString = simpleDateFormat.format(date);
            rabbitProducer.stringSend2("time:" + dataString + ";uuid:" + uuid + ";内容：" + simpleMailModel.toString() + ";结果：" + e.getMessage());
            MailSendLog mailSendLog = new MailSendLog(uuid, simpleMailModel.toString(), e.getMessage());
            mailSendLogMapper.insertMailSendLog(mailSendLog);
        }

    }

    /**
     * 发送html邮件
     * @param simpleMailModel
     */
    public void sendHtmlMail(SimpleMailModel simpleMailModel) {
        String uuid = UUIDUtil.getUUID();
        simpleMailModel.setMailForm(mailFrom);
        //建立邮件消息体
        MailMessage mailMessage = (MailMessage) javaMailSender.createMimeMessage();
    }


}
