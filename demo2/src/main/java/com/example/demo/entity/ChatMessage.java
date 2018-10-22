package com.example.demo.entity;

import lombok.Data;

/**
 * @author anonymity
 * @create 2018-10-18 16:25
 **/
@Data
public class ChatMessage {
    private String content;
    private String sender;
    private String receiver;
}
