package me.xuewen.chat.controller.contact;

import me.xuewen.chat.constant.contact.ContactType;
import me.xuewen.chat.controller.contact.bean.GetAllContactRespBean;
import me.xuewen.chat.entity.Contact;
import me.xuewen.chat.entity.Response;
import me.xuewen.chat.entity.User;
import me.xuewen.chat.service.ContactService;
import me.xuewen.chat.service.MessageService;
import me.xuewen.chat.service.UserService;
import me.xuewen.chat.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    ContactService contactService;
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    @GetMapping("getAllContact")
    public Response getAllContact(@RequestParam String token) {
        List<GetAllContactRespBean.ContactBean> contactBeanList = new ArrayList<>();
        Integer userId = (Integer) redisTemplate.opsForValue().get(token);
        List<Contact> contactList = contactService.getAllContactByUserId(userId);
        contactList.forEach(contact -> {
            if (contact.getContactType().equals(ContactType.USER)) {
                User user = userService.getById(contact.getContactId());
                GetAllContactRespBean.ContactBean contactBean = new GetAllContactRespBean.ContactBean();
                BeanUtils.copyProperties(contact, contactBean);
                contactBean.setAccount(user.getAccount());
                contactBean.setNickname(user.getNickname());
                contactBean.setAvatar(user.getAvatar());
                contactBean.setNewMessageCount(1);
                contactBeanList.add(contactBean);
            }
        });
        return ResultUtil.success(new GetAllContactRespBean(contactBeanList));
    }
}
