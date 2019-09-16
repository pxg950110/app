package com.pxg.app.core.service;

import com.pxg.app.core.mapper.appmapper.AppDictMapper;
import com.pxg.app.core.model.dict.AppDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.pxg.app.util.constant.Constant.*;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/9/4
 * @Time 22:43
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
@Service
public class AppDictService {

    @Autowired
    private AppDictMapper appDictMapper;

    public Map<Object, Object> getAppDictByClassType(String classType) {
        try {
            //如果classType 为空或null返回错误信息
            if (classType == null || "".equals(classType.trim())) {
                return InterfaceReturnInformation(ERROR_CODE, "参数不为空", ERROR_MESSAGE);
            }
            AppDict record = new AppDict(classType, 1);
            List<AppDict> appDicts = appDictMapper.selectSelective(record);

            return InterfaceReturnInformation(SUCCESS_CODE, appDicts, SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.getMessage();
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }

}
