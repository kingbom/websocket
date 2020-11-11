package com.kingbom.controller;

import com.kingbom.model.MessageModel;
import com.kingbom.storage.UserStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessagingTemplate simpleMessageTemplate;

    @MessageMapping("/chat/{to}")
    public void sentMessage(@DestinationVariable String to, MessageModel message) {
        log.info("sent message :{} to : {}", message, to);
        boolean isExists = UserStorage.getInstance().getUsers().contains(to);
        if (isExists) {
            simpleMessageTemplate.convertAndSend(String.format("/topic/message/%s", to), message);
        }
    }
}
