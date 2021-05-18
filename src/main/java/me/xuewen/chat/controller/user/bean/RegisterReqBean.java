package me.xuewen.chat.controller.user.bean;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class RegisterReqBean implements Serializable {
    private static final long serialVersionUID = 222161056973928390L;
    @NotBlank(message = "邮箱不能为空")
    private String email;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "昵称不能为空")
    private String nickname;
    //    @NotNull(message = "性别不能为空")
    private Integer gender;
    //    @NotNull(message = "生日不能为空")
    private Timestamp birthday;
    @NotBlank(message = "验证码不能为空")
    private String verifyCode;
    private String avatar;
}
