package com.project.messaging.chat_service.handler;

import com.project.messaging.chat_service.dto.Statusdto;
import com.project.messaging.chat_service.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatusHandler {

    @Autowired
    private MessageRepository messageRepository;

    public void handle(Statusdto message) {
        messageRepository.updateStatusByMessageId(message.getMessageId(), message.getStatus());
    }
}
