package me.xuewen.chat.exception;

import me.xuewen.chat.constant.code.UserResponseCode;

public class InvalidLoginTypeException extends BaseException {
    public InvalidLoginTypeException(Integer type) {
        super(UserResponseCode.INVALID_LOGIN_TYPE,
                String.format("Invalid Login Type: %d", type));
    }
}
