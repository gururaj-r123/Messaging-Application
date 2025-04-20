package com.project.messaging.WebSocketService.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {

    private String id;
    private Long messageId;  // Auto-incremental ID
    private String senderId;
    private String receiverId;
    private String messageText;
    private LocalDateTime timestamp;
    private MessageStatus status;
}
