package com.example.demo.controller;

import com.example.demo.service.ChatService;
import com.example.demo.entity.IMMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author anonymity
 * @create 2018-10-10 15:25
 **/
@RestController
@RequestMapping(value = "/chat")
public class ChatController {

    @Resource
    private ChatService chatService;

    /**
     * 发送消息
     */
    @PostMapping(value = "/sendOne")
    public String sendOne(@RequestBody IMMessage imMessage){
        return chatService.sendOne(imMessage);
    }
}
