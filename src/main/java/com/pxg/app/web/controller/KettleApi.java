package com.pxg.app.web.controller;

import com.pxg.app.core.model.kettle.KettleAccessType;
import com.pxg.app.core.model.kettle.KettleDatabaseType;
import com.pxg.app.core.model.kettle.KettleRepositoryTable;
import com.pxg.app.core.model.km.KettleFileList;
import com.pxg.app.core.modelutil.KettleFileUpload;
import com.pxg.app.core.service.KettleService;
import com.pxg.app.kettle.model.KettleRepositoryDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/9/1
 * @Time 16:27
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
@RestController
@RequestMapping("/api/kettle")
@Api(description = "kettle相关操作")
public class KettleApi {
    private static Logger log = LoggerFactory.getLogger(KettleApi.class);

    @Autowired
    private KettleService kettleService;

    @ApiOperation("上传作业或转换")
    @PostMapping(value = "/kettlefile/upload")
    public Map<Object, Object> upload(
            MultipartFile file,
            KettleFileUpload kettleFileUpload) {
        return kettleService.upload(file, kettleFileUpload);
    }

    @GetMapping("/kettlefile/info")
    public Map<Object, Object> getKettleFileListById(Integer id) {
        return kettleService.getKettleFileListById(id);
    }


    /**
     * 修改kettle文件内容
     * @param kettleFileList
     * @return
     */
    @PostMapping("/kettle/update")
    public Map<Object, Object> updateKettleFileList(@RequestBody KettleFileList kettleFileList) {
        return kettleService.updateKettleFileList(kettleFileList);
    }


    @GetMapping("/download")
    public Map<Object, Object> downLoadData(HttpServletResponse res, int id) {
        return kettleService.downLoadData(res, id);
    }

    @GetMapping("/run/trans")
    public Map<Object, Object> runKettleFileListById(int id) {
        return kettleService.runKettleFileListById(id);
    }



    /**
     * 获取上传文件  先禁用
     * @return
     */
    /**
     @GetMapping("/file/all")
     @ApiOperation("获取所有上传文件") public Map<Object, Object> getKettleFileList() {
     return kettleService.getKettleFileList();
     }
     */

    @GetMapping("/file/all/page")
    @ApiOperation("通过分页获取数据")
    public Map<Object, Object> getKettleFileListByPage(int pageId,
                                                       int pageCount,
                                                       int status, String name) {
        if (name == null || name.trim().equals(""))
            return kettleService.getKettleFileListByPage(pageId, pageCount, status);
        return kettleService.getKettleFileListByPageSearch(pageId, pageCount, status, name);
    }

    @ApiOperation("获取所有kettle数据库类型")
    @GetMapping("/database/dict/type")
    public Map<Object, Object> getAllKettleDataBaseType() {
        return kettleService.getAllKettleDataBaseType();
    }

    @ApiOperation("有条件的查询数据库类型")
    @PostMapping("/database/dict/type")
    public Map<Object, Object> getAllKettleDataBaseType(@RequestBody KettleDatabaseType kettleDatabaseType) {
        return kettleService.getAllKettleDataBaseType(kettleDatabaseType);
    }

    @ApiOperation("获取资数据库连接类型")
    @PostMapping("/database/access/type")
    public Map<Object, Object> getKettleAccessType(@RequestBody KettleAccessType kettleAccessType) {
        return kettleService.getKettleAccessType(kettleAccessType);
    }

    //添加资源库
    @ApiOperation("添加资源库")
    @PostMapping("/database/repository/add")
    public Map<Object, Object> addKetteDataBaseRepository(@RequestBody KettleRepositoryTable kettleRepositoryTable) {
        return kettleService.addKetteDataBaseRepository(kettleRepositoryTable);
    }

    @ApiOperation("获取所有资源库")
    @GetMapping("/database/repository/all/list")
    public Map<Object, Object> getAllKettleDataBaseRepository() {
        return kettleService.getAllKettleDataBaseRepository();
    }


    @ApiOperation("检测资源库是否有效")
    @PostMapping("/database/repository/check/valid")
    public Map<Object, Object> checkKettleDataBaseRepository(@RequestBody KettleRepositoryTable kettleRepositoryTable) {
        return kettleService.checkKettleDataBaseRepository(kettleRepositoryTable);
    }


    @ApiOperation("获取资源库中信息")
    @GetMapping("/database/repository/info/list/{id}")
    public Map<Object, Object> getKettleDatabaseRepostoryInfoListById(@PathVariable("id") Integer id) {
        return kettleService.getKettleDatabaseRepostoryInfoListById(id);
    }


    /**
     * @param dataInfo
     * @param repositoryId
     * @return
     */
    @PostMapping("/database/repository/info/{repositoryId}/jobortrans/info")
    @ApiOperation("获取资源库中的job或者transformation")
    public Map<Object, Object> getJobOrTransformationInfo(@RequestBody KettleRepositoryDataInfo dataInfo,
                                                          @PathVariable("repositoryId") Integer repositoryId) {
        return kettleService.getJobOrTransformationInfo(dataInfo, repositoryId);
    }

    @PostMapping("/database/repository/run/{repositoryId}/jobortrans")
    @ApiOperation("执行资源库中的job或者transformation")
    public Map<Object, Object> runKettleDatabaseJobOrTransformation(
            @RequestBody KettleRepositoryDataInfo kettleRepositoryDataInfo,  //资源库中的文件objectId
            @PathVariable("repositoryId") Integer repositoryId
    ) {
        return kettleService.runKettleDatabaseJobOrTransformation(
                kettleRepositoryDataInfo,
                repositoryId);
    }
}
