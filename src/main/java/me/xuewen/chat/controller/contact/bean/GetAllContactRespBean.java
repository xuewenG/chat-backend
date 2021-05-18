package me.xuewen.chat.controller.contact.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.xuewen.chat.entity.Contact;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllContactRespBean implements Serializable {
    private static final long serialVersionUID = 2387572392027995579L;
    private List<ContactBean> contactList;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class ContactBean extends Contact {
        private static final long serialVersionUID = 1661300523292590565L;
        private String account;
        private String nickname;
        private String avatar;
        private Integer newMessageCount;
    }
}
