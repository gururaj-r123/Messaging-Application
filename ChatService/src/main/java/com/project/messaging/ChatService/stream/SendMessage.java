package com.project.messaging.ChatService.stream;

import com.project.messaging.ChatService.model.Message;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
public class SendMessage {

    private final StreamBridge streamBridge;

    public SendMessage(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void send(Message message) {
        streamBridge.send("output", message);
    }
}

