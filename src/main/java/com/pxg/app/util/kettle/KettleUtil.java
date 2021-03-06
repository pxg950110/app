package com.pxg.app.util.kettle;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 2019/9/23  23:07
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * </p>
 * <p>
 * @author pxg
 * @emil pxg950110@163.com
 * @init
 * @Date 2019/9/23
 * @Version 1.0.0
 * @description <p>
 *
 * </p>
 */
public class KettleUtil extends Trans {

    private static Logger log = LoggerFactory.getLogger(KettleUtil.class);
    public static String kettle_log = "data_log";

    /**
     * <summer>
     * 通过文件路径允许 kettle的转换
     * </summer>
     * @param
     */
    public static void runTransformation() {
        try {
            String filename = "aa.ktr";
            KettleEnvironment.init();
            TransMeta transMeta = new TransMeta(filename);
            transMeta.getDbCache();
            log.error(transMeta.getLogChannelId());
            StepMeta stepMeta = new StepMeta();

            Trans trans = new KettleUtil(transMeta);

        } catch (KettleException e) {
            e.printStackTrace();
        }
    }

    public KettleUtil(TransMeta transMeta) {
        super(transMeta);
        log.info("kettle------------");
    }

    @Override
    public void execute(String[] arguments) throws KettleException {
        super.execute(arguments);
    }

}
