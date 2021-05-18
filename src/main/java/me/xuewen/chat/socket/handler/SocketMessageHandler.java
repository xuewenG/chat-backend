package me.xuewen.chat.socket.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import me.xuewen.chat.socket.ChatSocket;

public interface SocketMessageHandler {
    void handle(ChatSocket chatSocket, String data) throws JsonProcessingException;
}
