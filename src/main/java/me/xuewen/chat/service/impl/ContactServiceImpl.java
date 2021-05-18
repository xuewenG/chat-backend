package me.xuewen.chat.service.impl;

import me.xuewen.chat.entity.Contact;
import me.xuewen.chat.mapper.ContactMapper;
import me.xuewen.chat.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactMapper contactMapper;

    @Override
    public List<Contact> getAllContactByUserId(Integer userId) {
        return contactMapper.getAllContactByUserId(userId);
    }
}
