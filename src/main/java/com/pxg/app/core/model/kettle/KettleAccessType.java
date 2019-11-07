package com.pxg.app.core.model.kettle;

import java.io.Serializable;

public class KettleAccessType implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kettle.kettle_access_type.id
     * @mbg.generated Mon Oct 14 00:00:42 GMT+08:00 2019
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kettle.kettle_access_type.code
     * @mbg.generated Mon Oct 14 00:00:42 GMT+08:00 2019
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column kettle.kettle_access_type.description
     * @mbg.generated Mon Oct 14 00:00:42 GMT+08:00 2019
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table kettle.kettle_access_type
     * @mbg.generated Mon Oct 14 00:00:42 GMT+08:00 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kettle.kettle_access_type.id
     * @return the value of kettle.kettle_access_type.id
     * @mbg.generated Mon Oct 14 00:00:42 GMT+08:00 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kettle.kettle_access_type.id
     * @param id the value for kettle.kettle_access_type.id
     * @mbg.generated Mon Oct 14 00:00:42 GMT+08:00 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kettle.kettle_access_type.code
     * @return the value of kettle.kettle_access_type.code
     * @mbg.generated Mon Oct 14 00:00:42 GMT+08:00 2019
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kettle.kettle_access_type.code
     * @param code the value for kettle.kettle_access_type.code
     * @mbg.generated Mon Oct 14 00:00:42 GMT+08:00 2019
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column kettle.kettle_access_type.description
     * @return the value of kettle.kettle_access_type.description
     * @mbg.generated Mon Oct 14 00:00:42 GMT+08:00 2019
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column kettle.kettle_access_type.description
     * @param description the value for kettle.kettle_access_type.description
     * @mbg.generated Mon Oct 14 00:00:42 GMT+08:00 2019
     */
    public void setDescription(String description) {
        this.description = description;
    }
}