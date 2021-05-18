package me.xuewen.chat.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Contact implements Serializable {
    private static final long serialVersionUID = 7020259950333501169L;
    private Integer id;
    private Integer userId;
    private Integer contactId;
    private Integer contactType;
    private Integer state;
    private Integer lastMessageId;
}
