package com.person.test.demo_comsumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class DemoComsumerApplication {

    @Value("${rq.topic}")
    private static String topic;
    @Value("${rq.consumerGroup}")
    private static String consumerGroup;

    public static void main(String[] args) {
        SpringApplication.run(DemoComsumerApplication.class, args);
    }

   // @Slf4j
    @Service
    @RocketMQMessageListener(topic = "topic201912021032", consumerGroup = "Lzs-ComsumerGroup")
    public class MyConsumer1 implements RocketMQListener<String> {
        public void onMessage(String message) {
           // log.info("received message: {}", message);
            System.out.printf("received message: {%s} \n ", message);
        }
    }

}
