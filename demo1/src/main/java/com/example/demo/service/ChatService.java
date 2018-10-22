package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.config.WebSocketServer;
import com.example.demo.entity.IMMessage;
import org.springframework.stereotype.Service;

/**
 * @author anonymity
 * @create 2018-10-16 11:21
 **/
@Service
public class ChatService {

    public String sendOne(IMMessage message){
        try {
            String msg = JSON.toJSONString(message);
            WebSocketServer.sendOne(msg, message.getReceiverId());
            return "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
