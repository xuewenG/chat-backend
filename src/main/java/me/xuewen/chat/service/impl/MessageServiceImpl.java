package me.xuewen.chat.service.impl;

import me.xuewen.chat.controller.message.bean.GetAllMessageRespBean;
import me.xuewen.chat.entity.Contact;
import me.xuewen.chat.entity.Message;
import me.xuewen.chat.mapper.ContactMapper;
import me.xuewen.chat.mapper.MessageMapper;
import me.xuewen.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    ContactMapper contactMapper;

    @Override
    public Message addMessage(Integer fromId, Integer toId,
                              Integer contactType,
                              Integer type, String content, Timestamp timestamp) {
        Message message = new Message(null, fromId, toId, contactType, type, content, timestamp);
        messageMapper.insertMessage(message);
        return message;
    }

    @Override
    public List<GetAllMessageRespBean.ContactMessage> getAllMessage(Integer userId) {
        List<GetAllMessageRespBean.ContactMessage> contactMessageList =
                new ArrayList<>();
        List<Contact> contactList = contactMapper.getAllContactByUserId(userId);
        contactList.forEach(contact -> {
            GetAllMessageRespBean.ContactMessage contactMessage =
                    new GetAllMessageRespBean.ContactMessage();
            contactMessage.setContactId(contact.getContactId());
            contactMessage.setContactType(contact.getContactType());
            List<Message> messageList = messageMapper.selectMessage(contact.getContactType(), userId, contact.getContactId());
            contactMessage.setMessageList(messageList);
            contactMessageList.add(contactMessage);
        });
        return contactMessageList;
    }


    @Override
    public void readAllPrivateMessage(Integer userId, Integer friendId) {
        messageMapper.readAllPrivateMessage(userId, friendId);
    }

}
