package com.project.messaging.WebSocketService.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.messaging.WebSocketService.dto.Message;
import com.project.messaging.WebSocketService.dto.MessageStatus;
import com.project.messaging.WebSocketService.dto.Statusdto;
import com.project.messaging.WebSocketService.manager.PresenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.io.DataInput;
import java.io.IOException;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class MessageConsumer {

    private final PresenceService presenceService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    private StreamBridge streamBridge;


    @Bean
    public Consumer<Message> input() {
        return message -> {
            try {
                System.out.println("Received: " + message.getStatus());


                if (presenceService.isUserOnline(message.getReceiverId())) {
                    messagingTemplate.convertAndSendToUser(
                            message.getReceiverId(),
                            "/queue/messages",
                            message.getMessageText()
                    );
                    streamBridge.send("output", new Statusdto(message.getMessageId(), MessageStatus.DELIVERED));

                } else {
                    throw new RuntimeException("User offline - requeue message");
                }
            } catch (Exception e) {
                throw new RuntimeException("Message processing failed", e);
            }
        };
    }


}