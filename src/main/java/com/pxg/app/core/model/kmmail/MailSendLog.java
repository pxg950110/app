package com.pxg.app.core.model.kmmail;

import java.util.Date;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/8/17
 * @Time 21:07
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
public class MailSendLog {
    private int mailId;// mail_id serial primary key ,
    private String mailUUID;// mail_uuid varchar(50),----记录消息体的id
    private String mailContent;//mail_content text,----记录消息体具体发送的内容
    private Date mailTIme;//mail_time  timestamp,--记录时间信息
    private String mailLogInfo;//mail_log_info text --返回具体消息信息

    public MailSendLog() {
    }

    public MailSendLog(String mailUUID, String mailContent, String mailLogInfo) {
        this.mailUUID = mailUUID;
        this.mailContent = mailContent;
        this.mailLogInfo = mailLogInfo;
    }

    @Override
    public String toString() {
        return "MailSendLog{" +
                "mailId=" + mailId +
                ", mailUUID='" + mailUUID + '\'' +
                ", mailContent='" + mailContent + '\'' +
                ", mailTIme=" + mailTIme +
                ", mailLogInfo='" + mailLogInfo + '\'' +
                '}';
    }

    public int getMailId() {
        return mailId;
    }

    public void setMailId(int mailId) {
        this.mailId = mailId;
    }

    public String getMailUUID() {
        return mailUUID;
    }

    public void setMailUUID(String mailUUID) {
        this.mailUUID = mailUUID;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public Date getMailTIme() {
        return mailTIme;
    }

    public void setMailTIme(Date mailTIme) {
        this.mailTIme = mailTIme;
    }

    public String getMailLogInfo() {
        return mailLogInfo;
    }

    public void setMailLogInfo(String mailLogInfo) {
        this.mailLogInfo = mailLogInfo;
    }
}
