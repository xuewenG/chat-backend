package me.xuewen.chat.controller.user.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterRespBean implements Serializable {
    private static final long serialVersionUID = 3313012978132126083L;
    private String account;
}
