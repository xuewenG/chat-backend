package me.xuewen.chat.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.digest.DigestUtil;

public final class PasswordUtil {
    private PasswordUtil() {
    }

    public static String encode(String plainPassword) {
        return Convert.toHex(DigestUtil.sha256(plainPassword));
    }

    public static boolean checkPassword(String plainPassword, String encodePassword) {
        return encode(plainPassword).equals(encodePassword);
    }
}
