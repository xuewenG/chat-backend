package me.xuewen.chat.socket.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocketMessage implements Serializable {
    private static final long serialVersionUID = -1605332620227798017L;
    private Integer type;
    private String data;
}