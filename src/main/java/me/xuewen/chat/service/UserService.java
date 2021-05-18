package me.xuewen.chat.service;

import me.xuewen.chat.entity.User;

import java.sql.Timestamp;

public interface UserService {
    User getById(Integer id);

    User getByEmail(String email);

    User getByAccount(String account);

    User login(String credential, String password, Integer type);

    void register(String account, String password, String email, String nickname, String avatar, Timestamp birthday, Integer gender);
}
