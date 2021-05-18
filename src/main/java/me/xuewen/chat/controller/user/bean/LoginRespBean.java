package me.xuewen.chat.controller.user.bean;

import lombok.Data;
import me.xuewen.chat.entity.User;

import java.io.Serializable;

@Data
public class LoginRespBean implements Serializable {
    private static final long serialVersionUID = 6021622123582059598L;
    public User user;
    public String token;
}
