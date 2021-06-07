package me.xuewen.chat.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import me.xuewen.chat.constant.socket.SocketMessageType;
import me.xuewen.chat.socket.bean.SocketMessage;
import me.xuewen.chat.socket.handler.SocketMessageHandler;
import me.xuewen.chat.socket.handler.impl.MarkPrivateMessageReadHandler;
import me.xuewen.chat.socket.handler.impl.PrivateMessageHandler;
import me.xuewen.chat.socket.handler.impl.ShareScreenHandler;
import me.xuewen.chat.util.JsonUtil;
import me.xuewen.chat.util.SocketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@Component
@ServerEndpoint("/chatSocket/{token}")
public class ChatSocket {

    private final static ConcurrentHashMap<Integer, ChatSocket> chatSockets = new ConcurrentHashMap<>();
    private static RedisTemplate<String, Serializable> redisTemplate;
    private Session session;
    private Integer userId;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Serializable> redisTemplate) {
        ChatSocket.redisTemplate = redisTemplate;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "token") String token) throws IOException {
        this.session = session;
        log.debug("websocket request - token: " + token);
        Integer id = (Integer) redisTemplate.opsForValue().get(token);
        if (id == null) {
            log.error("websocket established failed, invalid token: " + token);
            session.close();
            return;
        }
        ChatSocket chatSocket = chatSockets.get(id);
        if (chatSocket != null) {
            chatSocket.session.close();
        }
        this.userId = id;
        chatSockets.put(userId, this);
        SocketUtil.sendMessage(session, SocketMessageType.CONNECTION_AUTH_SUCCEEDED, null);
        log.debug("websocket connection established, userId: " + this.userId);
    }

    @OnClose
    public void onClose() {
        log.debug("websocket connection destroyed, userId: " + this.userId);
        if (this.userId == null) {
            return;
        }
        chatSockets.remove(this.userId);
    }

    @OnMessage
    public void onMessage(String message) throws JsonProcessingException {
        log.debug("=======================================================================");
        log.debug("userId: " + this.userId);
        log.debug("message:" + message);
        // 解析 socket 消息
        SocketMessage socketMessage = JsonUtil.parse(message, SocketMessage.class);
        Integer messageType = socketMessage.getType();
        String data = socketMessage.getData();
        // 判断 socket 事件类型
        SocketMessageHandler socketMessageHandler = null;
        if (SocketMessageType.PRIVATE_MESSAGE.equals(messageType)) {
            socketMessageHandler = new PrivateMessageHandler();
        } else if (SocketMessageType.MARK_PRIVATE_MESSAGE_READ.equals(messageType)) {
            socketMessageHandler = new MarkPrivateMessageReadHandler();
        } else if (SocketMessageType.SHARE_SCREEN.equals(messageType)) {
            socketMessageHandler = new ShareScreenHandler();
        } else {
            // 忽略不支持的事件类型
            return;
        }
        // 处理 socket 事件
        socketMessageHandler.handle(this, data);
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    public Integer getUserId() {
        return this.userId;
    }

    public Session getSession() {
        return this.session;
    }

    public ChatSocket getChatSocketByUserId(Integer userId) {
        return ChatSocket.chatSockets.get(userId);
    }
}
