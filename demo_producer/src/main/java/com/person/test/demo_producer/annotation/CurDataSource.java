package com.person.test.demo_producer.annotation;

import java.lang.annotation.*;

/**
 * 指定使用的数据源
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurDataSource {
    String name() default "";
}
