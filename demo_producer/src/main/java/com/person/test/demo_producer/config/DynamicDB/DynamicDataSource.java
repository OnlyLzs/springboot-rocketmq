package com.person.test.demo_producer.config.DynamicDB;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源设置
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

//    /**
//     * 决定使用哪个数据源之前需要把多个数据源的信息以及默认数据源信息配置好
//     *  重写父类
//     * @param defaultTargetDataSource 默认数据源
//     * @param targetDataSources       目标数据源
//     */
//    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
//        super.setDefaultTargetDataSource(defaultTargetDataSource);
//        super.setTargetDataSources(targetDataSources);
//        super.afterPropertiesSet();
//    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }


}
