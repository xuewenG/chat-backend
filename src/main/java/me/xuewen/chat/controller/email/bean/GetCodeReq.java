package me.xuewen.chat.controller.email.bean;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class GetCodeReq implements Serializable {
    private static final long serialVersionUID = 2706971938612769920L;
    @NotBlank
    private String email;
}
