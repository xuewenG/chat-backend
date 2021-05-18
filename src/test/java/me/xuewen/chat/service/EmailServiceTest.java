package me.xuewen.chat.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailServiceTest {

    @Autowired
    EmailService emailService;

    @Test
    void testSendMail() {
        emailService.sendMail("test@example.com", "邮箱验证码", "您的邮箱验证码是：123456。");
    }
}
