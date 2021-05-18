package me.xuewen.chat.service;

public interface EmailService {
    void sendMail(String toEmail, String subject, String content);

    boolean isExist(String email);
}
