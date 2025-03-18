package com.tutorial.websocket.listeners;

import com.tutorial.websocket.dto.ChatMessageDto;
import com.tutorial.websocket.enums.MessageTypeEnum;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebsocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if(username != null) {
            log.info("User disconnected: {}", username);
            var chatMessage = ChatMessageDto.builder()
            .content("Hey Ngar Thur P")
            .type(MessageTypeEnum.LEAVE)
            .sender(username)
            .build();
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}