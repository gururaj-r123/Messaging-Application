package com.project.messaging.ChatService.dto;

import com.project.messaging.ChatService.manager.MessageStatus;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Statusdto {

    private Long messageId;
    private MessageStatus status;
}
