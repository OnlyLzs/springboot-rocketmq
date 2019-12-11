package com.person.test.demo_producer.Schedule;

import com.person.test.demo_producer.pojo.Schedule;
import com.person.test.demo_producer.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableAsync
public class AsyncScheduledTasks {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncScheduledTasks.class);
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    ScheduleService scheduleService;

    @Async
    @Scheduled(cron = "0/1 * * * * ?")
    public void saveTimeSecond() {
        Schedule schedule = new Schedule();
        schedule.setName("每秒插入成功"+DATE_FORMAT.format(new Date()));
        schedule.setCron("0/1 * * * * ? *");
        Integer result = scheduleService.AddScheduledTask(schedule);
        LOGGER.info("每秒插入成功 主键值{}", schedule.getId());
    }

    @Async
    @Scheduled(cron = "0 0/1 * * * ?")
    public void saveTimeMinute() {
        Schedule schedule = new Schedule();
        schedule.setName("每分钟插入成功"+DATE_FORMAT.format(new Date()));
        schedule.setCron("0 0/1 * * * ? *");
        Integer result = scheduleService.AddScheduledTask(schedule);
        LOGGER.info("每分钟插入成功 主键值{}", schedule.getId());
    }




}
