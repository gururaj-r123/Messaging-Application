package com.project.messaging.ChatService.dto;

import com.project.messaging.ChatService.manager.MessageStatus;
import com.project.messaging.ChatService.model.Message;

public class SenderRequest {
    private MessageStatus status;

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}