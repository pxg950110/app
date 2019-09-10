package com.pxg.app.core.model.task;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class TaskQuartzSet implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column km_task.task_quartz_set.id
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    private Integer id;

    @ApiModelProperty("任务id")
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column km_task.task_quartz_set.job_id
     *
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    private Integer jobId;

    @ApiModelProperty("任务类型")
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column km_task.task_quartz_set.job_type_id
     *
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    private Integer jobTypeId;

    @ApiModelProperty("计划开始时间")
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column km_task.task_quartz_set.start_plan_time
     *
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    private Date startPlanTime;

    @ApiModelProperty("下次计划执行时间")
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column km_task.task_quartz_set.next_plan_time
     *
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    private Date nextPlanTime;

    @ApiModelProperty("执行状态 0 停止  1 执行 2 等待")
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column km_task.task_quartz_set.status
     *
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    private Integer status;

    @ApiModelProperty("计划执行次数")
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column km_task.task_quartz_set.plan_times
     *
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */

    private Integer planTimes;

    //策略
    private Integer cronId;

    //策略设置
    private String cronText;
    @ApiModelProperty("创建时间")
    /**
     *
     * This field corresponds to the database column km_task.task_quartz_set.create_time
     *
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    private Date createTime;

    public Integer getCronId() {
        return cronId;
    }

    public void setCronId(Integer cronId) {
        this.cronId = cronId;
    }

    public String getCronText() {
        return cronText;
    }

    public void setCronText(String cronText) {
        this.cronText = cronText;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table km_task.task_quartz_set
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column km_task.task_quartz_set.id
     * @return the value of km_task.task_quartz_set.id
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column km_task.task_quartz_set.id
     * @param id the value for km_task.task_quartz_set.id
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column km_task.task_quartz_set.job_id
     * @return the value of km_task.task_quartz_set.job_id
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    public Integer getJobId() {
        return jobId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column km_task.task_quartz_set.job_id
     * @param jobId the value for km_task.task_quartz_set.job_id
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column km_task.task_quartz_set.job_type_id
     * @return the value of km_task.task_quartz_set.job_type_id
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    public Integer getJobTypeId() {
        return jobTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column km_task.task_quartz_set.job_type_id
     * @param jobTypeId the value for km_task.task_quartz_set.job_type_id
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    public void setJobTypeId(Integer jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column km_task.task_quartz_set.start_plan_time
     * @return the value of km_task.task_quartz_set.start_plan_time
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    public Date getStartPlanTime() {
        return startPlanTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column km_task.task_quartz_set.start_plan_time
     * @param startPlanTime the value for km_task.task_quartz_set.start_plan_time
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    public void setStartPlanTime(Date startPlanTime) {
        this.startPlanTime = startPlanTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column km_task.task_quartz_set.next_plan_time
     * @return the value of km_task.task_quartz_set.next_plan_time
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    public Date getNextPlanTime() {
        return nextPlanTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column km_task.task_quartz_set.next_plan_time
     * @param nextPlanTime the value for km_task.task_quartz_set.next_plan_time
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    public void setNextPlanTime(Date nextPlanTime) {
        this.nextPlanTime = nextPlanTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column km_task.task_quartz_set.status
     * @return the value of km_task.task_quartz_set.status
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column km_task.task_quartz_set.status
     * @param status the value for km_task.task_quartz_set.status
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column km_task.task_quartz_set.plan_times
     * @return the value of km_task.task_quartz_set.plan_times
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    public Integer getPlanTimes() {
        return planTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column km_task.task_quartz_set.plan_times
     * @param planTimes the value for km_task.task_quartz_set.plan_times
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    public void setPlanTimes(Integer planTimes) {
        this.planTimes = planTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column km_task.task_quartz_set.create_time
     * @return the value of km_task.task_quartz_set.create_time
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column km_task.task_quartz_set.create_time
     * @param createTime the value for km_task.task_quartz_set.create_time
     * @mbg.generated Fri Sep 06 18:17:47 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}