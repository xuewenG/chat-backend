package me.xuewen.chat.controller.message.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.xuewen.chat.entity.Message;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllMessageRespBean implements Serializable {
    private static final long serialVersionUID = 2696096292737558909L;
    private List<ContactMessage> contactMessageList;

    @Data
    public static class ContactMessage {
        private Integer contactId;
        private Integer contactType;
        private List<Message> messageList;
    }
}
