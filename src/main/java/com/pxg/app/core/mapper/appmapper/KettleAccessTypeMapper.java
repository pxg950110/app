package com.pxg.app.core.mapper.appmapper;

import com.pxg.app.core.model.kettle.KettleAccessType;

import java.util.List;

public interface KettleAccessTypeMapper {

    /**
     * 只提供一个条件查询的接口
     * @param kettleAccessType
     * @return
     */
    List<KettleAccessType> selectSelective(KettleAccessType kettleAccessType);
}