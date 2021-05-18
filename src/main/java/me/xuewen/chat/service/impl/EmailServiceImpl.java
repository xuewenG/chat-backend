package me.xuewen.chat.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import me.xuewen.chat.mapper.UserMapper;
import me.xuewen.chat.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserMapper userMapper;
    @Value("${spring.mail.from}")
    private String mailFrom;

    @Async
    @Override
    public void sendMail(String toEmail, String subject, String content) {
        if (StrUtil.hasBlank(toEmail, subject, content) || !Validator.isEmail(toEmail)) {
            return;
        }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    @Override
    public boolean isExist(String email) {
        if (StrUtil.isBlank(email) || !Validator.isEmail(email)) {
            return false;
        }
        return userMapper.getByEmail(email) != null;
    }
}
