package com.project.messaging.ChatService.handler;

import com.project.messaging.ChatService.dto.Statusdto;
import com.project.messaging.ChatService.repository.MessageRepository;
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
