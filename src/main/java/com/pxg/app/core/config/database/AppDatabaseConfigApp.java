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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * @Author pxg
 * @Date 2019/7/11
 * @Time 19:38
 * @Version v1.0
 * @mail pxg950110@163.com
 * @description
 */
@Configuration
@MapperScan(basePackages = "com.pxg.app.core.mapper.appmapper",
        sqlSessionTemplateRef = "appSqlSessionTemplate")
public class AppDatabaseConfigApp {

    /**
     * 配置数据源
     * @return
     */
    @Bean("appDataSource")
    @ConfigurationProperties(prefix = "spring.app.datasource")
    @Primary  //主配置
    public DataSource appDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置session工厂
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean("appSqlSessionFactory")
    @Primary
    public SqlSessionFactory appSqlSessionFactory(
            @Qualifier("appDataSource") DataSource dataSource
    ) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/mybatis/**Mapper.xml"));
        return sqlSessionFactoryBean.getObject();

    }


    /**
     * 配置事务管理
     * @param dataSource
     * @return
     */
    @Bean("appDataSourceTransactionManager")
    @Primary
    public DataSourceTransactionManager appDataSourceTransactionManager(
            @Qualifier("appDataSource") DataSource dataSource
    ) {
        return new DataSourceTransactionManager(dataSource);
    }

    //SqlSessionTemplate
    @Bean("appSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate appSqlSessionTemplate(@Qualifier("appSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
