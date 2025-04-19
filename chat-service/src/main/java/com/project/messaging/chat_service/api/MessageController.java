package com.project.messaging.chat_service.api;


import com.project.messaging.chat_service.manager.MessageService;
import com.project.messaging.chat_service.model.Message;
import com.project.messaging.chat_service.model.Message.MessageStatus;
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
        Message message = new Message();
        message.setSenderId(messageRequest.getSenderId());
        message.setReceiverId(messageRequest.getReceiverId());
        message.setMessageText(messageRequest.getMessageText());
        message.setTimestamp(LocalDateTime.now());
        message.setStatus(Message.MessageStatus.SENT);

        
        Message savedMessage = messageService.saveMessage(message);
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
            @RequestBody StatusUpdateRequest statusUpdateRequest) {
        Message message = messageRepository.findByMessageId(messageId);
        if (message == null) {
            return ResponseEntity.badRequest().body("Message with ID " + messageId + " not found");
        }
        messageRepository.updateStatusByMessageId(messageId, statusUpdateRequest.getStatus());
        return ResponseEntity.ok("Message status updated successfully");
    }

    // Request DTO for creating messages
    public static class MessageRequest {
        private String senderId;
        private String receiverId;
        private String messageText;

        public String getSenderId() {
            return senderId;
        }

        public void setSenderId(String senderId) {
            this.senderId = senderId;
        }

        public String getReceiverId() {
            return receiverId;
        }

        public void setReceiverId(String receiverId) {
            this.receiverId = receiverId;
        }

        public String getMessageText() {
            return messageText;
        }

        public void setMessageText(String messageText) {
            this.messageText = messageText;
        }
    }

    // Request DTO for updating status
    public static class StatusUpdateRequest {
        private MessageStatus status;

        public MessageStatus getStatus() {
            return status;
        }

        public void setStatus(MessageStatus status) {
            this.status = status;
        }
    }
}