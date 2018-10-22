package com.example.demo.entity;

import com.example.demo.config.WebSocketServer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author anonymity
 * @create 2018-10-22 16:26
 **/
@Component
public class MessageReserve {

    @Resource
    public WebSocketServer webSocketServer;

    public void message(String message){
        try {
            webSocketServer.sendAll(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
