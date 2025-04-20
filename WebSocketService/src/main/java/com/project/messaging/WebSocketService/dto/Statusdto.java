package com.project.messaging.WebSocketService.dto;

import lombok.Data;

@Data
public class Statusdto {

    private Long messageId;
    private MessageStatus status;

    public Statusdto(Long messageId, MessageStatus messageStatus) {
        this.messageId = messageId;
        this.status = messageStatus;
    }
}