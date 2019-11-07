package com.pxg.app.core.service;

import com.pxg.app.core.mapper.appmapper.*;
import com.pxg.app.core.model.kettle.KettleAccessType;
import com.pxg.app.core.model.kettle.KettleDatabaseType;
import com.pxg.app.core.model.kettle.KettleRepositoryTable;
import com.pxg.app.core.model.km.KettleFileList;
import com.pxg.app.core.model.task.TaskQuartzSet;
import com.pxg.app.core.modelutil.KettleFileListAll;
import com.pxg.app.core.modelutil.KettleFileUpload;
import com.pxg.app.kettle.model.KettleRepositoryDataInfo;
import com.pxg.app.util.FileUtil;
import com.pxg.app.util.JsonUtils;
import com.pxg.app.util.kettle.KettleInit;
import com.pxg.app.util.kettle.KettleLogStoreLogInfo;
import com.pxg.app.util.kettle.KettleRepositoryUtil;
import com.pxg.app.util.rabbit.RabbitProducer;
import org.pentaho.di.repository.LongObjectId;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.trans.Trans;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
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

    @Autowired
    private KettleDatabaseTypeMapper kettleDatabaseTypeMapper;

    @Autowired
    private KettleRepositoryTableMapper kettleRepositoryTableMapper;

    //连接类型
    @Autowired
    private KettleAccessTypeMapper kettleAccessTypeMapper;

    @Autowired
    private TaskQuartzSetMapper taskQuartzSetMapper;

    public void test() {
        KettleRepositoryTable kettleRepositoryTable = new KettleRepositoryTable();
        kettleRepositoryTable.setId(3);
        List<KettleRepositoryTable> repositoryTables = kettleRepositoryTableMapper.selectSelective(kettleRepositoryTable);
        kettleRepositoryTable = repositoryTables.get(0);
        System.out.println(kettleRepositoryTable);
        //获取
        KettleDatabaseRepository kettleDatabaseRepository = KettleRepositoryUtil.kettleDatabaseRepository(kettleRepositoryTable);
        KettleRepositoryUtil.runKettleDatabaseTransformation(new LongObjectId(1), kettleDatabaseRepository);
    }

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


    /**
     * 分页获取数据
     * @param pageId
     * @param pageCount
     * @param status
     * @return
     */
    public Map<Object, Object> getKettleFileListByPage(int pageId,
                                                       int pageCount,
                                                       int status) {
        try {


            //包含所有信息的文件
            List<KettleFileListAll> kettleFileListAlls = kettleFileListMapper.selectAllByPage(pageId, pageCount, status);
            //获取总页数
            KettleFileList kettleFileList = new KettleFileList();
            kettleFileList.setStatus(1);
            long count = kettleFileListMapper.count(kettleFileList);
//            long pageNumber=(count%pageCount==0)?(count/pageCount):(count/pageCount+1);
            Map<Object, Object> map = new HashMap<>();
            map.put("data", kettleFileListAlls);
            map.put("pageNumber", count);
            return InterfaceReturnInformation(SUCCESS_CODE, map, SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }


    /**
     * 分页获取数据
     * @param pageId
     * @param pageCount
     * @param status
     * @return
     */
    public Map<Object, Object> getKettleFileListByPageSearch(int pageId,
                                                             int pageCount,
                                                             int status, String name) {
        try {


            //包含所有信息的文件
            List<KettleFileListAll> kettleFileListAlls = kettleFileListMapper.selectAllByPageAndName(pageId, pageCount, status, name);
//            //获取总页数
//            KettleFileList kettleFileList=new KettleFileList();
//            kettleFileList.setStatus(1);
            long count = kettleFileListMapper.counta(name);
//            long pageNumber=(count%pageCount==0)?(count/pageCount):(count/pageCount+1);
            Map<Object, Object> map = new HashMap<>();
            map.put("data", kettleFileListAlls);
            map.put("pageNumber", count);
            return InterfaceReturnInformation(SUCCESS_CODE, map, SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }


    public void exe(KettleFileList kettleFileList) {
        System.err.println("aaaaa" + new Date());
    }


    /**
     * 数据库字典相关操作
     */
    //查询所有kettle数据库类型
    public Map<Object, Object> getAllKettleDataBaseType() {
        try {
            return InterfaceReturnInformation(SUCCESS_CODE, kettleDatabaseTypeMapper.selectSelective(new KettleDatabaseType()), SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }

    /**
     * 条件查询数据库类型
     * @param kettleDatabaseType
     * @return
     */
    public Map<Object, Object> getAllKettleDataBaseType(KettleDatabaseType kettleDatabaseType) {
        try {
            return InterfaceReturnInformation(SUCCESS_CODE, kettleDatabaseTypeMapper.selectSelective(kettleDatabaseType), SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }


    /**
     * *****************资源库相关操作**********************
     */

    /**
     * 添加资源库
     * @param kettleRepositoryTable
     * @return
     */
    public Map<Object, Object> addKetteDataBaseRepository(KettleRepositoryTable kettleRepositoryTable) {
        try {
            KettleDatabaseType kettleDatabaseType = new KettleDatabaseType();
            kettleDatabaseType.setIdDatabaseType((long) kettleRepositoryTable.getkTypeId());
            //通过id获取name
            List<KettleDatabaseType> kettleDatabaseTypes = kettleDatabaseTypeMapper.selectSelective(kettleDatabaseType);
            kettleRepositoryTable.setkTypeName(kettleDatabaseTypes.get(0).getCode());
//            (String kTypeName, String kHost, String kDb, String kPort, Integer status)
            //查询数据是否存
            List<KettleRepositoryTable> kettleRepositoryTables
                    = kettleRepositoryTableMapper.selectSelective(new KettleRepositoryTable(
                    kettleRepositoryTable.getkTypeName(),
                    kettleRepositoryTable.getkHost(),
                    kettleRepositoryTable.getkDb(),
                    kettleRepositoryTable.getkPort(),
                    1
            ));
            if (kettleRepositoryTables.size() > 0) {
                return InterfaceReturnInformation(EXIST_CODE, null, EXIST_MESSAGE);
            }
            //通过资源库验证是否有效
            KettleDatabaseRepository kettleDatabaseRepository
                    = KettleRepositoryUtil.kettleDatabaseRepository(kettleRepositoryTable);
            //查看资源库是否有效
            if (kettleDatabaseRepository == null) {
                return InterfaceReturnInformation(WARN_CODE, "数据无效", WARN_MESSAGE);
            }


            kettleRepositoryTable.setStatus(1);
            kettleRepositoryTable.setCreateTime(new Date());
            kettleRepositoryTableMapper.insert(kettleRepositoryTable);
            //返回成功标识
            return InterfaceReturnInformation(SUCCESS_CODE, null, SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            //返回错误信息
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }

    }


    /**
     * 获取所有资源库信息
     * @return
     */
    public Map<Object, Object> getAllKettleDataBaseRepository() {
        try {
            return InterfaceReturnInformation(SUCCESS_CODE, kettleRepositoryTableMapper.selectSelective(new KettleRepositoryTable()), SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }


    /**
     * 检测资源库是否有效
     * @param kettleRepositoryTable
     * @return
     */
    public Map<Object, Object> checkKettleDataBaseRepository(KettleRepositoryTable kettleRepositoryTable) {
        try {
            KettleDatabaseRepository kettleDatabaseRepository = KettleRepositoryUtil.kettleDatabaseRepository(kettleRepositoryTable);
            if (kettleDatabaseRepository == null) {
                return InterfaceReturnInformation(WARN_CODE, false, WARN_MESSAGE);
            } else {
                return InterfaceReturnInformation(SUCCESS_CODE, true, SUCCESS_MESSAGE);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }


    /**
     * 有条件获取连接类型
     * @param kettleAccessType
     * @return
     */
    public Map<Object, Object> getKettleAccessType(KettleAccessType kettleAccessType) {
        try {
            if (kettleAccessType == null)
                kettleAccessType = new KettleAccessType();
            log.info(JsonUtils.ObjectToJSONString(kettleAccessType));
            List<KettleAccessType> kettleAccessTypes = kettleAccessTypeMapper.selectSelective(kettleAccessType);
            return InterfaceReturnInformation(SUCCESS_CODE, kettleAccessTypes, SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }


    public Map<Object, Object> getKettleDatabaseRepostoryInfoListById(Integer id) {
        //查询资源库
        KettleRepositoryTable kettleRepositoryTable = kettleRepositoryTableMapper.selectByPrimaryKey(id);
        KettleDatabaseRepository kettleDatabaseRepository = null;
        try {
            kettleDatabaseRepository = KettleRepositoryUtil.kettleDatabaseRepository(kettleRepositoryTable);
        } catch (Exception e) {
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
        //判断是否有效
        if (kettleDatabaseRepository == null) {
            return InterfaceReturnInformation(WARN_CODE, "资源库不存在", WARN_MESSAGE);
        }
        //获取资源库相关信息
        try {
            return InterfaceReturnInformation(SUCCESS_CODE, KettleRepositoryUtil.getKettleDatabaseRepostoryInfoList(kettleDatabaseRepository), SUCCESS_MESSAGE);
        } catch (Exception e) {
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }

    /**
     * 获取kettle资源库中单个job或者transformation的xml内容
     * 获取相关信息v1.0版本只查询xml文件
     * v2.0版本查询所有步骤及相关信息
     * @param dataInfo
     * @param repositoryId
     * @return
     */
    public Map<Object, Object> getJobOrTransformationInfo(KettleRepositoryDataInfo dataInfo, Integer repositoryId) {
        try {
            KettleDatabaseRepository repository =
                    KettleRepositoryUtil.kettleDatabaseRepository(
                            kettleRepositoryTableMapper.selectByPrimaryKey(repositoryId));
            if (repository == null) {
                return InterfaceReturnInformation(WARN_CODE, "数据内容错误", WARN_MESSAGE);
            }
            String objectType = dataInfo.getObjectType();
            //判断类型 job transformation
            String res = null;
            if ("job".equals(objectType)) {
                res = KettleRepositoryUtil.getJobInfo(new LongObjectId(Long.parseLong(dataInfo.getObjectId())),
                        repository);
            } else if ("transformation".equals(objectType)) {
                res = KettleRepositoryUtil.getTransformation(new LongObjectId(Long.parseLong(dataInfo.getObjectId())),
                        repository);
            }
            if (res == null) {
                return InterfaceReturnInformation(WARN_CODE, "数据内容错误", WARN_MESSAGE);
            }
            return InterfaceReturnInformation(SUCCESS_CODE, res, SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
        }
    }

    /**
     * <p>
     * 执行 资源库中的工程
     * </p>
     * @param kettleRepositoryDataInfo
     * @param repositoryId
     * @return
     */
    public Map<Object, Object> runKettleDatabaseJobOrTransformation(
            KettleRepositoryDataInfo kettleRepositoryDataInfo,  //资源库中的文件objectId
            Integer repositoryId
    ) {
        Map<Object, Object> map = new HashMap<>();
        map.put("jobType", kettleRepositoryDataInfo.getObjectType());
        //通过资源库id获取资源库
        KettleDatabaseRepository repository =
                KettleRepositoryUtil
                        .kettleDatabaseRepository(
                                kettleRepositoryTableMapper
                                        .selectByPrimaryKey(repositoryId)
                        );
        //分类别执行
        if (kettleRepositoryDataInfo.getObjectType().equals(TYPE_TRANSFORMATION)) {
            try {
                map = KettleRepositoryUtil.runKettleDatabaseTransformation(
                        new LongObjectId(Long.parseLong(kettleRepositoryDataInfo.getObjectId())), repository);

                map.put("repositoyId", repositoryId);
                rabbitProducer.sendDatabaseJobRunInfo(map);
                return InterfaceReturnInformation(SUCCESS_CODE,
                        map, SUCCESS_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
            }
        } else if (kettleRepositoryDataInfo.getObjectType().equals(TYPE_JOB)) {
            try {
                map = KettleRepositoryUtil.runKettleDatabaseJob(
                        new LongObjectId(Long.parseLong(kettleRepositoryDataInfo.getObjectId())), repository
                );
                rabbitProducer.sendDatabaseJobRunInfo(map);
                return InterfaceReturnInformation(SUCCESS_CODE,
                        map, SUCCESS_MESSAGE);
            } catch (Exception e) {
                return InterfaceReturnInformation(ERROR_CODE, e.getMessage(), ERROR_MESSAGE);
            }
        } else {
            return InterfaceReturnInformation(WARN_CODE,
                    "任务类型执行错误", WARN_MESSAGE);
        }
    }

    /**
     * 通过
     */
    public Map<Object, Object> runKettleTaskInfo(TaskQuartzSet taskQuartzSet) {

        Map<Object, Object> map = new HashMap<>();
        //通过任务类型判断执行哪一个
        if (taskQuartzSet.getClassType().equals(CLASSTYPE_FILE)) {
            map.put("error", "暂不处理");
        } else if (taskQuartzSet.getClassType().equals(CLASSTYPE_REPOSITORY)) {


            KettleRepositoryDataInfo kettleRepositoryDataInfo = new KettleRepositoryDataInfo();
            kettleRepositoryDataInfo.setObjectId(String.valueOf(taskQuartzSet.getJobId()));
            kettleRepositoryDataInfo.setObjectType(taskQuartzSet.getJobType());
            //执行定时任务
            map = runKettleDatabaseJobOrTransformation(kettleRepositoryDataInfo, taskQuartzSet.getRepositoryId());
        }
        return map;
    }

    /**
     * 资源库的job执行次数+1
     */
    public void addTaskQuartzTimes(TaskQuartzSet taskQuartzSet) {
        taskQuartzSetMapper.addRunTimes(taskQuartzSet);
    }

}
