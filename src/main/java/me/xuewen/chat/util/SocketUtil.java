package me.xuewen.chat.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.xuewen.chat.socket.bean.SocketMessage;

import javax.websocket.Session;
import java.io.Serializable;

public final class SocketUtil {
    public static void sendMessage(Session session, Integer type, Serializable data) {
        String json = "{}";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        SocketUtil.sendAsText(session, new SocketMessage(type, json));
    }

    public static void sendAsText(Session session, Serializable serializable) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(serializable);
            session.getAsyncRemote().sendText(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
