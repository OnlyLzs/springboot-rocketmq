package com.person.test.demo_producer;

import com.person.test.demo_producer.config.DynamicDB.DynamicDataSource;
import com.person.test.demo_producer.config.DynamicDB.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * 动态数据源配置,需要将自有的配置依赖(DynamicDataSourceConfig),将原有的依赖去除(DataSourceAutoConfiguration)
 * 自己将DynamicDataSourceConfig 错写成了DynamicDataSource 导致targetDataSources 注入不进去
 */
@Import({DynamicDataSourceConfig.class})
//@MapperScan("com.person.test.demo_producer.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DemoProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProducerApplication.class, args);
    }

}
