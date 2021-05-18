package me.xuewen.chat.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {
    private static final long serialVersionUID = 2186078765283932978L;
    private String code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
}
