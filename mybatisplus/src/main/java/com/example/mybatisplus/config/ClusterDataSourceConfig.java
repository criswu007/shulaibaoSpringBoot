package com.example.mybatisplus.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.example.mybatisplus.mybatisplus.interceptor.shard.TableShardInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @description: 从库数据源配置，持久层使用mybatisPlus
 * @date: 2019-11-20 9:53
 * @author: wudb
 */

@Configuration
public class ClusterDataSourceConfig {
    private static final String IMP = "com.example.mybatisplus.mybatisplus.handler";

    @Bean(name = "clusterDataSource")
    public DataSource clusterDataSource(@Qualifier("dbConfig") Config config) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(config.getClusterUrl());
        dataSource.setUsername(config.getClusterUsername());
        dataSource.setPassword(config.getClusterPassword());
        dataSource.setDriverClassName(config.getClusterDriverClassName());

        //具体配置
        dataSource.setInitialSize(config.getInitialSize());
        dataSource.setMinIdle(config.getMinIdle());
        dataSource.setMaxActive(config.getMaxActive());
        dataSource.setMaxWait(config.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(config.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(config.getMinEvictableIdleTimeMillis());
        dataSource.setValidationQuery(config.getValidationQuery());
        dataSource.setTestWhileIdle(config.isTestWhileIdle());
        dataSource.setTestOnBorrow(config.isTestOnBorrow());
        dataSource.setTestOnReturn(config.isTestOnReturn());
        return dataSource;
    }


    /**
     * sessionFactory
     * @param clusterDataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "clusterSqlSessionFactory")
    public MybatisSqlSessionFactoryBean clusterSqlSessionFactory (@Qualifier("clusterDataSource") DataSource clusterDataSource, GlobalConfig globalConfig) throws Exception{
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(clusterDataSource);
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setGlobalConfig(globalConfig);
        configuration.setMapUnderscoreToCamelCase(true);    //下划线转驼峰
        sqlSessionFactoryBean.setConfiguration(configuration);

        List<Interceptor> interceptors = new ArrayList<>();
        //设置分页插件
        interceptors.add(new PaginationInterceptor());
        //设置分表插件
        interceptors.add(new TableShardInterceptor());

        sqlSessionFactoryBean.setPlugins(interceptors.toArray(new Interceptor[interceptors.size()]));

        //注册EnumTypeHandler
        sqlSessionFactoryBean.setTypeHandlersPackage(IMP);
        return sqlSessionFactoryBean;
    }

    /**
     * 包扫描
     * @return
     */
    @Bean
    public MapperScannerConfigurer clusterMapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.example.mybatisplus.mapper.cluster");
        //设置上面的factory name
        configurer.setSqlSessionFactoryBeanName("clusterSqlSessionFactory");
        return configurer;
    }


    /**
     * 事务管理器
     * @return
     */
    @Bean(name = "clusterTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager(@Qualifier("clusterDataSource") DataSource clusterDataSource) {
        return new DataSourceTransactionManager(clusterDataSource);
    }

}
