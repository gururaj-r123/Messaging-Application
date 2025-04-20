package com.project.messaging.WebSocketService.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PresenceEvent {
    private final String userId;
    private final boolean online;
}
