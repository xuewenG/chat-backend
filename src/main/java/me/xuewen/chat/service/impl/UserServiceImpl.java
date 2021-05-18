package me.xuewen.chat.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import me.xuewen.chat.constant.user.LoginTypeConstant;
import me.xuewen.chat.entity.User;
import me.xuewen.chat.exception.InvalidLoginTypeException;
import me.xuewen.chat.exception.InvalidPasswordException;
import me.xuewen.chat.exception.UserNotFoundException;
import me.xuewen.chat.mapper.UserMapper;
import me.xuewen.chat.service.UserService;
import me.xuewen.chat.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String credential, String password, Integer type) {
        User user;
        switch (type) {
            case LoginTypeConstant.EMAIL:
                user = userMapper.getByEmail(credential);
                break;
            case LoginTypeConstant.ACCOUNT:
                user = userMapper.getByAccount(credential);
                break;
            default:
                throw new InvalidLoginTypeException(type);
        }

        if (user == null) {
            throw new UserNotFoundException(credential);
        }

        if (!PasswordUtil.checkPassword(password, user.getPassword())) {
            throw new InvalidPasswordException(credential);
        }

        return user;
    }

    @Override
    public void register(String account, String password, String email, String nickname, String avatar, Timestamp birthday, Integer gender) {
        userMapper.register(account, password, email, nickname, avatar, birthday, gender);
    }

    @Override
    public User getByAccount(String account) {
        if (StrUtil.isBlank(account)) {
            return null;
        }
        return userMapper.getByAccount(account);
    }

    @Override
    public User getByEmail(String email) {
        if (StrUtil.isBlank(email) || !Validator.isEmail(email)) {
            return null;
        }
        return userMapper.getByEmail(email);
    }

    @Override
    public User getById(Integer id) {
        if (id == null) {
            return null;
        }
        return userMapper.getById(id);
    }
}
