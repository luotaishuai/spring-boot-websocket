package com.example.demo.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author anonymity
 * @create 2018-10-15 14:06
 **/
@Slf4j
@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocketServer {

    /**
     * 静态变量，用来记录当前在线连接数，应该设计为线程安全的
     */
    private static int onlineCount = 0;
    /**
     * 存放每个客户对应的WebSocket对象，key为userId，ConcurrentHashMap为线程安全map
     */
    private static Map<Long, WebSocketServer> clients = new ConcurrentHashMap<>();
    /**
     * 客户端连接会话，需要通过他来给客户端发送数据
     */
    private Session session;
    /**
     * 接收的userId
     */
    private Long userId;

    /**
     * 连接成功建立调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("userId") Long userId, Session session) {
        this.userId = userId;
        this.session = session;

        log.info("现在来连接的用户ID：" + userId);
        addOnlineCount();
        log.info("有新的连接加入！当前在线人数" + getOnlineCount());

        clients.put(userId, this);
    }

    /**
     * 发生错误调用的方法
     */
    @OnError
    public void onError(Throwable error) {
        log.error("WebSocket error", error);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        subOnlineCount();
        clients.remove(userId);
        log.info("有连接关闭！当前在线人数" + getOnlineCount());
    }

    /**
     * 收到客户端的消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message) {
        try {
            log.info("来自客户端消息：" + message);

            JSONObject jsonObject = JSON.parseObject(message);
            Long receiverId = jsonObject.getLong("receiverId");

            // 如果接受者Id是0，就是发给所有人
            if (receiverId.equals(0)) {
                sendAll(message);
            } else {
                sendOne(message, receiverId);
            }

        } catch (Exception e) {
            log.error("onMessage error", e);
        }
    }

    public static void sendOne(String message, Long userId) throws IOException {
        for (WebSocketServer item : clients.values()) {
            if (item.userId.equals(userId)) {
                item.session.getAsyncRemote().sendText(message);
                break;
            }
        }
    }

    public static void sendAll(String message) throws IOException {
        for (WebSocketServer item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
