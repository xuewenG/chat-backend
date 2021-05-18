package me.xuewen.chat.exception;

import me.xuewen.chat.constant.code.UserResponseCode;

public class InvalidPasswordException extends BaseException {
    public InvalidPasswordException(String credential) {
        super(UserResponseCode.INVALID_PASSWORD,
                String.format("Invalid Password, Credential: %s",
                        credential));
    }
}
