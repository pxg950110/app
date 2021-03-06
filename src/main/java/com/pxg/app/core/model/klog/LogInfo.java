package com.pxg.app.core.model.klog;

import java.util.Date;

/**
 * <p>
 * 2019/11/1  14:39
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * </p>
 * <p>
 * @author pxg
 * @emil pxg950110@163.com
 * @Date 2019/11/1
 * @Version 1.0.0
 * @description <desc>
 * 执行任务后，日志存储表
 * <p>
 * create table applog.log_info(
 * klog_id serial primary key,
 * klog_uuid varchar(36),
 * klog_job_id varchar(50),
 * klog_repository_id int ,--资源库id
 * klog_log_detail text ,--
 * klog_log_status int ,----运行日志状态 1 成功 2失败  3其他
 * klog_log_extend1 text ,--日志扩展 ---需要扩展存json字段
 * klog_start_time timestamp,
 * klog_end_time timestamp,
 * klog_create_time timestamp
 * )
 * </desc>
 * </p>
 */
public class LogInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.log_info.klog_id
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    private Integer klogId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.log_info.klog_uuid
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    private String klogUuid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.log_info.klog_job_id
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    private String klogJobId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.log_info.klog_repository_id
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    private Integer klogRepositoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.log_info.klog_log_detail
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    private String klogLogDetail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.log_info.klog_log_status
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    private Integer klogLogStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.log_info.klog_log_extend1
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    private String klogLogExtend1;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.log_info.klog_start_time
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    private Date klogStartTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.log_info.klog_end_time
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    private Date klogEndTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.log_info.klog_create_time
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    private Date klogCreateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table applog.log_info
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    private static final long serialVersionUID = 1L;

    public LogInfo() {
    }

    public LogInfo(String klogUuid, String klogJobId, Integer klogRepositoryId,
                   String klogLogDetail, Integer klogLogStatus, String klogLogExtend1,
                   Date klogStartTime, Date klogEndTime, Date klogCreateTime) {
        this.klogUuid = klogUuid;
        this.klogJobId = klogJobId;
        this.klogRepositoryId = klogRepositoryId;
        this.klogLogDetail = klogLogDetail;
        this.klogLogStatus = klogLogStatus;
        this.klogLogExtend1 = klogLogExtend1;
        this.klogStartTime = klogStartTime;
        this.klogEndTime = klogEndTime;
        this.klogCreateTime = klogCreateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.log_info.klog_id
     * @return the value of applog.log_info.klog_id
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public Integer getKlogId() {
        return klogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.log_info.klog_id
     * @param klogId the value for applog.log_info.klog_id
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public void setKlogId(Integer klogId) {
        this.klogId = klogId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.log_info.klog_uuid
     * @return the value of applog.log_info.klog_uuid
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public String getKlogUuid() {
        return klogUuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.log_info.klog_uuid
     * @param klogUuid the value for applog.log_info.klog_uuid
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public void setKlogUuid(String klogUuid) {
        this.klogUuid = klogUuid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.log_info.klog_job_id
     * @return the value of applog.log_info.klog_job_id
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public String getKlogJobId() {
        return klogJobId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.log_info.klog_job_id
     * @param klogJobId the value for applog.log_info.klog_job_id
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public void setKlogJobId(String klogJobId) {
        this.klogJobId = klogJobId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.log_info.klog_repository_id
     * @return the value of applog.log_info.klog_repository_id
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public Integer getKlogRepositoryId() {
        return klogRepositoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.log_info.klog_repository_id
     * @param klogRepositoryId the value for applog.log_info.klog_repository_id
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public void setKlogRepositoryId(Integer klogRepositoryId) {
        this.klogRepositoryId = klogRepositoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.log_info.klog_log_detail
     * @return the value of applog.log_info.klog_log_detail
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public String getKlogLogDetail() {
        return klogLogDetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.log_info.klog_log_detail
     * @param klogLogDetail the value for applog.log_info.klog_log_detail
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public void setKlogLogDetail(String klogLogDetail) {
        this.klogLogDetail = klogLogDetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.log_info.klog_log_status
     * @return the value of applog.log_info.klog_log_status
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public Integer getKlogLogStatus() {
        return klogLogStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.log_info.klog_log_status
     * @param klogLogStatus the value for applog.log_info.klog_log_status
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public void setKlogLogStatus(Integer klogLogStatus) {
        this.klogLogStatus = klogLogStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.log_info.klog_log_extend1
     * @return the value of applog.log_info.klog_log_extend1
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public String getKlogLogExtend1() {
        return klogLogExtend1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.log_info.klog_log_extend1
     * @param klogLogExtend1 the value for applog.log_info.klog_log_extend1
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public void setKlogLogExtend1(String klogLogExtend1) {
        this.klogLogExtend1 = klogLogExtend1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.log_info.klog_start_time
     * @return the value of applog.log_info.klog_start_time
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public Date getKlogStartTime() {
        return klogStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.log_info.klog_start_time
     * @param klogStartTime the value for applog.log_info.klog_start_time
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public void setKlogStartTime(Date klogStartTime) {
        this.klogStartTime = klogStartTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.log_info.klog_end_time
     * @return the value of applog.log_info.klog_end_time
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public Date getKlogEndTime() {
        return klogEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.log_info.klog_end_time
     * @param klogEndTime the value for applog.log_info.klog_end_time
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public void setKlogEndTime(Date klogEndTime) {
        this.klogEndTime = klogEndTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.log_info.klog_create_time
     * @return the value of applog.log_info.klog_create_time
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public Date getKlogCreateTime() {
        return klogCreateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.log_info.klog_create_time
     * @param klogCreateTime the value for applog.log_info.klog_create_time
     * @mbg.generated Fri Nov 01 14:44:04 GMT+08:00 2019
     */
    public void setKlogCreateTime(Date klogCreateTime) {
        this.klogCreateTime = klogCreateTime;
    }
}
