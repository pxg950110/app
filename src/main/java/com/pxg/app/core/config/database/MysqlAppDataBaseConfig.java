package com.pxg.app.core.config.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * <p>
 * 2019/9/16  22:33
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * </p>
 * <p>
 * @author pxg
 * @init
 * @Date 2019/9/16
 * @Version 1.0.0
 * @description </p>
 */
@Configuration
@MapperScan(basePackages = "com.pxg.app.core.mapper.mysqlappmapper",
        sqlSessionTemplateRef = "mysqlappSqlSessionTemplate")
public class MysqlAppDataBaseConfig {
    /**
     * 配置数据源
     * @return
     */
    @Bean("mysqlappDataSource")
    @ConfigurationProperties(prefix = "spring.mysqlapp.datasource")
//    @Primary  //主配置
    public DataSource appDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置session工厂
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean("mysqlappSqlSessionFactory")
//    @Primary
    public SqlSessionFactory appSqlSessionFactory(
            @Qualifier("mysqlappDataSource") DataSource dataSource
    ) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/mybatis-mysql/**Mapper.xml"));
        return sqlSessionFactoryBean.getObject();

    }


    /**
     * 配置事务管理
     * @param dataSource
     * @return
     */
    @Bean("mysqlappDataSourceTransactionManager")
//    @Primary
    public DataSourceTransactionManager appDataSourceTransactionManager(
            @Qualifier("mysqlappDataSource") DataSource dataSource
    ) {
        return new DataSourceTransactionManager(dataSource);
    }

    //SqlSessionTemplate
    @Bean("mysqlappSqlSessionTemplate")
//    @Primary
    public SqlSessionTemplate appSqlSessionTemplate(@Qualifier("mysqlappSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
