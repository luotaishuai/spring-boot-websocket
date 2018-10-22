package com.example.demo.topic;

import com.example.demo.config.WebSocketServer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author anonymity
 * @create 2018-10-19 18:06
 **/
@Component
@RabbitListener(queues = "direct")
public class DirectReceiver {

    /**
     * 对接收到的消息处理
     * @param message
     * @throws InterruptedException
     */
    @RabbitHandler
    public void process(String message) throws InterruptedException {
        System.err.println("direct 接收到的消息：" + message);

        for (WebSocketServer server : WebSocketServer.webSocketSet) {
            try {
                server.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
