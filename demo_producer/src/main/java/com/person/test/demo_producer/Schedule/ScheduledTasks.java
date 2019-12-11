package com.person.test.demo_producer.Schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 定时任务 现在都是同一个线程在执行任务
 * https://spring.io/guides/gs/scheduling-tasks/
 */
@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 在Scheduled当特定的方法运行注解定义。注意：此示例使用fixedRate，
     * 它指定从每次调用的开始时间开始测量的方法调用之间的间隔。
     * 还有其他选项，例如fixedDelay，它指定从任务完成开始测量的两次调用之间的间隔。
     * 您还可以使用@Scheduled(cron=". . .")表达式进行更复杂的任务调度。
     */
    //@Scheduled(fixedRate = 10000)
    public void reportCurrentTime() throws InterruptedException {
        Thread.sleep(1000);
        logger.info("时间汇报1 {}", dateFormat.format(new Date()));
    }

    //@Scheduled(fixedDelay = 1000)
    public void reportCurrentTime2() throws InterruptedException {
        Thread.sleep(1000);
        logger.info("时间汇报2 {}", dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void reportHours() throws InterruptedException {
        //Thread.sleep(1000);
        logger.info("小时汇报 {}", dateFormat.format(new Date()));
    }


}
