package me.xuewen.chat.util;

import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PasswordUtilTest {
    @Test
    public void testEncode() {
        String password = "pwd";
        String encodedPassword = "a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8";
        Assert.isTrue(PasswordUtil.checkPassword(password, encodedPassword));
    }
}
