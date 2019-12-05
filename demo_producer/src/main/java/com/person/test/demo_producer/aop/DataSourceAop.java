package com.person.test.demo_producer.aop;

import com.person.test.demo_producer.annotation.CurDataSource;
import com.person.test.demo_producer.config.DynamicDB.DataSourceContextHolder;
import com.person.test.demo_producer.config.DynamicDB.DynamicDataSource;
import org.apache.juli.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 数据源切面类 根据注解切换数据源
 */


@Aspect
@Component
public class DataSourceAop implements Ordered {

    private Logger logger = LoggerFactory.getLogger(DataSourceAop.class);

    @Pointcut("@annotation(com.person.test.demo_producer.annotation.CurDataSource)")
    public void CurDataSourcePointCut() {}

    @Around("CurDataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        CurDataSource ds = method.getAnnotation(CurDataSource.class);
        // 通过判断 DataSource 中的值来判断当前方法应用哪个数据源
        DataSourceContextHolder.setDataSource(ds.name());
        System.out.println("当前数据源: " + ds.name());
        logger.debug("set datasource is " + ds.name());
        try {
            return point.proceed();
        } finally {
            DataSourceContextHolder.clearDataSource();
            logger.debug("clean datasource");
        }
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
