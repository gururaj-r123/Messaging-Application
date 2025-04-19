package com.project.messaging.chat_service.dto;

import com.project.messaging.chat_service.manager.MessageStatus;
import com.project.messaging.chat_service.model.Message;

public class SenderRequest {
    private MessageStatus status;

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}