package com.pxg.app.util.kettle;

import com.pxg.app.core.model.kettle.KettleRepositoryTable;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.repository.RepositoryElementMetaInterface;
import org.pentaho.di.repository.kdr.KettleDatabaseRepository;
import org.pentaho.di.repository.kdr.KettleDatabaseRepositoryMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
    public static KettleDatabaseRepository kettleDatabaseRepository(KettleRepositoryTable repositoryTable) {
        //初始化实例
        KettleDatabaseRepository kettleDatabaseRepository = new KettleDatabaseRepository();
        // 连接到资源库
        //数据库data元
        KettleDatabaseRepositoryMeta kettleDatabaseRepositoryMeta = new KettleDatabaseRepositoryMeta();
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
            kettleDatabaseRepository.connect("admin", "admin");
            log.info("连接成功");
            return kettleDatabaseRepository;
        } catch (KettleException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * @param repository 获取资源库中文件列表
     */
    public static void getKettleDatabaseRepositoryFileList(KettleDatabaseRepository repository) {
        try {
            ObjectId objectId = repository.getRootDirectoryID();
            //获取根目录下的文件夹和文件
            String[] list = repository.getDirectoryNames(objectId);
            System.out.println(objectId);
            //获取根目录下的job和trans
            List<RepositoryElementMetaInterface> metaInterfaces = repository
                    .getJobObjects(objectId, true);
            System.out.println(metaInterfaces.size());
            for (RepositoryElementMetaInterface repositoryElementMetaInterface : metaInterfaces) {
                System.out.println(repositoryElementMetaInterface.getObjectType());
            }
            for (String aa : list) {
                System.out.println(aa);
            }
            //获取


        } catch (KettleException e) {
            e.printStackTrace();
        }
    }

}
