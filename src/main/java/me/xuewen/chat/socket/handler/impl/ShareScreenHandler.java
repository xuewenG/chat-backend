package me.xuewen.chat.socket.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import me.xuewen.chat.constant.contact.ContactType;
import me.xuewen.chat.constant.message.MessageType;
import me.xuewen.chat.constant.socket.SocketMessageType;
import me.xuewen.chat.entity.Message;
import me.xuewen.chat.service.MessageService;
import me.xuewen.chat.socket.ChatSocket;
import me.xuewen.chat.socket.bean.ChatMessage;
import me.xuewen.chat.socket.handler.SocketMessageHandler;
import me.xuewen.chat.util.JsonUtil;
import me.xuewen.chat.util.SocketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class ShareScreenHandler implements SocketMessageHandler {
    private static MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        ShareScreenHandler.messageService = messageService;
    }

    @Override
    public void handle(ChatSocket chatSocket, String data) throws JsonProcessingException {
        // 解析 chatMessage
        ChatMessage chatMessage = JsonUtil.parse(data, ChatMessage.class);
        Integer toId = chatMessage.getToId();
        // 将消息发送给好友
        ChatSocket friendSocket = chatSocket.getChatSocketByUserId(toId);
        if (friendSocket != null) {
            SocketUtil.sendMessage(friendSocket.getSession(), SocketMessageType.SHARE_SCREEN, null);
        }
        // 回传消息对象
//        SocketUtil.sendMessage(chatSocket.getSession(), SocketMessageType.SHARE_SCREEN, null);
    }
}
