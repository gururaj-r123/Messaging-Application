package com.project.messaging.chat_service.dto;

import com.project.messaging.chat_service.manager.MessageStatus;
import com.project.messaging.chat_service.model.Message;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Statusdto {

    private Long messageId;
    private MessageStatus status;
}
