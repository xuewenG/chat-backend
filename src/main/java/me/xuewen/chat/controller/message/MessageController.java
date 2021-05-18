package me.xuewen.chat.controller.message;

import me.xuewen.chat.controller.message.bean.GetAllMessageRespBean;
import me.xuewen.chat.entity.Response;
import me.xuewen.chat.service.MessageService;
import me.xuewen.chat.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;
    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    @GetMapping("getAllMessage")
    public Response getAllMessage(@RequestParam String token) {
        Integer userId = (Integer) redisTemplate.opsForValue().get(token);
        List<GetAllMessageRespBean.ContactMessage> messageList =
                messageService.getAllMessage(userId);
        return ResultUtil.success(new GetAllMessageRespBean(messageList));
    }
}
