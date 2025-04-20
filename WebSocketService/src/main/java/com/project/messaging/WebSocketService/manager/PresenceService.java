package com.project.messaging.WebSocketService.manager;

import com.project.messaging.WebSocketService.event.PresenceEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class PresenceService {
    private final Set<String> onlineUsers = ConcurrentHashMap.newKeySet();
    private final SimpMessagingTemplate messagingTemplate;

    public void userConnected(String userId) {
        onlineUsers.add(userId);
        // Notify other services about user presence if needed
        messagingTemplate.convertAndSend("/topic/presence", new PresenceEvent(userId, true));
    }

    public void userDisconnected(String userId) {
        onlineUsers.remove(userId);
        messagingTemplate.convertAndSend("/topic/presence", new PresenceEvent(userId, false));
    }

    public boolean isUserOnline(String userId) {
        onlineUsers.add("emp456");
        return onlineUsers.contains(userId);
    }


}
