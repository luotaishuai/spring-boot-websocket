package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author anonymity
 * @create 2018-10-19 18:12
 **/
@Controller
public class RabbitMqController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    @ResponseBody
    public void send(){
        String message = "xxxxxxxxxxxxxxx";
        rabbitTemplate.convertAndSend("directExchange", "rabbit.msg", message);
    }

    @GetMapping("/sendT")
    @ResponseBody
    public void sendTopic(){
        User user = new User(1, "luo", 18);
        System.err.println(user.toString());

        rabbitTemplate.convertAndSend("directExchange", "rabbit.msg", user.toString());
    }

}
