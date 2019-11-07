package com.pxg.app.kettle.model;

import java.util.Date;

/**
 * <p>
 * 2019/10/23  22:30
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * </p>
 * <p>
 * @author pxg
 * @emil pxg950110@163.com
 * @Date 2019/10/23
 * @Version 1.0.0
 * @description </p>
 */
public class KettleRepositoryDataInfo {


    //objectid
    private String objectId;

    //名称
    private String objectName;
    //描述
    private String objectDescription;

    //是否删除
    private boolean isDeleted;
    //类别

    private String ObjectType;

    //RepositoryDirectoryInterface  目录
    private String directoryObjectId;

    //修改人
    private String modifedUser;
    //修改时间
    private Date modifedDate;

    public KettleRepositoryDataInfo() {
    }

    public KettleRepositoryDataInfo(String objectId,
                                    String objectName,
                                    String objectDescription,
                                    boolean isDeleted,
                                    String objectType,
                                    String directoryObjectId,
                                    String modifedUser,
                                    Date modifedDate) {
        this.objectId = objectId;
        this.objectName = objectName;
        this.objectDescription = objectDescription;
        this.isDeleted = isDeleted;
        ObjectType = objectType;
        this.directoryObjectId = directoryObjectId;
        this.modifedUser = modifedUser;
        this.modifedDate = modifedDate;
    }

    @Override
    public String toString() {
        return "KettleRepositoryDataInfo{" +
                "objectId='" + objectId + '\'' +
                ", objectName='" + objectName + '\'' +
                ", objectDescription='" + objectDescription + '\'' +
                ", isDeleted=" + isDeleted +
                ", ObjectType='" + ObjectType + '\'' +
                ", directoryObjectId='" + directoryObjectId + '\'' +
                ", modifedUser='" + modifedUser + '\'' +
                ", modifedDate='" + modifedDate + '\'' +
                '}';
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectDescription() {
        return objectDescription;
    }

    public void setObjectDescription(String objectDescription) {
        this.objectDescription = objectDescription;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getObjectType() {
        return ObjectType;
    }

    public void setObjectType(String objectType) {
        ObjectType = objectType;
    }

    public String getDirectoryObjectId() {
        return directoryObjectId;
    }

    public void setDirectoryObjectId(String directoryObjectId) {
        this.directoryObjectId = directoryObjectId;
    }

    public String getModifedUser() {
        return modifedUser;
    }

    public void setModifedUser(String modifedUser) {
        this.modifedUser = modifedUser;
    }

    public Date getModifedDate() {
        return modifedDate;
    }

    public void setModifedDate(Date modifedDate) {
        this.modifedDate = modifedDate;
    }
}
