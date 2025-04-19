package com.project.messaging.chat_service.stream;

import com.project.messaging.chat_service.model.Message;
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

