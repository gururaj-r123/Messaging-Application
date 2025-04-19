package com.project.messaging.chat_service.manager;


import com.project.messaging.chat_service.model.Message;
import com.project.messaging.chat_service.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public MessageService(MessageRepository messageRepository, 
                         SequenceGeneratorService sequenceGeneratorService) {
        this.messageRepository = messageRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    public Message saveMessage(Message message) {
        message.setMessageId(sequenceGeneratorService.generateSequence("message_sequence"));
        return messageRepository.save(message);
    }
}