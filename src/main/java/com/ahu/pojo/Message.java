package com.ahu.pojo;

import lombok.Data;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class Message {
    private Integer messageId; // 消息ID

    private String receiver; // 收件人邮箱地址

    private String receiveBoat; // 收件人所在的船

    // private String sendBoat; // 发件人所在的船

    private String sender; // 发件人邮箱地址

    private String subject; // 邮箱主题

    private String content; // 邮箱内容

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime; // 发送时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receiveTime; // 收件时间

    private Integer favorite;// 收藏

    private Integer senderId; // 发件人ID

    private Integer receiverId; // 收件人ID

    private Integer status; // 消息状态：收件，发件，草稿，回收，已发送

    private String attachement; // 附件
}