package com.pxg.app.core.model.dict;

import java.io.Serializable;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/8/25
 * @Time 20:58
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description 数据字典
 */
public class AppDict implements Serializable {
    /**
     * This field corresponds to the database column dict.app_dict.id
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    private Integer id;

    /**
     * This field corresponds to the database column dict.app_dict.code
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    private String code;

    /**
     * This field corresponds to the database column dict.app_dict.name
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    private String name;

    /**
     * This field corresponds to the database column dict.app_dict.classtype
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    private String classtype;

    /**
     * This field corresponds to the database column dict.app_dict.classtype_name
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    private String classtypeName;

    /**
     * This field corresponds to the database column dict.app_dict.status
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    private Integer status;

    public AppDict() {
    }

    public AppDict(String classtype, Integer status) {
        this.classtype = classtype;
        this.status = status;
    }

    public AppDict(Integer id, String classtype, Integer status) {
        this.id = id;
        this.classtype = classtype;
        this.status = status;
    }

    /**
     * This field corresponds to the database table dict.app_dict
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method returns the value of the database column dict.app_dict.id
     * @return the value of dict.app_dict.id
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method sets the value of the database column dict.app_dict.id
     * @param id the value for dict.app_dict.id
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method returns the value of the database column dict.app_dict.code
     * @return the value of dict.app_dict.code
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    public String getCode() {
        return code;
    }

    /**
     * This method sets the value of the database column dict.app_dict.code
     * @param code the value for dict.app_dict.code
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * This method returns the value of the database column dict.app_dict.name
     * @return the value of dict.app_dict.name
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the value of the database column dict.app_dict.name
     * @param name the value for dict.app_dict.name
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method returns the value of the database column dict.app_dict.classtype
     * @return the value of dict.app_dict.classtype
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    public String getClasstype() {
        return classtype;
    }

    /**
     * This method sets the value of the database column dict.app_dict.classtype
     * @param classtype the value for dict.app_dict.classtype
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    public void setClasstype(String classtype) {
        this.classtype = classtype;
    }

    /**
     * This method returns the value of the database column dict.app_dict.classtype_name
     * @return the value of dict.app_dict.classtype_name
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    public String getClasstypeName() {
        return classtypeName;
    }

    /**
     * This method sets the value of the database column dict.app_dict.classtype_name
     * @param classtypeName the value for dict.app_dict.classtype_name
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    public void setClasstypeName(String classtypeName) {
        this.classtypeName = classtypeName;
    }

    /**
     * This method returns the value of the database column dict.app_dict.status
     * @return the value of dict.app_dict.status
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method sets the value of the database column dict.app_dict.status
     * @param status the value for dict.app_dict.status
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "AppDict{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", classtype='" + classtype + '\'' +
                ", classtypeName='" + classtypeName + '\'' +
                ", status=" + status +
                '}';
    }


    /**
     * @return 返回JSON字符串
     */
    public String toJsonString() {
        //
        return null;
    }


}