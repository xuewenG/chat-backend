package me.xuewen.chat.service;


import me.xuewen.chat.controller.message.bean.GetAllMessageRespBean;
import me.xuewen.chat.entity.Message;

import java.sql.Timestamp;
import java.util.List;

public interface MessageService {
    Message addMessage(Integer fromId, Integer toId,
                       Integer contactType,
                       Integer type, String content, Timestamp timestamp);

    List<GetAllMessageRespBean.ContactMessage> getAllMessage(Integer userId);

    void readAllPrivateMessage(Integer userId, Integer friendId);
}
