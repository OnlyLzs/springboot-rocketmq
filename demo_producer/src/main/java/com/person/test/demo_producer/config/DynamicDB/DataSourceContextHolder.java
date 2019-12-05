package com.person.test.demo_producer.config.DynamicDB;

/**
 * dataSource的上下文
 */
public class DataSourceContextHolder {


    /**
     * ThreadLocal 用于提供线程局部变量，在多线程环境可以保证各个线程里的变量独立于其它线程里的变量。
     * 也就是说 ThreadLocal 可以为每个线程创建一个【单独的变量副本】，相当于线程的 private static 类型变量。
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     *  设置数据源名称 在DynamicDataSourceConfig中已经将数据源名称和数据源以key value形式放入到targetDataSources中
     * @param dataSourceName
     */
    public static void setDataSource(String dataSourceName) {
        System.out.printf("数据源切换为：%s\n", dataSourceName);
        CONTEXT_HOLDER.set(dataSourceName);
    }

    public static String getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }

}
