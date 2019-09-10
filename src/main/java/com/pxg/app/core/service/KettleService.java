package com.pxg.app.core.service;

import com.pxg.app.core.mapper.appmapper.AppDictMapper;
import com.pxg.app.core.mapper.appmapper.KettleFileListMapper;
import com.pxg.app.core.model.km.KettleFileList;
import com.pxg.app.core.modelutil.KettleFileListAll;
import com.pxg.app.core.modelutil.KettleFileUpload;
import com.pxg.app.util.FileUtil;
import com.pxg.app.util.kettle.KettleInit;
import com.pxg.app.util.kettle.KettleLogStoreLogInfo;
import com.pxg.app.util.rabbit.RabbitProducer;
import org.pentaho.di.trans.Trans;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
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
 * @Date 2019/9/1
 * @Time 17:36
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
@Service
public class KettleService {

    private static org.slf4j.Logger log = LoggerFactory.getLogger(KettleService.class);
    @Autowired
    private KettleFileListMapper kettleFileListMapper;

    //字典
    @Autowired
    private AppDictMapper appDictMapper;

    @Autowired
    private RabbitProducer rabbitProducer;

    /**
     * 上传文件 并可执行
     * @param file
     * @param kettleFileUpload
     * @return
     */
    public Map<Object, Object> upload(
            MultipartFile file,
            KettleFileUpload kettleFileUpload) {
        try {
            //上
            // 传文件保存到数据库中
            KettleFileList kettleFileList = new KettleFileList();
            //类别
            kettleFileList.setJobTypeId(kettleFileUpload.getAppDictId());
            //名称
            kettleFileList.setName(kettleFileUpload.getFileName());
            //描述
            kettleFileList.setDescription(kettleFileUpload.getDescription());
            //文件内容
            kettleFileList.setFileText(new String(file.getBytes()));
            //
            kettleFileList.setStatus(1);
            kettleFileList.setUploadTime(new Date());
            kettleFileListMapper.insertSelective(kettleFileList);
            return InterfaceReturnInformation(SUCCESS_CODE, null, SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);

        }


    }

    /**
     * 通过id 获取 kettle文件
     * @param id
     * @return
     */
    public Map<Object, Object> getKettleFileListById(Integer id) {
        try {
            KettleFileList kettleFileList = kettleFileListMapper.selectByPrimaryKey(id);

            return InterfaceReturnInformation(SUCCESS_CODE, kettleFileList, SUCCESS_MESSAGE);

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }

    }


    //修改
    public Map<Object, Object> updateKettleFileList(KettleFileList kettleFileList) {

        try {

            kettleFileList.setUploadTime(null);
            kettleFileList.setStatus(null);
            kettleFileListMapper.updateByPrimaryKeySelective(kettleFileList);
            return InterfaceReturnInformation(SUCCESS_CODE, null, SUCCESS_MESSAGE);

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }

    }

    //通过id下载内容
    public Map<Object, Object> downLoadData(HttpServletResponse res, int id) {
        try {
            KettleFileList kettleFileList = kettleFileListMapper.selectByPrimaryKey(id);
            File file = new File(new String(kettleFileList.getName().getBytes("UTF-8")) + ".ktr");
            FileUtil.getFile(kettleFileList.getFileText().getBytes("UTF-8"), kettleFileList.getName() + ".ktr");
            FileUtil.responseTo(file, res);
            file.delete();
//            return InterfaceReturnInformation(SUCCESS_CODE,null ,SUCCESS_MESSAGE );
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
//            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(),ERROR_MESSAGE );
        }
        return null;

    }

    public Map<Object, Object> runKettleFileListById(int id) {
        try {
            KettleFileList kettleFileList = kettleFileListMapper.selectByPrimaryKey(id);
            KettleInit kettleInit = new KettleInit();
            File file = new File(kettleFileList.getName() + ".ktr");
            FileUtil.getFile(kettleFileList.getFileText().getBytes("UTF-8"), file.getName());
            Trans trans = kettleInit.runTransTest(file.getName());
            String logText = KettleLogStoreLogInfo.getKettleLogStoreLogInfoByLogChannelId(trans.getLogChannelId());
            rabbitProducer.stringSend2("成功");
            rabbitProducer.runKettleFileLog(logText);
            file.delete();
            return InterfaceReturnInformation(SUCCESS_CODE, logText, SUCCESS_MESSAGE);

        } catch (Exception e) {
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }


    //获取上传文件的kettle列表
    public Map<Object, Object> getKettleFileList() {
        try {
            //获取文件 查询所有
            KettleFileList kettleFileList = new KettleFileList();
            //查询有效的数据
            kettleFileList.setStatus(1);
            //获取文件 查询所有
//            List<KettleFileList> kettleFileLists=kettleFileListMapper.selectSelective(kettleFileListMapperList);

            //包含所有信息的文件
            List<KettleFileListAll> kettleFileListAlls = kettleFileListMapper.selectAll(1);


            return InterfaceReturnInformation(SUCCESS_CODE, kettleFileListAlls, SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }

    public void test() {
        List<KettleFileList> lists = kettleFileListMapper.selectSelective(new KettleFileList());
        lists.forEach(this::exe);
    }

    public void exe(KettleFileList kettleFileList) {
        System.err.println("aaaaa" + new Date());
    }

}
