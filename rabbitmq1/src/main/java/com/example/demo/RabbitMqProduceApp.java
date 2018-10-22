package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 使用rabbitmq订阅发布消息
 * @author anonymity
 * @create 2018-10-19 15:20
 **/
@SpringBootApplication
public class RabbitMqProduceApp {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqProduceApp.class, args);
    }
}
