package me.xuewen.chat.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public final class JsonUtil {
    private static ObjectMapper objectMapper;

    public static <T> T parse(String data, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(data, clazz);
    }

    public static String stringify(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        JsonUtil.objectMapper = objectMapper;
    }
}
