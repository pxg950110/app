package com.pxg.app.core.model.user;

import java.io.Serializable;

public class PermissionRole implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column appuser.permission_role.user_id
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column appuser.permission_role.role_id
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    private Integer roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table appuser.permission_role
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    private static final long serialVersionUID = 1L;

    public PermissionRole() {
    }

    public PermissionRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column appuser.permission_role.user_id
     * @return the value of appuser.permission_role.user_id
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column appuser.permission_role.user_id
     * @param userId the value for appuser.permission_role.user_id
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column appuser.permission_role.role_id
     * @return the value of appuser.permission_role.role_id
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column appuser.permission_role.role_id
     * @param roleId the value for appuser.permission_role.role_id
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "PermissionRole{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}