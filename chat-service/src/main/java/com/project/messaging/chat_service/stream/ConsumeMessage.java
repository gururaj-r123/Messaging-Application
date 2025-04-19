package com.project.messaging.chat_service.stream;


import com.project.messaging.chat_service.dto.Statusdto;
import com.project.messaging.chat_service.handler.StatusHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class ConsumeMessage {

    @Autowired
    private StatusHandler statusHandler;

    @Bean
    public Consumer<Statusdto> input() {
        return message -> {
            System.out.println("Received: " + message.getStatus());
            statusHandler.handle(message);
        };
    }

}

