package com.pxg.app.core.model.mail;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/8/17
 * @Time 20:50
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
public class SimpleMailModel {
    private String mailForm;
    private String mailTo;
    private String mailSubject;
    private String mailContent;

    public String getMailForm() {
        return mailForm;
    }

    public void setMailForm(String mailForm) {
        this.mailForm = mailForm;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    @Override
    public String toString() {
        return "SimpleMailModel{" +
                "mailForm='" + mailForm + '\'' +
                ", mailTo='" + mailTo + '\'' +
                ", mailSubject='" + mailSubject + '\'' +
                ", mailContent='" + mailContent + '\'' +
                '}';
    }
}
