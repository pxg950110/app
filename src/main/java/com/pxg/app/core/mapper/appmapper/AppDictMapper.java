package com.pxg.app.core.mapper.appmapper;

import com.pxg.app.core.model.dict.AppDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AppDictMapper {

    /**
     * sql语句中不加入删除
     */
//    int delete(AppDict11 appDict);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dict.app_dict
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    int insert(AppDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dict.app_dict
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    int insertSelective(AppDict record);

    List<AppDict> selectSelective(AppDict record);


    Long countSelective(AppDict record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dict.app_dict
     * @mbg.generated Sun Aug 25 23:48:41 CST 2019
     */
    int update(@Param("record") AppDict record);

}