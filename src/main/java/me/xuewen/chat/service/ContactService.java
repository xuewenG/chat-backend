package me.xuewen.chat.service;


import me.xuewen.chat.entity.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAllContactByUserId(Integer userId);
}
