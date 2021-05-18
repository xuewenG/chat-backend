package me.xuewen.chat.socket.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import me.xuewen.chat.service.MessageService;
import me.xuewen.chat.socket.ChatSocket;
import me.xuewen.chat.socket.handler.SocketMessageHandler;
import me.xuewen.chat.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarkPrivateMessageReadHandler implements SocketMessageHandler {
    private static MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        MarkPrivateMessageReadHandler.messageService = messageService;
    }

    @Override
    public void handle(ChatSocket chatSocket, String data) throws JsonProcessingException {
        Integer friendId = JsonUtil.parse(data, Integer.class);
        messageService.readAllPrivateMessage(chatSocket.getUserId(), friendId);
    }
}
