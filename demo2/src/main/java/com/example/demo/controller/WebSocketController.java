package com.example.demo.controller;

import com.example.demo.entity.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.security.Principal;

/**
 * @author anonymity
 * @create 2018-10-18 14:04
 **/
@Controller
public class WebSocketController {

    @Resource
    private SimpMessagingTemplate template;

    @GetMapping("/")
    public String index(){
        return "hello websocket";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @MessageMapping("/sendPublicMessage")
    @SendTo("/topic/public")
    public ChatMessage sendPublicMessage(@Payload ChatMessage chatMessage){
        return chatMessage;
    }

    @MessageMapping("/sendPrivateMessage")
    public void sendPrivateMessage(@Payload ChatMessage chatMessage, Principal principal){
        chatMessage.setSender(principal.getName());
        template.convertAndSendToUser(chatMessage.getReceiver(), "/topic/chat", chatMessage);
    }

}
