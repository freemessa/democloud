package com.example.flowable.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.example.flowable.utils.DBTypeEnum;
import com.example.flowable.utils.DynamicDataSource;
import liquibase.Liquibase;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@Configuration
@Slf4j
public class DatabaseConfiguration {
    protected static final String LIQUIBASE_CHANGELOG_PREFIX = "ACT_DE_";

    /**
     * flowable数据源
     */
    @Bean
    @ConfigurationProperties("spring.datasource.dynamic.datasource.flowable")
    @Primary
    public DataSource flowableDataSource(){
        log.info("加载主数据源flowable DataSource.");
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 业务
     */
    @Bean
    @ConfigurationProperties("spring.datasource.dynamic.datasource.biz")
    public DataSource bizDataSource(){
        log.info("加载从数据源biz DataSource.");
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源
     */
    @Bean
    public DataSource myRoutingDataSource(@Qualifier("flowableDataSource") DataSource flowableDataSource,
                                          @Qualifier("bizDataSource") DataSource courseDataSource) {
        log.info("加载[flowableDataSource-bizDataSource]设置为动态数据源DynamicDataSource.");
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DBTypeEnum.FLOWABLE, flowableDataSource);
        targetDataSources.put(DBTypeEnum.BIZ, courseDataSource);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(flowableDataSource);
        dynamicDataSource.setTargetDataSources(targetDataSources);

        return dynamicDataSource;
    }

    // 注意 这里需要指定dataSource为flowable datasource
    //@Bean
    //public Liquibase liquibase(@Qualifier("flowableDataSource") DataSource dataSource) throws SQLException {
        //...
    //    return new Liquibase("flowableDataSource",new ClassLoaderResourceAccessor(),dataSource.getConnection());
    //}

}
