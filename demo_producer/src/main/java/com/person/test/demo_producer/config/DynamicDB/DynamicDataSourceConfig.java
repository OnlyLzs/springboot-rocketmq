package com.person.test.demo_producer.config.DynamicDB;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.person.test.demo_producer.enums.DataSourceEnum;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源配置
 */
@Configuration
@MapperScan(basePackages = "com.person.test.demo_producer.mapper")
public class DynamicDataSourceConfig {
    /**
     * 创建 DataSource Bean
     * */
    @Primary
    @Bean("db1DataSource")
    @ConfigurationProperties("spring.datasource.druid.db1")
    public DataSource oneDataSource(){
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        return dataSource;
    }

    @Bean("db2DataSource")
    @ConfigurationProperties("spring.datasource.druid.db2")
    public DataSource twoDataSource(){
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        return dataSource;
    }

    /**
     * 如果还有数据源,在这继续添加 DataSource Bean
     * */
    @Bean
    public DynamicDataSource dataSource(@Qualifier("db1DataSource") DataSource oneDataSource, @Qualifier("db2DataSource") DataSource twoDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataSourceEnum.DBOne, oneDataSource);
        targetDataSources.put(DataSourceEnum.DBTwo, twoDataSource);
        // 还有数据源,在targetDataSources中继续添加
        System.out.println("DataSources信息是:" + targetDataSources);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(oneDataSource);
        //return new DynamicDataSource(oneDataSource, targetDataSources);
        return dynamicDataSource;
    }

    /**
     * 解决多数据源mybatis配置失效问题
     * 将bean 注入到SQLSessionFactory
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfiguration(){
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean
    //@Primary
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dataSource, org.apache.ibatis.session.Configuration mybatisConfiguration) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //设置mybatis配置
        sqlSessionFactoryBean.setConfiguration(mybatisConfiguration);
        //此处设置为了解决找不到mapper文件的问题
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().
                getResources("classpath:mybatis/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 事务管理
     *
     * @return 事务管理实例
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager(DynamicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }




}
