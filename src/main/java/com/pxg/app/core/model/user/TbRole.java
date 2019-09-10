package com.pxg.app.core.model.user;

import java.io.Serializable;

public class TbRole implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column appuser.tb_role.rid
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    private Integer rid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column appuser.tb_role.role_name
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    private String roleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column appuser.tb_role.note
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    private String note;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table appuser.tb_role
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column appuser.tb_role.rid
     * @return the value of appuser.tb_role.rid
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column appuser.tb_role.rid
     * @param rid the value for appuser.tb_role.rid
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column appuser.tb_role.role_name
     * @return the value of appuser.tb_role.role_name
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column appuser.tb_role.role_name
     * @param roleName the value for appuser.tb_role.role_name
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column appuser.tb_role.note
     * @return the value of appuser.tb_role.note
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column appuser.tb_role.note
     * @param note the value for appuser.tb_role.note
     * @mbg.generated Fri Aug 30 23:16:37 CST 2019
     */
    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "TbRole{" +
                "rid=" + rid +
                ", roleName='" + roleName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}