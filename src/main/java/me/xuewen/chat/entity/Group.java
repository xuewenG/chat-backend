package me.xuewen.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group implements Serializable {
    private static final long serialVersionUID = 2613170725656036170L;
    private Integer id;
    private String account;
    private String nickname;
    private String introduction;
    private String avatar;
}
