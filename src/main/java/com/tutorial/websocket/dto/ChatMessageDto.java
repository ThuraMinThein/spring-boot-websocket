package com.tutorial.websocket.dto;

import com.tutorial.websocket.enums.MessageTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessageDto {

    private String content;
    private String sender;
    private MessageTypeEnum type;

}

