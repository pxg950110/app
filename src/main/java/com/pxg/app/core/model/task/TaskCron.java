package com.pxg.app.core.model.task;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class TaskCron implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column task.task_cron.id
     * @mbg.generated Sat Sep 07 10:56:23 CST 2019
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column task.task_cron.cron
     * @mbg.generated Sat Sep 07 10:56:23 CST 2019
     */
    @ApiModelProperty("策略")
    private String cron;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column task.task_cron.status
     * @mbg.generated Sat Sep 07 10:56:23 CST 2019
     */
    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("描述")
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column task.task_cron.description
     *
     * @mbg.generated Sat Sep 07 10:56:23 CST 2019
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table task.task_cron
     * @mbg.generated Sat Sep 07 10:56:23 CST 2019
     */
    private static final long serialVersionUID = 1L;

    public TaskCron() {
    }

    public TaskCron(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column task.task_cron.id
     * @return the value of task.task_cron.id
     * @mbg.generated Sat Sep 07 10:56:23 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column task.task_cron.id
     * @param id the value for task.task_cron.id
     * @mbg.generated Sat Sep 07 10:56:23 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column task.task_cron.cron
     * @return the value of task.task_cron.cron
     * @mbg.generated Sat Sep 07 10:56:23 CST 2019
     */
    public String getcron() {
        return cron;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column task.task_cron.cron
     * @param cron the value for task.task_cron.cron
     * @mbg.generated Sat Sep 07 10:56:23 CST 2019
     */
    public void setcron(String cron) {
        this.cron = cron;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column task.task_cron.status
     * @return the value of task.task_cron.status
     * @mbg.generated Sat Sep 07 10:56:23 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column task.task_cron.status
     * @param status the value for task.task_cron.status
     * @mbg.generated Sat Sep 07 10:56:23 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column task.task_cron.description
     * @return the value of task.task_cron.description
     * @mbg.generated Sat Sep 07 10:56:23 CST 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column task.task_cron.description
     * @param description the value for task.task_cron.description
     * @mbg.generated Sat Sep 07 10:56:23 CST 2019
     */
    public void setDescription(String description) {
        this.description = description;
    }
}