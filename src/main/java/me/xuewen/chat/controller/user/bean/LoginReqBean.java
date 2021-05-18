package me.xuewen.chat.controller.user.bean;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LoginReqBean implements Serializable {
    private static final long serialVersionUID = 2632237037829682950L;
    @NotBlank(message = "凭证不能为空")
    private String credential;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotNull(message = "类型不能为空")
    private Integer type;
}
