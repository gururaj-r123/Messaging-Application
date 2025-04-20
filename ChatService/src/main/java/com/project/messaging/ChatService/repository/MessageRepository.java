package com.project.messaging.ChatService.repository;


import com.project.messaging.ChatService.manager.MessageStatus;
import com.project.messaging.ChatService.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    // Save message (inherited from MongoRepository)
    // Message save(Message message);

    // Get messages by senderId
    List<Message> findBySenderId(String senderId);

    // Get messages by receiverId
    List<Message> findByReceiverId(String receiverId);

    // Get messages by status
    List<Message> findByStatus(MessageStatus status);

    // Get messages by senderId and receiverId
    List<Message> findBySenderIdAndReceiverId(String senderId, String receiverId);

    // Update message status by messageId
    @Query("{ 'messageId' : ?0 }")
    @Update("{ '$set' : { 'status' : ?1 } }")
    void updateStatusByMessageId(Long messageId, MessageStatus status);

    // Find message by messageId
    Message findByMessageId(Long messageId);
}