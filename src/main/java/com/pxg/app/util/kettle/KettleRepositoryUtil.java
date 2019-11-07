package com.pxg.app.util.kettle;

import com.pxg.app.core.model.kettle.KettleRepositoryTable;
import com.pxg.app.kettle.model.KettleRepositoryDataInfo;
import com.pxg.app.util.UUIDUtil;
import com.pxg.app.util.constant.Constant;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.logging.LogLevel;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.LongObjectId;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.RepositoryElementMetaInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.pxg.app.util.constant.Constant.TYPE_FILE;
import static com.pxg.app.util.constant.Constant.dateToLong;

/**
 * <p>
 * 2019/9/25  23:06
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * </p>
 * <p>
 * @author pxg
 * @emil pxg950110@163.com
 * @Date 2019/9/25
 * @Version 1.0.0
 * @description kettle 资源库相关操作
 * </p>
 */
public class KettleRepositoryUtil {

    private static Logger log = LoggerFactory.getLogger(KettleRepositoryUtil.class);

    /**
     * 初始化kettle环境
     */
    static {
        try {
            KettleEnvironment.init();
        } catch (KettleException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取资源库相关信息
     * @return
     */
    public static KettleDatabaseRepository kettleDatabaseRepository(
            KettleRepositoryTable repositoryTable) {
        //初始化实例
        KettleDatabaseRepository kettleDatabaseRepository =
                new KettleDatabaseRepository();
        // 连接到资源库
        //数据库data元
        KettleDatabaseRepositoryMeta kettleDatabaseRepositoryMeta =
                new KettleDatabaseRepositoryMeta();
//        String name, String type, String access, String host, String db, String port, String user,
//        setValues( name, type, access, host, db, port, user, pass );
        DatabaseMeta meta = new DatabaseMeta();
        meta.setValues(
                repositoryTable.getkName(),//
                repositoryTable.getkTypeName(),//
                repositoryTable.getkAccess(),//
                repositoryTable.getkHost(),//
                repositoryTable.getkDb(),//
                repositoryTable.getkPort(),//
                repositoryTable.getkUser(),//
                repositoryTable.getkPwd());
        kettleDatabaseRepositoryMeta.setConnection(meta);
        kettleDatabaseRepository.init(kettleDatabaseRepositoryMeta);
        try {
            kettleDatabaseRepository.connect(repositoryTable.getkRepUser(), repositoryTable.getkRepPassword());
            log.info("连接成功");
            return kettleDatabaseRepository;
        } catch (KettleException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * @param repository
     * @return
     */
    //获取资源库中的相关信息
    public static List<KettleRepositoryDataInfo> getKettleDatabaseRepostoryInfoList(
            KettleDatabaseRepository repository) {
        List<KettleRepositoryDataInfo> map = new ArrayList<>();
        //资源库中job总数
        getFileList(repository, new LongObjectId(0), "/", map);
        return map;
    }

    /**
     * @param repository
     * @param objectId
     * @return
     */
    public static List<KettleRepositoryDataInfo>
    getFileList(KettleDatabaseRepository repository,
                ObjectId objectId,
                String parent_direct,
                List<KettleRepositoryDataInfo> map) {
        //循环调用
        try {
            //获取根目录
            if (objectId == null) {
                objectId = new LongObjectId(0);
            }
            //
            //通过objectID获取目录和目录下的文件
            //获取目录
            //获取kettle作业和转换
            List<RepositoryElementMetaInterface> repositoryElementMetaInterfaceList
                    = repository.getJobAndTransformationObjects(objectId, false);
            for (RepositoryElementMetaInterface repositoryElementMetaInterface : repositoryElementMetaInterfaceList) {
                map.add(new KettleRepositoryDataInfo(
// objectId,
                        repositoryElementMetaInterface.getObjectId().getId(),
//String objectName,
                        repositoryElementMetaInterface.getName(),
//String objectDescription,
                        repositoryElementMetaInterface.getDescription(),
// boolean isDeleted,
                        repositoryElementMetaInterface.isDeleted(),
//String objectType,
                        repositoryElementMetaInterface.getObjectType().toString(),
//String directoryObjectId,
                        repositoryElementMetaInterface.getRepositoryDirectory().getObjectId().getId(),
//String modifedUser,
                        repositoryElementMetaInterface.getModifiedUser(),
//String modifedDate
                        repositoryElementMetaInterface.getModifiedDate()
                ));
            }

            //获取目录
            String[] dirStrs = repository.getDirectoryNames(new LongObjectId(objectId));
            if (dirStrs.length == 0) {
                //如果无目录
            } else {
                //获取
                for (String dirStr : dirStrs) {
                    //获取目录相关信息
                    RepositoryDirectoryInterface directoryInterface =
                            repository.findDirectory(parent_direct + dirStr);

                    map.add(new KettleRepositoryDataInfo(
                            // objectId,
                            directoryInterface.getObjectId().getId(),
//String objectName,
                            directoryInterface.getName(),
//String objectDescription,
                            null,
// boolean isDeleted,
                            false,
//String objectType,
                            TYPE_FILE,
//String directoryObjectId,
                            directoryInterface.getParent().getObjectId().getId(),
//String modifedUser,
                            null,
//String modifedDate
                            null


                    ));
                    //获取objectid
                    RepositoryDirectoryInterface repositoryDirectoryInterface =
                            repository.findDirectory(parent_direct + dirStr);
                    objectId = repositoryDirectoryInterface.getObjectId();
                    getFileList(repository, objectId, parent_direct + dirStr + "/", map);
                }
            }

        } catch (KettleException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 通过objectId查询转换  v1.0版本返回xml文件
     * @param objectId
     * @param repository
     * @return
     */
    public static String getTransformation(LongObjectId objectId, KettleDatabaseRepository repository) {
        try {
            return repository.loadTransformation(objectId, null).getXML();
        } catch (KettleException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 通过objectId查询job  v1.0版本返回xml文件
     * @param objectId
     * @param repository
     * @return
     */
    public static String getJobInfo(LongObjectId objectId, KettleDatabaseRepository repository) {
        //
        try {
            JobMeta jobMeta = repository.loadJob(objectId, null);
            return jobMeta.getXML();
        } catch (KettleException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 执行kettle资源库中的transformation 和job
     */

    public static Map<Object, Object> runKettleDatabaseJob(
            LongObjectId objectId,
            KettleDatabaseRepository repository
    ) {
        Map<Object, Object> map = new HashMap<>();
        log.info(Constant.dateToFormatString(new Date()) +
                "    ===>>>开始执行JOB任务<<<===");
        //开始时间
        map.put("startDate", dateToLong(new Date()));
        //job
        Job job = null;
        try {
            JobMeta jobMeta = repository.loadJob(objectId, null);
            job = new Job(repository, jobMeta);
            job.setLogLevel(LogLevel.ROWLEVEL);
            job.start();
            job.waitUntilFinished();
            map.put("logInfo", KettleLogStoreLogInfo.getKettleLogStoreLogInfoByLogChannelId(job.getLogChannelId()));
        } catch (KettleException e) {
            e.printStackTrace();
        }


        return map;
    }

    /**
     * 执行 kettle 转换 v1.0版本日志未加入监听 只在运行之后输出日志到数据库和前端
     * @param objectId
     * @param repository
     */
    public static Map<Object, Object> runKettleDatabaseTransformation(
            LongObjectId objectId,  //资源库中的文件objectId
            KettleDatabaseRepository repository
    ) {

        Map<Object, Object> map = new HashMap<>();

        //生成执行数据的唯一表示uuid
        map.put("uuid", UUIDUtil.getUUID());
        map.put("jobObjectId", String.valueOf(objectId));
        log.info(Constant.dateToFormatString(new Date()) + "    ===>>>开始执行transformation工程<<<===");
        map.put("startTime", Constant.dateToLong(new Date()));
        // 获取transformation
        Trans trans = null;
        try {

            TransMeta transMeta = repository.loadTransformation(objectId, null);
            log.info("转换信息===>>>" + transMeta.toString());
            //转换
            trans = new Trans(transMeta);
            //设置日志级别
            trans.setLogLevel(LogLevel.ROWLEVEL);
            log.info("转换执行日志级别===>>>" + LogLevel.ROWLEVEL);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("errorInfo", e.getMessage());
            return map;
        }
        try {
            //执行任务
            trans.execute(null);
            trans.waitUntilFinished();
            log.info("执行结束");

            map.put("endTime", Constant.dateToLong(new Date()));
            map.put("logInfo", KettleLogStoreLogInfo
                    .getKettleLogStoreLogInfoByLogChannelId(
                            trans.getLogChannelId()));
            return map;
        } catch (KettleException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            map.put("logInfo", KettleLogStoreLogInfo
                    .getKettleLogStoreLogInfoByLogChannelId(
                            trans.getLogChannelId()));
            return map;
        }
    }


}
