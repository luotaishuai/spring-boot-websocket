package com.example.demo.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author anonymity
 * @create 2018-10-22 16:54
 **/
@Controller
public class RedisController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/redis")
    @ResponseBody // json
    public String redis(String channel, String message) {
        redisTemplate.convertAndSend(channel, message);
        return "success";
    }
}
