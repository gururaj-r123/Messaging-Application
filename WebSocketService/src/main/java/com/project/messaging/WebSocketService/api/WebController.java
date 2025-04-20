package com.project.messaging.WebSocketService.api;

import com.project.messaging.WebSocketService.manager.PresenceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller
public class WebController {

    @Autowired
    PresenceService presenceService;

    @MessageMapping("/register")
    public void registerUser(@Payload String userId) {
        System.out.println("the registed userId:  " + userId);
        presenceService.userConnected(userId);
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userId = headerAccessor.getFirstNativeHeader("userId");
        if (userId != null) {
            presenceService.userDisconnected(userId);
        }
    }
}
