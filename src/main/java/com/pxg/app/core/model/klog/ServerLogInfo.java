package com.pxg.app.core.model.klog;

import java.io.Serializable;
import java.util.Date;

public class ServerLogInfo implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.server_log_info.log_id
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    private Integer logId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.server_log_info.log_message_id
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    private String logMessageId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.server_log_info.log_level
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    private String logLevel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.server_log_info.log_location
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    private String logLocation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.server_log_info.log_thread
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    private String logThread;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.server_log_info.log_category_name
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    private String logCategoryName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.server_log_info.log_time
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    private Date logTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.server_log_info.log_info
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    private String logInfo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.server_log_info.log_content_type
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    private String logContentType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column applog.server_log_info.create_time
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table applog.server_log_info
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    private static final long serialVersionUID = 1L;

    public ServerLogInfo() {
    }

    public ServerLogInfo(String logMessageId, String logLevel, String logLocation, String logThread, String logCategoryName, Date logTime, String logInfo, String logContentType, Date createTime) {
        this.logMessageId = logMessageId;
        this.logLevel = logLevel;
        this.logLocation = logLocation;
        this.logThread = logThread;
        this.logCategoryName = logCategoryName;
        this.logTime = logTime;
        this.logInfo = logInfo;
        this.logContentType = logContentType;
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.server_log_info.log_id
     * @return the value of applog.server_log_info.log_id
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public Integer getLogId() {
        return logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.server_log_info.log_id
     * @param logId the value for applog.server_log_info.log_id
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.server_log_info.log_message_id
     * @return the value of applog.server_log_info.log_message_id
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public String getLogMessageId() {
        return logMessageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.server_log_info.log_message_id
     * @param logMessageId the value for applog.server_log_info.log_message_id
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public void setLogMessageId(String logMessageId) {
        this.logMessageId = logMessageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.server_log_info.log_level
     * @return the value of applog.server_log_info.log_level
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public String getLogLevel() {
        return logLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.server_log_info.log_level
     * @param logLevel the value for applog.server_log_info.log_level
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.server_log_info.log_location
     * @return the value of applog.server_log_info.log_location
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public String getLogLocation() {
        return logLocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.server_log_info.log_location
     * @param logLocation the value for applog.server_log_info.log_location
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public void setLogLocation(String logLocation) {
        this.logLocation = logLocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.server_log_info.log_thread
     * @return the value of applog.server_log_info.log_thread
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public String getLogThread() {
        return logThread;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.server_log_info.log_thread
     * @param logThread the value for applog.server_log_info.log_thread
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public void setLogThread(String logThread) {
        this.logThread = logThread;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.server_log_info.log_category_name
     * @return the value of applog.server_log_info.log_category_name
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public String getLogCategoryName() {
        return logCategoryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.server_log_info.log_category_name
     * @param logCategoryName the value for applog.server_log_info.log_category_name
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public void setLogCategoryName(String logCategoryName) {
        this.logCategoryName = logCategoryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.server_log_info.log_time
     * @return the value of applog.server_log_info.log_time
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public Date getLogTime() {
        return logTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.server_log_info.log_time
     * @param logTime the value for applog.server_log_info.log_time
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.server_log_info.log_info
     * @return the value of applog.server_log_info.log_info
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public String getLogInfo() {
        return logInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.server_log_info.log_info
     * @param logInfo the value for applog.server_log_info.log_info
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.server_log_info.log_content_type
     * @return the value of applog.server_log_info.log_content_type
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public String getLogContentType() {
        return logContentType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.server_log_info.log_content_type
     * @param logContentType the value for applog.server_log_info.log_content_type
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public void setLogContentType(String logContentType) {
        this.logContentType = logContentType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column applog.server_log_info.create_time
     * @return the value of applog.server_log_info.create_time
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column applog.server_log_info.create_time
     * @param createTime the value for applog.server_log_info.create_time
     * @mbg.generated Fri Nov 08 11:12:40 GMT+08:00 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}