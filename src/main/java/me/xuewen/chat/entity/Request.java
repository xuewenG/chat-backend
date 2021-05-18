package me.xuewen.chat.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Request implements Serializable {
    private static final long serialVersionUID = 2312710835957350760L;
    private String token;
}
