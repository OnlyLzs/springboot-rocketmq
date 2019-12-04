package com.person.test.demo_producer.controller;

import com.person.test.demo_producer.pojo.User;
import com.person.test.demo_producer.service.UserService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Controller
public class ProducerController {

    @Resource
    RocketMQTemplate rocketMQTemplate;

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/producerTest1")
    public String producerTest1 (String param1) {
        Random random = new Random();
        int version = random.nextInt(10000);
        rocketMQTemplate.convertAndSend("topic201912021032","我是话题topic201912021032-"+version);
        return version+"";
    }

    @ResponseBody
    @RequestMapping("/queryUser")
    public List<User> queryUser(){
        return userService.queryAll();
    }

}
