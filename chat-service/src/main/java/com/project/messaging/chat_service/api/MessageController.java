package com.project.messaging.chat_service.api;


import com.project.messaging.chat_service.dto.MessageRequest;
import com.project.messaging.chat_service.dto.SenderRequest;
import com.project.messaging.chat_service.manager.MessageService;
import com.project.messaging.chat_service.model.Message;
import com.project.messaging.chat_service.manager.MessageStatus;
import com.project.messaging.chat_service.repository.MessageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;
    private final MessageRepository messageRepository;

    public MessageController(MessageService messageService, MessageRepository messageRepository) {
        this.messageService = messageService;
        this.messageRepository = messageRepository;
    }

    // Save a new message
    @PostMapping
    public ResponseEntity<Message> saveMessage(@RequestBody MessageRequest messageRequest) {
        Message savedMessage = messageService.saveMessage(messageRequest);
        return ResponseEntity.ok(savedMessage);
    }

    // Get messages by senderId
    @GetMapping("/sender/{senderId}")
    public ResponseEntity<List<Message>> getMessagesBySenderId(@PathVariable String senderId) {
        List<Message> messages = messageRepository.findBySenderId(senderId);
        return ResponseEntity.ok(messages);
    }

    // Get messages by receiverId
    @GetMapping("/receiver/{receiverId}")
    public ResponseEntity<List<Message>> getMessagesByReceiverId(@PathVariable String receiverId) {
        List<Message> messages = messageRepository.findByReceiverId(receiverId);
        return ResponseEntity.ok(messages);
    }

    // Get messages by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Message>> getMessagesByStatus(@PathVariable MessageStatus status) {
        List<Message> messages = messageRepository.findByStatus(status);
        return ResponseEntity.ok(messages);
    }

    // Get messages by senderId and receiverId
    @GetMapping("/conversation")
    public ResponseEntity<List<Message>> getMessagesBySenderAndReceiver(
            @RequestParam String senderId,
            @RequestParam String receiverId) {
        List<Message> messages = messageRepository.findBySenderIdAndReceiverId(senderId, receiverId);
        return ResponseEntity.ok(messages);
    }

    // Update message status by messageId
    @PatchMapping("/{messageId}/status")
    public ResponseEntity<String> updateMessageStatus(
            @PathVariable Long messageId,
            @RequestBody SenderRequest statusUpdateRequest) {
        Message message = messageRepository.findByMessageId(messageId);
        if (message == null) {
            return ResponseEntity.badRequest().body("Message with ID " + messageId + " not found");
        }
        messageRepository.updateStatusByMessageId(messageId, message.getStatus());
        return ResponseEntity.ok("Message status updated successfully");
    }
}