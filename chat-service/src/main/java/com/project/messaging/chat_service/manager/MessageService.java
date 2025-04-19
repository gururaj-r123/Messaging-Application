package com.project.messaging.chat_service.manager;


import com.project.messaging.chat_service.api.MessageController;
import com.project.messaging.chat_service.dto.MessageRequest;
import com.project.messaging.chat_service.model.Message;
import com.project.messaging.chat_service.repository.MessageRepository;
import com.project.messaging.chat_service.stream.SendMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final SendMessage sendMessage;

    public MessageService(MessageRepository messageRepository,
                          SequenceGeneratorService sequenceGeneratorService, SendMessage sendMessage) {
        this.messageRepository = messageRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
        this.sendMessage = sendMessage;
    }

    public Message saveMessage(MessageRequest messageRequest) {
        Message message = new Message();
        message.setSenderId(messageRequest.getSenderId());
        message.setReceiverId(messageRequest.getReceiverId());
        message.setMessageText(messageRequest.getMessageText());
        message.setTimestamp(LocalDateTime.now());
        message.setStatus(MessageStatus.SENT);
        message.setMessageId(sequenceGeneratorService.generateSequence("message_sequence"));
        sendMessage.send(message);
        return messageRepository.save(message);
    }
}