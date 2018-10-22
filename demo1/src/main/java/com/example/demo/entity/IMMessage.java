package com.example.demo.entity;

import lombok.Data;

/**
 * @author anonymity
 * @create 2018-10-16 11:19
 **/
@Data
public class IMMessage {

    private Long senderId;
    private Long receiverId; // 0 所有人
    private String content;
}
