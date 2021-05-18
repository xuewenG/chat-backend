package me.xuewen.chat.controller.user.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAvatarReqBean implements Serializable {
    private static final long serialVersionUID = 255276015679203103L;
    private String avatar;
}
