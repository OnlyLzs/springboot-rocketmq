package com.person.test.demo_producer.service.impl;

import com.person.test.demo_producer.mapper.ScheduleMapper;
import com.person.test.demo_producer.pojo.Schedule;
import com.person.test.demo_producer.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ScheduledServiceImpl implements ScheduleService {

    @Resource
    ScheduleMapper scheduleMapper;

    @Override
    public Integer AddScheduledTask(Schedule schedule) {
        int result = scheduleMapper.insert(schedule);
        return result;
    }
}
