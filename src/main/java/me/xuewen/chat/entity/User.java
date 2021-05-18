package me.xuewen.chat.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
    private static final long serialVersionUID = 2373168391766228885L;
    private Integer id;
    private String account;
    private String nickname;
    private String email;
    private Integer gender;
    private Timestamp birthday;
    private String password;
    private String avatar;
}
